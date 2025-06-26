package com.nhs.testRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"pretty",
				"json:target/json/cucumber-report.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		features = { "src/test/resources/features" },
		glue = { "com.nhs.stepdefinitions" },
		tags = "@smoke",
		dryRun = false,
		monochrome = true,
		publish = true
)
public class TestRunner {

	@AfterClass
	public static void reportSetup() throws IOException {
		File reportOutputDirectory = new File("target/cucumber-report");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/json/cucumber-report.json");

		String projectName = "NHS JOB SEARCH";
		String buildNumber = "1";

		Configuration config = new Configuration(reportOutputDirectory, projectName);
		config.setBuildNumber(buildNumber);
		config.addClassifications("Platform", "macOS");
		config.addClassifications("Browser", "Chrome");
		config.addClassifications("Branch", "release/1.0");

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
		Reportable result = reportBuilder.generateReports();

		if (result.getFailedSteps() > 0) {
			System.out.println("Some steps failed!");
		}
	}
}
