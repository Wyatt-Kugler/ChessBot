import java.util.Random;
import java.util.List;
import java.util.Scanner;
public class botLevel1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the game
        Board board = new Board();
        Random random = new Random();
        

        int randInt = random.nextInt(2);
        Colour botColour;
        Colour playerColour;

        if (randInt == 0) {
            System.out.println("Bot plays as White");
            botColour = Colour.WHITE;
            playerColour = Colour.BLACK;
            Move move = chooseMove(board, botColour);
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
            
            if (isPlayerPromotingPawn(board, playerMove, playerColour)) {
                System.out.println("You are promoting a pawn! Please choose a piece to promote to (Q, R, B, N): ");
                String promotionChoice = scanner.nextLine().toUpperCase();
                PieceType promotionType;
                switch (promotionChoice) {
                    case "Q":
                        promotionType = PieceType.QUEEN;
                        break;
                    case "R":
                        promotionType = PieceType.ROOK;
                        break;
                    case "B":
                        promotionType = PieceType.BISHOP;
                        break;
                    case "N":
                        promotionType = PieceType.KNIGHT;
                        break;
                    default:
                        System.out.println("Invalid choice. Defaulting to Queen.");
                        promotionType = PieceType.QUEEN;
                }
                playerMove = new Move(playerMove.getFromRow(), playerMove.getFromCol(), playerMove.getToRow(), playerMove.getToCol(), promotionType);
            }
            board.makeMove(playerMove);

            // Bot's turn
            Move botMove = chooseMove(board, botColour);
            if (botMove == null) {
                System.out.println("Bot has no legal moves. Game over.");
                break;
            }
            System.out.println("Bot plays: " + chessUtil.coordinatesToPosition(botMove.getFromRow(), botMove.getFromCol(), botMove.getToRow(), botMove.getToCol()));
            board.makeMove(botMove);
        }




    }
    public static Move chooseMove(Board board, Colour botColour) {
        Random random = new Random();

        List<Move> possibleMoves = MoveGenerator.generateLegalMoves(board, botColour);
        if (possibleMoves.isEmpty()) {
            return null;
        }
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
        
    }

    private static boolean isPlayerPromotingPawn(Board board, Move move, Colour playerColour) {
        Piece piece = board.getPiece(move.getFromRow(), move.getFromCol());
        if (piece == null || piece.getPieceType() != PieceType.PAWN || piece.getColour() != playerColour) {
            return false;
        }
        if (playerColour == Colour.WHITE && move.getToRow() == 0) {
            return true;
        }
        if (playerColour == Colour.BLACK && move.getToRow() == 7) {
            return true;
        }
        return false;
    }
    public static Move getPlayerMove(Board board, Colour playerColour) {
        while (true){
            System.out.println("Enter your move in the format 'e2e4' (from position to position): ");
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
            if (!MoveGenerator.isPlayerMoveLegal(board, move, playerColour)) {
                System.out.println("Illegal move. Please try again.");
                continue;
            }
            return new Move(coords[0], coords[1], coords[2], coords[3]);
        }
        
    }
}