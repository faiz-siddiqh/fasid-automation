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
                {"1277333", "Bengaluru"},
                {"2643123", "Manchester"}
        };
    }

    @DataProvider
    public Object[][] getUnitsOfMeasurement() {

        /*
    Temperature is available in Fahrenheit, Celsius and Kelvin units.

    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call

         */

        return new Object[][]{
                {"1277333", "Bengaluru", "metric"},
                {"2643123", "Manchester", "imperial"}
        };
    }

    @DataProvider
    public Object[][] getAllParamsForNegativeTest() {

        /*
    Temperature is available in Fahrenheit, Celsius and Kelvin units.

    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call

         */

        return new Object[][]{
                {"1277333", "Bangalore", "metric" ,"fr"}
        };
    }

}
