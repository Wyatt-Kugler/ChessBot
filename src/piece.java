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
}
