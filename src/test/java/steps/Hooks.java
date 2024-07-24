package steps;

import cucumber.api.Scenario;
import driver.ThreadLocalDriver;

public class Hooks extends BaseSteps {
    public void setup() {
        if (ThreadLocalDriver.getTLDriver()!= null){
            setupCucumber();
        }else {
            System.out.println("Setup Switch API Test");
        }

    }
    public void stop(Scenario scenario) {
        if (ThreadLocalDriver.getTLDriver()!= null){
            teardown(scenario);
        }else {
            System.out.println("Finish Switch API Test");
        }
    }
}
