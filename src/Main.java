import java.io.File;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("sylaby.txt");

        DivideWord divideWord = new DivideWord(file);

        divideWord.readSyllableInFile(file);
        divideWord.divideIntoSyllables("");
        divideWord.printCountSyllable();
    }
}
