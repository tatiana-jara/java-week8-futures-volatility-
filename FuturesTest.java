public class FuturesTest {

    public static void main(String[] args) {

        GoldFuture gold =
                new GoldFuture(
                    "Gold Future",
                    2350.00,
                    120.00,
                    0.50
                );

        CopperFuture copper =
                new CopperFuture(
                    "Copper Future",
                    4.50,
                    0.25,
                    0.40
                );

        CoffeeFuture coffee =
                new CoffeeFuture(
                    "Coffee Future",
                    225.00,
                    15.00,
                    0.30
                );

        gold.loadPricesFromFile(
            "gold_prices.txt"
        );

        copper.loadPricesFromFile(
            "copper_prices.txt"
        );

        coffee.loadPricesFromFile(
            "coffee_prices.txt"
        );

        System.out.println();

        IVolatilityCalculator
                goldCalculator = gold;

        IVolatilityCalculator
                copperCalculator = copper;

        IVolatilityCalculator
                coffeeCalculator = coffee;

        gold.displayContract();

        double goldVolatility =
                goldCalculator
                .computeVolatility(
                    gold.getPrices()
                );

        System.out.println(
            "Volatility: "
            + goldVolatility
        );

        copper.displayContract();

        double copperVolatility =
                copperCalculator
                .computeVolatility(
                    copper.getPrices()
                );

        System.out.println(
            "Volatility: "
            + copperVolatility
        );

        coffee.displayContract();

        double coffeeVolatility =
                coffeeCalculator
                .computeVolatility(
                    coffee.getPrices()
                );

        System.out.println(
            "Volatility: "
            + coffeeVolatility
        );
    }
}