package com.fasid.core.ui.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;

public interface HasService<W extends WebDriver, D extends DriverService, C extends Capabilities> {

    D startService() throws IOException;

    W getLocalDriver(D service, C capabilities);

    void stopService();

}
