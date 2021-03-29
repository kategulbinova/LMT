import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StartingPage{  
	public static WebDriver driver;

	public StartingPage(WebDriver driver) {
        this.driver = driver;
    }
    	
    @FindBy(how = How.XPATH, using = "/html/body/nav[2]/div[1]/ul/li[4]/a")
    private WebElement funkcijasLink;
    
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[6]/div[2]/div/div[1]/div/a")
    private WebElement pieteiktiesKonsultacijaiButton;
    
    public void goToFunkcijas() {
    	funkcijasLink.click();
    }
    
    public void singUpForConsultation() {	
    	pieteiktiesKonsultacijaiButton.click();
    }
    
    public boolean signUpForConsultationButtonIsVisible() {
    	if (pieteiktiesKonsultacijaiButton.isDisplayed()) return true;
    	return false;
    }
    
    public WebElement getPieteiktiesKonsultacijaiButton() {
    	return pieteiktiesKonsultacijaiButton;
    }
}
