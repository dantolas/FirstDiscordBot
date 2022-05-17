package com.princecharming;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class EventListeners extends ListenerAdapter {


    //region<Message listener>
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        //If the author of the message sent is a bot, return
        if(event.getAuthor().isBot()){
            return;
        }
        //Storing the message triggering the listener
        String message = event.getMessage().getContentRaw();

        //Different actions based on the message
        switch (message){
            //region<Commands> Shows all bot commands
            case "!commands" -> {
                String commands =
                        "!commands : Lists all commands you can yelll at the bot.\n" +
                        "!joke : The bot tries his best to make you laugh.";

                event.getTextChannel().sendMessage(commands).queue();

            }
            //endregion

            default -> event.getTextChannel().sendMessage(message).queue();

        }
    }
    //endregion


}
