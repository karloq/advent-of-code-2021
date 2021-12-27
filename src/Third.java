import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Third {
    private static int[] array = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] bitarray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) {
        ReadFile();
        Solve();
    }

    private static void Solve() {
        int epsilon = 0;
        int gamma = 0;
        for (int i = 0; i < array.length; i++) {
            int bit = array[i];
            if (bit < 500) {
                bitarray[i] = 0;
                epsilon = epsilon + (int) Math.pow(2, (11 - i));
            } else {
                bitarray[i] = 1;
                gamma = gamma + (int) Math.pow(2, (11 - i));
            }
        }

        System.out.println("Part 1: " + epsilon * gamma);

        ArrayList<String> data_oxygen = new ArrayList<>();
        ArrayList<String> data_scrubber = new ArrayList<>();
        for (String value : data) {
            data_oxygen.add(value);
            data_scrubber.add(value);
        }

        for (int i = 0; i < bitarray.length; i++) {
            ArrayList<Integer> deleteArr = new ArrayList<>();
            int common = getMostCommon(data_oxygen, i);
            for (int j = 0; j < data_oxygen.size(); j++) {
                String value = data_oxygen.get(j);
                if (Character.getNumericValue(value.toCharArray()[i]) != common) {
                    deleteArr.add(j);
                }
            }
            deleteValues(deleteArr, data_oxygen);
            if (data_oxygen.size() == 1) {
                break;
            }
        }

        for (int i = 0; i < bitarray.length; i++) {
            ArrayList<Integer> deleteArr = new ArrayList<>();
            int leastcommon = getLeastCommon(data_scrubber, i);
            for (int j = 0; j < data_scrubber.size(); j++) {
                String value = data_scrubber.get(j);
                if (Character.getNumericValue(value.toCharArray()[i]) != leastcommon) {
                    deleteArr.add(j);
                }
            }
            deleteValues(deleteArr, data_scrubber);
            if (data_scrubber.size() == 1) {
                break;
            }
        }
        System.out.println("Part 2: " + Integer.parseInt(data_scrubber.get(0), 2) * Integer.parseInt(data_oxygen.get(0), 2));
    }

    private static void deleteValues(ArrayList<Integer> deleteArr, ArrayList<String> data) {
        for (int i = 0; i < deleteArr.size(); i++) {
            data.remove(deleteArr.get(i) - i);
        }
    }

    public static int getMostCommon(ArrayList<String> values, int bitPosition) {
        int zeroes = 0;
        int ones = 0;

        for (String value : values) {
            if (Character.getNumericValue(value.toCharArray()[bitPosition]) == 1) {
                ones++;
            } else {
                zeroes++;
            }
        }

        if (ones >= zeroes) return 1;
        else return 0;
    }

    public static int getLeastCommon(ArrayList<String> values, int bitPosition) {
        int zeroes = 0;
        int ones = 0;

        for (String value : values) {
            if (Character.getNumericValue(value.toCharArray()[bitPosition]) == 1) {
                ones++;
            } else {
                zeroes++;
            }
        }

        if (ones < zeroes) return 1;
        else return 0;
    }

    private static void ReadFile() {
        File txt = new File("resources/3.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            data.add(scan.nextLine());
        }

        for (String string : data) {
            char[] charArr = string.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                array[i] = array[i] + Character.getNumericValue(charArr[i]);
            }
        }
    }
}
