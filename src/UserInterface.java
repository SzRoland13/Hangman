import java.util.Scanner;

public class UserInterface {
    private static final int MAX_THEME_ATTEMPTS = 3;

    private static final Scanner scanner = new Scanner(System.in);


    public boolean printIntroduction() {

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
        return getAnswer("Ready to play? (y/n): ");

    }

    private boolean getAnswer(String printQuestion) {
        while (true) {
            switch (getCharInput(printQuestion)) {
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

    public String chooseTheme() {

        System.out.println("Before you can start the game, you need to choose a theme!");
        System.out.println("List of the current themes: ");
        for (ThemeEnum themes : ThemeEnum.values()) {
            System.out.println(themes.name());
        }

        for (int attempts = 0; attempts < MAX_THEME_ATTEMPTS; attempts++) {
            String input = getStringInput("What theme would you like to choose?: ");
            for (ThemeEnum theme : ThemeEnum.values()) {
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

    public boolean playAgain() {
        return getAnswer("Do you want to play again? (y/n): ");
    }

    public String createPath() {
        return System.getProperty("user.dir") + "/src/topics/" + chooseTheme();
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().toLowerCase();
    }

    public String getStringInput(String prompt) {
        return getInput(prompt);
    }

    public char getCharInput(String prompt) {
        return getStringInput(prompt).charAt(0);
    }
}

