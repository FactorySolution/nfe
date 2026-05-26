package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador400;

class DefaultNFeAutorizacaoEndpointProvider implements NFeAutorizacaoEndpointProvider {

    @Override
    public String getAutorizacaoEndpoint(final DFModelo modelo, final NFAutorizador400 autorizador, final DFAmbiente ambiente) {
        return DFModelo.NFE.equals(modelo) ? autorizador.getNfeAutorizacao(ambiente) : autorizador.getNfceAutorizacao(ambiente);
    }
}
