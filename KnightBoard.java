public class KnightBoard {
    int[][] board;
    int board_size;
    int total_moves;
    boolean is_solvable;
    Position pos;
    int[] row_move = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] column_move = {1, 2, 2 , 1, -1, -2, -2, -1};

    KnightBoard(int n, int x, int y){
        board = new int[n][n];
        board_size = n;
        pos = new Position(x, y);
    }

    public boolean solve(int row_start, int column_start, int heuristic){

        if (is_valid_move(row_start, column_start)){
                pos.row = row_start;
                pos.column= column_start;
                total_moves = 1;
                is_solvable = true;
                board[pos.get_row()][pos.get_column()] = 1;
        }
        else{
            return false;
        }

        if (heuristic == 0){
            return no_heuristic(row_start, column_start, 2);
        }
        else if (heuristic == 1){
            return heuristicI(row_start, column_start, 2);
        }
        else{
            return heuristicII(row_start, column_start, 2);
        }

    }

    private boolean no_heuristic(int row, int column, int move){

        if (move > (board_size * board_size)){
            return true;
        }

        int new_row;
        int new_column;
        for (int i = 0; i < 8; i++){
            new_row = row + row_move[i];
            new_column = column + column_move[i];
            if (is_valid_move(new_row,new_column)){
                board[new_row][new_column] = move;
                total_moves++;

                if (no_heuristic(new_row, new_column, move + 1)){
                    return true;
                }

                board[new_row][new_column] = 0;
            }
        }

        return false;
    }

    private boolean heuristicI(int row, int column, int move){

        if (move > (board_size * board_size)){
            return true;
        }

        int[][] candidates = new int[8][3];
        int candidate_count = 0;

        for (int i = 0; i < 8; i++){
            int new_row = row + row_move[i];
            int new_column = column + column_move[i];
            if (is_valid_move(new_row, new_column)){
                candidates[candidate_count][0] = new_row;
                candidates[candidate_count][1] = new_column;
                candidates[candidate_count][2] = distance_to_closest_boarder(new_row, new_column);
                candidate_count++;
            }
        }

        if (candidate_count == 0){
            return false;
        }

        insertion_sort(candidates, candidate_count);

        for (int i= 0; i < candidate_count; i++){
            board[candidates[i][0]][candidates[i][1]] = move;
            total_moves++;
            if (heuristicI(candidates[i][0], candidates[i][1], move + 1)){
                    return true;
            }
            board[candidates[i][0]][candidates[i][1]] = 0;
        }

        return false;
    }

    private boolean heuristicII(int row, int column, int move){

        if (move > (board_size * board_size)){
            return true;
        }

        int[][] candidates = new int[8][3];
        int candidate_count = 0;

        for (int i = 0; i < 8; i++){
            int new_row = row + row_move[i];
            int new_column = column + column_move[i];
            if (is_valid_move(new_row, new_column)){
                candidates[candidate_count][0] = new_row;
                candidates[candidate_count][1] = new_column;
                candidates[candidate_count][2] = num_eligible_moves(new_row, new_column);
                candidate_count++;
            }
        }

        if (candidate_count == 0){
            return false;
        }

        insertion_sort(candidates, candidate_count);

        for (int i= 0; i < candidate_count; i++){
            board[candidates[i][0]][candidates[i][1]] = move;
            total_moves++;
            if (heuristicII(candidates[i][0], candidates[i][1], move + 1)){
                    return true;
                }
                board[candidates[i][0]][candidates[i][1]] = 0;
        }

        return false;
    }

    private int num_eligible_moves(int row, int column){
        int eligible_moves = 0;
        for (int i = 0; i < 8; i++){
            int new_row = row + row_move[i];
            int new_column = column + column_move[i];
            if (is_valid_move(new_row, new_column)){
                eligible_moves++;
            }
        }

        return eligible_moves;
    }

    private void insertion_sort(int[][] candidates, int count) {
        for (int i = 1; i < count; i++) {
            int[] keyRow = candidates[i];
            int keyVal = candidates[i][2];
        
            int j = i - 1;

            while (j >= 0 && candidates[j][2] > keyVal) {
                candidates[j + 1] = candidates[j];
                j--;
            }
        
            candidates[j + 1] = keyRow;
        }
    }


    private int distance_to_square(int current_row, int current_column, int dest_row, int dest_column){
        return Math.abs(current_row - dest_row) + Math.abs(current_column - dest_column);
    }

    private int distance_to_closest_boarder(int row, int column){

        if (distance_to_square(row, column, 0, 0) < distance_to_square(row, column, 0, board_size - 1)){
            if (distance_to_square(row, column, 0, 0) < distance_to_square(row, column, board_size - 1 , 0)){
                return distance_to_square(row, column, 0, 0);
            }
            else{
                return distance_to_square(row, column, board_size - 1, 0);
            }
        }
        else{
            if (distance_to_square(row, column, 0, 0) < distance_to_square(row, column, board_size - 1 , 0)){
                return distance_to_square(row, column, 0, board_size - 1);
            }
            else{
                return distance_to_square(row, column, board_size - 1, board_size - 1);
            }
        }
    }

    public boolean is_valid_move(int x, int y){
        if ((x >= 0 && x < board_size) && (y >= 0 && y < board_size) && board[x][y] == 0){
            return true;
        }
        return false;
    }

    public void print_board(){

        double temp_num = board_size * board_size;
        int width = 1;
        do{
            width++;
            temp_num /= 10.0;
        } while (temp_num > 1);

        String format = "%-" + width + "d";

        System.out.println("Total Moves: " + total_moves);

        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                System.out.printf(format, board[i][j]);
            }
            System.out.println();
        }
    }
}