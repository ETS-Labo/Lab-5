package Controller;

import Model.PerspectiveModel;

public interface CopyStrategy {

    void apply(PerspectiveModel source, PerspectiveModel target);
}
