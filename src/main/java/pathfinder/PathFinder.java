package pathfinder;

import maze.Maze;
import maze.MazeCell;
import maze.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

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

    /**
     * Reconstructs the path from the given HashMap.
     *
     * @param map  The map of all nodes.
     * @param goal The goal.
     * @return A reversed path from the goal.
     */
    static ArrayList<Integer> reconstructPath(HashMap<Integer, Integer> map, MazeCell goal) {

        ArrayList<Integer> nodes = new ArrayList<>();

        if (map.isEmpty()) {
            return nodes;
        }

        nodes.add(goal.getId());

        // This is the id of the node that led to the goal:
        Integer next = map.get(goal.getId());
        nodes.add(next);

        Set<Integer> keys = map.keySet();

        while (keys.contains(next)) {

            next = map.get(next);
            nodes.add(next);
        }

        // Finally reverse the array:
        Collections.reverse(nodes);
        return nodes;
    }
}
