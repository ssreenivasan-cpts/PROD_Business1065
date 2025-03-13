/**
 * 
 */
package testcases;

import org.testng.annotations.Test;

import com.google.errorprone.annotations.Var;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.AccountSettingsPO;
import pageObjects.AddaReturnpgPO;
import pageObjects.BankAppPO;
import pageObjects.BillingSetupPO;
import pageObjects.FRM1040PO;
import pageObjects.FRM1099_MISCPO;
import pageObjects.FRM8867PO;
import pageObjects.FRM8879PO;
import pageObjects.FRMSCHK1_1065PO;
import pageObjects.FRMSCH_CPO;
import pageObjects.FRMSCH_EPO;
import pageObjects.FRMSCH_FPO;
import pageObjects.FRMW2PO;
import pageObjects.InterviewModePO;
import pageObjects.LogincloPO;
import pageObjects.OverviewpgPO;
import pageObjects.TaxreturnBarPO;
import pageObjects.addaforminaReturnPO;
import resources.Base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.bson.Document;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//@Listeners(Listener.class)
/**
 * @author ssreenivasan
 */
public class AddaNewReturn extends Base {

	public WebDriver driver;
	Logger logger = LogManager.getLogger(getClass());
	// public Actions action = new Actions(driver);

	public void generateSSN(String env,String busType) throws IOException, InterruptedException {
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		//Go to Business tab in 2023
		//String busType;
		OverviewpgPO op = new OverviewpgPO(driver);
		ap.clkCloseNewRtrnbtn().click();
		op.clickBusinesstabinprep().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		op.clickNewTaxreturnsbtn().click();
		
		//sheet = getCellData();
		
		// *********** creates SSN************
		int aEINnum = 0;
		int bEINnum = 0; 
		aEINnum = (int) ((Math.random() * 70) + 10);
		bEINnum = (int) ((Math.random() * 90000000) + 1000);
		if (aEINnum < 011) {
			aEINnum = (int) ((Math.random() * 70) + 100);
		}
		op.getBus_newreturn_ein().sendKeys(String.valueOf(aEINnum) + String.valueOf(bEINnum));
		op.getBus_newreturn_confirmein().sendKeys(String.valueOf(aEINnum) + String.valueOf(bEINnum));
		
		if(busType.equals("1"))
			op.clickBus_newreturn_radiobtn1065().click();
		else if(busType.equals("2"))
			op.clickBus_newreturn_radiobtn1120s().click();
		else
			op.clickBus_newreurn_radiobtn1120().click();
		op.clickBus_newreturn_Create().click();
		test.log(LogStatus.INFO, "Entered SSN");
		logger.info("SSN taken");
		logger.info("New Taxreturn Opened");

	}
	 
	public void changeto23(String env,String year) throws InterruptedException, IOException
	{
		
		
		  Reporter.log("opened a preparer"); Reporter.log("<br>");
		  test.log(LogStatus.INFO, "opened a preparer"); AddaReturnpgPO ap = new
		  AddaReturnpgPO(driver); ap.clkCloseNewRtrnbtn().click(); 
		  switchYear(year);
		  OverviewpgPO op = new OverviewpgPO(driver);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  Thread.sleep(1000); op.clickdashboardBtn().click(); openaPreparer(env, 334);
		 
	}

	public void addDOB() throws InterruptedException {

		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.getdob().click();
		Thread.sleep(1000);
		ap.getdob().sendKeys("05051987");
		Thread.sleep(2000);
		ap.getremotesignConsent().sendKeys("Y");
		ap.clickinfoandstatusbtn().click();
		ap.clicksigned8879().sendKeys("X"); /// TY2021
		

	}

	public void addCDS(String env, String year)
			throws IOException, InterruptedException {

		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		// Wait.until(ExpectedConditions.elementToBeClickable(ap.clickClientdatabtn()));
		Thread.sleep(2000); // changed for FF
		// WebDriverWait Wait2 = new WebDriverWait(driver,20);
		// Wait2.until(ExpectedConditions.elementToBeClickable(ap.clickClientdatabtn()));
		Boolean bool = true;
		while (bool) {
			try {
				ap.clickClientdatabtn().click();
				bool = false;
			} catch (Exception e) {
				bool = true;
			}
		}
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "Entering CDS info");
		ap.clickbusinessname().sendKeys("1065 Automation Return");
		ap.clickofficeorpatner1().sendKeys("qa");
		//Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		ap.clickofficeorpatner2().sendKeys("partner");
		//Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		ap.clickofficephone().sendKeys("6103088236");
		Thread.sleep(500);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// ap.getcellph2().clear();
		ap.clicktitle().sendKeys("MANAGER");
		Thread.sleep(500);
		ap.clickbiz_ssn().sendKeys("232232301");
		ap.clickbiz_email().sendKeys("ssreenivasan@crosslinktax.com");
		ap.gettextmsg().sendKeys("X");
		ap.gettextmsg().sendKeys(Keys.TAB);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clkagreeForTextmsg().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		Thread.sleep(500);
		AltplusC("cellphcarrier", ap.getcellphcarrier());
		Thread.sleep(500);
		ap.clickbiz_remoteconsent().sendKeys("y");
		ap.getusaddress().sendKeys("18722 silver way");
		Thread.sleep(1000);
		AltplusC("zipcode", ap.getzip());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,250);");
		int year_int = Integer.parseInt(year); // convert year string to Int
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_stateoforg().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_stateoforg().sendKeys("CA");
		Thread.sleep(500);
		ap.clickbiz_dateorganized().sendKeys("01012022");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		//AltplusC("bizacitvity",ap.clickbiz_principalbizactivity());
		ap.clickbiz_fiscalyrbeggining().sendKeys("01012024");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		ap.clickbiz_naicscode().click();
		AltplusC("naicscode",ap.clickbiz_naicscode());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(500);
		ap.clickbiz_fiscalyrend().sendKeys("09012024");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_principalproduct().sendKeys("fruits");
		Thread.sleep(2000);
		//AltplusC("servicecentre",ap.clickbiz_servicecentrefiled());
		ap.clickbiz_accounting_cash().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_accoutingdetail().sendKeys("X"); //taxbasis
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_Initialreturn_checkbox().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_useofficersnamebox().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_useentitysaddress().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_boicheckbox().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_answernotoallquessbox().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_signingofficersssn().sendKeys("234232302");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_filinglicencetype().sendKeys("P");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_priorincomewas0().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_parentcompdeetsdonotapply().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_additionalformsdonotapply().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_nopaymentsmadethisyear().sendKeys("X");
		Thread.sleep(1000);
		/*
		 * ap.clickbiz_form8879for1120s().click();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 * ap.clickbiz_EROPIN().sendKeys("11111");
		 */
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb = new TaxreturnBarPO(driver);
		tb.clkbtnSaveTaxReturn().click();
	}
	
	public void addfinancialIncomestatement()
	{
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickbiz_form_financialincomestatement().click();
		ap.clickbiz_grossreceipts().sendKeys("1000");
		ap.click_bizexpenses_9b().sendKeys("1000");
		ap.clickbiz_expenses_19().sendKeys("1000");
		Reporter.log("ADDED FINANCIAL INCOME STATEMENT"); 
		Reporter.log("<br>");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		
	    tb.clkbtnSaveTaxReturn().click();
	}
	
	public void addfinancialbalancesheet()
	{
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickbiz_form_financialBalancesheet().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_assets_endofyear23_1().sendKeys("500");
		ap.clickbiz_liabilities_accountspayable2023_14().sendKeys("500");
		 Reporter.log("ADDED BALANCE SHEET"); 
		    Reporter.log("<br>");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
	    tb.clkbtnSaveTaxReturn().click();
		
	}
	public void sch1065form() throws InterruptedException
	{
		test.log(LogStatus.INFO, "starting 1065 in Business");
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickform1065inFederal().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getqbi_1_1065().sendKeys("Q");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getschB_1a_1065().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getschB_4d_1065().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getschB_31_1065().sendKeys("X");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		ap.getnameofPR().sendKeys("PR name");
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  tb.clkbtnSaveTaxReturn().click();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getPRaddress().sendKeys("233 vangosh st");
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  tb.clkbtnSaveTaxReturn().click();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getPRzipcode().click();
		Thread.sleep(500);
		AltplusC("zipcode",ap.getPRzipcode());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getPRphonenumber().sendKeys("2562588585");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		tb.clkbtnSaveTaxReturn().click();
		 Reporter.log("COMPLETED SCH 1065 FORM"); 
		    Reporter.log("<br>");
		ap.clickIRSPartyinfoonfile_Yes().sendKeys("X");
	    tb.clkbtnSaveTaxReturn().click();
	    Thread.sleep(500);
		
     }
	public void addformk1_one() throws InterruptedException
	{
		test.log(LogStatus.INFO, "starting k1 in Business");
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickbiz_schk1infederal().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_PartnerEIN_E().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ADD));
		Thread.sleep(500);
        ap.clickbiz_PartnerEIN_E().sendKeys("215555256");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ap.clickbiz_partnerBizname_F().click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(1000);
        ap.clickbiz_partnerBizname_F().sendKeys("MY BUSINESS");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_partnerBizname_F().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_partnerAddress().sendKeys("345 master st");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickpartneraddressZipcode().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		AltplusC("zipcode",ap.clickpartneraddressZipcode());
		ap.clickbiz_generalPartner_G().sendKeys("X");
		ap.clickbiz_domesticPatner_H1().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_typeofentitypartner().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(500);
		AltplusC("entity1",ap.clickbiz_typeofentitypartner());
		ap.clickbiz_begginingProfit_j().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_begginingProfit_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_begginingLoss_j().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_begginingLoss_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_begginingCapital_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.getroundingamount_lineJ_k1().sendKeys("x");
		Thread.sleep(500);
		ap.clicklineM_k1_No().sendKeys("X");
	}
	
	public void addformk1_two() throws InterruptedException
	{
		test.log(LogStatus.INFO, "starting second k1 in Business");
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickbiz_form_k1manager().click();
		Thread.sleep(500);
		
		java.util.List<WebElement> addbtnink1mgr = driver.findElements(By.id("btnAdd"));
		addbtnink1mgr.get(1).click();
		Thread.sleep(1000);
		//ap.clickpatnerrepresentative_k1().sendKeys("X");
        ap.clickpartnerssn_k1().sendKeys("4545430002");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickpartnerfirstname_k1().sendKeys("second");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(500);
		ap.clickpartnerlastname_k1().sendKeys("partner");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_partnerAddress().sendKeys("451 second st");
		Thread.sleep(500);
		ap.clickpartneraddressZipcode().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    AltplusC("zipcode",ap.clickpartneraddressZipcode());
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_generalPartner_G().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_domesticPatner_H1().sendKeys("X");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ap.clickbiz_typeofentitypartner().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AltplusC("entity",ap.clickbiz_typeofentitypartner());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(500);
		ap.clickbiz_begginingProfit_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickbiz_begginingLoss_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(500);
		ap.clickbiz_begginingCapital_j().sendKeys("50");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.getroundingamount_lineJ_k1().sendKeys("x");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(500);
		ap.clicklineM_k1_No().sendKeys("X");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb.clkbtnSaveTaxReturn().click();
	}

	

	public void frm8879(String env, String refundtype) throws IOException, InterruptedException {
		Reporter.log("frm8879");
		

		
	}

	

	public void verifyareturn(String env) throws IOException, InterruptedException {
		try {
			TaxreturnBarPO tb = new TaxreturnBarPO(driver);
			Thread.sleep(1000);
			test.log(LogStatus.INFO, "in Verify a return");
			Reporter.log("verifyareturn-5");
			tb.clkbtnSaveTaxReturn().click();
			tb.clkverifyReturnbtn().click();
			Thread.sleep(1000);
			try {
				if (tb.clkOKinVerifysuccessbtn().isDisplayed()) {
					System.out.println("Verify success");
					/*
					 * String path = TakeScreenshot(); String imagePath =
					 * test.addScreenCapture(path); test.log(LogStatus.PASS, "Took screen shot",
					 * imagePath);
					 */
					File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screenshotFile, new File("./reports/verifyerror.png"));
					String path = "verifyerror.png";
					// String imagePath = test.addScreenCapture(path);
					logger.info("screen shot taken");
					test.log(LogStatus.PASS, "Took screen shot", test.addScreenCapture(path));
	
				}
			} catch (Exception e) {
				System.out.println("Verified with errors");
			}
			if (tb.clkOKinVerifysuccessbtn().isDisplayed()) {
				tb.clkOKinVerifysuccessbtn().click();
	
				tb.clkbtnArrowOptions().click();
				tb.clkbtnSaveTaxReturn().click();
			} else
				tb.clkcloseinverify().click();
			tb.clkbtnArrowOptions().click();
			tb.clkbtnSaveTaxReturn().click(); // change after stale elm exception
			// Thread.sleep(1000);
			/*
			 * Wait.until(ExpectedConditions.elementToBeClickable(tb.clkbtnSaveTaxReturn()))
			 * ; tb.clkbtnSaveTaxReturn().click();
			 * 
			 * tb.clkbtnArrowOptions().click();
			 * //driver.findElement(By.id("btnOptionsHeaderBarForms")).click();
			 * Thread.sleep(1000); tb.clkbtnsaveinarrow().click();
			 */
			// driver.findElement(By.xpath("//li[@id='menuOptions9']")).click(); /// save a
			// return btn
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("Error in Addareturn testcase" + e);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("C:\\CLO-Errors screenshots\\error10.png"));
		}
	
	}

	public void addAssets() throws InterruptedException {
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clicktabDepreciation().click();
		Thread.sleep(2000);
		ap.clickbtnAddAsset().click();
		addaforminaReturnPO af = new addaforminaReturnPO(driver);
		/*
		 * Actions action = new Actions(driver);
		 * action.doubleClick(af.clkcartruck()).perform();
		 */
		af.clkcartruck().click();
		af.clkAddbtnAddanewform().click();

		ap.clickaddAssetDesc().clear();
		ap.clickaddAssetDesc().sendKeys("PORCHE");
		Thread.sleep(500);
		ap.clickaddAssetDate().clear();
		ap.clickaddAssetDate().sendKeys("01012019");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ap.clickgasPoweredAuto().click();
		ap.clickaddbtninAddAsset().click();
		// Wait.until(ExpectedConditions.elementToBeClickable(ap.getoriginalCost()));
		ap.getoriginalCost().sendKeys("25000");
		ap.getvehiclePersUse_Y().sendKeys("X");
		ap.getvehiclemorethan5_Y().sendKeys("X");
		ap.getisAnotherVehicle_Y().sendKeys("X");
		// Wait.until(ExpectedConditions.textToBePresentInElement(ap.getvehiclemorethan5_Y(),
		// "X"));
		// Wait.until(ExpectedConditions.textToBePresentInElement(ap.getvehiclePersUse_Y(),
		// "X"));
		ap.getownthisVehicle_y().sendKeys("X");
		// Wait.until(ExpectedConditions.textToBePresentInElement(ap.getownthisVehicle_y(),
		// "X"));
		ap.getforceActExpense_N().sendKeys("X");
		// Wait.until(ExpectedConditions.textToBePresentInElement(ap.getvehiclePersUse_Y(),
		// "X"));
		ap.getforceStndMileage_N().sendKeys("X");
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Thread.sleep(1000);
		// Wait.until(ExpectedConditions.textToBePresentInElement(ap.getforceStndMileage_N(),
		// "X"));
		// ap.getACRSinprevYear_N().sendKeys("X");
		Thread.sleep(500);
		ap.clickbtnClose().click();
		test.log(LogStatus.INFO, "Asset Added");
		ap.clickcarTruckVehAlloc().click();
	}

	@Parameters({ "env", "year" })
	// @BeforeTest
	@BeforeMethod
	public void login(String env, String year) throws IOException, InterruptedException {
		driver = invokeBrowser(env);
		LogincloPO lp = new LogincloPO(driver);
		// test = report.startTest("add a return");
		if (env.equalsIgnoreCase("qa")) {
			driver.get(prop.getProperty("url"));
			// ("https://qa.crosslinkonline.com/#");
			String mwh = driver.getWindowHandle();
			lp.getusername().clear();
			Thread.sleep(500);
			lp.getusername().sendKeys(prop.getProperty("username"));
			lp.getPassword().sendKeys(prop.getProperty("password"));
			lp.clickLogin().click();
			// changepasspopup
			Thread.sleep(1000);
			Set s = driver.getWindowHandles();
			Iterator ite = s.iterator();
			System.out.println(s);
			System.out.println(mwh);
			while (ite.hasNext()) {
				String popupHandle = ite.next().toString();
				System.out.println(popupHandle);
				if (!popupHandle.contains(mwh)) {
					driver.switchTo().window(popupHandle);
					lp.clickpwdexpireCancelbtn().click();
					logger.info("Username and Password success");
					Reporter.log("reporter working");
					Thread.sleep(1000);
					driver.switchTo().window(mwh);
				}

				Thread.sleep(1000);
				lp.clicksecLogin().click();
				// lp.clickmarkaspublic().click();
				lp.clickconfirmationkey().sendKeys("abcde");
			}
		}

		/*
		 * else if (env.equalsIgnoreCase("dev")) {
		 * driver.get("https://dev.crosslinkonline.com/#");
		 * lp.getusername().sendKeys(prop.getProperty("username"));
		 * lp.getPassword().sendKeys(prop.getProperty("devssbpassword"));
		 * lp.clickLogin().click(); logger.info("Username and Password success");
		 * //...Wait.until(ExpectedConditions.elementToBeClickable(lp.clicksecLogin()));
		 * Thread.sleep(1000); lp.clicksecLogin().click();
		 * lp.clickmarkaspublic().click(); }
		 */ else if (env.equalsIgnoreCase("prod")) {
			driver.get(prop.getProperty("url"));
			lp.getusername().clear();
			lp.getusername().sendKeys(prop.getProperty("username"));
			lp.getPassword().sendKeys(prop.getProperty("password"));
			lp.clickrememberPassword().click();
			lp.clickLogin().click();
			logger.info("Username and Password success");
			// Wait.until(ExpectedConditions.elementToBeClickable(lp.clicksecLogin()));
			Thread.sleep(1000);
			lp.clicksecLogin().click();
			Thread.sleep(500);
			// lp.clickmarkaspublic().click();
			// lp.getdeviceLabel().sendKeys("testing Pc");// or testing Pc1
			WebElement confirmkey = driver.findElement(By.id("txtConfirmKey"));
			// By.xpath("//*[@id=\"mfaEnterCodeContainer\"]/div/div/div[2]/form/div[2]/div/input"));

			String otpKeyStr = prop.getProperty("1700011_secretkey");
			Totp totp = new Totp(otpKeyStr);
			String twoFcode = totp.now();
			confirmkey.sendKeys(twoFcode);
			// lp.clickthrdcontbtn().click();
			// lp.clickcontbtnIRS().click();
			// lp.clickcanceltour().click();
			logger.info("In OverView Page");
		} else if (env.equalsIgnoreCase("prod1")) {
			driver.get("https://crosslinkonline.com/");
			lp.getusername().clear();
			lp.getusername().sendKeys("NAHMIC");
			lp.getPassword().sendKeys("P@ssword8");
			lp.clickrememberPassword().click();
			lp.clickLogin().click();
			logger.info("Username and Password success");
			Thread.sleep(1000);
			lp.clicksecLogin().click();
			Thread.sleep(500);
			// lp.clickmarkaspublic().click();
			WebElement confirmkey = driver.findElement(By.id("txtConfirmKey"));
			String otpKeyStr = prop.getProperty("NAHMIC_secretkey");
			Totp totp = new Totp(otpKeyStr);
			String twoFcode = totp.now();
			confirmkey.sendKeys(twoFcode);
			// lp.clickthrdcontbtn().click();
			// lp.clickcontbtnIRS().click();
			// lp.clickcanceltour().click();
			logger.info("In OverView Page");

		}
		logger.info("URL open-Success");
		lp.clickthrdcontbtn().click();
		// lp.clickcontbtnIRS().click();
		// lp.clickcanceltour().click();
		// logger.info("In OverView Page");

	}

	public void switchYear(String year) throws IOException, InterruptedException {

		AccountSettingsPO ap = new AccountSettingsPO(driver);
		Wait = new WebDriverWait(driver,Duration.ofSeconds(20));
				// WebDriverWait(driver, 20);
		Wait.until(ExpectedConditions.elementToBeClickable(ap.clickaccountsdropdown()));
		ap.clickaccountsdropdown().click();
		Wait.until(ExpectedConditions.elementToBeClickable(ap.clickswitchlink()));
		ap.clickswitchlink().click();
		Wait.until(ExpectedConditions.elementToBeClickable(ap.clickswitchselectyeardropdown()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		//ap.clickswitchselectyeardropdown().click();
	//	Select se = new Select(driver.findElement(By.xpath("//Select[@name = 'season']")));
				//By.xpath("//option[@value = '2024'))")));
		//se.selectByVisibleText("2021 Tax year");
		//ap.clickswitchoptionyear2020().click();
		 Thread.sleep(2000);
		
			/*
			 * ap.clickswtichoptionyear2023().click(); switch (Integer.valueOf(year)) {
			 * 
			 * case 2017: ap.clickswitchoptionyear2017().click();
			 * logger.info("switched to the year 2017"); test.log(LogStatus.INFO,
			 * "switched to the year 2017"); break; case 2018:
			 * ap.clickswitchoptionyear2018().click();
			 * logger.info("switched to the year 2018"); test.log(LogStatus.INFO,
			 * "switched to the year 2018"); break; case 2019:
			 * ap.clickswitchoptionyear2019().click();
			 * logger.info("switched to the year 2019"); test.log(LogStatus.INFO,
			 * "switched to the year 2019"); break; case 2020:
			 * ap.clickswitchoptionyear2020().click();
			 * logger.info("switched to the year 2020"); test.log(LogStatus.INFO,
			 * "switched to the year 2020"); break; case 2021:
			 * ap.clickswitchoptionyear2021().click();
			 * logger.info("switched to the year 2020"); test.log(LogStatus.INFO,
			 * "switched to the year 2021"); break; case 2022:
			 * ap.clickswitchoptionyear2022().click();
			 * logger.info("switched to the year 2020"); test.log(LogStatus.INFO,
			 * "switched to the year 2022"); break; case 2023:
			 * ap.clickswtichoptionyear2023().click();
			 * logger.info("switched to the year 2020"); test.log(LogStatus.INFO,
			 * "switched to the year 2023"); break; }
			 */

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ap.clickswitchbtn().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Parameters("env")
	@AfterMethod(alwaysRun = true)
	public void closeBrowser(String env) throws InterruptedException {
		logger.info("in tear down");
		if (driver != null) {
			//driver.close();
		}
	}

	/*
	 * @Parameters("env")
	 * 
	 * @AfterMethod(alwaysRun = true) public void teardown(String env) throws
	 * InterruptedException { logger.info("in tear down"); if(driver != null ) {
	 * //driver.close(); // driver.quit(); }
	 * 
	 * // report.endTest(test); report.flush();
	 * 
	 * } }
	 */

	// @Test(priority = 3, dependsOnMethods = "addareturn")
	public void scheduleC(String year) throws InterruptedException, IOException {
		try {
			System.out.println("SCHEDULE C");
			AddaReturnpgPO ap = new AddaReturnpgPO(driver);
			Thread.sleep(1000);
			ap.clickaddaform().click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			addaforminaReturnPO af = new addaforminaReturnPO(driver);
			af.clksearchAForm().click();
			// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			af.clksearchAForm().sendKeys("sch c");
			Actions action = new Actions(driver);
			action.doubleClick(af.clkfrmschC()).perform();
			// af.clkfrmschC().click();
			// af.clkAddbtnAddanewform().click();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// TaxreturnBarPO tb = new TaxreturnBarPO(driver);
			FRMSCH_CPO schc = new FRMSCH_CPO(driver);
			logger.info("ADDED FORM SCH-C");
			test.log(LogStatus.INFO, "ADDED FORM SCH-C");
			schc.getA().clear();
			schc.getA().sendKeys("cafe");
			System.out.println(schc.getA().getLocation());
			AltplusC("schc_bizcode", schc.getB());
			schc.getE_Bizaddress().sendKeys("12 z st");
			AltplusC("zipcode", schc.getE_Zip());
			schc.getF_Cash().sendKeys("X");
			schc.getI_No().sendKeys("X");
			if (year != "2017") {
				schc.getQualbizIndicator().sendKeys("Q");
			}
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0,250);");
			schc.getgrossSales().sendKeys("2000");
			schc.getutilities().sendKeys("2000");
			logger.info("SCH-C SUCCESS");
			TaxreturnBarPO tb = new TaxreturnBarPO(driver);
			tb.clkbtnSaveTaxReturn().click();

		} catch (Exception e) {
			logger.error("Error in SCH C " + e);
			test.log(LogStatus.ERROR, "Error in SCH C");
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("C:\\CLO-Errors screenshots\\error2.png"));
		}

	}

	// @Test(priority = 4, dependsOnMethods = { "addareturn", "scheduleC" })
	public void scheduleE(String year) throws InterruptedException, IOException {
		try {
			System.out.println("SCHEDULE E");
			AddaReturnpgPO ap = new AddaReturnpgPO(driver);
			Thread.sleep(1000);
			ap.clickaddaform().click();
			addaforminaReturnPO af = new addaforminaReturnPO(driver);
			af.clksearchAForm().sendKeys("sch e");
			af.clkfrmschE().click();
			af.clkAddbtnAddanewform().click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			FRMSCH_EPO sche = new FRMSCH_EPO(driver);
			sche.getpayments_Question().sendKeys("X");
			sche.getA_streetaddr().sendKeys("87 bolsom ct");
			logger.info("ADDED FORM SCH-E");
			test.log(LogStatus.INFO, "ADDED FORM SCH-E");
			AltplusC("zipcode", sche.getA_zip());
			// sche.getA_zip().sendKeys("95330");
			AltplusC("proptype", sche.getA_proptype());
			sche.getA_numofdays().sendKeys("365");
			if (year != "2017") {
				sche.getA_QBI().sendKeys("N");
			}
			// sche.getMortInterest().sendKeys("15000");
			sche.getRepairs().sendKeys("15000");
			TaxreturnBarPO tb = new TaxreturnBarPO(driver);
			tb.clkbtnSaveTaxReturn().click();
			logger.info("FRM SCH-E SUCCESS");
		}

		catch (Exception e) {
			System.out.println("ERror " + e);

			/*
			 * logger.error("Error in SCH E " + e); test.log(LogStatus.ERROR,
			 * "Error in SCH E"); File screenshotFile = ((TakesScreenshot)
			 * driver).getScreenshotAs(OutputType.FILE);
			 */
			// FileUtils.copyFile(screenshotFile, new File("C:\\CLO-Errors
			// screenshots\\errorInSCH-E.png"));
		}

	}

	// @Test(priority = 5, dependsOnMethods = { "addareturn", "scheduleC",
	// "scheduleE" })
	public void frm1099_MISC() throws InterruptedException, IOException {
		try {
			System.out.println("1099MISC");
			AddaReturnpgPO ap = new AddaReturnpgPO(driver);
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Thread.sleep(1000);
			// Wait.until(ExpectedConditions.elementToBeClickable(ap.clickaddaform()));//
			// changed from thread.sleep
			ap.clickaddaform().click();
			addaforminaReturnPO af = new addaforminaReturnPO(driver);
			af.clksearchAForm().sendKeys("1099-MIS");
			af.clkfrm1099MISC().click();
			af.clkAddbtnAddanewform().click();
			FRM1099_MISCPO obj1 = new FRM1099_MISCPO(driver);
			AltplusC("payersEIN", obj1.getpayersEIN());
			logger.info("ADDED FORM 1099-MISC");
			test.log(LogStatus.INFO, "ADDED FORM 1099-MISC");
			obj1.getrents().sendKeys("15000");
			AltplusC("linkto", obj1.getlinksTo());
			TaxreturnBarPO tb = new TaxreturnBarPO(driver);
			tb.clkbtnSaveTaxReturn().click();
			// tb.clkbtnSaveAndClose().click();
			Thread.sleep(1000);
			logger.info("1099MISC SUCCESS");
		} catch (Exception e) {
			// logger.error("Error in SCH 1099-MISC " + e);
			/*
			 * test.log(LogStatus.ERROR, "Error in SCH 1099-MISC"); File screenshotFile =
			 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			 * FileUtils.copyFile(screenshotFile, new
			 * File("C:\\CLO-Errors screenshots\\errorIn1099MISC.png"));
			 */
		}

	}

	// @Test(priority = 6 )//,dependsOnMethods = { "addareturn", "scheduleC",
	// "scheduleE", "frm1099_MISC" }
	public void printTaxReturn() throws InterruptedException, IOException {
		System.out.println("PRINT FINAL TAX RETURN");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		Thread.sleep(1000);
		tb.clkprintbtn().click();
		logger.info("clicked print button");
		test.log(LogStatus.INFO, "PRINT FINAL TAX RETURN");
		// Wait.until(ExpectedConditions.elementToBeClickable(tb.clkfinalTaxReturnbtn()));
		// // changed from thread.sleep
		Thread.sleep(500);
		tb.clkfinalTaxReturnbtn().click();
		// logger.info("clicked final tax return button");
		// Wait.until(ExpectedConditions.elementToBeClickable(tb.clkprintInVerifybtn()));
		// // changed from thread.sleep
		Assert.assertTrue(isElementPresent(tb.clkprintInVerifybtn()), "Print button disabled"); // changed from
																								// thread.sleep
		tb.clkprintInVerifybtn().click();
		Thread.sleep(1000);
		tb.clkprintBankDocsbtn().click();
		Thread.sleep(1000);
		String mwh = driver.getWindowHandle();
		Set s = driver.getWindowHandles();
		System.out.println(s);
		System.out.println(s);
		tb.clkclosebtninPDFdoc().click();
		// Iterator ite=s.iterator();
		// while(ite.hasNext())
		/*
		 * for(String handle: driver.getWindowHandles()) { String popupHandle =
		 * ite.next().toString(); //if(!popupHandle.contains(mwh)) //{
		 * //driver.switchTo().window(popupHandle); driver.switchTo().window(handle);
		 * TakeScreenshot(); //driver.switchTo().window(mwh); //} }
		 */
		// driver.switchTo()
		/*
		 * for(String handle: driver.getWindowHandles()) { Thread.sleep(1000);
		 * driver.switchTo().window(handle); }
		 */
		// TakeScreenshot();
		Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(sc.getImage(), "PNG", new File(System.getProperty("user.dir") + "\\reports\\" + "new.png"));
		tb.clkbtnSaveTaxReturn().click();
		// logger.info("save and closing the tax return");
		test.log(LogStatus.INFO, "save and closing the tax return");

		/*
		 * } catch (Exception e) { logger.error("Error in PRINT TAX RETURN " + e);
		 * test.log(LogStatus.ERROR, "Error in PRINT TAX RETURN"); File screenshotFile =
		 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(screenshotFile, new
		 * File("C:\\CLO-Errors screenshots\\errorInPrintTaxRtrn.png")); }
		 */

	}

	public void addSchF(String env) throws InterruptedException, IOException {

		System.out.println("SCHF");
		FRMSCH_FPO schf = new FRMSCH_FPO(driver);
		AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		ap.clickaddaform().click();
		addaforminaReturnPO af = new addaforminaReturnPO(driver);
		af.clksearchAForm().sendKeys("sch f");
		af.clkfrmSchF().click();
		af.clkAddbtnAddanewform().click();
		schf.getactivity_A().clear();
		schf.getactivity_A().sendKeys("farming");
		AltplusC("activitycode", schf.getactivitycode_B());
		schf.getcash_C().sendKeys("X");
		schf.getE_yes().sendKeys("X");
		schf.getF_No().sendKeys("X");
		schf.getQBIindicator().sendKeys("N");
		schf.getpart1_1a().sendKeys("2000");
		schf.getpart1_1b().sendKeys("1000");
		schf.getpart11_28().sendKeys("1000");

	}

	public void frm1040() throws InterruptedException {
		FRM1040PO obj1 = new FRM1040PO(driver);
		Thread.sleep(1000);
		// Wait.until(ExpectedConditions.elementToBeClickable(obj1.clkFrm1040btn()));
		Boolean bool = true;
		while (bool) {
			try {

				obj1.clkFrm1040btn().click();
				bool = false;
			} catch (Exception e) {
				Thread.sleep(1000);
				obj1.clkFrm1040btn().click();
				bool = true;
			}
		}
		Thread.sleep(1000);
		obj1.getvirtualcurrency_No().sendKeys("X");
		// obj1.clkarrownear1040btn().click();
		// obj1.clkrecoveryrebatesheettbn().click();
		obj1.clkbox30infrom1040().click();
		obj1.clkthreedotsnearbox30().click();
		Thread.sleep(500);
		obj1.clkworksheetbtnin3dots().click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		obj1.clknostimuluspaymntcheckbx1().sendKeys("X");
		// obj1.clknostimuluspaymntcheckbx2().sendKeys("X");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb.clkbtnSaveTaxReturn().click();

	}

	@Parameters({ "env", "year" })
	//@Test(groups= {"training"})
	final void trainingModeReturn(String env, String year) throws IOException, InterruptedException {
		// test= report.startTest("TRAINING MODE RETURN");
		test.log(LogStatus.INFO, "STARTING ~ TRAINING MODE RETURN");
		System.out.println("training mode ");
		AccountSettingsPO ap = new AccountSettingsPO(driver);
		AddaReturnpgPO ar = new AddaReturnpgPO(driver);
		// Wait.until(ExpectedConditions.elementToBeClickable(ap.clickaccountsdropdown()));
		Thread.sleep(2000);
		openaPreparer(env, 334);
		changeto23(env,year);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Wait.until(ExpectedConditions.(ar.clkCloseNewRtrnbtn());
		Thread.sleep(1000);
		ar.clkCloseNewRtrnbtn().click();
		ap.clickaccountsdropdown().click();
		// Wait.until(ExpectedConditions.elementToBeClickable(ap.clicktoggletrainingmodelink()));
		Thread.sleep(2000);
		ap.clicktoggletrainingmodelink().click();
		OverviewpgPO op = new OverviewpgPO(driver);
		op.clickoffices().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("scrollTo(0,4500);");
		Thread.sleep(1000);
		op.clickviewbtnfor334().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		op.clickpreparertab().click();
		Thread.sleep(1000);
		op.clickviewprepbtn().click();
		op.clickTaxreturns().click();
		Thread.sleep(1000);
		op.clickNewTaxreturnsbtn().click();
	//	generateSSN(env);
		//addCDS(env, year, "trainingmode", "1");
		addDOB();
		//addW2(env, "single");
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		Thread.sleep(1000);
		// tb.clkbtnSaveTaxReturn().click();
		tb.clkbtnArrowOptions().click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		//---------------COPYTILIVE--------------------------------------
		ap.clickcopytolivelink().click();
		// String promptmsg = ap.clickcopiedsuccessfullyPromptmsg().getText();
		// Assert.assertEquals(promptmsg,"Tax return copied to live Database");
		// sendTextmsg();
		Thread.sleep(500);
		tb.clkbtnArrowOptions().click();
		tb.clkbtnSaveAndClose().click();
		// Wait.until(ExpectedConditions.elementToBeClickable(ap.clickaccountsdropdown()));
		Thread.sleep(500);
		ap.clickaccountsdropdown().click();
		ap.clicktoggletrainingmodelink().click();

	}

	public void sendTextmsg() throws InterruptedException {
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb.clkbtnArrowOptions().click();
		Thread.sleep(500);
		tb.clkbtnSendTxtMsg().click();
		Thread.sleep(1000);
		WebElement dummy = driver.findElement(By.id("txtTextLinkCellNumber"));
		dummy.click();
		tb.gettxtmsgcontent().click();
		tb.gettxtmsgcontent().sendKeys("AUTOMATION TEXT MSG");
		tb.clkSendbtninTextmsg().click();
		tb.clkOkinTextmsgSuccessbtn().click();
	}

	@Parameters({ "env", "year" })
//	@Test
	final void addaReturn(String env, String year) throws IOException, InterruptedException {

		// test= report.startTest("ADD A NEW RETURN");
		test.log(LogStatus.INFO, "STARTING ADD A RETURN");
		Reporter.log("STARTING ADDARETURN");
		Reporter.log("<br>");
		openaPreparer(env, 334);
		changeto23(env,year);
	    Reporter.log("opened a preparer"); 
	    Reporter.log("<br>");
	    test.log(LogStatus.INFO, "opened a preparer"); 
		//generateSSN(env);
		//addCDS(env, year, "allforms", "1");
		addDOB();
	//addW2(env, "single");
		frm8879(env, "1");
		scheduleC(year);
		test.log(LogStatus.INFO, "sch-C success");
		scheduleE(year);
		test.log(LogStatus.INFO, "sch-E success");
		frm1099_MISC();
		test.log(LogStatus.INFO, "FRM1099MISC success");
		frm1040();
		verifyareturn(env);
		test.log(LogStatus.INFO, "RETURN VERIFIED");
		printTaxReturn();
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		Thread.sleep(500);
		// send text
		/*
		 * tb.clkbtnArrowOptions().click(); Thread.sleep(500);
		 * tb.clkbtnSendTxtMsg().click(); Thread.sleep(1000); WebElement dummy=
		 * driver.findElement(By.id("txtTextLinkCellNumber")); dummy.click();
		 * tb.gettxtmsgcontent().click();
		 * tb.gettxtmsgcontent().sendKeys("AUTOMATION TEXT MSG");
		 * tb.clkSendbtninTextmsg().click(); tb.clkOkinTextmsgSuccessbtn().click();
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * Thread.sleep(1000);
		 */
		tb.clksignatureBtn().click();
		Thread.sleep(500);
		tb.clksignBtn().click();
		Thread.sleep(500);
		tb.clksignbtninReturnErrors().click();
		Thread.sleep(500);
		// Wait.until(ExpectedConditions.elementToBeClickable(tb.clksendBtninCaptureSignature()));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		tb.clksendBtninCaptureSignature().click();
		Thread.sleep(500);
		tb.clkcancelBtn().click();
		Thread.sleep(500);
		tb.clksignatureBtn().click();
		Thread.sleep(500);
		tb.clksignBtn().click();
		// Thread.sleep(500);
		// tb.clksignbtninReturnErrors().click();
		Thread.sleep(500);
		tb.clksignoptionDropdown().click();
		Select signoption = new Select(tb.clksignMethodDropdown());
		signoption.selectByValue("Remote Signature");
		Thread.sleep(1000);
		tb.clkEmailRadioBtn().click();
		Thread.sleep(500);
		// Next btn shows only if there is spouse to sign
		// tb.clkNextBtn.click();
		tb.clksendBtninCaptureSignature().click();
		// tb.clkokbtninRemoteSignRequest().click();
		// tb.clkcancelBtn().click();
		Thread.sleep(500);
		//tb.clkcancelbtninremotesignerror().click();---------------season 24 working on it
		// transmit
		Thread.sleep(1000);
		tb.clktransmitReturnbtn().click();
		Thread.sleep(500);
		tb.clkOKinVerifysuccessbtn().click();
		Thread.sleep(500);
		tb.clkNextBtn().click();
		Thread.sleep(500);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("./reports/transmitreturn.png"));
		String path1 = "transmitreturn.png";
		
	}

	
	
	public void frm8867(String env) throws InterruptedException {
		// AddaReturnpgPO ap = new AddaReturnpgPO(driver);
		addaforminaReturnPO fm = new addaforminaReturnPO(driver);
		// ap.clickaddaform().click();
		Thread.sleep(1000);
		/*
		 * fm.clksearchAForm().sendKeys("FRM 8867"); fm.clkfrm8867().click();
		 * fm.clkAddbtnAddanewform().click(); fm.clkfrm8867().click();
		 * fm.clkAddbtnAddanewform().click();
		 */
		TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb.clkbtnSaveTaxReturn().click();
		Thread.sleep(1000);
		fm.clkfrm8867().click();
		FRM8867PO f867 = new FRM8867PO(driver);
		f867.getyes_1().sendKeys("X");
		f867.getyes_2().sendKeys("X");
		f867.getyes_3().sendKeys("X");
		f867.getNo_4().sendKeys("X");
		f867.getyes_5().sendKeys("X");
		f867.getyes_6().sendKeys("X");
		f867.getyes_7().sendKeys("X");
		f867.getyes_10().sendKeys("X");
		Thread.sleep(1000);
		f867.getyes_11().sendKeys("X");
		f867.getyes_12().sendKeys("X");
		f867.getyes_15().sendKeys("X");
		f867.getline5_A_d().sendKeys("X");
		// TaxreturnBarPO tb = new TaxreturnBarPO(driver);
		tb.clkbtnSaveTaxReturn().click();

	}
	
	
	@Parameters({ "env", "year" })
	@Test
	public void add1065return(String env,String year) throws IOException, InterruptedException
	{
		test.log(LogStatus.INFO, "STARTING ADD A RETURN");
		Reporter.log("STARTING 1065 BUSINESS RETURN");
		Reporter.log("<br>");
		openaPreparer(env, 334);
		//changeto23(env,year);
	    Reporter.log("opened a preparer"); 
	    Reporter.log("<br>");
	    test.log(LogStatus.INFO, "opened a preparer"); 
	    generateSSN(env,"1");
	    addCDS(env,year);
	    addfinancialIncomestatement();
	    addfinancialbalancesheet();
	    sch1065form(); 
	    addformk1_two();
	    addformk1_one();	
	    TaxreturnBarPO tb = new TaxreturnBarPO(driver);
	    tb.clkbtnSaveTaxReturn().click();
	      
	}
	
	
	
	@Parameters({ "env", "year" })
	//@Test
	public void add1120return(String env,String year) throws IOException, InterruptedException
	{
		test.log(LogStatus.INFO, "STARTING ADD A RETURN");
		Reporter.log("STARTING ADDARETURN");
		Reporter.log("<br>");
		openaPreparer(env, 334);
		changeto23(env,year);
	    Reporter.log("opened a preparer"); 
	    Reporter.log("<br>");
	    test.log(LogStatus.INFO, "opened a preparer"); 
	    generateSSN(env,"3");
	    addCDS(env,year);
	    addfinancialIncomestatement();
	    addfinancialbalancesheet();
	    TaxreturnBarPO tb = new TaxreturnBarPO(driver);
	    tb.clkbtnSaveTaxReturn().click();
	    
	    
	}

}
