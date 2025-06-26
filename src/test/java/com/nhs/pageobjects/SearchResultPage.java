package com.nhs.pageobjects;

import com.nhs.basesetup.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends TestBase {

    WebDriver driver;
    WebDriverWait wait;


    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = TestBase.getWebDriverWait();
    }

    @FindBy(xpath = "//a[@data-test='search-result-job-title']")
    private List<WebElement> jobResults;

    @FindBy(id = "sort")
    private WebElement sortDropdown;

    @FindBy(xpath = "//h3[@class='nhsuk-u-font-weight-bold']/div")
    private List<WebElement> postcode;

    @FindBy(id = "search-results-heading")
    private WebElement searchResultsHeading;

    @FindBy(xpath = "//h3[contains(text(),'No result found')]")
    private WebElement displayMsg;

    @FindBy(xpath = "//div[contains(@class,'job-listing')]//strong[contains(@class,'nhsuk-u-font-weight-bold')]")
    private List<WebElement> jobPostedDates;

    @FindBy(xpath= "//h3[@class='nhsuk-u-font-weight-bold']")
    private WebElement employer;

    public boolean isJobResultDisplayed(String keyword) {
        wait.until(ExpectedConditions.visibilityOfAllElements(jobResults));
        return jobResults.stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.toLowerCase().contains(keyword.toLowerCase()));
    }

    public String getFirstPostcodeText() {
        return postcode.get(0).getText();
    }

    public String getSearchHeadingText() {
        wait.until(ExpectedConditions.visibilityOf(searchResultsHeading));
        return searchResultsHeading.getText();
    }

    public  void sortResultsBy(String sortOption) {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOption);
    }
    public String getNoResultsMessageText() {
        wait.until(ExpectedConditions.visibilityOf(displayMsg));
        return displayMsg.getText().trim();
    }

    public List<LocalDate> getAllPostedDates() {
        List<LocalDate> postedDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        for (WebElement element : jobPostedDates) {
            String dateText = element.getText().trim();
            try {
                LocalDate date = LocalDate.parse(dateText, formatter);
                postedDates.add(date);
            } catch (Exception e) {
                System.out.println("Skipping invalid date: " + dateText);
            }
        }

        return postedDates;
    }

    public void assertEmployerName(String expectedEmployerName) {
        Assert.assertTrue("Employer name is not matching. Expected: " + expectedEmployerName +
                        " but found: " + employer.getText(),
                employer.getText().contains(expectedEmployerName));
    }



}
