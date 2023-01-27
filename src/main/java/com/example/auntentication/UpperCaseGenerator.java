package com.example.auntentication;

public class UpperCaseGenerator extends Passwordgenarator2{
    private static final char CHAR_A = 'A';
    private static final char CHAR_Z = 'Z';


    @Override
    public String getChar() {
        return String.valueOf((char) (Helper.randomChar(CHAR_A,CHAR_Z)));
    }
}
