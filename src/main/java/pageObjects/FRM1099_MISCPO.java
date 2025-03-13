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
public class FRM1099_MISCPO {
	
	public WebDriver driver;
	public FRM1099_MISCPO(WebDriver driver) {
		this.driver=driver;
	}

	By payersEIN = By.id("008500");
	By rents = By.id("011500");
	By linksTo = By.id("890000");
			//By.xpath("//input[@id='890000']");
			//By.id("890000");
	
	public WebElement getpayersEIN()
	{
		return driver.findElement(payersEIN);
	}
	public WebElement getrents()
	{
		return driver.findElement(rents);
	}
	public WebElement getlinksTo()
	{
		return driver.findElement(linksTo);
	}	
}
