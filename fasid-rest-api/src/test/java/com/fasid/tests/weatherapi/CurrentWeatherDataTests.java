package com.fasid.tests.weatherapi;

import com.fasid.BaseApiTest;
import com.fasid.assertions.Asserts;
import com.fasid.dataproviders.weatherapi.CurrentWeatherDataProvider;
import com.fasid.enums.MethodType;
import com.fasid.groups.TestGroups;
import com.fasid.weatherapi.actions.WeatherDataActions;
import com.fasid.weatherapi.dto.WeatherDataDTO;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CurrentWeatherDataTests extends BaseApiTest {


    @Test(testName = "Fetch Weather by City Name",
            dataProvider = "getCityDetails",
            dataProviderClass = CurrentWeatherDataProvider.class,
            groups = TestGroups.WEATHER_API)
    public void fetchWeatherDataByCityName(String cityName) {

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("q", cityName);

        WeatherDataDTO results = new WeatherDataActions()
                .getApiResponse(MethodType.GET, WeatherDataDTO.class, queryParam);

        Asserts.assertEquals(cityName, results.getName(), "Error !! CityNames doesn't match");

    }

    @Test(testName = "Fetch Weather by City Id",
            dataProvider = "getCityId",
            dataProviderClass = CurrentWeatherDataProvider.class,
            groups = TestGroups.WEATHER_API)
    public void fetchWeatherDataByCityId(String id, String cityName) {

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("id", id);

        WeatherDataDTO results = new WeatherDataActions()
                .getApiResponse(MethodType.GET, WeatherDataDTO.class, queryParam);

        Asserts.assertEquals(Integer.parseInt(id), results.getId(), "Error !! Id's doesn't match");
        Asserts.assertEquals(cityName, results.getName(), "Error !! Error !! CityNames doesn't match");

    }

    @Test(testName = "Fetch Weather by City Id",
            dataProvider = "getUnitsOfMeasurement",
            dataProviderClass = CurrentWeatherDataProvider.class,
            groups = TestGroups.WEATHER_API)
    public void validateTemperatureInParticularUnitsOfMeasurement(String id, String cityName, String unitsOfMeasurement) {

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("id", id);
        queryParam.put("units", unitsOfMeasurement);

        WeatherDataDTO results = new WeatherDataActions()
                .getApiResponse(MethodType.GET, WeatherDataDTO.class, queryParam);

        Asserts.assertEquals(Integer.parseInt(id), results.getId(), "Error !! Id's doesn't match");
        Asserts.assertEquals(cityName, results.getName(), "Error !! Error !! CityNames doesn't match");


    }

    @Test(testName = "Validate Test Failures",
            dataProvider = "getAllParamsForNegativeTest",
            dataProviderClass = CurrentWeatherDataProvider.class,
            groups = TestGroups.WEATHER_API)
    public void validateTestFailures(String id, String cityName, String unitsOfMeasurement,String language) {

        Map<String ,Object> queryParams=new HashMap<>();
        queryParams.put("q",cityName);
        queryParams.put("id",id);
        queryParams.put("units",unitsOfMeasurement);
        queryParams.put("lang",language);

        //Check Unauthorized access 1)Without token 2) with invalid token
        new WeatherDataActions()
                .validateTestFailureOnSendingApiWithoutToken(queryParams);

        //Validate failure with Incorrect query params and values
        new WeatherDataActions()
                .validateErrorOnIncorrectQueryParams(queryParams);

    }



}
