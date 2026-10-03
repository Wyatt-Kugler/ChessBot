import java.util.List;
import java.util.ArrayList;

public class MoveGenerator {
    public List<Move> generatePsuedoLegalMoves(Board board){
        return new ArrayList<Move>();
    }

    public List<Move> generateKnightMoves(Board board, int row, int col) {

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
    public List<Move> generateBishopMoves(Board board, int row, int col){
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
    public List<Move> generateRookMoves(Board board, int row, int col){
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

    public List<Move> generateQueenMoves(Board board, int row, int col){

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

    public List<Move> generateKingMoves(Board board, int row, int col){
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
    public List<Move> generatePawnMoves(Board board, int row, int col) {
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
            moves.add(new Move(row, col, nextRow, col));
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
                moves.add(new Move(row, col, nextRow, targetCol));
            } else if (target == null && canCaptureEnPassant(board, pawn, row, targetCol)) {
                moves.add(new Move(row, col, nextRow, targetCol));
            }
        }
        return moves;
    }

    private boolean canCaptureEnPassant(Board board, Piece pawn, int row, int adjacentCol) {
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
}

