package game;

import java.util.Arrays;
import java.util.LinkedList;

public class Game {
    private final Board board;
    private LinkedList<Character> last4moves;
    public Game() {
        board = new Board();
        last4moves = new LinkedList<>();
    }

    /**
     * This method is used to run the game, choosing the actions that have to be performed according to player`s choice.
     * If there are no available positions, it automatically updates 'last4Moves' list with directions chosen by the player.
     * In case that all directions were tried and no sum of existing numbers on the board can be made, the game will
     * end automatically. Also, the game can be stopped by the player by pressing the q+ENTER buttons.
     * Furthermore, the method automatically checks if the player has won (the highest value on the board is 2048),
     * stopping the game too.
     */
    public void run(){
        System.out.println("Insert: \n" +
                "\t-'q' to quit\n" +
                "\t-'w' to collect everything up side\n" +
                "\t-'a' to collect everything to the left side\n" +
                "\t-'s' to collect everything down side\n" +
                "\t-'d' to collect everything to the right side\n");
        board.print();
        char input = Input.getInstance().getInput();
        while (input!='q') {
           if (!board.hasFreePositions()){
               if (last4moves.size()<4){
                   last4moves.add(input);
               } else {
                   last4moves.removeLast();
                   last4moves.addFirst(input);
               }
           }
            switch (input) {
                case 'w':
                    board.process("up");
                    break;
                case 'a':
                    board.process("left");
                    break;
                case 's':
                    board.process("down");
                    break;
                case 'd':
                    board.process("right");
                    break;
                default:
                    break;
            }
           if (board.hasWon()){
               input = 'q';
               System.out.println("Congratulations! You have won!");
           } else if (last4moves.size() == 4 && last4moves.containsAll(Arrays.asList('w', 'a', 's', 'd'))){
               input = 'q';
               System.out.println("Game over!");
           } else {
               input = Input.getInstance().getInput();
           }
        }
    }
}
