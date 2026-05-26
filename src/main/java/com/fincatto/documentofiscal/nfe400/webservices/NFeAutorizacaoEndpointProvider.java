package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador400;

interface NFeAutorizacaoEndpointProvider {
    String getAutorizacaoEndpoint(DFModelo modelo, NFAutorizador400 autorizador, DFAmbiente ambiente);
}
