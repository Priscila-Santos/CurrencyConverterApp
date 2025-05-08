import service.CurrencyConverter;
import service.ExchangeRateService;
import util.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        CurrencyConverter converter = new CurrencyConverter(exchangeRateService);
        ConsoleUI ui = new ConsoleUI(converter);
        ui.run();
    }
}

