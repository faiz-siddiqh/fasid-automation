package com.fasid.greenkart.tests.homepage;

import com.fasid.greenkart.GreenkartBaseUITest;
import com.fasid.greenkart.actions.GreenkartHomepageActions;
import com.fasid.greenkart.dataprovider.homepage.GreenkartHomepageDataProvider;
import com.fasid.groups.TestGroups;
import org.testng.annotations.Test;

public class GreenkartHomePageTest extends GreenkartBaseUITest {

    @Test(testName = "Search A Product On Greencart ",
            groups = TestGroups.GREENKART,
            dataProviderClass = GreenkartHomepageDataProvider.class,
            dataProvider = "searchProduct"
    )
    public void validateAbleToSearchAProductOnGreenkart(String vegetableName) {

        new GreenkartHomepageActions()
                .hasLoaded()
                .searchProduct(vegetableName);

    }

}
