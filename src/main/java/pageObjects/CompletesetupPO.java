/**
 * 
 */
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author ssreenivasan
 *
 */
public class CompletesetupPO {
	
	public WebDriver driver;
	public CompletesetupPO(WebDriver driver) {
		this.driver=driver;
	}
	
	By Officebtn = By.xpath("//p[text()='Office']");
	By firmName = By.id("txtFirmNameOfficeSetup");
	By firmAddress = By.id("txtFirmAddressOfficeSetup");
	By firmCity = By.id("txtCityOfficeSetup");
	By firmZip = By.id("nfZipOfficeSetup");
	By firmph = By.id("nfOfficePhoneOfficeSetup");
	By rtrnoverridecheckbox = By.id("chkSameInfoOfficeSetup");
	By SavenNextOffice = By.id("btnNextOfficeSetup");
	By ERObtn = By.id("miClickableRowERO");
	By EROName = By.id("txtERONameEROSETUP");
	By EROaddress = By.id("txtEROAddressEROSETUP");
	By EROcity = By.id("txtEROcityEROSETUP");
	By EROzip = By.id("nfEROzipEROSETUP");
	By EROph = By.id("nfEROPhoneEROSetup");
	By EROSavenNext = By.id("btnNextEROSETUP");
	By Preparerbtn = By.xpath("//li[@id='miClickableRowPreparer']");
	By BillingAddFormbtn = By.id("btnAddFormFormBilling");//add form billing
	By W2Peritem = By.id("bpriceper-15352");
	By AddBtninAddBillingFrm = By.id("btnUpdateBillingState");//check
	By AddworksheetinBilling = By.id("btnAddFormFormBilling");
	By PerIteminworksheet = By.id("bpriceper-17461");
	By AddWorksheetBtn = By.id("btnUpdateBillingState");//check
	By AddLineItemsBtninBilling = By.xpath("//span[contains(text(),'Add Line Items +')]");
	By PerIteminLinesheet = By.id("bpriceper-16390");
	By AddLinesheetBtn = By.id("btnUpdateBillingState");//check
	By billingSavebtn = By.id("btnNextBillingSetup");
	//By AddforminBilling = By.id("btnAddFormFormBilling");
	By BillingRowBtn = By.id("miClickableRowBilling");
	By Efilingbtn = By.xpath("//div/p[text()='EFiling']");
	By CheckBox1 = By.id("chkDiscardACKLettersEfilingSetup");
	By CheckBox2 = By.id("chkAutoAddFilingOptionsEfilingSetup");
	By CheckBox3 = By.id("chkExcludeDocsEfilingSetup");
	By CheckBox4 = By.id("chkRequireUserStatusEfilingSetup");
	By SaveEfilingbtn = By.id("btnNextEFileSetup");
	By Printingbtn = By.id("miClickableRowPrinting");
	By PcheckBox1 = By.id("chkPrintPreparerCopyPrintingSetup");
	By PcheckBox2 = By.id("chkPrint8879PrintingSetup");		
	By PcheckBox3 = By.id("chkDisableSignaturesPrintingSetup");
	By PcheckBox4 = By.id("chkRtnOrDanMaskingPrintingSetup");
	By PcheckBox5 = By.id("chkPrintCopySeparatePagesPrintingSetup");
	By PcheckBox6 = By.id("chkSsnMaskingPrintingSetup");
	By PcheckBox7 = By.id("chkPreparerStateClientLetterPrintingSetup");
	By PcheckBox8 = By.id("chkPreparerStateReturnPrintingSetup");
	By PcheckBox9 = By.id("chkPreparerAppLetterPrintingSetup");
	By PcheckBox10 = By.id("chkClientFedClientLetterPrintingSetup");
	By printingSavebtn = By.xpath("//span[text()='Save']");
	By CloseSetupBtn = By.id("btnSaveAndCloseSettingsProfile");
	
	public WebElement clkOfficebtn()
	{
		return driver.findElement(Officebtn);
	}
	public WebElement getfirmName()
	{
		return driver.findElement(firmName);
	}
	public WebElement getfirmAddress()
	{
		return driver.findElement(firmAddress);
	}
	public WebElement getfirmCity()
	{
		return driver.findElement(firmCity);
	}
	public WebElement getfirmZip()
	{
		return driver.findElement(firmZip);
	}
	public WebElement getfirmph()
	{
		return driver.findElement(firmph);
	}
	public WebElement clkrtrnoverridecheckbox()
	{
		return driver.findElement(rtrnoverridecheckbox);
	}
	public WebElement clkSavenNextOffice()
	{
		return driver.findElement(SavenNextOffice);
	}
	public WebElement clkERObtn()
	{
		return driver.findElement(ERObtn);
	}
	public WebElement getEROName()
	{
		return driver.findElement(ERObtn);
	}
	public WebElement getEROaddress()
	{
		return driver.findElement(EROaddress);
	}
	public WebElement getEROcity()
	{
		return driver.findElement(EROcity);
	}
	public WebElement getEROzip()
	{
		return driver.findElement(EROzip);
	}
	public WebElement clkEROSavenNext()
	{
		return driver.findElement(EROSavenNext);
	}
	public WebElement clkPreparerbtn()
	{
		return driver.findElement(Preparerbtn);
	}
	public WebElement getEROph()
	{
		return driver.findElement(EROph);
	}
	public WebElement clkBillingAddFormbtn()
	{
		return driver.findElement(BillingAddFormbtn);
	}
	public WebElement clkBillingRowBtn()
	{
		return driver.findElement(BillingRowBtn);
	}
	public WebElement clkbillingSavebtn()
	{
		return driver.findElement(billingSavebtn);
	}
	public WebElement getW2Peritem()
	{
		return driver.findElement(W2Peritem);
	}
	public WebElement clkAddBtninAddBillingFrm()
	{
		return driver.findElement(AddBtninAddBillingFrm);
	}
	public WebElement clkAddworksheetinBilling()
	{
		return driver.findElement(AddworksheetinBilling);
	}
	public WebElement getPerIteminworksheet()
	{
		return driver.findElement(PerIteminworksheet);
	}
	public WebElement clkAddWorksheetBtn()
	{
		return driver.findElement(AddWorksheetBtn);
	}
	public WebElement clkAddLineItemsBtninBilling()
	{
		return driver.findElement(AddLineItemsBtninBilling);
	}
	public WebElement getPerIteminLinesheet()
	{
		return driver.findElement(PerIteminLinesheet);
	}
	public WebElement clkAddLinesheetBtn()
	{
		return driver.findElement(AddLinesheetBtn);
	}
	public WebElement clkEfilingbtn()
	{
		return driver.findElement(Efilingbtn);
	}
	public WebElement clkCheckBox1()
	{
		return driver.findElement(CheckBox1);
	}
	public WebElement clkCheckBox2()
	{
		return driver.findElement(CheckBox2);
	}
	public WebElement clkCheckBox3()
	{
		return driver.findElement(CheckBox3);
	}
	public WebElement clkCheckBox4()
	{
		return driver.findElement(CheckBox4);
	}
	public WebElement clkSaveEfilingbtn()
	{
		return driver.findElement(SaveEfilingbtn);
	}
	public WebElement clkPrintingbtn()
	{
		return driver.findElement(Printingbtn);
	}
	
	public WebElement clkPcheckBox1()
	{
		return driver.findElement(PcheckBox1);
	}
	public WebElement clkPcheckBox2()
	{
		return driver.findElement(PcheckBox2);
	}
	public WebElement clkPcheckBox3()
	{
		return driver.findElement(PcheckBox3);
	}
	public WebElement clkPcheckBox4()
	{
		return driver.findElement(PcheckBox4);
	}
	public WebElement clkPcheckBox5()
	{
		return driver.findElement(PcheckBox5);
	}
	public WebElement clkPcheckBox6()
	{
		return driver.findElement(PcheckBox6);
	}
	public WebElement clkPcheckBox7()
	{
		return driver.findElement(PcheckBox7);
	}
	public WebElement clkPcheckBox8()
	{
		return driver.findElement(PcheckBox8);
	}
	public WebElement clkPcheckBox9()
	{
		return driver.findElement(PcheckBox9);
	}
	public WebElement clkPcheckBox10()
	{
		return driver.findElement(PcheckBox10);
	}
	public WebElement clkprintingSavebtn()
	{
		return driver.findElement(printingSavebtn);
	}
	public WebElement clkCloseSetupBtn()
	{
		return driver.findElement(CloseSetupBtn);
	}

}
