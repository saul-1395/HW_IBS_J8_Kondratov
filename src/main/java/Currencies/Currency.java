package Currencies;

public enum Currency {
    RUB("RUB"), EU("EU"), USD("USD");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

}
