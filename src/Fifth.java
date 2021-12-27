import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Fifth {

    private static ArrayList<Line> lines = new ArrayList<>();
    private final static Set<Coord> nonDuplicateCoords_1 = new HashSet<>();
    private final static Set<Coord> duplicateCoords_1 = new HashSet<>();
    private final static Set<Coord> nonDuplicateCoords_2 = new HashSet<>();
    private final static Set<Coord> duplicateCoords_2 = new HashSet<>();

    public static void main(String[] args) {
        ReadFile();
        Solve();
    }

    private static void Solve() {
        for (Line line : lines) {
            if (!line.diagonal) {
                for (Coord coord : line.lineCoords) {
                    if (!nonDuplicateCoords_1.add(coord)) {
                        duplicateCoords_1.add(coord);
                    }
                }
            }
        }
        System.out.println("Part 1: " + duplicateCoords_1.size());

        for (Line line : lines) {
            for (Coord coord : line.lineCoords) {
                if (!nonDuplicateCoords_2.add(coord)) {
                    duplicateCoords_2.add(coord);
                }
            }
        }
        System.out.println("Part 2: " + duplicateCoords_2.size());
    }

    public static void ReadFile() {
        File txt = new File("resources/5.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(" -> |\\,");

            int index = 0;
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;

            for (String stringValue : split) {
                int value = Integer.parseInt(stringValue);
                switch (index) {
                    case 0: {
                        x1 = value;
                        index++;
                        break;
                    }
                    case 1: {
                        y1 = value;
                        index++;
                        break;
                    }
                    case 2: {
                        x2 = value;
                        index++;
                        break;
                    }
                    default: {
                        y2 = value;
                        index = 0;
                        lines.add(new Line(x1, y1, x2, y2));
                        break;
                    }
                }
            }
        }

    }

    public static class Line {
        Coord start;
        Coord end;
        boolean horizontal;
        boolean diagonal;
        ArrayList<Coord> lineCoords = new ArrayList<>();

        public Line(int sX, int sY, int eX, int eY) {
            start = new Coord(sX, sY);
            end = new Coord(eX, eY);
            if (sX == eX) {
                horizontal = true;
                diagonal = false;
            } else if (sY == eY) {
                horizontal = false;
                diagonal = false;
            } else {
                horizontal = false;
                diagonal = true;
            }

            makeLine(start, end);
        }

        private void makeLine(Coord start, Coord end) {
            int deltaX = end.x - start.x;
            int deltaY = end.y - start.y;
            int length = Math.max(Math.abs(deltaX), Math.abs(deltaY));
            lineCoords.add(start);
            int prevX = start.x;
            int prevY = start.y;
            for (int i = 0; i < length; i++) {
                int x = prevX + (deltaX / length);
                int y = prevY + (deltaY / length);
                prevX = x;
                prevY = y;
                lineCoords.add(new Coord(x, y));
            }
        }

        @Override
        public String toString() {
            return "(" + start.x + ", " + start.y + ")(" + end.x + ", " + end.y + ")";
        }
    }

    public static class Coord {
        int x;
        int y;

        public Coord(int xx, int yy) {
            x = xx;
            y = yy;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + this.x;
            hash = 71 * hash + this.y;
            return hash;
        }

        @Override
        public String toString() {
            return (x + " , " + y);
        }

        @Override
        public boolean equals(Object obj) {
            // If the object is compared with itself then return true
            if (obj == this) {
                return true;
            }


            if (!(obj instanceof Coord)) {
                return false;
            }

            Coord c = (Coord) obj;

            // Compare the data members and return accordingly
            return ((this.x == c.x) && (this.y == c.y));
        }
    }

}
