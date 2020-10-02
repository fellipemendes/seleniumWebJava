package com.steps;

import com.common.utils.BaseFileName;
import com.common.utils.DriverUtils;
import com.common.utils.Utils;
import com.pages.pages1;
import com.pages.PrincipalPage;
import io.cucumber.java.pt.*;
import static org.assertj.core.api.Assertions.*;

public class ValidarTest4 {

    pages1 basesPage;
    PrincipalPage pginicial;
    Utils utils;
    private static final String fileNamePJ = "file.csv";

    public static String emailLogin ="";

    public ValidarTest4(pages1 basesPage, PrincipalPage pginicial, Utils utils){
        this.basesPage = basesPage;
        this.pginicial = pginicial;
        this.utils = utils;
    }

    @Quando("^realizar o upload de uma base$")
    public void realizarOUploadDeUmaBaseClientePJ() throws Throwable {
        pginicial.linkMenuBases.click();
        utils.waitElement(basesPage.setTxtCaminhoBase());
        utils.arquivoBaseUpload(BaseFileName.fileNamePJ);
    }

    @Então("^deverá apresentar sucesso no envio da base$")
    public void deveráApresentarSucessoNoEnvioDaBase() throws Throwable {
        utils.waitTextOnElement("xpath", "//h3[@class='message__title']", "Upload concluído com sucesso");
        utils.waitElement(basesPage.msgTitleUploadBase);
        utils.waitLoadingBases();
        assertThat(utils.returnTextElement("XPATH", "//h3[@class='message__title']"))
                .isEqualTo("Upload concluído com sucesso");
        utils.waitElement(basesPage.msgSubtitleUploadBase);
        assertThat(utils.returnTextElement("XPATH", "//h3[@class='message__subtitle']"))
                .isEqualTo("O upload do arquivo " + Utils.fileName + " foi concluído com sucesso");
    }

    @E("^base deverá aparecer na lista de bases$")
    public void baseDeveráAparecerNaListaDeBases() throws Throwable {
        utils.waitElement(basesPage.primeiraBase);
        assertThat(utils.returnTextElement("XPATH", "/html/body/"))
                .isEqualTo(Utils.fileName);
        assertThat(utils.returnTextElement("XPATH", "/html/body"))
                .startsWith(emailLogin);
        DriverUtils.tirarScreenShot();
    }


}