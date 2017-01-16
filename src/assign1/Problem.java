package assign1;

import java.util.ArrayList;


/**
 * Created by emol on 1/15/17.
 */
public abstract class Problem {
    State startState;
    abstract boolean isGoal (State crtState);
    abstract boolean isLegal (State s, Operator op);
    abstract ArrayList getLegalOps (State s);
    abstract ArrayList nextState (State crtState, Operator op);
    abstract float cost(State s, Operator op);

    public State getStartState() {
        return startState;
    }
}
