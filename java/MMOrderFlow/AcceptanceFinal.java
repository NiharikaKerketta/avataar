package MMOrderFlow;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.pages.PlaceOrderPage1;
import ai.avataar.supernovaV3.pages.ResourceReviewPage;
import ai.avataar.supernovaV3.pages.ThreeD_TeamAssignmentPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;


@Listeners(ListenersClass.class)
public class AcceptanceFinal extends TestBase{
	LoginPage loginPage;
	CommonUtils commonUtils;
	PlaceOrderPage1 placeOrderPage;
	ResourceReviewPage resourceReviewPage;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	ThreeD_TeamAssignmentPage threeD_TeamAssignmentPage;
	SoftAssert softAsrt;
	
	public String ProductID;
	
	public AcceptanceFinal() {
		super();
		PageFactory.initElements(driver, this);
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
		softAsrt = new SoftAssert();
	}
	
	@Test(priority= 1)
	
	public void verify_TenantSelection_Test() throws InterruptedException {
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //can change tenant from config
		Thread.sleep(3000);
	}
	
	@Test(priority = 2)
	public void verify_SaveProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductIDByIndex_Generic(1);
		commonUtils.verify_Varients();
		Thread.sleep(3000);
		System.out.println("Product ID choosen to work on is: "+ProductID);
	}
	
	@Test(priority = 3)
	public void verify_PlaceOrderFromAvataar_Test() throws InterruptedException {
		commonUtils.SelectCheckBoxByIndex_Generic(2);
		placeOrderPage.getBottomOrderText();
		placeOrderPage.click_OrderFromAvataar_Btn();
		Thread.sleep(2000);
		softAsrt.assertEquals((commonUtils.get_successfulOrderPlacedMsgText()),"Order placed successfully for 1 product");
		commonUtils.click_Closeicon_successPopup_placeOrderStage();
		System.out.println("Place order with avataar is successfull");
		softAsrt.assertAll();
	}
	
	@Test(priority = 4)
	public void verify_StoreOwnerSignOut_Test() throws InterruptedException {
		commonUtils.SignOut_genericMethod("p");
		System.out.println("Signed out successfully from Store Owner");
	}
	
	@Test(priority = 5)
	public void verify_ManagerSignInFromAvataar_Test() throws InterruptedException{
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
		Thread.sleep(6000);
	}
	
	@Test(priority = 6)
	public void verify_StageSwitchToResourceReview_Test() throws InterruptedException {
		Thread.sleep(2000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Resource Review");
		commonUtils.mousehover_ToPageTitle("Resource Review");
	}
	
	@Test(priority = 7)
	public void verify_ResourceReview_ReviewTable_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		System.out.println("product ID entered in resource review search is "+ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		resourceReviewPage.click_AcceptIcon();
		Thread.sleep(2000);
	}
	
	@Test(priority = 8)
	public void verify_ResourceReview_ComplexityDropdown_Test() throws InterruptedException {
		resourceReviewPage.click_ComplexityDropdown();
		Thread.sleep(1000);
		resourceReviewPage.select_complexity("1");
	}
	
	@Test (priority = 9)
	public void verify_ResourceReview_Category_Test() throws InterruptedException {
		resourceReviewPage.click_CategoryBtn();
		resourceReviewPage.select_category(prop.getProperty("Category")); //change any category in config
		resourceReviewPage.select_subcategory(prop.getProperty("Sub_Category")); //change any sub category in config
		resourceReviewPage.click_SaveBtn_category();
		commonUtils.click_SubmitBtn();
		Thread.sleep(7000);
		softAsrt.assertEquals(commonUtils.get_SuccessfullySubmittedmsg(), "Submitted Successfully!");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Resource Review completed Successfully");
		softAsrt.assertAll();
	}
	
	@Test(priority= 10)
	public void verify_StageSwitchTo3DTeamAssignment_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "3D Team Assignment");
		commonUtils.mousehover_ToPageTitle("3D Team Assignment");
	}
	
	@Test(priority= 11)
	public void verify_3DTeamAssignment_DueDate_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(3000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		threeD_TeamAssignmentPage.click_datePicker();
		threeD_TeamAssignmentPage.selectDueDate("30");
	}
	
	@Test(priority= 12)
	public void Verify_ModTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Modelling_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Modelling_Team"));
	}
	
	@Test(priority= 13)
	public void Verify_TexturingTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Texturing_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Texturing_Team"));
	}
	
	@Test(priority= 14,enabled=false)
	public void Verify_AnimationTeamAssignmentDropdown_Test() {
		threeD_TeamAssignmentPage.click_Animation_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("Animation_Team"));
	}
	
	@Test(priority= 15)
	public void Verify_QCTeamAssignmentDropdown_Test() throws InterruptedException {
		threeD_TeamAssignmentPage.click_QC_TeamDd();
		threeD_TeamAssignmentPage.Select_TeamNameFrom_DD(prop.getProperty("QC_Team"));
	}
	
	@Test(priority= 16)
	public void Verify_3DTeamAssignment_SubmitBtn_Test() throws InterruptedException {
		commonUtils.click_SubmitBtn();
		Thread.sleep(10000);
		softAsrt.assertEquals(commonUtils.get_SuccessfullySubmittedmsg(), "Submitted Successfully!");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("3D Teams have been assigned Successfully");
		softAsrt.assertAll();
	}
	
	@Test(priority= 17)
	public void Verify_SwitchRoleToModellingLead_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
	}
	
	@Test(priority= 18)
	public void Verify_SwitchStageToArtistAssignment_ByModellingLead_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Artist Assignment");
		commonUtils.mousehover_ToPageTitle("Artist Assignment");
		Thread.sleep(4000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 19)
	public void Verify_ModellingArtistAssignment_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(1500);
		commonUtils.click_ArtistDropdown();
		Thread.sleep(1500);
		commonUtils.Select_ArtistFromDropdowm(prop.getProperty("Modelling_Artist"));
		Thread.sleep(1500);
		commonUtils.click_SubmitBtn();
		Thread.sleep(6000);
		softAsrt.assertEquals(commonUtils.get_SuccessfullySubmittedmsg(), "Submitted Successfully!");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		System.out.println("Modelling Artist is assigned Successfully");
		softAsrt.assertAll();
	}
	
	@Test(priority= 20)
	public void Verify_SwitchRoleToModellingArtist_And_StageToModelling_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Modelling");
		commonUtils.mousehover_ToPageTitle("Modelling");
		Thread.sleep(3000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 21)
	public void Verify_GLBFileUploadInModelling_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(4000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Configurator_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_Source_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3"); // for_glb
		javaUtils.fileUpload_Generic("Recliner.glb");
		System.out.println("GLB file Uploaded successfully");
	}
	
	@Test(priority= 22)
	public void Verify_FBX_WT_FileUploadInModelling_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("2"); //for FBX_WT
		javaUtils.fileUpload_Generic("Recliner_WT.fbx");
		System.out.println("FBX file without Texture Uploaded successfully");
	}
	
	@Test(priority= 23)
	public void Verify_otherSource_FileUploadInModelling_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("5");
		javaUtils.fileUpload_Generic("Sofa_SS.png");
		System.out.println("Other source .png Uploaded successfully");
	}
	
	@Test(priority= 24)
	public void Verify_Upload_And_Submit_InModelling_Test() throws InterruptedException {
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(18000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 25)
	public void Verify_Autoprocessing_Modelling_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		Thread.sleep(2000);
		commonUtils.select_RoleFromDropdown("Modelling Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		
		for (int i=1; i<22; i++) {
			commonUtils.SearchBar.sendKeys(ProductID);
			Thread.sleep(4000);
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
	}
	
	@Test (priority= 26)
	public void Verify_ModellingQC_Accept_Test () throws InterruptedException {
		Thread.sleep(3000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(3000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(3000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(9000);
		//need code for closing toast message on top right
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 27)
	public void Verify_SwitchRoleToTexturingLead_And_StageToArtistAssignment_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Artist Assignment");
		Thread.sleep(2000);
		commonUtils.mousehover_ToPageTitle("Artist Assignment");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 28)
	public void Verify_TexturingArtistAssignment_ByTexturingLead_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(4000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		commonUtils.click_ArtistDropdown();
		Thread.sleep(2000);
		commonUtils.Select_ArtistFromDropdowm(prop.getProperty("Texturing_Artist"));
		Thread.sleep(2000);
		commonUtils.click_SubmitBtn();
		Thread.sleep(5000);
		softAsrt.assertEquals(commonUtils.get_SuccessfullySubmittedmsg(), "Submitted Successfully!");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		Thread.sleep(2000);
		softAsrt.assertAll();
	}
	
	@Test(priority= 29)
	public void Verify_SwitchRoleToTexturingArtist_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Texturing");
		commonUtils.mousehover_ToPageTitle("Texturing");
		Thread.sleep(2000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	
	@Test(priority= 30)
	public void Verify_GLBFile_UploadInTexturing_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(4000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		//orionEnginePage.click_Configurator_Btn(); //commented line as config is default page
		//Thread.sleep(2000);
		orionEnginePage.click_Source_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3");
		javaUtils.fileUpload_Generic("Recliner.glb");
		System.out.println("GLB file Uploaded successfully in texturing");
	}
	
	@Test(priority= 31)
	public void Verify_fbx_T_UploadInTexturing_Test() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("1");
		javaUtils.fileUpload_Generic("Recliner_T.fbx");
		System.out.println("FBX_T file Uploaded successfully in texturing");
	}
	
	@Test(priority= 32)
	public void Verify_Upload_And_Submit_InTexturing_Test() throws InterruptedException {
		Thread.sleep(2000);
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(18000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 33)
	public void Verify_Autoprocessing_Texturing_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		Thread.sleep(2000);
		commonUtils.select_RoleFromDropdown("Texturing Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		
		for (int i=1; i<22; i++) {
			commonUtils.SearchBar.sendKeys(ProductID);
			Thread.sleep(4000);
			try {
				commonUtils.click_productnameIfClickable();
				System.out.println("successfully completed autoprocessing in after texturing");
				break;
			} catch (Exception e) {
				System.out.println("not clicked "+i+" time");
			}
			commonUtils.SearchBar.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			Thread.sleep(3000);
		}
	}
	
	@Test (priority= 34)
	public void Verify_TexturingQC_Accept_Test () throws InterruptedException {
		Thread.sleep(3000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(3000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(3000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(9000);
		//need code for closing toast message on top right
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
	}
	
	@Test(priority= 35)
	public void Verify_SwitchRoleToQCLead_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("QC Lead");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "QC Exec Assignment");
		commonUtils.mousehover_ToPageTitle("QC Exec Assignment");
		Thread.sleep(2000);
		}
	
	@Test(priority= 36)
	public void Verify_QCArtistAssignment_ByQCLead_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		commonUtils.Click_genericArtistDropdown(13);
		Thread.sleep(2000);
		commonUtils.Select_ArtistFromDropdowm(prop.getProperty("QC_Artist"));
		Thread.sleep(2000);
		commonUtils.click_SubmitBtn();
		Thread.sleep(10000);
		softAsrt.assertEquals(commonUtils.get_SuccessfullySubmittedmsg(), "Submitted Successfully!");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		Thread.sleep(2000);
		softAsrt.assertAll();
	}
	
	@Test(priority= 37)
	public void Verify_SwitchRoleToQCArtist_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("QC Artist");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Approval L1");
		Thread.sleep(1000);
		commonUtils.mousehover_ToPageTitle("Approval L1");
	}
	
	@Test(priority = 38)
	public void Verify_ApprovalL1_ByQCArtist_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(4000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(4000);
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(2000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(10000);
		softAsrt.assertEquals(commonUtils.get_ReviewAndL1popupText(), "3D Model Approved");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		softAsrt.assertAll();
	}
	
	@Test(priority= 39)
	public void Verify_SignOut_QCArtist_Test () throws InterruptedException {
		commonUtils.SignOut_genericMethod("3");
		System.out.println("Signed out successfully from 3dExecutive");
	}
	
//Changing the role to Store Owner for Review stage
	@Test(priority = 40)
	public void Verify_StoreOwnerSignIn_Test_For_Review() throws InterruptedException {
		loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		Thread.sleep(2000);
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //can change tenant from config
		Thread.sleep(3000);
	}
	
	@Test(priority= 41)
	public void Verify_ApprovalL2_ByStoreOwner_Test() throws InterruptedException {
		commonUtils.mousehover_ToPageTitle("Place Order");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Review");
		Thread.sleep(1000);
		commonUtils.mousehover_ToPageTitle("Review");
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(3000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(6000);
		orionEnginePage.click_Feedback_Btn_ReviewStage();
		Thread.sleep(2000);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(2000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(6000);
		softAsrt.assertEquals(commonUtils.get_ReviewAndL1popupText(), "3D Model Approved");
		commonUtils.click_CloseIcon_OnSuccessDialog();
		softAsrt.assertAll();
	}
	
	@Test(priority = 42)
	public void Verify_Publish_ByStoreOwner_Test() throws InterruptedException {
		Thread.sleep(3000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Publish");
		commonUtils.mousehover_ToPageTitle("Publish");
		Thread.sleep(2000);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(3000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(1000);
		commonUtils.click_PublishBtn();
		Thread.sleep(4000);
		softAsrt.assertEquals(commonUtils.get_publishPopupText(), "3D and AR for the selected product is now live on the PDP");
		commonUtils.click_CloseIcon_OnSuccessDialog();	
		System.out.println("Product has been Published");
		softAsrt.assertAll();
	}
	
	@Test(priority = 43)
	public void Verify_Live_Test() throws InterruptedException {
		Thread.sleep(3000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Live");
		commonUtils.mousehover_ToPageTitle("Live");
		Thread.sleep(2000);
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		assertTrue(commonUtils.ProductID_FirstRow.isDisplayed());
		System.out.println("Product is in Live Stage");
	}

@AfterClass
public void tearDown() {
	driver.quit();
	}	
}
