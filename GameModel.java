package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GameModel {
    // Stage grid sizes; Min 3 stages
    public static final int STAGE1_ROWS = 5;
    public static final int STAGE1_COLS = 9;

    public static final int STAGE2_ROWS = 6;
    public static final int STAGE2_COLS = 10;

    public static final int STAGE3_ROWS = 7;
    public static final int STAGE3_COLS = 11;

    public static int curr_ROWS;
    public static int curr_COLS;

    private Tile[][] board;
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private ArrayList<Pea> peas = new ArrayList<>();
    private ArrayList<SnowPea> snowPeas = new ArrayList<>();
    private ArrayList<SunToken> sunTokens = new ArrayList<>();
    private ArrayList<Plant> plants = new ArrayList<>();

    // Plant cooldowns
    private Map<String, Integer> plantCooldowns = new HashMap<>();

    private int sunCount = 50;
    private int uncollectedSun = 0;
    private int lastSunGeneratedTime = -99; // Initiated by function later on
    private int timeRemaining = 180;
    private int currentTime = 0; // Needed for cooldown logic
    private boolean gameOver = false;

    // Set board size based on stage number; Initialize cooldowns
    public GameModel(int stage) {
        switch (stage) {
            case 1:
                curr_ROWS = STAGE1_ROWS;
                curr_COLS = STAGE1_COLS;
                break;
            case 2:
                curr_ROWS = STAGE2_ROWS;
                curr_COLS = STAGE2_COLS;
                break;
            case 3:
                curr_ROWS = STAGE3_ROWS;
                curr_COLS = STAGE3_COLS;
                break;
            default:
                throw new IllegalArgumentException("Invalid stage number: " + stage);
        }

        board = new Tile[curr_ROWS][curr_COLS];
        plantCooldowns.put("Sunflower", 0);
        plantCooldowns.put("Peashooter", 0);
        plantCooldowns.put("SnowPea", 0);
        plantCooldowns.put("Cherrybomb", 0);
        plantCooldowns.put("Wallnut", 0);
        plantCooldowns.put("Potatomine", 0);
    }

    /*  Game Timer logic 
    // TO BE TRANSFERRED TO THE CONTROLLER
    public void startTimerThread() {
        Thread gameTimer = new Thread(() -> {
            while (timeRemaining > 0 && !gameOver) {
                try {
                    Thread.sleep(1000); // wait 1 second
                    timeRemaining--;
                    currentTime++; // Needed for plant cd
                    System.out.println("Time left: " + timeRemaining + "s");
                } catch (InterruptedException e) {
                    System.out.println("[ERROR]: Game Timer Thread interrupted.");
                }
            }
            if (!gameOver) {
                System.out.println("Time is up!");
                gameOver = true;
            }
        });

    gameTimer.start(); 
}
*/

    /* Collecting Sun */
    public void collectSunAt(int mouseX, int mouseY) {
        for (int i = 0; i < sunTokens.size(); i++) {
            SunToken token = sunTokens.get(i);
            if (!token.isCollected() && token.getBounds().contains(mouseX, mouseY)) {
                sunCount += token.getValue();
                token.collect();
                sunTokens.remove(i); // remove from list after collection
                break;
            }
        }
    }


    /* Generated sun on the board */
    public void generateSun() {
        if (timeRemaining % 5 == 0 && timeRemaining != lastSunGeneratedTime) {
            int x = (int)(Math.random() * 600); // Random horizontal position
            int y = (int)(Math.random() * 400); // Random vertical position
            sunTokens.add(new SunToken(x, y));
            lastSunGeneratedTime = timeRemaining; // Use as reference 
        }
    }

    /* Check if a plant exists on a tile */
    public Plant getPlantAt(int row, int col) {
        for (Plant p : plants) {
            if (p.getRow() == row && p.getCol() == col) {
                return p;
            }
        }
        return null;
    }

    /* Check if plant can be placed */
    public boolean canPlacePlant(String type, int currentTime) {
        int nextAvailable = plantCooldowns.getOrDefault(type, 0); // Check map created for curr cd
        return currentTime >= nextAvailable;
    }

    /* Placing plant logic */
    public boolean placePlant(Plant p, int row, int col, int currentTime) {
        String type = p.getClass().getSimpleName(); // e.g., "Sunflower", "Peashooter"

        // Tile occupied
        if (getPlantAt(row, col) != null) return false;

        // Not enough sun
        if (sunCount < p.getCost()) return false;

        // Cooldown not yet expired
        int nextAvailable = plantCooldowns.getOrDefault(type, 0);
        if (currentTime < nextAvailable) {
            System.out.println(type + " is cooling down! Wait " + (nextAvailable - currentTime) + " more ticks.");
            return false;
        }

        // Place plant
        p.setRow(row);
        p.setCol(col);
        plants.add(p);
        sunCount -= p.getCost();

        // Use the plant's internal cooldown value
        plantCooldowns.put(type, currentTime + p.getCooldown());

        System.out.println(type + " planted at (" + row + "," + col + "). Cooldown: " + p.getCooldown() + " ticks.");
        return true;
    }


    /* Remove Plant */
    public void removePlant(int row, int col) {
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            if (p.getRow() == row && p.getCol() == col) {
                plants.remove(i);
                System.out.println("Plant removed at [" + row + ", " + col + "]");
                
                // Clear plant from tile
                if (board[row][col] != null) {
                    board[row][col].setPlant(null);
                }
                return;
            }
        }

        System.out.println("No plant found at [" + row + ", " + col + "] to remove.");
    }

    /* Run plants through their actions every tick */
    /* TICK METHOD */
    public void updatePlants() {
        for (Plant p : plants) {
            p.performAction(this); // Each plant handles its own behavior
        }
    }


    /* Zombie Spawn Logic */
    /* TICK METHOD */
    public int handleZombieSpawning(int time, Zombie[] zombies, int zombieCount) {
        // Huge wave warning
        if (time == 171) {
            System.out.println("A huge wave of zombies is approaching!\n");
        }

        // Spawn FlagZombie exactly at 170s to signal the wave
        if (time == 170 && zombieCount < zombies.length) {
            int lane = 1 + (int)(Math.random() * curr_ROWS); // Lanes 1–5
            int tile = curr_COLS;                            // Rightmost column
            zombies[zombieCount++] = new FlagZombie(lane, tile);
            System.out.println("Flag Zombie has appeared! [Lane: " + lane + ", Tile: " + tile + "]\n");
            return zombieCount; // Skip regular spawn that tick
        }

        // Determine if we should spawn a zombie this tick
        boolean spawnZombie = false;
        if ((time >= 30 && time <= 80 && time % 10 == 0) ||    // 30–80s: every 10s
            (time >= 81 && time <= 140 && time % 5 == 0) ||    // 81–140s: every 5s
            (time >= 141 && time <= 170 && time % 3 == 0) ||   // 141–170s: every 3s
            (time >= 171 && time <= 180)) {                    // 171–180s: every tick
            spawnZombie = true;
        }

        // Spawn logic
        if (spawnZombie && zombieCount < zombies.length) {
            int lane = (int)(Math.random() * curr_ROWS); // Lanes 0–(curr_ROWS-1)
            int tile = curr_COLS - 1;

            Zombie newZombie = getZombieForTime(time, lane, tile);
            zombies[zombieCount++] = newZombie;

            System.out.println(newZombie.getType() + " has appeared! [Lane: " + lane + ", Tile: " + tile + "]\n");
        }

        return zombieCount; // For Zombie indexing
    }

    // Helper method to choose zombie type based on current time
    private Zombie getZombieForTime(int time, int lane, int tile) {
        if (time < 141) {
            return new NormalZombie(lane, tile);
        }

        double r = Math.random();
        if (r < 0.6) {
            return new NormalZombie(lane, tile);
        } else if (r < 0.9) {
            return new ConeheadZombie(lane, tile);
        } else {
            return new BucketheadZombie(lane, tile);
        }
    }


    /* Run zombies through their action for every second/tick */
    /* TICK METHOD */
    public void updateZombies() {
        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);

            // Skip dead zombies
            if (z.isDead()) {
                zombies.remove(i);
                i--;
                continue;
            }

            z.performAction(this); //Let each zombie handle their behavior
        }
    }

    /* Check Game Over cons; No time / Zombie wins */
    /* TICK METHOD */
    public void checkGameOver() {

        for (Zombie z : zombies) {
            if (z.getCol() <= 0 && !z.isDead()) {
                gameOver = true;
                System.out.println("A zombie reached the house. Game Over!");
                return;
            }
        }

        if (zombies.isEmpty() && timeRemaining <= 0) { 
            gameOver = true;
            System.out.println("All zombies defeated! You win!");
        }
    }

    /* PROJECTILE METHODS */

    /* Handles Projctile Movement */
    /* TICK METHOD */
    public void moveProjectiles() {
        for (int i = 0; i < peas.size(); i++) {
            Pea pea = peas.get(i);
            pea.move(this); // This calls your existing move logic

            if (!pea.isActive()) {
                peas.remove(i);
                i--;
            }
        }

        // Repeat same logic for SnowPea if it has similar structure
        for (int i = 0; i < snowPeas.size(); i++) {
            SnowPea snowPea = snowPeas.get(i);
            snowPea.move(this); // assumes SnowPea has move(GameModel) method too

            if (!snowPea.isActive()) {
                snowPeas.remove(i);
                i--;
            }
        }
    }

public void gameTick() {
    if (gameOver) return;

    System.out.println("Time left: " + timeRemaining + "s");

    // Generate passive sun
    generateSun();

    // Let each plant perform its action
    updatePlants();

    // Move projectiles and handle collisions
    moveProjectiles();

    // Handle zombie spawning and updating
    zombieCount = handleZombieSpawning(timeRemaining, zombieArray, zombieCount);

    // Let zombies perform their actions 
    updateZombies(); 

    // Check for game over condition (either win or lose)
    checkGameOver();

}

}
