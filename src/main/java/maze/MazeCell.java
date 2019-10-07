package maze;

import utilities.Direction;
import utilities.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a cell in the Maze.
 */
public class MazeCell {

    /**
     * Holds the i, j coordinates of the cell.
     */
    private Point coordinates;

    /**
     * The global id of the cell.
     */
    private int id = -1;

    /**
     * Flag indicating whether cell is blocked or not.
     */
    private boolean isBlocked = false;

    /**
     * The neighbours of the cell.
     * For mapping of Direction to int,
     * see Utils.java.
     */
    private Map<Direction, MazeCell> neighbours;


    /**
     * Constructor:
     *
     * @param id The global cell id,
     * @param i  The i coordinate of the cell,
     * @param j  The j coordinate of the cell,
     * @param b  The flag indicating cell blockage.
     */
    public MazeCell(int id, int i, int j, String b) {

        this.id = id;
        this.coordinates = new Point(i, j);
        this.setIsBlockedFlag(b);
        neighbours = new HashMap();
    }


    /**
     * @return the global id of the cell.
     */
    public final int getId() {
        return this.id;
    }


    /**
     * @return the coordinates of the cell.
     */
    public Point getCoordinates() {
        return this.coordinates;
    }


    /**
     *
     * @param i The neighbour cell.
     * @return The i-th neighbour by converting integer to direction.
     */
    public final MazeCell getNeighbour(int i) {

        Direction dir = Utils.convertIntToDirection(i);
        return neighbours.get(dir);
    }


    /**
     *
     * @param dir The direction.
     * @return The i-th neighbour given the direction.
     */
    public final MazeCell getNeighbour(Direction dir) {
        return neighbours.get(dir);
    }


    /**
     * @return The number of neighbours a cell has.
     */
    public final int getNumberOfNeighbours() {
        return this.neighbours.size();
    }

    /**
     * Set the flag indicating whether cell is blocked or not.
     *
     * @param flag "O" = open or "X" = blocked.
     */
    public final void setIsBlockedFlag(String flag) {

        if (flag == null) {
            throw new NullPointerException("Null flag was provided.");
        }
        else if (!flag.equals("O") && !flag.equals("X")) {
            throw new IllegalArgumentException("Flag should be either O or X but got: " + flag);
        }
        else if (flag.equals("X")) {
            this.isBlocked = true;
        }
        else if (flag.equals("O")) {
            this.isBlocked = false;
        }
    }


    /**
     * Set the neighbours of the cell for the given direction.
     *
     * @param n   The main.java.maze cell.
     * @param dir The given direction.
     */
    public void setNeighbours(MazeCell n, Direction dir) {
        this.neighbours.put(dir, n);
    }


    /**
     * @return true if cell is blocked.
     */
    public final boolean isBlocked() {
        return this.isBlocked;
    }

}
