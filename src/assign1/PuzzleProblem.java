package assign1;

import java.util.*;

/**
 * Created by emol on 1/15/17.
 */
public class PuzzleProblem extends Problem{
    PuzzleState startState;
    PuzzleState goalState;
    Operator[] ops = Operator.values();
    ArrayList<PuzzleState> visitedState = new ArrayList<PuzzleState>();

    public PuzzleProblem(){
        char[][] tiles = {{'5', '1', ' '},{'4', '3', '2'}};
        int[] bpos = {0, 2};
        this.startState = new PuzzleState(tiles, bpos);

        char[][] gTiles = {{' ', '1', '2'}, {'5', '4', '3'}};
        int[] gbpos = {0, 0};
        this.goalState = new PuzzleState(gTiles, gbpos);
    }

    boolean isGoal (State crtState){
        return crtState.isEqual(this.goalState);
    }

    boolean isLegal (State s, Operator op){
        int row = ((PuzzleState)s).spotPos[0];
        int col = ((PuzzleState)s).spotPos[1];
        switch (op){
            case UP:
                if (row == 1) return true;
                break;

            case DOWN:
                if (row == 0) return true;
                break;

            case LEFT:
                if (col > 0) return true;
                break;

            case RIGHT:
                if (col < 2) return true;
                break;
        }
        return false;
    }


    public ArrayList getLegalOps (State s){
        ArrayList legalOps = new ArrayList<Operator>();
        for (Operator op : this.ops){
            if(this.isLegal(s, op)) legalOps.add(op);
        }
        return legalOps;
    }


    //if multiple states, moving smaller tile is preferred
    public State[] nextStates(State s){
        ArrayList<Operator> ops = this.getLegalOps(s);
        State[] states = new State[6];
        for (Operator op : ops){
            ArrayList<Object> state = nextState(s, op);
            int moved = Integer.parseInt(((Character)state.get(0)).toString());
            states[moved] = (PuzzleState) state.get(1);
        }
        return states;
    }


    ArrayList<Object> nextState (State crtState, Operator op){
        ArrayList<Object> result = new ArrayList<>();
        char[][] tiles = ((PuzzleState)crtState).tilePos;
        int[] spPos = ((PuzzleState)crtState).spotPos;

        char[][] nTiles = new char[2][3];
        nTiles[0] = tiles[0].clone();
        nTiles[1] = tiles[1].clone();

        int[] nSpPos = spPos.clone();

        int col = nSpPos[1];
        int row = nSpPos[0];

        switch (op){
            case UP:
                result.add(nTiles[0][col]);
                nTiles[1][col] = nTiles[0][col];
                nTiles[0][col] = ' ';
                nSpPos[0] = 0;
                nSpPos[1] = col;
                break;

            case DOWN:
                result.add((nTiles[1][col]));
                nTiles[0][col] = nTiles[1][col];
                nTiles[1][col] = ' ';
                nSpPos[0] = 1;
                nSpPos[1] = col;
                break;

            case LEFT:
                result.add(nTiles[row][col-1]);
                nTiles[row][col] = nTiles[row][col-1];
                nTiles[row][col-1] = ' ';
                nSpPos[0] = row;
                nSpPos[1] = col - 1;
                break;

            case RIGHT:
                result.add(nTiles[row][col+1]);
                nTiles[row][col] = nTiles[row][col+1];
                nTiles[row][col+1] = ' ';
                nSpPos[0] = row;
                nSpPos[1] = col + 1;
                break;

        }
        result.add(new PuzzleState(nTiles, nSpPos));
        return result;
    }

    float cost(State s, Operator op){
        return 1;
    }

    public boolean isVisited(PuzzleState s){
        for (PuzzleState ps : this.visitedState){
            if (s.isEqual(ps)) return true;
        }
        return false;
    }

}
