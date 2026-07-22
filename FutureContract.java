import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class FutureContract
        implements IVolatilityCalculator {

    private String name;
    private double underlyingPrice;
    private double optionPrice;
    private double timeToExpiration;

    protected ArrayList<Double> prices;

    protected IVolatilityCalculator volatilityCalculator;

    public FutureContract(
            String name,
            double underlyingPrice,
            double optionPrice,
            double timeToExpiration,
            IVolatilityCalculator volatilityCalculator) {

        this.name = name;
        this.underlyingPrice = underlyingPrice;
        this.optionPrice = optionPrice;
        this.timeToExpiration = timeToExpiration;

        this.prices = new ArrayList<>();

        this.volatilityCalculator =
                volatilityCalculator;
    }

    public FutureContract(FutureContract other) {

        this.name = other.name;
        this.underlyingPrice =
                other.underlyingPrice;
        this.optionPrice =
                other.optionPrice;
        this.timeToExpiration =
                other.timeToExpiration;

        this.prices =
                new ArrayList<>(other.prices);

        this.volatilityCalculator =
                other.volatilityCalculator;
    }

    public String getName() {
        return name;
    }

    public double getUnderlyingPrice() {
        return underlyingPrice;
    }

    public double getOptionPrice() {
        return optionPrice;
    }

    public double getTimeToExpiration() {
        return timeToExpiration;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public void loadPricesFromFile(
            String filename) {

        prices.clear();

        try (
            BufferedReader reader =
                    new BufferedReader(
                        new FileReader(filename)
                    )
        ) {

            String line;

            while (
                (line = reader.readLine())
                != null
            ) {

                line = line.trim();

                if (!line.isEmpty()) {

                    try {

                        double price =
                                Double.parseDouble(
                                    line
                                );

                        prices.add(price);

                    } catch (
                        NumberFormatException e
                    ) {

                        System.out.println(
                            "Invalid price: "
                            + line
                        );
                    }
                }
            }

            System.out.println(
                "Prices loaded successfully for "
                + name
            );

        } catch (IOException e) {

            System.out.println(
                "Error reading file: "
                + filename
            );

            System.out.println(
                e.getMessage()
            );
        }
    }

    @Override
    public double computeVolatility(
            List<Double> prices) {

        return volatilityCalculator
                .computeVolatility(prices);
    }

    public double computeVolatility() {

        return computeVolatility(prices);
    }

    public abstract double
            computeImpliedVolatility();

    public void displayContract() {

        System.out.println(
            "------------------------------"
        );

        System.out.println(
            "Contract: " + name
        );

        System.out.println(
            "Underlying Price: $"
            + underlyingPrice
        );

        System.out.println(
            "Option Price: $"
            + optionPrice
        );

        System.out.println(
            "Time to Expiration: "
            + timeToExpiration
        );

        System.out.println(
            "Historical Prices Loaded: "
            + prices.size()
        );
    }
}