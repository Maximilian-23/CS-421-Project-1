public class Position {
    int x;
    int y;

    Position(int x_start, int y_start){
        x = x_start;
        y = y_start;
    }
    
    public void set_position(int x_new, int y_new){
        x = x_new;
        y = y_new;
    }

    public int get_x_position(){
        return x;
    }

    public int get_y_position(){
        return y;
    }
}