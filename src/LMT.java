import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class LMT {
	public WebDriver driver;

//--------------------------------------------------------------------------------------------------------------------------------------    
//--------------------------------------------------------------------------------------------------------------------------------------	
	@Test (priority = 1)
	@Parameters ({"url", "StartPageTitle", "sleep"})
	
	public void e2eScenario(String url, String checkStartPageTitle, int sleep) throws Throwable {
        Reporter.log("<font color = green><b><---------------------E2E TEST SCENARIO---------------------></b></font><br>");
        Reporter.log("<b><u>Parameters</u></b> used: url: '<font color = darkred><b><u>" + url + "</b></u></font>', Start page title should be: '<font color = darkred><b>" + checkStartPageTitle + "</b></font>', and sleep length: <b><font color = darkred>" + sleep + "</font></b>.<br>");
        
		//Init required pages
        StartingPage startingPage = PageFactory.initElements(driver, StartingPage.class);
        QuestionnairePage questionnairePage = PageFactory.initElements(driver, QuestionnairePage.class);        
        //Init required pages
        
		Reporter.log("<b>STEP 1</b>: Check page title is correctly set. Expected: '" + checkStartPageTitle + "', but actual is: '" + driver.getTitle() + "'.<br>");
		assertEquals(driver.getTitle(), checkStartPageTitle, "Check title is correctly set.");
        
		Reporter.log("<b>STEP 2</b>: Go to 'Funkcijas' section.<br>");
        startingPage.goToFunkcijas();
        
		Reporter.log("<b>STEP 3</b>: Go back to top.<br>");
        driver.get(url);

		Reporter.log("<b>STEP 4</b>: Check whether button 'Pieteikties konsultacijai' is visible. Expected: true. Actual: " + startingPage.signUpForConsultationButtonIsVisible() + ".<br>");
        assertEquals(startingPage.signUpForConsultationButtonIsVisible(), true, "Check whether 'Pieteikties Konsultacijai' button is visible.");

        Reporter.log("<b>STEP 5</b>: In case button 'Pieteikties konsultacijai' is visible, scroll down to it, wait a little, and then click the button.<br>");        	    	
        if ( startingPage.signUpForConsultationButtonIsVisible() ) {
            Actions actions = new Actions(driver);
        	actions.moveToElement(startingPage.getPieteiktiesKonsultacijaiButton());
        	actions.perform();
            Thread.sleep(sleep);
        	startingPage.singUpForConsultation();

        	Reporter.log("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#8618;<b>STEP 6</b>: Click happened, so fill in test data in the new tab.<br>");
            questionnairePage.fillInTestData();
        }
        
        Reporter.log("<font color = green><b><---------------------E2E TEST SCENARIO---------------------></b></font><br>");
	}
	
//-----HELPER METHODS-------------------------------------------------------------------------------------------------------------------	
	
	// Prepare for tests + cleanup.
	@BeforeTest
    @Parameters ({"url"})
	public void profileSetup(String url) {
		System.setProperty("webdriver.chrome.driver", "C://ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(url);
	}
	
	@AfterTest
	public void cleanup() {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        //close all open Tabs
        for (int i = 0; i<newTb.size(); i++) {
        	driver.switchTo().window(newTb.get(i));
        	driver.close();
        }
	}
	
}
