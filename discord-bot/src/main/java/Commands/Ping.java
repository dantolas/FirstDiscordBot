package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Ping implements Command {



    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(event.getJDA().getGatewayPing()+ "ms").queue();
    }

    @Override
    public String getCommandName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Shows the gateway ping of the bot\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
