package maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class StudentResult implements Result {
    private final List<Point> path = new ArrayList<>();

    @Override
    public void addLocation(int x, int y) {
        path.add(new Point(x, y));
    }

    public List<Point> getPath() {
        return path;
    }
}