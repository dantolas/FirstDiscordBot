package com.princecharming;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main{
    public static void main(String[] args) throws LoginException, InterruptedException {


        //region<Launching the bot>!!Remember to make the token as an argument when running the bot
        JDA bot = JDABuilder.createDefault("OTc2MDAxMDIxMTk1MjA2NzA2.GvPplw.Gxx5nEbm7zRNTk1DaAl2WNQHGefQnQ7-lXl47Y")
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing(Constants.BOT_ACTIVITY))
                .addEventListeners(new Listener())
                .build().awaitReady();

        //endregion





    }

}
