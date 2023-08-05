package game;

public class Game {
    private final Board board;

    public Game() {
        board = new Board();
    }

    public void run(){
        board.print();
        char input = Input.getInstance().getInput();
        while (input!='q') {
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
            input = Input.getInstance().getInput();
        }
    }
}
