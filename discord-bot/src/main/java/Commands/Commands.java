package Commands;

import com.princecharming.Command;
import com.princecharming.CommandManager;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Commands implements Command {

    public final CommandManager cm;

    public Commands(CommandManager cm){
        this.cm = cm;
    }


    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.isEmpty()){
            EmbedBuilder e = new EmbedBuilder()
                    .setTitle(":robot:  List of all commands :robot: ")
                    .setThumbnail(event.getGuild().getIconUrl())
                    .setFooter("Bot owner ~"+Constants.OWNER_NAME);

            StringBuilder desc = e.getDescriptionBuilder();
            cm.getCommands().forEach(command -> {
                desc.append(" **").append(command.getCommandName()+"** -"+command.getHelp()+"\n**--------------------**\n");
            });
            event.getTextChannel().sendMessageEmbeds(e.build()).queue();
        }
    }

    @Override
    public String getCommandName() {
        return "commands";
    }

    @Override
    public String getHelp() {
        return "Lists all commands you can give the bot.\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
