package algorithms;

import maze.Point;
import utilities.Pair;

public class EuclideanCalculator
        implements DistanceCalculator, HeuristicFunction<Pair<Point, Point>, Double> {

    /**
     * Implements the HeuristicFunction interface.
     * @param in The input to the heuristic.
     * @return The distance between two points.
     */
    public Double heuristicOut(final Pair<Point, Point> in) {
        return this.calculateDistance(in.first, in.second);
    }


    /**
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between two points.
     */
    public double calculateDistance(final Point p1, final Point p2) {

        double dx = p1.i - p2.i;
        double dy = p1.j - p2.j;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
