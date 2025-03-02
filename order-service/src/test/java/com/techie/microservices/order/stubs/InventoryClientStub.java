package com.techie.microservices.order.stubs;

import lombok.experimental.UtilityClass;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@UtilityClass
public class InventoryClientStub {
    public static void stubInventoryCallInStock(String skuCode, Integer quantity){
        stubFor(get(urlEqualTo("/api/inventory?skuCode="+skuCode+"&quantity="+quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
                )
        );
    }

    public static void stubInventoryCallOutStock(String iphone15, Integer quantity) {
        stubFor(get(urlEqualTo("/api/inventory?skuCode="+iphone15+"&quantity="+quantity))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("false")
                )
        );
    }
}
