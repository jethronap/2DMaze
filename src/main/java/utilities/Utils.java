package utilities;

public class Utils {

    /**
     * Method that converts Direction to integer.
     */
    public static int convertDirectionToInt(Direction dir) {
        int dirInt = -1;

        switch (dir) {
            case SOUTH:
                dirInt = 0;
                break;
            case EAST:
                dirInt = 1;
                break;
            case NORTH:
                dirInt = 2;
                break;
            case WEST:
                dirInt = 3;
                break;
        }
        return dirInt;
    }

    /**
     * Method that converts integer to Direction.
     */
    public static Direction convertIntToDirection(int dirInt) {
        switch (dirInt) {
            case 0:
                return Direction.SOUTH;
            case 1:
                return Direction.EAST;
            case 2:
                return Direction.NORTH;
            case 3:
                return Direction.WEST;
            default:
                return Direction.INVALID;

        }
    }

    public static final <T, U> Pair<T, U> makePair(T t, U u) {
        Pair<T, U> p = new Pair();
        p.first = t;
        p.second = u;
        return p;
    }
}
