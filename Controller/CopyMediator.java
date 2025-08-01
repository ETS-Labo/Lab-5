package Controller;

import Model.PerspectiveMemento;
import Model.PerspectiveModel;

public class CopyMediator {
    private PerspectiveMemento lastMemento;
    private MementoManager manag
    private CopyStrategy strat;
    private CopyMediator mediator;

    private CopyMediator(){}

    public CopyMediator getInstance(MementoManager manag){
        if(mediator == null){
            mediator = new CopyMediator();
        }
        this.manag = manag;
        return mediator;
    }

    public void copy(PerspectiveModel source, CopyStrategy strat){
        lastMemento = source.createMemento();
        MementoManager.addMemento(lastMemento);
        this.strat = strat;
    }

    public void past(PerspectiveModel source, PerspectiveModel target){
        strat.apply(source, target);
    }

}
