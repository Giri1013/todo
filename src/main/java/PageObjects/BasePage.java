package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @FindBy(how = How.LINK_TEXT, using = "AngularJS")
    public WebElement angularJS;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
