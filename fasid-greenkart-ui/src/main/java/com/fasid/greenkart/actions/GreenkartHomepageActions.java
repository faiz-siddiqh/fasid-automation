package com.fasid.greenkart.actions;

import com.fasid.assertions.Asserts;
import com.fasid.core.ui.actions.AbstractActions;
import com.fasid.core.ui.utils.BrowserAction;
import com.fasid.core.ui.waits.Waits;

import static com.fasid.greenkart.pages.GrenkartBasePage.*;

public class GreenkartHomepageActions implements AbstractActions<GreenkartHomepageActions> {

    private BrowserAction browserAction;
    private Waits waits;

    public GreenkartHomepageActions() {
        this.browserAction = BrowserAction.getInstance();
        this.waits = new Waits();
    }

    @Override
    public GreenkartHomepageActions hasLoaded() {
        return this;
    }


    public GreenkartHomepageActions searchProduct(String product) {

        browserAction
                .clearAndTypeAndWait(productSearchBar, product)
                .clickOn(searchSubmit);
        waits
                .awaitForElementToBeDisplayed(productName, 5);

        //Handle Assertions


        return this;
    }
}
