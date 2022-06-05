package Commands;

import LavaPlayer.GuildMusicManager;
import LavaPlayer.PlayerManager;
import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

//Music player command
//Command to pause or resume the current song playing
public class PausePlay implements Command {

    public final PlayerManager playerManager;
    private GuildMusicManager musicManager;

    public PausePlay() {
        this.playerManager = PlayerManager.getINSTANCE();
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getTextChannel().sendMessage("You need to be in a voice channel to use this command.").queue();
            return;
        }
        this.musicManager = playerManager.getMusicManager(event.getGuild());

        if(musicManager == null || musicManager.audioPlayer.getPlayingTrack() == null){
            event.getTextChannel().sendMessage("A song must be playing to run this command.");
            return;
        }

        boolean action = !musicManager.audioPlayer.isPaused();
        playerManager.getMusicManager(event.getGuild()).audioPlayer.setPaused(action);
        if (action){
            event.getTextChannel().sendMessage("Track :"+musicManager.audioPlayer.getPlayingTrack().getInfo().title+" has been paused").queue();
        }else {
            event.getTextChannel().sendMessage("Track :"+musicManager.audioPlayer.getPlayingTrack().getInfo().title+" has been resumed").queue();
        }
    }

    @Override
    public String getCommandName() {
        return "pause";
    }

    @Override
    public String getHelp() {
         return "Pauses/resumes current track if bot is playing one.\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
