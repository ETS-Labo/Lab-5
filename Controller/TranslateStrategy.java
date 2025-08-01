package Controller;

import Model.PerspectiveModel;

import java.awt.*;

public class TranslateStrategy implements CopyStrategy {
    @Override
    public void apply(PerspectiveModel source, PerspectiveModel target) {
        Point translation = source.getTranslation();
        target.setTranslation(translation.getX(), translation.getY());
    }
}
