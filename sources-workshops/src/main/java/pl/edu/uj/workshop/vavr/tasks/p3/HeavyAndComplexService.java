package pl.edu.uj.workshop.vavr.tasks.p3;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;
import static io.vavr.Predicates.isIn;

final class HeavyAndComplexService {

    private static final String TOP_SECRET_INFO = "SOME TOP SECRET INFORMATION";
    private static final String DEFAULT_INFO = "There aren't any information in the queried scope!";

    private HeavyAndComplexService() {
    }

    static String getData(int requestedId) {
        return Match(requestedId).of(
            Case($(isIn(1, 2)), e -> throwIae()),
            Case($(isIn(3, 4)), e -> throwNpe()),
            Case($(isIn(5, 6)), e -> throwIse()),
            Case($(isIn(7, 8)), e -> throwUoe()),
            Case($(is(9)), TOP_SECRET_INFO),
            Case($(), DEFAULT_INFO)
        );
    }

    private static String throwIae() {
        throw new IllegalArgumentException("IllegalArgumentException has been thrown!");
    }

    private static String throwNpe() {
        throw new NullPointerException("NullPointerException has been thrown!");
    }

    private static String throwIse() {
        throw new IllegalStateException("IllegalStateException has been thrown!");
    }

    private static String throwUoe() {
        throw new UnsupportedOperationException("UnsupportedOperationException has been thrown!");
    }
}
