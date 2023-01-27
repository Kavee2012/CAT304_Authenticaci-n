package com.example.auntentication;

public class SpecialCharGenerator extends Passwordgenarator2 {
    private static final char[] SPECIAL_CHAR_ARRAY = "?./!%*$^+-)]@(['{}#<>".toCharArray();

    @Override
    public String getChar() {
        return String.valueOf(SPECIAL_CHAR_ARRAY[Helper.randomChar(0, SPECIAL_CHAR_ARRAY.length - 1)]);
    }
}
