package com.tcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import junit.framework.TestCase;

public class TaxCreditCoTest extends TestCase {
	@Test
	public void testBulkInput() throws IOException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String csvFile = "contacts.csv";
		String line = "";
		String cvsSplitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		int lineNum = 0;

		while ((line = br.readLine()) != null) {
			String[] temp = line.split(cvsSplitBy);
			
			if (lineNum++ != 0)
				contacts.add(new Contact(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8]));
		}

		for (Contact contact : contacts) {
			driver.get("http://taxcreditco.com");

			WebElement contactLink = driver.findElement(By.xpath("//*[@id=\"menu-item-369\"]/div/a"));
			contactLink.click();
			assertEquals("Contact TCC | TCC", driver.getTitle());
			
			WebElement firstname = driver.findElement(By.name("firstname"));
			firstname.sendKeys(contact.firstname);
			WebElement title = driver.findElement(By.name("jobtitle"));
			title.sendKeys(contact.jobtitle);
			WebElement email = driver.findElement(By.name("email"));
			email.sendKeys(contact.email);
			WebElement phone = driver.findElement(By.name("phone"));
			phone.sendKeys(contact.phone);
			WebElement company = driver.findElement(By.name("company"));
			company.sendKeys(contact.company);
			WebElement company_size = driver.findElement(By.name("company_size"));
			company_size.sendKeys(contact.company_size);
			WebElement state = driver.findElement(By.name("state"));
			state.sendKeys(contact.state);
			WebElement message = driver.findElement(By.name("message"));
			message.sendKeys(contact.message);	
			
			WebElement submit = driver.findElement(By.xpath("//*[@id=\"hsForm_3a877d85-0e59-43b4-9079-9eb8b5306c04\"]/div[9]/div[2]/input"));
			submit.click();
		}
		

		driver.quit();
	}
	

	@Test
	public void testMissingEmailTriggersMessage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://taxcreditco.com");

		WebElement contactLink = driver.findElement(By.xpath("//*[@id=\"menu-item-369\"]/div/a"));
		contactLink.click();
		assertEquals("Contact TCC | TCC", driver.getTitle());
		
		WebElement firstname = driver.findElement(By.name("firstname"));
		firstname.sendKeys("test");
		
		WebElement company = driver.findElement(By.name("company"));
		company.sendKeys("test");
		
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"hsForm_3a877d85-0e59-43b4-9079-9eb8b5306c04\"]/div[9]/div[2]/input"));
		submit.click();
		
		WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"hsForm_3a877d85-0e59-43b4-9079-9eb8b5306c04\"]/div[9]/ul/li/label"));
		assertEquals(errorMessage.getText(), "Please complete all required fields.");
		
		driver.quit();
	}
}


