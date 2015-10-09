import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final static int MIN_COLLINEAR_COUNT = 3;
    private List<LineSegment> segments = new LinkedList<>();

    public FastCollinearPoints(Point[] points) {
        if (null == points) throw new NullPointerException();

        int notEqualsCount = 0;
        for (int i = 0; i < points.length - 1; i++)
            if (!points[i].equals(i + 1))
                notEqualsCount++;
        if(notEqualsCount == 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 1; i++) {
            Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
            int collinearCount = 1;
            for (int j = i + 1; j < points.length - 1; j++) {
                if (points[i].compareTo(points[j]) == 0) continue;
                if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[j + 1])) == 0) {
                    collinearCount++;
                } else if (collinearCount >= MIN_COLLINEAR_COUNT) {
                    segments.add(new LineSegment(points[i], points[j]));
                    break;
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

}
