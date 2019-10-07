package algorithms;

import maze.Maze;
import maze.MazeBuilder;
import maze.MazeCell;
import maze.Point;
import org.junit.Test;
import pathfinder.PathFinder;
import pathfinder.Route;
import utilities.Direction;
import utilities.Utils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AStarTest {

    /**
     * Test Scenario: The application attempts to use A* algorithm with
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

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point();
        Point goal = new Point(0, 0);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with
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

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point();

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with
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

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(2, 1);
        Point goal = new Point(0, 0);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with
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

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point(2, 1);

        finder.find(maze, start, goal);
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with valid Maze, start and goal location.
     * Expected Output: A valid route should be computed.
     */
    @Test
    public void testAStarPathFinder() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point(2, 2);

        Route route = finder.find(maze, start, goal);

        assertNotNull(route);
        assertEquals(route.size(), 5);

        int[] cellIDs = {0, 3, 4, 5, 8};

        for(int i = 0; i < route.size(); ++i){
            Integer item = route.getItem(i);
            assertEquals(item.intValue(), cellIDs[i]);
        }
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with valid Maze, start and goal location.
     * Expected Output: A valid route should be computed.
     */
    @Test
    public void testAStarPathFinder12X20() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_12_20.txt";

        Maze maze = MazeBuilder.build(filePath);

        int xWidth = maze.getxWidth();
        int yWidth = maze.getyWidth();

        for(int c=0; c<maze.size(); ++c){

            MazeCell cell = maze.getCell(c);

            int i = cell.getCoordinates().i;
            int j = cell.getCoordinates().j;

            for(int n=0; n<cell.getNumberOfNeighbours(); ++n){

                Direction dir = Utils.convertIntToDirection(n);
                MazeCell nn = cell.getNeighbour(dir);

                if(dir == Direction.SOUTH){

                    if(nn == null){
                        assertEquals(i, 0);
                    }
                    else{
                        assertEquals(nn.getId(), cell.getId() - yWidth);
                    }
                }

                if(dir == Direction.NORTH){

                    if(nn == null){
                        assertEquals(i, xWidth - 1);
                    }
                    else{
                        assertEquals(nn.getId(), cell.getId() + yWidth);
                    }
                }

                if(dir == Direction.WEST){

                    if(nn == null){
                        assertEquals(j, 0);
                    }
                    else{
                        assertEquals(nn.getId(), cell.getId() - 1);
                    }
                }

                if(dir == Direction.EAST){

                    if(nn == null){
                        assertEquals(j, yWidth - 1);
                    }
                    else{
                        assertEquals(nn.getId(), cell.getId() +1 );
                    }
                }
            }
        }

        assertNotNull(maze);
        assertEquals(maze.size(), 12 * 20);

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point(11, 19);

        Route route = finder.find(maze, start, goal);
        assertNotNull(route);
        assertFalse(route.empty());

        int[] cellIDs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                12, 32, 52, 53, 54, 55, 56, 57, 58,
                59, 79,  99, 119, 139, 159, 179, 199, 219, 239};

        assertEquals(route.size(), cellIDs.length);

        for(int i = 0; i < route.size(); ++i){
            Integer item = route.getItem(i);
            assertEquals(item.intValue(), cellIDs[i]);
        }
    }


    /**
     * Test Scenario: The application attempts to use A* algorithm with valid Maze, start and goal location
     *                But there is not open path between the start and goal.
     * Expected Output: A valid but empty route should be computed.
     */
    @Test
    public void testAStarPathFinderBlockedDiagonal() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3_open_diagonal_only.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        PathFinder finder = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());

        Point start = new Point(0, 0);
        Point goal = new Point(2, 2);

        Route route = finder.find(maze, start, goal);

        assertNotNull(route);
        assertEquals(route.size(), 0);
        assertTrue(route.empty());
    }
}
