package HelpAndSupportFlow;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ai.avataar.supernovaV3.base.TestBase;
import ai.avataar.supernovaV3.pages.CommonUtils;
import ai.avataar.supernovaV3.pages.HelpAndSupportGuidePage;
import ai.avataar.supernovaV3.pages.LoginPage;
import ai.avataar.supernovaV3.util.ListenersClass;
@Listeners(ListenersClass.class)
public class Guide extends TestBase {

	LoginPage loginPage;
	CommonUtils commonUtils;
	HelpAndSupportGuidePage helpAndSupportGuidePage;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		commonUtils = loginPage.login(prop.getProperty("QA_Flipzoneid"), prop.getProperty("QA_pwd"));
		helpAndSupportGuidePage = new HelpAndSupportGuidePage();
	}
	
	@Test(priority = 1)
	public void verify_DemoStoreLogin_Test() throws InterruptedException {
//		Thread.sleep(2000);
		commonUtils.click_Tenant(prop.getProperty("tenant_Name")); //can change tenant from config
//		Thread.sleep(500);
		commonUtils.mousehover_ToPageTitle("Place Order");
		commonUtils.mousehover_ToTenantAnd_SwitchStage_withoutPageTitle(prop.getProperty("mouseHoverToTenant_Name_byCustomer"), "Help & Support");
		helpAndSupportGuidePage.choose_OptionFrom_HelpAndSupport("Guide");
		commonUtils.mousehover_ToPageTitle("Guide");
		Thread.sleep(2000);
		helpAndSupportGuidePage.click_ContinueBtn();
		Thread.sleep(3000);
	}
		
	@Test(priority = 2)
	public void verify_GuideStep1() throws InterruptedException {
		String step1 = helpAndSupportGuidePage.getStep1Text();
		System.out.println(step1);
		Thread.sleep(2000);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 3)
	public void verify_GuideStep2() throws InterruptedException {
//		Thread.sleep(500);
		String step2 = helpAndSupportGuidePage.getStep2Text();
		System.out.println(step2);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 4)
	public void verify_GuideStep3() throws InterruptedException {
//		Thread.sleep(2000);
		String step3 = helpAndSupportGuidePage.getStep3Text();
		System.out.println(step3);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 5)
	public void verify_GuideStep4() throws InterruptedException {
//		Thread.sleep(2000);
		String step4 = helpAndSupportGuidePage.getStep4Text();
		System.out.println(step4);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 6)
	public void verify_GuideStep5() throws InterruptedException {
//		Thread.sleep(2000);
		String step5 = helpAndSupportGuidePage.getStep5Text();
		System.out.println(step5);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 7)
	public void verify_GuideStep6() throws InterruptedException {
//		Thread.sleep(2000);
		String step6 = helpAndSupportGuidePage.getStep6Text();
		System.out.println(step6);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 8)
	public void verify_GuideStep7() throws InterruptedException {
//		Thread.sleep(2000);
		String step7 = helpAndSupportGuidePage.getStep7Text();
		System.out.println(step7);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 9)
	public void verify_GuideStep8() throws InterruptedException {
//		Thread.sleep(2000);
		String step8 = helpAndSupportGuidePage.getStep8Text();
		System.out.println(step8);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 10)
	public void verify_GuideStep9() throws InterruptedException {
//		Thread.sleep(2000);
		String step9 = helpAndSupportGuidePage.getStep9Text();
		System.out.println(step9);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 11)
	public void verify_GuideStep10() throws InterruptedException {
//		Thread.sleep(2000);
		String step10 = helpAndSupportGuidePage.getStep10Text();
		System.out.println(step10);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 12)
	public void verify_GuideStep11() throws InterruptedException {
//		Thread.sleep(2000);
		String step11 = helpAndSupportGuidePage.getStep11Text();
		System.out.println(step11);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_NextBtn();
	}
	
	@Test(priority = 13)
	public void verify_GuideStep12() throws InterruptedException {
//		Thread.sleep(2000);
		String step12 = helpAndSupportGuidePage.getStep12Text();
		System.out.println(step12);
		Thread.sleep(500);
		helpAndSupportGuidePage.click_FinishBtn();
	}
	
	@AfterTest
	public void tear_Down() {
		driver.quit();
	}
}
