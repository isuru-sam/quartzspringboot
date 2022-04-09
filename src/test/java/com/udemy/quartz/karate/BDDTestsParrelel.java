package com.udemy.quartz.karate;



import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import com.intuit.karate.junit5.Karate;
public class BDDTestsParrelel {
	private static final int THREAD_COUNT = 1;

	//@BeforeTestClass
	public static void beforeClass() throws Exception {

	}

	//@AfterTestClass
	public static void afterClass() {
	//	System.out.println("DB clean up task to remove all data");
		
	}

	/*@Test
	@Karate.Test
	public void testBDDTestsParallel() {
		System.out.println("Running BDDTests parallelly");
		String outputPath = "target/surefire-reports";
		KarateStats  stats = CucumberRunner.parallel(getClass(), THREAD_COUNT, outputPath);
		generateBddReports(outputPath);
		assertTrue("There are scenario failures for  BDD tests", stats.getFailCount() == 0);
	}
	
	private static void generateBddReports(String outputPath) {
		Collection<File> jsonFiles = FileUtils.listFiles(new File(outputPath), new String[] { "json" }, true);

		List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

		Configuration config = new Configuration(new File("target"), " BDD test suite");

		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();
	}*/

}
