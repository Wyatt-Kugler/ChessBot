import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class MoveGenerator {
    public static List<Move> generatePseudoLegalMoves(Board board, Colour colour) {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row<8; row++){
            for (int col = 0; col<8; col++){
                Piece piece = board.getPiece(row, col);
                if (piece == null || piece.getColour() != colour) {
                    continue;
                }

                moves.addAll(generateMovesForPiece(board, row, col, colour));
            }
        }
        return moves;
    }

    @Deprecated
    public List<Move> generatePsuedoLegalMoves(Board board, Colour colour) {
        return generatePseudoLegalMoves(board, colour);
    }

    private static List<Move> generateMovesForPiece(Board board, int row, int col,
            Colour colour) {
        List<Move> moves = new ArrayList<>();
        Piece piece = board.getPiece(row, col);
        if (piece == null || piece.getColour() != colour) {
            return moves;
        }

        switch (piece.getPieceType()) {
            case PAWN:
                moves.addAll(generatePawnMoves(board, row, col));
                break;
            case KNIGHT:
                moves.addAll(generateKnightMoves(board, row, col));
                break;
            case BISHOP:
                moves.addAll(generateBishopMoves(board, row, col));
                break;
            case ROOK:
                moves.addAll(generateRookMoves(board, row, col));
                break;
            case QUEEN:
                moves.addAll(generateQueenMoves(board, row, col));
                break;
            case KING:
                moves.addAll(generateKingMoves(board, row, col));
                break;
            default:
                break;
        }
        return moves;
    }

    public static List<Move> generateKnightMoves(Board board, int row, int col) {

        List<Move> moves = new ArrayList<Move>();
        int[][] knightMoves = {
            {-2, -1},
            {-2,  1},
            {-1, -2},
            {-1,  2},
            { 1, -2},
            { 1,  2},
            { 2, -1},
            { 2,  1}
        };

        for (int i = 0; i < knightMoves.length; i++){
            int newRow = row + knightMoves[i][0];
            int newCol = col + knightMoves[i][1];

            if (!board.isInside(newRow,newCol)){
                continue;
            }

            Piece targetPiece = board.getPiece(newRow,newCol);
            Piece thisPiece = board.getPiece(row,col);
            
            if(targetPiece == null){
                moves.add(new Move(row,col,newRow,newCol));
                continue;
            }

            if (thisPiece.getColour() == targetPiece.getColour()){
                continue;
            }

            moves.add(new Move(row,col,newRow,newCol));

        }
        return moves;

    }
    public static List<Move> generateBishopMoves(Board board, int row, int col){
        List<Move> moves = new ArrayList<Move>();
        int[][] directions = {
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1}
    };
        for (int[] direction : directions) {
            int increment = 0;
            while (true) {
                increment++;
                int tempRow = row + direction[0] * increment;
                int tempCol = col + direction[1] * increment;

                if (!board.isInside(tempRow, tempCol)) {
                    break;
                }

                Piece targetPiece = board.getPiece(tempRow, tempCol);

                if (targetPiece == null) {
                    moves.add(new Move(row, col, tempRow, tempCol));
                }
                else if (targetPiece.getColour() == board.getPiece(row, col).getColour()) {
                    break;
                }
                else {
                    moves.add(new Move(row, col, tempRow, tempCol));
                    break;
                }
            }
        }
            return moves;
    }
    public static List<Move> generateRookMoves(Board board, int row, int col){
        List<Move> moves = new ArrayList<Move>();
        int[][] directions = {
        {1, 0},
        {-1,0},
        {0, 1},
        {0, -1}
    };
        for (int[] direction : directions) {
            int increment = 0;
            while (true) {
                increment++;
                int tempRow = row + direction[0] * increment;
                int tempCol = col + direction[1] * increment;

                if (!board.isInside(tempRow, tempCol)) {
                    break;
                }

                Piece targetPiece = board.getPiece(tempRow, tempCol);

                if (targetPiece == null) {
                    moves.add(new Move(row, col, tempRow, tempCol));
                }
                else if (targetPiece.getColour() == board.getPiece(row, col).getColour()) {
                    break;
                }
                else {
                    moves.add(new Move(row, col, tempRow, tempCol));
                    break;
                }
            }
        }
            return moves;
    }

    public static List<Move> generateQueenMoves(Board board, int row, int col){

        List<Move> moves = new ArrayList<Move>();
        int[][] directions = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
            {1, 0},
            {-1,0},
            {0, 1},
            {0, -1}
        };

        for (int[] direction : directions) {
            int increment = 0;
            while (true) {
                increment++;
                int tempRow = row + direction[0] * increment;
                int tempCol = col + direction[1] * increment;

                if (!board.isInside(tempRow, tempCol)) {
                    break;
                }

                Piece targetPiece = board.getPiece(tempRow, tempCol);

                if (targetPiece == null) {
                    moves.add(new Move(row, col, tempRow, tempCol));
                }
                else if (targetPiece.getColour() == board.getPiece(row, col).getColour()) {
                    break;
                }
                else {
                    moves.add(new Move(row, col, tempRow, tempCol));
                    break;
                }
            }
        }
            return moves;

    }

    public static List<Move> generateKingMoves(Board board, int row, int col){
        List<Move> moves = new ArrayList<Move>();

        int[][] directions = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
            {1, 0},
            {-1,0},
            {0, 1},
            {0, -1}
        };

        for (int[] direction: directions){
            int tempRow = row+direction[0];
            int tempCol = col+direction[1];

            if (!board.isInside(tempRow, tempCol)) {
                    continue;
                }

                Piece targetPiece = board.getPiece(tempRow, tempCol);

                if (targetPiece == null) {
                    moves.add(new Move(row, col, tempRow, tempCol));
                }
                else if (targetPiece.getColour() == board.getPiece(row, col).getColour()) {
                    continue;
                }
                else {
                    moves.add(new Move(row, col, tempRow, tempCol));
                    continue;
                }
            }
        
        return moves;
        }
    public static List<Move> generatePawnMoves(Board board, int row, int col) {
        List<Move> moves = new ArrayList<Move>();
        Piece pawn = board.getPiece(row, col);
        if (pawn == null || pawn.getPieceType() != PieceType.PAWN) {
            return moves;
        }

        Colour colour = pawn.getColour();
        int direction = colour == Colour.WHITE ? -1 : 1;
        int startingRow = colour == Colour.WHITE ? 6 : 1;
        int nextRow = row + direction;

        if (board.isInside(nextRow, col) && board.getPiece(nextRow, col) == null) {
            addPawnMove(moves, row, col, nextRow, col, colour);
            int doubleRow = row + 2 * direction;
            if (row == startingRow && board.getPiece(doubleRow, col) == null) {
                moves.add(new Move(row, col, doubleRow, col));
            }
        }

        for (int colDelta : new int[] {-1, 1}) {
            int targetCol = col + colDelta;
            if (!board.isInside(nextRow, targetCol)) {
                continue;
            }
            Piece target = board.getPiece(nextRow, targetCol);
            if (target != null && target.getColour() != colour) {
                addPawnMove(moves, row, col, nextRow, targetCol, colour);
            } else if (target == null && canCaptureEnPassant(board, pawn, row, targetCol)) {
                moves.add(new Move(row, col, nextRow, targetCol));
            }
        }
        return moves;
    }

    private static void addPawnMove(List<Move> moves, int fromRow, int fromCol,
            int toRow, int toCol, Colour colour) {
        int lastRank = colour == Colour.WHITE ? 0 : 7;
        if (toRow == lastRank) {
            moves.add(new Move(fromRow, fromCol, toRow, toCol, PieceType.QUEEN));
            moves.add(new Move(fromRow, fromCol, toRow, toCol, PieceType.ROOK));
            moves.add(new Move(fromRow, fromCol, toRow, toCol, PieceType.BISHOP));
            moves.add(new Move(fromRow, fromCol, toRow, toCol, PieceType.KNIGHT));
        } else {
            moves.add(new Move(fromRow, fromCol, toRow, toCol));
        }
    }

    private static boolean canCaptureEnPassant(Board board, Piece pawn, int row, int adjacentCol) {
        Move lastMove = board.getLastMove();
        if (lastMove == null || lastMove.getToRow() != row || lastMove.getToCol() != adjacentCol
                || lastMove.getFromCol() != adjacentCol
                || Math.abs(lastMove.getFromRow() - lastMove.getToRow()) != 2) {
            return false;
        }
        Piece adjacentPawn = board.getPiece(row, adjacentCol);
        return adjacentPawn != null
                && adjacentPawn.getPieceType() == PieceType.PAWN
                && adjacentPawn.getColour() != pawn.getColour();
    }

    // Determines if Psuedo Legal move is fully legal
    // Ie Doesn't leave king in check.
    public static boolean isLegal(Board board, Move move){
        if (board == null || move == null
                || !board.isInside(move.getFromRow(), move.getFromCol())
                || !board.isInside(move.getToRow(), move.getToCol())) {
            return false;
        }

        Piece movingPiece = board.getPiece(move.getFromRow(), move.getFromCol());
        if (movingPiece == null) {
            return false;
        }

        if (!isPseudoLegalMove(board, move, movingPiece)) {
            return false;
        }

        Piece destinationPiece = board.getPiece(move.getToRow(), move.getToCol());
        if (destinationPiece != null
                && destinationPiece.getColour() == movingPiece.getColour()) {
            return false;
        }
        if (destinationPiece != null && destinationPiece.getPieceType() == PieceType.KING) {
            return false;
        }

        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();
        int capturedRow = toRow;
        int capturedCol = toCol;

        // En passant captures a pawn on the source rank, not the destination square.
        if (movingPiece.getPieceType() == PieceType.PAWN
                && fromCol != toCol && destinationPiece == null
                && canCaptureEnPassant(board, movingPiece, fromRow, toCol)) {
            capturedRow = fromRow;
            capturedCol = toCol;
        }

        Piece capturedPiece = board.getPiece(capturedRow, capturedCol);
        Move previousLastMove = board.lastMove;

        try {
            board.board[fromRow][fromCol] = null;
            board.board[capturedRow][capturedCol] = null;
            board.board[toRow][toCol] = movingPiece;
            board.lastMove = move;

            int[] kingPosition = movingPiece.getColour() == Colour.WHITE
                    ? board.findWhiteKing() : board.findBlackKing();
            if (kingPosition == null) {
                return false;
            }

            return !isSquareAttacked(board, kingPosition[0], kingPosition[1],
                    oppositeColour(movingPiece.getColour()));
        } finally {
            board.board[fromRow][fromCol] = movingPiece;
            board.board[toRow][toCol] = destinationPiece;
            if (capturedRow != toRow || capturedCol != toCol) {
                board.board[capturedRow][capturedCol] = capturedPiece;
            }
            board.lastMove = previousLastMove;
        }
    }
    public static boolean isPlayerMoveLegal(Board board, Move move, Colour playerColour) {
        if (board == null || move == null
                || !board.isInside(move.getFromRow(), move.getFromCol())
                || !board.isInside(move.getToRow(), move.getToCol())) {
            return false;
        }

        Piece movingPiece = board.getPiece(move.getFromRow(), move.getFromCol());
        if (movingPiece == null || movingPiece.getColour() != playerColour) {
            return false;
        }

        Piece destinationPiece = board.getPiece(move.getToRow(), move.getToCol());
        if (destinationPiece != null
                && destinationPiece.getColour() == movingPiece.getColour()) {
            return false;
        }
        if (destinationPiece != null && destinationPiece.getPieceType() == PieceType.KING) {
            return false;
        }
        if (!isPseudoLegalMove(board, move, movingPiece)) {
            return false;
        }

        return isLegal(board, move);
    }

    private static boolean isPseudoLegalMove(Board board, Move move, Piece movingPiece) {
        List<Move> pieceMoves;
        switch (movingPiece.getPieceType()) {
            case PAWN:
                pieceMoves = generatePawnMoves(board, move.getFromRow(), move.getFromCol());
                break;
            case KNIGHT:
                pieceMoves = generateKnightMoves(board, move.getFromRow(), move.getFromCol());
                break;
            case BISHOP:
                pieceMoves = generateBishopMoves(board, move.getFromRow(), move.getFromCol());
                break;
            case ROOK:
                pieceMoves = generateRookMoves(board, move.getFromRow(), move.getFromCol());
                break;
            case QUEEN:
                pieceMoves = generateQueenMoves(board, move.getFromRow(), move.getFromCol());
                break;
            case KING:
                pieceMoves = generateKingMoves(board, move.getFromRow(), move.getFromCol());
                break;
            default:
                return false;
        }

        for (Move candidate : pieceMoves) {
            if (candidate.getFromRow() == move.getFromRow()
                    && candidate.getFromCol() == move.getFromCol()
                    && candidate.getToRow() == move.getToRow()
                    && candidate.getToCol() == move.getToCol()
                    && candidate.getPromotion() == move.getPromotion()) {
                return true;
            }
        }
        return false;
    }

    private static Colour oppositeColour(Colour colour) {
        return colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }

    private static boolean isSquareAttacked(Board board, int row, int col, Colour attackingColour) {
        for (int sourceRow = 0; sourceRow < 8; sourceRow++) {
            for (int sourceCol = 0; sourceCol < 8; sourceCol++) {
                Piece piece = board.getPiece(sourceRow, sourceCol);
                if (piece == null || piece.getColour() != attackingColour) {
                    continue;
                }

                int rowDelta = row - sourceRow;
                int colDelta = col - sourceCol;
                int absRowDelta = Math.abs(rowDelta);
                int absColDelta = Math.abs(colDelta);

                switch (piece.getPieceType()) {
                    case PAWN:
                        int pawnDirection = attackingColour == Colour.WHITE ? -1 : 1;
                        if (rowDelta == pawnDirection && absColDelta == 1) {
                            return true;
                        }
                        break;
                    case KNIGHT:
                        if ((absRowDelta == 2 && absColDelta == 1)
                                || (absRowDelta == 1 && absColDelta == 2)) {
                            return true;
                        }
                        break;
                    case KING:
                        if (absRowDelta <= 1 && absColDelta <= 1
                                && (absRowDelta != 0 || absColDelta != 0)) {
                            return true;
                        }
                        break;
                    case BISHOP:
                        if (absRowDelta == absColDelta
                                && isClearPath(board, sourceRow, sourceCol, row, col)) {
                            return true;
                        }
                        break;
                    case ROOK:
                        if ((rowDelta == 0 || colDelta == 0)
                                && isClearPath(board, sourceRow, sourceCol, row, col)) {
                            return true;
                        }
                        break;
                    case QUEEN:
                        if ((absRowDelta == absColDelta || rowDelta == 0 || colDelta == 0)
                                && isClearPath(board, sourceRow, sourceCol, row, col)) {
                            return true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    private static boolean isClearPath(Board board, int fromRow, int fromCol,
            int toRow, int toCol) {
        int rowStep = Integer.compare(toRow, fromRow);
        int colStep = Integer.compare(toCol, fromCol);
        int row = fromRow + rowStep;
        int col = fromCol + colStep;

        while (row != toRow || col != toCol) {
            if (board.getPiece(row, col) != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    public static List<Move> generateLegalMoves(Board board, Colour colour) {
        List<Move> pseudoLegalMoves = generatePseudoLegalMoves(board, colour);
        List<Move> legalMoves = new ArrayList<>();

        for (Move move : pseudoLegalMoves) {
            if (isLegal(board, move)) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }
    public static void testIsPlayerMoveLegal(){
        Board board = new Board();
        List<Move> legalMoves = generateLegalMoves(board, Colour.WHITE);
        for (Move move : legalMoves) {
            if (!isPlayerMoveLegal(board, move, Colour.WHITE)) {
                System.out.println("Error: Legal move not recognized as legal: " + move);
            }
        }
}
    public static void main(String[] args) {
        testIsPlayerMoveLegal();
    }
}


