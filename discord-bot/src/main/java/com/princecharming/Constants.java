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
   public static Map<String,String> colorNameToHex = Map.ofEntries(
           Map.entry("red","#FF0000"),
           Map.entry("salmon","#FA8072"),
           Map.entry("blue","#0000FF"),
           Map.entry("black","#171717"),
           Map.entry("purple","#A020F0"),
           Map.entry("magenta","#FF00FF"),
           Map.entry("green","#013220"),
           Map.entry("yellow","#FFEA00"),
           Map.entry("white","#E8E8E8"),
           Map.entry("gray","#808080"),
           Map.entry("brown","#964B00"),
           Map.entry("orange","#FFA500"),
           Map.entry("silver","#C0C0C0"),
           Map.entry("gold","#FFD700"),
           Map.entry("violet","#8F00FF"),
           Map.entry("olive","#808000"),
           Map.entry("peach","#FFE5B4"),
           Map.entry("crimson","#990000"),
           Map.entry("beige","#f5f5dc"),
           Map.entry("navy","#145DA0")
   );


   //This list contains the colors that will be put onto the Color Picker 9000 (Commands.Colors)
   public static List<String> colorMenu = new ArrayList<>();

   public static List<String> baseColors = new ArrayList<>(){{
      add("red");
      add("salmon");
      add("blue");
      add("black");
      add("purple");
      add("magenta");
      add("green");
      add("yellow");
      add("white");
      add("gray");
      add("brown");
      add("orange");
      add("silver");
      add("gold");
      add("violet");
      add("olive");
      add("peach");
      add("crimson");
      add("beige");
      add("navy");
   }};




   public static Map<String,String> colorEmojisImm = Map.ofEntries(
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

   //This is done because the first map is immutable, and I'm running out of time to do the manual labor of rewriting every map here to a mutable one
   public static Map<String,String> colorEmojis = new HashMap<>(colorEmojisImm);

   //This is the base list of colors that can easily be added to colorEmojis
   public static Map<String,String> baseColorsEmojis = Map.ofEntries(
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


   public static Map<String,String> emojiUniToColorsImm = Map.ofEntries(
           Map.entry("\uD83E\uDD75","red"),
           Map.entry("\uD83D\uDC21","salmon"),
           Map.entry("\uD83E\uDDE2","blue"),
           Map.entry("\uD83D\uDDA4","black"),
           Map.entry("\uD83C\uDF47","purple"),
           Map.entry("\uD83E\uDD91","magenta"),
           Map.entry("\uD83D\uDC0A","green"),
           Map.entry("\uD83E\uDE99","yellow"),
           Map.entry("\uD83E\uDDB7","white"),
           Map.entry("\uD83D\uDC80","gray"),
           Map.entry("\uD83D\uDCBC","brown"),
           Map.entry("\uD83D\uDD25","orange"),
           Map.entry("\uD83E\uDD48","silver"),
           Map.entry("\uD83E\uDD47","gold"),
           Map.entry("\uD83D\uDE08","violet"),
           Map.entry("\uD83E\uDED2","olive"),
           Map.entry("\uD83C\uDF51","peach"),
           Map.entry("\uD83E\uDE78","crimson"),
           Map.entry("\uD83E\uDDA7","beige"),
           Map.entry("\uD83E\uDED0","navy")
   );

   public static Map<String,String> emojiUniToColors = new HashMap<>(emojiUniToColorsImm);

   public static List<Long> colorEmbedIds = new ArrayList<>();

}
