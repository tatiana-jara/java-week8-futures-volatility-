import java.util.Random;

public class GoldFuture
        extends FutureContract {

    public GoldFuture(
            String name,
            double underlyingPrice,
            double optionPrice,
            double timeToExpiration) {

        super(
            name,
            underlyingPrice,
            optionPrice,
            timeToExpiration,
            new MonteCarloVolatility()
        );
    }

    public GoldFuture(GoldFuture other) {
        super(other);
    }

    @Override
    public double computeImpliedVolatility() {

        Random random = new Random();

        return random.nextDouble();
    }
}