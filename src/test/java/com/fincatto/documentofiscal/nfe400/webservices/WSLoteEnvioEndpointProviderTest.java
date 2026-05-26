package com.fincatto.documentofiscal.nfe400.webservices;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.nfe.NFeConfig;
import com.fincatto.documentofiscal.nfe400.classes.NFAutorizador400;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyStore;
import java.security.KeyStoreException;

public class WSLoteEnvioEndpointProviderTest {

    @Test
    public void deveUsarEndpointProviderParaResolverEndpoint() {
        final String endpointEsperado = "http://localhost:9999/mock-nfe";
        final NFeAutorizacaoEndpointProvider provider = (modelo, autorizador, ambiente) -> endpointEsperado;
        final WSLoteEnvio wsLoteEnvio = new WSLoteEnvio(new NFeConfigFake(), provider);

        final String endpoint = provider.getAutorizacaoEndpoint(DFModelo.NFE,
                NFAutorizador400.valueOfCodigoUF(DFUnidadeFederativa.SP), DFAmbiente.HOMOLOGACAO);

        Assert.assertEquals(endpointEsperado, endpoint);
        Assert.assertNotNull(wsLoteEnvio);
    }

    @Test
    public void deveManterComportamentoPadraoNoProviderDefault() {
        final DefaultNFeAutorizacaoEndpointProvider provider = new DefaultNFeAutorizacaoEndpointProvider();
        final NFAutorizador400 autorizador = NFAutorizador400.valueOfCodigoUF(DFUnidadeFederativa.SP);

        final String endpointNfe = provider.getAutorizacaoEndpoint(DFModelo.NFE, autorizador, DFAmbiente.HOMOLOGACAO);
        final String endpointNfce = provider.getAutorizacaoEndpoint(DFModelo.NFCE, autorizador, DFAmbiente.HOMOLOGACAO);

        Assert.assertEquals(autorizador.getNfeAutorizacao(DFAmbiente.HOMOLOGACAO), endpointNfe);
        Assert.assertEquals(autorizador.getNfceAutorizacao(DFAmbiente.HOMOLOGACAO), endpointNfce);
    }

    private static class NFeConfigFake extends NFeConfig {
        @Override
        public DFUnidadeFederativa getCUF() {
            return DFUnidadeFederativa.SP;
        }

        @Override
        public KeyStore getCertificadoKeyStore() throws KeyStoreException {
            return null;
        }

        @Override
        public String getCertificadoSenha() {
            return "";
        }

        @Override
        public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
            return null;
        }

        @Override
        public String getCadeiaCertificadosSenha() {
            return "";
        }
    }
}
