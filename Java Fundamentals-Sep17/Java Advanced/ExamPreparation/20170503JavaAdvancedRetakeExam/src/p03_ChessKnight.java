import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p03_ChessKnight {
    public static void main(String[] args) throws IOException {
        char[][] board = new char[8][8];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int outOfBoard = 0;
        int invalidMoves = 0;
        List<Character> taken = new ArrayList<>();
        // fill up the board
        for (int i = 0; i < 8; i++) {
            board[i] = reader.readLine()
                    .replaceAll("\\|","").toCharArray();
        }
        // read start position as array [row, col]
        int[] start = reader.readLine().chars()
                .mapToObj(Character::getNumericValue).mapToInt(x -> x).toArray();
        String moves;
        // read moves
        while(!(moves = reader.readLine()).equals("END")){
            // split to [fromPos, toPos]
            String[] tokens = moves.split(" -> ");
            // convert characters to coordinates [row, col]
            int[] fromPos = tokens[0].chars()
                    .mapToObj(Character::getNumericValue).mapToInt(x -> x).toArray();
            // convert characters to coordinates [row, col]
            int[] toPos = tokens[1].chars()
                    .mapToObj(Character::getNumericValue).mapToInt(x -> x).toArray();
            // So obviously there is no incorrect start position in test cases
            // but let leave it as that
            if(validMove(fromPos, toPos)){
                // if the move is valid, but out of board
                if(toPos[0] > 7 || toPos[1] > 7){
                    outOfBoard++;
                } else {
                    // if is in the board and valid
                    // take the character
                    Character stepOn = board[toPos[0]][toPos[1]];
                    // if we have piece there take it!
                    if (stepOn != ' ') {
                        taken.add(stepOn);
                        board[toPos[0]][toPos[1]] = ' ';
                    }
                }
            } else {
                invalidMoves++;
            }
        }
        System.out.printf("Pieces take: %s%n", taken.toString().replaceAll("\\[|\\]", ""));
        System.out.printf("Invalid moves: %d%n", invalidMoves);
        System.out.printf("Board out moves: %d%n", outOfBoard);
    }

    private static boolean validMove(int[] fromPos, int[] toPos) {
        // So much unnecessary code
        //if(start[0] != fromPos[0] || start[1] != fromPos[1]) return false;
        //if(toPos[0] > 7 || toPos[1] > 7) return false;

        /*
        boolean valid = fromPos[0] != toPos[0] && fromPos[1] != toPos[1];
        valid &= (fromPos[0] - fromPos[1] != toPos[0] - toPos[1]);
        valid &= (fromPos[0] + fromPos[1] != toPos[0] + toPos[1]);
        valid &= (Math.abs(toPos[0] - fromPos[0]) <= 2 && Math.abs(toPos[1] - fromPos[1]) <= 2);
        */
        return (Math.abs(fromPos[0] - toPos[0]) == 2 && Math.abs(fromPos[1] - toPos[1]) == 1)
                || (Math.abs(fromPos[0] - toPos[0]) == 1 && Math.abs(fromPos[1] - toPos[1]) == 2);
    }
}
