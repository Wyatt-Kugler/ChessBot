import java.util.List;

/** Standalone regression checks for pawn promotion. Run with: java PromotionTest */
public class PromotionTest {
    private static final PieceType[] PROMOTION_TYPES = {
        PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT
    };

    public static void main(String[] args) {
        testWhitePromotionMovesAndApplication();
        testBlackPromotionMovesAndApplication();
        testPromotionCaptureMoves();
        testPromotionMoveIsMatchedByChoice();
        System.out.println("Promotion tests passed (4 checks).");
    }

    private static void testWhitePromotionMovesAndApplication() {
        Board board = boardWithKings();
        board.board[1][0] = new Piece(Colour.WHITE, PieceType.PAWN);

        List<Move> moves = MoveGenerator.generatePawnMoves(board, 1, 0);
        assertPromotionOptions(moves, 1, 0, 0, 0);
        for (PieceType type : PROMOTION_TYPES) {
            board.makeMove(new Move(1, 0, 0, 0, type));
            assertPiece(board, 0, 0, Colour.WHITE, type);
            board.board[0][0] = null;
            board.board[1][0] = new Piece(Colour.WHITE, PieceType.PAWN);
        }
    }

    private static void testBlackPromotionMovesAndApplication() {
        Board board = boardWithKings();
        board.board[6][7] = new Piece(Colour.BLACK, PieceType.PAWN);

        List<Move> moves = MoveGenerator.generatePawnMoves(board, 6, 7);
        assertPromotionOptions(moves, 6, 7, 7, 7);
        for (PieceType type : PROMOTION_TYPES) {
            board.makeMove(new Move(6, 7, 7, 7, type));
            assertPiece(board, 7, 7, Colour.BLACK, type);
            board.board[7][7] = null;
            board.board[6][7] = new Piece(Colour.BLACK, PieceType.PAWN);
        }
    }

    private static void testPromotionCaptureMoves() {
        Board board = boardWithKings();
        board.board[1][3] = new Piece(Colour.WHITE, PieceType.PAWN);
        board.board[0][4] = new Piece(Colour.BLACK, PieceType.ROOK);

        List<Move> moves = MoveGenerator.generatePawnMoves(board, 1, 3);
        assertPromotionOptions(moves, 1, 3, 0, 4);
    }

    private static void testPromotionMoveIsMatchedByChoice() {
        Board board = boardWithKings();
        board.board[1][0] = new Piece(Colour.WHITE, PieceType.PAWN);
        Move rookPromotion = new Move(1, 0, 0, 0, PieceType.ROOK);
        Move queenPromotion = new Move(1, 0, 0, 0, PieceType.QUEEN);

        check(MoveGenerator.isPlayerMoveLegal(board, rookPromotion, Colour.WHITE),
                "rook promotion should be recognized as legal");
        check(!MoveGenerator.isPlayerMoveLegal(board, queenPromotion, Colour.BLACK),
                "opponent should not be allowed to move the pawn");
    }

    private static Board boardWithKings() {
        Board board = new Board();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.board[row][col] = null;
            }
        }
        board.board[7][4] = new Piece(Colour.WHITE, PieceType.KING);
        board.board[0][4] = new Piece(Colour.BLACK, PieceType.KING);
        return board;
    }

    private static void assertPromotionOptions(List<Move> moves, int fromRow,
            int fromCol, int toRow, int toCol) {
        for (PieceType type : PROMOTION_TYPES) {
            boolean found = false;
            for (Move move : moves) {
                if (move.getFromRow() == fromRow && move.getFromCol() == fromCol
                        && move.getToRow() == toRow && move.getToCol() == toCol
                        && move.getPromotion() == type) {
                    found = true;
                    break;
                }
            }
            check(found, "missing promotion option " + type);
        }
        int count = 0;
        for (Move move : moves) {
            if (move.getFromRow() == fromRow && move.getFromCol() == fromCol
                    && move.getToRow() == toRow && move.getToCol() == toCol) {
                count++;
            }
        }
        check(count == 4, "expected four promotion moves, found " + count);
    }

    private static void assertPiece(Board board, int row, int col,
            Colour colour, PieceType type) {
        Piece piece = board.getPiece(row, col);
        check(piece != null && piece.getColour() == colour
                && piece.getPieceType() == type,
                "expected " + colour + " " + type + " on " + row + "," + col);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
