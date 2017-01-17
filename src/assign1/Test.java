package assign1;

/**
 * Created by emol on 1/15/17.
 */

import java.util.*;
public class Test {

    public static void uniformCostSearch(){
        //cost is the same for every move
        bfs();
    }
    public static void bfs(){
        PuzzleProblem p = new PuzzleProblem();
        Queue queue = new LinkedList<PuzzleState>();

        queue.add(p.startState);
        while (!queue.isEmpty()){
            System.out.println("**************");
            PuzzleState crtState = (PuzzleState) queue.poll();
            crtState.print();

            if (p.isGoal(crtState)) return;
            p.visitedState.add(crtState);

            State[] states = p.nextStates(crtState);
            //when multiple states, we prefer moving lower numbered tile. Since it's queue, go from low to high
            for (int i = 1; i< 6; i++){
                PuzzleState nextState = (PuzzleState)states[i];
                if (nextState!= null){
                    //nextState.print();
                    //System.out.println();
                    if(!p.isVisited(nextState)){
                        queue.add(nextState);
                    }
                }
            }
        }

    }

    public static void iterativeDeepeningSearch(){
        for (int i = 1; i< Integer.MAX_VALUE; i++){
            if (limitedDfs(i)) return;
        }
    }

    /**
     *
     * @param limit the level limit
     * @return boolean return true if we find the goal or we have searched all nodes in the tree
     */
    public static boolean limitedDfs(int limit){
        PuzzleProblem p = new PuzzleProblem();
        Stack stk = new Stack();

        // the second item in the list is the level of the state in the tree
        Object[] item = {p.startState, 1};
        stk.add(item);

        //flag
        boolean moreNodes = false;

        while(!stk.isEmpty()){
            //the limit >= 1, so the first item must be able to pop
            //no need to check level here, just check when you are about to add stuff in the queue
            System.out.println("**************");
            Object[] items = (Object[]) (stk.pop());
            PuzzleState crtState = (PuzzleState)items[0];
            int level = (int) items[1];
            crtState.print();
            //System.out.println();

            if (p.isGoal(crtState)) return true;

            p.visitedState.add(crtState);

            State[] states = p.nextStates(crtState);
            if (level == limit){
                for (State s : states){
                    if (s != null) moreNodes = true;
                }
                continue;
            }

            //when multiple states, we prefer moving lower numbered tile. Since it's stack, go from high to low
            for (int i = 5; i> 0; i--){
                PuzzleState nextState = (PuzzleState)states[i];
                if (nextState!= null){
                    if(!p.isVisited(nextState)){
                        //nextState.print();
                        Object[] item2 = {nextState, level+1};
                        stk.add(item2);
                    }
                }
            }

        }
        if (moreNodes) return false;
        return true;
    }


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
            //when multiple states, we prefer moving lower numbered tile. Since it's stack, go from high to low
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
