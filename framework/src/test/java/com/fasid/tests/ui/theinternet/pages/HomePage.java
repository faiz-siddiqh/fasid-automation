
package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.By.linkText;

/**
 * Home page.
 */
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Home page instance.
     *
     * @return {@link HomePage}
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    /**
     * Gets link locator based on the link name.
     *
     * @param linkText link name
     *
     * @return link locator
     */
    public Locator link (final String linkText) {
        return Locator.constructLocator ().web (linkText (linkText))
            .name (format ("Link [{0}]", linkText))
            .build ();
    }


}
