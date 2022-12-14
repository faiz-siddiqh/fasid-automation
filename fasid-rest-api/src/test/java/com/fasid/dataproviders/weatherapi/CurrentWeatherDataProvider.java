package com.fasid.dataproviders.weatherapi;

import org.testng.annotations.DataProvider;

public class CurrentWeatherDataProvider {

    @DataProvider
    public Object[][] getCityDetails() {

        return new Object[][]{
                {"Bengaluru"},
                {"Manchester"}
        };
    }


    @DataProvider
    public Object[][] getCityId() {

        return new Object[][]{
                {"1277333","Bengaluru"},
                {"2643123","Manchester"}
        };
    }

}
