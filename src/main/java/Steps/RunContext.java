package Steps;

import Helpers.CustomException;
import Helpers.DriverHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

public class RunContext {

   public  WebDriver driver;
   public Scenario scenario;

    @Before
    public void init(Scenario scenario){
    //driver = DriverHelper.getDriver("chrome");
     String browserType = "";
     /*try {
          browserType = System.getProperty("browser");
       }
     catch (Exception e){
      throw new CustomException(e,"Exception while reading command line args");
     }
     browserType = ((browserType==null || browserType.equals(""))?"chrome":browserType); */
     driver = DriverHelper.getDriver("chrome");
    this.scenario = scenario;
    }
    @After
    public void tearDown(Scenario scenario){
    driver.close();
    }
}
