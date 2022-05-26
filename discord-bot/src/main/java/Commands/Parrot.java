package Commands;

import com.princecharming.Command;
import com.princecharming.CommandManager;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class Parrot implements Command {

    public final CommandManager cm;

    public Parrot(CommandManager cm) {
        this.cm = cm;
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.isEmpty()){
            event.getTextChannel().sendMessage(getHelp()).queue();
            return;
        }
        args.forEach(argument ->{
            String userTag = argument.replaceFirst("#","");
            if(!cm.tm.getParrotList().contains(userTag)){
                cm.tm.getParrotList().add(userTag);
                event.getTextChannel().sendMessage("*"+userTag+"* is being parroted.").queue();

            }else {
                cm.tm.getParrotList().remove(userTag);
                event.getTextChannel().sendMessage("*"+userTag+"* is being parroted no longer.").queue();
            }
        });
    }

    @Override
    public String getCommandName() {
        return "parrot";
    }

    @Override
    public String getHelp() {
        return "This will parrot a user.\n" +
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+" <discordTag>";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
