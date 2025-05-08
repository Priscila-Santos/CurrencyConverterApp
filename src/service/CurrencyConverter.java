package service;

public class CurrencyConverter {
    private final ExchangeRateService exchangeRateService;

    public CurrencyConverter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public double convert(String from, String to, double amount) {
        try {
            double rate = exchangeRateService.getRate(from, to);
            return amount * rate;
        } catch (Exception e) {
            System.out.println("Erro ao converter: " + e.getMessage());
            return -1;
        }
    }
}

