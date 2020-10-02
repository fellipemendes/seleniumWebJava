package com.common.drivers;

import com.common.utils.PropertiesUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.common.drivers.EnvType.*;

public class DockerChromeManager {

	public static WebDriver DockerChromeDriver() throws MalformedURLException, InterruptedException {
		String urlDocker = null;
		String TestEnv = System.getProperty("TEST_ENV");
		if (TestEnv == null){TestEnv = "";}
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("headless");
		options.addArguments("window-size=1920x1080");
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		options.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		if(TestEnv.equals(DEV.name()) || TestEnv.equals(QA.name())){
			urlDocker = PropertiesUtil.getProperty("hub.x");
			System.out.println("-- Hub x");
		}else{
			urlDocker = PropertiesUtil.getProperty("hub.y");
			System.out.println("-- Hub y");
		}
		return new RemoteWebDriver(new URL(urlDocker), options);
	}
}