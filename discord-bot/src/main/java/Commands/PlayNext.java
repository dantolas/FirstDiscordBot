package Commands;

import LavaPlayer.PlayerManager;
import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PlayNext implements Command {

    public final PlayerManager playerManager;


    public PlayNext() {
        this.playerManager = PlayerManager.getINSTANCE();
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getTextChannel().sendMessage("You need to be in a voice channel to use this command.").queue();
            return;
        }

        playerManager.next(event.getTextChannel());





    }

    @Override
    public String getCommandName() {
        return "playnext";
    }

    @Override
    public String getHelp() {
        return "Plays next song in queue\n" +
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
