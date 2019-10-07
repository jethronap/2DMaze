package pathfinder;

import maze.Maze;
import maze.MazeCell;
import maze.Point;

/**
 * Basic Interface that models a path finder.
 */
public interface PathFinder {

    /**
     * Finds a Route in a given Maze.
     * @param maze The given maze.
     * @param start The starting point.
     * @param goal The finishing point.
     * @return Route.
     */
    Route find(final Maze maze, final Point start, final Point goal);


    /**
     * Performs checks on cell position.
     * @param c The given cell.
     * @param <Cell> The Cell object which extends MazeCell.
     */
    static <Cell extends MazeCell> void checkCell(Cell c) {

        if (c == null) {
            throw new IllegalArgumentException("Invalid cell. Cell not in Maze.");
        }

        if (c.isBlocked()) {
            throw new IllegalArgumentException("Cell is blocked. Please change your location.");
        }
    }
}
