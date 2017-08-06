package PageObjects;

import Helpers.Assertion;
import Helpers.DriverHelper;
import Helpers.RunHelper;
import Steps.RunContext;
import gherkin.lexer.Ru;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Todo {

    @FindBy(how = How.CSS, using = "#new-todo")
    public WebElement todo;

    @FindBy(how = How.CSS, using = "#todo-list")
    public WebElement todoList;

    @FindBy(how = How.CSS, using = "#toggle-all")
    public WebElement toggleAll;


    @FindBy(how = How.CSS, using = "a[text='Completed'] ")
    public WebElement completedFilter;

    @FindBy(how = How.CSS, using = "a[text='Active'] ")
    public WebElement activeFilter;

    @FindBy(how = How.CSS, using = "#clear-completed")
    public WebElement clearAll;

    public Todo(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void addEntry(WebDriver driver, String todoText){
        DriverHelper.waitAndSendKeys(driver,todo,todoText);
        DriverHelper.sendSpecialKeys(todo,"Enter");

    }
    public static WebElement getMatchingElement(final WebDriver driver,final WebElement todoListItems,String newText){
        List<WebElement> todoItems = todoListItems.findElements(By.cssSelector("li"));
        for(WebElement element : todoItems) {
            if(newText.equals(element.getText()))
                return element;
        }
        return null;
    }
    public void verifyAddedEntry(RunContext runContext, String todoText){
        List<WebElement> todoItems = todoList.findElements(By.cssSelector("li"));
        Boolean result = false;
        for(WebElement element : todoItems){
           result= Assertion.assertEquals("Verifying new entry ",todoText,element.getText(),false);
           if(result)
               break;
        }

        if(result)
            runContext.scenario.write("Pass : New Entry added with text - "+todoText);
        else
            runContext.scenario.write("Fail : New Entry didnt get added with text - "+todoText);
    }

    public void editExistingEntry(RunContext runContext,String todoText,String newText){
        List<WebElement> todoItems = todoList.findElements(By.cssSelector("li"));
        Boolean result = false;
        for(WebElement element : todoItems){
            result= Assertion.assertEquals("Verifying new entry ",todoText,element.getText(),false);
            if(result) {
                DriverHelper.performDoubleClickAndSendText(runContext.driver, element,newText);
                break;
            }
        }
    }

    public void markEntryAs(RunContext runContext,String newText, String markAs){
              WebElement element =  getMatchingElement(runContext.driver,todoList,newText);
              Boolean selectElement = false;
              if(markAs.equalsIgnoreCase("complete") && !element.getAttribute("class").contains("completed"))
                  selectElement = true;
               else if(markAs.equalsIgnoreCase("active") && element.getAttribute("class").contains("completed"))
                 selectElement = true;
                  if(selectElement)
                      DriverHelper.clickElement(element.findElement(By.cssSelector("input")));
            }

    public void toggleAll(){
        DriverHelper.clickElement(toggleAll);

    }

    public void destroyElement(RunContext runContext, String text){
       WebElement element= getMatchingElement(runContext.driver,todoList,text);
       DriverHelper.moveToAndClick(runContext.driver,element,element.findElement(By.cssSelector("button.destroy")));

    }
    public void applyFilter(String filterBy){
        switch (filterBy.toLowerCase()){

            case "completed":
                DriverHelper.clickElement(completedFilter);
                break;

            case "active":
                DriverHelper.clickElement(activeFilter);
                break;

        }
        DriverHelper.clickElement(toggleAll);

    }




}
