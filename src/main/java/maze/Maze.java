package maze;

import java.util.ArrayList;

/**
 * Class that represents a 2D Maze.
 */
public class Maze {

    /**
     * Number of cells in x direction.
     */
    private int xWidth;

    /**
     * Number of cells in y direction.
     */
    private int yWidth;

    /**
     * The main.java.maze cells.
     */
    private ArrayList<MazeCell> cells;

    public Maze() {
    }


    public Maze(int xWidth, int yWidth) {
        this.initialize(xWidth, yWidth);
    }


    /**
     * Returns the c-th maze cell
     *
     * @param c the index of the cell.
     * @return the maze cell at given index.
     */
    public final MazeCell getCell(int c) {
        return this.cells.get(c);
    }


    /**
     * How many cells is the maze currently holding.
     *
     * @return the current size of the maze.
     */
    public final int size() {
        return this.cells.size();
    }


    /**
     * @return cells in x-direction.
     */
    public final int getxWidth() {
        return this.xWidth;
    }


    /**
     * @return cells in y-direction.
     */
    public final int getyWidth() {
        return this.yWidth;
    }


    /**
     * Sets blocked or open status for the given cell.
     *
     * @param id   Global id of cell.
     * @param flag Blocked or open flag for cell.
     */
    public final void setCellBlockedFlag(int id, String flag) {

        this.cells.get(id).setIsBlockedFlag(flag);
    }


    /**
     * Creates an x * y maze.
     *
     * @param xWidth Cells in x direction.
     * @param yWidth Cells in y direction.
     */
    public final void initialize(int xWidth, int yWidth) {
        if (xWidth * yWidth == 0) {
            throw new IllegalArgumentException("Cannot create a Maze without any cells.");
        }

        this.cells = new ArrayList<MazeCell>(xWidth * yWidth);
        this.xWidth = xWidth;
        this.yWidth = yWidth;
    }


    /**
     * Adds a cell to the maze.
     *
     * @param cell A maze cell.
     */
    public final void addCell(MazeCell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("Cell trying to be added is null.");
        }

        this.cells.add(cell);
    }


    /**
     * Finds a cell in the maze.
     * @param coordinates Coordinates of the cell.
     * @return The respective cell. Otherwise, null(i.e. cell not found).
     */
    public final MazeCell findCell(Point coordinates) {

        for (int c = 0; c < this.size(); c++) {

            MazeCell cell = this.cells.get(c);
            if (cell.getCoordinates().equals(coordinates)) {
                return cell;
            }
        }
        return null;
    }
}
