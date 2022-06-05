package Commands;

import com.princecharming.Command;
import com.princecharming.CommandManager;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

//Command that will give you the description of any and multiple commands
public class Help implements Command {

    public final CommandManager cm;

    public Help(CommandManager cm){
        this.cm = cm;
    }


    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.isEmpty()){
            event.getTextChannel().sendMessage(getHelp()).queue();
            return;
        }
        args.forEach(argument ->{
            if(cm.getCommandsList().containsKey(argument)){
                event.getTextChannel().sendMessage(cm.getCommand(argument).getHelp()).queue();
                return;
            }
            event.getTextChannel().sendMessage("The \""+argument+"\" command does not exist.").queue();
        });

    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Gives you the description of one or multiple commands\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+" <command> for one command, or <command> <command> <command>... for multiple commands.\n"+
                "   ->do "+Constants.BOT_COMMAND_PREFIX+"commands for lit of all commands.";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
