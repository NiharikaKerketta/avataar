package Rejection;
import java.awt.AWTException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import MMOrderFlow.AcceptanceFinal;
import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.pages.OrionEnginePage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)
public class Internal_Artist_Rejection extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	JavaUtils javaUtils;
	OrionEnginePage orionEnginePage;
	AcceptanceFinal acceptanceFinal;
	Actions act;
	public String ProductID;
		
	public Internal_Artist_Rejection() {
			super();
	}
		
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
		Thread.sleep(6000);
		javaUtils = new JavaUtils();
		orionEnginePage = new OrionEnginePage();
		acceptanceFinal = new AcceptanceFinal();
		act = new Actions(driver);
	}
		
	@Test(priority= 1)
		public void Verify_SwitchRoleToModellingLead_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 2)
		public void Verify_SwitchStageToArtistAssignment_ByModellingLead_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Artist Assignment");
		commonUtils.mousehover_ToPageTitle("Artist Assignment");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority = 3)
		public void verify_GetProductID_Test() throws InterruptedException {
		ProductID = commonUtils.getProductID();
	//	commonUtils.SearchBar.sendKeys("ProductID");   //already called in below method
		Thread.sleep(2000);
		System.out.println("Product ID choosen to work on is: "+ProductID);
		}
		
		@Test(priority= 4)
		public void Verify_ModellingArtistAssignment_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(1500);
		commonUtils.click_ArtistDropdown();
		Thread.sleep(1500);
		commonUtils.Select_ArtistFromDropdowm("3dexecutiveavt");
		Thread.sleep(1500);
		commonUtils.click_SubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		Thread.sleep(2000);
		System.out.println("Modelling Artist is assigned Successfully");
		}
		
		@Test(priority= 5)
		public void Verify_SwitchRoleToModellingArtist_And_StageToModelling_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Modelling");
		commonUtils.mousehover_ToPageTitle("Modelling");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}

		@Test(priority= 6)
		public void Verify_GLBFileUploadInModelling_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Configurator_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_Source_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3");
		javaUtils.glbFileUpload();
		System.out.println("GLB file Uploaded successfully");
		}
		
		@Test(priority= 7)
		public void Verify_FBX_WT_FileUploadInModelling_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("2");
		javaUtils.FBX_WT_Upload();
		System.out.println("FBX file without Texture Uploaded successfully");
		}
		
		@Test(priority= 8)
		public void Verify_Source_FileUploadInModelling_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("5");
		javaUtils.fileUpload_Generic("Sofa_SS");
		System.out.println("Source file Uploaded successfully");
		}
		
		@Test(priority= 9)
		public void Verify_Upload_And_Submit_InModelling_Test() throws InterruptedException {
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 10)
		public void Verify_Autoprocessing_Modelling_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Lead");
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
		}
		
		@Test (priority= 11)
		public void Verify_ModellingQC_Reject_Test () throws InterruptedException, AWTException {
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackRejectBtn();
		Thread.sleep(1500);
		}
		
		@Test (priority = 12)
		public void Verify_ModellingQC_UploadFiles_Test () throws InterruptedException, AWTException {
		orionEnginePage.click_SelectRejectionReasonsDropdown();
		orionEnginePage.select_Reasons("Model issues");
		System.out.println("Reason Selected");
		orionEnginePage.click_uploadImagesAfterRejection();
		javaUtils.fileUpload_Generic("Sofa_SS");
		System.out.println("File uploaded");
		Thread.sleep(4000);
		orionEnginePage.click_addComments();
		orionEnginePage.enter_RejectionCommentsText();
		System.out.println("comments added");
		Thread.sleep(3000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(8000);
		//need code for closing toast message on top right
		System.out.println("Feedback for rejection in Modelling submitted successfully");
		Thread.sleep(3000);
		}
		
		@Test (priority = 13)
		public void Verify_ModellingArtist_AfterRejection_Test() throws InterruptedException {
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Modelling");
		commonUtils.mousehover_ToPageTitle("Modelling");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		}
		
		@Test(priority= 14)
		public void Verify_GLBFileUploadInModellingAfterRejection_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2500);
		//added verification for Rejection Work status
		commonUtils.getRejectedWorkStatus_Generic("Modelling Rejected");
		Thread.sleep(2000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(8000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
//		orionEnginePage.click_Configurator_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_Source_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3");
		javaUtils.glbFileUpload();
		System.out.println("GLB file after Rejection Uploaded successfully");
		}
		
		@Test(priority= 15)
		public void Verify_FBX_WT_FileUploadInModellingAfterRejection_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("2");
		javaUtils.FBX_WT_Upload();
		System.out.println("FBX file without Texture after Rejection Uploaded successfully");
		}
		
		@Test(priority= 16)
		public void Verify_Source_FileUploadInModellingAfterRejection_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("5");
		javaUtils.fileUpload_Generic("Sofa_SS");
		System.out.println("Source file after Rejection Uploaded successfully");
		}
		
		@Test(priority= 17)
		public void Verify_Upload_And_Submit_InModellingAfterRejection_Test() throws InterruptedException {
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		System.out.println("All File uploaded successfully after Rejection");
		}
		
		@Test(priority= 18)
		public void Verify_Autoprocessing_ModellingAfterRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
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
		
		@Test (priority= 19)
		public void Verify_ModellingQC_Accept_Test () throws InterruptedException {
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1500);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 20)
		public void Verify_SwitchRoleToTexturingLead_And_StageToArtistAssignment_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Artist Assignment");
		commonUtils.mousehover_ToPageTitle("Artist Assignment");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 21)
		public void Verify_TexturingArtistAssignment_ByTexturingLead_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(500);
		commonUtils.click_ArtistDropdown();
		Thread.sleep(2000);
		commonUtils.Select_ArtistFromDropdowm("3dexecutiveavt");
		Thread.sleep(2000);
		commonUtils.click_SubmitBtn();
		Thread.sleep(5000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
		Thread.sleep(2000);
		}
		
		@Test(priority= 22)
		public void Verify_SwitchRoleToTexturingArtist_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Texturing");
		commonUtils.mousehover_ToPageTitle("Texturing");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		}
		
		
		@Test(priority= 23)
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
		
		@Test(priority= 24)
		public void Verify_fbx_T_UploadInTexturing_Test() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("1");
		javaUtils.fileUpload_Generic("Recliner_T.fbx");
		System.out.println("FBX_T file Uploaded successfully in texturing");
		}
		
		@Test(priority= 25)
		public void Verify_Upload_And_Submit_InTexturing_Test() throws InterruptedException {
		Thread.sleep(2000);
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 26)
		public void Verify_Autoprocessing_Texturing_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Lead");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
			
		for (int i=1; i<22; i++) {
				commonUtils.SearchBar.sendKeys(ProductID);
				Thread.sleep(2500);
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
		
		@Test (priority= 27)
		public void Verify_TexturingQC_Rejection_Test () throws InterruptedException {
		Thread.sleep(3000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackRejectBtn();
		Thread.sleep(1500);
		}
			
			
		@Test (priority = 28)
		public void Verify_TexturingQC_UploadFiles_Test () throws InterruptedException, AWTException {
		orionEnginePage.click_SelectRejectionReasonsDropdown();
		orionEnginePage.select_Reasons("Texture issues");
		System.out.println("Reason Selected for Texturing Rejection");
		orionEnginePage.click_uploadImagesAfterRejection();
		javaUtils.fileUpload_Generic("Sofa_SS");
		System.out.println("File uploaded");
		Thread.sleep(4000);
		orionEnginePage.click_addComments();
		orionEnginePage.enter_RejectionCommentsText();
		System.out.println("comments added");
		Thread.sleep(3000);
		orionEnginePage.click_EngineSubmitBtn();
		System.out.println("Feedback for rejection in Texturing submitted successfully");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(3000);
	}
		
		@Test(priority= 29)
		public void Verify_SwitchRoleToTexturingArtistAfterRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Texturing");
		commonUtils.mousehover_ToPageTitle("Texturing");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		Thread.sleep(2000);
		}
		
		
		@Test(priority= 30)
		public void Verify_GLBFile_UploadInTexturingAfterRejection_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.getRejectedWorkStatus_Generic("Texturing Rejected");
		Thread.sleep(2000);
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
		public void Verify_fbx_T_UploadInTexturingAfterRejection_Test() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("1");
		javaUtils.fileUpload_Generic("Recliner_T.fbx");
		System.out.println("FBX_T file Uploaded successfully in texturing");
		}
		
		@Test(priority= 32)
		public void Verify_Upload_And_Submit_InTexturingAfterRejection_Test() throws InterruptedException {
		Thread.sleep(2000);
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 33)
		public void Verify_Autoprocessing_TexturingAfterRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
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
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1500);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 35)
		public void Verify_SwitchRoleToQCLead_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("QC Lead");
		//commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup(); //not applicable for QC teams
		//Thread.sleep(3000);
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "QC Exec Assignment");
		commonUtils.mousehover_ToPageTitle("QC Exec Assignment");
		Thread.sleep(2000);
		//commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup(); //not applicable for QC teams
		}
		
		@Test(priority= 36)
		public void Verify_QCArtistAssignment_ByQCLead_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		commonUtils.Click_genericArtistDropdown(13);
		Thread.sleep(2000);
		commonUtils.Select_ArtistFromDropdowm("3dexecutiveavt");
		Thread.sleep(2000);
		commonUtils.click_SubmitBtn();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_OnSuccessDialog();
		}
		
		@Test(priority= 37)
		public void Verify_SwitchRoleToQCArtist_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("QC Artist");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Approval L1");
		commonUtils.mousehover_ToPageTitle("Approval L1");
		}
		
		@Test(priority = 38)
		public void Verify_RejectionInApprovalL1_ByQCArtist_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2500);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(4000);
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackRejectBtn();
		Thread.sleep(1500);
		}
		
		@Test (priority = 39)
		public void Verify_QCArtist_UploadFiles_Test () throws InterruptedException, AWTException {
		orionEnginePage.click_SelectRejectionReasonsDropdown();
		orionEnginePage.select_Reasons("Size issues");
		Thread.sleep(2000);
		System.out.println("Reason Selected");
		orionEnginePage.click_uploadImagesAfterRejection();
		javaUtils.fileUpload_Generic("Sofa_SS");
		System.out.println("File uploaded");
		Thread.sleep(2000);
		orionEnginePage.click_addComments();
		orionEnginePage.enter_RejectionCommentsText();
		System.out.println("comments added");
		Thread.sleep(3000);
		orionEnginePage.click_EngineSubmitBtn();
		Thread.sleep(8000);
		commonUtils.click_closePopup_For3DModelRejected();
		//need code for closing toast message on top right
		System.out.println("Feedback for rejection in QC Stage submitted successfully");
		Thread.sleep(3000);
		}
		
		//Full Acceptance code from modelling Artist
		
		@Test(priority= 40)
		public void Verify_SwitchRoleToModellingArtist_And_StageToModellingAfter_QCRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Modelling");
		commonUtils.mousehover_ToPageTitle("Modelling");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 41)
		public void Verify_GLBFileUploadInModellingAfter_QCRejection_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2500);
		commonUtils.getRejectedWorkStatusAfterQC_Generic("Internal Rework 1");
		Thread.sleep(2000);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(10000);
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
//		orionEnginePage.click_Configurator_Btn();
//		Thread.sleep(2000);
		orionEnginePage.click_Source_Btn();
		Thread.sleep(2000);
		orionEnginePage.click_UploadFiles("3"); // for_glb
		javaUtils.fileUpload_Generic("Recliner.glb");
		System.out.println("GLB file Uploaded successfully");
		}
		
		@Test(priority= 42)
		public void Verify_FBX_WT_FileUploadInModellingAfter_QCRejection_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("2"); //for FBX_WT
		javaUtils.fileUpload_Generic("Recliner_WT.fbx");
		System.out.println("FBX file without Texture Uploaded successfully");
		}
		
		@Test(priority= 43)
		public void Verify_otherSource_FileUploadInModellingAfter_QCRejection_Test() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		orionEnginePage.click_UploadFiles("5");
		javaUtils.fileUpload_Generic("Sofa_SS.png");
		System.out.println("Other source .png Uploaded successfully");
		}
		
		@Test(priority= 44)
		public void Verify_Upload_And_Submit_InModellingAfter_QCRejection_Test() throws InterruptedException {
		orionEnginePage.click_Upload_Btn();
		Thread.sleep(10000);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 45)
		public void Verify_Autoprocessing_ModellingAfter_QCRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Modelling Lead");
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
		}
		
		@Test (priority= 46)
		public void Verify_ModellingQC_AcceptAfter_QCRejection_Test () throws InterruptedException {
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1500);
		orionEnginePage.click_EngineSubmitBtn();
		//need code for closing toast message on top right
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 47)
		public void Verify_SwitchRoleToTexturingArtistAfter_QCRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("Texturing Artist");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Texturing");
		commonUtils.mousehover_ToPageTitle("Texturing");
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		
		@Test(priority= 48)
		public void Verify_GLBFile_UploadInTexturingAfter_QCRejection_Test() throws InterruptedException, AWTException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2500);
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
		
		@Test(priority= 49)
		public void Verify_fbx_T_UploadInTexturingAfter_QCRejection_Test() throws InterruptedException, AWTException {
			Thread.sleep(2000);
			orionEnginePage.click_UploadFiles("1");
			javaUtils.fileUpload_Generic("Recliner_T.fbx");
			System.out.println("FBX_T file Uploaded successfully in texturing");
		}
		
		@Test(priority= 50)
		public void Verify_Upload_And_Submit_InTexturingAfter_QCRejection_Test() throws InterruptedException {
			Thread.sleep(2000);
			orionEnginePage.click_Upload_Btn();
			Thread.sleep(10000);
			orionEnginePage.click_EngineSubmitBtn();
			commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 51)
		public void Verify_Autoprocessing_TexturingAfter_QCRejection_Test() throws InterruptedException {
			commonUtils.click_RoleDropdown_Btn();
			commonUtils.select_RoleFromDropdown("Texturing Lead");
			commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
			Thread.sleep(2000);
			
			for (int i=1; i<22; i++) {
				commonUtils.SearchBar.sendKeys(ProductID);
				Thread.sleep(2500);
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
		
		@Test (priority= 52)
		public void Verify_TexturingQC_AcceptAfter_QCRejection_Test () throws InterruptedException {
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1500);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_DownloadAvataar3DApp_popup();
		}
		
		@Test(priority= 53)
		public void Verify_SwitchRoleToQCArtistAfter_QCRejection_Test() throws InterruptedException {
		commonUtils.click_RoleDropdown_Btn();
		commonUtils.select_RoleFromDropdown("QC Artist");
		commonUtils.mousehover_ToTenantAnd_SwitchStage(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Approval L1");
		commonUtils.mousehover_ToPageTitle("Approval L1");
		}
		
		@Test(priority = 54)
		public void Verify_ApprovalL1_ByQCArtistAfter_QCRejection_Test() throws InterruptedException {
		commonUtils.SearchBar.sendKeys(ProductID);
		Thread.sleep(2500);
		commonUtils.click_productName_hyperlink();
		Thread.sleep(4000);
		orionEnginePage.click_Feedback_Btn();
		Thread.sleep(1500);
		orionEnginePage.click_FeedbackAcceptBtn();
		Thread.sleep(1500);
		orionEnginePage.click_EngineSubmitBtn();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		}
		
		@Test(priority= 55)
		public void Verify_SignOut_QCArtist_Test () throws InterruptedException {
			commonUtils.SignOut_genericMethod("3");
			System.out.println("Signed out successfully from 3dExecutive");
		}
		
		@AfterClass
		public void tearDown() {
			driver.quit();
		}
}


