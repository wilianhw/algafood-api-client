package com.algaworks.algafoodjavaclient;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import com.algaworks.algafoodjavaclient.model.RestauranteModel;
import com.algaworks.algafoodjavaclient.model.input.CidadeIdInput;
import com.algaworks.algafoodjavaclient.model.input.CozinhaIdInput;
import com.algaworks.algafoodjavaclient.model.input.EnderecoInput;
import com.algaworks.algafoodjavaclient.model.input.RestauranteInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        try {
            RestauranteInput restauranteInput = new RestauranteInput();

            restauranteInput.setNome("Barbecue");
            restauranteInput.setTaxaFrete(BigDecimal.valueOf(12));

            CozinhaIdInput cozinhaIdInput = new CozinhaIdInput();
            cozinhaIdInput.setId(1L);
            restauranteInput.setCozinha(cozinhaIdInput);

            EnderecoInput enderecoInput = new EnderecoInput();
            enderecoInput.setCep("12341234");
            enderecoInput.setBairro("Centro");
            enderecoInput.setComplemento("Casa");
            enderecoInput.setLogradouro("Rua Aureliano");
            enderecoInput.setNumero("14");
            CidadeIdInput cidadeIdInput = new CidadeIdInput();
            cidadeIdInput.setId(1L);
            enderecoInput.setCidade(cidadeIdInput);

            restauranteInput.setEndereco(enderecoInput);

            RestTemplate restTemplate = new RestTemplate();
            RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");
            RestauranteModel restauranteModel = restauranteClient.adicionar(restauranteInput);

            System.out.println(restauranteModel);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getUserMessage());

                e.getProblem().getObjects().stream()
                        .forEach(p -> System.out.println("- " + p.getUserMessage()));

            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
