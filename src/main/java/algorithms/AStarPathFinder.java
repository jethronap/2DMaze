package algorithms;

import maze.Maze;
import maze.MazeCell;
import maze.Point;
import pathfinder.PathFinder;
import pathfinder.Route;
import utilities.Pair;

import java.util.*;

import static utilities.Utils.makePair;

/**
 * An Implementation of A* path finding algorithm.
 */
public class AStarPathFinder implements PathFinder {

    private HeuristicFunction<Pair<Point, Point>, Double> heuristic;
    private DistanceCalculator distanceCalculator;

    /**
     * Constructor.
     *
     * @param distanceCalculator The distance.
     * @param heuristic          The heuristic.
     */
    public AStarPathFinder(DistanceCalculator distanceCalculator,
                           HeuristicFunction<Pair<Point, Point>, Double> heuristic) {

        this.distanceCalculator = distanceCalculator;
        this.heuristic = heuristic;
    }

    protected class AStarCell {

        public MazeCell cell;


        /**
         * The cost of a path that so far leads this node.
         */
        int gCost = Integer.MAX_VALUE;


        /**
         * The total cost for A* for getting from
         * the start node to the goal node by passing from that node.
         */
        int fCost = Integer.MAX_VALUE;


        /**
         * Constructor.
         *
         * @param cell The MazeCell that this AStarCell maps to.
         */
        AStarCell(MazeCell cell) {
            this.cell = cell;
        }


        @Override
        public final boolean equals(Object other) {

            if (this == other) {
                return true;
            }
            if (!(other instanceof AStarCell)) {
                return false;
            }

            Point coordinatesThis = cell.getCoordinates();
            Point coordinatesOther = ((AStarCell) other).cell.getCoordinates();
            return coordinatesThis.equals(coordinatesOther);
        }


        @Override
        public final int hashCode() {
            return cell.getId();
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
        PriorityQueue<AStarCell> open = new PriorityQueue<>(new Comparator<AStarCell>() {
            @Override
            public int compare(AStarCell o1, AStarCell o2) {
                if (o1.fCost > o2.fCost)
                    return 1;
                if (o1.fCost < o2.fCost)
                    return -1;
                return 0;
            }
        });

        // The explored set of positions:
        Set<MazeCell> explored = new HashSet<MazeCell>();

        // This is where the path is stored:
        HashMap<Integer, Integer> cameFrom = new HashMap<>();

        AStarCell source = new AStarCell(mCellStart);

        // Add the source:
        open.add(source);

        // The cost of the path so far leading to this node is 0 at the beginning.
        source.gCost = 0;

        // The calculation of fCost from start to goal is done heuristically:
        source.fCost = this.heuristic.heuristicOut(makePair(start, goal)).intValue();

        AStarCell target = new AStarCell(mCellGoal);

        while (!open.isEmpty()) {

            // Get the current node out of the set:
            AStarCell currentNode = open.poll();

            // Check if current node is the goal:
            if (currentNode.equals(target)) {
                break;
            }

            /**
             * If current node is not the goal we proceed,
             * add this node to the explored set.
             */
            explored.add(currentNode.cell);

            // Loop over the neighbours to expand the search:
            for (int n = 0; n < currentNode.cell.getNumberOfNeighbours(); n++) {

                // Get the n-th neighbour of the current node:
                MazeCell nn = currentNode.cell.getNeighbour(n);

                // If the neighbour is null we continue:
                if (nn == null) {
                    continue;
                }

                // If the neighbour is blocked neglect it:
                if (nn.isBlocked()) {
                    continue;
                }

                // If the neighbour is in the explored set neglect it:
                if (explored.contains(nn)) {
                    continue;
                }

                AStarCell aCell = new AStarCell(nn);

                // If the neighbour is not in the open set add it:
                if (!open.contains(aCell)) {
                    open.add(aCell);
                }

                // This is the cost of the path from current node to reach its neighbour:
                int tgCost = (int) (
                        currentNode.gCost +
                                this.distanceCalculator.calculateDistance(currentNode.cell.getCoordinates(),
                                        aCell.cell.getCoordinates()));
                if (tgCost >= aCell.gCost) {
                    // This is not a better path:
                    continue;
                }

                // This is the best path up until now so record it:
                cameFrom.put(aCell.cell.getId(), currentNode.cell.getId());
                aCell.gCost = tgCost;

                // Calculation of f(nn) = g(nn) + h(nn):
                aCell.fCost = (int) (aCell.gCost + this.heuristic
                        .heuristicOut(makePair(aCell.cell.getCoordinates(), goal)));
            }
        }

        ArrayList<Integer> path = this.reconstructPath(cameFrom, mCellGoal);

        route.setPath(path);
        return route;
    }


    /**
     * Reconstructs the path from the given HashMap.
     *
     * @param map  The map of all nodes.
     * @param goal The goal.
     * @return A reversed path from the goal.
     */
    private final ArrayList<Integer> reconstructPath(HashMap<Integer, Integer> map, MazeCell goal) {

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
