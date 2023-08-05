package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final Board board;
    private LinkedList<Character> last4moves;
    public Game() {
        board = new Board();
        last4moves = new LinkedList<>();
    }

    public void run(){
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
           if (last4moves.size() == 4 && last4moves.containsAll(Arrays.asList('w', 'a', 's', 'd'))){
               input = 'q';
               System.out.println("Game over!");
           } else {
               input = Input.getInstance().getInput();
           }
        }
    }
}
