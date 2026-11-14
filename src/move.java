public class Move {
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private PieceType promotion;
    public Move(int fromRow, int fromCol, int toRow, int toCol){
        this(fromRow, fromCol, toRow, toCol, null);
    }
    public Move(int fromRow, int fromCol, int toRow, int toCol, PieceType promotion){
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        if (promotion == PieceType.PAWN || promotion == PieceType.KING) {
            throw new IllegalArgumentException("A pawn can only promote to a queen, rook, bishop, or knight");
        }
        this.promotion = promotion;
    }
    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }
    public PieceType getPromotion() { return promotion; }
    @Override 
    public String toString() {
        String moveString = chessUtil.coordinatesToPosition(fromRow, fromCol, toRow, toCol);
        return moveString;
    }
    
    public boolean equals(Object obj, Board board) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Move other = (Move) obj;
        Piece thisPiece = board.getPiece(fromRow, fromCol);
        Piece otherPiece = board.getPiece(other.fromRow, other.fromCol);
        
        if (thisPiece == null || otherPiece == null) {
            return false;
        }
        if (thisPiece.getPieceType() != otherPiece.getPieceType()) {
            return false;
        }
        
        return fromRow == other.fromRow && fromCol == other.fromCol &&
               toRow == other.toRow && toCol == other.toCol && promotion == other.promotion;
    }

    
}
