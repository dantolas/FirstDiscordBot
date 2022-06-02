package com.princecharming;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class Listener extends ListenerAdapter {

    public final CommandManager cm = new CommandManager();


    //Prints to console when bot is ready and on how many guilds he is available
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println(event.getJDA().getSelfUser().getName()+" is online");
        System.out.println("Available on `"+event.getGuildAvailableCount()+"` guilds out of `"+event.getGuildTotalCount()+"`");
    }

    //Whenever someone joins, they get a welcome messages
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String[] MEMBER_JOIN_WELCOME_MESSAGES = {
                "A wild "+event.getUser().getName() + " has appeared!",
                "You don goofed!  "+event.getUser().getName() + " has activated my trap card!",
                "Who's that pokemon??? It's a wild "+event.getUser().getName() + ".",
                "Who do we have here? "+event.getUser().getName() + ", I'll keep my sensors on you!",
                "Aahhh yes, welcome welcome dear "+event.getUser().getName() + ". Come and tell us your story!",
                " "+event.getUser().getName() + " has appeared!",
                "Who invited him? "+event.getUser().getName() + " looks kinda funky...",
                "Oh brother this guy "+event.getUser().getName() + " STINKS!",
                "Hello there  "+event.getUser().getName() + ".",
                "Hewwo mastew" +event.getUser().getName() + ", I hope I can be of sewice :3 (please end my suffering)",

        };
        event.getGuild().getTextChannelsByName("general",true).forEach(textChannel -> textChannel.sendMessage("").queue());
    }
    //Text manager handles message reactions
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        cm.tm.handle(event);

    }
    //Command manager or text manager handles messages
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (event.getAuthor().isBot()){
            return;
        }

        if(message.startsWith(Constants.BOT_COMMAND_PREFIX) || message.endsWith(Constants.BOT_SECRET_COMMAND_AFTERFIX)){
            System.out.println("Listener gave the message to cm");
            cm.handle(event);
        }else {
            System.out.println("Listener gave the message to tm");
            cm.tm.handle(event);
        }

    }
}
