import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVZController implements ActionListener {
	
	
	public PVZController(PVZGUI gui) {
		this.gui = gui;

		updateView();

		gui.setActionListener(this);
	}

	public void updateView() {
		


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

	private PVZGUI gui;  // View
						// Model
}