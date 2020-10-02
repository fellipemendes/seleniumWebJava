package com.pages;

import com.common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pages3 {

    public pages3() {
        PageFactory.initElements(DriverUtils.getDriver(), this);
    }

    @FindBy(xpath = "//button[@class='btn btn-full']")
    public  WebElement btnAplicarFiltro;

    @FindBy(xpath = "//a[@class='ng-star-inserted']//img")
    public  WebElement btnSelecRemovTodosFiltros;

    @FindBy(xpath = "//card-select[@data-test='filter-risk_points']")
    public  WebElement cmbPontuRiscoFiltros;


}
