package com.fasid.greenkart.actions;

import com.fasid.config.Config;
import com.fasid.core.ui.actions.AbstractActions;
import com.fasid.core.ui.utils.BrowserAction;
import com.fasid.core.ui.waits.Waits;

import static com.fasid.greenkart.pages.GrenkartBasePage.greenkartLabel;

public class GreenkartLoginActions implements AbstractActions<GreenkartLoginActions> {

    private Waits waits;

    private BrowserAction browserAction;

    public GreenkartLoginActions() {
        waits = new Waits();
        browserAction = BrowserAction.getInstance();
    }


    @Override
    public GreenkartLoginActions hasLoaded() {
        //Implement some locator or Url which contains login and validate here
        waits
                .awaitForElementToBeDisplayed(greenkartLabel, 20);

        return this;
    }

    public GreenkartLoginActions navigateToBaseUrl() {
        browserAction
                .navigateTo(Config.getBaseUrl());

        return this;
    }


}
