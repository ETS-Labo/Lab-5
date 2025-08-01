package Controller;

import Model.PerspectiveMemento;
import Model.PerspectiveModel;

public class CopyMediator {
    private PerspectiveMemento lastMemento;
    private MementoManager manag
    private CopyStrategy strat;
    private CopyMediator mediator;

    private CopyMediator(){}

    public CopyMediator getInstance(Memento Manager manag){
        if(mediator == null){
            mediator = new CopyMediator();
        }
        this.manag = manage;
        return mediator;
    }

    public void copy(PerspectiveModel source, CopyStrategy strat){
        lastMemento = source.createMemento();
        MementoManager.addMemento(lastMemento);
        this.strat = strat;
    }

    public void past(PerspectiveModel target){
        start.apply(target)
    }

}
