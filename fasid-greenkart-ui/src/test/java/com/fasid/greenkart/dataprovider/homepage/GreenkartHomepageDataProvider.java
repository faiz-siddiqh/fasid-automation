package com.fasid.greenkart.dataprovider.homepage;

import org.testng.annotations.DataProvider;

public class GreenkartHomepageDataProvider {

    @DataProvider
    public Object[][] searchProduct() {
        String vegetableName = "Brinjal";

        return new Object[][]{
                new String[]{vegetableName}
        };

    }


}
