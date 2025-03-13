package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FRMK1PO {
	
	public WebDriver driver;
	public FRMK1PO(WebDriver driver) {
		this.driver = driver;
	}

	By thirdchecbox = By.id("803000"); //complete patners basis worksheet
	By partthree_Box1 = By.id("049000");//ordinary bus income
	By partthree_Box14a = By.id("CB0300"); //self employment earning
	By partthree_Box14b = By.id("CB0400");
	
}
