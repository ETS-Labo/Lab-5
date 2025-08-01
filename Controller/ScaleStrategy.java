package Controller;

import Model.PerspectiveModel;

public class ScaleStrategy implements CopyStrategy {
    @Override
    public void apply(PerspectiveModel source, PerspectiveModel target) {
        double scale = source.getScale();
        target.setScale(scale);
    };
}
