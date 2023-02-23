package Rejection;

import java.awt.AWTException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.Customer_Manager_Rejection_Page;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.pages.ResourceReviewPage;
import ai.avataar.supernovaV3.pages.ThreeD_TeamAssignmentPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)
public class Manager_Rejection extends TestBase{
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	Customer_Manager_Rejection_Page customer_manager_rejection;
	Actions act;
	public String ProductID;
	
	public Manager_Rejection() {
		super();
	}
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		placeOrderPage = new PlaceOrderPage1();
		resourceReviewPage = new ResourceReviewPage();
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
		threeD_TeamAssignmentPage = new ThreeD_TeamAssignmentPage();
		customer_manager_rejection = new Customer_Manager_Rejection_Page();
		act = new Actions(driver);
	}
	
	@Test(priority= 1)
	public void verify_TenantSelection_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name"));
	}
	
	@Test(priority = 2)
	public void verify_SaveProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductID();
		System.out.println("Product ID choosen to work on is: "+ProductID);
	}
	
	@Test(priority = 3)
	public void verify_PlaceOrderFromAvataar_Test() throws InterruptedException {
		commonUtils.click_SingleCheckbox_FirstRow();
		placeOrderPage.getBottomOrderText();
		placeOrderPage.click_OrderFromAvataar_Btn();
		commonUtils.click_Closeicon_successPopup_placeOrderStage();
		System.out.println("Place order with avataar is successfull");
	}
	
	@Test(priority = 4)
	public void verify_StoreOwnerSignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("p");
		commonUtils.click_SignOut_Btn();
		driver.navigate().refresh();
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@Test(priority = 5)
	public void verify_ManagerSignInFromAvataar_Test() throws InterruptedException{
		loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority = 6)
	public void verify_StageSwitchToResourceReview_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Resource Review");
		commonUtils.mousehover_ToPageTitle("Resource Review");
	}
	
	@Test(priority = 7)
	public void verify_ResourceReview_ReviewTable_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		System.out.println("product ID entered in resource review search is "+ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		resourceReviewPage.click_RejectIcon();
		Thread.sleep(1000);
		customer_manager_rejection.select_Rejection_Reason("Insufficient reference images");
		Thread.sleep(1000);
		customer_manager_rejection.click_Add_Rejection_comment_area();
		customer_manager_rejection.add_Rejection_comment_text();
		customer_manager_rejection.click_Rejection_popup_save_Btn();
		Thread.sleep(1000);
		commonUtils.click_SubmitBtn();
		Thread.sleep(1000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
	}
	
	@Test(priority = 8)
	public void verify_ManagerSignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("3");
		commonUtils.click_SignOut_Btn();
		driver.navigate().refresh();
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@Test(priority = 9)
	public void verify_CustomerSignInFromAvataar_Test() throws InterruptedException{
		loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); // added now
		commonUtils.mousehover_ToPageTitle("Place Order"); // added now
	}
	
	@Test(priority = 10)
	public void verify_StageSwitchToAttentionNeeded_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Attention Needed!");
		commonUtils.mousehover_ToPageTitle("Attention Needed!");
	}
	
	@Test(priority = 11)
	public void check_product_In_Attention_Needed() throws InterruptedException {
		System.out.println("product id sending in Attention needed is :"+ ProductID);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		}
	@Test(priority = 12)
	public void Add_comments_By_Customer() throws InterruptedException {
		customer_manager_rejection.click_Add_Req_Btn();
		Thread.sleep(1000);
		customer_manager_rejection.SwitchTo_Add_Additional_Req_Options(2); // Here Comments Option has been selected
		customer_manager_rejection.click_Additional_Req_Comment_Box();
		Thread.sleep(1000);
		customer_manager_rejection.add_customer_comments();
		customer_manager_rejection.click_Add_Additional_Req_Save_Btn();
		System.out.println("Customer Comments have been added..!!");
	}
	
	@Test(priority = 13)
	public void Add_Files_By_Customer() throws AWTException {
		customer_manager_rejection.SwitchTo_Add_Additional_Req_Options(3); //Here Files option has been selected
		customer_manager_rejection.click_Files_Browse_Btn();
		javaUtils.pngUpload();
		commonUtils.click_SubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
	}
	
	@Test(priority = 14)
	public void verify_StoreOwner_SignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("p");
		commonUtils.click_SignOut_Btn();
		driver.navigate().refresh();
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@Test(priority = 15)
	public void verify_ManagerSignInFromAvataar_Test2() throws InterruptedException{
		loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
	}
	
	@Test(priority = 16)
	public void verify_StageSwitchToResourceReview_Test2() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Resource Review");
		commonUtils.mousehover_ToPageTitle("Resource Review");
	}
	
	@Test(priority = 17)
	public void verify_ResourceReview_ReviewTable_Test2() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		System.out.println("product ID entered in resource review search is "+ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		resourceReviewPage.click_AcceptIcon();
		Thread.sleep(1000);
	}
	
	@Test(priority = 18)
	public void verify_ResourceReview_ComplexityDropdown_Test() throws InterruptedException {
		resourceReviewPage.click_ComplexityDropdown();
		Thread.sleep(500);
		resourceReviewPage.select_complexity("1");
	}
	
	@Test (priority = 19)
	public void verify_ResourceReview_Category_Test() throws InterruptedException {
		resourceReviewPage.click_CategoryBtn();
		resourceReviewPage.select_category(prop.getProperty("Category")); //change any category in config
		resourceReviewPage.select_subcategory(prop.getProperty("Sub_Category")); //change any sub category in config
		resourceReviewPage.click_SaveBtn_category();
		commonUtils.click_SubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Resource Review completed Successfully");
	}
	
	@Test(priority= 20)
	public void verify_StageSwitchTo3DTeamAssignment_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "3D Team Assignment");
		commonUtils.mousehover_ToPageTitle("3D Team Assignment");
	}
	
	@Test(priority= 21)
	public void verify_3DTeamAssignment_DueDate_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(1000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(1000);
		threeD_TeamAssignmentPage.click_datePicker();
		threeD_TeamAssignmentPage.selectDueDate("30");
	}
	
	@Test(priority= 22)
	public void Verify_ModTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Modelling_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Modelling_Team"));
	}
	
	@Test(priority= 23)
	public void Verify_TexturingTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Texturing_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Texturing_Team"));
	}
	
	@Test(priority= 24,enabled=false)
	public void Verify_AnimationTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Animation_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Animation_Team"));
	}
	
	@Test(priority= 25)
	public void Verify_QCTeamAssignmentDropdown_Test() throws InterruptedException {
		threeD_TeamAssignmentPage.click_QC_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("QC_Team"));
	}
	
	@Test(priority= 26)
	public void Verify_3DTeamAssignment_SubmitBtn_Test() throws InterruptedException {
		commonUtils.click_SubmitBtn();
		Thread.sleep(8000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("3D Teams have been assigned Successfully");
	}
	
	@Test(priority = 27)
	public void verify_Manager_SignOut_Test() throws InterruptedException {
		commonUtils.click_Profile("3");
		commonUtils.click_SignOut_Btn();
		driver.navigate().refresh();
		System.out.println("Signed out successfully from Manager");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}