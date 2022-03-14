package com.company.FaceBook;

import com.company.Day18Part2.User;
import com.company.Day18Part2.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FacebookAPI {

    static String currentEmail;
    static String currentPassword;

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Autowired
    UserFBDao userFBDao;
    @PostMapping(value="/createuser" )
    @ResponseBody
    public ModelAndView createAccount111(@RequestBody MultiValueMap<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("register1");
        ResponseEntity<Object> responseEntity = null;
        String userId = formData.get("userId").get(0);
        String name = formData.get("name").get(0);
        String email = formData.get("email").get(0);
        String password = formData.get("password").get(0);
        int userId1 = Integer.parseInt(userId);
        if(containsInvalidChars(name))
        {
            responseEntity = new ResponseEntity<>("name contains invalid characters",
                    HttpStatus.BAD_REQUEST);
        }
        else if(!emailValidator(email))
        {
            responseEntity = new ResponseEntity<>("Invalid Email",
                    HttpStatus.BAD_REQUEST);
        }
        else if(password=="")
        {
            responseEntity = new ResponseEntity<>("Please enter password",
                    HttpStatus.BAD_REQUEST);
        }
        else
        {
            UserFB u1=new UserFB(userId1,name,email,password);
            userFBDao.create(u1);
            responseEntity = new ResponseEntity<>("Creation successful",
                    HttpStatus.OK);

        }
        return modelAndView;

    }
    boolean emailValidator(String value)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return(matcher.matches());
    }
    private boolean containsInvalidChars(String str) {
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                flag = false;
            }
        }
        return !flag;
    }
    //After Login Successfull
    @PostMapping("/login")
    public ModelAndView fetchUserTweets(@RequestBody MultiValueMap<String,String> formdata) {
        String email=formdata.get("email").get(0);
        String password=formdata.get("password").get(0);
        currentEmail=email;
        currentPassword=password;
        List lists=userFBDao.readByEmail(email,password);
        ModelAndView modelAndView = new ModelAndView("SpecificUserDetails");
        modelAndView.getModel().put("lists", lists);
        return modelAndView;
    }
@PostMapping("/post")
ResponseEntity post(@RequestBody MultiValueMap<String ,String> formData)
{
    ResponseEntity responseEntity=null;
    String post_data=formData.get("post").get(0);
    String email=formData.get("email").get(0);
    String password=formData.get("password").get(0);
    PostFB postFB=new PostFB(email,password,post_data);
    userFBDao.post(postFB);
    responseEntity = new ResponseEntity<>("Post successfully",
        HttpStatus.OK);
    return responseEntity;
}
    @GetMapping("/displayFBUserDetails")
    @ResponseBody
    public ModelAndView getUserDetails() {

        List list=userFBDao.readAll();
        System.out.println("list are "+list);
        ModelAndView modelAndView = new ModelAndView("allusers");
        modelAndView.getModel().put("list", list);
        return modelAndView;
    }
    @PostMapping("/follow")
    @ResponseBody
    public ResponseEntity follow(@RequestBody MultiValueMap <String,String > formData)
    {
        String email=formData.get("email").get(0);
         userFBDao.follow(email);
//        System.out.println("list are "+list);
//        ModelAndView modelAndView = new ModelAndView("allusers");
//        modelAndView.getModel().put("list", list);
        ResponseEntity responseEntity=new ResponseEntity("Follow successfull",HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/seeAllFollowes")
    ModelAndView seeAllFollowes()
    {
        List list=userFBDao.allFollwers();
        ModelAndView modelAndView = new ModelAndView("allFollowers");
        modelAndView.getModel().put("list", list);
        return modelAndView;
    }
       @PostMapping("/block")
    @ResponseBody
    public ResponseEntity block(@RequestBody MultiValueMap <String,String > formData)
    {
        String email=formData.get("email").get(0);
        userFBDao.block(email);
//        System.out.println("list are "+list);
//        ModelAndView modelAndView = new ModelAndView("allusers");
//        modelAndView.getModel().put("list", list);
        ResponseEntity responseEntity=new ResponseEntity("Block successfull",HttpStatus.OK);
        return responseEntity;
    }
     @GetMapping("/seeAllBlockedUsers")
    ModelAndView seeAllBlockedUsers()
    {
        List list=userFBDao.allBlockedUsers();
        ModelAndView modelAndView = new ModelAndView("allFollowers");
        modelAndView.getModel().put("list", list);
        return modelAndView;
    }
}
