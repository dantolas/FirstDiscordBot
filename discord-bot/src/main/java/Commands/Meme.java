package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meme implements Command {

    private Map<String,String> currentMeme;

    @Override
    public void run(List<String> args, MessageReceivedEvent event){

        URL memeURL;

         try {
             memeURL = new URL("https://meme-api.herokuapp.com/gimme");
             BufferedReader reader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));
             String line;
             while ((line = reader.readLine()) != null){
                 this.currentMeme = readJSONAsMap(line);
             }

             EmbedBuilder builder = new EmbedBuilder()
                     .setTitle(getMemeTitle())
                     .setDescription("posted by *"+getMemeAuthor()+"*\n on subreddit *"+getSubrredit()+"*"+" with *"+getUpvotes()+"* :arrow_up: ")
                     .setImage(getMemeUrl())
                     .setColor(Color.orange)
                     .setFooter(":link: "+getPostLink());


             event.getChannel().sendMessageEmbeds(builder.build()).queue();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
         }

    }

    @Override
    public String getCommandName() {
        return "meme";
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

    public String getMemeAuthor(){
        return this.currentMeme.get("author");
    }

    public String getMemeTitle(){
        return this.currentMeme.get("title");
    }

    public boolean getSpoiler(){
        return !this.currentMeme.get("spoiler").equals("false");
    }

    public boolean getNSFW(){
        return !this.currentMeme.get("nsfw").equals("false");
    }

    public String getPostLink(){
        return this.currentMeme.get("postLink");
    }

    public String getMemeUrl(){
        return this.currentMeme.get("url");
    }

    public String getSubrredit(){
        return this.currentMeme.get("subreddit");
    }

    public String getUpvotes(){
        return this.currentMeme.get("ups");
    }

    private Map<String,String> readJSONAsMap(String string){
        String[] split = string.split(",");
        for(int i = 0; i < split.length; i++){
           split[i] = split[i].replaceAll("(\")","");
        }
        List<String> listSplit = Arrays.asList(split);
        Map<String,String> map = new HashMap<>();
        String key = "";
        String value = "";
        for(String s : listSplit){
            if(s.equals("") || s.equals(" ")){ break;}

            String[] splitS = s.split("((?<!https):)");
            key = splitS[0];
            if(key.startsWith("{")){
                key = key.replaceFirst("\\{","");
            }
            if(!key.equals("preview") && splitS.length != 1) {
                value = splitS[1];
                map.put(key, value);
            }
        }
        return map;
    }
}
