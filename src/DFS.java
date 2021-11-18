
import org.w3c.dom.Node;

import java.util.*;

public class DFS {


    String order;
    int[][] solution = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};

    private LinkedHashMap<Vertex, Vertex> exploredList = new LinkedHashMap<>();
    LinkedList<Vertex> frontierList = new LinkedList<>();


    int visitedV = 0;
    int finishedV = 0;
    boolean solved = false;
    long timeDuration = 0;
    String path = "";
    int maxDepth = 0;
    Statistics stats = new Statistics();


    public DFS(String order){
        this.order = order;
    }


    public void solvingPuzzleDFS(Puzzle puzzle) {
        long StartTime = System.nanoTime();

        Vertex currentV = new Vertex(null, puzzle);

        if (currentV != null){
            visitedV++;
            frontierList.push(currentV);
        };


        if (Arrays.deepEquals(currentV.puzzle.numbers, solution)) {
            solved = true;
            path = currentV.getSolutionOperations();
            timeDuration = System.nanoTime() - StartTime;
            return;
        }





        while (!frontierList.isEmpty() && !solved)
        {
            currentV = frontierList.pop();
            int depth = currentV.getDepth();

            if (Arrays.deepEquals(currentV.puzzle.numbers, solution)) {
                solved = true;
                path = currentV.getSolutionOperations();
                timeDuration = System.nanoTime() - StartTime;
                return;
            }

            if (depth <= 20)
            {
                for (int i = order.length()-1; i >= 0 && !solved; i--) {

                    char letter = order.toCharArray()[i];
                    if (letter == 'L')
                    {
                        Vertex leftV = currentV.getLeftNeighbor();
                        if (leftV != null)
                        {
                            if (Arrays.deepEquals(leftV.puzzle.numbers, solution)) {
                                visitedV++;
                                solved = true;
                                path = leftV.getSolutionOperations();
                                timeDuration = System.nanoTime() - StartTime;
                                return;
                            }

                            Vertex tmpV = exploredList.get(leftV);
                            if (tmpV == null) {
                                visitedV++;
                                exploredList.put(leftV, leftV);
                                frontierList.push(leftV);
                            }
                        }
                    }
                    else if (letter == 'R')
                    {
                        Vertex rightV = currentV.getRightNeighbor();
                        if (rightV != null)
                        {
                            if (Arrays.deepEquals(rightV.puzzle.numbers, solution)) {
                                visitedV++;
                                solved = true;
                                path = rightV.getSolutionOperations();
                                timeDuration = System.nanoTime() - StartTime;
                                return;
                            }

                            Vertex tmpV = exploredList.get(rightV);
                            if (tmpV == null) {
                                visitedV++;
                                exploredList.put(rightV, rightV);
                                frontierList.push(rightV);
                            }
                        }
                    }
                    else if (letter == 'D')
                    {
                        Vertex downV = currentV.getDownNeighbor();
                        if(downV != null)
                        {
                            if (Arrays.deepEquals(downV.puzzle.numbers, solution)) {
                                visitedV++;
                                solved = true;
                                path = downV.getSolutionOperations();
                                timeDuration = System.nanoTime() - StartTime;
                                return;
                            }

                            Vertex tmpV = exploredList.get(downV);
                            if (tmpV == null) {
                                visitedV++;
                                exploredList.put(downV, downV);
                                frontierList.push(downV);
                            }
                        }
                    }
                    else if (letter == 'U')
                    {
                        Vertex upV = currentV.getUpNeighbor();
                        if (upV != null)
                        {
                            if (Arrays.deepEquals(upV.puzzle.numbers, solution)) {
                                visitedV++;
                                solved = true;
                                path = upV.getSolutionOperations();
                                timeDuration = System.nanoTime() - StartTime;
                                return;
                            }

                            Vertex tmpV = exploredList.get(upV);
                            if (tmpV == null) {
                                visitedV++;
                                exploredList.put(upV, upV);
                                frontierList.push(upV);
                            }
                        }
                    }
                }
                maxDepth = Math.max(maxDepth, depth);
            }

            finishedV++;
        }

        timeDuration = System.nanoTime() - StartTime;
    }


    public Statistics getStats(){
        this.stats.setAll(this.visitedV, this.finishedV, this.maxDepth, this.timeDuration, this.path);
        return this.stats;
    }

}
