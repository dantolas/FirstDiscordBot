package com.princecharming;

import Commands.HangmanPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import java.util.ArrayList;
import java.util.List;

//The "sub manager" for CommandManager, exists basically to not clutter up the CommandManager.handle method too much and leave just the commands to the command manager,
// while textManager takes care of general reactions to text messages and some other stuff
public class TextManager {

    //TextManager also handles the hangman game (Commands.Hangman) and parroting users (Commands.Parrot)
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
        System.out.println("Text Manager received a reaction event ~ System time:" + System.currentTimeMillis() / 1000);

        if(!Constants.colorEmbedIds.contains(event.getMessageIdLong())){
            return;
        }
        colorEmbedReaction(event);
    }


    //This method has to do with the Commands.Colors command, when someone reacts to a specific "Color Picker" embed, this method handles it
    private void colorEmbedReaction(MessageReactionAddEvent event){
        Guild guild = event.getGuild();
        String emojiUnicode = event.getReactionEmote().getEmoji();
        String color = Constants.emojiUniToColors.get(emojiUnicode);
        if(color == null){
            System.out.println("Problemos");
            return;
        }

        List<Role> roles = guild.getRolesByName(color,true);

        if(roles == null || roles.size() == 0){
            event.getTextChannel().sendMessage("`No role found`").queue();
            return;
        }

        event.getTextChannel().sendMessage("`Assigning role to "+event.getUser().getName()+" -"+color+"`").queue();

        StringBuilder builder = new StringBuilder();
        roles.forEach(role -> {
            builder.append("**").append(role.getName()).append("** ");
        });

        event.getTextChannel().sendMessage("Role(s) found:" + builder+"").queue();

        try {
            //if the user doesn't have the role, he will get it
            if(event.getMember().getRoles().contains(roles.get(0))){
                event.getTextChannel().sendMessage("You already have that role!:skull: ").queue();
                return;
            }
            guild.addRoleToMember(UserSnowflake.fromId(event.getUser().getIdLong()), roles.get(0)).queue();
            event.getTextChannel().sendMessage("Role assigned :thumbsup:").queue();
        }catch (HierarchyException e){
            event.getTextChannel().sendMessage("That role is above me in terms of hierarchy, I cannot give that role to you, sorry :(").queue();
        }

    }

    //Handles all messages fired
    public void handle(MessageReceivedEvent event){
        String username = event.getAuthor().getName();
        String message = event.getMessage().getContentRaw();

        System.out.println("Text manager has received a message ~ System time" + System.currentTimeMillis() / 1000);

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
