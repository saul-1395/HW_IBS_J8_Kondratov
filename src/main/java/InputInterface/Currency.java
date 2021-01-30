package InputInterface;

public class Currency {
    public enum currency{ RUB("RUB"), EU("EU"), USD("USD");

        private String currency;

        currency(String currency) {
            this.currency = currency;
        }

        public String getCurrency() {
            return currency;
        }
    }
}
