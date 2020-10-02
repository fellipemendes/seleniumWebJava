package com.common.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.log4j.Level;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import com.common.drivers.DriverManager;
import com.common.drivers.DriverType;

import io.cucumber.java.Scenario;

public class DriverUtils {

	public static WebDriver driver;
	public static Scenario scenario;
	public static WebDriverWait wait;
	

	public static void selecionaBrowser(DriverType selDriver) throws MalformedURLException, InterruptedException {
		driver = DriverManager.getManager(selDriver);
	}

	public static void setScenario(Scenario sce) {
		scenario = sce;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void navegar(String url) {
		driver.get(url);
	}

	public static void esperar(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public static void fecharBrowser() throws IOException, InterruptedException {
		driver.close();
		driver.quit();
	}

	public static void maximizarBrowser() {
		driver.manage().window().maximize();
	}

	public static void tirarScreenShot(){
		try {
			Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(getDriver());
			BufferedImage originalImage = fpScreenshot.getImage();
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				ImageIO.write(originalImage, "png", baos);
				baos.flush();
				scenario.embed(baos.toByteArray(), "image/png");
			}catch (RuntimeException e){
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
