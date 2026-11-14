public class Board {
    public Piece[][] board = new Piece[8][8];
    Move lastMove;

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
        Piece movingPiece = board[move.getFromRow()][move.getFromCol()];
        Piece destinationPiece = board[move.getToRow()][move.getToCol()];
        if (move.getPromotion() != null
                && (movingPiece == null || movingPiece.getPieceType() != PieceType.PAWN
                || move.getToRow() != (movingPiece.getColour() == Colour.WHITE ? 0 : 7))) {
            throw new IllegalArgumentException("Promotion must be a pawn move to the last rank");
        }

        // En passant captures the opposing pawn beside the moving pawn.
        if (movingPiece != null && movingPiece.getPieceType() == PieceType.PAWN
                && move.getFromCol() != move.getToCol() && destinationPiece == null
                && lastMove != null
                && lastMove.getToRow() == move.getFromRow()
                && lastMove.getToCol() == move.getToCol()
                && lastMove.getFromCol() == move.getToCol()
                && Math.abs(lastMove.getFromRow() - lastMove.getToRow()) == 2) {
            Piece adjacentPiece = board[move.getFromRow()][move.getToCol()];
            if (adjacentPiece != null && adjacentPiece.getPieceType() == PieceType.PAWN
                    && adjacentPiece.getColour() != movingPiece.getColour()) {
                board[move.getFromRow()][move.getToCol()] = null;
            }
        }

        board[move.getToRow()][move.getToCol()] = movingPiece;
        board[move.getFromRow()][move.getFromCol()] = null;
        if (move.getPromotion() != null) {
            board[move.getToRow()][move.getToCol()] =
                    new Piece(movingPiece.getColour(), move.getPromotion());
        }
        lastMove = move;
    }

    public void makeTempMove(Move move){
        board[move.getToRow()][move.getToCol()] = board[move.getFromRow()][move.getFromCol()];
        lastMove = move;
    }

    public Move getLastMove(){
        return lastMove;
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
    public int[] findWhiteKing(){
        for (int i=0; i<8;i++){
            for(int j = 0; j<8; j++){
                Piece piece = getPiece(i, j);
                if(piece != null && piece.getPieceType() == PieceType.KING
                        && piece.getColour() == Colour.WHITE)
                    return new int[]{i, j};
            }
        }
        return null;
    }
    public int[] findBlackKing(){
        for (int i=0; i<8;i++){
            for(int j = 0; j<8; j++){
                Piece piece = getPiece(i, j);
                if(piece != null && piece.getPieceType() == PieceType.KING
                        && piece.getColour() == Colour.BLACK)
                    return new int[]{i, j};
            }
        }
        return null;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece == null) {
                    sb.append(".");
                } else {
                    char pieceChar = piece.getPieceChar();
                    sb.append(pieceChar);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
