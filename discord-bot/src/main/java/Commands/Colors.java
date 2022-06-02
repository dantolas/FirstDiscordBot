package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Colors implements Command {

    public void addColor(String colorname){

    }

    public void run(MessageEmbedEvent event){

    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(!args.isEmpty() && args.size()<2){
            Constants.colorEmojis.put(args.get(0),args.get(1));
            return;
        }

        StringBuilder sBuilder = new StringBuilder();

        Map<String,String> map = Constants.colorEmojis;
        for(Map.Entry<String,String> entry : Constants.colorEmojis.entrySet()){
            sBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }

        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Color Picker 9000 "+"\uD83C\uDF08")
                .setDescription(sBuilder)
                .setFooter("Pick a card! Any card....");

        event.getTextChannel().sendMessageEmbeds(builder.build()).queue(message -> {
            Constants.colorEmojis.values().forEach(emoji ->{
                message.addReaction(emoji).queue();
            });


            Constants.colorEmbedIds.add(message.getIdLong());
        });

    }

    @Override
    public String getCommandName() {
        return "colors";
    }

    @Override
    public String getHelp() {
        return "Let's you pick your color if the server has any\n"+
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+
                "\nIf your color role exist but the bot doesn't show it, add it like this\n"+
                "->do "+Constants.BOT_COMMAND_PREFIX+getCommandName()+" <color> <:emoji:>";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
