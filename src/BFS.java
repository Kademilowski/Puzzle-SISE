
import java.util.*;

public class BFS {


    String order;
    int[][] solution = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};


    LinkedList<Vertex> frontierList = new LinkedList<>();
    LinkedHashMap<Vertex, Vertex> exploredList = new LinkedHashMap<>();


    int visitedV = 0;
    int finishedV = 0;
    boolean solved = false;
    long timeDuration = 0;
    String path = "";
    int maxDepth = 0;
    Statistics stats = new Statistics();

    public BFS(String order){
        this.order = order;
    }



    public void solvingPuzzleBFS(Puzzle puzzle)
    {
        long StartTime = System.nanoTime();

        Vertex currentV = new Vertex(null, puzzle);

        if(currentV != null)
        {
            visitedV++;
            frontierList.add(currentV);
        }


        if (Arrays.deepEquals(currentV.puzzle.numbers, solution))
        {
            solved = true;
            path = currentV.getSolutionOperations();
            timeDuration = System.nanoTime() - StartTime;
            return;
        }



        while(!frontierList.isEmpty() && !solved)
        {

            currentV = frontierList.remove();
            maxDepth = Math.max(maxDepth, currentV.getDepth());


            if (Arrays.deepEquals(currentV.puzzle.numbers, solution)) {
                solved = true;
                path = currentV.getSolutionOperations();
                timeDuration = System.nanoTime() - StartTime;
                return;
            }

            for (char letter : this.order.toCharArray()){

                if (letter == 'L')
                {
                    Vertex leftV = currentV.getLeftNeighbor();
                    if(leftV != null && !exploredList.containsKey(leftV))
                    {
                        visitedV++;
                        frontierList.add(leftV);
                    }
                }
                else if (letter == 'R')
                {
                    Vertex rightV = currentV.getRightNeighbor();

                    if(rightV != null && !exploredList.containsKey(rightV))
                    {
                        visitedV++;
                        frontierList.add(rightV);
                    }
                }
                else if (letter == 'U')
                {
                    Vertex upV = currentV.getUpNeighbor();

                    if(upV != null && !exploredList.containsKey(upV))
                    {
                        visitedV++;
                        frontierList.add(upV);
                    }
                }

                else if (letter == 'D')
                {
                    Vertex downV = currentV.getDownNeighbor();


                    if(downV != null && !exploredList.containsKey(downV))
                    {
                        visitedV++;
                        frontierList.add(downV);
                    }
                }
            }

            finishedV++;
            exploredList.put(currentV, currentV);

        }

        timeDuration = System.nanoTime() - StartTime;

    }



    public Statistics getStats(){
        this.stats.setAll(this.visitedV, this.finishedV, this.maxDepth, this.timeDuration, this.path);
        return this.stats;
    }



}
