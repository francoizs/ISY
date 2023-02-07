package othello;

import gameFramework.ClientValuesSingleton;
import gameFramework.Player;

import java.util.ArrayList;

public class AiOthello extends Player {

    private ClientValuesSingleton singleton = ClientValuesSingleton.getInstance();
    private final int maxTilesEarly = singleton.getMaxTilesEarly();
    private final int maxTilesMid = singleton.getMaxTilesMid();
    private final int maxDepthTilePoints = singleton.getTilePointsDepth();
    private final int maxDepthGreedy = singleton.getGreedyDepth();
    private boolean isTiles = true;

    public AiOthello(int playernumber, char piece) {
        super(playernumber, piece, "AI");
    }

    public int AiMove(Othello AiBoard, char piece, int number) {
        if (AiBoard.filledSpaces() < maxTilesEarly){
            TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
            return tilepointsai1.moveselectOthello7(AiBoard, piece, maxDepthTilePoints);
        } else if (AiBoard.filledSpaces() < maxTilesMid) {
            TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
            return tilepointsai1.moveselectOthello7(AiBoard, piece, maxDepthTilePoints);
        } else {
            isTiles = false;
            GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
            return greedyai1.moveselectOthello1(AiBoard, piece, maxDepthGreedy);
        }
    }

    public int getMax_depth() {
        if(isTiles){
            return this.maxDepthTilePoints;
        } else {
            return this.maxDepthGreedy;
        }
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