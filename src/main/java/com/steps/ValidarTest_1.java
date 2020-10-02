package com.steps;

import com.common.utils.*;
import com.pages.pages4;
import com.pages.ExtractionPage;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidarTest_1 {
    Utils utils;
    pages4 pages4;
    UtilsDashPJ utilsDashPJ;
    ExtractionPage extractionPage;
    GetLocalStorage getLocalStorage;
    static String[] arrayNames = {"ABCD", "ABCE", "ABCF", "ABCG", "ABCH", "ABCI", "ABCJ"};
    static String qtdTotal = "";
    String[] arrayNames_2 = {"ABCD", "ABCE", "ABCF", "ABCG"};
    ArrayList<String> ArrayNamesList = new ArrayList<String>();

    public ValidarTest_1(
            Utils utils,
            pages4 pages4,
            UtilsDashPJ utilsDashPJ,
            ExtractionPage extractionPage,
            GetLocalStorage getLocalStorage) {
        this.utils = utils;
        this.pages4 = pages4;
        this.utilsDashPJ = utilsDashPJ;
        this.extractionPage = extractionPage;
        this.getLocalStorage = getLocalStorage;
    }

    @Então("deverá apresentar as informações")
    public void deveráApresentarAsInformaçõesComDadosDaCompra() {
        for (int i=0; i < 4; i++){
            String qtdTran="";
            qtdTran = utils.returnTextElement("xpath", "//td[@data-test='precification-item-quantity-" + arrayNames[i] + "']//div[@class='quantity__square']");
            assertThat(qtdTotal).isEqualTo(qtdTran);
            Log.info("Total: " + qtdTotal + " - Extração "+ arrayNames[i] +": " + qtdTran);
        }
    }
    @Quando("selecionar a {string} para quantidade {string}")
    public void selecionar_a_transação_para_extração_e_quantidade(String transacao, String quantidade) throws Throwable {
        utils.waitElement(extractionPage.btnExtracao);
        switch (transacao) {
            case "A":
                extractionPage.clickX_1();
                break;
            case "B":
                extractionPage.clickX_2();
                break;
            case "C":
                extractionPage.clickX_3();
                break;
            case "D":
                extractionPage.clickX_3();
                break;
            case "A,B,C,D,E":
                extractionPage.clickX_1();
                extractionPage.clickX_2();
                extractionPage.clickX_3();
                extractionPage.clickX_4();
                break;
        }
        extractionPage.txtQtdExtracao.clear();
        extractionPage.txtQtdExtracao.sendKeys(quantidade);
        DriverUtils.esperar(1000);
        DriverUtils.tirarScreenShot();
        extractionPage.btnExtracao.click();
    }

    @Então("deverá apresentar tela de ")
    public void deverá_apresentar_tela_de_(String X, String Y) throws Throwable {
        utils.waitElement(extractionPage.btnComprar);

        switch (tran){
            case "A,B,C,D,E":
                transactionNames.addAll(Arrays.asList(ArrayNamesList));
                transactionNames.forEach((n) ->
                        utilsDashPJ.validarInformacoesTelaCompra(n, quantidade));
                break;
            default:
                utilsDashPJ.validarInformacoesTelaCompra(X, Y);
        }
    }
}
