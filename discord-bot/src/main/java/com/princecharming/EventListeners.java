package com.princecharming;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        //The matcher is used only for !command messages
        Pattern pattern = Pattern.compile("(^!\\w*)\\s*([-#]\\w*)*");
        Matcher matcher = pattern.matcher(message);
        //region<!Command messages>
        if (matcher.matches()){

            switch (matcher.group(1).toLowerCase()){

                //region<Commands> Shows all bot commands
                case "!commands" -> event.getTextChannel().sendMessage(Commands.commands).queue();
                //endregion

                //region<Joke command>
                case "!joke" ->{
                    try {
                        event.getTextChannel().sendMessage(Commands.getRandomJoke()).queue();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        event.getTextChannel().sendMessage("Something went wrong!!!").queue();
                    }
                }
                //endregion

                //region<Parrot command- parrots a user>

                case "!parrot" -> {
                    try{
                        String nameOrIdCaught = matcher.group(2).substring(1);
                        Commands.parrot(nameOrIdCaught);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                //endregion


                default -> event.getTextChannel().sendMessage("I don't know how to respond to that.").queue();

            }
        }
        //endregion
        //Different actions based on the message
        //I tried using a switch to not make it so disgusting, but you cannot put a matcher.group match into a switch because Java,
        //so it shall remain disgustingly made from if statements
/*
        if(matcher.group(1).equalsIgnoreCase("!commands")){
            event.getTextChannel().sendMessage(Commands.commands).queue();
        }

        else if (matcher.group(1).equalsIgnoreCase("!joke")){
            try {
                event.getTextChannel().sendMessage(Commands.getRandomJoke()).queue();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                event.getTextChannel().sendMessage("Something went wrong!!!").queue();
            }
        }

        else if(matcher.group(1).equalsIgnoreCase("!joke")){
            System.out.println("parrot");
        }*/
        
        //Repeats parroted users*/
        if(Commands.isOnParrotList(event.getAuthor().getName())){
            event.getTextChannel().sendMessage(message).queue();
        }
    }
    //endregion


}
