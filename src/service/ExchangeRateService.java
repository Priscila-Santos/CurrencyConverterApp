package service;

import com.google.gson.Gson;
import model.ExchangeRatesResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;

public class ExchangeRateService {
    private final String API_KEY = "be5694655c68a4075e82cd03";
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    /**
     * Retorna a taxa de conversão de uma moeda base para outra moeda alvo.
     */
    public double getRate(String base, String target) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao acessar a API de câmbio");
        }

        ExchangeRatesResponse exchangeResponse = gson.fromJson(response.body(), ExchangeRatesResponse.class);
        Map<String, Double> rates = exchangeResponse.getConversionRates();

        if (!rates.containsKey(target)) {
            throw new RuntimeException("Moeda não suportada: " + target);
        }

        return rates.get(target);
    }

    /**
     * Retorna a lista de moedas disponíveis para conversão com base em uma moeda.
     */
    public Set<String> getAvailableCurrencies(String base) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao acessar a API de câmbio");
        }

        ExchangeRatesResponse exchangeResponse = gson.fromJson(response.body(), ExchangeRatesResponse.class);
        return exchangeResponse.getConversionRates().keySet();  // retorna todas as moedas
    }
}
