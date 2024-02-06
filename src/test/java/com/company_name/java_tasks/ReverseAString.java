package com.company_name.java_tasks;

import java.util.ArrayList;

public class ReverseAString {

    // create a method to reverse a string without using String Builder

    public static String reverse (String str){

        return str;
    }


    public static void main(String[] args) {

        String [] arr = {"Hello", "olleH", "Buy", "$^&#("};

        for(String each: arr){
            System.out.println(reverse(each));
        }
    }

}
