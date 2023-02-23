package Generic;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.util.ListenersClass;
@Listeners(ListenersClass.class)
public class ProductCountDisplayCheck extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority = 1)
	public void Verify_DemoStoreLogin_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
		commonUtils.mousehover_ToPageTitle("Place Order");
	}
	
	@Test(priority = 2)
	public void Verify_TotalProductCount_Test() throws InterruptedException {
		String count = commonUtils.getTotalProductsCount();
		System.out.println("Total product present: "+count);
		int i=Integer.parseInt(count);
		System.out.println(i);
	}
	
	@Test(priority = 3)
	public void Verify_10ItemsPresentPerPage_Test() throws InterruptedException {
		commonUtils.click_ItemsPerPageBtn();
		Object page = commonUtils.click_PageSelectionPerItem("10");
		System.out.println("10 Items per page selected : " +page);
		Thread.sleep(1500);
		commonUtils.click_allProductCheckbox();
		String countDisplay = commonUtils.getNoOfProductsSelected();
		System.out.println("Number of products selected : "+countDisplay);
		Thread.sleep(1500);
		commonUtils.click_allProductCheckbox();
	}
	
	@Test(priority =4)
	public void Verify_25ItemsPresentPerPage_Test() throws InterruptedException {
		commonUtils.click_ItemsPerPageBtn();
		Object page = commonUtils.click_PageSelectionPerItem("25");
		System.out.println("25 Items per page selected : " +page);
		Thread.sleep(1500);
		commonUtils.click_allProductCheckbox();
		String countDisplay = commonUtils.getNoOfProductsSelected();
		System.out.println("Number of products selected : "+countDisplay);
		Thread.sleep(1500);
		commonUtils.click_allProductCheckbox();
	}
	
	@Test(priority =5)
	public void Verify_50ItemsPresentPerPage_Test() throws InterruptedException {
		commonUtils.click_ItemsPerPageBtn();
		Object page = commonUtils.click_PageSelectionPerItem("50");
		System.out.println("50 Items per page selected : " +page);
		Thread.sleep(2000);
		commonUtils.click_allProductCheckbox();
		String countDisplay = commonUtils.getNoOfProductsSelected();
		System.out.println("Number of products selected : "+countDisplay);
		Thread.sleep(1500);
		commonUtils.click_allProductCheckbox();
	}
	
	@Test(priority =6)
	public void Verify_100ItemsPresentPerPage_Test() throws InterruptedException {
		commonUtils.click_ItemsPerPageBtn();
		Object page = commonUtils.click_PageSelectionPerItem("100");
		System.out.println("100 Items per page selected : " +page);
		Thread.sleep(2000);
		commonUtils.click_allProductCheckbox();
		String countDisplay = commonUtils.getNoOfProductsSelected();
		System.out.println("Number of products selected : "+countDisplay);
		Thread.sleep(3000);
		commonUtils.click_allProductCheckbox();
	}
	
	@Test(priority =7)
	public void Verify_500ItemsPresentPerPage_Test() throws InterruptedException {
		commonUtils.click_ItemsPerPageBtn();
		Object page = commonUtils.click_PageSelectionPerItem("500");
		System.out.println("500 Items per page selected : " +page);
		Thread.sleep(2000);
		commonUtils.click_allProductCheckbox();
		String countDisplay = commonUtils.getNoOfProductsSelected();
		System.out.println("Number of products selected : "+countDisplay);
		String count = commonUtils.getTotalProductsCount();
		System.out.println("Total product present: "+count);
		assertTrue(countDisplay.contains(count),"Total product present is NOT matching with the total product Selected");
		Thread.sleep(2000);
		commonUtils.click_allProductCheckbox();
	}
	
	@Test(priority =8)
	public void Verify_Logout() throws InterruptedException {
		commonUtils.click_Profile("p");
		commonUtils.click_SignOut_Btn();
		System.out.println("Signed out successfully from Store Owner");
	
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
}
