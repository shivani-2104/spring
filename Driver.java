package com.company.SpringAssessment;

public class Driver {
    public static void main(String[] args) {
        ProcessData processData=new ProcessData();
        processData.addReadingType(new CSVReader());
        processData.demo();
    }
}
