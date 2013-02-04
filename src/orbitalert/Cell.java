package orbitalert;

/**
 *
 * @author Aaron
 */
public class Cell {
    private int x;
    private int y;
    private int z;

    public Cell(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
    }

    public int getX() {
            return x;
    }
    public int getY() {
            return y;
    }
    public int getZ() {
            return z;
    }
    public String getSummary(){
        String x = Integer.toString(getX());
        String y = Integer.toString(getY());
        String z = Integer.toString(getZ());
        String output = "( " + x + ", " + y + ", " + z + " )";
        return output;
    }
}
