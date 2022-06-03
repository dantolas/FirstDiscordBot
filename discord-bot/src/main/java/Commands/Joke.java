package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Joke implements Command {

    private static ArrayList<String> jokes;
    private static Scanner scanner;
    private static Random rng = new Random();


    public static String getRandomJoke() throws FileNotFoundException {
        if(jokes ==null){
            jokes = new ArrayList<String>();
            scanner = new Scanner(new File("discord-bot/src/main/resources/jokes.csv"));
            while (scanner.hasNextLine()){
                jokes.add(scanner.nextLine());
            }
        }

        return jokes.get(rng.nextInt(jokes.size()));

    }


    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        try {
            event.getTextChannel().sendMessage(getRandomJoke()).queue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            event.getTextChannel().sendMessage("Something went wrong").queue();
        }
    }

    @Override
    public String getCommandName() {
        return "joke";
    }

    @Override
    public String getHelp() {
        return "The bot tries his best to make you laugh.\n" +
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
