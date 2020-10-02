package com.pages;

import com.common.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pages1 {

    public pages1() {
        PageFactory.initElements(DriverUtils.getDriver(), this);
    }

    @FindBy(id="input")
    public WebElement txtCaminhoBase;

    @FindBy(xpath = "//h3[@class='message__title']")
    public WebElement msgTitleUploadBase;

    @FindBy(xpath = "//h3[@class='message__subtitle']")
    public WebElement msgSubtitleUploadBase;

    @FindBy(xpath = "/html/body/app-root/div/main/div/app-base/section[3]/app-base-item[1]/article/div[1]/div[1]/div[2]/h3")
    public WebElement primeiraBase;

    @FindBy(xpath = "//button[@class='button']")
    public WebElement btnIniciarUploadBase;

    @FindBy(xpath = "//div[@class='dropdown-classification-selection__header']")
    public WebElement cmbBaseType;

    @FindBy(id = "Pessoa Física_")
    public WebElement checkFilePF;

    @FindBy(id = "Pessoa Jurídica_")
    public WebElement checkFilePJ;

    @FindBy(id="Modelagem_")
    public WebElement checkFileModelagem;

    @FindBy(id="Estudo_")
    public WebElement checkFileEstudo;

    @FindBy(xpath = "//h4[contains(text()")
    public  WebElement lblPortfolioManagementUpload;

    @FindBy(xpath="//button[@class='button']")
    public WebElement btnProsseguirUpload;

    @FindBy(xpath="//button[@class='button button--secondary']")
    public WebElement btnCancelarUpload;

    @FindBy(xpath = "//h4[@class='module__title'][contains(text()")
    public  WebElement lblAnalyticalUpload;

    @FindBy(xpath = "//button[@data-test='btn-upload']")
    public WebElement btnUp;

    public WebElement setTxtCaminhoBase(){
        return txtCaminhoBase;
    }
}
