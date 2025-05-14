package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import service.ExchangeRateService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Set;

public class CurrencyServer {
    public static void main(String[] args) throws IOException {
        ExchangeRateService service = new ExchangeRateService();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Endpoint /rate
        server.createContext("/rate", (exchange) -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();

            String[] params = query.split("&");
            String base = "", target = "";
            for (String param : params) {
                String[] pair = param.split("=");
                if (pair[0].equals("base")) base = pair[1];
                if (pair[0].equals("target")) target = pair[1];
            }

            try {
                double rate = service.getRate(base, target);
                String responseText = "{\"rate\": " + rate + "}";

                exchange.sendResponseHeaders(200, responseText.length());
                OutputStream os = exchange.getResponseBody();
                os.write(responseText.getBytes());
                os.close();
            } catch (Exception e) {
                String errorMsg = "{\"error\": \"Erro ao buscar taxa: " + e.getMessage() + "\"}";
                exchange.sendResponseHeaders(500, errorMsg.length());
                exchange.getResponseBody().write(errorMsg.getBytes());
                exchange.getResponseBody().close();
            }
        });

        // Endpoint /currencies
        server.createContext("/currencies", (exchange) -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            String base = "USD"; // valor padrão
            try {
                Set<String> currencies = service.getAvailableCurrencies(base);

                String json = new Gson().toJson(currencies);

                exchange.sendResponseHeaders(200, json.length());
                exchange.getResponseBody().write(json.getBytes());
                exchange.getResponseBody().close();
            } catch (Exception e) {
                String errorMsg = "{\"error\": \"Erro ao buscar moedas: " + e.getMessage() + "\"}";
                exchange.sendResponseHeaders(500, errorMsg.length());
                exchange.getResponseBody().write(errorMsg.getBytes());
                exchange.getResponseBody().close();
            }
        });

        server.start();
        System.out.println("Servidor rodando em http://localhost:8080");
    }
}
