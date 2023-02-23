package ai.avataar.supernovaV3.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ai.avataar.supernovaV3.base.TestBase;

public class MyOrdersPage extends TestBase {
	
	public String Actual_PlacedOrderType;
	public String Actual_OrderStatusOfProduct;
	
	public String OrderPriceOfProduct_beforeAccepting;
	public String OrderIDOfProduct;
	String Actual_PriceOfProduct;
	
	CommonUtils commonUtils = new CommonUtils();
	ResourceReviewPage resourceReviewPage = new ResourceReviewPage();
	
	@FindBy(xpath="(//div[@class='textLink'])[1]")
	public WebElement OrderID;
	
	@FindBy(xpath="//td[@data-colindex='2']")
	public WebElement OrderType;
	
	@FindBy(xpath="//td[@data-colindex='3']")
	public WebElement OrderStatus;
	
	@FindBy(xpath="//td[@data-colindex='4']")
	public WebElement OrderPrice;
	
	@FindBy(xpath="//button[text()='Back']")
	public WebElement Back_Btn_MyOrders;
	
	@FindBy(xpath="//div[@class='image-container']//div[1]")
	public WebElement Image;
	
	//Initializing page objects:
	public MyOrdersPage() {
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}
	
	public void click_OrderID () {
		OrderID.click();
	}
	
	public void click_Back_Btn_MyOrders () {
		Back_Btn_MyOrders.click();
	}
	
	public String getOrderType () {
		return OrderType.getText();
	}
	
	/*
	public String getOrderStatus () {
		return OrderStatus.getText();
	}
	*/
	
	public void AcceptOrderInResourceReview_Test(String Productnumber) throws InterruptedException {
		commonUtils.SearchBar.sendKeys(Productnumber);
		System.out.println("product ID entered in resource review search is "+Productnumber);
		Thread.sleep(2000);
		commonUtils.click_SingleCheckbox_FirstRow();
		Thread.sleep(2000);
		resourceReviewPage.click_AcceptIcon();
		Thread.sleep(2000);
	}
	
	public void getActualOrderType_Generic(String MMorSS, Object object) throws InterruptedException {
		Actual_PlacedOrderType = OrderType.getText();
		System.out.println("Order type when store owner places " + MMorSS + "order is : " + Actual_PlacedOrderType);
		Assert.assertEquals(Actual_PlacedOrderType, object , "Order Type of Product is wrong");
	}
	
	public void getActualOrderStatus_Generic(String Event, String ExpectedStatus) throws InterruptedException {
		Actual_OrderStatusOfProduct = OrderStatus.getText();
		System.out.println("Status of the order after " + Event + " is : " + Actual_OrderStatusOfProduct);
		Assert.assertEquals(Actual_OrderStatusOfProduct, ExpectedStatus , "Order Status Of the Product is wrong");
	}
	
	public void getActualPrice_Generic(String ActionOccured, String ExpectedPrice) throws InterruptedException {
		Actual_PriceOfProduct = OrderPrice.getText();
		System.out.println("Actual price of order after " + ActionOccured + " is : " + Actual_PriceOfProduct);
		Assert.assertEquals(Actual_PriceOfProduct, ExpectedPrice , "Order Price Of the Product is wrong");
	}
	
	public String Avataar_ParentTab_Myorders;
	public String NewChildWindow_Login;
	
	public void SwitchToNewChildWindow_Login () throws Exception{
			
			Set<String> allWindows = driver.getWindowHandles();
			Iterator <String> it = allWindows.iterator();
			Avataar_ParentTab_Myorders = it.next();
			NewChildWindow_Login = it.next();
			driver.switchTo().window(NewChildWindow_Login);
			driver.get(prop.getProperty("url"));
			}

	public void SwitchToAvataar_ParentTab_Myorders() throws Throwable {
		//driver.close();
		driver.switchTo().window(Avataar_ParentTab_Myorders);
		Thread.sleep(1000);
	}

	
	
	
	
	
	
	
	
}
