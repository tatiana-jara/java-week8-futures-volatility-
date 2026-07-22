import java.util.Random;

public class CoffeeFuture
        extends FutureContract {

    public CoffeeFuture(
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

    public CoffeeFuture(
            CoffeeFuture other) {

        super(other);
    }

    @Override
    public double computeImpliedVolatility() {

        Random random = new Random();

        return random.nextDouble();
    }
}