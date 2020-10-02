package com.common.drivers;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import com.steps.common.Hooks;

public class DriverManager {

	static String driverPath;
	static DriverType type;

	public static WebDriver getManager(DriverType type) throws MalformedURLException, InterruptedException {
		switch (type) {
			case CHROME: ChromeDriverManager chrDrvMng = new ChromeDriverManager();
				Hooks.setRunningDriver(type);
				return chrDrvMng.getDriver();
			case DOCKER_CHROME:
				return DockerChromeManager.DockerChromeDriver();
			default:
				return null;
		}
	}

	public static DriverType getType() {
		return type;
	}

	public static void setType(DriverType type) {
		DriverManager.type = type;
	}
}