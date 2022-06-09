package Commands;

import com.princecharming.Command;
import com.princecharming.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



public class Colors implements Command {

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {

        if(args.isEmpty()){
            //region<Building the Color Picker 900 embed>
            createColorPicker(event);
            //endregion
            return;
        }

        addColorToMenu(args, event);
        createColorRole(args, event);
        createBaseColors(args,event);
        removeBaseColors(args,event);
        removeColorRole(args,event);




    }
    //Creates the Color Picker embed, that users can react to and change their color role
    public void createColorPicker(MessageReceivedEvent event){
        StringBuilder sBuilder = new StringBuilder();

        if(Constants.colorMenu.isEmpty()){
            Constants.colorMenu = Constants.baseColors;
        }

        Constants.colorMenu.forEach(color ->{
            sBuilder.append(color).append(" ").append(Constants.colorEmojis.get(color)).append(" ");
        });

        //Creating the Color Picker 900 embed
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Color Picker 9000 "+"\uD83C\uDF08")
                .setDescription(sBuilder)
                .setFooter("Pick a card! Any card....");

        event.getTextChannel().sendMessageEmbeds(builder.build()).queue(message -> {
            AtomicInteger count = new AtomicInteger();
            Constants.colorMenu.forEach(color ->{
                if(!event.getGuild().getRolesByName(color, true).isEmpty()){
                    try{
                        if(count.getAndIncrement() < 20){
                            message.addReaction(Constants.colorEmojis.get(color)).queue();
                        }
                    }catch (Exception e){ //This exception should only occur when there are more than 20 color roles
                        e.printStackTrace();
                        event.getTextChannel().sendMessage("You have reached the color limit of the Color Picker 9000!! (Too many colors) => This color was not added : **"+color+"**").queue();

                    }
                }

            });



            Constants.colorEmbedIds.add(message.getIdLong());
        });
    }

    //region<Add color>
    public void addColorToMenu(List<String> args, MessageReceivedEvent event){

        if(args.size() !=3){
            return;
        }

        //returns if the args don't specify that a color is supposed to be created
        if(!args.get(0).equalsIgnoreCase("add")){
            event.getTextChannel().sendMessage("Wrong use of the command! - addColorToMenu").queue();
            return;
        }
        //Check if the role exists
        if(event.getGuild().getRolesByName(args.get(1),true).isEmpty()){
            event.getTextChannel().sendMessage("That role does not exist on this server!").queue();
            return;
        }
        if(!Constants.colorMenu.contains(args.get(1))){
            Constants.colorMenu.add(args.get(1));
            Constants.colorEmojis.put(args.get(1), args.get(2));
            Constants.emojiUniToColors.put(args.get(2),args.get(1));
            event.getTextChannel().sendMessage("Color added : "+args.get(1)+" "+args.get(2)).queue();
        }
    }
    //endregion

    //region<Create Color>
    public void createColorRole(List<String> args, MessageReceivedEvent event){
        //returns if not enough or too many arguments were passed in
        if(args.size() !=4){
            return;
        }

        //returns if the args don't specify that a color is supposed to be created
        if(!args.get(0).equalsIgnoreCase("create")){
            event.getTextChannel().sendMessage("Wrong use of the command! - create colorRole").queue();
            return;
        }

        //Returns if the user doesn't have adming permission
        if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.getTextChannel().sendMessage("U need **ADMIN PERMISSION** for this command").queue();
            return;
        }

        //If role exists, return
        if(!event.getGuild().getRolesByName(args.get(1),true).isEmpty()){
            event.getTextChannel().sendMessage("Role already exists!!").queue();
            return;
        }

        //add it to necessary lists
        if(!Constants.colorMenu.contains(args.get(1))){
            Constants.colorMenu.add(args.get(1));
            Constants.colorEmojis.put(args.get(1), args.get(3));
            Constants.emojiUniToColors.put(args.get(3),args.get(1));
        }

        //Creating the role
        event.getGuild().createRole().setName(args.get(1))
                .setColor(Color.decode(args.get(2))).setMentionable(false).queue();


    }
    //endregion

    //region<RemoveColor>
    public void removeColorRole(List<String> args, MessageReceivedEvent event){
        //returns if not enough or too many arguments were passed in
        if(args.size() !=2){
            return;
        }

        if(!args.get(0).equalsIgnoreCase("remove") || args.get(1).equalsIgnoreCase("base")){
            return;
        }

        //Returns if the user doesn't have adming permission
        if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.getTextChannel().sendMessage("U need **ADMIN PERMISSION** for this command").queue();
            return;
        }

        //If role doesn't exists, return
        if(event.getGuild().getRolesByName(args.get(1),true).isEmpty()){
            event.getTextChannel().sendMessage("Role doesn't exists!!").queue();
            return;
        }

        //Remove it from
        if(Constants.colorMenu.contains(args.get(1))){
            Constants.colorMenu.remove(args.get(1));
            Constants.emojiUniToColors.remove(args.get(1),args.get(1));
        }

        //Removing the role
        event.getGuild().getRolesByName(args.get(1),true).forEach(role -> {
            role.delete().queue();
        });

        String emoji = "";
        if(Constants.colorEmojis.get(args.get(1)) != null){
            emoji = Constants.colorEmojis.get(args.get(1));
        }
        event.getTextChannel().sendMessage("Role deleted: "+args.get(1) + emoji).queue();


    }
    //endregion

    //region<Create base>
    public void createBaseColors(List<String> args, MessageReceivedEvent event){
        //returns if not enough or too many arguments were passed in
        if(args.size() !=2){
            return;
        }

        if(!args.get(0).equalsIgnoreCase("create")){
            return;
        }

        //returns if the args don't specify that a color is supposed to be created
        if(args.get(0).equalsIgnoreCase("create") && !args.get(1).equalsIgnoreCase("base")){
            event.getTextChannel().sendMessage("Wrong use of the command! - create base").queue();
            return;
        }
        //Returns if the user doesn't have adming permission
        if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.getTextChannel().sendMessage("U need **ADMIN PERMISSION** for this command").queue();
            return;
        }

        try {
            Constants.baseColorsEmojis.entrySet().forEach(entry ->{
                if(!Constants.colorMenu.contains(entry.getKey())){
                    Constants.colorMenu.add(entry.getKey());
                }
                //Creating the role
                event.getGuild().createRole().setName(entry.getKey())
                        .setColor(Color.decode(Constants.colorNameToHex.get(entry.getKey()))).setMentionable(false).queue();
            });
            event.getTextChannel().sendMessage("All base colors created :thumbsup:").queue();
        }catch (Exception e){
            event.getTextChannel().sendMessage("Something went wrong").queue();
        }

    }
    //endregion

    //region<Remove base>
    public void removeBaseColors(List<String> args, MessageReceivedEvent event){
        //returns if not enough or too many arguments were passed in
        if(args.size() !=2){
            return;
        }

        if(!args.get(0).equalsIgnoreCase("remove")){
            return;
        }

        //returns if the args don't specify that a color is supposed to be created
        if(args.get(0).equalsIgnoreCase("remove") && !args.get(1).equalsIgnoreCase("base")){
            return;
        }
        //Returns if the user doesn't have adming permission
        if(!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.getTextChannel().sendMessage("U need **ADMIN PERMISSION** for this command").queue();
            return;
        }

        try {
            Constants.baseColorsEmojis.entrySet().forEach(entry ->{
                Constants.colorMenu.remove(entry.getKey());

                event.getGuild().getRolesByName(entry.getKey(),true).forEach(role -> {
                    role.delete().queue();
                });
            });
            event.getTextChannel().sendMessage("All base colors removed :thumbsup:").queue();
        }catch (Exception e){
            event.getTextChannel().sendMessage("Something went wrong").queue();
        }

    }
    //endregion

    @Override
    public String getCommandName() {
        return "colors";
    }

    @Override
    public String getHelp() {
        return "Let's you pick your color if the server has any of the colors on the menu." +
                "`\n(Limit for colors on one server is 20!)(After adding a color to the menu, or creating a role, the"+Constants.BOT_COMMAND_PREFIX+getCommandName()+" command must be executed again)`\n"+
                "   ->do "+ Constants.BOT_COMMAND_PREFIX+getCommandName()+
                "\nIf you already have a color role, but it's not on the menu, add it like this\n"+
                "   ->do "+Constants.BOT_COMMAND_PREFIX+getCommandName()+" add <color> <emojiUnicode>)"+
                "\nIf you want to create a color role, create it like this\n"+
                "   ->do "+Constants.BOT_COMMAND_PREFIX+getCommandName()+" create <color> <#hexColorcode> <emojiUnicode> **ADMIN PERMISSION REQUIRED**"+
                "\nIf you want to remove a color role, remove it like this\n"+
                "   ->do "+Constants.BOT_COMMAND_PREFIX+getCommandName()+" remove <color> **ADMIN PERMISSION REQUIRED**"+
                "\nIf you want to create all the color roles on the menu, create them like this\n"+
                "   ->do "+Constants.BOT_COMMAND_PREFIX+getCommandName()+" create base **ADMIN PERMISSION REQUIRED**";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
