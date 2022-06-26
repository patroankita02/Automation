package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks
{
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefintions m = new StepDefintions();
        if(StepDefintions.placeid==null)
        {
            m.addPlacePayload("Ranjan","French","Asia");
            m.userCallsWithHttpRequest("AddPlaceAPI","POST");
            m.verifyPlace_idCreatedMapsToUsing("Ranjan","getPlaceAPI");
        }

    }
}
