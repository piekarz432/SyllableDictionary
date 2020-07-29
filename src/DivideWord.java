import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DivideWord {

    private List<String> syllableList = new ArrayList<>();
    private List<String> divideWordIntoSyllable = new ArrayList<>();
    private List<String> countSyllable = new ArrayList<>();
    private List<String> ignoreSyllable = new ArrayList<>();
    private String[] splitStr;
    private Map<Integer, String> position = new HashMap<>();
    private File syllableFile;
    
    public DivideWord(File syllableFile)
    {
        this.syllableFile = syllableFile;
    }

    /**
     * Podzial slowa na sylaby
     * @param word slowo
     */
    public void divideIntoSyllables(String word) {
        word = word.toLowerCase();
        splitStr = word.split(("\\s+"));

        for(int i = 0; i<splitStr.length; i++) {

            String tmp = splitStr[i];

            for(int j = 0; j<syllableList.size(); j++)
            {
                if (splitStr[i].contains(syllableList.get(j)))
                {
                    position.put(splitStr[i].indexOf(syllableList.get(j)), syllableList.get(j));
                    splitStr[i] = splitStr[i].replace(syllableList.get(j), "*");
                }
            }
            ignoreSyllable.add(splitStr[i]);
            addSyllable();
            System.out.println(tmp + " : " + divideWordIntoSyllable + " Ignore: " + ignoreSyllable);
            divideWordIntoSyllable.clear();
            position.clear();
            ignoreSyllable.clear();
        }
    }

    /**
     * Metoda zliczająca ilosc sylab
     * @param list lista
     * @return
     */
    private Map<String, Integer> countSyllable(List<String> list) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (String element : list) {
            if (resultMap.containsKey(element)) {
                resultMap.put(element, resultMap.get(element) + 1);
            } else {
                resultMap.put(element, 1);
            }
        }
        return resultMap;
    }

    /**
     * Wypisanie ilosci poszczegolnych sylab
     */
    public void printCountSyllable() {
        System.out.println();
        System.out.println("Count Syllable: ");
        System.out.println(countSyllable(countSyllable));
    }

    /**
     * Odczyt sylab z pliku
     * @param file - plik
     * @throws FileNotFoundException
     */
    public void readSyllableInFile(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);

        String syllable = "";
        while (in.hasNextLine())
        {
            syllable = in.nextLine().toLowerCase();
            syllableList.add(syllable);
        }
        in.close();

        Collections.sort(syllableList, Comparator.comparing(String::length));
        Collections.reverse(syllableList);
        System.out.println();
        System.out.println("Syllable in file: ");
        System.out.println(syllableList);
        System.out.println();
    }

    /**
     * Metoda prznoszaca slaby z mapy do listy w odpowiedniej kolejnosci
     */
    private void addSyllable() {
        for (Map.Entry<Integer, String > entry : position.entrySet()) {
            String value = entry.getValue();
            divideWordIntoSyllable.add(value);
            countSyllable.add(value);
        }
    }
}
