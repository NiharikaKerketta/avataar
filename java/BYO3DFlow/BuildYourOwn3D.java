package BYO3DFlow;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.BYO3DPage;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)
public class BuildYourOwn3D extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage placeOrderPage;
	BYO3DPage byo3dPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	
	public String ProductID;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		placeOrderPage = new PlaceOrderPage();
		byo3dPage = new BYO3DPage();
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
	}
	
	@Test(priority= 1)
	public void Select_Tenant_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //QA store
		commonUtils.click_SingleCheckbox_FirstRow();
	}
	
	@Test(priority = 2 )
	public void getProductID_storeProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductID();
		commonUtils.SearchBar.click();
	//	commonUtils.SearchBar.sendKeys(ProductID); 
		System.out.println("test passed for storing ID");
	}

	@Test(priority = 3 )
	public void verify_PlaceOrder_Test() throws InterruptedException {
		placeOrderPage.getBottomOrderText();
		Thread.sleep(500);
		placeOrderPage.click_Upload3DModels_Btn();
		Thread.sleep(1000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Your order has been Placed");
	}
	
	@Test(priority = 4)
	public void Verify_upload3D_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Upload 3D");
		commonUtils.mousehover_ToPageTitle("Upload 3D");
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		
	}
	
	@Test(priority = 5)
	public void Verify_Assign3DTeam_Test() throws InterruptedException {
		Thread.sleep(1000);
		byo3dPage.click_Assign3DTeamDropdown();
		byo3dPage.select_3DTeam("3d Team Automation");
		commonUtils.clickSumbitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("3D Team assigned Successfully");
	}
	@Test(priority = 6)
	public void Verify_RoleSwitch_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("3D Lead");
		Thread.sleep(1000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Artist Assignment");
		commonUtils.mousehover_ToPageTitle("Artist Assignment");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
		
	@Test(priority = 7)
	public void Verify_ArtistAssignment_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		byo3dPage.click_Atrist3DDropdown();
		byo3dPage.select_3DArtist("products");
		commonUtils.click_SubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("3D Artist got assigned");
	}
	
	@Test(priority = 8)
	public void Verify_3DArtist_Upload_Test() throws InterruptedException {
		byo3dPage.click_3DRoleDropdown();
		commonUtils.select_RoleFromDropdown("3D Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Modelling");
		commonUtils.mousehover_ToPageTitle("Modelling");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority = 9)
	public void Verify_Upload_Glb_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Configurator_Btn();
		orionEnginePage.click_Source_Btn();
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("3");
		javaUtils.glbFileUpload();
		Thread.sleep(3000);
	}
	
	@Test(priority=10)
	public void Verify_UploadInModelling_Test() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(20000);
		orionEnginePage.click_EngineSubmitBtn();
		try {
			commonUtils.click_CloseIcon_OnSuccessDialog();
			} catch (Exception e) {
			System.out.println("didn't click on successDialog - mod artist upload stage");
			}
			Thread.sleep(2000);
			commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority=11)
	public void Verify_ModellingQC_Test() throws InterruptedException {
		byo3dPage.click_3DRoleDropdown();
		commonUtils.select_RoleFromDropdown("3D Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Modelling QC");
		commonUtils.mousehover_ToPageTitle("Modelling QC");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		
		for (int i=1; i<22; i++) {
			commonUtils.SearchBar.sendKeys(ProductID);
			Thread.sleep(2500);
			try {
				commonUtils.click_productnameIfClickable();
				System.out.println("successfully completed autoprocessing");
				break;
			} catch (Exception e) {
				System.out.println("not clicked "+i+" time");
			}
			commonUtils.SearchBar.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			Thread.sleep(3000);
		}
		System.out.println("Product came to modelling QC");
	}
	
	@Test(priority=12)
	public void Verify_ModQC_Feedback_Test() throws InterruptedException {
//		commonUtils.SearchBar.sendKeys(ProductID);
//		Thread.sleep(2000);
//		commonUtils.click_productName_hyperlink();
		Thread.sleep(2000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(5000);
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(1000);
		try {
			commonUtils.click_CloseIcon_OnSuccessDialog();
			} catch (Exception e) {
			System.out.println("didn't click on successDialog - mod artist upload stage");
			}
			Thread.sleep(2000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		System.out.println("Feedback submitted");
	}
	
	@Test(priority=13)
	public void Verify_InApproval_Test() throws InterruptedException {
		Thread.sleep(3000);
		byo3dPage.click_3DRoleDropdown();
		commonUtils.select_RoleFromDropdown("Store Owner");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Review");
		commonUtils.mousehover_ToPageTitle("Review");
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(4000);
	}
	
	@Test(priority=14)
	public void Verify_Review_Feedback_Test() throws InterruptedException {
		byo3dPage.click_ReviwStage_Feedback_Btn();
		Thread.sleep(1000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(3000);
		commonUtils.click_CloseIcon_OnSuccessDialog();			//closing of download popup does not require in customer
		Thread.sleep(1000);
		System.out.println("Customer Feedback submitted");
	}
	
	@Test(priority=15)
	public void Verify_Publish_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Publish");
		Thread.sleep(1000);
		commonUtils.mousehover_ToPageTitle("Publish");
		Thread.sleep(1000);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		commonUtils.click_PublishBtn();
		Thread.sleep(1000);
	//	commonUtils.click_CloseIcon_OnSuccessDialog();
		byo3dPage.click_ClosePopupAtPublishStage();
	//	commonUtils.click_productName_hyperlink();   // Once experience is integrated - need to add back button
	//	Thread.sleep(4000);
	}
	
	@Test(priority=16)
	public void Verify_Live_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Live");
		commonUtils.mousehover_ToPageTitle("Live");
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
	}
	
	@Test(priority = 17)
	public void verify_StoreOwner_SignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("p");
		Thread.sleep(1000);
		commonUtils.click_SignOut_Btn();
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}	
}
