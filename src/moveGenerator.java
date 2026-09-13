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
}
