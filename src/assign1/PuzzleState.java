package assign1;

import java.util.Arrays;

/**
 * Created by emol on 1/15/17.
 */
public class PuzzleState extends State{
    int[] spotPos; //int[2] the first int for row, the second for col
    char[][] tilePos; //char[2][3]

    public PuzzleState(char[][] tiles, int[] spot){

        this.tilePos = tiles;
        this.spotPos = spot;
    }

    public void print(){
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(tilePos[i][j]+"  ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public boolean isEqual(State s){
        return Arrays.deepEquals(this.tilePos, ((PuzzleState)s).tilePos);
    }


}
