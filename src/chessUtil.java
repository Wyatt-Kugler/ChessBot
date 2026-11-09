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
    public static String coordinatesToPosition(int startRow, int startCol, int endRow, int endCol) {
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 ||
            endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        char fromrow = (char) ('a' + startCol);
        char fromcol = (char) ('8' - startRow);
        char torow = (char) ('a' + endCol);
        char tocol = (char) ('8' - endRow);

        return "" + fromrow + fromcol + torow + tocol;
    }
}
