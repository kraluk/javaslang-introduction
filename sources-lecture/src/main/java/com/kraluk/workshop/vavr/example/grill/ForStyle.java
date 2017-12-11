package com.kraluk.workshop.vavr.example.grill;

import com.kraluk.workshop.vavr.example.grill.DataClass.Charcoal;
import com.kraluk.workshop.vavr.example.grill.DataClass.CornCob;
import com.kraluk.workshop.vavr.example.grill.DataClass.Dinner;
import com.kraluk.workshop.vavr.example.grill.DataClass.Fire;
import com.kraluk.workshop.vavr.example.grill.DataClass.Lighter;

import java.util.Objects;

import io.vavr.control.Option;

import static io.vavr.API.For;

/**
 * @author lukasz
 */
public final class ForStyle {

    private ForStyle() {
    }

    public static Option<Dinner> makeDinner(GrillService service) {
        return For(service.getCharcoal(), charcoal ->
            For(service.getLighter(), lighter ->
                For(service.lightFire(charcoal, lighter), fire ->
                    For(service.getCornCob(), cornCob ->
                        For(service.grill(fire, cornCob))
                            .yield()
                    )
                )
            )
        ).toOption();
    }

    interface GrillService {
        Option<Charcoal> getCharcoal();

        Option<Lighter> getLighter();

        Option<Fire> lightFire(Charcoal charcoal, Lighter lighter);

        Option<CornCob> getCornCob();

        Option<Dinner> grill(Fire fire, CornCob cornCob);
    }

    /**
     * Just a *dummy* implementation
     */
    final class GrillServiceImpl implements GrillService {

        @Override
        public Option<Charcoal> getCharcoal() {
            return Option.of(new Charcoal());
        }

        @Override
        public Option<Lighter> getLighter() {
            return Option.of(new Lighter());
        }

        @Override
        public Option<Fire> lightFire(Charcoal charcoal, Lighter lighter) {
            if (Objects.isNull(charcoal) || Objects.isNull(lighter)) {
                return Option.none();
            } else {
                return Option.of(new Fire());
            }
        }

        @Override
        public Option<CornCob> getCornCob() {
            return Option.of(new CornCob());
        }

        @Override
        public Option<Dinner> grill(Fire fire, CornCob cornCob) {
            if (Objects.isNull(fire) || Objects.isNull(cornCob)) {
                return Option.none();
            } else {
                return Option.of(new Dinner());
            }
        }
    }
}