import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Fourth {
    private static ArrayList<Integer> callings = new ArrayList<>();
    private static ArrayList<int[][]> boards = new ArrayList<>();
    private static ArrayList<int[][]> marks = new ArrayList<>();
    private static int totalBoards = 100;
    private static ArrayList<Integer> winningBoards = new ArrayList<>();

    public static void main(String[] args) {
        ReadFile();
        Solve();
    }

    private static void Solve() {
        for (int calledNumber : callings) {
            markCalling(calledNumber, boards, marks);
            int result = checkForWin(boards, marks, calledNumber);
            if (result != -1) {
                System.out.println("Part 1: " + result);
                return;
            }
        }
    }

    private static int checkForWin(ArrayList<int[][]> boards, ArrayList<int[][]> marks, int drawnNumber) {
        for (int boardNo = 0; boardNo < boards.size(); boardNo++) {
            if (winningBoards.contains(boardNo)) {
                continue;
            }
            //Check full column
            for (int i = 0; i < 5; i++) {
                int temp = 0;
                int sum = 0;
                for (int j = 0; j < 5; j++) {

                    if (marks.get(boardNo)[j][i] < 0) {
                        temp++;
                    }
                    if (temp == 5) {
                        sum = countUnmarked(boardNo, boards, marks);
                        return sum * drawnNumber;
                    }
                }
            }


            //Check full row
            for (int i = 0; i < 5; i++) {
                int temp = 0;
                int sum = 0;
                for (int j = 0; j < 5; j++) {
                    if (marks.get(boardNo)[i][j] < 0) {
                        temp++;
                    }
                    if (temp == 5) {
                        sum = countUnmarked(boardNo, boards, marks);
                        return sum * drawnNumber;
                    }
                }
            }
        }
        return -1;
    }

    private static void markCalling(int no, ArrayList<int[][]> boards, ArrayList<int[][]> marks) {
        for (int boardNo = 0; boardNo < boards.size(); boardNo++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (boards.get(boardNo)[i][j] == no) {
                        marks.get(boardNo)[i][j] = -1;
                    }
                }
            }
        }
    }

    private static int countUnmarked(int board, ArrayList<int[][]> boards, ArrayList<int[][]> marks) {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (marks.get(board)[i][j] != -1) {
                    result = result + boards.get(board)[i][j];
                }
            }
        }
        return result;
    }

    private static void ReadFile() {
        File txt = new File("resources/4_callings.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] splitted = scan.nextLine().split(",");
            for (String calling : splitted)
                callings.add(Integer.parseInt(calling));
        }

        File boards_txt = new File("resources/4_boards.txt");
        scan = null;
        try {
            scan = new Scanner(boards_txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int boardNo = 0; boardNo < totalBoards; boardNo++) {
            int[][] board = new int[5][5];
            int[][] mark = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = scan.nextInt();
                    mark[i][j] = 0;
                }
            }
            boards.add(board);
            marks.add(mark);
        }
    }
}
