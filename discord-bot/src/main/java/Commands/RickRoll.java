package Commands;

import LavaPlayer.PlayerManager;
import com.princecharming.Command;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class RickRoll implements Command {




    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getTextChannel().sendMessage("You need to be in a voice channel to use this command.").queue();
            return;
        }

        if(!event.getGuild().getSelfMember().getVoiceState().inAudioChannel()){
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }

    @Override
    public String getCommandName() {
        return "never";
    }

    @Override
    public String getHelp() {
        return "We're no strangers to love\n" +
                "You know the rules and so do I\n" +
                "A full commitment's what I'm thinking of\n" +
                "You wouldn't get this from any other guy\n" +
                "\n" +
                "I just wanna tell you how I'm feeling\n" +
                "Gotta make you understand\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "We've known each other for so long\n" +
                "Your heart's been aching, but\n" +
                "You're too shy to say it\n" +
                "Inside, we both know what's been going on\n" +
                "We know the game and we're gonna play it\n" +
                "\n" +
                "And if you ask me how I'm feeling\n" +
                "Don't tell me you're too blind to see\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "(Ooh, give you up)\n" +
                "(Ooh, give you up)\n" +
                "Never gonna give, never gonna give\n" +
                "(Give you up)\n" +
                "Never gonna give, never gonna give\n" +
                "(Give you up)\n" +
                "\n" +
                "We've known each other for so long\n" +
                "Your heart's been aching, but\n" +
                "You're too shy to say it\n" +
                "Inside, we both know what's been going on\n" +
                "We know the game and we're gonna play it\n" +
                "\n" +
                "I just wanna tell you how I'm feeling\n" +
                "Gotta make you understand\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna run around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
