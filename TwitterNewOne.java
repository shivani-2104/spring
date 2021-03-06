package com.company.Day14Part2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class TwitterNewOne {
   List<User> listOfUsers=new ArrayList<>();
    @PostMapping("/create1")
    private ResponseEntity<Object> createAccount(@RequestBody User user) {
        ResponseEntity<Object> responseEntity = null;
        int userId = user.getUserId();
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

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
           User u1=new User(userId,name,email,password);
           listOfUsers.add(u1);
            responseEntity = new ResponseEntity<>("Creation successful",
                    HttpStatus.OK);
            System.out.println(listOfUsers);
        }
        return responseEntity;
    }
    @GetMapping("/fetch1")
    private List fetchAllUsers()
    {
      return listOfUsers;
    }
@PostMapping("/newTweet1")
private ResponseEntity<Object> newTweet(@RequestBody Tweet tweet)
{
    String email=tweet.getEmail();
    String password=tweet.getPassword();
    ResponseEntity<Object> responseEntity = null;
    for(int i=0;i<listOfUsers.size();i++)
    {
        if(listOfUsers.get(i).getEmail().equals(email)&&listOfUsers.get(i).getPassword().equals(password))
        {
            if(listOfUsers.get(i).getTweet()!=null)
            {
                List newTweetsList=new ArrayList();
                for(Object s:listOfUsers.get(i).getTweet())
                {
                    newTweetsList.add(s);
                }
                newTweetsList.add(tweet.getTweet());

                listOfUsers.get(i).setTweet(newTweetsList);
            }
            else {
                listOfUsers.get(i).setTweet(Arrays.asList(tweet.getTweet()));
                responseEntity = new ResponseEntity<>("Successfully Creation",
                        HttpStatus.OK);
            }
        }
    }
    if(responseEntity==null)
    {
        responseEntity = new ResponseEntity<>("Invalid UserName and Password",
                HttpStatus.BAD_REQUEST);
    }
    return responseEntity;
}
@PutMapping("/update1")
private ResponseEntity<Object> update1(@RequestBody User user)
{
    String email=user.getEmail();
    ResponseEntity responseEntity=null;
    for(int i=0;i<listOfUsers.size();i++)
    {
        if(listOfUsers.get(i).getEmail().equals(email))
        {
            listOfUsers.remove(i);
            User u1=new User(user.getUserId(), user.getName(), user.getEmail(),user.getPassword());
            listOfUsers.add(u1);
            responseEntity = new ResponseEntity<>("Update successfully",
                    HttpStatus.OK);
        }

    }
    if(responseEntity==null)
    {
        responseEntity = new ResponseEntity<>("User doesn't exist",
                HttpStatus.BAD_REQUEST);
    }
    return responseEntity;
}
@DeleteMapping("/delete1")
ResponseEntity remove(@RequestParam String email,@RequestParam String password)
{
    ResponseEntity responseEntity=null;
    for(int i=0;i<listOfUsers.size();i++)
    {
        if(listOfUsers.get(i).getEmail().equals(email) && listOfUsers.get(i).getPassword().equals(password))
        {
            listOfUsers.remove(i);
            responseEntity = new ResponseEntity<>("Delete SuccessFully",
                    HttpStatus.OK);
        }
    }
    if(responseEntity==null)
    {
        responseEntity = new ResponseEntity<>("User doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    return responseEntity;
}
//fetch tweets of specific Users
@GetMapping("fetch2")
private List fetch2(@RequestParam String email)
{
    for(int i=0;i<listOfUsers.size();i++)
    {
        if(listOfUsers.get(i).getEmail().equals(email))
            return listOfUsers.get(i).getTweet();
    }
    return Arrays.asList("No tweets Found");
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
    boolean emailValidator(String value)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return(matcher.matches());
    }
}
