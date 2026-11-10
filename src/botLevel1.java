import java.util.Random;
import java.util.List;
import java.util.Scanner;
public class botLevel1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the game
        Board board = new Board();
        Random random = new Random();
        MoveGenerator moveGenerator = new MoveGenerator();

        int randInt = random.nextInt(2);
        Colour botColour;
        Colour playerColour;

        if (randInt == 0) {
            System.out.println("Bot plays as White");
            botColour = Colour.WHITE;
            playerColour = Colour.BLACK;
            Move move = chooseMove(board, botColour, moveGenerator);
            System.out.println("Bot plays: " + chessUtil.coordinatesToPosition(move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol()));
            board.makeMove(move);
            
        } else {
            System.out.println("Bot plays as Black");
            botColour = Colour.BLACK;
            playerColour = Colour.WHITE;
        }

        while (true) {
            // Player's turn
            Move playerMove = getPlayerMove(board, playerColour);
            if (!MoveGenerator.isLegal(board, playerMove)) {
                System.out.println("Illegal move. Please try again.");
                continue;
            }
            board.makeMove(playerMove);

            // Bot's turn
            Move botMove = chooseMove(board, botColour, moveGenerator);
            if (botMove == null) {
                System.out.println("Bot has no legal moves. Game over.");
                break;
            }
            System.out.println("Bot plays: " + chessUtil.coordinatesToPosition(botMove.getFromRow(), botMove.getFromCol(), botMove.getToRow(), botMove.getToCol()));
            board.makeMove(botMove);
        }




    }
    public static Move chooseMove(Board board, Colour botColour, MoveGenerator moveGenerator) {
        Random random = new Random();

        List<Move> possibleMoves = moveGenerator.generateLegalMoves(board, botColour);
        if (possibleMoves.isEmpty()) {
            return null;
        }
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
        
    }
    public static Move getPlayerMove(Board board, Colour playerColour) {
        while (true){
            System.out.println("Enter move (e.g., e2e4): ");
            String moveStr = scanner.nextLine();
            if (moveStr.length() != 4) {
                System.out.println("Invalid move format. Please try again.");
                continue;
            }
            String currentPos = moveStr.substring(0, 2);
            String newPos = moveStr.substring(2, 4);
            int[] coords = chessUtil.positionToCoordinates(currentPos + newPos);
            Move move = new Move(coords[0], coords[1], coords[2], coords[3]);
            if (!MoveGenerator.isLegal(board, move)) {
                System.out.println("Illegal move. Please try again.");
                continue;
            }
            return new Move(coords[0], coords[1], coords[2], coords[3]);
        }
        
    }
}