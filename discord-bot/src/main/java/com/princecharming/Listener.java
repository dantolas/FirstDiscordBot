package com.princecharming;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Commands.Parrot;

public class Listener extends ListenerAdapter {

    public final CommandManager cm = new CommandManager();



    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println(event.getJDA().getSelfUser().getName()+" is online");
    }



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        cm.handle(event);
    }
}
