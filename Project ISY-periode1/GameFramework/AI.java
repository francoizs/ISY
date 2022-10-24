import java.util.ArrayList;

/**
 * AI for tic-tac-toe and Othello
 * @author Mart de Vries
 * @version 1.0
 */
public class AI{

    /**
     * AI tic-tac-toe first player
     * @param movesopponent all moves done by the opponent
     * @param ownmoves all moves done by itself
     * @return move AI as first player (tic-tac-toe)
     * @author Mart de Vries
     */
    public static int TicTacToeAI1(ArrayList<Integer> movesopponent, ArrayList<Integer> ownmoves) {
        int moveAI = 0;
        if (movesopponent.size() == 0 && ownmoves.size() == 0) {
            moveAI = 1;
        } else {
            if (movesopponent.size() == 1 && ownmoves.size() == 1) {
                if (movesopponent.contains(5) && !ownmoves.contains(3)) {
                    moveAI = 3;
                } else {
                    if (!movesopponent.contains(5) && !movesopponent.contains(3) && !movesopponent.contains(2) && !ownmoves.contains(3)) {
                        moveAI = 3;
                    } else {
                        if (!movesopponent.contains(5) && !movesopponent.contains(7) && !movesopponent.contains(4) && !ownmoves.contains(7)) {
                            moveAI = 7;
                        }
                    }
                }
            } else {
                if (movesopponent.size() == 2 && ownmoves.size() == 2) {
                    if (ownmoves.contains(3) && !movesopponent.contains(2)) {
                        moveAI = 2;
                    } else {
                        if (ownmoves.contains(7) && !movesopponent.contains(4)) {
                            moveAI = 4;
                        } else {
                            if (ownmoves.contains(3) && movesopponent.contains(2) && !movesopponent.contains(5) && !movesopponent.contains(7) && !movesopponent.contains(9)) {
                                moveAI = 5;
                            } else {
                                if (ownmoves.contains(7) && movesopponent.contains(4) && !movesopponent.contains(5) && !movesopponent.contains(3) && !movesopponent.contains(9)) {
                                    moveAI = 5;
                                } else {
                                    if (ownmoves.contains(3) && movesopponent.contains(2) && movesopponent.contains(5)) {
                                        moveAI = 8;
                                    } else {
                                        if (ownmoves.contains(7) && movesopponent.contains(4) && movesopponent.contains(5)) {
                                            moveAI = 6;
                                        } else {
                                            if (ownmoves.contains(3) && movesopponent.contains(2) && movesopponent.contains(9)) {
                                                moveAI = 7;
                                            } else {
                                                if (ownmoves.contains(3) && movesopponent.contains(2) && movesopponent.contains(7)) {
                                                    moveAI = 9;
                                                } else {
                                                    if (ownmoves.contains(7) && movesopponent.contains(4) && movesopponent.contains(9)) {
                                                        moveAI = 3;
                                                    } else {
                                                        if (ownmoves.contains(7) && movesopponent.contains(4) && movesopponent.contains(3)) {
                                                            moveAI = 9;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (movesopponent.size() == 3 && ownmoves.size() == 3) {
                        if (ownmoves.contains(3) && ownmoves.contains(5) && !movesopponent.contains(7)) {
                            moveAI = 7;
                        } else {
                            if (ownmoves.contains(3) && ownmoves.contains(5) && !movesopponent.contains(9)) {
                                moveAI = 9;
                            } else {
                                if (ownmoves.contains(7) && ownmoves.contains(5) && !movesopponent.contains(3)) {
                                    moveAI = 3;
                                } else {
                                    if (ownmoves.contains(7) && ownmoves.contains(5) && !movesopponent.contains(9)) {
                                        moveAI = 9;
                                    } else {
                                        if (ownmoves.contains(3) && movesopponent.contains(5) && movesopponent.contains(4) && movesopponent.contains(2)) {
                                            moveAI = 6;
                                        } else {
                                            if (ownmoves.contains(3) && movesopponent.contains(5) && movesopponent.contains(6) && movesopponent.contains(2)) {
                                                moveAI = 4;
                                            } else {
                                                if (ownmoves.contains(3) && movesopponent.contains(5) && movesopponent.contains(7) && !ownmoves.contains(9)) {
                                                    moveAI = 9;
                                                } else {
                                                    if (ownmoves.contains(3) && movesopponent.contains(5) && movesopponent.contains(9) && !ownmoves.contains(7)) {
                                                        moveAI = 7;
                                                    } else {
                                                        if (ownmoves.contains(7) && movesopponent.contains(5) && movesopponent.contains(8) && movesopponent.contains(4)) {
                                                            moveAI = 2;
                                                        } else {
                                                            if (ownmoves.contains(7) && movesopponent.contains(5) && movesopponent.contains(2) && movesopponent.contains(4)) {
                                                                moveAI = 8;
                                                            } else {
                                                                if (ownmoves.contains(7) && movesopponent.contains(5) && movesopponent.contains(9) && !ownmoves.contains(3)) {
                                                                    moveAI = 3;
                                                                } else {
                                                                    if (ownmoves.contains(7) && movesopponent.contains(5) && movesopponent.contains(3) && !ownmoves.contains(9)) {
                                                                        moveAI = 9;
                                                                    } else {
                                                                        if (ownmoves.contains(3) && !movesopponent.contains(5) && !ownmoves.contains(5)) {
                                                                            moveAI = 5;
                                                                        } else {
                                                                            if (ownmoves.contains(7) && !movesopponent.contains(5) && !ownmoves.contains(5)) {
                                                                                moveAI = 5;
                                                                            } else {
                                                                                if (ownmoves.contains(7) && movesopponent.contains(5) && ownmoves.contains(3) && !movesopponent.contains(4)) {
                                                                                    moveAI = 4;
                                                                                } else {
                                                                                    if (ownmoves.contains(7) && movesopponent.contains(5) && ownmoves.contains(3) && !movesopponent.contains(2)) {
                                                                                        moveAI = 2;
                                                                                    } else {
                                                                                        if (ownmoves.contains(3) && movesopponent.contains(5) && ownmoves.contains(9) && !movesopponent.contains(6)) {
                                                                                            moveAI = 6;
                                                                                        } else {
                                                                                            if (ownmoves.contains(7) && movesopponent.contains(5) && ownmoves.contains(9) && !movesopponent.contains(8)) {
                                                                                                moveAI = 8;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (movesopponent.size() == 4 && ownmoves.size() == 4) {
                            for (int i = 1; i < 10; i++) {
                                if (!movesopponent.contains(i) && !ownmoves.contains(i)) {
                                    moveAI = i;
                                }
                            }
                        }
                    }
                }
            }
        }
        return moveAI;
    }

    /**
     * AI tic-tac-toe second player
     * @param movesopponent all moves done by the opponent
     * @param ownmoves all moves done by itself
     * @return move AI as second player (tic-tac-toe)
     * @author Mart de Vries
     */
    public static int TicTacToeAI2(ArrayList<Integer> movesopponent, ArrayList<Integer> ownmoves) {
        int moveAI = 0;
        if (movesopponent.get(0) != 1) {
            moveAI = 1;
        }
        return moveAI;
    }

    /**
     * AI Othello first player
     * @return move AI as first player (Othello)
     */
    public static int OthelloAI1() {
        return 0;
    }

    /**
     * AI Othello second player
     * @return move AI as second player (Othello)
     */
    public static int OthelloAI2() {
        return 0;
    }
}
