package com.fasid.greenkart;

import com.fasid.greenkart.actions.GreenkartLoginActions;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class GreenkartBaseUITest extends AbstractFasidUITest {

    @BeforeMethod(alwaysRun = true)
    public void login(Method method) {
        //Handle login

        new GreenkartLoginActions()
                .navigateToBaseUrl()
                .hasLoaded();

    }

}
