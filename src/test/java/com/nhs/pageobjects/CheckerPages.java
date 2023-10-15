package com.nhs.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.nhs.basesetup.TestBase;
import com.nhs.stepdefinitions.CheckerSteps;
import com.nhs.utilities.ExcelReader;

public class CheckerPages extends TestBase {

	WebDriver driver;
	WebDriverWait wait;
	ExcelReader excelReader;

	public CheckerPages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = TestBase.getWebDriverWait();
	}

	public void launchApplication() {
		driver.get(TestBase.url);
	}

	@FindBy(id = "next-button")
	WebElement startNowButton;
	
	@FindBy(id="question-heading")
	WebElement questionHeading;

	@FindBy(id="next-button")
	WebElement nextButton;
	
	@FindBy(id="dob-day")
	WebElement dayTexBox;
	
	@FindBy(id="dob-month")
	WebElement monthTexBox;;
	
	@FindBy(id="dob-year")
	WebElement yearTexBox;;
	
	
	public void startApplication() {
		wait.until(ExpectedConditions.elementToBeClickable(startNowButton)).click();
	}
	
	String commonText = "(//*[text()='replaceMe'] | //*[contains (text(),'replaceMe')])";
	
	
	public void completeChecklist() throws InterruptedException {
		excelReader = new ExcelReader(CheckerSteps.circumstances, CheckerSteps.checkerTool);
		
		clickRadioButton(excelReader.fieldsAndValues.get("Country"));
		nextButton.click();
		
		clickRadioButton(excelReader.fieldsAndValues.get("GP_Practice"));
		nextButton.click();
		
		clickRadioButton(excelReader.fieldsAndValues.get("Cntry_Dntl_Practice"));
		nextButton.click();
		
		dayTexBox.sendKeys(excelReader.fieldsAndValues.get("Dob_Day"));
		monthTexBox.sendKeys(excelReader.fieldsAndValues.get("Dob_Month"));
		yearTexBox.sendKeys(excelReader.fieldsAndValues.get("Dob_Year"));
		nextButton.click();
		
		clickRadioButton(excelReader.fieldsAndValues.get("Live_With_Partner"));
		nextButton.click();
		
		
		clickRadioButton(excelReader.fieldsAndValues.get("Tax_Crdts"));
		nextButton.click();
		
		switch (CheckerSteps.circumstances) {
		case "1":
			
			clickRadioButton(excelReader.fieldsAndValues.get("Unvrsl_Credit"));
			nextButton.click();
			
			
			clickRadioButton(excelReader.fieldsAndValues.get("Joint_Unvrsl_Credit"));
			nextButton.click();
			
			clickRadioButton(excelReader.fieldsAndValues.get("Cmbnd_Take_Home"));
			nextButton.click();
			
			break;
			
		case "2":
			clickRadioButton(excelReader.fieldsAndValues.get("Preganancy_Status"));
			nextButton.click();
			
			clickRadioButton(excelReader.fieldsAndValues.get("Injury_Illnes"));
			nextButton.click();
			
			clickRadioButton(excelReader.fieldsAndValues.get("Diabetes_Status"));
			nextButton.click();
			
			clickRadioButton(excelReader.fieldsAndValues.get("Glaucoma_Status"));
			nextButton.click();
			

			clickRadioButton(excelReader.fieldsAndValues.get("Care_Home"));
			nextButton.click();
			

			clickRadioButton(excelReader.fieldsAndValues.get("Savings"));
			nextButton.click();
			
			break;
		default:
			break;
		}
		
		
		//  
	}
	
	@FindBy(xpath = "//*[@class='done-panel']/h1")
	WebElement doneText;
	
	@FindBy(xpath = "//*[@class='done-panel']/h2")
	WebElement helpText;
	
	@FindBy(xpath = "//*[contains (text(),'You get free')]")
	WebElement youGetFree;
	
	@FindBy(xpath="//*[contains (text(),'You get free')]//parent::div/ul/li")
	List <WebElement> resultsList;
	
	public boolean isGetHelpTrue() {
		boolean isHelp = false;
		try {
			if (youGetFree.getText().equalsIgnoreCase("You get free:")) {
				for(WebElement list: resultsList) {
					//System.out.println(list.getAttribute("innerText"));
					System.out.println("Benefits That List ===> "+list.getText());
				}
					isHelp = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return isHelp;
	}
	
	public void clickRadioButton(String replaceText) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(questionHeading).build().perform();
		String element = commonText.replace("replaceMe", replaceText.toString());
		driver.findElement(By.xpath(element)).click();
		Thread.sleep(300);
	}

}
