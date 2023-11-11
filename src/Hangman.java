public class Hangman {


    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        GameLogic gameLogic = new GameLogic();

        boolean play = true;

        userInterface.printIntroduction();
        if (userInterface.startToPlay()) {
            while (play) {
                if (gameLogic.playGame()) {
                    System.out.println("Congratulations!");
                } else {
                    System.out.println("I'm sorry, but you didn't guessed it correctly.");
                }
                play = userInterface.playAgain();
            }
        }
    }


}
/*
Room for improvement:
 - Could make it a GUI app
 - Make more themes and more words in each theme
 - Make the code to not be able to get the same word one after another
 - Make a scoring mechanism
 */
