package algorithms;

import maze.Point;

/**
 *  Some path finding algorithms like A* use heuristic functions
 *  in order to work. Often this heuristic may simply be the distance
 *  between two positions. This interface provides a common contract
 *  for distance calculation between two Points.
 *
 */
public interface DistanceCalculator {

    /**
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between two points.
     */
    double calculateDistance(final Point p1, final Point p2);
}
