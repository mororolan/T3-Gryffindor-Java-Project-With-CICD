package team3.passpasspass.VM.controller;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;

class MaintainerPanelTest {

	@Test
	void testOpenMaintainerFrame() {
		JFrame maintainerFrame = new MaintainerLoginFrame();
		String maintainerFrameTitle = "Test Maintainer Panel";
		((MaintainerLoginFrame) maintainerFrame).createMaintainerJFrame(maintainerFrameTitle);
	}
	
	@Test
	void testGetTotalCash() {
		MaintainerFileManager mfm = new MaintainerFileManager();
		int totalCash = mfm.getTotalCash("./maintainerData/coins.csv");
		assertEquals(totalCash,5380);
	}
	
	
}
