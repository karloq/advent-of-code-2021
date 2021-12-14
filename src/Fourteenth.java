import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Fourteenth {

    private static ArrayList<Character> polymer = new ArrayList<>();
    private static HashMap<Pair<Character, Character>,Character> recipeMap = new HashMap<>();

    public static void main(String[] args) {
        readFile();
        step(10);
        System.out.println("Difference for task 1: " + countDifference());
    }

    private static int countDifference() {
        ArrayList<Character> charArray = new ArrayList<>();
        for (Character letter : polymer) {
            if (!charArray.contains(letter)) {
                charArray.add(letter);
            }
        }
        int[] occurancesArray = new int[charArray.size()];
        for (Character letter : polymer) {
            int index = charArray.indexOf(letter);
            occurancesArray[index]++;
        }
        Arrays.sort(occurancesArray);
        return (occurancesArray[occurancesArray.length-1]-occurancesArray[0]);
    }

    private static void step(int steps) {
        for (int step = 0; step < steps; step++) {
            int length = polymer.size();
            for (int i = 0; i < ((length*2)-2); i++) {
                Character a = polymer.get(i);
                Character b = polymer.get(i + 1);
                polymer.add(i+1,recipeMap.get(new Pair<>(a,b)));
                i++;
            }
        }
    }

    private static void readFile() {
        File txt = new File("resources/14_test.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char[] out = scan.nextLine().toCharArray();
        for (char letter : out) {
            polymer.add(letter);
        }
        //remove blank line
        scan.nextLine();
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(" -> ");
            char[] ab = split[0].toCharArray();
            char[] c = split[1].toCharArray();
            recipeMap.put(new Pair<>(ab[0],ab[1]),c[0]);
        }
    }

    public static class PolyRecipe {
        char mono_1;
        char mono_2;
        char result;

        public PolyRecipe(char a, char b, char c) {
            mono_1 = a;
            mono_2 = b;
            result = c;
        }

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append(mono_1);
            out.append(mono_2);
            out.append(" -> ");
            out.append(result);
            return out.toString();
        }

        public Character check(char a, char b) {
            if ((this.mono_1 == a) && (this.mono_2 == b)) {
                return result;
            } else return null;
        }
    }
}
