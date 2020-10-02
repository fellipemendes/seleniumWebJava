package com.common.utils;

import com.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class Utils_A {

    pages1 basesPage;
    PrincipalPage pginicial;
    Utils utils;
    pages4 pages4;
    GetLocalStorage getLocalStorage;
    ExtractionPage extractionPage;
    pages3 pages3;
    ExtractionsName extractionsName;
    static String[] siglasEstados ={"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};

    public Utils_A(
            pages1 basesPage,
            PrincipalPage pginicial,
            Utils utils,
            pages4 pages4,
            GetLocalStorage getLocalStorage,
            ExtractionPage extractionPage,
            pages3 pages3,
            ExtractionsName extractionsName){
        this.basesPage = basesPage;
        this.pginicial = pginicial;
        this.utils = utils;
        this.pages4 = pages4;
        this.getLocalStorage = getLocalStorage;
        this.extractionPage = extractionPage;
        this.pages3 = pages3;
        this.extractionsName = extractionsName;
    }

    public void Validar_1() throws Throwable {
        utils.waitElement(pages4.cmbBase);
        assertThat("Base")
                .isEqualTo(utils.returnTextElement("xpath", "//div[@class='card-select overlapping']//p[contains(text(),'Base')]"));
        assertThat("Selecione")
                .isEqualTo(utils.returnTextElement("xpath", "//app-base-select[@data-test='combobox-base-cliente']//div[@class='card-select-header-info']"));

        assertThat(pages4.cmbBaseCliente.isDisplayed());
        assertThat(pages4.cmbBaseCliente.isEnabled());


        assertThat(pages4.cmbTemplateDisabled.isDisplayed());
        assertThat(pages4.cmbTemplateDisabled.isSelected()).isFalse();

        assertThat(pages4.btnFiltros.isDisplayed());
        assertThat(pages4.btnFiltros.isEnabled());

        assertThat(pages4.btnUpBaseFromDashboard.isDisplayed());
    }

    public void Validar_2() throws Throwable {
        utils.waitElement(pages4.bntDownloadPDFDashboard);
        Log.info("Validar Dash");

        assertThat(utils.returnTextElement("xpath", "//span[@class='line__text']")).contains("filtrados de um total de ");
        assertThat(utils.returnTextElement("xpath", "//span[@class='line__text']")).contains("CNPJs");

        assertThat("picture_as_pdf").isEqualTo(utils.returnTextElement("xpath", "//i[contains(text(),'picture_as_pdf')]"));
        assertThat("Faixa de Faturamento Presumido").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-title-column-client']/span[1]"));
        assertThat(utils.returnTextElement("xpath", "//span[@class='title__tooltip ng-star-inserted']"))
                .isEqualTo("Modelo que prevê Faturamento Líquido da empresa estimado nos últimos 12 meses.");

        assertThat("75,1% a 100%").contains(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-0-client']//span[contains(text(),'75,1% a 100%')]"));
        assertThat("Alto").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-0-client']//span[contains(text(),'Alto')]"));
        assertThat("info").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-0-client']//i[contains(text(),'info')]"));

        assertThat("25,1% a 75%").contains(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-1-client']//span[contains(text(),'25,1% a 75%')]"));
        assertThat("Médio").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-1-client']//span[contains(text(),'Médio')]"));
        assertThat("info").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-1-client']//i[contains(text(),'info')]"));

        assertThat("0% a 25%'").contains(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-2-client']//span[contains(text(),'0% a 25%')]"));
        assertThat("Baixo").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-2-client']//span[contains(text(),'Baixo')]"));
        assertThat("info").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard-column-2-client']//i[contains(text(),'info')]"));

        assertThat("700 a 1000").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard]-row-0-client']//span[contains(text(),'700 a 1000')]"));
        assertThat("300 a 700").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard]-row-1-client']//span[contains(text(),'300 a 700')]"));
        assertThat("2 a 300").isEqualTo(utils.returnTextElement("xpath", "//div[@data-test='dashboard]-row-2-client']//span[contains(text(),'2 a 300')]"));

        assertThat((pages4.squareAltoPotencial.isDisplayed()));
        assertThat((pages4.squareMedioPotencial.isDisplayed()));
        assertThat((pages4.squareLowPotencial.isDisplayed()));
        assertThat((pages4.squareSemInformacoes.isDisplayed()));
        assertThat("Alto Potencial").isEqualTo(utils.returnTextElement("xpath", "//div[contains(text(),'Alto Potencial')]"));
        assertThat("Médio Potencial").isEqualTo(utils.returnTextElement("xpath", "//div[contains(text(),'Médio Potencial')]"));
        assertThat("Baixo Potencial").isEqualTo(utils.returnTextElement("xpath", "//div[contains(text(),'Baixo Potencial')]"));
        assertThat("Sem Informações").isEqualTo(utils.returnTextElement("xpath", "//div[contains(text(),'Sem Informações')]"));

        utils.waitElement(pages4.boxExtracao);
        assertThat(pages4.boxExtracao.isDisplayed());
        assertThat("Conforme sua necessidade, utilize filtros, escolha tipo de enriquecimento e quantidade de documentos").
                isEqualTo(utils.returnTextElement("xpath", "//p[@class='extraction-title']"));

        assertThat(pages4.txtEnriquecimentoExtracao.isDisplayed());
        assertThat(pages4.txtQuantidadeExtracao.isDisplayed());
        assertThat(extractionPage.sliderExtracao.isDisplayed());
        assertThat(extractionPage.pointerSliderExtracao.isDisplayed());
        assertThat("100").isEqualTo(utils.returnTextElement("xpath", "//span[@class='ng5-slider-span ng5-slider-bubble ng5-slider-limit ng5-slider-floor']"));

        assertThat(utils.returnAttributeElement("//input[@class='extraction__group-amount-value ng-untouched ng-pristine ng-valid']", "value")).isNotEmpty();
        assertThat(utils.returnAttributeElement("//input[@class='extraction__group-amount-value ng-untouched ng-pristine ng-valid']", "value")).isNotNull();
        assertThat(utils.returnAttributeElement("//input[@class='extraction__group-amount-value ng-untouched ng-pristine ng-valid']", "value")).isNotNull();

        assertThat(pages4.btnFiltros.isDisplayed());
        assertThat(pages4.btnFiltros.isSelected()).isFalse();

        assertThat(pages4.btnUpBaseFromDashboard.isDisplayed());
    }


    public void Validar_3(){
        Log.info("Validar as");
        String bundle = getLocalStorage.getObjectBundle("bundle_pj");
        assertThat(extractionPage.chkScoreEmpresa.isDisplayed());
        assertThat(extractionPage.chkScoreEmpresa.isSelected()).isFalse();
        assertThat(extractionPage.chkFaturamentoPresumido.isDisplayed());
        assertThat(extractionPage.chkFaturamentoPresumido.isSelected()).isFalse();
        assertThat(extractionPage.chkLimiteCredito.isDisplayed());
        assertThat(extractionPage.chkLimiteCredito.isSelected()).isFalse();
        assertThat(extractionPage.chkIndicadorOperacionalidade.isDisplayed());
        assertThat(extractionPage.chkIndicadorOperacionalidade.isSelected()).isFalse();

        if (bundle.equals("silver") || bundle.equals("gold") || bundle.equals("black")){
            assertThat(extractionPage.chkHistPositivoComercial.isDisplayed());
            assertThat(extractionPage.chkHistPositivoComercial.isSelected()).isFalse();
            assertThat(extractionPage.chkConcentre.isDisplayed());
            assertThat(extractionPage.chkConcentre.isSelected()).isFalse();
            assertThat(extractionPage.chkCreditRating.isDisplayed());
            assertThat(extractionPage.chkCreditRating.isSelected()).isFalse();
        }
        assertThat(extractionPage.pointerSliderExtracao.isDisplayed());
        assertThat(extractionPage.sliderExtracao.isDisplayed());
        assertThat(extractionPage.txtQtdExtracao.isDisplayed());
        assertThat(extractionPage.btnExtracao.isDisplayed());
        assertThat(extractionPage.btnExtracao.isEnabled()).isFalse();
    }

    public void Validar_4() throws Throwable {
        pages4.btnFiltros.click();
        utils.waitElement(pages4.btnAplicarFiltro);
        validarPontuacaoDeRiscoFiltro();
        validarFaturamentoPresumidoFiltro();

        Log.info("Validar presença dos filtros de Estado");
        pages3.cmbEstados.click();
        utils.waitElement(pages3.chkEstadoTodos);
        assertThat(pages3.chkEstadoTodos.isDisplayed());
        assertThat(pages3.chkEstadoTodos.isSelected()).isTrue();
        for (int i=0; i <=siglasEstados.length-1; i++) {
            WebElement chkEstados = DriverUtils.getDriver()
                    .findElement(By.xpath("//label[@data-test='filter-states-" + siglasEstados[i] +"']//input"));
            assertThat(chkEstados.isDisplayed());
            assertThat(chkEstados.isSelected()).isTrue();
        }
        assertThat(pages3.chkEstadoSemInformacoes.isDisplayed());
        assertThat(pages3.chkEstadoSemInformacoes.isSelected()).isTrue();
        pages3.cmbEstados.click();

        Log.info("Validar presença dos filtros de");
        pages3.cmbCNAE.click();
        utils.waitElement(pages3.chkCnaeTodos);
        assertThat(pages3.chkCnaeTodos.isDisplayed());
        assertThat(pages3.chkCnaeTodos.isSelected()).isTrue();
        for (char ch = 'A'; ch <= 'U'; ch++) {
            WebElement chkCNAE = DriverUtils.getDriver()
                    .findElement(By.xpath("//label[@data-test='filter-cnaes-" + ch + "']//input"));
            assertThat(chkCNAE.isDisplayed());
            assertThat(chkCNAE.isSelected()).isTrue();
        }
        assertThat(pages3.chkCnaeSemInformacoes.isDisplayed());
        assertThat(pages3.chkCnaeSemInformacoes.isSelected()).isTrue();
        pages3.cmbCNAE.click();
    }

    public void Validar_5() throws Throwable {
        Log.info("Validar presença dos filtros de X");
        pages3.cmbPontuRiscoFiltros.click();
        utils.waitElement(pages3.chkPontuRiscoTodos);
        assertThat(pages3.chkPontuRiscoTodos.isDisplayed());
        assertThat(pages3.chkPontuRiscoTodos.isSelected()).isFalse();

        assertThat(pages3.chkPontuRisco2_a_300.isDisplayed());
        assertThat(pages3.chkPontuRisco2_a_300.isSelected()).isTrue();

        assertThat(pages3.chkPontuRisco300_a_700.isDisplayed());
        assertThat(pages3.chkPontuRisco300_a_700.isSelected()).isTrue();

        assertThat(pages3.chkPontuRisco700_a_1000.isDisplayed());
        assertThat(pages3.chkPontuRisco700_a_1000.isSelected()).isTrue();

        assertThat(pages3.chkPontuRiscoSemInformacoes.isDisplayed());
        assertThat(pages3.chkPontuRiscoSemInformacoes.isSelected()).isFalse();
        pages3.cmbPontuRiscoFiltros.click();
    }

    public void Validar_6() throws Throwable {
        Log.info("Validar presença dos filtros de ");
        pages3.cmbFaturamentoPresumido.click();
        utils.waitElement(pages3.chkFatPresumidoTodos);
        assertThat(pages3.chkFatPresumidoTodos.isDisplayed());
        assertThat(pages3.chkFatPresumidoTodos.isSelected()).isFalse();

        assertThat(pages3.chkFatPresumido0_a_570000.isDisplayed());
        assertThat(pages3.chkFatPresumido0_a_570000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido570000_a_1350000.isDisplayed());
        assertThat(pages3.chkFatPresumido570000_a_1350000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido1350000_a_6750000.isDisplayed());
        assertThat(pages3.chkFatPresumido1350000_a_6750000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido6750000_a_17230000.isDisplayed());
        assertThat(pages3.chkFatPresumido6750000_a_17230000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido17230000_a_90000000.isDisplayed());
        assertThat(pages3.chkFatPresumido17230000_a_90000000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido90000000_a_340000000.isDisplayed());
        assertThat(pages3.chkFatPresumido90000000_a_340000000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido90000000_a_340000000.isDisplayed());
        assertThat(pages3.chkFatPresumido90000000_a_340000000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido340000000_a_750000000.isDisplayed());
        assertThat(pages3.chkFatPresumido340000000_a_750000000.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumido750000000_a_0.isDisplayed());
        assertThat(pages3.chkFatPresumido750000000_a_0.isSelected()).isTrue();

        assertThat(pages3.chkFatPresumidoSemInformacoes.isDisplayed());
        assertThat(pages3.chkFatPresumidoSemInformacoes.isSelected()).isFalse();
        pages3.cmbFaturamentoPresumido.click();
    }

    public void validarInformacoesTelaCompra(String transacao, String quantidade) {
        assertThat(utils.returnTextElement("xpath", "//td[@data-test='precification-item-id-" + transacao +"']"))
                .isEqualTo(extractionsName.returnNameExtraction(transacao));
        assertThat(utils.returnTextElement("xpath", "//td[@data-test='precification-item-price-" + transacao +"']"))
                .isNotEmpty();
        assertThat(utils.returnTextElement("xpath", "//td[@data-test='precification-item-quantity-" + transacao +"']//div[@class='quantity__square']"))
                .isEqualTo(quantidade);
        assertThat(utils.returnTextElement("xpath", "//td[@data-test='precification-item-total-price-" + transacao +"']"))
                .isNotEmpty();
    }
}
