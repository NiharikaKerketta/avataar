package ai.avataar.supernovaV3.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import ai.avataar.supernovaV3.base.TestBase;

public class CommonUtils extends TestBase {
	
	Actions action = new Actions(driver);
	SoftAssert softAsrt = new SoftAssert();
	
	public String SelectedProductID1;
	
	//Page factory - objects repository
	
	@FindBy(xpath= "//div[@class='stage-info']")
	private WebElement StageName;
	
	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement SingleCheckbox;
	
	@FindBy (xpath="(//div[@class='column-text'])[1]")
	public WebElement ProductID_FirstRow;
	
	@FindBy(xpath = "//button[normalize-space()='Dismiss']")
	WebElement Dismisss_Btn;
	
	@FindBy(xpath="//div[text()='Sign Out']")
	private WebElement SignOut;
	
	@FindBy(xpath="//*[name()='svg' and @class='MuiSvgIcon-root']")
	private WebElement Closeicon_successPopup_placeOrderStage;
	
	@FindBy(xpath = "(//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')])[1]")
	public WebElement SearchBar;
	  
	@FindBy(xpath = "//span[text()='Submit']")
	private WebElement SubmitBtn;
	
	@FindBy(xpath = "//span[text()='Publish']")
	private WebElement PublishBtn;
		
	@FindBy(xpath= "//div[@class='role-switcher-container']")
	private WebElement RoleDropdown_Btn;
	
	// Alternative for role switcher
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[21]")
	private WebElement SwitchRoleDropdown_Btn;
		 
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement CloseIcon_DownloadAvataar3DApp;
	
	@FindBy(xpath = "//span[@class='CustomFormFields_value__5rQV3']")
	private WebElement current_Role;
		
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[16]")
	private WebElement ArtistDropdown;												
		
	public void Select_ArtistFromDropdowm(String artist_name_dd) {
			WebElement artist_name = driver.findElement(By.xpath("//span[normalize-space()='"+artist_name_dd+"']"));
			artist_name.click();
	}
	
	public void Click_genericArtistDropdown (int index ) {
		WebElement genericArtistDropdown = driver.findElement(By.xpath("(//*[name()='svg'][@stroke='currentColor'])["+index+"]"));
		genericArtistDropdown.click();
	}
		
	@FindBy(xpath = "(//div[@class='textLink'])[1]")
	private WebElement ProductName_hyperlink;
	
	//elements for navigation
	@FindBy(xpath="//span[@class='total-count']")
	private WebElement productTotalCount;

	@FindBy(xpath="//div[@class='rows-per-page']")
	private WebElement ItemsPerPage;
	
	@FindBy(xpath="(//input[@type='checkbox'])[1]")
	private WebElement AllProductCheckbox;
	
	@FindBy(xpath="//div[@class='flex-center']//span")
	private WebElement productSelected;
	
	//newly added
	@FindBy(xpath = "//*[name()='path' and contains(@d,'M19 6.41L1')]")
	private WebElement RejectedCloseButton;
	
	@FindBy(xpath ="//div[contains(@class,'Pill_pillWrapper__gOREn Pill_yellow__Yc92r')]")
	private WebElement WorkStatusForArtistRejection;
	
	@FindBy(xpath = "//div[contains(@class,'Pill_pillWrapper__gOREn Pill_red__eAGCz')]")
	private WebElement WorkStatusForInternalRejection;
	
	@FindBy(xpath = "(//*[name()='svg'][@id='expandable-button'])[2]")
	private WebElement varient_arrow;
	
	@FindBy(xpath = "(//div[@class='long-text'])[4]")
	private WebElement prd_Name_first_Line;
	
	//Initializing page objects:
		public CommonUtils() {
			PageFactory.initElements(driver, this);//this means current class variables/objects
		}
	

//=====================================Action Methods for Common_utils page ====================================//
			
	public void click_Tenant(String tenantName) throws InterruptedException {
		WebElement tenant = driver.findElement(By.xpath("//div[@title='"+tenantName+"']"));
		tenant.click();
		Thread.sleep(3000);
		String selectedtenant = tenant.getText();
		assertEquals(selectedtenant, prop.getProperty("ExpectedTenant") , "selected tenant is wrong"); //Expected tenant can be changed from config while running script on differnt one
	}
	
	public void click_SingleCheckbox_FirstRow() throws InterruptedException {
		SingleCheckbox.click();
		Thread.sleep(500);
	}
	
	public String getProductID(){
		return ProductID_FirstRow.getText();
	}
	
	public void click_Dismiss_Btn() {
		Dismisss_Btn.click();
	}
	
	public void storeProductID() {
		SelectedProductID1 = getProductID();
		System.out.println("SelectedProductID is " + SelectedProductID1);
		}
	
	public void click_Closeicon_successPopup_placeOrderStage() {
		Closeicon_successPopup_placeOrderStage.click();
	}
	
	public void click_Profile(String profileId) {
		 WebElement Id = driver.findElement(By.xpath("//div[text()='"+profileId+"']"));
		 Id.click();
	}
	
	public void click_SignOut_Btn() {
		SignOut.click();
	}
	
	public void click_SearchBar() {
		SearchBar.click();
	}
	
	public void search_By_ID() throws InterruptedException {
		click_SearchBar();
		Thread.sleep(500);
		SearchBar.sendKeys(SelectedProductID1);
	}
	
	public void click_Search() {
		SearchBar.click();
	}
	
	public void click_SubmitBtn() {
		SubmitBtn.click();
	}
	
	public void click_PublishBtn() {
		PublishBtn.click();
	}
	
	public String get_current_Role() {
		String Current_Role = current_Role.getText();
		return Current_Role;
	}
	
	public void mousehover_ToTenantAnd_SwitchStage(String tenantName, String stagename) throws InterruptedException {
		Thread.sleep(2000);
		WebElement tenant = driver.findElement(By.xpath("//div[text()='"+tenantName+"']"));
		action.moveToElement(tenant).perform();
		Thread.sleep(500);
		WebElement stage = driver.findElement(By.xpath("(//div[text()='"+stagename+"'])[1]"));//---------------------------------------------------------------------
		stage.click();
		Thread.sleep(500);
		//String pageTitle = getPageTitle();
	//	Assert.assertEquals(pageTitle, stagename,"Wrong Stage Selected");
	}
	
	public void mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(String tenantName, String stagename) throws InterruptedException {
		Thread.sleep(2000);
		WebElement tenant = driver.findElement(By.xpath("//div[text()='"+tenantName+"']"));
		action.moveToElement(tenant).perform();
		Thread.sleep(500);
		WebElement stage = driver.findElement(By.xpath("(//div[text()='"+stagename+"'])[1]"));//---------------------------------------------------------------------
		stage.click();
		Thread.sleep(500);
	}
	
	//For Modelling QC xpath is different so Different Method has been created
	public void mousehover_ToTenantAnd_SwitchQCStage(String tenantName, String stagename) {
		WebElement tenant = driver.findElement(By.xpath("//div[@title='"+tenantName+"']"));
		action.moveToElement(tenant).perform();
		WebElement stage = driver.findElement(By.xpath("(//div[text()='"+stagename+"'])[2]"));//---------------------------------------------------------------------
		stage.click();
	}
	
	public void click_productnameIfClickable()
	{
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	    		.withTimeout(Duration.ofSeconds(30))
	    		.pollingEvery(Duration.ofSeconds(3))
	    		.ignoring(Exception.class);
	    		
	    		wait.until(ExpectedConditions.elementToBeClickable(ProductName_hyperlink));

	    	    ProductName_hyperlink.click();
	}
	
	public void mousehover_ToPageTitle(String stagename) throws InterruptedException {
		WebElement pageTitle = driver.findElement(By.xpath("(//div[text()='"+stagename+"'])[2]"));
		action.moveToElement(pageTitle).perform();
		Thread.sleep(1500);
	}
	
	public void click_RoleDropdown_Btn() {
		RoleDropdown_Btn.click();
	}
	
	public void click_SwitchRoleDropdown_Btn() {
		SwitchRoleDropdown_Btn.click();
	}
	
	public void select_RoleFromDropdown(String Role_Name) throws InterruptedException {
		WebElement role = driver.findElement(By.xpath("//span[text()='"+Role_Name+"']"));
		role.click();
		Thread.sleep(2000);
		Assert.assertEquals(get_current_Role(), Role_Name,"Wrong Role Selected");
	}
	
	public void click_CloseIcon_DownloadAvataar3DApp_popup() {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(20000))
				.pollingEvery(Duration.ofMillis(1000))
				.ignoring(Exception.class);
				wait.until(ExpectedConditions.visibilityOf(CloseIcon_DownloadAvataar3DApp));
			
			CloseIcon_DownloadAvataar3DApp.click();
		} catch (Exception e) {
			System.out.println("Download Avataar 3D App popup not found");
		}
		
	}
	
	 public void click_ArtistDropdown() {
		 ArtistDropdown.click();
	 }
	 
	 public void click_productName_hyperlink() {
		ProductName_hyperlink.click();
	}

	public void clickSumbitBtn() {
		SubmitBtn.click();
	}
	
	public Boolean Verify_ProductId() {
		Boolean Status = ProductID_FirstRow.isDisplayed();
		return Status;
	}

	//Actions:	
		public String verifyHomePageTitle(){
			return driver.getTitle();
		}
		
		public void Refresh_Page() {
			driver.navigate().refresh();
		}
		
		@FindBy(xpath = "//div[normalize-space()='Order placed successfully for 1 product']")
		private WebElement successfulOrderPlacedMsg;		//at Place Order stage
		
		@FindBy(xpath = "//div[text()='Submitted Successfully!']")
		private WebElement SuccessfullySubmittedmsg;		//at the Resource Review stage - Works for all messages
		
		@FindBy(xpath = "//*[name()='path' and contains(@d,'M19 6.41L1')]")
		private WebElement closeIcon;		//Close Button for Submitted Successfully message
		
		@FindBy(xpath = "//div[@xpath='1']")
		private WebElement publishPopupText;   //Publish popup text
		
		@FindBy(xpath = "//div[@class='MuiDialogTitle-root']//h2[1]")
		private WebElement ReviewAndL1popup;

	//----------------------Methods----------------------//
		public void getOrderPlacedMsg_And_Verify() {
			softAsrt.assertEquals(get_successfulOrderPlacedMsgText(), "Order placed successfully for 1 product", "Wrong Success Message Displayed");
			System.out.println(get_successfulOrderPlacedMsgText());
			softAsrt.assertAll();
	}
		
		public void printNotificationMsg(String printMessage) { //Try to get String with the help of class name as ref
			System.out.println(printMessage);
		}
		
		
		public String get_successfulOrderPlacedMsgText() {
			String OrderPlacedText = successfulOrderPlacedMsg.getText();
			return OrderPlacedText;
		}
		
		public String get_SuccessfullySubmittedmsg() {
			String Success_submit = SuccessfullySubmittedmsg.getText();
			return Success_submit;
		}
		
		public void Read_popup_And_Verify() {
			softAsrt.assertEquals(get_SuccessfullySubmittedmsg(), "Submitted Successfully!","Wrong Success Message Displayed");
			System.out.println(get_SuccessfullySubmittedmsg());
		}
		
		public String get_publishPopupText() {
			String publish_popupMsg = publishPopupText.getText();
			return publish_popupMsg;
		}
		
		public void Read_Publish_Text_And_Verify() {
			softAsrt.assertEquals(get_publishPopupText(), "3D and AR for the selected product is now live on the PDP"," Wrong Publish Message Displayed");
		}
		
		//Method to close the success popup, which comes afte successfull submission of product 
		public void click_CloseIcon_OnSuccessDialog() {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			  .withTimeout(Duration.ofMillis(15000))
			  .pollingEvery(Duration.ofMillis(1000))
			  .ignoring(Exception.class);
			  wait.until(ExpectedConditions.visibilityOf(closeIcon));
			    		
			closeIcon.click();
		}
		
		public String get_ReviewAndL1popupText() {
			String ReviewL1Text = ReviewAndL1popup.getText();
			return ReviewL1Text;
		}
		
		public void Read_Approval_Msg_And_Verify(){
			softAsrt.assertEquals(get_ReviewAndL1popupText(), "3D Model Approved","Wrong Approval Message Displayed");
		}
		
		public String getPageTitle () {
			return StageName.getText();
		}
	
		public void SignOut_genericMethod (String profileId) throws InterruptedException {
			click_Profile(profileId);
			Thread.sleep(1000);
			click_SignOut_Btn();
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(2000);
		}
	
		public void SelectCheckBoxByIndex_Generic(int CheckboxIndex) throws InterruptedException {
			WebElement CheckBox = driver.findElement(By.xpath("(//input[@type='checkbox'])["+CheckboxIndex+"]"));
		    CheckBox.click();
			assertTrue(CheckBox.isSelected(), "checkbox is not selected");
		}
		
		public String getProductIDByIndex_Generic(int selectedcheckboxIndex) {
			String ProductSelected = driver.findElement(By.xpath("(//div[@class='column-text'])["+selectedcheckboxIndex+"]")).getText();
			return ProductSelected;
		}
		
		//methods for navigation
		public String getTotalProductsCount() {
			return productTotalCount.getText();
		}
		
		public void click_ItemsPerPageBtn() {
			ItemsPerPage.click();
		}
		
		public Object click_PageSelectionPerItem(String productCount) throws InterruptedException {
			WebElement count = driver.findElement(By.xpath("//span[text()='"+productCount+"']"));
			count.click();
			return count;
		}
		
		public void click_allProductCheckbox() {
			AllProductCheckbox.click();
		}
		
		public String getNoOfProductsSelected() {
			return productSelected.getText();
		}
			
		//newly added
			public void click_closePopup_For3DModelRejected() {
				try {
					RejectedCloseButton.click();
				} catch (Exception e) {
					System.out.println("3D Model Rejected popup not found");
				
			}
		}

		public void getRejectedWorkStatus_Generic(String expected_reject_status) {
			String rejectionStatus = WorkStatusForArtistRejection.getText();
			softAsrt.assertEquals(rejectionStatus, expected_reject_status , "Expected Status is not matching ");
			System.out.println("Work status for Rejection : "+ rejectionStatus );
		}
		
		public void getRejectedWorkStatusAfterQC_Generic(String expected_reject_statusAfterQC) {
			String rejectionStatusAfterQC = WorkStatusForInternalRejection.getText();
			softAsrt.assertEquals(rejectionStatusAfterQC, expected_reject_statusAfterQC, "Expected Status is not matching in QC");
			System.out.println("Work status for Rejection : "+ rejectionStatusAfterQC );
		}
		
		public void verify_Varients() throws InterruptedException {
			
				Boolean result = varient_arrow.isDisplayed();
				if (result == true) {
					varient_arrow.click();
					Thread.sleep(2000);
					int i = 2;
					WebElement next_prdID_Line;
					int count = 0;
					int Varient;
					String Next_Line_ID;
					Boolean B1;
					do { 
						next_prdID_Line = driver.findElement(By.xpath("(//div[@class='column-text'])["+i+"]"));
						Next_Line_ID = next_prdID_Line.getText();
						B1 = Next_Line_ID.contains(getProductID());
						i=i+1;
						count = count+1;
						Varient = (count-1);
//						System.out.println(Next_Line_ID);
//						System.out.println(getProductID());
					}
					while(B1 == true);	
					System.out.println("Selected Product has "+Varient+" Variants.");
				}
				else {
					System.out.println("Selected product is without Variant.");
				}
				
			
		}
}
