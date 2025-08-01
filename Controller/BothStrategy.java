package Controller;

import Model.PerspectiveModel;

public class BothStrategy implements CopyStrategy {
        private final ScaleStrategy scaleStrat = new ScaleStrategy();
        private final TranslateStrategy translateStrat = new TranslateStrategy();
        @Override
        public void apply(PerspectiveModel source, PerspectiveModel target) {
            scaleStrat.apply(source, target);
            translateStrat.apply(source, target);
    }

}
