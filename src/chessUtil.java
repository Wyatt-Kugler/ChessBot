public class chessUtil {
    public static int[] positionToCoordinates(String position) {
        if (position.length() != 4) {
            throw new IllegalArgumentException("Invalid position format");
        }

        char fromrow = position.charAt(0);
        char fromcol = position.charAt(1);
        char torow = position.charAt(2);
        char tocol = position.charAt(3);

        if (fromrow < 'a' || fromrow > 'h' || fromcol < '1' || fromcol > '8') {
            throw new IllegalArgumentException("Position out of bounds");
        }

        int startRow = 8 - (fromcol - '0'); // Convert '1'-'8' to 7-0
        int startCol = fromrow - 'a'; // Convert 'a'-'h'
        int endRow = 8 - (tocol - '0'); // Convert '1'-'8' to 7-0
        int endCol = torow - 'a'; // Convert 'a'-'h'

        return new int[]{startRow, startCol, endRow, endCol};
    }
}
