public class KnightBoard {
    int[][] board;
    int board_size;
    

    KnightBoard(int n, int x, int y){
        board = new int[n][n];
        board_size = n;
        board[x][y] = 1;
    }

    public boolean is_complete(){
         for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                if (board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean is_valid_move(int x, int y){
        if ((x >= 0 && x < board_size) && (y >= 0 && y < board_size) && board[x][y] == 0){
            return true;
        }
        return false;
    }

    public void update_board(int x, int y, int step){
        board[x][y] = step;
    }

     public void print_board(){
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}