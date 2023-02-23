package ai.avataar.supernovaV3.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FileUtility {
	
	public static String path1;
	
	
	public String getPropertyKeyValue(String key) throws IOException {
	path1 = System.getProperty("user.dir");
	
	FileInputStream f = new FileInputStream(path1 +"/src/test/resources/config.properties");
	Properties p = new Properties();
	p.load(f);
	String value = p.getProperty(key);
	return value;
	}

public static String getScreenShot(WebDriver driver) {
	
	TakesScreenshot ts = (TakesScreenshot)driver;
	
	File src = ts.getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis();

	File destination= new File(path);

	try{
	FileUtils.copyFile(src, destination);
	} catch (IOException e)
	{
	System.out.println("capture failed"+e.getMessage());
	}
	 return path;
	}

	public StringSelection glbFileUpload() throws AWTException {
	Robot robot = new Robot();
	StringSelection stringSelection = new StringSelection("C:\\Users\\Avataar\\eclipse-workspace\\supernova\\src\\Recliner.glb");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	robot.setAutoDelay(1000);
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyRelease(KeyEvent.VK_V);
	
	robot.setAutoDelay(1000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	return stringSelection;
	
}
	
	public StringSelection fbxUpload() throws AWTException {
		Robot robot = new Robot();
		StringSelection stringSelection = new StringSelection("C:\\Users\\Avata\\Downloads\\Upload\\ReclinerFbx.fbx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return stringSelection;
		
	}


}
