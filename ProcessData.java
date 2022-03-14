package com.company.SpringAssessment;

import com.company.Day12.Sorting;

public class ProcessData {
    ReaderInterface readerInterface;
    public void addReadingType(ReaderInterface _readerInterface)
    {
        readerInterface = _readerInterface;
    }
    void demo()
    {
        readerInterface.read();
    }
}
