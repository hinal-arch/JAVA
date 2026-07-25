import java.util.Objects;

class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

   
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

   
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Main {
    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2),   // repeat
            new Point(5, 6),
            new Point(3, 4)    // repeat
        };

        int distinctCount = 0;

        for (int i = 0; i < points.length; i++) {
            boolean seenBefore = false;

            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    seenBefore = true;
                    break;
                }
            }

            if (!seenBefore) {
                distinctCount++;
            }

            System.out.println(points[i]);
        }

        System.out.println("Distinct: " + distinctCount);
    }
}
