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
public class FRMSCH_CPO {
	
	public WebDriver driver;
	public FRMSCH_CPO(WebDriver driver) {
		this.driver=driver;
	}
	
	By A=By.xpath("//input[@title='Describe the Business or Professional Activity']");
			//By.xpath("//input[@id='002000']");
			//By.cssSelector("input#002000.form_field");
			//By.xpath("//input[@id='002000']");
		//	By.id("002000");
	By B=By.id("003000");
	By E_Bizaddress = By.id("006100");
	By E_Zip=By.id("806400");
	By F_Cash = By.id("006300");
	By I_No=By.id("018900");
	By QualbizIndicator = By.id("880000");
	By grossSales = By.id("00Z200");
	By utilities = By.id("040700");
	
	public WebElement getA()
	{
		return driver.findElement(A);
	}
	public WebElement getB()
	{
		return driver.findElement(B);
	}
	public WebElement getE_Bizaddress()
	{
		return driver.findElement(E_Bizaddress);
	}
	public WebElement getE_Zip()
	{
		return driver.findElement(E_Zip);
	}
	public WebElement getF_Cash()
	{
		return driver.findElement(F_Cash);
	}
	public WebElement getI_No()
	{
		return driver.findElement(I_No);
	}
	public WebElement getQualbizIndicator()
	{
		return driver.findElement(QualbizIndicator);
	}
	public WebElement getgrossSales()
	{
		return driver.findElement(grossSales);
	}
	public WebElement getutilities()
	{
		return driver.findElement(utilities);
	}
}
