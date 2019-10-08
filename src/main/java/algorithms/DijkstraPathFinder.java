package algorithms;

import maze.Maze;
import maze.MazeCell;
import maze.Point;
import pathfinder.PathFinder;
import pathfinder.Route;

import java.util.*;


/**
 * Single source shortest path finding using
 * Dijkstra's algorithm
 */
public class DijkstraPathFinder implements PathFinder {

    private DistanceCalculator distanceCalculator;


    /**
     * Constructor.
     */
    public DijkstraPathFinder(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    protected class DijkstraCell {

        MazeCell cell;
        int distanceToGoal = Integer.MAX_VALUE;

        public DijkstraCell(MazeCell cell, int distanceToGoal) {
            this.cell = cell;
            this.distanceToGoal = distanceToGoal;
        }


        public DijkstraCell(MazeCell cell) {
            this(cell, Integer.MAX_VALUE);
        }

        @Override
        public final boolean equals(Object other) {

            if (this == other) {
                return true;
            }
            if (!(other instanceof DijkstraCell)) {
                return false;
            }

            return cell.equals(((DijkstraCell) other).cell);
        }

        @Override
        public final int hashCode() {
            return this.cell.getId();
        }
    }


    /**
     * @param maze  The given maze.
     * @param start The starting point.
     * @param goal  The finishing point.
     * @return A route.
     */
    public Route find(final Maze maze, final Point start, final Point goal) {

        // Find the cell that actually corresponds to the source location:
        MazeCell mCellStart = maze.findCell(start);

        PathFinder.checkCell(mCellStart);

        // Find the cell that corresponds to the goal location:
        MazeCell mCellGoal = maze.findCell(goal);

        PathFinder.checkCell(mCellGoal);

        Route route = new Route();

        // If starting point is the same to the goal there is nothing else to do:
        if (start.equals(goal)) {
            route.addItem(mCellStart.getId());
            System.out.println("Starting point is the same as the goal. Bye!");
            return route;
        }

        // Cells not explored yet:
        PriorityQueue<DijkstraPathFinder.DijkstraCell> open =
                new PriorityQueue<>(new Comparator<DijkstraPathFinder.DijkstraCell>() {
                    @Override
                    public int compare(DijkstraPathFinder.DijkstraCell o1,
                                       DijkstraPathFinder.DijkstraCell o2) {

                        if (o1.distanceToGoal > o2.distanceToGoal)
                            return 1;
                        if (o1.distanceToGoal < o2.distanceToGoal)
                            return -1;
                        return 0;
                    }
                });

        // The explored set of positions:
        Set<MazeCell> explored = new HashSet<MazeCell>();

        // This is where the path is stored:
        HashMap<Integer, Integer> cameFrom = new HashMap<>();

        DijkstraCell source = new DijkstraCell(mCellStart, 0);

        // Add the source:
        open.add(source);

        while (!open.isEmpty()) {

            // Get the current node out of the set:
            DijkstraCell currentNode = open.poll();

            // This node is explored regardless of being the node or not:
            explored.add(currentNode.cell);

            // Loop over the neighbours to expand the search:
            for (int n = 0; n < currentNode.cell.getNumberOfNeighbours(); n++) {

                // Get the n-th neighbour of the current node:
                MazeCell nn = currentNode.cell.getNeighbour(n);

                if (nn != null && !nn.isBlocked()) {

                    if (!explored.contains(nn)) {

                        // Find the DijkstraCell belonging to this node:
                        DijkstraCell dijkstraCell = new DijkstraCell(nn);

                        int distance = (int) distanceCalculator
                                .calculateDistance(currentNode.cell.getCoordinates(), nn.getCoordinates());

                        if (currentNode.distanceToGoal != Integer.MAX_VALUE) {

                            if (currentNode.distanceToGoal + distance < dijkstraCell.distanceToGoal) {
                                dijkstraCell.distanceToGoal = currentNode.distanceToGoal + distance;
                                cameFrom.put(dijkstraCell.cell.getId(), currentNode.cell.getId());
                                open.add(dijkstraCell);
                            }
                        }
                        else {

                            // Simply add the new DijkstraCell:
                            open.add(dijkstraCell);
                        }
                    }
                }
            }
        }

        ArrayList<Integer> path = PathFinder.reconstructPath(cameFrom, mCellGoal);

        route.setPath(path);
        return route;
    }
}
