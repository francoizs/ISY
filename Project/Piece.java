public class Piece extends Board{
    private char type;

    /**
     * Sets the type of the piece.
     * @param type ('X', 'O', 'B', 'W')
     */
    public Piece(char type){
        this.type = type;
    }
}
