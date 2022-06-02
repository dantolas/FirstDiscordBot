package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Shutdown implements Command {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("Peace :v: ").queue();
        event.getJDA().shutdown();
        System.exit(0);
    }

    @Override
    public String getCommandName() {
        return "shutdown";
    }

    @Override
    public String getHelp() {
        return "This will shutdown the bot. **OWNER PERMISSION REQUIRED**\n" +
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+" <username>";
    }

    @Override
    public boolean needOwner() {
        return true;
    }
}
