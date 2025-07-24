/** The subclass SnowPeashooter represents a plant
 * that can be planted by the player
  *
  * @author Darryl Ac-ac & Brian Garcia
  * @version 1.1
  */

public class SnowPeashooter extends Peashooter {

    /** This constructor initializes the SnowPeashooter
     * and overrides its base attributes accordingly
     * 
     * @param row row where the plant is placed
     * @param col column where the plant is placed
     */
    public SnowPeashooter(int row, int col) {
        super(row, col);

        // Override default attributes
        this.sunCost = 175;
        this.damage = 20;         // Base damage
        this.directDamage = 30;   // Does more damage if Zombie is within 2 tiles
        this.regenerateRate = 8;
        this.health = 100;
        this.range = GameModel.COLS;
        this.speed = 3;
    }

    /** This method fires a SnowPea projectile.
     * If the zombie is within 3 tiles, it does bonus damage.
     * 
     * @param model the GameModel to apply projectile into
     */
    @Override
    protected void fireProjectile(GameModel model) {
        Zombie target = identifyZombie(model);
        if (target == null) return;

        int distance = target.getCol() - this.col;
        int actualDamage = (distance <= 2) ? directDamage : damage; // Check if Zombie is within 2 tiles away

        model.addPea(new SnowPea(row, col + 1, actualDamage)); // Pass damage
        System.out.println("Snow Peashooter at (" + row + "," + col + ") fired a snow pea for " + actualDamage + " dmg.");
    }

    /** This method returns the sun cost
     * required to place the SnowPeashooter
     * 
     * @return 175 sun cost
     */
    @Override
    public int getCost() {
        return sunCost;
    }
}
