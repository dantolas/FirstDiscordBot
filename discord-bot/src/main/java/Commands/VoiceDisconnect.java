package Commands;

import LavaPlayer.GuildMusicManager;
import LavaPlayer.PlayerManager;
import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

//Music player command
//Disconnects the bot from a voice channel and pauses the song he is playing if he is playing one
public class VoiceDisconnect implements Command {

    public final PlayerManager playerManager;

    public VoiceDisconnect() {
        this.playerManager = PlayerManager.getINSTANCE();
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        //Checks if the bot is in an audio channel
        if(event.getGuild().getSelfMember().getVoiceState().inAudioChannel()){

            GuildMusicManager musicManager = playerManager.getMusicManager(event.getGuild());
            //Checks if the bot is currently playing a song, if it is, it pauses the song
            if(musicManager != null && musicManager.audioPlayer.getPlayingTrack() != null){
                if(!musicManager.audioPlayer.isPaused()){
                    musicManager.audioPlayer.setPaused(true);
                    event.getTextChannel().sendMessage("Track :"+musicManager.audioPlayer.getPlayingTrack().getInfo().title+" has been paused").queue();
                }
            }

            //disconnects the bot
            final AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.closeAudioConnection();
        }else {
            event.getTextChannel().sendMessage("Bot is not in a voice channel.").queue();
        }
    }

    @Override
    public String getCommandName() {
        return "voicedc";
    }

    @Override
    public String getHelp() {
        return "Disconnects the bot from a voice channel if he's in one\n" +
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName();
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
