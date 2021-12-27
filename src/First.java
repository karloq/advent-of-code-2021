import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class First {
    private static ArrayList<Integer> data = new ArrayList<>();

    public static void main(String[] args) {
        ReadFile();
        Solve();
    }

    private static void Solve() {
        System.out.println("Part 1:" + countIncreases(data));

        ArrayList<Integer> slidingData = new ArrayList<>();
        for (int i = 0; i < data.size() - 2; i++) {
            slidingData.add(data.get(i) + data.get(i + 1) + data.get(i + 2));
        }
        System.out.println("Part 2:" + countIncreases(slidingData));
    }

    private static int countIncreases(ArrayList<Integer> depthList) {
        int noOfIncreases = 0;
        int prev = depthList.get(0);
        for (int depth : depthList) {
            if (depth > prev) {
                noOfIncreases = noOfIncreases + 1;
            }
            prev = depth;
        }
        return noOfIncreases;
    }


    private static void ReadFile() {
        File txt = new File("resources/1.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            data.add(Integer.parseInt(scan.nextLine()));
        }
    }
}
