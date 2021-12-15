import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Fourteenth {

    private static Map<String, Long> occurances = new HashMap<>();
    private static Map<String, Long> occurances_temp = new HashMap<>();
    private static Map<String, String> recipes = new HashMap<>();
    private static Map<Character, Long> letter_occurances = new HashMap<>();
    private static String polymer;

    public static void main(String[] args) {
        readFile();
        initPolymer();
        step(10);
        System.out.println("Difference after 10 steps: " + count());
        step(30);
        System.out.println("Difference after 40 steps: " + count());
    }

    private static void initPolymer() {
        for (int i = 0; i < polymer.length() - 1; i++) {
            String ab = polymer.substring(i, i + 2);
            occurances.replace(ab, occurances.get(ab) + 1L);
        }
        for (int i = 0; i < polymer.length(); i++) {
            Character a = polymer.charAt(i);
            letter_occurances.replace(a, letter_occurances.get(a) + 1L);
        }
    }

    private static void step(int steps) {
        for (int i = 0; i < steps; i++) {
            for (String pair : occurances.keySet()) {
                Long occurance = occurances.get(pair);
                String pair1 = pair.toCharArray()[0] + recipes.get(pair);
                String pair2 = recipes.get(pair) + pair.toCharArray()[1];
                occurances_temp.replace(pair1, occurances_temp.get(pair1) + occurance);
                occurances_temp.replace(pair2, occurances_temp.get(pair2) + occurance);
                Character letter = recipes.get(pair).charAt(0);
                letter_occurances.replace(letter, letter_occurances.get(letter) + occurance);
            }
            copyFromTemp();
        }
    }

    private static Long count() {
        Long max = 0L;
        Long min = Long.MAX_VALUE;
        for (Character letter : letter_occurances.keySet()) {
            Long letter_occurance = letter_occurances.get(letter);
            if (letter_occurance > 0) {
                if (letter_occurance > max) {
                    max = letter_occurance;
                }
                if (letter_occurance < min) {
                    min = letter_occurance;
                }
            }
        }
        return max - min;
    }

    private static void copyFromTemp() {
        for (String key : occurances_temp.keySet()) {
            occurances.replace(key, occurances_temp.get(key));
            occurances_temp.replace(key, 0L);
        }
    }


    private static void readFile() {
        File txt = new File("resources/14.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        polymer = scan.nextLine();
        //remove blank line
        scan.nextLine();
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(" -> ");
            String ab = split[0];
            Character a = ab.toCharArray()[0];
            Character b = ab.toCharArray()[1];
            String c = split[1];
            recipes.put(ab, c);
            occurances.put(ab, 0L);
            occurances_temp.put(ab, 0L);
            letter_occurances.put(a, 0L);
            letter_occurances.put(b, 0L);
        }
    }

}
