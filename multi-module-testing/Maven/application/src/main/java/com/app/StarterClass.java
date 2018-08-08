package com.app;


import java.util.*;
import com.srv.InputValidation;
import com.srv.IntegerAsker;

public interface StarterClass{

    Scanner scan = new Scanner(System.in);
    IntegerAsker intAsker = new IntegerAsker(System.in, System.out);

    default Integer getInteger(int min, int max){
        InputValidation.setRange(min, max);
        System.out.println("Enter length of field");
        return InputValidation.getBoundIntegerFromUser(intAsker);
    }

    public void start();

}
