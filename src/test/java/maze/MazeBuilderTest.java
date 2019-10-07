package maze;


import org.junit.Test;
import utilities.Direction;
import utilities.Utils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class MazeBuilderTest {

    /**
     * Test Scenario: The application attempts to build a null Maze.
     * Expected Output: NullPointerException is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullMaze() throws IOException {

        MazeBuilder.build(null);
    }


    /**
     * Test Scenario: The application attempts to build a Maze with a valid & supported format.
     * Expected Output: The Maze should be build correctly.
     */
    @Test
    public void testValidFormat() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_3_3.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 3 * 3);

        // Let's get all the data verified:
        for (int c = 0; c < maze.size(); c++) {

            MazeCell cell = maze.getCell(c);
            assertEquals(cell.getId(), c);

            Point coordinates = cell.getCoordinates();

            if (c == 0) {

                assertTrue(coordinates.equals(new Point(0, 0)));
                assertFalse(cell.isBlocked());

                assertNull(cell.getNeighbour(Direction.WEST));
                assertNull(cell.getNeighbour(Direction.SOUTH));

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 1);

                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 3);
            }
            if (c == 1) {

                assertTrue(coordinates.equals(new Point(0, 1)));
                assertTrue(cell.isBlocked());

                assertNull(cell.getNeighbour(Direction.SOUTH));

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 0);

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 2);

                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 4);
            }
            if (c == 2) {

                assertTrue(coordinates.equals(new Point(0,2)));
                assertNull(cell.getNeighbour(Direction.SOUTH));

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 1);

                assertNull(cell.getNeighbour(Direction.EAST));


                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 5);
            }
            if (c == 3) {

                assertTrue(coordinates.equals(new Point(1, 0)));
                assertFalse(cell.isBlocked());

                assertNull(cell.getNeighbour(Direction.WEST));

                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 0);

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 4);

                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 6);
            }
            if (c == 4) {

                assertTrue(coordinates.equals(new Point(1, 1)));
                assertFalse(cell.isBlocked());

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 3);

                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 1);

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 5);

                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 7);
            }
            if (c == 5) {

                assertTrue(coordinates.equals(new Point(1, 2)));
                assertFalse(cell.isBlocked());

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 4);

                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 2);

                assertNull(cell.getNeighbour(Direction.EAST));

                assertNotNull(cell.getNeighbour(Direction.NORTH));
                assertEquals(cell.getNeighbour(Direction.NORTH).getId(), 8);
            }
            if (c == 6) {

                assertTrue(coordinates.equals(new Point(2, 0)));
                assertFalse(cell.isBlocked());

                assertNull(cell.getNeighbour(Direction.WEST));


                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 3);

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 7);

                assertNull(cell.getNeighbour(Direction.NORTH));
            }
            if (c == 7) {

                assertTrue(coordinates.equals(new Point(2, 1)));
                assertTrue(cell.isBlocked());

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 6);


                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 4);

                assertNotNull(cell.getNeighbour(Direction.EAST));
                assertEquals(cell.getNeighbour(Direction.EAST).getId(), 8);

                assertNull(cell.getNeighbour(Direction.NORTH));
            }
            if (c == 8) {

                assertTrue(coordinates.equals(new Point(2, 2)));
                assertFalse(cell.isBlocked());

                assertNotNull(cell.getNeighbour(Direction.WEST));
                assertEquals(cell.getNeighbour(Direction.WEST).getId(), 7);


                assertNotNull(cell.getNeighbour(Direction.SOUTH));
                assertEquals(cell.getNeighbour(Direction.SOUTH).getId(), 5);

                assertNull(cell.getNeighbour(Direction.EAST));
                assertNull(cell.getNeighbour(Direction.NORTH));
            }
        }
    }


    /**
     * Test Scenario: The application attempts to build a Maze with a valid & supported format.
     * Expected Output: The Maze should be build correctly.
     */
    @Test
    public void testValidFormat12X20() throws IOException {

        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/maze_12_20.txt";

        Maze maze = MazeBuilder.build(filePath);

        assertNotNull(maze);
        assertEquals(maze.size(), 12 * 20);

        int xWidth = maze.getxWidth();
        int yWidth = maze.getyWidth();

        for (int c = 0; c < maze.size(); ++c) {

            MazeCell cell = maze.getCell(c);

            int i = cell.getCoordinates().i;
            int j = cell.getCoordinates().j;

            for (int n = 0; n < cell.getNumberOfNeighbours(); ++n) {

                Direction direction = Utils.convertIntToDirection(n);
                MazeCell nn = cell.getNeighbour(direction);

                if (direction == Direction.SOUTH) {

                    if (nn == null) {
                        assertEquals(i,0);
                    }
                    else {
                        assertEquals(nn.getId(), cell.getId() - yWidth);
                    }
                }
                if (direction == Direction.NORTH) {

                    if (nn == null) {
                        assertEquals(i, xWidth - 1);
                    }
                    else {
                        assertEquals(nn.getId(), cell.getId() + yWidth);
                    }
                }
                if (direction == Direction.WEST) {

                    if (nn == null) {
                        assertEquals(j, 0);
                    }
                    else {
                        assertEquals(nn.getId(), cell.getId() - 1);
                    }
                }
                if (direction == Direction.EAST) {

                    if (nn == null) {
                        assertEquals(j, yWidth - 1);
                    }
                    else {
                        assertEquals(nn.getId(), cell.getId() + 1);
                    }
                }
            }
        }
    }
}
