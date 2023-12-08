package com.algaworks.algafoodjavaclient.api;

import com.algaworks.algafoodjavaclient.model.RestauranteModel;
import com.algaworks.algafoodjavaclient.model.RestauranteResumoModel;
import com.algaworks.algafoodjavaclient.model.input.RestauranteInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantes";

    private RestTemplate restTemplate;

    private String url;

    public List<RestauranteResumoModel> listar() {
        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            RestauranteResumoModel[] restaurantes = restTemplate.getForEntity(resourceUri, RestauranteResumoModel[].class).getBody();

            return Arrays.asList(restaurantes);
        } catch (ClientApiException e) {
            if (e.getProblem() == null) {
                System.out.println(e.getProblem().getUserMessage());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public RestauranteModel adicionar(RestauranteInput restauranteInput) {
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate
                    .postForObject(resourceUri, restauranteInput, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
