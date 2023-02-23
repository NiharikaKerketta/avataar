package ai.avataar.supernovaV3.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	public static WebDriver driver = null;
	public static WebDriver sdriver = null;
	public static Properties prop;
	public static String path;
	public static String currentDir;
	
	public TestBase() {
		
		path = System.getProperty("user.dir");
		
		try {
			prop = new Properties();
			
	//		System.out.println("user directory is : " + path); //commenting as this is used only when path need to be checked
			
			FileInputStream ip = new FileInputStream(path + "/src/test/resources/config.properties");
			
			prop.load(ip);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		/*
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--headless");
		*/
		
		if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver" );//from jenkins
			System.setProperty("webdriver.chrome.driver", path +"/src/test/resources//chromedriver.exe" );//project path
			driver = new ChromeDriver();
		}
		
		else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", path +"/src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("edge")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("no browser value is given");
		}
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT);
		*/
		driver.get(prop.getProperty("url"));
	}
	
	public void failed(final String testMethodName) {
        final File path = (File)((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);
        
        try {
            FileUtils.copyFile(path, new File("./ScreenShot/" + testMethodName + "_" + ".png"));
        }
        catch (IOException e) {
        	System.out.println("capture failed"+e.getMessage());
        }
    }
}
