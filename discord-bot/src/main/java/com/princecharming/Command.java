package com.princecharming;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

//Command interface that all bot commands must implement

public interface Command {


    //In this method is the code that is ran everytime a command is issued
    void run(List<String> args, MessageReceivedEvent event);
    String getCommandName();
    String getHelp();

    //Sets if botOwner permission is required for the command
    boolean needOwner();

}
