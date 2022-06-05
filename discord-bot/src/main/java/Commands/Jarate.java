package Commands;

import com.princecharming.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;



// This is a joke command created for a friend
public class Jarate implements Command {




    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("https://cdn.discordapp.com/attachments/874447411144171610/891693028148932608/piss.mp4").queue();
    }

    @Override
    public String getCommandName() {
        return "jarate";
    }

    @Override
    public String getHelp() {
        return "Piss off you wanker";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
