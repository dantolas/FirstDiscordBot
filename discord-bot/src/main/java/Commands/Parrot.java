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
        args.forEach(argument ->{
            String username = argument.replaceFirst("#","");
            if(!cm.parrotList.contains(username)){
                cm.parrotList.add(username);

            }else {
                cm.parrotList.remove(username);
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
