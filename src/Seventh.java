import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Seventh {
    private static int[] positions;

    public static void main(String[] args) {
        readFile();
        System.out.println("Task 1 fuel consumed: " + calcFuelConsumption_1(median()));
        System.out.println("Task 2 fuel consumed: " + calcFuelConsumption_2(mean()));
    }

    private static int calcFuelConsumption_2(int mean) {
        int fuel = 0;
        for (int position : positions) {
            fuel += summation(Math.abs(position - mean));
        }
        return fuel;
    }

    private static int calcFuelConsumption_1(int median) {
        int fuel = 0;
        for (int position : positions) {
            fuel += Math.abs(position - median);
        }
        return fuel;
    }

    private static void readFile() {
        File txt = new File("resources/7.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(",");
            positions = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                positions[i] = Integer.parseInt(split[i]);
            }
        }
    }

    private static int median() {
        //If there is not explicit middle value it returns closest value down
        Arrays.sort(positions);
        return positions[positions.length / 2];
    }

    private static int mean() {
        //Gives back truncated value
        Arrays.sort(positions);
        double sum = 0.0;
        for (int position : positions) {
            sum += position;
        }
        double denominator = (double) positions.length;
        double quote = sum / denominator;
        return (int) quote;
    }

    private static int summation(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return sum;
    }
}
