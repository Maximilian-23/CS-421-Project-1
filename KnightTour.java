public class KnightTour {
    public static void main(String[] args) {
        
         // Print Usage
        if (args.length != 4){
            System.out.println("Usage: java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>");
            System.exit(1);
        }

        int heuristic = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int x = Integer.parseInt(args[2]);
        int y = Integer.parseInt(args[3]);
        int moves = 0;

        if (n <= 2){
            System.out.println("n must be greater than 2");
            System.exit(1);
        }

        KnightBoard board = new KnightBoard(n, x, y);
        Position pos = new Position(x, y);

        if (heuristic == 0){
            
        }

        board.print_board();
    }
}
