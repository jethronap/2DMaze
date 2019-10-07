package algorithms;


import maze.Maze;
import maze.MazeBuilder;
import maze.Point;
import org.junit.Test;
import pathfinder.PathFinder;
import pathfinder.Route;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit tests for DijkstraPathFinder class.
 */
public class DijkstraTest {

    /**
     * Test Scenario: The application attempts to use DijkstraPathFinder with
     *                an invalid start position i.e. not in the Maze.
     * Expected Output: IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartLocation() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point();
        Point goal  =  new Point(0, 0);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use DijkstraPathFinder with
     *                an invalid goal position i.e. not in the Maze.
     * Expected Output: IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGoalLocation() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal  =  new Point();

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use DijkstraPathFinder with
     *                a blocked start position i.e. not in the Maze.
     * Expected Output: IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBlockedStartLocation() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point(2, 1);
        Point goal  =  new Point(0, 0);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use DijkstraPathFinder with
     *                a blocked goal position i.e. not in the Maze.
     * Expected Output: IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBlockedGoalLocation() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal  =  new Point(2, 1);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use Dijkstra algorithm with valid Maze, start and goal location.
     * Expected Output: A valid route should be computed.
     */
    @Test
    public void testDijkstraPathFinder() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3_lower_right_blocked.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull("Null maze instance", maze);
        assertEquals("Invalid maze size", maze.size(), 3 * 3);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        Route route = finder.find(maze, start, end);

        // The expected route:
        int[] cellIds = {0, 3, 6, 7, 8};

        assertNotNull("Null route", route);
        assertEquals("Incorrect route size", cellIds.length, route.size());


        for(int i=0; i<route.size(); ++i){
            Integer item = route.getItem(i);
            assertEquals("Invalid cell id in route", item.intValue(), cellIds[i]);
        }

    }


    /**
     * Test Scenario: The application attempts to use Dijkstra algorithm with valid Maze, start and goal location.
     * Expected Output: A valid route should be computed.
     */
    @Test
    public void testDijkstraPathFinder12X20() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_12_20.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull("Null maze instance", maze);
        assertEquals("Invalid maze size", maze.size(), 12 * 20);

        PathFinder finder = new DijkstraPathFinder(new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point(11, 19);

        Route route = finder.find(maze, start, goal);
        assertNotNull("Null route instance", route);
        assertFalse("Route is empty", route.empty());

        int[] cellIDs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                12, 32, 52, 53, 54, 55, 56, 57, 58,
                59, 79,  99, 119, 139, 159, 179, 199, 219, 239};

        assertEquals("Invalid route size", route.size(), cellIDs.length);

        for (int i = 0; i < route.size(); ++i) {

            Integer item = route.getItem(i);
            assertEquals(item.intValue(), cellIDs[i]);
        }
    }
}
