import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private static final int MAX_MISTAKES = 10;
    FileHandler fileHandler = new FileHandler();
    UserInterface userInterface = new UserInterface();


    public boolean playGame() {
        String chosenWord = getRandomWord(fileHandler.readWordsOfTheme(userInterface.chooseTheme()));
        StringBuilder sb = new StringBuilder("_".repeat(chosenWord.length()));
        int i = 0;
        if (chosenWord.contains(" ")) {
            while (chosenWord.toLowerCase().indexOf(" ", i) != -1) {
                sb.setCharAt(chosenWord.toLowerCase().indexOf(" ", i), ' ');
                i = chosenWord.toLowerCase().indexOf(' ', i) + 1;
            }
        }
        int mistake = 0;

        System.out.println(sb);
        while (sb.toString().contains("_") && mistake != MAX_MISTAKES) {
            char ch = userInterface.getCharInput("Type in a character!: ");
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

    private static String getRandomWord(ArrayList<String> arrayList) {
        Random random = new Random();
        return arrayList.get(random.nextInt(arrayList.size()+1));
    }

}
