package com.pages;

import com.common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pages2 {

    public pages2() {
        PageFactory.initElements(DriverUtils.getDriver(), this);
    }

    Actions builder = new Actions(DriverUtils.getDriver());

    @FindBy(xpath = "//span[@class='badge__text']")
    public  WebElement badge;

    @FindBy(xpath = "//app-dashboard-pj[@class='ng-star-inserted']//div[@data-test='dashboard-title-column-client']/span[1]")
    public  WebElement lblFaixa;

    public void clickCmbPrimeiraBaseCliente(){
        builder.click(cmbPrimeiraBaseCliente).build().perform();
    }



}
