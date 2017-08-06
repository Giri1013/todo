package Helpers;

import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;

public class DriverHelper {


    public static WebDriver getDriver(String browserType){

        WebDriver driver = null;

        switch (browserType.toLowerCase()){

            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                //
                System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "safari":
                break;


        }

      driver.manage().window().maximize();
        return driver;


    }

    public static void clickElement(WebElement element){
        try{
            element.click();
        }
        catch (Exception e){
            throw  new CustomException(e, "Exception while clicking element - "+element.getText());
        }
    }
    public static void selectElement(WebElement element){
        try{
            element.click();
        }
        catch (Exception e){
            throw  new CustomException(e, "Exception while clicking element - "+element.getText());
        }
    }

    public static void sendKeys(WebElement element,String text){
        try{
            element.sendKeys(text);
        }
        catch (Exception e){
            throw  new CustomException(e, "Exception while entering text into element - "+element.getText());
        }
    }
    public static void sendSpecialKeys(WebElement element,String keyOption){
        try{
            Keys key = Keys.NULL;
            switch (keyOption.toLowerCase()){
                case "enter":
                    key = Keys.ENTER;
                    break;
            }
            element.sendKeys(key);

        }
        catch (Exception e){
            throw  new CustomException(e, "Exception while entering special keys into element - "+element.getText());
        }
    }
    public static void waitForElement(WebDriver driver ,WebElement element){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitAndSendKeys(WebDriver driver ,WebElement element,String text){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public static void performDoubleClickAndSendText(WebDriver driver,WebElement element,String newText){
     //   JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
       // myExecutor.executeScript("arguments[0].dblclick();", element);
       // myExecutor.executeScript("arguments[0].value='"+newText+"';",element);
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
        actions.sendKeys(Keys.BACK_SPACE);
        actions.sendKeys(newText);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();

      //  actions.keyUp(Keys.BACK_SPACE);


       // actions.keyDown(element,Keys.SHIFT).keyDown(element,Keys.HOME).keyDown(element,Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyUp(Keys.HOME).keyUp(Keys.SHIFT);
       // actions.keyDown(Keys.HOME);

      //  actions.keyUp(Keys.HOME);
      //  actions.keyUp(Keys.SHIFT);




    }

    public  static void moveToAndClick(WebDriver driver,WebElement moveToElement,WebElement clickElement){
        Actions actions = new Actions(driver);
        actions.moveToElement(moveToElement);
        actions.build().perform();
        waitForElement(driver,clickElement);
        actions = new Actions(driver);
        actions.moveToElement(clickElement).click();
        actions.build().perform();
    }

    public static void embedScreenshot(WebDriver driver,Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (Exception e) {
            throw new CustomException(e, "Exception while embedding a screenshot");

        }
    }

}
