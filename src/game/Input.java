package game;

import java.util.Scanner;

public class Input {
   private static Input INSTANCE;
   private Scanner scanner;

    private Input() {
        this.scanner = new Scanner(System.in);
    }

    public char getInput(){
        while (scanner.hasNextLine()){
            return scanner.nextLine().toLowerCase().charAt(0);
        }
        return 'q';
    }

    public static Input getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Input();
        }
        return INSTANCE;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }
}
