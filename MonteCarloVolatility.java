import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MonteCarloVolatility implements IVolatilityCalculator {

    @Override
    public double computeVolatility(List<Double> prices) {

        if (prices == null || prices.size() < 2) {
            return 0.0;
        }

        ArrayList<Double> historicalReturns = new ArrayList<>();

        for (int i = 1; i < prices.size(); i++) {

            double previousPrice = prices.get(i - 1);
            double currentPrice = prices.get(i);

            if (previousPrice != 0) {

                double dailyReturn =
                        (currentPrice - previousPrice)
                        / previousPrice;

                historicalReturns.add(dailyReturn);
            }
        }

        if (historicalReturns.isEmpty()) {
            return 0.0;
        }

        Random random = new Random();

        int numberOfSimulations = 10000;

        ArrayList<Double> simulatedReturns =
                new ArrayList<>();

        for (int i = 0; i < numberOfSimulations; i++) {

            int randomIndex =
                    random.nextInt(
                        historicalReturns.size()
                    );

            double simulatedReturn =
                    historicalReturns.get(
                        randomIndex
                    );

            simulatedReturns.add(
                    simulatedReturn
            );
        }

        double sum = 0.0;

        for (double value : simulatedReturns) {
            sum += value;
        }

        double mean =
                sum / simulatedReturns.size();

        double squaredDifferenceSum = 0.0;

        for (double value : simulatedReturns) {

            double difference =
                    value - mean;

            squaredDifferenceSum +=
                    difference * difference;
        }

        double variance =
                squaredDifferenceSum
                / simulatedReturns.size();

        return Math.sqrt(variance);
    }
}