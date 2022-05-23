package com.princecharming;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Commands implements Serializable {
    private static ArrayList<String> jokes;
    private static ArrayList<String> parrotList = new ArrayList<>();
    private static Scanner scanner;
    private static Random rng = new Random();
    public static String commands =
            "!commands : Lists all commands you can yelll at the bot.\n" +
            "!joke : The bot tries his best to make you laugh.\n" +
            "!parrot : The bot will parrot the person of your choosing (do !parrot -username)";


    //region<GetRandomJoke - returns a joke>
    public static String getRandomJoke() throws FileNotFoundException {
        if(jokes ==null){
            jokes = new ArrayList<String>();
            scanner = new Scanner(new File("main/resources/jokes.csv"));
            while (scanner.hasNextLine()){
                jokes.add(scanner.nextLine());
            }
        }

        return jokes.get(rng.nextInt(jokes.size()));

    }
    //endregion

    //region<Parrot-parrots a user>
    public static void parrot(String username){
        scanner = new Scanner(System.in);
        if(!parrotList.contains(username)){
            parrotList.add(username);
            return;
        }
        parrotList.remove(username);
    }


    public static boolean isOnParrotList(String username){
        return parrotList.contains(username);
    }
    //endregion


}
