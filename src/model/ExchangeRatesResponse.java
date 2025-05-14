package model;

import java.util.Map;

public class ExchangeRatesResponse {

    private Map<String, Double> conversion_rates;

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}
