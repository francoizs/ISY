package othello;

import gameFramework.Player;

import java.util.ArrayList;

public class AiOthello extends Player {

    private final int Max_depth = 4;

    public AiOthello(int playernumber, char piece) {
        super(playernumber, piece, "AI");
    }

    public int AiMove(Othello AiBoard, char piece, int number) {
        switch (number) {
            case 1:
                AiWithoutPruning aiwithoutpruning = new AiWithoutPruning(getPlayernumber(), piece);
                return aiwithoutpruning.moveselectOthello(AiBoard, piece);
            case 2:
                GenerousAi generousai = new GenerousAi(getPlayernumber(), piece);
                return generousai.moveselectOthello(AiBoard, piece);
            case 3:
                GreedyAi greedyai = new GreedyAi(getPlayernumber(), piece);
                return greedyai.moveselectOthello(AiBoard, piece);
            case 4:
                TilePointsAi tilepointsai = new TilePointsAi(getPlayernumber(), piece);
                return tilepointsai.moveselectOthello(AiBoard, piece);
        }
        return 0;
    }

    public int getMax_depth() {
        return this.Max_depth;
    }

    /**
     * Adds a move to the board
     * @author Mart de Vries
     * @param AIBoard
     * @param position
     * @param piece
     */
    public void AiAdd(Othello AIBoard, int position, char piece) {
        AIBoard.add(piece, position);
        AIBoard.flipPiece(position, piece);
    }

    /**
     * Flips all the discs that have previously been flipped
     * @author Mart de Vries
     * @param flipped
     * @param piece
     * @param AIBoard
     */
    public void ReverseFlip(ArrayList<Integer> flipped, char piece, Othello AIBoard) {
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}
        for (int flippedpiece : flipped) {
            AIBoard.remove(piece, flippedpiece);
            AIBoard.add(oppPiece, flippedpiece);
        }
    }

    /**
     * Checks which discs have been flipped
     * @author Mart de Vries
     * @param board
     * @param position
     * @param previousboard
     * @return returns all the flipped discs in an ArrayList
     */
    public ArrayList<Integer> CheckFlipped(char[][] board, int position, char[][] previousboard) {
        int counter = -1;
        ArrayList<Integer> flipped = new ArrayList<>();
        for (int row=0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                counter++;
                if (previousboard[row][col] != board[row][col] && position != counter) {
                    flipped.add(counter);
                }
            }
        }
        return flipped;
    }

    /**
     * Removes a move from the board
     * @author Mart de Vries
     * @param AIBoard
     * @param position
     * @param piece
     * @param flipped
     */
    public void AiRemove(Othello AIBoard, int position, char piece, ArrayList<Integer> flipped) {
        AIBoard.remove(piece, position);
        ReverseFlip(flipped, piece, AIBoard);
    }
}
