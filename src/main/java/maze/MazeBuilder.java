package maze;

import utilities.Direction;

import java.io.*;

/**
 * Class that builds a maze.
 */
public class MazeBuilder {

    /**
     * Builds a main.java.maze from a given file.
     * @param filename The name of the file that contains the main.java.maze connectivity.
     * @return A maze.
     */
    public static Maze build(String filename) throws IOException {

        File file = new File(filename);
        BufferedReader reader;

        try {
            // This is the object that reads the file:
            reader = new BufferedReader(new FileReader(file));
            Maze maze = MazeBuilder.doCheckAndBuildMaze(reader);
            MazeBuilder.buildMazeConnectivity(maze);
            reader.close();
            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("Invalid filename given, please try again.");
            System.exit(1);
        }

        return null;
    }


    /**
     * Given the maze it builds the connections of each cell.
     * @param maze A maze instance.
     */
    protected static void buildMazeConnectivity(Maze maze) {

        if (maze == null) {
            throw new NullPointerException("Null maze was provided.");
        }

        int xWidth = maze.getxWidth();
        int yWidth = maze.getyWidth();

        // Loop over cells and build up the neighbours:
        for (int c = 0; c < maze.size(); c++) {

            MazeCell cell = maze.getCell(c);
            int cellID = cell.getId();

            Point coords = cell.getCoordinates();
            int i  = coords.i;
            int j = coords.j;


            // First the SOUTH & NORTH are build up:
            if (i == xWidth - 1) {

                // This is the top row:
                cell.setNeighbours(null, Direction.NORTH);
                cell.setNeighbours(maze.getCell(cellID - yWidth), Direction.SOUTH);
            }
            else if (i == 0) {

                // This is the bottom row:
                cell.setNeighbours(null, Direction.SOUTH);
                cell.setNeighbours(maze.getCell(cellID + yWidth), Direction.NORTH);
            }
            else {

                // Now the NORTH & SOUTH cells are build up:
                cell.setNeighbours(maze.getCell(cellID - yWidth), Direction.SOUTH);
                cell.setNeighbours(maze.getCell(cellID + yWidth), Direction.NORTH);
            }

            // Now the EAST & WEST are build up:
            if (j == yWidth - 1) {

                // This is the right most cell it does not have any neighbours from the EAST:
                cell.setNeighbours(null, Direction.EAST);
                cell.setNeighbours(maze.getCell(cellID - 1), Direction.WEST);
            }
            else if (j == 0) {

                // This is the left side row:
                cell.setNeighbours(null, Direction.WEST);
                cell.setNeighbours(maze.getCell(cellID + 1), Direction.EAST);
            }
            else {
                // Now the EAST & WEST are build up:
                cell.setNeighbours(maze.getCell(cellID + 1), Direction.EAST);
                cell.setNeighbours(maze.getCell(cellID - 1), Direction.WEST);
            }
        }
    }


    /**
     * Checks given file and builds a maze.
     * @param reader The file reader.
     * @return A maze.
     */
    protected static Maze doCheckAndBuildMaze(BufferedReader reader) {

        Maze maze = new Maze();

        try {

            String line = reader.readLine();
            MazeBuilder.checkNullLine(line);

            String[] lineData = line.split("=");
            MazeBuilder.trimArray(lineData);
            String[] shouldBe = {"n_rows", "NonZeroInteger"};

            MazeBuilder.checkLineData(lineData, shouldBe);

            int nx = Integer.parseInt(lineData[1]);

            line = reader.readLine();
            MazeBuilder.checkNullLine(line);

            lineData = line.split("=");
            MazeBuilder.trimArray(lineData);
            shouldBe[0] = "n_cols";

            MazeBuilder.checkLineData(lineData, shouldBe);

            int ny = Integer.parseInt(lineData[1]);

            maze.initialize(nx, ny);

            int counter = 0;

            // Create maze cells.
            for (int i = 0; i < nx; i++) {
                for (int j = 0; j < ny; j++) {
                    // By default all cell are open
                    maze.addCell(new MazeCell(counter++, i, j, "O"));
                }
            }

            // Loop over the file and actually read whether the cell is blocked or not
            line = reader.readLine();
            String[] cellData;
            String[] cellDataShouldBe = {"Integer", "OorX"};

            while (line != null) {

                cellData = line.split(",");
                MazeBuilder.trimArray(cellData);
                MazeBuilder.checkLineData(cellData, cellDataShouldBe);

                maze.setCellBlockedFlag(Integer.parseInt(cellData[0]), cellData[1]);
                line = reader.readLine();
            }

            return maze;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }


    /**
     * Checks for empty lines in the maze file
     * @param line The given line.
     */
    protected static void checkNullLine(String line) {
        if (line == null) {
            throw new NullPointerException("Null line found");
        }
    }


    /**
     * Checks each line of the given maze file.
     * @param lineIs The given format
     * @param lineShouldBe The expected format.
     */
    protected static void checkLineData(String[] lineIs, String[] lineShouldBe) {

        if (lineIs == null || lineIs.length == 0) {
            throw new NullPointerException("No line data is provided.");
        }

        // The line may be null or not what we expected.
        for (int i = 0; i < lineIs.length; i++) {

            if (lineIs[i] == null) {
                throw new NullPointerException("Null line found when expecting: " + lineShouldBe);
            }

            if (!lineIs[i].equals(lineShouldBe[i])) {
                if (lineShouldBe[i].equals("NonZeroInteger")) {

                    if (!MazeBuilder.isInteger(lineIs[i])) {
                        throw new IllegalArgumentException("Expected integer but got " + lineIs[i]);
                    }
                    if (lineIs[i].equals("0")) {
                        throw new IllegalArgumentException("Expected non zero integer but got one.");
                    }
                }
                else if (lineShouldBe[i].equals("Integer") && !MazeBuilder.isInteger(lineIs[i])) {
                    throw new IllegalArgumentException("Expected an integer but found: " + lineIs[i]);
                }
                else if (lineShouldBe[i].equals("OorX") && (!lineIs[i].equals("O") && !lineIs[i].equals("X"))) {
                    throw new IllegalArgumentException("Expected an O or an X but found: " + lineIs[i]);
                }
            }
        }
    }


    /**
     * Returns true if given string can be parsed to integer.
     *
     * @param s The given string
     * @return Boolean
     */
    protected static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }


    /**
     * Trims a given array.
     *
     * @param array The array to trim
     */
    protected static final void trimArray(String[] array) {

        if (array == null) {
            throw new NullPointerException("Null array for trim.");
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }
}
