package com.princecharming;

import Commands.HangmanPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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



    void handle(MessageEmbedEvent event){
        MessageEmbed embed = event.getMessageEmbeds().get(0);

        if (embed.getFooter().getText().equalsIgnoreCase("color")){
            event.getTextChannel().sendMessage("ColorEmbedRecieved").queue();
        }

    }

    public void handle(MessageReceivedEvent event){
        String username = event.getAuthor().getName();
        String message = event.getMessage().getContentRaw();

        System.out.println("Text manager has received a message");

        String[] tagSplit = event.getAuthor().getAsTag().split("#");
        String authorTag = tagSplit[1];
        String userTag = event.getAuthor().getName();
        //Checks for parroting users, and hangman players
        if(parrotList.contains(authorTag)){
            if(message.equalsIgnoreCase("i'm dumb") || message.equalsIgnoreCase("i'm stupid")|| message.equalsIgnoreCase("im dumb") ||message.equalsIgnoreCase("im stupid")){
                event.getTextChannel().sendMessage("We know you are.").queue();
            }else {
                event.getTextChannel().sendMessage(event.getMessage().getContentRaw()).queue();
            }
        }


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



    //Checks if the player got at least one character right, if yes then return true
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
