package testing;

import saravia.Dictionary;

import java.io.File;
import java.util.Scanner;

public class DictionaryTest {

    public static void main(String[] args) {

        Dictionary dict = new Dictionary();

        // Load File and Insert Words into Dictionary
        try {
            Scanner fileScanner = new Scanner(new File("HW4InputSample.txt"));

            while (fileScanner.hasNext()) {
                String word = fileScanner.next()
                        .replaceAll("[^a-zA-Z]", "")
                        .toLowerCase();

                if (!word.isEmpty()) {
                    dict.insertWordNode(word);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            return;
        }

		// Assertions Test
        assert dict.checkWord("cleaning");
        assert dict.checkWord("room");
        assert dict.checkWord("kitchen");
        assert !dict.checkWord("spaceship");
        assert !dict.checkWord("wizard");

        dict.removeWord("room");
        assert !dict.checkWord("room");

        dict.insertWordNode("room");
        assert dict.checkWord("room");

        dict.removeWord("cleaning");
        assert !dict.checkWord("cleaning");

        /* ================= FINAL OUTPUT ================= */
        System.out.println("Successful");
    }
}
