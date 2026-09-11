public class board {
    public piece[][] board = new piece[8][8];

    public board() {
        setupBackRank(0, colour.BLACK);
        setupPawns(1, colour.BLACK);
        setupPawns(6, colour.WHITE);
        setupBackRank(7, colour.WHITE);
    }

    private void setupBackRank(int row, colour colour) {
        piecetype[] backRank = {
            piecetype.ROOK,
            piecetype.KNIGHT,
            piecetype.BISHOP,
            piecetype.QUEEN,
            piecetype.KING,
            piecetype.BISHOP,
            piecetype.KNIGHT,
            piecetype.ROOK
        };

        for (int column = 0; column < backRank.length; column++) {
            board[row][column] = new piece(colour, backRank[column]);
        }
    }

    private void setupPawns(int row, colour colour) {
        for (int column = 0; column < board[row].length; column++) {
            board[row][column] = new piece(colour, piecetype.PAWN);
        }
    }
    public void makeMove(move move){
        board[move.getToRow()][move.getToCol()] = board[move.getFromRow()][move.getFromCol()];
    }
}
