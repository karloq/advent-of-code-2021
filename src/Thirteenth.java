import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Thirteenth {

    private static ArrayList<Pair<Integer, Integer>> dots_list = new ArrayList<>();
    private static ArrayList<Pair<Character, Integer>> folds = new ArrayList<>();

    public static void main(String[] args) {
        readFile();
        for (int i = 0; i < 1; i++) {
            fold(i);
        }
        System.out.println("Visible dots after 1 fold: " + dots_list.size());
        for (int i = 1; i < folds.size(); i++) {
            fold(i);
        }
        spell();
    }

    private static void spell() {
        Integer xMax = 0;
        Integer yMax = 0;
        for (Pair<Integer, Integer> dot : dots_list) {
            if (dot.getKey() > xMax) xMax = dot.getKey();
            if (dot.getValue() > yMax) yMax = dot.getValue();
        }
        char[][] output = new char[yMax + 1][xMax + 1];

        for (int row = 0; row < output.length; row++) {
            for (int col = 0; col < output[row].length; col++) {
                output[row][col] = ' '; //Whatever value you want to set them to
            }
        }

        for (Pair<Integer, Integer> dot : dots_list) {
            Integer x = dot.getKey();
            Integer y = dot.getValue();
            output[y][x] = '8';
        }

        for (char[] row : output) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void fold(int foldNo) {
        ArrayList<Pair<Integer, Integer>> tempDots_list = new ArrayList<>();
        Pair<Character, Integer> fold = folds.get(foldNo);
        Integer foldVal = fold.getValue();
        switch (fold.getKey()) {
            case 'x': {
                int length = dots_list.size();
                for (int i = 0; i < length; i++) {
                    Pair<Integer, Integer> dot = dots_list.get(i);
                    Integer x = dot.getKey();
                    Integer y = dot.getValue();
                    if (x >= foldVal) {
                        x -= ((x - foldVal) * 2);
                    }
                    Pair<Integer, Integer> tempPair = new Pair<Integer, Integer>(x, y);
                    if (!tempDots_list.contains(tempPair)) {
                        tempDots_list.add(tempPair);
                    }
                }
                break;
            }
            default: {
                int length = dots_list.size();
                for (int i = 0; i < length; i++) {
                    Pair<Integer, Integer> dot = dots_list.get(i);
                    Integer x = dot.getKey();
                    Integer y = dot.getValue();
                    if (y >= foldVal) {
                        y -= ((y - foldVal) * 2);
                    }
                    Pair<Integer, Integer> tempPair = new Pair<Integer, Integer>(x, y);
                    if (!tempDots_list.contains(tempPair)) {
                        tempDots_list.add(tempPair);
                    }
                }
                break;
            }
        }
        dots_list = tempDots_list;
    }

    private static void readFile() {
        File txt = new File("resources/13.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1004; i++) {
            String[] split = scan.nextLine().split(",");
            dots_list.add(new Pair<>(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        scan.nextLine();
        for (int i = 0; i < 12; i++) {
            String[] split = scan.nextLine().split(" |=");
            folds.add(new Pair<>(split[2].toCharArray()[0], Integer.parseInt(split[3])));
        }
    }
}
