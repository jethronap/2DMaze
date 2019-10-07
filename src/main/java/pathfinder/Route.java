package pathfinder;

import java.util.ArrayList;

/**
 * Represents a collection of points
 * that form a path in a maze.
 */
public class Route {

    /**
     * The list of points that form the path that
     * this route represents.
     */
    private ArrayList<Integer> points;


    public Route() {
        this.points = new ArrayList<Integer>();
    }


    /**
     *
     * @param i Item in route.
     * @return The i-th item in route.
     */
    public final Integer getItem(int i) {
        return this.points.get(i);
    }


    /**
     * Sets the points of a path.
     * @param path List of points that were set.
     */
    public final void setPath(final ArrayList<Integer> path) {

        for (int i = 0; i < path.size(); i++) {
            this.points.add(path.get(i));
        }
    }


    /**
     * @return How many items are in route.
     */
    public final int size() {
        return this.points.size();
    }


    /**
     * @return True if route is empty.
     */
    public final boolean empty() {
        return this.points.size() == 0;
    }


    /**
     * Adds an item for this route.
     * @param p Item for path.
     */
    public final void addItem(Integer p) {
        this.points.add(p);
    }
}
