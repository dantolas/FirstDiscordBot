package com.princecharming;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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
