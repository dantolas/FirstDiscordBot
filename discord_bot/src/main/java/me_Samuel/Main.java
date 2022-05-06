package me_Samuel;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {

        JDA bot = JDABuilder.createDefault("Token").setActivity(Activity.playing("With yo dad")).build();
    }
}
