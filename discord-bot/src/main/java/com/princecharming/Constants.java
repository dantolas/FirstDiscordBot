package com.princecharming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Class with some constants the rest of the code references, consider this a settings class
public abstract class Constants {

   //List of some basic common colors
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


   public static final String BOT_COMMAND_PREFIX = "!";

   public static final String BOT_SECRET_COMMAND_AFTERFIX = "?";

   public static final Long OWNER_ID = 257377227451269122L;

   public static final String OWNER_NAME = "Horse";

   public static final String BOT_ACTIVITY = "Playing with your dad #]";

   public static Map<String,String> colorEmojis = Map.ofEntries(
           Map.entry("red",":hot_face:"),
           Map.entry("salmon",":blowfish:"),
           Map.entry("blue",":billed_cap:"),
           Map.entry("black",":black_heart:"),
           Map.entry("purple",":grapes:"),
           Map.entry("magenta",":octopus:"),
           Map.entry("green",":crocodile:"),
           Map.entry("yellow",":coin:"),
           Map.entry("cyan",":dollar:"),
           Map.entry("white",":tooth:"),
           Map.entry("gray",":skull:"),
           Map.entry("brown",":briefcase:"),
           Map.entry("orange",":fire:"),
           Map.entry("silver",":second_place:"),
           Map.entry("gold",":first_place:"),
           Map.entry("violet",":smiling_imp:"),
           Map.entry("olive",":olive:"),
           Map.entry("peach",":peach:"),
           Map.entry("lime",":kiwi:"),
           Map.entry("turquoise",":beginner:"),
           Map.entry("crimson",":drop_of_blood:"),
           Map.entry("beige",":orangutan:"),
           Map.entry("navy",":blueberries:")
   );

}
