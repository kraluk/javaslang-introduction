package com.kraluk.workshop.vavr.example.grill;

import com.kraluk.workshop.vavr.example.grill.DataClass.Charcoal;
import com.kraluk.workshop.vavr.example.grill.DataClass.CornCob;
import com.kraluk.workshop.vavr.example.grill.DataClass.Dinner;
import com.kraluk.workshop.vavr.example.grill.DataClass.Fire;
import com.kraluk.workshop.vavr.example.grill.DataClass.Lighter;

/**
 * @author lukasz
 */
public final class PojoStyle {

    private PojoStyle() {
    }

    public static Dinner makeDinner(GrillService service) {
        Charcoal charcoal = service.getCharcoal();
        Lighter lighter = service.getLighter();

        if (charcoal != null && lighter != null) {
            Fire fire = service.lightFire(charcoal, lighter);
            CornCob cornCob = service.getCornCob();

            if (fire != null && cornCob != null) {
                return service.grill(fire, cornCob);
            }
        }
        return null; // ???
    }

    interface GrillService {
        Charcoal getCharcoal();

        Lighter getLighter();

        Fire lightFire(Charcoal charcoal, Lighter lighter);

        CornCob getCornCob();

        Dinner grill(Fire fire, CornCob cornCob);
    }

    final class GrillServiceImpl implements GrillService {

        @Override
        public Charcoal getCharcoal() {
            return new Charcoal();
        }

        @Override
        public Lighter getLighter() {
            return new Lighter();
        }

        @Override
        public Fire lightFire(Charcoal charcoal, Lighter lighter) {
            if (charcoal == null || lighter == null) {
                return null;
            }

            return new Fire();
        }

        @Override
        public CornCob getCornCob() {
            return new CornCob();
        }

        @Override
        public Dinner grill(Fire fire, CornCob cornCob) {
            if (fire == null || cornCob == null) {
                return null;
            }

            return new Dinner();
        }
    }
}
