package com.steps.common;

import java.io.File;
import java.util.Collection;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import com.common.drivers.DriverType;
import com.common.utils.DriverUtils;

import org.apache.log4j.Logger;

import io.cucumber.java.Scenario;

public class Hooks {
	private static WebDriver driver;
	private static Collection<String> taggs;
	public static Scenario scenario;
	private static String TempDriverLocation;
	private static File TempDriver;
	private static DriverType runningDriver;
	private static String emailEnviado;
	private static Logger log;

	@Before
	public void runBeforeWithOrder(Scenario scenario) throws Throwable {
		Hooks.setScenario(scenario);
		keepScenarion(scenario);
	}	
}

