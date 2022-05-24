package Commands;

import LavaPlayer.PlayerManager;
import com.princecharming.Constants;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Play implements com.princecharming.Command {



    @Override
    public void run(List<String> args, MessageReceivedEvent event) {

        if(args.isEmpty()){
            event.getTextChannel().sendMessage(getHelp()).queue();
            return;
        }

        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getTextChannel().sendMessage("You need to be in a voice channel to use this command.").queue();
            return;
        }

        if(!event.getGuild().getSelfMember().getVoiceState().inAudioChannel()){
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String link = args.get(0);
        if(!isURL(link)){
            link = "ytsearch:" + link + "audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
    }

    public boolean isURL(String url){
        try{
            new URI(url);
            return true;

        }catch (URISyntaxException e){
            return false;
        }
    }

    @Override
    public String getCommandName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+" <url>";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
