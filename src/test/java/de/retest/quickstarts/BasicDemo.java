package de.retest.quickstarts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;

@RunWith( JUnit4.class )
public class BasicDemo {

	WebDriver driver;
	Recheck re;

	@Before
	public void setup() {
		final ChromeOptions options = new ChromeOptions() //
				.addArguments( // 
						"--headless", //
						"--no-sandbox", //
						"--window-size=1200,800" );
		driver = new ChromeDriver( options );

		final RecheckOptions recheckOptions = RecheckOptions.builder() //
				.enableReportUpload() //
				.build();
		re = new RecheckImpl( recheckOptions );
	}

	@Test
	public void basicTest() {
		// Set test name
		re.startTest( "basic-test" );

		// Navigate the browser to the demo page.
		driver.get( "https://demo.retest.org/start.html" );

		// To see visual bugs after the first run, use the commented line below instead.
		//driver.get("https://demo.retest.org/changed.html");

		// Check the demo page.
		re.check( driver, "check" );

		// End the test.
		re.capTest();
	}

	@After
	public void shutdown() {
		// Close the browser.
		driver.quit();

		// Collect all test results
		re.cap();
	}
}
