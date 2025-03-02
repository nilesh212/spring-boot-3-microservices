package com.techie.microservices.order.config;

import com.techie.microservices.order.client.InventoryClient;
//import lombok.var;
import io.micrometer.observation.ObservationRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;
    private final ObservationRegistry observationRegistry;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .observationRegistry(observationRegistry)
        .build();
        var restClientAdapter= RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);

    }

//    private ClientHttpRequestFactory getClientRequestFactory() {
//        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.defaults()
//                .withConnectTimeout(Duration.ofSeconds(3))
//                .withReadTimeout(Duration.ofSeconds(3));
//
//        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
//
//    }

//    private HttpComponentsClientHttpRequestFactory getClientRequestFactory() {
//        // Configure HTTP client with timeouts
//        HttpClient httpClient = HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(3))// Set connect timeout
//                .build();  // Build the HttpClient
//
//
//        // Wrap the HttpClient in HttpComponentsClientHttpRequestFactory
//        return new HttpComponentsClientHttpRequestFactory(httpClient);
//    }


}
