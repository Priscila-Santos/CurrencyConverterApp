package util;

import service.CurrencyConverter;

import java.util.Scanner;

public class ConsoleUI {
    private final CurrencyConverter converter;
    private final Scanner scanner;

    public ConsoleUI(CurrencyConverter converter) {
        this.converter = converter;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Conversor de Moedas ---");
            System.out.println("1. USD -> EUR");
            System.out.println("2. EUR -> BRL");
            System.out.println("3. BRL -> USD");
            System.out.println("4. GBP -> JPY");
            System.out.println("5. CAD -> USD");
            System.out.println("6. AUD -> BRL");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            if (option == 0) break;

            double amount;
            String from = "", to = "";

            switch (option) {
                case 1 -> { from = "USD"; to = "EUR"; }
                case 2 -> { from = "EUR"; to = "BRL"; }
                case 3 -> { from = "BRL"; to = "USD"; }
                case 4 -> { from = "GBP"; to = "JPY"; }
                case 5 -> { from = "CAD"; to = "USD"; }
                case 6 -> { from = "AUD"; to = "BRL"; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            System.out.print("Digite o valor em " + from + ": ");
            amount = scanner.nextDouble();

            double result = converter.convert(from, to, amount);
            if (result >= 0) {
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, from, result, to);
            }
        }
        System.out.println("Encerrando programa...");
    }
}

