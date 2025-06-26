package com.nhs.stepdefinitions;

import com.nhs.basesetup.TestBase;
import com.nhs.pageobjects.SearchResultPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class SearchResultSteps {
    WebDriver driver;
    SearchResultPage searchresultPage;

    private static final Logger logger = LoggerFactory.getLogger(SearchResultSteps.class);

    public SearchResultSteps() {
        this.driver = TestBase.getDriver();
        this.searchresultPage = new SearchResultPage(driver);

    }

    @Then("I should see search result for {string}")
    public void i_should_see_search_result_for(String expectedKeyword) {
        logger.info("Verifying search results contain: {}" ,expectedKeyword);
        boolean found = searchresultPage.isJobResultDisplayed(expectedKeyword);
        Assert.assertTrue("Job results not found for: " + expectedKeyword, found);
        logger.info("Job results found for: {}", expectedKeyword);
    }

    @And("I sort the results by {string}")
    public void i_sort_the_results_by(String sortOption) {
        logger.info("Sorting results by: {}", sortOption);
        searchresultPage.sortResultsBy(sortOption);

    }

    @Then("I should see search result for postcode {string}")
    public void i_should_see_search_result_for_postcode(String expectedPostcode) {
        String actualPostcode = searchresultPage.getFirstPostcodeText();
        logger.info("Actual postcode from result: {}", actualPostcode);
        Assert.assertTrue("Job location postcode is not matching. Expected: " + expectedPostcode
                        + " but was: " + actualPostcode,
                actualPostcode.toLowerCase().contains(expectedPostcode.toLowerCase()));

    }

    @Then("I should see search results for town {string}")
    public void i_should_see_search_results_for_town(String expectedTown) {
        String headingText = searchresultPage.getSearchHeadingText();
        logger.info("Search results heading: {}",  headingText);
        Assert.assertTrue("Expected town/city not found in heading: " + expectedTown,
        headingText.toLowerCase().contains(expectedTown.toLowerCase()));
    }

    @Then("I should see a message {string}")
    public void i_should_see_a_message(String expectedMessage) {
        String actualMessage = searchresultPage.getNoResultsMessageText();
        logger.info("Expected message: {}", expectedMessage);
        logger.info("Actual message: {}",  actualMessage);
        Assert.assertEquals("Expected message not displayed", expectedMessage, actualMessage);
    }

        @Then("the results should be sorted by date")
           public void results_should_be_sorted_by_newest_date() {
                 List<LocalDate> actualDates = searchresultPage.getAllPostedDates();

                 for (int i = 0; i < actualDates.size() - 1; i++) {
                     boolean isSorted = actualDates.get(i).isAfter(actualDates.get(i + 1))
                                        || actualDates.get(i).isEqual(actualDates.get(i + 1));
                     Assert.assertTrue(
                         "Dates not in descending order: " + actualDates.get(i) + " before " + actualDates.get(i + 1),
                         isSorted
                     );
                 }

                 logger.info("Search results are correctly sorted by date (newest first).");
             }


              @Then("I should see search result for employer {string}")
              public void i_should_see_search_result_for_employer(String employerName) {
                  searchresultPage.assertEmployerName(employerName);
                  logger.info("Verified that search results contain employer: " + employerName);
              }



    }









