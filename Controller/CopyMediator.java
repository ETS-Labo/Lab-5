package Controller;

import Model.PerspectiveModel;

public class CopyMediator {
    private PerspectiveMemento lastMemento;
    private CopyStrategy strat;
    private CopyMediator mediator;

    private CopyMediator(){}

    public CopyMediator getInstance(){
        if(mediator == null){
            mediator = new CopyMediator();
        }
        return mediator;
    }

    public void copy(PerspectiveModel source, CopyStrategy strat){
        lastMemento = ;
        this.strat = strat
        
    }

    public void past(PerspectiveModel target){
        strat.apply(lastMemento.getState().getModel(), target);
    }
}
