package Commands;

import com.princecharming.Command;
import com.princecharming.CommandManager;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.*;


//Command that let's you play hangman with the bot
public class Hangman implements Command {

    public CommandManager cm;
    //Map of the available categories and their words
    private Map<String,List<String>> words = new HashMap<>();



    public Hangman(CommandManager cm) {
        //words variable is filled with keys and values from /main/resources/hangmanWords.csv
        this.words = fillmap();
        this.cm = cm;
    }


    //Method tha fills the words Hashmap with keys as categories and values as lists of words
    public Map<String,List<String>> fillmap(){
        Scanner scanner;
        Map<String,List<String>> map = new HashMap<>();
        try {
            InputStream jokesFile = this.getClass().getClassLoader().getResourceAsStream("hangmanWords.csv");

            BufferedReader reader = new BufferedReader(new InputStreamReader(jokesFile));

            String nextLine = "";

            while ((nextLine = reader.readLine()) != null){
                String[] categoryLineSplit = nextLine.split(":");
                String[] words = categoryLineSplit[1].split(";");
                if(!this.words.containsKey(categoryLineSplit[0])){
                    map.put(categoryLineSplit[0], Arrays.asList(words));
                }
            }





        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }


    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        String username = event.getAuthor().getName();
        Random rng = new Random();


        if(!cm.tm.getHangmanPlayers().contains(HangmanPlayer.getByName(cm.tm.getHangmanPlayers(),username))){
            if(args.isEmpty()){
                event.getTextChannel().sendMessage(getHelp()).queue();
                return;
            }
            if (words.containsKey(args.get(0))){
                List<String> wordsList = words.get(args.get(0));
                cm.tm.getHangmanPlayers().add(new HangmanPlayer(username,wordsList.get(rng.nextInt(wordsList.size())).toLowerCase()));
                event.getTextChannel().sendMessage("`You are now playing!!!`" +" ~ *"+username+"*").queue();
                event.getTextChannel().sendMessage(HangmanPlayer.getByName(cm.tm.getHangmanPlayers(),username).getLastGuess()).queue();
            }else {
                event.getTextChannel().sendMessage("That category is unknown.").queue();
                return;
            }
        }else {
            cm.tm.getHangmanPlayers().remove(HangmanPlayer.getByName(cm.tm.getHangmanPlayers(),username));
            event.getTextChannel().sendMessage("`You've stopped playing!`"+" ~ *"+username+"*").queue();
            return;
        }



    }

    @Override
    public String getCommandName() {
        return "hangman";
    }

    @Override
    public String getHelp() {
        String categories = "";
        for(String key : words.keySet()){
            categories += "`"+key+"`\n";
        }
        return "The bot will play hangman with you.\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+" <category>\n"+"Available categories:\n"+ categories+
                "\n"+"  to stop playing just type the command again";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
