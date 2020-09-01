package com.starkIndustries.game;

import java.util.Scanner;

public class

Prompter {

    public static String ask(String question) {
        Scanner scan = new Scanner(System.in);
        System.out.println(question);
        String userInput = scan.nextLine();
        System.out.println("You answered: " + userInput);
        return userInput;
    }

    public static void promptEnterKey(){
        System.out.println("\n ** Please press \"ENTER\" to continue **");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static String handleResponse(String question){
        String answer = "I am a default String";
        while ( !answer.equals("EXIT") && !answer.equals("A") && !answer.equals("B")){
            System.out.println("Please enter a valid input");
            answer = ask(question).toUpperCase();
        }
        return answer;
    }
}
