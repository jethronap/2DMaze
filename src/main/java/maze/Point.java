package maze;


/**
 * Class that holds index info of a point.
 */
public class Point {

    public int i = -1;
    public int j = -1;


    /**
     * This construct creates an invalid Point.
     * We want to force the user to create his own
     * valid points.
     */
    public Point() {
        this(-1, 1);
    }

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }


    @Override
    public final boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        else {
            return this.i == ((Point) obj).i && this.j == ((Point) obj).j;
        }
    }


    @Override
    public int hashCode() {
        return i + j;
    }


    /**
     * Returns a String representation of a Point.
     */
    @Override
    public String toString() {
        return Integer.toString(this.i) + Integer.toString(this.j);
    }


    /**
     * Returns true of the Point has valid coordinates.
     */
    public final boolean isValid() {
        return this.i >= 0 && this.j >= 0;
    }
}
