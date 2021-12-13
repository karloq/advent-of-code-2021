import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sixth {
    private static ArrayList<Long> fishes = new ArrayList<>((
            Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L)));

    public static void main(String[] args) {
        readFile();
        System.out.println(simulate(256));
    }

    private static Long simulate(int days) {
        for (int i = 0; i < days; i++) {
            ArrayList<Long> fishes_temp = new ArrayList<>((
                    Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L)));
            for (int dayGroup = 0; dayGroup < Sixth.fishes.size(); dayGroup++) {
                if (dayGroup == 0) {
                    if (Sixth.fishes.get(dayGroup) > 0){
                        fishes_temp.set(6, Sixth.fishes.get(dayGroup));
                        fishes_temp.set(8, fishes_temp.get(8) + Sixth.fishes.get(dayGroup));
                    }
                } else {
                    fishes_temp.set(dayGroup - 1, fishes_temp.get(dayGroup - 1) + Sixth.fishes.get(dayGroup));
                }
            }
            for (int j = 0; j < fishes_temp.size(); j++) {
                Sixth.fishes.set(j, fishes_temp.get(j));
            }
        }
        Long sum = 0L;
        for (Long total: fishes){
            sum = sum + total;
        }
        return sum;
    }

    private static void readFile() {
        File txt = new File("resources/6.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(",");
            for (String value : split) {
                int data = Integer.parseInt(value);
                Long current = fishes.get(data);
                fishes.set(data, current + 1);
            }
        }
    }
}
