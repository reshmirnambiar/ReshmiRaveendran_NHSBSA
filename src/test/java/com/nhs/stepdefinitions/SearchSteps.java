package com.nhs.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import com.nhs.basesetup.TestBase;
import com.nhs.pageobjects.SearchPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchSteps {
	
	WebDriver driver;
	SearchPage searchPage;

	private static final Logger logger = LoggerFactory.getLogger(SearchSteps.class);

	public  SearchSteps() {
		this.driver = TestBase.getDriver();
		this.searchPage = new SearchPage(driver);
	}

	@Given("I navigate to the search page")
	public void i_navigate_to_the_search_page() {
		searchPage.launchApplication();
	}

	@When("I search for job with {string}")
	public void i_search_for_job_with_keyword(String keyword)  {
		logger.info("search job with: {}", keyword);
		 searchPage.enterKeyword(keyword);
	}

	@And("I click Search button")
	public void i_click_search_button() {
		logger.info("Clicking the Search button...");
		searchPage.clickSearchButton();
	}
	@Given("I search for jobs using postcodes {string}")
	public void i_search_for_jobs_using_postcodes(String postcode) {
		logger.info("Entering postcode: {}",  postcode);
		searchPage.enterLocations(postcode);

	}

		@When("I search for jobs using town {string}")
		public void i_search_for_jobs_using_town (String town){
			logger.info("Entering town: {}", town);
			searchPage.enterLocations(town);
		}

		@When("I click on More Search Options")
		public void i_click_on_more_search_options (){
		logger.info("Clicking on 'More Search Options'");
		searchPage.clickMoreSearchOption();
		}

		@Then("I see all 4 search fields on search screen")
		public void the_correct_search_options_are_displayed() {
		logger.info("Verifying that all 4 search fields are visible on the screen");
		searchPage.assertSearchOptionsLabelsLandingPage();
	}

	@Given("I search for job with employer {string}")
	public void i_search_for_job_with_employer(String employer) {
		logger.info("Entering employer: {}",  employer);
		searchPage.enterEmployer(employer);



	}


	}





