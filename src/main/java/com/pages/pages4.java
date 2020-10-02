package com.pages;

import com.common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pages4 {

    public pages4() {
        PageFactory.initElements(DriverUtils.getDriver(), this);
    }

    Actions builder = new Actions(DriverUtils.getDriver());

    @FindBy(xpath = "//app-base-select[@data-test='combobox-")
    public  WebElement cmbBaseCliente;

    @FindBy(xpath = "//app-template-select-pj[@data-test='combobox-template-select']")
    public  WebElement cmbTemplateDisabled;

    public void clickCmbTemplateEnabled(){
        builder.click(cmbTemplateEnabled).build().perform();
    }

    public void clickCmbTemplateRiscoFaturamento(){
        builder.click(cmbTemplateRiscoFaturamento).build().perform();
    }

    public void clickCmbCliente(){
        builder.click(cmbBaseCliente).build().perform();
    }

    public void clickCmbPrimeiraBaseCliente(){
        builder.click(cmbPrimeiraBaseCliente).build().perform();
    }

}
