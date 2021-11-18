import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static List<Integer> readFile(String path) {

        List<Integer> arr = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String data;

            while ((data = br.readLine()) != null) {
                String[] tmp = data.split(" ");    //Split space
                for(String s: tmp)
                    arr.add(Integer.parseInt(s));
            }

        } catch(Exception e){

        }

        return arr;
    }


    public static void writeSolution(Statistics stats, String path) {
        try (FileWriter ostream = new FileWriter(path)) {
            if (stats.operationsSolution.length() > 0) {
                ostream.write(stats.operationsSolution.length() + "\n");
            } else {
                ostream.write("-1\n");
            }
            ostream.write(stats.operationsSolution + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeStats(Statistics stats, String path) {
        try (FileWriter ostream = new FileWriter(path)) {
            if (stats.operationsSolution.length() > 0) {
                ostream.write(stats.operationsSolution.length() + "\n");
            } else {
                ostream.write("-1\n");
            }
            ostream.write(stats.visitedVertex + "\n");
            ostream.write(stats.finishedVertex + "\n");
            ostream.write(stats.depth + "\n");

            double timeMillis = stats.time/ 1E6;
            DecimalFormat threeDigits = new DecimalFormat("#0.000");
            ostream.write(threeDigits.format(timeMillis) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        List<Integer> readedValues = readFile(args[2]);
        //List<Integer> readedValues = readFile("puzz.txt");
        int zeroX = 0;
        int zeroY = 0;
        int width = readedValues.get(0);
        int height = readedValues.get(1);


        int[][] numbers = new int[height][width];

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                numbers[i][j] = readedValues.get(readedValues.get(0)*i+j+2);
                if(numbers[i][j] == 0){
                    zeroX = j;
                    zeroY = i;
                }
            }
        }


        Puzzle puz = new Puzzle(numbers,width,height,zeroX, zeroY, Character.MIN_VALUE);


        String strategy = args[0];
        String order = args[1];


        if(strategy.equals("dfs"))
        {
            DFS dfs = new DFS(order);
            dfs.solvingPuzzleDFS(puz);
            writeSolution(dfs.getStats(), args[3]);
            writeStats(dfs.getStats(), args[4]);
        }
        else if(strategy.equals("bfs"))
        {
            BFS bfs = new BFS(order);
            bfs.solvingPuzzleBFS(puz);
            writeSolution(bfs.getStats(), args[3]);
            writeStats(bfs.getStats(), args[4]);

        }
        else if(strategy.equals("astr"))
        {
            AStar astar = new AStar(order);
            astar.solvingPuzzleAStar(puz);
            writeSolution(astar.getStats(), args[3]);
            writeStats(astar.getStats(), args[4]);

        }

    }




}
