import java.util.Arrays;

public class Vertex{

    Puzzle puzzle;
    Vertex parent;

    public Vertex(Vertex parent, Puzzle puzzle)
    {
        this.puzzle = puzzle;
        this.parent = parent;
    }



    public Vertex getLeftNeighbor()
    {
        if(puzzle.zeroX % puzzle.width != 0)
        {


            int [][] tmpw = new int[puzzle.height][puzzle.width];

            for(int i=0; i<puzzle.numbers.length; i++){
                for(int j=0; j<puzzle.numbers[i].length; j++){
                    tmpw[i][j] = puzzle.numbers[i][j];
                }

            }


            Puzzle puzzleState = new Puzzle(tmpw, puzzle.width, puzzle.height, puzzle.zeroX, puzzle.zeroY, 'L');
            puzzleState.changeElementWithEmpty(-1, 0);

            Vertex neighbor = new Vertex(this, puzzleState);

            return neighbor;

        }
        return null;
    }


    public Vertex getRightNeighbor()
    {
        int tmpX = puzzle.zeroX+1;

        if(tmpX % puzzle.width !=0)
        {


            int [][] tmpw = new int[puzzle.height][puzzle.width];

            for(int i=0; i<puzzle.numbers.length; i++){
                for(int j=0; j<puzzle.numbers[i].length; j++){
                    tmpw[i][j] = puzzle.numbers[i][j];
                }

            }

            Puzzle puzzleState = new Puzzle(tmpw, puzzle.width, puzzle.height, puzzle.zeroX, puzzle.zeroY, 'R');
            puzzleState.changeElementWithEmpty(1, 0);

            Vertex neighbor = new Vertex(this, puzzleState);


            return neighbor;

        }
        return null;
    }



    public Vertex getUpNeighbor()
    {
        if(puzzle.zeroY % puzzle.height !=0)
        {

            int [][] tmpw = new int[puzzle.height][puzzle.width];

            for(int i=0; i<puzzle.numbers.length; i++){
                for(int j=0; j<puzzle.numbers[i].length; j++){
                    tmpw[i][j] = puzzle.numbers[i][j];
                }

            }


            Puzzle puzzleState = new Puzzle(tmpw, puzzle.width, puzzle.height, puzzle.zeroX, puzzle.zeroY, 'U');
            puzzleState.changeElementWithEmpty(0, -1);


            Vertex neighbor = new Vertex(this, puzzleState);

            return neighbor;

        }
        return null;
    }



    public Vertex getDownNeighbor()
    {
        int tmpY = puzzle.zeroY;
        tmpY = tmpY + 1;
        if(tmpY % puzzle.height !=0)
        {

            int [][] tmpw = new int[puzzle.height][puzzle.width];


            for(int i=0; i<puzzle.numbers.length; i++){
                for(int j=0; j<puzzle.numbers[i].length; j++){
                    tmpw[i][j] = puzzle.numbers[i][j];
                }
            }


            Puzzle puzzleState = new Puzzle(tmpw, puzzle.width, puzzle.height, puzzle.zeroX, puzzle.zeroY, 'D');
            puzzleState.changeElementWithEmpty(0, 1);

            Vertex neighbor = new Vertex(this, puzzleState);

            return neighbor;


        }
        return null;
    }


    public String getSolutionOperations()
    {
        String operations = "";

        for(Vertex v = this; v.parent != null; v = v.parent)
        {
            operations = operations + v.puzzle.move;
        }

        return new StringBuilder(operations).reverse().toString();

    }


    public int getDepth()
    {
        int depth = 0;
        for(Vertex v = this; v.parent != null; v = v.parent)
        {
            depth++;
        }

        return depth;
    }


    public Vertex[] getNeighbours()
    {
        Vertex[] nodes = {getDownNeighbor(), getUpNeighbor(), getLeftNeighbor(), getRightNeighbor()};
        return nodes;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vertex v = (Vertex) obj;

        return Arrays.deepEquals(v.puzzle.numbers, puzzle.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzle.numbers);
    }



}
