/** The class Pea represents a projectile fired by a Normal Peashooter.
  *
  * @author Darryl Ac-ac & Brian Garcia
  * @version 1.1
  */

public class Pea {

    private int row;
    private int col;
    private boolean active = true; // This pea stays active until leaves the board or hits a Zombie.
    private static final int DAMAGE = 20;

    /**
     * Constructs a pea at the specified row and column.
     * 
     * @param row the row where the pea starts (same as the plant that fired it)
     * @param col the column where the pea starts (typically one tile right of the shooter)
     */
    public Pea(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Moves the pea one tile to the right.
     * - If the pea collides with a zombie, it deals damage and deactivates.
     * - If the pea reaches the end of the board, it also deactivates.
     *
     * @param model the GameModel containing the board state
     */
    public void move(GameModel model) {
        if (!active) return;
        col++; // Move one tile to the right
        // Check if pea went off the board
        if (col >= GameModel.COLS) {
            active = false;
            return;
        }

        // Check if a Zombie has been hit
        Tile tile = model.getTile(row, col);
        Zombie zombie = tile.getZombie();

        if (zombie != null) {
            zombie.takeDamage(DAMAGE);
            active = false;
            System.out.println("Pea hit zombie at (" + row + "," + col + ") for " + DAMAGE + " damage.");
        }
    }

    /**
     * Returns whether this pea is still active.
     * 
     * @return true if the pea is still active; false if it has already hit or expired
     */
    public boolean isActive() {
        return active;
    }

    /** These method checks for the
     * current row and col of the Pea
     * 
     * @return row 
     * @return col 
   */	
    public int getRow() { return row; }
    public int getCol() { return col; }
}

