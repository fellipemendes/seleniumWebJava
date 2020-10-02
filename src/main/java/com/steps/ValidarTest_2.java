package com.steps;

import com.common.utils.*;
import com.pages.pages1;
import com.pages.pages2;
import com.pages.pages4;
import com.pages.PrincipalPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidarTest_2 {

    pages1 basesPage;
    PrincipalPage pginicial;
    Utils utils;
    pages4 pages4;
    UtilsDashPJ utilsDashPJ;
    GetLocalStorage getLocalStorage;
    pages2 pages2;
    static boolean Benchmark;

    public ValidarTest_2(
            pages1 basesPage,
            PrincipalPage pginicial,
            Utils utils,
            pages4 pages4,
            UtilsDashPJ utilsDashPJ,
            GetLocalStorage getLocalStorage,
            pages2 pages2) {
        this.basesPage = basesPage;
        this.pginicial = pginicial;
        this.utils = utils;
        this.pages4 = pages4;
        this.utilsDashPJ = utilsDashPJ;
        this.getLocalStorage = getLocalStorage;
        this.pages2 = pages2;
    }

    @Quando("abrir a tela \"([^\"]*)\"$")
    public void abrirATelaDePortfolioEmpresaPerfil(String perfil) {
        try {
            if (perfil.equals("PPP")) {
                utils.waitElement(pginicial.linkMenuAPMPJ);
                pginicial.clickAPMPJmenu();
                //apmpjPO.aceitarTermo();
            } else {
                utils.waitElement(pginicial.linkMenuAPMPJ_Empresa);
                pginicial.clickAPMPJmenu_Empresa();
            }
            utils.waitElement(pages4.cmbBaseCliente);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Então("deverá conter os elementos para selecionar base")
    public void deveráConterOsElementosParaSelecionarBaseDoCliente() throws Throwable {
        utilsDashPJ.validarPortfolioEmpresaPagina();
    }

    @E("selecionar a base")
    public void selecionarABaseClienteEOTemplate() throws Throwable {
        utils.waitElement(pages4.cmbBaseCliente);
        pages4.clickCmbCliente();
        utils.waitElement(pages4.cmbPrimeiraBaseCliente);
        pages4.clickCmbPrimeiraBaseCliente();
        utils.waitElement(pages4.cmbTemplateRiscoFaturamento);
    }

    @Então("deverá apresentar o dashboard e os itens de informações e manipulação")
    public void deveráApresentarODashboardEOsItensDeInformaçõesEManipulação() throws Throwable {
        utilsDashPJ.validarDashboardClientCarregado();
    }

    @Então("deverá conter os elementos para aplicar os filtros")
    public void deveráConterOsElementosParaAplicarOsFiltrosNaBase() throws Throwable {
        utils.waitElement(pages4.btnFiltros);
        utilsDashPJ.validarFiltrosElementos();

    }

    @E("selecionar um")
    public void selecionarUmBase() throws Throwable {
        if (!getLocalStorage.getObjectBundle("bundle_pj").equals("light")) {
            selecionarABaseClienteEOTemplate();
            utilsDashPJ.validarDashboardClientCarregado();
            Benchmark = true;
        } else {
            Benchmark = false;
        }
        DriverUtils.tirarScreenShot();
    }

    @Então("devera apresentar as bases lado a lado com informações corretas")
    public void deveraApresentarAsBasesLadoALadoComInformaçõesCorretas() throws Throwable {
        if (Benchmark) {
            utils.waitElement(pages2.badgeCnaePredominante);
            assertThat(pages2.badgeCnaePredominante.isDisplayed());

            utilsDashPJ.validarDashboardCarregado();

            Log.info("Validar as celulas do ");
            for(int linha=0; linha <= 2; linha++){
                for(int coluna=0; coluna <= 2; coluna++){
                    WebElement celulaDashClient = DriverUtils.getDriver()
                            .findElement(By.xpath("//div[@data-test='dashboard-cell-" + coluna + "-" + linha + "-client']"));
                    WebElement celulaDash = DriverUtils.getDriver()
                            .findElement(By.xpath("//div[@data-test='dashboard-cell-" + coluna + "-" + linha));

                    assertThat(celulaDashClient.getAttribute("textContent").trim()).isNotEmpty();
                    assertThat(celulaDashgetAttribute("textContent").trim()).isNotEmpty();
                }
            }

            utilsDashPJ.validarTransacoesDisponiveis();

            DriverUtils.tirarScreenShot();
        } else {
            DriverUtils.escreverNoCenario("mensagem");
            DriverUtils.tirarScreenShot();
        }
    }
}
