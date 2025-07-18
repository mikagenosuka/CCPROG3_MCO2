/** The class SnowPea represents a projectile fired by a Snow Peashooter.
  *
  * @author Darryl Ac-ac & Brian Garcia
  * @version 1.2
  */

public class SnowPea {
    private int row;
    private int col;
    private boolean active = true; // Pea stays active until it hits or leaves board
    private int damage;            // Variable damage based on range

    /**
     * This constructor initializes the SnowPea
     * with a custom damage value passed in by the SnowPeashooter.
     * 
     * @param row the row of the SnowPea
     * @param col the column where it starts (1 tile to the right of shooter)
     * @param damage the amount of damage this SnowPea will deal
     */
    public SnowPea(int row, int col, int damage) {
        this.row = row;
        this.col = col;
        this.damage = damage;
    }

    /** This method moves the SnowPea one tile to the right
     * and checks for collision with a zombie. If hit, it
     * deals damage and slows the zombie, then deactivates.
     * 
     * @param model the GameModel that contains the board
     */
    public void move(GameModel model) {
        if (!active) return;

        col++; // Move one tile right

        if (col >= GameModel.COLS) {
            active = false;
            return;
        }

        Tile tile = model.getTile(row, col);
        Zombie zombie = tile.getZombie();

        if (zombie != null) {
            zombie.takeDamage(damage);
            zombie.applySlow(); // Slows the zombie
            active = false;
            System.out.println("SnowPea hit zombie at (" + row + "," + col + ") for " + damage + " damage.");
        }
    }

    /** This method checks if the SnowPea is still active
     * (not expired or hit)
     * 
     * @return TRUE if still moving; FALSE otherwise
     */
    public boolean isActive() {
        return active;
    }

    /** These methods return the current
     * row and column of the SnowPea
     * 
     * @return row position
     * @return col position
     */
    public int getRow() { return row; }
    public int getCol() { return col; }
}
