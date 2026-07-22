import java.util.List;

public interface IVolatilityCalculator {

    double computeVolatility(List<Double> prices);
}