import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private static final int MAX_MISTAKES = 10;
    private ArrayList<String> wordList;
    private StringBuilder guessedWord;
    private int mistakes;
    private String chosenWord;


    public void initializeGame(ThemeEnum themeEnum) {
        FileHandler fileHandler = new FileHandler();
        wordList = fileHandler.readWordsOfTheme(themeEnum);
        startNewGame();
    }

    private void startNewGame() {
        chosenWord = getRandomWord(wordList);
        guessedWord = new StringBuilder("_".repeat(chosenWord.length()));
        mistakes = 0;
        int i = 0;
        if (chosenWord.contains(" ")) {
            while (chosenWord.toLowerCase().indexOf(" ", i) != -1) {
                guessedWord.setCharAt(chosenWord.toLowerCase().indexOf(" ", i), ' ');
                i = chosenWord.toLowerCase().indexOf(' ', i) + 1;
            }
        }
    }

    public void makeGuess(char guessedChar) {
        int index = chosenWord.toLowerCase().indexOf(guessedChar);
        if (index != -1) {
            while (index != -1) {
                guessedWord.setCharAt(index, chosenWord.charAt(index));
                index = chosenWord.toLowerCase().indexOf(guessedChar, index + 1);
            }
            System.out.println("Correct!");
        } else {
            mistakes++;
            System.out.println("WRONG! You guessed it wrong " + mistakes + " time(s)!");
        }
        System.out.println(guessedWord);
    }
    public void firstLookAtTheWord(){
        System.out.println(guessedWord);
    }

    public boolean isGameRunning() {
        return guessedWord.toString().contains("_") && mistakes < MAX_MISTAKES;
    }

    public boolean isGameWon() {
        return !guessedWord.toString().contains("_");
    }


    private static String getRandomWord(ArrayList<String> arrayList) {
        Random random = new Random();
        return arrayList.get(random.nextInt(arrayList.size()));
    }

}
