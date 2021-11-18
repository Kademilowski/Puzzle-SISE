import java.util.*;

public class AStar{


    LinkedList<Vertex> frontierList= new LinkedList<>();
    LinkedHashMap<Vertex, Vertex> exploredList = new LinkedHashMap<>();

    int[][] solution = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};


    int visitedV = 0;
    int finishedV = 0;
    boolean solved = false;
    long timeDuration = 0;
    String path = "";
    int maxDepth = 0;
    boolean isUseHamm = false;
    Statistics stats = new Statistics();


    public AStar(String heuristic){

        if(heuristic.equals("hamm")){
            isUseHamm = true;
        }
        else
        {
            isUseHamm = false;
        }
    }




    public void solvingPuzzleAStar(Puzzle puzzle) {
        long timeStart = System.nanoTime();

        Vertex currentV = new Vertex(null, puzzle);


        if (currentV != null)
        {
            currentV.puzzle.estimateValue = currentV.getDepth();

            if(isUseHamm){
                currentV.puzzle.estimateValue += Hamming(currentV);
            }
            else{
                currentV .puzzle.estimateValue += Manhattan(currentV);
            }

            frontierList.add(currentV);
            visitedV++;

        }


        while (!frontierList.isEmpty() && !solved) {

            currentV = Collections.min(frontierList, (a, b) -> a.puzzle.estimateValue - b.puzzle.estimateValue);
            frontierList.remove(currentV);

            maxDepth = Math.max(maxDepth, currentV.getDepth());

            if (Arrays.deepEquals(currentV.puzzle.numbers, solution)) {
                solved = true;
                path = currentV.getSolutionOperations();
                timeDuration = System.nanoTime() - timeStart;
                return;
            }

            Vertex[] neighbours = currentV.getNeighbours();
            for (Vertex neighbour : neighbours) {


                if (neighbour != null && !exploredList.containsKey(neighbour)) {
                    neighbour.puzzle.estimateValue = neighbour.getDepth();

                    if(isUseHamm){
                        neighbour.puzzle.estimateValue += Hamming(neighbour);
                    }
                    else{
                        neighbour.puzzle.estimateValue += Manhattan(neighbour);
                    }

                    exploredList.put(neighbour, neighbour);
                    frontierList.add(neighbour);
                    visitedV++;
                }
            }

            finishedV++;
        }

        timeDuration = System.nanoTime() - timeStart;
    }




    private int Hamming(Vertex v)
    {
        int difference = 0;
        for (int i = 0; i < v.puzzle.numbers.length; i++) {
            for(int j=0; j<v.puzzle.numbers[i].length; j++) {
                if (v.puzzle.numbers[i][j] != solution[i][j] && v.puzzle.numbers[i][j]!=0) {
                    difference++;
                }
            }
        }
        return difference;
    }


    private int Manhattan(Vertex v)
    {
        int correctX, correctY;
        int distance = 0;

        for (int i = 0; i < v.puzzle.numbers.length; i++) {
            for(int j=0; j<v.puzzle.numbers[i].length; j++){
                if (v.puzzle.numbers[i][j] != solution[i][j]  && v.puzzle.numbers[i][j]!=0) { //
                    int[] goalPos = indexElementPuzzle(solution, v.puzzle.numbers[i][j]);
                    correctX = goalPos[0];
                    correctY = goalPos[1];
                    distance += Math.abs(j - correctX) + Math.abs(i - correctY);
                }
            }
        }

        return distance;

    }


    public static int[] indexElementPuzzle(int[][] arr, int value) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == value) {
                    int [] tmp = {j,i};
                    return tmp;
                }
            }
        }
        return null;
    }


    public Statistics getStats(){
        this.stats.setAll(this.visitedV, this.finishedV, this.maxDepth, this.timeDuration, this.path);
        return this.stats;
    }
}