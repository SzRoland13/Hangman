import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<String> readWordsOfTheme(ThemeEnum themeEnum) {
        File file = new File(createPath(themeEnum));
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage() + "\nPlease check the file path. ");
            System.exit(1);
        }
        return lines;
    }

    private String createPath(ThemeEnum themeEnum) {
        return System.getProperty("user.dir") + "/src/topics/" + themeEnum.getFileName();
    }

}
