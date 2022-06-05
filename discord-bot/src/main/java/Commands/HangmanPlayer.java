package Commands;

import java.util.List;
import java.util.Objects;

//Helper class used for storing data needed to play a hangman game
public class HangmanPlayer{

    private String username;
    private String wordBeingGuessed;
    private String lastGuess;
    private int mistakes;
    public final String[] hangmanStates = {
            " +---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            " +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            " +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========\n",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "========="
    };


    public HangmanPlayer(String username, String wordBeingGuessed) {
        this.username = username;
        this.wordBeingGuessed = wordBeingGuessed;
        this.lastGuess = wordBeingGuessed.replaceAll("[\\w]","+");
        mistakes = -1;
    }

    public void setLastGuess(String lastGuess) {
        this.lastGuess = lastGuess;
    }

    public String getUsername() {
        return username;
    }

    public String getWordBeingGuessed() {
        return wordBeingGuessed;
    }

    public String getLastGuess() {
        return lastGuess;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void incrementMistakes() {
        this.mistakes++;
    }

    public static HangmanPlayer getByName(List<HangmanPlayer> playerList, String username){
        for(HangmanPlayer player : playerList){
            if(player.equals(new HangmanPlayer(username,""))){
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HangmanPlayer that = (HangmanPlayer) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
