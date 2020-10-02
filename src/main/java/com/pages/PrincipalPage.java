package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.utils.DriverUtils;

public class PrincipalPage {

	public PrincipalPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	@FindBy(xpath = "//button[@type='button']")
	WebElement botaoInserir;

	@FindBy(xpath = "//h3[@class='text-center']")
	WebElement header;

	public WebElement botaoInserirEmMeuSite() {
		return botaoInserir;
	}

	public WebElement header() {
		return header;
	}

	@FindBy(xpath="//span[contains(text()")
	public WebElement linkMenuAPMPJ;

	@FindBy(xpath="//span[contains(text(),'Empresas')]")
	public WebElement linkMenuAPMPJ_Empresa;

	@FindBy(name="username")
	public WebElement usernameTextBox;

	@FindBy(name="password")
	public WebElement passwordTextBox;

	@FindBy(id="okta-signin-submit")
	public WebElement loginButton;

	@FindBy(xpath="//span[contains(text(),'Bases')]")
	public WebElement linkMenuBases;

	public WebElement username(){
		return usernameTextBox;
	}
	public WebElement password(){
		return passwordTextBox;
	}

	public WebElement Basesmenu()  {
		return linkMenuBases;
	}

	public void clickAPMPJmenu(){
		linkMenuAPMPJ.click();
	}

	public void clickAPMPJmenu_Empresa(){
		linkMenuAPMPJ_Empresa.click();
	}


}