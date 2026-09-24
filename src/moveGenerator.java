import java.util.List;
import java.util.ArrayList;

public class MoveGenerator {
    generatePsuedoLegalMoves(Board board){

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
    }



