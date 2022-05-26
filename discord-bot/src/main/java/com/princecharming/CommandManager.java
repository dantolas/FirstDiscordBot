package com.princecharming;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

//region<Imported Commands>
import Commands.Joke;
import Commands.Commands;
import Commands.Help;
import Commands.Play;
import Commands.PlayNext;
import Commands.Parrot;
import Commands.Shutdown;
import Commands.SniperPiss;
import Commands.RickRoll;
import Commands.PausePlay;
import Commands.VoiceDisconnect;
import Commands.Ping;
import Commands.Hangman;


//endregion


import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {


    private final Map<String,Command> commands = new HashMap<>();
    private final Map<String,Command> secretCommands = new HashMap<>();
    public final TextManager tm;


    public CommandManager() {
        tm = new TextManager();
        addCommand(new Commands(this));
        addCommand(new Help(this));
        addCommand(new Joke());
        addCommand(new Play());
        addCommand(new PlayNext());
        addCommand(new PausePlay());
        addCommand(new Parrot(this));
        addCommand(new Shutdown());
        addCommand(new VoiceDisconnect());
        addCommand(new Ping());
        addCommand(new Hangman(this));

        addSecretCommand(new SniperPiss());
        addSecretCommand(new RickRoll());
    }

    private void addCommand(Command command){
        if(!commands.containsKey(command.getCommandName())){
            commands.put(command.getCommandName(),command);
        }
    }

    private void addSecretCommand(Command command){
        if(!secretCommands.containsKey(command.getCommandName())){
            secretCommands.put(command.getCommandName(),command);
        }
    }

    public Map<String,Command> getCommandsList(){
        return commands;
    }

    public Collection<Command> getCommands(){
        return commands.values();
    }

    public Command getCommand(String cName){
        if(cName == null){
            return null;
        }
        return commands.get(cName);
    }

    void handle(MessageReceivedEvent event){

        final String message = event.getMessage().getContentRaw();
        System.out.println("Command manager has recieved a message");

        if(!message.startsWith(Constants.BOT_COMMAND_PREFIX) && !message.endsWith(Constants.BOT_SECRET_COMMAND_AFTERFIX)){
            tm.handle(event);
            System.out.println("Command manager has passed it on to tm to handle");
            return;
        }

        //region<isSecretCommand check>
        //Checks if the message starts with the secret command prefix, executes if true
        if(message.endsWith(Constants.BOT_SECRET_COMMAND_AFTERFIX)){
            final String[] msgSplit = message
                    .replaceAll(Pattern.quote(Constants.BOT_SECRET_COMMAND_AFTERFIX),"")
                    .split("\\s+");
            final String commandName = msgSplit[0].toLowerCase();

            if(secretCommands.containsKey(commandName)){
                final List<String> args = Arrays.asList(msgSplit).subList(1, msgSplit.length);
                secretCommands.get(commandName).run(args,event);
            }
            return;
        }
        //endregion

        //region<IsCommand check>
        //Checks if the message starts with a command prefix
        if(!message.startsWith(Constants.BOT_COMMAND_PREFIX)){
            return;
        }
        //endregion

        final String[] msgSplit = message
                                    .replaceFirst(Pattern.quote(Constants.BOT_COMMAND_PREFIX),"")
                                    .split("\\s+");
        final String commandName = msgSplit[0].toLowerCase();

        //region<needOwnerPermission check & respond if the command is unknow>
        //Lot of nested ifs, let's describe them
        //Checks if the command that came in is on the commands list, continues if true
        if(commands.containsKey(commandName)){
            //Checks if the command needs bot owner permission, continues if false
            if(!commands.get(commandName).needOwner()){
                final List<String> args = Arrays.asList(msgSplit).subList(1, msgSplit.length);
                commands.get(commandName).run(args,event);
            }else { //This happens if the command does need an owner permission
                //If the author of the command is the bot owner, continue
                if(event.getAuthor().getIdLong() == Constants.OWNDER_ID) {
                    final List<String> args = Arrays.asList(msgSplit).subList(1, msgSplit.length);
                    commands.get(commandName).run(args, event);
                }else { //If the author is not the bot owner, do this
                    event.getTextChannel().sendMessage("You need **owner permission** for that command.").queue();
                }
            }
        }else {
            event.getTextChannel().sendMessage("I don't know that command :skull:").queue();
        }
        //endregion
    }

}
