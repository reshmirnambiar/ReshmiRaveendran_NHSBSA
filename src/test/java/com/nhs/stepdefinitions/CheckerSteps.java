package com.nhs.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.nhs.basesetup.TestBase;
import com.nhs.pageobjects.CheckerPages;
import com.nhs.utilities.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CheckerSteps {
	
	WebDriver driver;
	TestBase testBase;
	CheckerPages checkerPage;
	public static ExcelReader excelReader;
	public static String circumstances;
	public static String checkerTool;
	
	@Given("I launch application")
	public void i_launch_application() {
		testBase = new TestBase();
		driver = testBase.getDriver();
		checkerPage = new CheckerPages(driver);
		checkerPage.launchApplication();
	}

	@Given("I am a citizen of the UK")
	public void i_am_a_citizen_of_the_uk() throws InterruptedException {
		checkerPage.startApplication();
	}
	
	@When("I put my {string} into the {string}")
	public void iPutMyIntoThe(String circumstances, String checkerTool) throws Throwable {
		excelReader = new ExcelReader(circumstances,checkerTool);
		this.circumstances = circumstances;
		this.checkerTool = checkerTool;
		checkerPage.completeChecklist();
	}

	@Then("I should get a result of whether I will get help or not")
	public void i_should_get_a_result_of_whether_i_will_get_help_or_not() {
	    Assert.assertEquals(true, checkerPage.isGetHelpTrue());
	}
}


