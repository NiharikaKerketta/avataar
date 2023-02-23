package HelpAndSupportFlow;

import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.DemoStorePage;
import ai.avataar.supernovaV3.pages.HelpAndSupportGuidePage;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.util.ListenersClass;
@Listeners(ListenersClass.class)
public class Doc_Community_ContactUs extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	HelpAndSupportGuidePage helpAndSupportGuidePage;
	DemoStorePage demoStorePage;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		helpAndSupportGuidePage = new HelpAndSupportGuidePage();
		demoStorePage = new DemoStorePage();
	}
	
	@Test(priority = 1)
	public void verify_Documentation_Page() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); // can change tenant from config while running on different tenants
		commonUtils.mousehover_ToPageTitle("Place Order");
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Help & Support");
		helpAndSupportGuidePage.choose_OptionFrom_HelpAndSupport("Documentation");
		
	    String MainWindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + MainWindow);
	    
	    Set<String> windows = driver.getWindowHandles();
	    Iterator<String> it = windows.iterator();
	    
	    while (it.hasNext()){
	    	String window = it.next();
	    	driver.switchTo().window(window);
	    	Thread.sleep(2000);
	    }
	    String DocPageURL = driver.getCurrentUrl();
	    Assert.assertEquals("https://support.avataar.ai/support/home", DocPageURL);
	    driver.switchTo().window(MainWindow);	
	}
	
	
	@Test(priority = 2)
	public void verify_Community_Page() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order");
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Help & Support");
		helpAndSupportGuidePage.choose_OptionFrom_HelpAndSupport("Community");
		
	    String MainWindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + MainWindow);
	    
	    Set<String> windows = driver.getWindowHandles();
	    Iterator<String> it = windows.iterator();
	    
	    while (it.hasNext()){
	    	String window = it.next();
	    	driver.switchTo().window(window);
	    	Thread.sleep(3000);
	    }	    
	    String CommunityPageURL = driver.getCurrentUrl();
	    Assert.assertEquals("https://community.avataar.ai/", CommunityPageURL);
	    driver.switchTo().window(MainWindow);
	}
	
	@Test(priority = 3)
	public void verify_ContactUs_Page() throws InterruptedException {
		driver.navigate().refresh();
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order");
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Help & Support");
		Thread.sleep(3000);
		helpAndSupportGuidePage.choose_OptionFrom_HelpAndSupport("Contact Us");
		
	    String MainWindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + MainWindow);
	    
	    Set<String> windows = driver.getWindowHandles();
	    Iterator<String> it = windows.iterator();
	    
	    while (it.hasNext()){
	    	String window = it.next();
	    	driver.switchTo().window(window);
	    	Thread.sleep(3000);
	    }	    
	    String ContactUsPageURL = driver.getCurrentUrl();
	    Assert.assertEquals("https://support.avataar.ai/support/tickets/new", ContactUsPageURL);
	    driver.switchTo().window(MainWindow);
	}
	
	@Test(priority = 4)
	public void Verify_Help_Button() throws InterruptedException {
		driver.navigate().refresh();
		helpAndSupportGuidePage.click_Help_Btn();
		Thread.sleep(3000);
	    String MainWindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + MainWindow);
	    
	    Set<String> windows = driver.getWindowHandles();
	    Iterator<String> it = windows.iterator();
	    
	    while (it.hasNext()){
	    	String window = it.next();
	    	driver.switchTo().window(window);
	    	Thread.sleep(2000);
	    }
	    String HelpBtnURL = driver.getCurrentUrl();
	    Assert.assertEquals("https://support.avataar.ai/support/home", HelpBtnURL);
	    driver.switchTo().window(MainWindow);
	}
	
	@AfterTest
	public void tear_Down() {
		driver.quit();
	}
}
