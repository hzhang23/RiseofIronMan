package com.starkIndustries.game;

import java.util.Scanner;

public class Prompter {

    public static String ask(String question) {

        Scanner scan = new Scanner(System.in);
        System.out.println(question);

        String userInput = scan.nextLine();
        System.out.println("You answered: " + userInput);
        return userInput;
    }
}
