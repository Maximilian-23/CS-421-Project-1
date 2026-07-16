public class Position {
    int row;
    int column;

    Position(int x, int y){
        this.row = x;
        this.column = y;
    }

    public int get_row(){
        return row;
    }

    public int get_column(){
        return column;
    }
}