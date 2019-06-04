package com.imooc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class MocKServer {

    public static void main(String[] args) {
        WireMock.configureFor (8060);
        WireMock.removeAllMappings ();
        WireMock.stubFor (WireMock.get (WireMock.urlEqualTo ("/order/1"))
        .willReturn (WireMock.aResponse ().withBody ("{\"id\" : 1}").withStatus (200)));
    }
}
