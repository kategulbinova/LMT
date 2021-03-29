import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class QuestionnairePage {
	public static WebDriver driver;

	public QuestionnairePage(WebDriver driver) {
        QuestionnairePage.driver = driver;
    }
	
    @FindBy(how = How.ID, using = "companyId2")
    private WebElement company;

    @FindBy(how = How.NAME, using = "name")
    private WebElement name;
    
    @FindBy(how = How.NAME, using = "surname")
    private WebElement surname;
 
    @FindBy(how = How.NAME, using = "phone")
    private WebElement phone;
    
    @FindBy(how = How.NAME, using = "email")
    private WebElement email;    
    
    @FindBy(how = How.NAME, using = "comment")
    private WebElement comment;
    
    public void fillInTestData() throws Throwable {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(newTb.get(1));

        company.sendKeys("Mana firma");
    	name.sendKeys("Kate");
    	surname.sendKeys("Gulbinova");
    	phone.sendKeys("29562544");
    	email.sendKeys("kate.gulbinova@gmail.com");
    	comment.sendKeys("Te ir loti gars komentars par manu interesi kapec es velos sanemt konsultaciju.");
    }
    
}
