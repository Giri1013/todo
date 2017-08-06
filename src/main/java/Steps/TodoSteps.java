package Steps;

import Helpers.DriverHelper;
import Helpers.RunHelper;
import Helpers.StringUtils;
import PageObjects.Todo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TodoSteps extends BaseSteps{
    RunContext runContext;
   public TodoSteps(RunContext runContext){
        super(runContext);
        this.runContext = runContext;
    }

    @Given("^I am on todomvc home page$")
    public void i_am_on_todomvc_home_page(){
        runContext.driver.get("http://todomvc.com");
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I click on \"([^\"]*)\" link$")
    public void i_click_on_link(String linkName){
        linkName = StringUtils.concatWordsInitLower(linkName);
        DriverHelper.clickElement(RunHelper.getWebElementByName(runContext.driver,"BasePage",linkName));
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I add a Todo item entry with text ([^\"]*)$")
    public void i_add_a_Todo_item_entry(String todoText){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","addEntry",runContext.driver,todoText);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @Then("^I see the new todo item ([^\"]*) in the list of todos$")
    public void i_see_the_new_todo_item_in_the_list_of_todos(String todoText){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","verifyAddedEntry",runContext,todoText);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I edit the new entry ([^\"]*) text to ([^\"]*)$")
    public void i_edit_the_new_entry_text_to(String todoText,String newText){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","editExistingEntry",runContext,todoText,newText);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }
    @When("^I mark the entry with text ([^\"]*) as \"([^\"]*)\"$")
    public void i_mark_the_entry_with_text_as(String newText, String markAs){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","markEntryAs",runContext,newText,markAs);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I mark all todo items as \"([^\"]*)\"$")
    public void i_mark_all_todo_items_as(String toggleAll){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","toggleAll");
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I filter the todo entries by \"([^\"]*)\" state$")
    public void i_filter_the_todo_entries_by(String filterBy){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","applyFilter",filterBy);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @When("^I remove a todo entry with text ([^\"]*)$")
    public void I_remove_a_todo_entry_with_text(String text){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","destroyElement",runContext,text);
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

    @Then("^I clear all completed$")
    public void I_clear_all_completed(){
        RunHelper.executePageObjectMethod(runContext.driver,"Todo","toggleAll");
        DriverHelper.clickElement(RunHelper.getWebElementByName(runContext.driver,"Todo","clearAll"));
        DriverHelper.embedScreenshot(runContext.driver,runContext.scenario);
    }

}
