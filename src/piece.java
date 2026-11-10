public class Piece {
    Colour colour;
    PieceType type;

    public Piece(Colour colour, PieceType type){
        this.colour = colour;
        this.type = type;
    }
    public Colour getColour(){
        return colour;
    }
    public PieceType getPieceType(){
        return type;
    }
    public char getPieceChar() {
        char pieceChar;
        switch (type) {
            case PAWN:
                pieceChar = 'P';
                break;
            case ROOK:
                pieceChar = 'R';
                break;
            case KNIGHT:
                pieceChar = 'N';
                break;
            case BISHOP:
                pieceChar = 'B';
                break;
            case QUEEN:
                pieceChar = 'Q';
                break;
            case KING:
                pieceChar = 'K';
                break;
            default:
                throw new IllegalArgumentException("Invalid piece type: " + type);
        }
        return pieceChar;
    }
}
