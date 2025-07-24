import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVZController implements ActionListener {
	
	// with gamemodel
	/*public PVZController(PVZGUI gui, GameModel model) {
		this.gui = gui;
		this.model = model;

		updateView();

		gui.setActionListener(this);
	} */

	public PVZController(PVZGUI gui) {
		this.gui = gui;

		updateView();

		gui.setActionListener(this);
	}

	public void updateView() {
		//repaint();
		//where updating of values would be visible in gui
		//calling of set sunamount time etc

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Sunflower")) {
			System.out.println("AAAAAA");
		}
		// else if (e.getActionCommand().getClass().getSimpleName() == "Tile") {
		// 	System.out.println("EEEEEE");
			
		// }
		
 
	}

	
	private PVZGUI gui;  		// View
	//private GameModel model		// Model
}