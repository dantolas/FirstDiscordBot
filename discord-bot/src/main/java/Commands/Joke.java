package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//The bot will tell you a (very offensive) joke
public class Joke implements Command {

    //List for storing all the jokes

    InputStream jokesFile = this.getClass().getClassLoader().getResourceAsStream("jokes.csv");

    private static ArrayList<String> jokes;
    private static Random rng = new Random();

    //Gets a random joke from the list
    //When run for the first time, it needs to fill the jokes list. The path to the jokes.csv file is a hard coded path, and must be slightly changed on different PC's for this command to work
    public String getRandomJoke() throws IOException {
        if(jokes ==null){
            jokes = new ArrayList<String>();
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(jokesFile));
            String currentLine;
            while ((currentLine = reader.readLine()) != null){
                jokes.add(currentLine);
            }
        }

        int random = rng.nextInt(jokes.size() / 2 + jokes.size() / 2 );
        return jokes.get(random);

    }


    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        try {
            event.getTextChannel().sendMessage(getRandomJoke()).queue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            event.getTextChannel().sendMessage("Something went wrong").queue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "joke";
    }

    @Override
    public String getHelp() {
        return "The bot tries his best to make you laugh.\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
