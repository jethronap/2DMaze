package algorithms;

/**
 *  Some path finding algorithms like A* use heuristic functions
 *  in order to work. Often this heuristic may simply be the distance
 *  between two positions. This interface provides a common contract
 *  for heuristic functions.
 */
public interface HeuristicFunction<InputType, OutputType> {

    /**
     * Returns an output given the input.
     * @param in The input to the heuristic.
     * @return The calculated output.
     */
    OutputType heuristicOut(final InputType in);
}
