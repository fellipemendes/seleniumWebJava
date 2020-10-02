package com.steps.common;

import io.cucumber.java.pt.*;
import com.common.drivers.DriverType;
import com.common.utils.DriverUtils;
import io.cucumber.java.After;

public class CommonSteps {

    @Dado("^que estou usando \"([^\"]*)\" browser$")
    public void selectDriver(DriverType driver) throws Throwable {
        DriverUtils.selecionaBrowser(driver);
    }

    @After
    public void close() throws Exception {
        if (Hooks.scenario.isFailed()) {
            try {
                DriverUtils.tirarScreenShot();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        DriverUtils.fecharBrowser();
    }
}
