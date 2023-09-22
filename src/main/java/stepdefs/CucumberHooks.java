package stepdefs;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import webdriver.Driver;

public class CucumberHooks {

    // before scenario
    @Before
    public void beforeScenario(){
        Driver.getDriver();
    }


    // after scenario
    @After
    public void afterScenario(){
        Driver.quit();
    }
}
