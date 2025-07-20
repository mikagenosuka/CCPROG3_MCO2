import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PVZGUI extends JFrame {
	public PVZGUI () {
		super("Plants vs Zombies");
		setLayout(new BorderLayout());
		
		setSize(1280, 720);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		
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
		
		ImageIcon back = new ImageIcon("sprites/back.png");
		Image image = back.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		back = new ImageIcon(image);
		
		ImageIcon peapea = new ImageIcon("sprites/peap.png");
		image = peapea.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		peapea = new ImageIcon(image);
		
		// NORTH PANEL (Sun amount and Plant select)
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		panelNorth.setBackground(Color.decode("#0A285F"));
		panelNorth.setPreferredSize(new Dimension(1280, 70));
		
		JPanel control = new JPanel();
		control.setLayout(new GridLayout(1, 10, 0, 0));
		control.setBackground(Color.decode("#f6b32e"));
		
		JLabel testIMG = new JLabel(back);
		//testIMG.setForeground(Color.WHITE);
		control.add(testIMG);
		
		JLabel sunAmount = new JLabel("SUN");
		sunAmount.setForeground(Color.WHITE);
		sunAmount.setFont(new Font("Verdana", Font.BOLD, 20));
		control.add(sunAmount);
		
		sunflower = new JButton("Sunflower");
		
		control.add(sunflower);
		
		JButton normalPea = new JButton("Peashooter");
		normalPea = new JButton(peapea);
		control.add(normalPea);
		
		JButton snowPea = new JButton("Snow Pea");
		control.add(snowPea);
		
		JButton wallNut = new JButton("Wall-nut");
		control.add(wallNut);
		
		JButton cherryBomb = new JButton("Cherry Bomb");
		control.add(cherryBomb);
		
		JButton potatoMine = new JButton("Cherry Bomb");
		control.add(potatoMine);
		
		
		JButton shovel = new JButton("Shovel");
		control.add(shovel);
		
		panelNorth.add(control, BorderLayout.WEST);
		this.add(panelNorth, BorderLayout.NORTH);
	
		GridBagConstraints gbc = new GridBagConstraints();
	
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
		
		// CENTER PANEL
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(5, 10, 0, 0)); // row, col, spacing, spacing
		panelCenter.setBackground(Color.decode("#245d1f"));
		
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 10; j++) {
				tile = new JButton("Tile " + (i + 1) + ", " + j);
				tile.setOpaque(false);
				tile.setContentAreaFilled(false);
				//tile.setBorderPainted(false);
				panelCenter.add(tile);
			}
		}
		
		this.add(panelCenter, BorderLayout.CENTER);
	
		// SOUTH PANEL (Wave Indicator)
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		panelSouth.setBackground(Color.decode("#245d1f"));
		
		waveTime = new JLabel("WAVE IN:");
		waveTime.setForeground(Color.WHITE);
		waveTime.setFont(new Font("Bell MT", Font.BOLD, 20));
		panelSouth.add(waveTime);
		
		this.add(panelSouth, BorderLayout.SOUTH);
	}
		
	public void setActionListener(ActionListener listener) {
		sunflower.addActionListener(listener);
	}
	
	public void setWaveTime(int timeidk) {
		waveTime.setText("WAVE IN: " + timeidk);
	}
	
	
	private JLabel waveTime;
	private JButton sunflower;
	private JButton tile;
}