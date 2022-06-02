package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

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
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Color Picker 9000")
                //.setImage("main/resources/colorWheel.png")
                .setDescription("Pick a color! Any color...")
                .setFooter("color");

        event.getTextChannel().sendMessageEmbeds(builder.build()).queue();

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
