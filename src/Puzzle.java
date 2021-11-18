import com.sun.jdi.Value;

public class Puzzle {


    int [][] numbers;
    int width;
    int height;

    //Pozycja pustego pola
    int zeroX;
    int zeroY;

    char move;
    int estimateValue = 0;


    public Puzzle(int[][] numbers, int width, int height, int zeroX, int zeroY, char move)
    {
        this.numbers = numbers.clone();
        this.width = width;
        this.height = height;
        this.zeroX = zeroX;
        this.zeroY = zeroY;
        this.move = move;
    }


    public void changeElementWithEmpty(int x, int y)
    {
        this.numbers[this.zeroY][this.zeroX] = this.numbers[this.zeroY+y][this.zeroX+x];
        this.numbers[this.zeroY+y][this.zeroX+x] = 0;
        this.zeroX = this.zeroX + x;
        this.zeroY = this.zeroY + y;
    }




}
