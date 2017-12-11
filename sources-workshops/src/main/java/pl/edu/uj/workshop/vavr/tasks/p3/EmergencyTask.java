package pl.edu.uj.workshop.vavr.tasks.p3;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

public final class EmergencyTask {

    private EmergencyTask() {
    }

    public static Option<String> getInformation(int requestedId) {
        return Try.ofSupplier(() -> HeavyAndComplexService.getData(requestedId))
            .recover(
                e -> Match(e).of(
                    Case($(instanceOf(IllegalArgumentException.class)), "IAE"),
                    Case($(instanceOf(IllegalStateException.class)), "ISE"),
                    Case($(instanceOf(UnsupportedOperationException.class)), "UOE"),
                    Case($(instanceOf(NullPointerException.class)), "NPE")
                )
            ).toOption();
    }

    public static Either<Throwable, String> getInformationInAnotherWay(int requestedId) {
        return Try.ofSupplier(() -> HeavyAndComplexService.getData(requestedId))
            .onFailure(e -> System.out.println(e.getMessage()))
            .toEither();
    }

    public static String getInformationInAnotherAnotherWay(int requestedId) {
        return Try.ofSupplier(() -> HeavyAndComplexService.getData(requestedId))
            .getOrElseGet(e -> e.getClass().getSimpleName());
    }
}
