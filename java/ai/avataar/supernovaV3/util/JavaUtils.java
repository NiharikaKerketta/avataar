package ai.avataar.supernovaV3.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import ai.avataar.supernovaV3.base.TestBase;

public class JavaUtils extends TestBase {

	public StringSelection glbFileUpload() throws AWTException {
		Robot robot = new Robot();
		StringSelection glb_Path = new StringSelection( path +"\\src\\test\\resources\\files\\Recliner.glb");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(glb_Path, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		return glb_Path;
	}
	
	public StringSelection FBX_WT_Upload() throws AWTException {
		Robot robot = new Robot();
		StringSelection fbx_WT_Path = new StringSelection(path +"\\src\\test\\resources\\files\\Recliner_WT.fbx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fbx_WT_Path, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return fbx_WT_Path;
	}
	
	public StringSelection FBX_T_Upload() throws AWTException {
		Robot robot = new Robot();
		StringSelection fbx_T_Path = new StringSelection( path +"\\src\\test\\resources\\files\\Recliner_T.fbx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fbx_T_Path, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return fbx_T_Path;
	}
	
	public StringSelection pngUpload() throws AWTException {
		Robot robot = new Robot();
		StringSelection png_path = new StringSelection( path +"\\src\\test\\resources\\files\\Sofa_SS.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(png_path, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return png_path;
	}
	
	public StringSelection fileUpload_Generic(String fileName) throws AWTException {
		Robot robot = new Robot();
		StringSelection file_path = new StringSelection( path +"\\src\\test\\resources\\files\\"+fileName+"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file_path, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return file_path;
	}
	
	
	
}
