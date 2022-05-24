package com.princecharming;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface Command {

    void run(List<String> args, MessageReceivedEvent event);
    String getCommandName();
    String getHelp();
    boolean needOwner();

}
