package ai.avataar.supernovaV3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ai.avataar.supernovaV3.base.TestBase;

public class ThreeD_TeamAssignmentPage extends TestBase {

	@FindBy(xpath = "(//div[@class='date-input'][normalize-space()='Select'])[1]")
	private WebElement DatePicker;
		
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[27]")
	private WebElement Modelling_TeamDd_Btn;
		
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[28]")
	private WebElement Texturing_TeamDd_Btn;
		
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[29]")
	private WebElement Animation_TeamDd_Btn;
		
	@FindBy(xpath = "(//*[name()='svg'][@stroke='currentColor'])[30]")
	private WebElement QC_TeamDd_Btn;
	
	public void Select_TeamNameFrom_DD(String TeamName) {
		WebElement TeamnameDD = driver.findElement(By.xpath("//span[normalize-space()='"+TeamName+"']"));
		TeamnameDD.click();
	}
	
	//Initializing page objects:
	public ThreeD_TeamAssignmentPage() { //constructor
		PageFactory.initElements(driver, this);//this means current class variables/objects
	}

//============================Action Methods for ThreeD_TeamAssignment Page=====================================//
	
	public void click_datePicker() {
		DatePicker.click();
	}
	
	public void selectDueDate(String date) {
		//WebElement  DatePicker =driver.findElement(By.xpath("(//div[text()='"+date+"'])[2]"));
		WebElement  DatePicker =driver.findElement(By.xpath("(//div[text()='"+date+"'])")); //updated from jan2023
		DatePicker.click();
	}
	
	public void click_Modelling_TeamDd() {
		Modelling_TeamDd_Btn.click();
	}
	
	public void click_Texturing_TeamDd() {
		Texturing_TeamDd_Btn.click();
	}
	
	public void click_Animation_TeamDd() {
		Animation_TeamDd_Btn.click();
	}
	
	public void click_QC_TeamDd() {
		QC_TeamDd_Btn.click();
	}
	
	
	
	
	
}
