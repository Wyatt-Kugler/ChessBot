public class Board {
    public Piece[][] board = new Piece[8][8];

    public Board() {
        setupBackRank(0, Colour.BLACK);
        setupPawns(1, Colour.BLACK);
        setupPawns(6, Colour.WHITE);
        setupBackRank(7, Colour.WHITE);
    }

    private void setupBackRank(int row, Colour colour) {
        PieceType[] backRank = {
            PieceType.ROOK,
            PieceType.KNIGHT,
            PieceType.BISHOP,
            PieceType.QUEEN,
            PieceType.KING,
            PieceType.BISHOP,
            PieceType.KNIGHT,
            PieceType.ROOK
        };

        for (int column = 0; column < backRank.length; column++) {
            board[row][column] = new Piece(colour, backRank[column]);
        }
    }

    private void setupPawns(int row, Colour colour) {
        for (int column = 0; column < board[row].length; column++) {
            board[row][column] = new Piece(colour, PieceType.PAWN);
        }
    }
    public void makeMove(Move move){
        board[move.getToRow()][move.getToCol()] = board[move.getFromRow()][move.getFromCol()];
    }

    // check if position is within chess board
    public boolean isInside(int row, int col) {
        return row >= 0 && row < 8 &&
        col >= 0 && col < 8;
    }

    //Returns True if piece is present at specified spot
    private boolean hasPiece(int row, int col){
        if (board[row][col] == null){
            return false;
        }
        return true;
    }

    // Returns piece at position, else null
    public Piece getPiece(int row, int col){
        if (!isInside(row, col)){
            throw new IllegalArgumentException("Position outside board!");
        }
        if (!hasPiece(row,col)){
            return null;
        }
        return board[row][col];

    }
}
