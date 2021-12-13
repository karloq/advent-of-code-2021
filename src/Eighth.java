import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Eighth {
    private static ArrayList<String> output = new ArrayList<>();
    private static ArrayList<InputOutputPair> pairs = new ArrayList<>();
    private static String[] alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g"};
    private static ArrayList<Integer> codeAlphabet = new ArrayList<>(Arrays.asList(10331, 111221, 111231, 331, 121, 1131, 1321));
    private static ArrayList<String> digits = new ArrayList<>(Arrays.asList("abcdef", "bc", "abdeg", "abcdg", "bcfg", "acdfg", "acdefg", "abc", "abcdefg", "abcdfg"));

    public static void main(String[] args) {
        readFile();
        System.out.println("number of occurances of 1,4,7,8: " + countUniqueLengths());
        int totalSum = 0;
        for (InputOutputPair pair : pairs) {
            totalSum += pair.sum;
        }
        System.out.println("Sum of all outputs: " + totalSum);
    }

    public static class InputOutputPair {
        int[][] codes;
        int[] decoded;
        ArrayList<String> output;
        int[] translated;
        int sum;

        private InputOutputPair(ArrayList<String> inputs, ArrayList<String> outputs) {
            codes = new int[7][7];
            decoded = new int[7];
            translated = new int[4];
            for (int i = 0; i < alphabet.length; i++) {
                String letter = alphabet[i];
                for (int j = 0; j < inputs.size(); j++) {
                    String strinput = inputs.get(j);
                    if (strinput.contains(letter)) {
                        codes[i][strinput.length() - 1] += 1;
                    }
                }
            }
            output = outputs;
            decodeDigits();
            translateOutput();
            sum = intArrayToInt(translated);
        }

        private void decodeDigits() {
            for (int i = 0; i < codes.length; i++) {
                int[] letterCode = codes[i];
                int temp = intArrayToInt(letterCode);
                int letterIndex = codeAlphabet.indexOf(temp);
                decoded[i] = letterIndex;
            }
        }

        private void translateOutput() {
            for (int i = 0; i < output.size(); i++) {
                String word = output.get(i);
                char[] temp = word.toCharArray();
                StringBuilder tempbld = new StringBuilder();
                for (int j = 0; j < temp.length; j++) {
                    char letter = temp[j];
                    switch (letter) {
                        case 'a': {
                            tempbld.append(alphabet[decoded[0]]);
                            break;
                        }
                        case 'b': {
                            tempbld.append(alphabet[decoded[1]]);
                            break;
                        }
                        case 'c': {
                            tempbld.append(alphabet[decoded[2]]);
                            break;
                        }
                        case 'd': {
                            tempbld.append(alphabet[decoded[3]]);
                            break;
                        }
                        case 'e': {
                            tempbld.append(alphabet[decoded[4]]);
                            break;
                        }
                        case 'f': {
                            tempbld.append(alphabet[decoded[5]]);
                            break;
                        }
                        case 'g': {
                            tempbld.append(alphabet[decoded[6]]);
                            break;
                        }
                    }
                }
                translated[i] = digits.indexOf(sortString(tempbld.toString()));
            }
        }
    }

    private static void readFile() {
        File txt = new File("resources/8.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(" ");
            ArrayList<String> tempOut = new ArrayList<>();
            ArrayList<String> tempIn = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                if (i == 10) continue;
                if (i < 10) {
                    tempIn.add(sortString(split[i]));
                } else {
                    output.add(sortString(split[i]));
                    tempOut.add(sortString(split[i]));
                }
            }
            pairs.add(new InputOutputPair(tempIn, tempOut));
        }
    }

    private static int countUniqueLengths() {
        int occurances = 0;
        for (String numString : output) {
            int len = numString.length();
            if (len == 2 || len == 3 || len == 4 || len == 7) {
                occurances++;
            }
        }
        return occurances;
    }

    private static String sortString(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    private static int intArrayToInt(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = result + (int) Math.pow(10, arr.length - (i + 1)) * arr[i];
        }
        return result;
    }
}
