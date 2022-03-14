package com.company.SpringAssessment;

public class EagerLoading {
    static EagerLoading eagerLoading=new EagerLoading();
    private EagerLoading()
    {

    }
    static EagerLoading getInstance()
    {
        return eagerLoading;
    }
}
