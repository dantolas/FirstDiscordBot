package Commands;

import com.princecharming.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Cope implements Command {




    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("https://tenor.com/view/kongming-gif-25434850").queue();
    }

    @Override
    public String getCommandName() {
        return "cope";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
