import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<String> readFile(File file) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                arrayList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage() + "\nPlease check the file path. ");
            System.exit(404);
        }
        return arrayList;
    }
}
