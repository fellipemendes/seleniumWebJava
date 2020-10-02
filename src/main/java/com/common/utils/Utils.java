package com.common.utils;
import com.common.utils.RunnerClass.Runner;
import com.pages.pages1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class Utils {
    public static String fileName="";
    pages1 basesPage;
    Actions builder = new Actions(DriverUtils.getDriver());

    public Utils(pages1 basesPage){
        this.basesPage = basesPage;
    }

    public String returnTextElement(String locator, String element) {
        String textElement = "";
        if (locator.toUpperCase().equals("XPATH")) {
            WebElement object = DriverUtils.getDriver().findElement(By.xpath(element));
            textElement = object.getAttribute("textContent").trim();
        }
        return textElement;
    }

    public String returnAttributeElement(String by, String attribute){
        return DriverUtils.getDriver().findElement(By.xpath(by)).getAttribute(attribute);
    }

    public void waitLoadingBases() throws InterruptedException {
        String text = null;
        int time = 0;
        while (text == null || text.equals("0 bases encontradas")){
            text = returnTextElement("XPATH", "//h2[@class='section__title ng-star-inserted']");
            Thread.sleep(500);
            time++;
            if(time == 60) break;
        }
    }

    public void waitElement(WebElement element) throws Throwable {
        int globalTimeout = 45;
        for (int i = 0; i < globalTimeout; i++) {
            try {
                element.isDisplayed();
                Thread.sleep(1000);
                break;
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
    }

    public void waitTextOnElement (String locator, String element, String text) {
        try {
            switch (locator.toUpperCase()) {
                case "NAME":
                    DriverUtils.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name(element), text));
                    break;
                case "ID":
                    DriverUtils.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(element), text));
                    break;
                case "XPATH":
                    DriverUtils.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(element), text));
                    break;
                case "CLASS":
                    DriverUtils.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(element), text));
                    break;
            }
        }catch (Exception e){
            System.out.println("-----------------------" + e.getMessage());
        }
    }

    public void waitVisibilityOfElementLocated (String locator, String element) {
        try {
            switch (locator.toUpperCase()) {
                case "NAME":
                    DriverUtils.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
                    break;
                case "ID":
                    DriverUtils.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
                    break;
                case "XPATH":
                    DriverUtils.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
                    break;
                case "CLASS":
                    DriverUtils.wait.until(ExpectedConditions.elementToBeClickable(By.className(element)));
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("------------------- " + e.getMessage());
        }
    }

    public void waitPresenceOfElementLocated (String locator, String element) {
        try {
            switch (locator.toUpperCase()) {
                case "NAME":
                    DriverUtils.wait.until(ExpectedConditions.presenceOfElementLocated(By.name(element)));
                    break;
                case "ID":
                    DriverUtils.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
                    break;
                case "XPATH":
                    DriverUtils.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
                    break;
                case "CLASS":
                    DriverUtils.wait.until(ExpectedConditions.presenceOfElementLocated(By.className(element)));
                    break;
            }
        }catch (Exception e){
            System.out.println("--------------------------" + e.getMessage());
        }

    }

    public void arquivoBaseUpload(String Perfil) throws Exception {
        fileName = Perfil;
        try {
            basesPage.setTxtCaminhoBase().sendKeys(Runner.JarFileName + "/data/" + Perfil);
        }catch (Exception e){
            basesPage.setTxtCaminhoBase().sendKeys(System.getProperty("user.dir")+ "/src/main/resources/data/" + Perfil);
        }
        DriverUtils.tirarScreenShot();
    }

    //Valida a seleção dos checkbox de seleção de base
    public void validaCheckModalUploadBase(String perfil) throws Throwable {
        //waitPresenceOfElementLocated("XPATH", "//button[@class='button']");
        waitElement(basesPage.btnIniciarUploadBase);
        builder.click(basesPage.cmbBaseType).build().perform();

        switch (perfil) {
            case "PF-PJ":
                validaCheckModalUpload_PF_PJ();
                break;
            case "PF-ANALYTICS":
                validaCheckModalUpload_PF_Estudo_Modelagem();
                break;
            case "PJ-ANALYTICS":
                validaCheckModalUpload_PJ_Estudo_Modelagem();
                break;
        }
    }

    public void validaCheckModalUpload_PF_PJ() throws Throwable {
        waitElement(basesPage.checkFilePF);
        validaActionsButtons_False();

        assertThat(basesPage.lblPortfolioManagementUpload.getText().trim()).isEqualTo("A");

        validaCheckModalUpload_PF_True();
        checkAnalyticNotVisible();
        validaActionsButtons_True();

        validaCheckModalUpload_PJ_True();
        checkAnalyticNotVisible();
        validaActionsButtons_True();
        builder.click(basesPage.checkFilePJ).build().perform();
        validaActionsButtons_False();
    }

    private void validaActionsButtons_True() {
        assertThat(basesPage.btnProsseguirUpload.isEnabled()).isTrue();
    }

    private void validaActionsButtons_False() {
        assertThat(basesPage.btnProsseguirUpload.isEnabled()).isFalse();
    }

    private void validaCheckModalUpload_PJ_True() {
        builder.click(basesPage.checkFilePJ).build().perform();
        assertThat(basesPage.checkFilePJ.isSelected()).isTrue();
        assertThat(basesPage.checkFilePF.isSelected()).isFalse();
    }

    private void validaCheckModalUpload_PF_True() {
        builder.click(basesPage.checkFilePF).build().perform();
        assertThat(basesPage.checkFilePF.isSelected()).isTrue();
    }

    private void validaCheck_PF_PJ_Visible_NotSelect() {
        assertThat(basesPage.checkFilePJ.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePF.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePJ.isSelected()).isFalse();
        assertThat(basesPage.checkFilePF.isSelected()).isFalse();
    }

    private void validaCheck_PF_Visible_NotSelect() {
        assertThat(basesPage.checkFilePF.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePF.isSelected()).isFalse();
    }

    private void validaCheck_PJ_Visible_NotSelect() {
        assertThat(basesPage.checkFilePJ.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePJ.isSelected()).isFalse();
    }

    private void validaCheckModalUpload_Estudo_True() {
        builder.click(basesPage.checkFileEstudo).build().perform();
        assertThat(basesPage.checkFileEstudo.isSelected()).isTrue();
        assertThat(basesPage.checkFileModelagem.isEnabled()).isTrue();
    }

    private void validaCheckModalUpload_PF_Estudo_True() {
        validaCheckModalUpload_Estudo_True();
        assertThat(basesPage.checkFileModelagem.isSelected()).isFalse();
        validaCheckModalUpload_PF_True();
    }

    private void validaCheckModalUpload_Modelagem_True() {
        builder.click(basesPage.checkFileModelagem).build().perform();
        assertThat(basesPage.checkFileModelagem.isSelected()).isTrue();
        assertThat(basesPage.checkFileEstudo.isEnabled()).isTrue();
        assertThat(basesPage.checkFileEstudo.isSelected()).isFalse();
    }

    private void validaCheckModalUpload_PF_Modelagem_True() {
        validaCheckModalUpload_Modelagem_True();
        validaCheckModalUpload_PF_True();
    }

    private void validaCheckModalUpload_Estudo_Modelagem_True() {
        assertThat(basesPage.lblAnalyticalUpload.getText().trim()).isEqualTo("A");

        validaCheckModalUpload_Estudo_True();
        validaCheck_PF_Visible_NotSelect();
        builder.click(basesPage.checkFileEstudo).build().perform();
        validaActionsButtons_False();
        validaCheckModalUpload_Modelagem_True();
        validaCheck_PF_Visible_NotSelect();
        validaActionsButtons_True();
        builder.click(basesPage.checkFileModelagem).build().perform();
        validaActionsButtons_False();
    }

    private void validaCheckModalUpload_PF_Estudo_Modelagem_True() throws Throwable {
        assertThat(basesPage.checkFileModelagem.isSelected()).isTrue();
        assertThat(basesPage.checkFileEstudo.isSelected()).isTrue();
        assertThat(basesPage.checkFilePF.isSelected()).isTrue();
        checkPJNotVisible();
    }

    private void validaCheckModalUpload_PJ_Estudo_Modelagem_True() throws Throwable {
        assertThat(basesPage.checkFileEstudo.isSelected()).isTrue();
        assertThat(basesPage.checkFileModelagem.isSelected()).isTrue();
        assertThat(basesPage.checkFilePJ.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePJ.isSelected()).isFalse();
        checkPFNotVisible();
    }

    public void validaCheckModalUpload_PF_Estudo_Modelagem() throws Throwable {
        waitElement(basesPage.checkFilePF);
        assertThat(basesPage.lblPortfolioManagementUpload.getText().trim()).isEqualTo("A");
        assertThat(basesPage.lblAnalyticalUpload.getText().trim()).isEqualTo("A");
        validaCheckModalUpload_Estudo_Modelagem_True();
        checkPJNotVisible();
        validaCheckModalUpload_PF_Estudo_True();
        builder.click(basesPage.checkFileEstudo).build().perform();
        builder.click(basesPage.checkFilePF).build().perform();
        validaCheckModalUpload_PF_Modelagem_True();
        validaCheckModalUpload_Estudo_True();
        validaCheckModalUpload_PF_Estudo_Modelagem_True();
        validaActionsButtons_True();
    }

    public void validaCheckModalUpload_PJ_Estudo_Modelagem() throws Throwable {
        waitElement(basesPage.checkFilePJ);
        assertThat(basesPage.lblPortfolioManagementUpload.getText().trim()).isEqualTo("As");
        assertThat(basesPage.lblAnalyticalUpload.getText().trim()).isEqualTo("A");
        checkPFNotVisible();

        validaCheckModalUpload_Estudo_True();
        assertThat(basesPage.checkFileModelagem.isSelected()).isFalse();
        validaActionsButtons_True();
        checkPJVisibleNotSelect();
        builder.click(basesPage.checkFilePJ).build().perform();
        checkAnalyticsVisibleNotSelected();
        validaActionsButtons_True();

        validaCheckModalUpload_Modelagem_True();
        checkPJVisibleNotSelect();
        validaActionsButtons_True();
        builder.click(basesPage.checkFilePJ).build().perform();
        checkAnalyticsVisibleNotSelected();
        validaActionsButtons_True();

        validaCheckModalUpload_Estudo_True();
        validaActionsButtons_True();
        builder.click(basesPage.checkFileEstudo).build().perform();
        validaCheckModalUpload_Modelagem_True();
        validaActionsButtons_True();
        builder.click(basesPage.checkFileEstudo).build().perform();
        validaCheckModalUpload_PJ_Estudo_Modelagem_True();
        validaActionsButtons_True();
    }

    public void checkAnalyticNotVisible(){
        assertThat(basesPage.checkFileEstudo.isDisplayed()).isTrue();
        assertThat(basesPage.checkFileModelagem.isDisplayed()).isTrue();
    }

    public void checkPJNotVisible(){
        assertThat(basesPage.checkFilePJ.isEnabled()).isFalse();
        assertThat(basesPage.checkFilePJ.isSelected()).isFalse();
    }

    public void checkPFNotVisible(){
        assertThat(basesPage.checkFilePF.isEnabled()).isFalse();
        assertThat(basesPage.checkFilePF.isSelected()).isFalse();
    }

    public void checkPJVisibleNotSelect(){
        assertThat(basesPage.checkFilePJ.isEnabled()).isTrue();
        assertThat(basesPage.checkFilePJ.isSelected()).isFalse();
    }

    public void checkAnalyticsVisibleNotSelected(){
        assertThat(basesPage.checkFileEstudo.isEnabled()).isTrue();
        assertThat(basesPage.checkFileModelagem.isEnabled()).isTrue();
        assertThat(basesPage.checkFileEstudo.isSelected()).isFalse();
        assertThat(basesPage.checkFileModelagem.isSelected()).isFalse();
    }

}
