package othello;

import gameFramework.Player;

import java.util.ArrayList;

public class AiOthello extends Player {

    private final int Max_depth = 7;
    private final int Max_depth1 = 5;
    private final int Max_depth2 = 6;
    private final int Max_depth3 = 7;
    private final int Max_depth4 = 8;
    private final int Max_depth5 = 14;

    public AiOthello(int playernumber, char piece) {
        super(playernumber, piece, "AI");
    }

    public int AiMove(Othello AiBoard, char piece, int number) {
        switch (number) {
            case 1:
                GreedyAi greedyai = new GreedyAi(getPlayernumber(), piece);
                return greedyai.moveselectOthello1(AiBoard, piece, Max_depth);
            case 2:
                GenerousAi generousai = new GenerousAi(getPlayernumber(), piece);
                return generousai.moveselectOthello3(AiBoard, piece, Max_depth);
            case 3:
                GreedyMovesAi greedymovesai = new GreedyMovesAi(getPlayernumber(), piece);
                return greedymovesai.moveselectOthello5(AiBoard, piece, Max_depth);
            case 4:
                TilePointsAi tilepointsai = new TilePointsAi(getPlayernumber(), piece);
                return tilepointsai.moveselectOthello7(AiBoard, piece, Max_depth);
            case 5:
                TestAi testai = new TestAi(getPlayernumber(), piece);
                return testai.moveselectOthello9(AiBoard, piece);
            case 6:
                OpponentAi opponentai = new OpponentAi(getPlayernumber(), piece);
                return opponentai.moveselectOthello10(AiBoard, piece);
            case 7:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 8:
                if (AiBoard.filledSpaces() < 21){
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 9:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 10:
                if (AiBoard.filledSpaces() < 21){
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 11:
                if (AiBoard.filledSpaces() < 21){
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth2);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 12:
                if (AiBoard.filledSpaces() < 21){
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth2);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 13:
                if (AiBoard.filledSpaces() < 21){
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 14:
                if (AiBoard.filledSpaces() < 21){
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 15:
                if (AiBoard.filledSpaces() < 21){
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth1);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 16:
                if (AiBoard.filledSpaces() < 21){
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth1);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 17:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth1);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 18:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth3);
                } else {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth5);
                }
            case 19:
                if (AiBoard.filledSpaces() < 21){
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth2);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 20:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    TilePointsAi tilepointsai1 = new TilePointsAi(getPlayernumber(), piece);
                    return tilepointsai1.moveselectOthello7(AiBoard, piece, Max_depth2);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
            case 21:
                if (AiBoard.filledSpaces() < 21){
                    GenerousAi generousai1 = new GenerousAi(getPlayernumber(), piece);
                    return generousai1.moveselectOthello3(AiBoard, piece, Max_depth4);
                } else if (AiBoard.filledSpaces() < 51) {
                    GreedyMovesAi greedymovesai1 = new GreedyMovesAi(getPlayernumber(), piece);
                    return greedymovesai1.moveselectOthello5(AiBoard, piece, Max_depth1);
                } else {
                    GreedyAi greedyai1 = new GreedyAi(getPlayernumber(), piece);
                    return greedyai1.moveselectOthello1(AiBoard, piece, Max_depth5);
                }
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
