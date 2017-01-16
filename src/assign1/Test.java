package assign1;

/**
 * Created by emol on 1/15/17.
 */

import java.util.*;
public class Test {
    public static void dfs(){
        PuzzleProblem p = new PuzzleProblem();
        Stack stk = new Stack();
        stk.add(p.startState);
        while(!stk.isEmpty()){
            System.out.println("**************");
            PuzzleState crtState = (PuzzleState) stk.pop();
            crtState.print();
            //System.out.println();

            if (p.isGoal(crtState)) return;
            p.visitedState.add(crtState);

            State[] states = p.nextStates(crtState);
            for (int i = 5; i> 0; i--){
                PuzzleState nextState = (PuzzleState)states[i];
                if (nextState!= null){
                    nextState.print();
                    //System.out.println();
                    if(!p.isVisited(nextState)){
                        stk.add(nextState);
                    }
                }
            }

        }
    }
}
