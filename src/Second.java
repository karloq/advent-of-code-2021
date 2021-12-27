import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Second {
    private static ArrayList<Integer> command = new ArrayList<>();
    private static ArrayList<Integer> value = new ArrayList<>();

    public static void main(String[] args) {
        ReadFile();
        Solve();
    }

    private static void Solve() {
        int depth = 0;
        int horizontal = 0;
        for (int i = 0; i < command.size(); i++) {
            switch (command.get(i)) {
                case -1: {
                    depth -= value.get(i);
                    break;
                }
                case 1: {
                    depth += value.get(i);
                    break;
                }
                case 2: {
                    horizontal += value.get(i);
                    break;
                }
            }
        }
        System.out.println("part 1: " + depth * horizontal);

        depth = 0;
        horizontal = 0;
        int aim = 0;
        for (int i = 0; i < command.size(); i++) {
            switch (command.get(i)) {
                case -1: {
                    aim = aim - value.get(i);
                    break;
                }
                case 1: {
                    aim = aim + value.get(i);
                    break;
                }
                case 2: {
                    horizontal = horizontal + value.get(i);
                    depth = depth + (aim * value.get(i));
                    break;
                }
            }
        }
        System.out.println("part 2: " + depth * horizontal);
    }

    private static void ReadFile() {
        File txt = new File("resources/2.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            String[] splitted = scan.nextLine().split(" ");
            switch (splitted[0]) {
                case "up": {
                    command.add(-1);
                    break;
                }
                case "down": {
                    command.add(1);
                    break;
                }
                case "forward": {
                    command.add(2);
                    break;
                }
            }
            value.add(Integer.parseInt(splitted[1]));
        }
    }
}
