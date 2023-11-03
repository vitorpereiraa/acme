package com.isep.acme.model;

public class Utils {
    public static String GetMiddleOfString(String input){
        int position;

        if (input.length() % 2 == 1) {
            position = input.length() / 2 + 1;
        } else {
            position = input.length() / 2 ;
        }

        return input.substring(position - 5, position + 5);
    }
}
