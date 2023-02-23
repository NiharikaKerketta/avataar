package AssetLibraryFlow;

import java.awt.AWTException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.AssetLibraryPage;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.util.JavaUtils;
import ai.avataar.supernovaV3.util.ListenersClass;

@Listeners(ListenersClass.class)
public class AssetLibrary extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	AssetLibraryPage assetLibraryPage;
	JavaUtils javaUtils;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		assetLibraryPage = new AssetLibraryPage();
		javaUtils = new JavaUtils();
		commonUtils = loginPage.login(prop.getProperty("QA_AVT"), prop.getProperty("QA_pwd"));
	}
	
	//Project selection
	@Test(priority = 1)
	public void Verify_AssetLibPage_Test() throws InterruptedException {
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byInternal3D"), "Asset Library");//comment-line is failing due to absence of common pagetitle, try with title displaying on asset library
	}
	
	@Test(priority = 2)
	public void Verify_3DMesh_Test() throws InterruptedException {
		assetLibraryPage.click_search("Sofa");
//		Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void Verify_Asset_Test() throws InterruptedException {
		assetLibraryPage.click_asset();
		System.out.println("Any asset got selected");
	}
	
	@Test(priority = 4)
	public void Verify_ViewImages_Test() throws InterruptedException {
		assetLibraryPage.click_ViewAllImages();
		assetLibraryPage.click_ImageSlider();
		assetLibraryPage.click_ImageSlider();
		assetLibraryPage.click_ImageSlider();
		commonUtils.click_CloseIcon_OnSuccessDialog();
		System.out.println("Viewed all the images");
	}
	
	@Test(priority = 5)
	public void Verify_DownloadAllFiles_Test() throws InterruptedException {
		assetLibraryPage.click_DownloadAllFiles();
//		Thread.sleep(3000);
		System.out.println("All files got downloaded");
	}
	
	@Test(priority = 6)
	public void Verify_ParticularImageDownload_Test() throws InterruptedException {
		assetLibraryPage.click_DownloadOneFile();
//		Thread.sleep(2000);
		assetLibraryPage.click_BackButtonIn3DMesh();
	}
	
	@Test(priority = 7)
	public void Verify_SwitchDropdown_Test() throws InterruptedException {
		assetLibraryPage.click_3Ddropdown();
		commonUtils.select_RoleFromDropdown("Texture");
		assetLibraryPage.click_3Ddropdown();
		commonUtils.select_RoleFromDropdown("Material");
		assetLibraryPage.click_3Ddropdown();
		commonUtils.select_RoleFromDropdown("3D Mesh");
	}
	
	@Test(priority = 8)
	public void Verify_checkbox_Test() throws InterruptedException {
		assetLibraryPage.click_checkbox("1");
//		Thread.sleep(3000);
		assetLibraryPage.click_clearBtn();
	}
	
	@Test(priority = 9)
	public void Verify_checkbox2_Test() throws InterruptedException {
		assetLibraryPage.click_checkbox2();
//		Thread.sleep(3000);
		assetLibraryPage.click_clearBtn();
	}
	
	@Test(priority = 10)
	public void Verify_SearchByImage() throws InterruptedException, AWTException {
		assetLibraryPage.click_searchByImage();
		assetLibraryPage.click_clickUploadImage();
		javaUtils.pngUpload();
		assetLibraryPage.click_SearchBtn();
//		Thread.sleep(3000);
	}
	
	@Test(priority = 11)
	public void Verify_CloseBtn_Test() throws InterruptedException {
		assetLibraryPage.click_CloseBtn();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
