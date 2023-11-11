import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Hangman {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_MISTAKES = 10;
    private static final int MAX_THEME_ATTEMPTS = 3;
    enum Txt {
        ANIMALS("animals.txt"),
        COUNTRIES("countries.txt"),
        DRINKS("drinks.txt"),
        FOODS("foods.txt"),
        FRUITS("fruits.txt"),
        VEGETABLES("vegetables.txt");

        private final String fileName;

        Txt(String fileName) {
            this.fileName = fileName;
        }
        public String getFileName() {
            return fileName;
        }
    }

    public static void main(String[] args) {
        boolean play = true;

        if (intro()){
            while (play){
                if(guessing(createPath())){
                    System.out.println("Congratulations!");
                } else {
                    System.out.println("I'm sorry, but you didn't guessed it correctly.");
                }
                play = playAgain();
            }
        }
    }
    public static boolean intro(){

        String introBlock = """
                Welcome to Hangman!
                                
                Guess the hidden word by choosing letters. Be careful! You have 10 incorrect guesses before the hanging man appears.
                                
                How to Play:
                                
                1. Pick a theme: animals, countries, drinks, foods, fruits, or vegetables.
                2. Guess one letter at a time.
                3. Correct guesses reveal the letters; incorrect ones add to the hanging man.
                4. Win by guessing the word or lose if the hanging man appears.
                """;
        System.out.println(introBlock);
        while (true){
            switch (getCharInput("Ready to play? (y/n): ")){
                case 'y' -> {
                    System.out.println("Okay, here you go! Good luck!");
                    return true;
                }
                case 'n' -> {
                    System.out.println("Oh, sorry to hear that!");
                    return false;
                }
                default -> System.out.println("Oops, something went wrong, try it again!");
            }
        }

    }
    private static String createPath() {
        return System.getProperty("user.dir") + "/src/topics/" + chooseTheme();
    }

    private static String chooseTheme() {

        System.out.println("Before you can start the game, you need to choose a theme!");
        System.out.println("List of the current themes: ");
        for (Txt themes : Txt.values()) {
            System.out.println(themes.name());
        }

        for (int attempts = 0; attempts < MAX_THEME_ATTEMPTS; attempts++) {
            String input = getStringInput("What theme would you like to choose?: ");
            for (Txt theme : Txt.values()) {
                if (input.equals(theme.name().toLowerCase())) {
                        return theme.getFileName();
                }
            }
                System.out.println("You typed something wrong! Please choose a valid theme.");
        }
            System.out.println("Too many incorrect attempts. Exiting the game.");
            System.exit(0);
            return null;
        }
    private static boolean playAgain() {
        while(true){
            char ch = getCharInput("Do you want to play again? (y/n): ");
            switch (ch){
                case 'y' -> {
                    System.out.println("Okay, here you go! Good luck!");
                    return true;
                }
                case 'n' -> {
                    System.out.println("Oh, sorry to hear that!");
                    return false;
                }
                default -> System.out.println("Oops, something went wrong, try it again!");
            }
        }
    }
    private static boolean guessing(String path) {
        File file = new File(path);
        ArrayList<String> words = fileReading(file);
        String chosenWord = randomChoosing(words);
        StringBuilder sb = new StringBuilder("_".repeat(chosenWord.length()));
        int i = 0;
        if(chosenWord.contains(" ")){
            while (chosenWord.toLowerCase().indexOf(" ", i) != -1) {
                sb.setCharAt(chosenWord.toLowerCase().indexOf(" ", i), ' ');
                i = chosenWord.toLowerCase().indexOf(' ', i) + 1;
            }
        }
        int mistake = 0;

        System.out.println(sb);
        while (sb.toString().contains("_") && mistake != MAX_MISTAKES) {
            char ch = getCharInput("Type in a character!: ");
            int index = chosenWord.toLowerCase().indexOf(ch);

            if (index != -1) {
                while (index != -1) {
                    sb.setCharAt(index, chosenWord.charAt(index));
                    index = chosenWord.toLowerCase().indexOf(ch, index + 1);
                }
                System.out.println("Correct!");
            } else {
                mistake++;
                System.out.println("WRONG! You guessed it wrong " + mistake + " time(s)!");
            }

            System.out.println(sb);
        }
        return (!sb.toString().contains("_") && mistake != 10);
    }
    private static String randomChoosing(ArrayList<String> arrayList) {
        Random random = new Random();
        return arrayList.get(random.nextInt(arrayList.size()));
    }
    private static ArrayList<String> fileReading(File file) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                arrayList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + file.getName() + ". " + e.getMessage());
        }
        return arrayList;
    }
    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().toLowerCase();
    }
    private static String getStringInput(String prompt) {
        return getInput(prompt);
    }
    private static char getCharInput(String prompt) {
        return getStringInput(prompt).charAt(0);
    }
}
/*
Room for improvement:
 - Could make it a GUI app
 - Make more themes and more words in each theme
 - Make the code to not be able to get the same word one after another
 - Make a pointing mechanism
 */
