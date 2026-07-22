import java.util.Random;

public class CopperFuture
        extends FutureContract {

    public CopperFuture(
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

    public CopperFuture(
            CopperFuture other) {

        super(other);
    }

    @Override
    public double computeImpliedVolatility() {

        Random random = new Random();

        return random.nextDouble();
    }
}