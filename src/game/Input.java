package game;

import java.util.Scanner;

public class Input {
   private static Input INSTANCE;
   private Scanner scanner;

    private Input() {
        this.scanner = new Scanner(System.in);
    }

    public char getInput(){
        char input;
        while (scanner.hasNextLine()){
            try {
                input = scanner.nextLine().toLowerCase().charAt(0);
                if (input == 'q' || input == 'w' || input == 'a' || input == 's' || input == 'd'){
                    return input;
                } else {
                    System.out.println("Insert: \n" +
                            "\t-'q' to quit\n" +
                            "\t-'w' to collect everything up side\n" +
                            "\t-'a' to collect everything to the left side\n" +
                            "\t-'s' to collect everything down side\n" +
                            "\t-'d' to collect everything to the right side\n");
                }
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Insert: \n" +
                        "\t-'q' to quit\n" +
                        "\t-'w' to collect everything up side\n" +
                        "\t-'a' to collect everything to the left side\n" +
                        "\t-'s' to collect everything down side\n" +
                        "\t-'d' to collect everything to the right side\n");
            }

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
