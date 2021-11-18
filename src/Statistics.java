public class Statistics {

    int visitedVertex = 0;
    int finishedVertex = 0;
    int depth = 0;
    long time = 0;
    String operationsSolution = "";



    public void setAll(int visited, int finished, int depth, long time, String opeartions)
    {
        this.visitedVertex = visited;
        this.finishedVertex = finished;
        this.depth = depth;
        this.time = time;
        this.operationsSolution = opeartions;
    }
}
