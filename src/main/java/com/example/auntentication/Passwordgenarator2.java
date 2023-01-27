package com.example.auntentication;

import java.util.ArrayList;

public abstract class Passwordgenarator2 {
    private static ArrayList<Passwordgenarator2> generators;

    public static void clear(){
        if(generators != null) generators.clear();
        else generators = new ArrayList<>();
    }

    public static boolean isEmpty(){
        return generators.isEmpty();
    }

    public static void add(Passwordgenarator2 pwdg){
        generators.add(pwdg);
    }

    public abstract String getChar();

    private static Passwordgenarator2 getRandomPassGen(){
        if(generators == null) {
            generators = new ArrayList<>();
            add(new LowerCaseGenerator());
            add(new NumericGenerator());
            add(new SpecialCharGenerator());
            add(new UpperCaseGenerator());
        }

        if(generators.size() == 1) return generators.get(0);
        int randomIndex = Helper.randomVal(generators.size());
        return generators.get(randomIndex);
    }

    public static String generatePassword(int sizeOfPassword){
        StringBuilder password = new StringBuilder();

        while (sizeOfPassword !=0){
            password.append(getRandomPassGen().getChar());
            sizeOfPassword--;
        }

        return password.toString();
    }

}
