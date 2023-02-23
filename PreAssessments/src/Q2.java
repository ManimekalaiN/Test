import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Q2 {

	
	String Deposit_option="//button[contains(text(),'Deposit')]";
	String Withdrawl_option="//button[contains(text(),'Withdrawl')]";
	String credit_amount="//input[@placeholder='amount']";
	String debit_amount="//label[contains(text(),'Amount to be Withdrawn :')]//following-sibling::input";
	String deposit_submit_button="//button[@type='submit']";
	String debit_submit_button="//button[@type='submit' and text()='Withdraw']";
	String Deposit_success="//span[@ng-show='message' and text()='Deposit Successful']";
	String withdraw_success="//span[@ng-show='message' and text()='Transaction successful']";
	String current_Balance="//div[contains(text(),'Account Number :' )]//strong[2]";
	//int updated_balance=0;
	
	public void credit(WebDriver driver,String amount) throws InterruptedException {
	String c_balance=driver.findElement(By.xpath(current_Balance)).getText();
	System.out.println("Current balance before credit Transaction "+c_balance);
	int updated_balance=Integer.parseInt(c_balance);
	Thread.sleep(2000);
	driver.findElement(By.xpath(Deposit_option)).click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath(credit_amount)).sendKeys(amount);
	driver.findElement(By.xpath(deposit_submit_button)).click();
	Thread.sleep(2000);
	if(driver.findElement(By.xpath(Deposit_success)).isDisplayed())
	{
		System.out.println("Amount "+amount+" successfully credited to the account");
		int amnt=Integer.parseInt(amount);
		updated_balance=updated_balance+amnt;
		System.out.println("Current balance after credit Transaction "+updated_balance);
	}
	String upd_balance=String.valueOf(updated_balance);
	if(upd_balance.equals(driver.findElement(By.xpath(current_Balance)).getText())) {
		System.out.println("Current Balance is tally with the Balance Section");
		System.out.println("Balance in balance section "+upd_balance);
	}
	else {
		System.out.println("Current Balance is not tally with the Balance Section");
	}
	}
	
	public void debit(WebDriver driver,String amount)throws InterruptedException {
		String c_balance=driver.findElement(By.xpath(current_Balance)).getText();
		System.out.println("Current balance before Debit Transaction "+c_balance);
		int updated_balance=Integer.parseInt(c_balance);
		Thread.sleep(2000);
		driver.findElement(By.xpath(Withdrawl_option)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(debit_amount)).sendKeys(amount);
		driver.findElement(By.xpath(debit_submit_button)).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath(withdraw_success)).isDisplayed())
		{
			System.out.println("Amount "+amount+" successfully debited from the account");
			int amnt=Integer.parseInt(amount);
			updated_balance=updated_balance-amnt;
			System.out.println("Current balance After debit Transaction "+updated_balance);
		}
		String upd_balance=String.valueOf(updated_balance);
		if(upd_balance.equals(driver.findElement(By.xpath(current_Balance)).getText())) {
			System.out.println("Current Balance is tally with the Balance Section");
			System.out.println("Balance in balance section "+upd_balance);
		}
		else {
			System.out.println("Current Balance is not tally with the Balance Section");
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Q2 obj=new Q2();
		WebDriver driver=new ChromeDriver();
		String customer_login= "//button[@class='btn btn-primary btn-lg' and text()='Customer Login']";
		String customer_name="//select[@id='userSelect']";
		String accountSelect="//select[@id='accountSelect']";
		String Login="//button[text()='Login']";
		String accountnumber_selected="//div[contains(text(),'Account Number :' )]//strong[1]";
		
		String userName="Hermoine Granger";
		String accountnumber="1003";
		
		
		//Navigate to Bank url
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login.");
		driver.manage().window().maximize();
		System.out.println("Login details_after");
		Thread.sleep(3000);
		//Select bankManage Login
		WebElement Customer=driver.findElement(By.xpath(customer_login));
		System.out.println("Element found");
		Customer.click();
		
		//Select customerfrom Dropdown
		Thread.sleep(2000);
		Select user=new Select(driver.findElement(By.xpath(customer_name)));
		user.selectByVisibleText(userName);
		Thread.sleep(2000);
		
		//Click Login Button
		driver.findElement(By.xpath(Login)).click();
		//Select customerfrom Dropdown
		Thread.sleep(2000);
		Select account=new Select(driver.findElement(By.xpath(accountSelect)));
		account.selectByVisibleText(accountnumber);
		Thread.sleep(2000);
		
		//Verifying if the right account selected 
		if(driver.findElement(By.xpath(accountnumber_selected)).getText().equals(accountnumber)) {
			System.out.println("Account number "+accountnumber+" is selected");
		}
		else {
			System.out.println("Incorrect Account number selected. Please try again");
		}
		obj.credit(driver, "50000");
		obj.debit(driver, "3000");
		obj.debit(driver, "2000");
		obj.credit(driver, "5000");
		obj.debit(driver, "10000");
		obj.debit(driver, "15000");
		obj.credit(driver, "1500");
		
	}

}
