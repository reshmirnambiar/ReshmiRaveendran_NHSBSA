package com.nhs.basesetup;

import java.time.Duration;
import java.util.Properties;

import com.nhs.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	static WebDriver driver;
	static WebDriverWait wait;
	Properties config;
	public static String url;

	public WebDriver selectBrowser() {

		config = new ConfigReader().readProperties();
		url = config.getProperty("url");

		// Priority to system property, fallback to config file, default to "edge"
		String browserName = System.getProperty("browser");
		if (browserName == null || browserName.isEmpty()) {
			browserName = config.getProperty("browserName", "edge");
		}

		browserName = browserName.toLowerCase();

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();  // Setup ChromeDriver binary
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();  // Setup EdgeDriver binary
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();  // Setup FirefoxDriver binary
			driver = new FirefoxDriver();
		} else {
			throw new RuntimeException("Unsupported browser: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriverWait getWebDriverWait() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait;
	}
}
