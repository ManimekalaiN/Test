import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Login {
	public static void main(String[] args) throws InterruptedException {
		String bankmanager= "//button[@class='btn btn-primary btn-lg' and text()='Bank Manager Login']";
		String add_customer="//button[contains(.,'Add Customer')]";
		String First_Name="//input[@placeholder='First Name']";
		String Last_Name="//input[@placeholder='Last Name']";
		String Post_code="//input[@placeholder='Post Code']";
		String button_addcustomer="//button[@type='submit']";
		String Customers="//button[contains(.,'Customers')]";
		String Customer_Table="//table[@class='table table-bordered table-striped']/tbody/tr";
		String[] firstname={"Christopher","Frank","Christopher","Connely","Jackson","Minka","Jackson"};
		String[] lastname= {"Connely","Christopher","Minka","Jackson","Frank","Jackson","Connely"};
		String[] Postcode= {"L789C349","A897N450","M098Q585","L789C349","L789C349","A897N450","L789C349"};
		String[] delete_fname= {"Jackson","Christopher"};
		String[] delete_lname= {"Frank","Connely"};
		
		List<String> f_name=new ArrayList<String>();
		System.out.println("Login details");
		WebDriver driver=new ChromeDriver();
		//Navigate to Bank url
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login.");
		driver.manage().window().maximize();
		System.out.println("Login details_after");
		Thread.sleep(3000);
		//Select bankManage Login
		WebElement BankAdmin=driver.findElement(By.xpath(bankmanager));
		System.out.println("Element found");
		BankAdmin.click();
		
		//Click Add Customer button
		Thread.sleep(2000);
		driver.findElement(By.xpath(add_customer)).click();
		Thread.sleep(2000);
		
		//Add customers
		for(int i=0;i<firstname.length;i++) {
			int j=i+1;
			driver.findElement(By.xpath(First_Name)).click();
			driver.findElement(By.xpath(First_Name)).sendKeys(firstname[i]);
			driver.findElement(By.xpath(Last_Name)).click();
			driver.findElement(By.xpath(Last_Name)).sendKeys(lastname[i]);
			driver.findElement(By.xpath(Post_code)).click();
			driver.findElement(By.xpath(Post_code)).sendKeys(Postcode[i]);
			driver.findElement(By.xpath(button_addcustomer)).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			System.out.println("Customer "+j+" added Successfully");
		}
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(Customers)).click();
		Thread.sleep(3000);
		List<WebElement> rows=driver.findElements(By.xpath(Customer_Table));
		int rownumber=rows.size();
		System.out.println("Rownumber"+rownumber);
		for(int i=1;i<=rownumber;i++) {
			for(int j=0;j<firstname.length;j++) {
			String FirstName=driver.findElement(By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr["+i+"]//td[1]")).getText();
			//System.out.println(FirstName);
			if(FirstName.equalsIgnoreCase(firstname[j])) {
				String LastName=driver.findElement(By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr["+i+"]//td[2]")).getText(); 
				//System.out.println(LastName);
				if(LastName.equalsIgnoreCase(lastname[j])) {
					System.out.println("Customer "+FirstName + LastName+ " is Present");
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		}
		//To delete customer:
		System.out.println("Row number before deleting records"+rownumber);
		for(int i=1;i<=rownumber;i++) {
			for(int j=0;j<delete_fname.length;j++) {
				String FirstName=driver.findElement(By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr["+i+"]//td[1]")).getText();
				if(FirstName.equalsIgnoreCase(delete_fname[j])) {
					String LastName=driver.findElement(By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr["+i+"]//td[2]")).getText(); 
					//System.out.println(LastName);
					if(LastName.equalsIgnoreCase(delete_lname[j])) {
						System.out.println("Customer "+FirstName + LastName+ " is Present");
						driver.findElement(By.xpath("//table[@class='table table-bordered table-striped']//tbody//tr["+i+"]//td[5]//button")).click();
						Thread.sleep(2000);
						rownumber=rownumber-1;
					}
			}
		}
	}
		System.out.println("Row number after deleting records"+rownumber);
	}

}

