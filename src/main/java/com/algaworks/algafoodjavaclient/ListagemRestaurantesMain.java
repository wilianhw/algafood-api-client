package com.algaworks.algafoodjavaclient;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");

            restauranteClient.listar()
                    .forEach(System.out::println);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
