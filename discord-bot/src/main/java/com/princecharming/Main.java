package com.princecharming;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;


public class Main{
    public static void main(String[] args) throws LoginException, InterruptedException {


        //region<Launching the bot>!!Remember to make the token as an argument when running the bot
        JDA bot = JDABuilder.createDefault("insert bot token here")
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing(Constants.BOT_ACTIVITY))
                .addEventListeners(new Listener())
                .build().awaitReady();

        System.out.println("Project working directory"+System.getProperty("user.dir"));
        //endregion





    }

}
