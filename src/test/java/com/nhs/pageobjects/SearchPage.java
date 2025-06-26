package com.nhs.pageobjects;

import com.nhs.basesetup.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends TestBase {
    WebDriver driver;
    WebDriverWait wait;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = TestBase.getWebDriverWait();

    }
    public void launchApplication() {
        driver.get(TestBase.url);
    }

    @FindBy(id = "keyword")
    private WebElement keywordInput;

    @FindBy(xpath = "//*[contains(@class,'nhsuk-button')][@id='search']")
    private WebElement searchButton;

    @FindBy(id = "location")
    private WebElement location;

    @FindBy(id = "searchOptionsBtn")
    private WebElement searchOptions;

    @FindBy(xpath = "//label[contains(text(),'What')][@for='keyword']")
    private WebElement what;

    @FindBy(xpath = "//label[contains(text(),'Where')][@for='location']")
    private WebElement where;

    @FindBy(xpath = "//label[contains(text(),'Job reference')][@for='jobReference']")
    private WebElement jobReferenceLabel;

    @FindBy(xpath = "//label[contains(text(),'Employer')][@for='employer']")
    private WebElement employerLabel;

    @FindBy(id = "employer")
    private WebElement employers;

    public void enterKeyword(String keyword) {
        keywordInput.clear();
        keywordInput.sendKeys(keyword);
    }


    public void clickSearchButton() {
        searchButton.click();
    }

    public void enterLocations(String locations) {
        location.clear();
        location.sendKeys(locations);

    }

    public void clickMoreSearchOption() {
        searchOptions.click();
    }

    public void enterEmployer(String employer) {
        employers.clear();
        employers.sendKeys(employer);

    }

    public void assertSearchOptionsLabelsLandingPage() {

        Assert.assertTrue("What is not displayed", what.isDisplayed());
        Assert.assertTrue("Where is not displayed", where.isDisplayed());
        Assert.assertTrue("Employer is not displayed", employerLabel.isDisplayed());
        Assert.assertTrue("Job Reference is not displayed", jobReferenceLabel.isDisplayed());

    }

}
