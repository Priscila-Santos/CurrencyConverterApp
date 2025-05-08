package service;

import com.google.gson.Gson;
import model.ExchangeRatesResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {
    private final String API_KEY = "be5694655c68a4075e82cd03";
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public double getRate(String base, String target) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao acessar a API de câmbio");
        }

        Gson gson = new Gson();
        ExchangeRatesResponse exchangeResponse = gson.fromJson(response.body(), ExchangeRatesResponse.class);

        Double rate = exchangeResponse.getConversionRates().get(target);
        if (rate == null) {
            throw new RuntimeException("Moeda não suportada: " + target);
        }

        return rate;
    }
}
