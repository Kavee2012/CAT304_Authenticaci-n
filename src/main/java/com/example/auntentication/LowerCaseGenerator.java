package com.example.auntentication;

public class LowerCaseGenerator extends Passwordgenarator2{
    private static final char CHAR_A = 'a';
    private static final char CHAR_Z = 'z';


    @Override
    public String getChar() {
        return String.valueOf((char) (Helper.randomChar(CHAR_A,CHAR_Z)));
    }
}
