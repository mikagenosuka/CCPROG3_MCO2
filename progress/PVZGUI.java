import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PVZGUI extends JFrame {
	public PVZGUI () {
		super("Plants vs Zombies");
		setLayout(new BorderLayout());
		
		setSize(1280, 960);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	private void init() {
		
/*		GridBagConstraints gbc = new GridBagConstraints();
		
		// NORTH PANEL (Sun amount and Plant select)
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridBagLayout());
		panelNorth.setBackground(Color.decode("#0A285F"));
		
		JLabel sunAmount = new JLabel("SUN");
		sunAmount.setForeground(Color.WHITE);
		sunAmount.setFont(new Font("Verdana", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = gbc.WEST;
		panelNorth.add(sunAmount, gbc);
		
		JButton sunflower = new JButton("Sunflower");
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelNorth.add(sunflower, gbc);
		
		JButton normalPea = new JButton("Peashooter");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelNorth.add(normalPea, gbc);
		
		JButton snowPea = new JButton("Snow Pea");
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelNorth.add(snowPea, gbc);
		
		JButton wallNut = new JButton("Wall-nut");
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelNorth.add(wallNut, gbc);
		
		JButton cherryBomb = new JButton("Cherry Bomb");
		gbc.gridx = 5;
		gbc.gridy = 0;
		panelNorth.add(cherryBomb, gbc);
		
		JButton potatoMine = new JButton("Cherry Bomb");
		gbc.gridx = 6;
		gbc.gridy = 0;
		panelNorth.add(potatoMine, gbc);
		
		
		JButton shovel = new JButton("Shovel");
		gbc.gridx = 10;
		gbc.gridy = 0;
		panelNorth.add(shovel, gbc);
		
		this.add(panelNorth, BorderLayout.NORTH); */
		
		ImageIcon back = new ImageIcon("sprites/PotatoMine.png");
		Image image = back.getImage().getScaledInstance(60, 40, Image.SCALE_SMOOTH);
		back = new ImageIcon(image);
		
		ImageIcon peapea = new ImageIcon("sprites/peapea.png");
		image = peapea.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		peapea = new ImageIcon(image);
		
		ImageIcon zomb = new ImageIcon("sprites/zomb.png");
		image = zomb.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
		zomb = new ImageIcon(image);
		
		//game board
		gamePanel = new PVZGamePanel();
        this.add(gamePanel, BorderLayout.CENTER);


		// NORTH PANEL (Sun amount and Plant select)
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		panelNorth.setBackground(Color.decode("#0A285F"));
		//panelNorth.setPreferredSize(new Dimension(864, 70));
		
		GridBagConstraints gbc = new GridBagConstraints();

		// sunpanel (sunIcon and sunAmount)
		JPanel sunPanel = new JPanel();
		sunPanel.setLayout(new GridBagLayout());
		//sunPanel.setLayout(new GridLayout(2, 1, 0, 0));
		//sunPanel.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		sunPanel.setBackground(Color.decode("#f6b32e"));
		
		JLabel testIMG = new JLabel(back);
		//testIMG.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		sunPanel.add(testIMG, gbc);
		
		JLabel sunAmount = new JLabel("SUN");
		sunAmount.setForeground(Color.WHITE);
		sunAmount.setFont(new Font("Verdana", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 1;
		sunPanel.add(sunAmount, gbc);

		// control (plant select)
		JPanel control = new JPanel();
		control.setLayout(new GridLayout(1, 10, 0, 0));
		//control.setLayout(new FlowLayout());
		control.setBackground(Color.decode("#f6b32e"));
		
		control.add(sunPanel);

		sunflower = new JButton("Sunflower");
		
		control.add(sunflower);
		
		normalPea = new JButton("Peashooter");
		normalPea = new JButton(peapea);
		control.add(normalPea);
		
		snowPea = new JButton("Snow Pea");
		//snowPea.setPreferredSize(new Dimension(10, 40));
		control.add(snowPea);
		
		wallNut = new JButton("Wall-nut");
		control.add(wallNut);
		
		cherryBomb = new JButton("Cherry Bomb");
		control.add(cherryBomb);
		
		potatoMine = new JButton("potatoMine");
		control.add(potatoMine);
		
		
		shovel = new JButton("Shovel");
		control.add(shovel);
		
		panelNorth.add(control, BorderLayout.WEST);
		this.add(panelNorth, BorderLayout.NORTH);
	
		//GridBagConstraints gbc = new GridBagConstraints();
	
		// WEST PANEL
/*		JPanel panelWest = new JPanel();
		panelWest.setLayout(new GridBagLayout());
		panelWest.setBackground(Color.decode("#DFDFDF"));
		
		for (int i = 0; i < 5; i++) {
			JButton btnNext = new JButton(">");
			gbc.gridx = 0;
			gbc.gridy = i * 4;
			gbc.gridwidth = 1;
			//gbc.gridheight = 5;
			gbc.fill = GridBagConstraints.VERTICAL;
			//gbc.fill = GridBagConstraints.HORIZONTAL;
			
			panelWest.add(btnNext);
		}
		
		this.add(panelWest, BorderLayout.WEST);*/
		
		// EAST PANEL
/*		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridBagLayout());
		panelEast.setBackground(Color.decode("#DFDFDF")); */
		
		/*for (int i = 0; i < 5; i++) {
			JButton btnNext = new JButton(">");
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.gridheight = 4;
			gbc.fill = GridBagConstraints.VERTICAL;
			
			panelEast.add(btnNext);
		}*/
		
		/*JButton btnNext = new JButton(">");
		panelEast.add(btnNext);
		
		this.add(panelEast, BorderLayout.EAST); */
		
		// CENTER PANEL (buncha buttons)
	//	JPanel panelCenter = new JPanel();
	//	panelCenter.setLayout(new GridLayout(5, 9, 0, 0)); // row, col, spacing, spacing
	//	panelCenter.setBackground(Color.decode("#245d1f"));
		
		/*for (int i = 0; i < 5; i++){
			for (int j = 0; j < 10; j++) {
				tile = new JButton("Tile " + (i + 1) + ", " + j, zomb);
				tile.setOpaque(false);
				tile.setContentAreaFilled(false);
				tile.setBorderPainted(false);
				
				panelCenter.add(tile);
			}
		}*/
		
		//Arraylist<Tile> tiles = new ArrayList<>();
		 
	/* 	for (int i = 0; i < 5; i++){
			for (int j = 0; j < 9; j++) {
				tile = new JButton("Tile " + (i + 1) + ", " + j, zomb);
				tile.setOpaque(false);
				tile.setContentAreaFilled(false);
				tile.setBorderPainted(false);
				
				panelCenter.add(tile);
			}
		}*/
		
		//tile.setIconImage(zomb.getImage());
		//panelCenter.add(tile);
		
		//this.add(panelCenter, BorderLayout.CENTER); 	
	//	gamePanel.add(panelCenter); 	


		// SOUTH PANEL (Wave Indicator)
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		panelSouth.setBackground(Color.decode("#245d1f"));
		
		timeTime = new JLabel("??? SECS LEFT ");
		timeTime.setForeground(Color.WHITE);
		timeTime.setFont(new Font("Bell MT", Font.BOLD, 20));
		panelSouth.add(timeTime);
		
		waveTime = new JLabel("WAVE IN:");
		waveTime.setForeground(Color.WHITE);
		waveTime.setFont(new Font("Bell MT", Font.BOLD, 20));
		panelSouth.add(waveTime);

		this.add(panelSouth, BorderLayout.SOUTH);
	}
		
	public void setActionListener(ActionListener listener) {
		sunflower.addActionListener(listener);	
		normalPea.addActionListener(listener);
		snowPea.addActionListener(listener);
		wallNut.addActionListener(listener);
		cherryBomb.addActionListener(listener);
		potatoMine.addActionListener(listener);
		shovel.addActionListener(listener);
	}

	public void setWaveTime(int timeidk) {
		waveTime.setText("WAVE IN: " + timeidk);
	}

	/*public void setSunAmount(int sunAmount) {
		this.sunAmount = sunAmount;
	}*/

	private PVZGamePanel gamePanel;

	private JLabel waveTime;
	private JLabel timeTime;

	private JButton sunflower;
	private JButton normalPea;
	private JButton snowPea;
	private JButton wallNut;
	private JButton cherryBomb;
	private JButton potatoMine;
	private JButton shovel;

	private JButton tile;
	
	private Thread gameThread;
}