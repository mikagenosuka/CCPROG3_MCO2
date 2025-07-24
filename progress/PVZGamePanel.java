import java.awt.*;
import javax.swing.*;

class PVZGamePanel extends JPanel {
    public PVZGamePanel () {
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        setBackground(new Color(153, 204, 102));
        //setBackground(new Color.decode("#245d1f"));
    }

    ImageIcon zomb = new ImageIcon("sprites/normzomb.png"); // should be in the classes instead
	Image image = zomb.getImage().getScaledInstance(80, 128, Image.SCALE_SMOOTH);
	//zomb = new ImageIcon(image);

    ImageIcon peapea = new ImageIcon("sprites/peapea.png");
	Image image2 = peapea.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw grid
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                g.setColor(Color.BLACK);
                g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                // Draw plant/zombie if exists (placeholder)
                //g.drawImage(image, 128 + col * 256, 128 + row * 256, this);
               // g.drawImage(image2, 128 + col * 256, 256 * row, this); //should be based on
                                                                        // tile size
                
            }
		}

        for (int row = 0; row < ROWS; row++) { // plants?
            for (int col = 0; col < COLS; col++) {
                // Draw plant/zombie if exists (placeholder)
                //g.drawImage(image, 128 + col * 256, 128 + row * 256, this);
                g.drawImage(image2, 0 + col * 128, 40 + 128 * row, this); //should be based on
                                                                        // tile size
                g.drawImage(image, 48 + col * 128, 0 + row * 128, this);
            }
		}

       /* for (int row = 0; row < ROWS; row++) { //zombies?
            for (int col = 0; col < COLS; col++) {
                g.drawImage(image, 0 + col * 128, 64 + row * 128, this);
                //g.drawImage(image2, 128 + col * 256, 256 * row, this); //should be based on
                                                             // tile size & the actual instantiated
                                                            // zombies/plants  
            }
		}*/

       //  g.drawImage(image2, 0, 64, this);
       // g.drawImage(image, 64, 0, this);
	}

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int TILE_SIZE = 128;
}