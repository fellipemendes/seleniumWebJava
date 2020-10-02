package com.steps;

import com.common.utils.BaseFileName;
import com.common.utils.Utils;
import com.pages.pages1;
import com.pages.PrincipalPage;
import io.cucumber.java.pt.*;

public class ValidarTest3 {
    pages1 basesPage;
    PrincipalPage pginicial;
    Utils utils;

    public ValidarTest3(pages1 basesPage, PrincipalPage pginicial, Utils utils){
        this.basesPage = basesPage;
        this.pginicial = pginicial;
        this.utils = utils;
    }

    @Quando("^selecionar uma base para upload \"([^\"]*)\"$")
    public void selecionarUmaBaseParaUpload(String perfil) throws Throwable {
        pginicial.linkMenuBases.click();
        utils.waitElement(basesPage.txtCaminhoBase);
        switch (perfil){
            case "P_x":
            case "P_y":
                utils.arquivoBaseUpload(BaseFileName.fileNamePJ);
                break;
            case "P__z":
                utils.arquivoBaseUpload(BaseFileName.fileNamePF);
                break;
        }
    }

    @Então("^deverá abrir a modal de finalidade de base$")
    public void deveráAbrirAModalDeFinalidadeDeBase() throws Throwable {
        utils.waitElement(basesPage.btnIniciarUploadBase);
    }

    @E("^deverá respeitar a regra de seleção do tipo de base para habilitar botão de Upload \"([^\"]*)\"$")
    public void deveráRespeitarARegraDeSeleçãoDoTipoDeBaseParaHabilitarBotãoDeUploadPerfil(String perfil) throws Throwable {
        utils.validaCheckModalUploadBase(perfil);
    }
}
