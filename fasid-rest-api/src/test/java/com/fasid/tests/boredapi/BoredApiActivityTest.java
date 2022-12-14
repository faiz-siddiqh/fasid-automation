package com.fasid.tests.boredapi;

import com.fasid.boredapi.actions.ActivityActions;
import com.fasid.boredapi.dto.ActivityDTO;
import com.fasid.assertions.Asserts;
import com.fasid.dataproviders.boredapi.BoredApiDataProvider;
import com.fasid.enums.MethodType;
import com.fasid.BaseApiTest;
import org.testng.annotations.Test;

public class BoredApiActivityTest extends BaseApiTest {


    @Test(description = "Get a Random Activity using the Bored API")
    public void getARandomActivity() {

        ActivityDTO randomActivity = new ActivityActions()
                .createActivitySpec(MethodType.GET, ActivityDTO.class);

        System.out.println(randomActivity.toString());

    }

    @Test(description = "Get a Random activity and Get the activity by Key")
    public void searchAnActivity() {

        ActivityDTO randomActivity = new ActivityActions()
                .createActivitySpec(MethodType.GET, ActivityDTO.class);

        String keyValue = randomActivity.getKey();
        String activityName = randomActivity.getActivity();

        // Fetch the random activity using the key created

        ActivityDTO getRandomActivity = new ActivityActions()
                .getActivitySpec(MethodType.GET, ActivityDTO.class, keyValue);

        Asserts
                .assertEquals(keyValue, getRandomActivity.getKey(), "Error !! Key Value do not match");
        Asserts
                .assertEquals(activityName, getRandomActivity.getActivity(), "Error !!! Returned activity is not same");


    }

    @Test(testName = "Fetch Random Activity of given type",
            dataProvider = "getActivityType",
            dataProviderClass = BoredApiDataProvider.class)
    public void getRandomActivityByType(String activityType) {

        ActivityDTO activityDTO = new ActivityActions()
                .getActivityByTypeSpec(MethodType.GET, ActivityDTO.class, activityType);

        Asserts
                .assertEquals(activityType, activityDTO.getType(), "Error !! Incorrect Activity type");


    }
}
