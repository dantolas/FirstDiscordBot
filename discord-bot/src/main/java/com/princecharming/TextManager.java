package com.princecharming;

import Commands.HangmanPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.util.ArrayList;
import java.util.List;

public class TextManager {

    private List<HangmanPlayer> hangmanPlayers = new ArrayList<HangmanPlayer>();
    private List<String> parrotList = new ArrayList<>();



    public List<HangmanPlayer> getHangmanPlayers(){
        return hangmanPlayers;
    }

    public List<String> getParrotList(){
        return parrotList;
    }

    public HangmanPlayer getHangmanPlayer(String username){
        for(HangmanPlayer player: hangmanPlayers){
            if(player.getUsername().equals(username)) return player;
        }
        return null;
    }

    //Handles all Message Reactions fired
    void handle(MessageReactionAddEvent event){

        if(event.getUser().isBot()){
            return;
        }
        System.out.println("Text Manager received a reaction event");

        if(!Constants.colorEmbedIds.contains(event.getMessageIdLong())){
            return;
        }
        colorEmbedReaction(event);
    }

    private void colorEmbedReaction(MessageReactionAddEvent event){
        Guild guild = event.getGuild();
        String emojiName = event.getReactionEmote().getName();

        event.getTextChannel().sendMessage("Reaction to color picker spotted"+event.getReactionEmote().getName()).queue();
        List<Role> roles = guild.getRolesByName(emojiName,true);
        if(roles == null || roles.size() == 0){
            event.getTextChannel().sendMessage("No role found").queue();
            return;
        }
    }

    //Handles all messages fired
    public void handle(MessageReceivedEvent event){
        String username = event.getAuthor().getName();
        String message = event.getMessage().getContentRaw();

        System.out.println("Text manager has received a message");

        String[] tagSplit = event.getAuthor().getAsTag().split("#");
        String authorTag = tagSplit[1];
        String userTag = event.getAuthor().getName();



        //Checks for parroting users
        if(parrotList.contains(authorTag)){
            if(message.equalsIgnoreCase("i'm dumb") || message.equalsIgnoreCase("i'm stupid")|| message.equalsIgnoreCase("im dumb") ||message.equalsIgnoreCase("im stupid")){
                event.getTextChannel().sendMessage("We know you are.").queue();
            }else {
                event.getTextChannel().sendMessage(event.getMessage().getContentRaw()).queue();
            }
        }

        //Checks for hangman players
        if(hangmanPlayers.contains(HangmanPlayer.getByName(this.hangmanPlayers,username))){
            char letter =  message.charAt(0);
            HangmanPlayer player = getHangmanPlayer(username);
            boolean hit = hangmanContainsLetter(player,letter);
            if(hit){
                if(player.getWordBeingGuessed().equals(player.getLastGuess())){
                    event.getTextChannel().sendMessage("`You have won!`\n"+"The word was:"+player.getWordBeingGuessed()).queue();
                    hangmanPlayers.remove(HangmanPlayer.getByName(this.hangmanPlayers,username));
                }else{
                 event.getTextChannel().sendMessage(player.getLastGuess()).queue();
                }
            }else if(!hit){
                if(player.getMistakes() == player.hangmanStates.length-1){
                    event.getTextChannel().sendMessage("`You have lost!`\n"+"The word was:"+player.getWordBeingGuessed()+"\n"+player.hangmanStates[player.getMistakes()]).queue();
                    hangmanPlayers.remove(HangmanPlayer.getByName(this.hangmanPlayers,username));
                }else{
                    event.getTextChannel().sendMessage(player.hangmanStates[player.getMistakes()]+"\n"+player.getLastGuess()).queue();
                }
            }
        }



    }


    //Hangman related function
    //Checks if the player got at least one character right, if yes then return true
    //Also takes care of changing the last guess and incrementing player mistakes
    private boolean hangmanContainsLetter(HangmanPlayer player, char playerInput){

        StringBuilder builder = new StringBuilder(player.getLastGuess());
        int i = 0;
        int matches = 0;
        for(char x : player.getWordBeingGuessed().toLowerCase().toCharArray()){
            if(x == playerInput){
                builder.setCharAt(i,playerInput);
                matches++;
            }
            i++;
        }
        if(matches == 0){
            player.incrementMistakes();
            return false;
        }
        player.setLastGuess(builder.toString());
        return true;

    }


}
