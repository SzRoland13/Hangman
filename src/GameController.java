public class GameController {

    private final GameLogic gameLogic;
    private final UserInterface userInterface;

    public GameController() {
        this.gameLogic = new GameLogic();
        this.userInterface = new UserInterface();
    }

    public void startGame() {
        boolean play = true;

        userInterface.printIntroduction();
        if (userInterface.startToPlay()) {

            while (play) {
                playSingleGame();
                if (!userInterface.playAgain()) {
                    play = false;
                }
            }
        }
    }

    private void playSingleGame() {
        ThemeEnum themeEnum = userInterface.chooseTheme();
        gameLogic.initializeGame(themeEnum);
        char ch;
        gameLogic.firstLookAtTheWord();

        while (gameLogic.isGameRunning()) {
            ch = userInterface.getCharInput("Type in a character!: ");
            gameLogic.makeGuess(ch);
        }

        if (gameLogic.isGameWon()) {
            System.out.println("Congratulations!");
        } else {
            System.out.println("I'm sorry, but you didn't guessed it correctly.");
        }

    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
/*
Room for improvement:
 - Could make it a GUI app
 - Make more themes and more words in each theme
 - Make the code to not be able to get the same word one after another
 - Make a scoring mechanism
 */