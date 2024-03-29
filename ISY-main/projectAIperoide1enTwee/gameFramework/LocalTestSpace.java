package gameFramework;

/**
 * Backup van de testomgeving van het onderzoek.
 */
public class LocalTestSpace {
    public static void main(String[] args) {
//        int manualorauto = 1;
//
//        if (manualorauto == 0) {
//            int player1win = 0;
//            int draw = 0;
//            int player2win = 0;
//            int potjes = 0;
//            while (potjes < 2) {
//                Othello othello = new Othello(8, 8, '•');
//                othello.startPositions();
//                Player player1 = new Player(1, '◦', "AI");
//                Player player2 = new Player(2, '•', "AI");
//
//                potjes++;
//                 outer: while (!othello.isFull()) {
//                    for (int i = 0; i < 64; i++) {
//                        if (othello.allowMoveOthello(i, player1.getPiece())) {
//                            //Player1
//                            // check if player 1 has thought of a move in 10 seconds
//                            long startTime = System.currentTimeMillis();
//                            AiOthello ai = new AiOthello(player1.getPlayernumber(), player1.getPiece());
//                            int move1 = ai.AiMove(othello, player1.getPiece(), 9);
//                            long endTime = System.currentTimeMillis();
//                            long duration = (endTime - startTime);
//                            if (duration > 10000) {
//                                System.out.println("Player 1 took too long to think of a move");
//                                break outer;
//                            }
//                            othello.add(player1.getPiece(), move1);
//                            othello.flipPiece(move1, player1.getPiece());
//                            System.out.println(othello.toString());
//                            break;
//                        }
//                    }
//
//                    for (int b = 0; b < 64; b++) {
//                        if (othello.allowMoveOthello(b, player2.getPiece())) {
//                            // player2
//                            long startTime2 = System.currentTimeMillis();
//                            AiOthello ai2 = new AiOthello(player2.getPlayernumber(), player2.getPiece());
//                            int move2 = ai2.AiMove(othello, player2.getPiece(), 1);
//                            long endTime2 = System.currentTimeMillis();
//                            long duration2 = (endTime2 - startTime2);
//                            if (duration2 > 10000) {
//                                System.out.println("Player 2 took too long to think of a move");
//                                break outer;
//                            }
//                            othello.add(player2.getPiece(), move2);
//                            othello.flipPiece(move2, player2.getPiece());
//                            System.out.println(othello.toString());
//                            break;
//                        }
//                    }
//
//                    ArrayList<Integer> lijst = new ArrayList<>();
//                    for (int c = 0; c < 64; c++) {
//                        if (othello.allowMoveOthello(c, player1.getPiece()) || othello.allowMoveOthello(c, player2.getPiece())) {
//                            lijst.add(0, 1);
//                        }
//                    }
//                    if (lijst.size() == 0) {
//                        break;
//                    }
//                }
//
//                Path fileName = Path.of("/ResultsAi/TestAivsGreedyAi.txt"); //path van het bestand waarin de data wordt opgeslagen.
//
//                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toFile(), true));
//                writer.append("\n");
//                String ply1 = String.valueOf("Player: " + player1.getPlayernumber() + " Score is: " + othello.pieceCounter(player1.getPiece()));
//                String ply2 = String.valueOf("Player: " + player2.getPlayernumber() + " Score is: " + othello.pieceCounter(player2.getPiece()));
//                String result = ply1 + " || " + ply2 + " || nr_potje: " + potjes;
//
//                if (othello.pieceCounter(player1.getPiece()) > othello.pieceCounter(player2.getPiece())) {
//                    player1win++;
//                } else if (othello.pieceCounter(player1.getPiece()) == othello.pieceCounter(player2.getPiece())) {
//                    draw++;
//                } else {
//                    player2win++;
//                }
//
//                if (potjes == 1) {
//                    writer.append("----------------------------------------------------------------");
//                    writer.append("\n");
//                    writer.append("Begin test");
//                    writer.append("\n");
//                    writer.append("\n");
//                    writer.append("Player 1: TestAi");
//                    writer.append("\n");
//                    writer.append("Player 2: GreedyAi");
//                    writer.append("\n");
//                    writer.append("Aantal potjes: 100");
//                    writer.append("\n");
//                    writer.append("Max depth: 4");
//                    writer.append("\n");
//                    writer.append("\n");
//                }
//
//                writer.append(result);
//
//                if (potjes == 2) {
//                    writer.append("\n");
//                    writer.append("\n");
//                    writer.append("Player 1 total wins: ").append(String.valueOf(player1win));
//                    writer.append("\n");
//                    writer.append("Total draws: ").append(String.valueOf(draw));
//                    writer.append("\n");
//                    writer.append("Player 2 total wins: ").append(String.valueOf(player2win));
//                    writer.append("\n");
//                    writer.append("\n");
//                    writer.append("Einde test");
//                    writer.append("\n");
//                    writer.append("----------------------------------------------------------------");
//                    writer.append("\n");
//                }
//
//                writer.close();
//            }
//        } else {
//            ArrayList<String> ai4 = new ArrayList<>();
//            ArrayList<String> ai5 = new ArrayList<>();
////            Collections.addAll(ai5, "Gen+Gre", "Til+Gre", "Gen+Til", "Gre+Til", "GrM+Gre", "GrM+Til", "Til+Gre(mid)", "Gre+Til(mid)", "GrM+Til(mid)", "GrM+Gre(mid)",
////                                    "Gen+GrM+Til", "Gen+Gre+Til", "GrM+Til+Gre", "Gen+Til+Gre", "Gen+GrM+Gre", "GrM+Gre+Til", "Gre+GrM+Til", "Til+GrM+Gre");
////            Collections.addAll(ai4, "Gen+Gre", "Til+Gre", "Gen+Til", "Gre+Til", "GrM+Gre", "GrM+Til", "Til+Gre(mid)", "Gre+Til(mid)", "GrM+Til(mid)", "GrM+Gre(mid)",
////                                    "Gen+GrM+Til", "Gen+Gre+Til", "GrM+Til+Gre", "Gen+Til+Gre", "Gen+GrM+Gre", "GrM+Gre+Til", "Gre+GrM+Til", "Til+GrM+Gre");
////            Collections.addAll(ai4, "DepthTest");
////            Collections.addAll(ai4, "Gen+Gre", "Til+Gre", "Gen+Til", "Gre+Til", "GrM+Gre", "GrM+Til");
////            Collections.addAll(ai4, "Til+Gre(mid)", "Gre+Til(mid)", "GrM+Til(mid)", "GrM+Gre(mid)");
////            Collections.addAll(ai4, "Gen+GrM+Til", "Gen+Gre+Til", "GrM+Til+Gre", "Gen+Til+Gre", "Gen+GrM+Gre", "GrM+Gre+Til", "Gre+GrM+Til", "Til+GrM+Gre");
////            for (String l : ai5) {
////                ai4.add("Gre+GrM(late)vs" + l);
//            Collections.addAll(ai4, "Gre+Til(late)");
//                for (String q : ai4) {
//                    for (int s = 0; s < 2; s++) {
//                        int player1win = 0;
//                        int draw = 0;
//                        int player2win = 0;
//                        double winpercentage;
//                        int potjes = 0;
//                        int legalpotjes = 0;
//                        int potjes1 = 0;
//                        int depth = 0;
//                        int filled = 0;
//                        int p = 50;
//
//                        while (potjes < p) {
//                            Othello othello = new Othello(8, 8, '•');
//                            othello.startPositions();
//                            Player player1 = new Player(1, '◦', "AI");
//                            Player player2 = new Player(2, '•', "AI");
//                            String over_10 = "";
//                            boolean timeout = false;
//
//                            potjes++;
//                            outer:
//                            while (!othello.isFull()) {
//                                for (int i = 0; i < 64; i++) {
//                                    if (othello.allowMoveOthello(i, player1.getPiece())) {
//                                        //Player1
//                                        if (s == 0) {
//                                            AiOthello ai = new AiOthello(player1.getPlayernumber(), player1.getPiece());
//                                            int move1 = ai.AiMove(othello, player1.getPiece(), 5);
//                                            othello.add(player1.getPiece(), move1);
//                                            othello.flipPiece(move1, player1.getPiece());
//                                            System.out.println(othello.toString());
//                                            break;
//                                        } else {
//                                            AiOthello ai = new AiOthello(player1.getPlayernumber(), player1.getPiece());
//                                            depth = ai.getMax_depth();
//                                            long strartTime = System.currentTimeMillis();
//                                            int move1 = ai.AiMove(othello, player1.getPiece(), ai4.indexOf(q) + 25);
//                                            long endTime = System.currentTimeMillis();
//                                            long duration = (endTime - strartTime);
//                                            if (duration > 9500) {
//                                                if (legalpotjes == 0) {
//                                                    potjes1 = 1;
//                                                }
//                                                potjes = p;
//                                                timeout = true;
//                                                filled = othello.filledSpaces();
//                                                break outer;
//                                            }
//                                            othello.add(player1.getPiece(), move1);
//                                            othello.flipPiece(move1, player1.getPiece());
//                                            System.out.println(othello.toString());
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                for (int b = 0; b < 64; b++) {
//                                    if (othello.allowMoveOthello(b, player2.getPiece())) {
//                                        // player2
//                                        if (s == 0) {
//                                            AiOthello ai2 = new AiOthello(player2.getPlayernumber(), player2.getPiece());
//                                            depth = ai2.getMax_depth();
//                                            long strartTime = System.currentTimeMillis();
//                                            int move2 = ai2.AiMove(othello, player2.getPiece(), ai4.indexOf(q) + 25);
//                                            long endTime = System.currentTimeMillis();
//                                            long duration = (endTime - strartTime);
//                                            if (duration > 9500) {
//                                                if (legalpotjes == 0) {
//                                                    potjes1 = 1;
//                                                }
//                                                potjes = p;
//                                                timeout = true;
//                                                filled = othello.filledSpaces();
//                                                break outer;
//                                            }
//                                            othello.add(player2.getPiece(), move2);
//                                            othello.flipPiece(move2, player2.getPiece());
//                                            System.out.println(othello.toString());
//                                            break;
//                                        } else {
//                                            AiOthello ai2 = new AiOthello(player2.getPlayernumber(), player2.getPiece());
//                                            int move2 = ai2.AiMove(othello, player2.getPiece(), 5);
//                                            othello.add(player2.getPiece(), move2);
//                                            othello.flipPiece(move2, player2.getPiece());
//                                            System.out.println(othello.toString());
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                ArrayList<Integer> lijst = new ArrayList<>();
//                                for (int c = 0; c < 64; c++) {
//                                    if (othello.allowMoveOthello(c, player1.getPiece()) || othello.allowMoveOthello(c, player2.getPiece())) {
//                                        lijst.add(0, 1);
//                                    }
//                                }
//                                if (lijst.size() == 0) {
//                                    break;
//                                }
//                            }
//
//                            if (potjes == 1) {
//                                legalpotjes++;
//                            }
//
//                            Path fileName = Path.of("/ResultsAi/Late/TestAivs" + q + ".txt"); //path van het bestand waarin de data wordt opgeslagen.
//
//                            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toFile(), true));
//                            writer.append("\n");
//                            String ply1 = String.valueOf("Player: " + player1.getPlayernumber() + " Score is: " + othello.pieceCounter(player1.getPiece()));
//                            String ply2 = String.valueOf("Player: " + player2.getPlayernumber() + " Score is: " + othello.pieceCounter(player2.getPiece()));
//                            String result = ply1 + " || " + ply2 + " || nr_potje: " + potjes;
//                            if (timeout) {
//                                result = ply1 + " || " + ply2 + " || nr_potje: " + potjes + " || Game timed out";
//                            }
//                            if (othello.pieceCounter(player1.getPiece()) > othello.pieceCounter(player2.getPiece())) {
//                                player1win++;
//                            } else if (othello.pieceCounter(player1.getPiece()) == othello.pieceCounter(player2.getPiece())) {
//                                draw++;
//                            } else {
//                                player2win++;
//                            }
//
//                            if (s == 0 && potjes == p) {
//                                winpercentage = (double) player2win / p;
//                            } else {
//                                winpercentage = (double) player1win / p;
//                            }
//
//                            if (potjes == 1 || potjes1 == 1) {
//                                writer.append("----------------------------------------------------------------");
//                                writer.append("\n");
//                                writer.append("Begin test");
//                                writer.append("\n");
//                                writer.append("\n");
//                                if (s == 0) {
//                                    writer.append("Player 1: TestAi");
//                                } else {
//                                    writer.append("Player 1: ").append(q);
//                                }
//                                writer.append("\n");
//                                if (s == 0) {
//                                    writer.append("Player 2: ").append(q);
//                                } else {
//                                    writer.append("Player 2: TestAi");
//                                }
////                                if (s == 0) {
////                                    String name = "";
////                                    for (String i : ai4) {
////                                        for (int o = 0; o < i.length(); o++) {
////                                            Character d = i.charAt(o);
////                                            if (d.equals('v')) {
////                                                writer.append("Player 1: ").append(name);
////                                                break;
////                                            } else {
////                                                name = name + d;
////                                            }
////                                        }
////                                    }
////                                } else {
////                                    String name = "";
////                                    for (String i : ai4) {
////                                        for (int o = i.length() - 1; o >= 0; o--) {
////                                            Character d = i.charAt(o);
////                                            if (d.equals('s')) {
////                                                writer.append("Player 1: ").append(name);
////                                                break;
////                                            } else {
////                                                name = d + name;
////                                            }
////                                        }
////                                    }
////                                }
////                                writer.append("\n");
////                                if (s == 0) {
////                                    String name = "";
////                                    for (String i : ai4) {
////                                        for (int o = i.length() - 1; o >= 0; o--) {
////                                            Character d = i.charAt(o);
////                                            if (d.equals('s')) {
////                                                writer.append("Player 2: ").append(name);
////                                                break;
////                                            } else {
////                                                name = d + name;
////                                            }
////                                        }
////                                    }
////                                } else {
////                                    String name = "";
////                                    for (String i : ai4) {
////                                        for (int o = 0; o < i.length(); o++) {
////                                            Character d = i.charAt(o);
////                                            if (d.equals('v')) {
////                                                writer.append("Player 2: ").append(name);
////                                                break;
////                                            } else {
////                                                name = name + d;
////                                            }
////                                        }
////                                    }
////                                }
//                                writer.append("\n");
//                                writer.append("Amount of games: ").append(String.valueOf(p));
//                                writer.append("\n");
//                                writer.append("Max depth TestAi: ").append(String.valueOf(depth));
//                                writer.append("\n");
//                                writer.append("\n");
//                            }
//
//
//                            writer.append(result);
//
//                            if (potjes == p) {
//                                writer.append("\n");
//                                writer.append("\n");
//                                writer.append("Player 1 total wins: ").append(String.valueOf(player1win));
//                                writer.append("\n");
//                                writer.append("Total draws: ").append(String.valueOf(draw));
//                                writer.append("\n");
//                                writer.append("Player 2 total wins: ").append(String.valueOf(player2win));
//                                writer.append("\n");
//                                writer.append("Win percentage: ").append(String.valueOf(Math.round(winpercentage * 100))).append("%");
//                                if (filled != 0) {
//                                    writer.append("\n");
//                                    writer.append("Timed out at ").append(String.valueOf(filled)).append(" filled spaces.");
//                                }
//                                writer.append("\n");
//                                writer.append("Total legal games: ").append(String.valueOf(legalpotjes));
//                                writer.append("\n");
//                                writer.append("\n");
//                                writer.append("End test");
//                                writer.append("\n");
//                                writer.append("----------------------------------------------------------------");
//                                writer.append("\n");
//                            }
//
//                            writer.close();
//
//                            if (potjes == p) {
//                                break;
//                            }
//
//                            legalpotjes++;
//                        }
//                    }
//                }
////                ai4.remove("Gre+GrM(late)vs" + l);
////            }
//        }
    }
}
