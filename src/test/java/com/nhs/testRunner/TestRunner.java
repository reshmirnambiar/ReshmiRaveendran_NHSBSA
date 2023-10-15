package com.nhs.testRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/json/cucumber-report.json" }
			,features = { "src/test/java/features" }
			,glue = {"com/nhs/stepdefinitions" }
			, tags = "@smoke"
			, dryRun = false
			, monochrome = true
			, publish = true)

public class TestRunner {
	@AfterClass
	public static void reportSetup() throws IOException {
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target\\json\\cucumber-report.json");
		String buildNumber = "1";
		String projectName = "NHS Checker Tool";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		configuration.setBuildNumber(buildNumber);
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Branch", "release/1.0");
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
	}
}
