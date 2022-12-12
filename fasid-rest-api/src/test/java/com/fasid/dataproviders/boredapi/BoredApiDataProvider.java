package com.fasid.dataproviders.boredapi;

import org.testng.annotations.DataProvider;

public class BoredApiDataProvider {

    @DataProvider
    public Object[][] getActivityType() {

        //Available options
        /*
        "education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork"
         */
        return new String[][]{
                {"recreational"}, {"education"}
        };

    }

}
