package com.princecharming;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) throws LoginException, InterruptedException {


        System.out.println("Please enter your bot token: ");
        String token = new Scanner(System.in).next();
        token = token.replaceAll("\\s+","");



        //region<Launching the bot>
        JDA bot = JDABuilder.createDefault(token)
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing(Constants.BOT_ACTIVITY))
                .addEventListeners(new Listener())
                .build().awaitReady();

        System.out.println("Project working directory"+System.getProperty("user.dir"));
        //endregion





    }

}
