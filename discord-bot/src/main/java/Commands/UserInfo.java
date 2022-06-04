package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserInfo implements Command {
    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.size() != 1){
            event.getTextChannel().sendMessage(getHelp()).queue();
            return;
        }
        Member targetMember;
        String id = args.get(0).replaceAll("<@","").replaceAll("!","").replaceAll(">","");
        try{
             targetMember = event.getGuild().getMemberById(id);

            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle(targetMember.getUser().getName() + "("+targetMember.getNickname()+")")
                    .setAuthor(targetMember.getUser().getName(),targetMember.getAvatarUrl(),targetMember.getEffectiveAvatarUrl())
                    .setDescription("**MENTION** :"+targetMember.getAsMention() + "\n**Tag** :"+targetMember.getUser().getAsTag() + "\n**ID** :"+targetMember.getUser().getId() + "\n**Avatar Link** : [link]("+targetMember.getUser().getEffectiveAvatarUrl()+")")
                    .addField("Server join time",targetMember.getTimeJoined().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),true)
                    .addField("Account creation date",targetMember.getTimeCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),true);
            event.getTextChannel().sendMessageEmbeds(builder.build()).queue();

        }catch (Exception e){
            event.getTextChannel().sendMessage("Incorrect use! \n"+getHelp()).queue();
        }



    }

    @Override
    public String getCommandName() {
        return "userinfo";
    }

    @Override
    public String getHelp() {
        return "Gives you the info about a certain user\n"+
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName() + "<@username>";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
