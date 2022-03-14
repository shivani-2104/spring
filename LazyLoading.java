package com.company.SpringAssessment;

public class LazyLoading {
    static LazyLoading lazyLoading=null;
    private LazyLoading()
    {

    }
    static LazyLoading getInstance()
    {
        if(lazyLoading==null)
            lazyLoading=new LazyLoading();
        return lazyLoading;
    }
}
