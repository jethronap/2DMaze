package pathfinder;


import algorithms.AStarPathFinder;
import algorithms.DijkstraPathFinder;
import algorithms.EuclideanCalculator;
import maze.Maze;
import maze.MazeBuilder;
import maze.Point;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Starting point of the application.
 */
public class PathFinderApp {

    /**
     * Start the application by providing
     * 1.  Name of file for reading the maze
     * 2.  Start position
     * 3.  Goal position
     * 4.  PathFinder type
     */

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Specify the maze file name: ");

        String mazeFilename = scanner.nextLine();
        File directory = new File("./");
        String filePath = directory.getCanonicalPath() + "/src/test_data/" + mazeFilename;

        Maze maze = MazeBuilder.build(filePath);

        System.out.println("Specify starting point: ");
        System.out.println("The x-coordinate:");
        int i = scanner.nextInt();
        System.out.println("The y-coordinate:");
        int j = scanner.nextInt();
        Point start = new Point(i, j);

        System.out.println("Specify goal point: ");
        System.out.println("The x-coordinate:");
        int k = scanner.nextInt();
        System.out.println("The y-coordinate:");
        int l = scanner.nextInt();
        Point goal = new Point(k, l);

        System.out.println("Specify path finding algorithm: ");
        System.out.println("Press 1 for AStar.");
        System.out.println("Press 2 for Dijkstra.");

        int pathFinder = scanner.nextInt();

        switch (pathFinder) {

            case 1:
                PathFinder pathFinderAStar = new AStarPathFinder(new EuclideanCalculator(), new EuclideanCalculator());
                Route routeAStar = pathFinderAStar.find(maze, start, goal);
                for (int c = 0; c < routeAStar.size(); ++c) {
                    Integer item = routeAStar.getItem(c);
                    System.out.print(item+", ");
                }
                break;

            case 2:
                PathFinder pathFinderDijkstra = new DijkstraPathFinder(new EuclideanCalculator());
                Route routeDijkstra = pathFinderDijkstra.find(maze, start, goal);
                for (int c = 0; c < routeDijkstra.size(); ++c) {
                    Integer item = routeDijkstra.getItem(c);
                    System.out.print(item+", ");
                }
                break;
        }
    }
}
