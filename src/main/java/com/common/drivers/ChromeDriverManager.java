package com.common.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager {
	private static WebDriver driver;
	private static String driverPath = "src/main/resources/chromedriver";

	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();

		return driver;
	}

}