package com.princecharming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Class with some constants the rest of the code references, consider this a settings class
public abstract class Constants {

   public static final String BOT_COMMAND_PREFIX = "!";

   public static final String BOT_SECRET_COMMAND_AFTERFIX = "?";

   public static final Long OWNER_ID = 257377227451269122L;

   public static final String OWNER_NAME = "Horse";

   public static final String BOT_ACTIVITY = " with your dad #]";

   //The stuff below has to do with the Commands.Colors command, because these collections need to be
   //available to more classes in the project
   public static final List<String> colors = new ArrayList<>(){{
      add("salmon");
      add("red");
      add("blue");
      add("black");
      add("purple");
      add("magenta");
      add("green");
      add("yellow");
      add("cyan");
      add("white");
      add("gray");
      add("brown");
      add("orange");
      add("grey");
      add("silver");
      add("gold");
      add("violet");
      add("olive");
      add("peach");
      add("pink");
      add("lime");
      add("turquoise");
      add("crimson");
      add("beige");
      add("navy");
      add("olive");
   }};

   public static Map<String,String> colorEmojis = Map.ofEntries(
           Map.entry("red","\uD83E\uDD75"),
           Map.entry("salmon","\uD83D\uDC21"),
           Map.entry("blue","\uD83E\uDDE2"),
           Map.entry("black","\uD83D\uDDA4"),
           Map.entry("purple","\uD83C\uDF47"),
           Map.entry("magenta","\uD83E\uDD91"),
           Map.entry("green","\uD83D\uDC0A"),
           Map.entry("yellow","\uD83E\uDE99"),
           Map.entry("white","\uD83E\uDDB7"),
           Map.entry("gray","\uD83D\uDC80"),
           Map.entry("brown","\uD83D\uDCBC"),
           Map.entry("orange","\uD83D\uDD25"),
           Map.entry("silver","\uD83E\uDD48"),
           Map.entry("gold","\uD83E\uDD47"),
           Map.entry("violet","\uD83D\uDE08"),
           Map.entry("olive","\uD83E\uDED2"),
           Map.entry("peach","\uD83C\uDF51"),
           Map.entry("crimson","\uD83E\uDE78"),
           Map.entry("beige","\uD83E\uDDA7"),
           Map.entry("navy","\uD83E\uDED0")
   );

   public static Map<String,String> emojiNameColors = Map.ofEntries(
           Map.entry(":hot_face:","red"),
           Map.entry(":blowfish:","salmon"),
           Map.entry(":billed_cap:","blue"),
           Map.entry(":black_heart:","black"),
           Map.entry(":grapes:","purple"),
           Map.entry(":squid:","magenta"),
           Map.entry(":crocodile:","crocodile"),
           Map.entry(":coin:","yellow"),
           Map.entry(":tooth:","white"),
           Map.entry(":skull:","gray"),
           Map.entry(":briefcase:","brown"),
           Map.entry(":fire:","orange"),
           Map.entry(":second_place:","silver"),
           Map.entry(":first_place:","gold"),
           Map.entry(":smiling_imp:","violet"),
           Map.entry(":olive:","olive"),
           Map.entry(":peach:","peach"),
           Map.entry(":drop_of_blood:","crimson"),
           Map.entry(":orangutan:","beige"),
           Map.entry(":blueberries:","navy")
   );

   public static List<Long> colorEmbedIds = new ArrayList<>();

}
