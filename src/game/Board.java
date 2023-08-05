package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int[][] board;
    private static Random random = new Random();
    private final List<Position> freePositions;
    private int highest;
    public Board() {
        freePositions = new ArrayList<>();
        board = new int[4][4];
        highest = 0;
        load();
        getInt();
        getInt();
    }

    private void load(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
                freePositions.add(new Position(i,j));
            }
        }
    }

    /**
     * This method selects randomly a free square/position from the table and puts on it either number '2' or '4'
     * (2 has a chance of appearance of 90%, while 4 has 10%).
     * The track for free squares is kept by 'freePositions' list.
     */
    private void getInt(){
        if (freePositions.size() == 0){
            System.out.println("Out of free positions.");
        }
        int i = random.nextInt(freePositions.size());
        Position pos = freePositions.get(i);
        i = random.nextInt(10);
        if (i == 0){
            board[pos.getX()][pos.getY()] = 4;
        } else {
            board[pos.getX()][pos.getY()] = 2;
        }
        freePositions.remove(pos);
    }

    /**
     * Depending on the inserted direction of your choice, this method iterates through each
     * lane or column and calculates the sum of identical numbers which are neighbours (except empty spots).
     * After that, all numbers will be shifted to the inserted direction.
     *      -eq.: process("up") for the following matrix will have this result:
     *      0 2 2 0      4 4 2 2
     *      2 0 0 0  =>  0 2 0 0
     *      0 2 0 0      0 0 0 0
     *      2 2 0 0      0 0 0 0
     * Also, 'flag' variable is used to keep track of possible changes of the matrix. So, in case that any modification
     * was made, there will be generated a random number (2 or 4) at a random position (depending on the available squares).
     */
    public void process(String direction){
        boolean flag = false;
        switch (direction.trim().toLowerCase()){
            case "up":
                for (int j = 0; j < board.length; j++) {
                    int tmp = 0;
                    int k = tmp+1;
                    while (k < board.length){
                        while (k < board.length-1 && board[k][j] == 0) {
                            k++;
                        }
                        if (board[k][j] != 0 && board[k][j] == board[tmp][j]) {
                            board[tmp][j] *= 2;
                            board[k][j] = 0;
                            freePositions.add(new Position(k, j));
                            updateHighest(board[tmp][j]);
                            flag = true;
                        }
                        tmp = k;
                        k = tmp+1;
                    }
                    tmp = 1;
                    k = tmp;

                    while (k<board.length) {
                        while (k < board.length && board[k][j] == 0) {
                            k++;
                        }
                        if (k < board.length) {
                            if (board[tmp-1][j] == 0) {
                                board[tmp-1][j] = board[k][j];
                                board[k][j] = 0;
                                freePositions.remove(new Position(tmp-1, j));
                                freePositions.add(new Position(k, j));
                                flag = true;
                            }
                        }
                        tmp++;
                        k = tmp;
                    }
                }
                break;
            case "down":
                for (int j = 0; j < board.length; j++) {
                    int tmp = board.length-1;
                    int k = tmp-1;
                    while (k >=0){
                        while (k > 0 && board[k][j] == 0) {
                            k--;
                        }
                        if (board[k][j] != 0 && board[k][j] == board[tmp][j]) {
                            board[tmp][j] *= 2;
                            board[k][j] = 0;
                            freePositions.add(new Position(k, j));
                            updateHighest(board[tmp][j]);
                            flag = true;
                        }
                        tmp = k;
                        k = tmp-1;
                    }
                    tmp = board.length-2;
                    k = tmp;

                    while (k >=0) {
                        while (k >= 0 && board[k][j] == 0) {
                            k--;
                        }
                        if (k >= 0) {
                            if (board[tmp+1][j] == 0) {
                                board[tmp+1][j] = board[k][j];
                                board[k][j] = 0;
                                freePositions.remove(new Position(tmp+1, j));
                                freePositions.add(new Position(k, j));
                                flag = true;
                            }
                        }
                        tmp--;
                        k = tmp;
                    }
                }
                break;
            case "left":
                for (int i = 0; i < board.length; i++) {
                    int tmp = 0;
                    int k = tmp+1;
                    while (k < board.length){
                        while (k < board.length-1 && board[i][k] == 0) {
                            k++;
                        }
                        if (board[i][k] != 0 && board[i][k] == board[i][tmp]) {
                            board[i][tmp] *= 2;
                            board[i][k] = 0;
                            freePositions.add(new Position(i, k));
                            updateHighest(board[i][tmp]);
                            flag = true;
                        }
                        tmp = k;
                        k = tmp+1;
                    }
                    tmp = 1;
                    k = tmp;

                    while (k<board.length) {
                        while (k < board.length && board[i][k] == 0) {
                            k++;
                        }
                        if (k < board.length) {
                            if (board[i][tmp-1] == 0) {
                                board[i][tmp-1] = board[i][k];
                                board[i][k] = 0;
                                freePositions.remove(new Position(i, tmp-1));
                                freePositions.add(new Position(i, k));
                                flag = true;
                            }
                        }
                        tmp++;
                        k = tmp;
                    }
                }
                break;
                case "right":
                for (int i = 0; i < board.length; i++) {
                    int tmp = board.length-1;
                    int k = tmp-1;
                    while (k >=0){
                        while (k > 0 && board[i][k] == 0) {
                            k--;
                        }
                        if (board[i][k] != 0 && board[i][k] == board[i][tmp]) {
                            board[i][tmp] *= 2;
                            board[i][k] = 0;
                            freePositions.add(new Position(i, k));
                            updateHighest(board[i][tmp]);
                            flag = true;
                        }
                        tmp = k;
                        k = tmp-1;
                    }
                    tmp = board.length-2;
                    k = tmp;

                    while (k >=0) {
                        while (k >= 0 && board[i][k] == 0) {
                            k--;
                        }
                        if (k >= 0) {
                            if (board[i][tmp+1] == 0) {
                                board[i][tmp+1] = board[i][k];
                                board[i][k] = 0;
                                freePositions.remove(new Position(i, tmp+1));
                                freePositions.add(new Position(i, k));
                                flag = true;
                            }
                        }
                        tmp--;
                        k = tmp;
                    }
                }
                break;
        }
        if (flag){
            getInt();
            print();
        } else {
            System.out.println("Try another direction!");
        }
    }

    /**
     * 'highest' variable is used to track the maximum value on the board.
     */
    private void updateHighest(int number){
        if (number> highest){
            highest = number;
        }
    }
    /**
     * Returns: true if there are available positions and false otherwise.
     */
    public boolean hasFreePositions() {
        return freePositions.size()>0;
    }
    /**
     * Returns: true if number 2048 was reached and false otherwise.
     */
    public boolean hasWon(){
        return highest == 2048;
    }

    public void print(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] > 0) {
                    if (j == board.length-1){
                        System.out.printf("%4s", board[i][j]);
                    } else {
                        System.out.printf("%4s|", board[i][j]);
                    }
                } else {
                    if (j == board.length-1){
                        System.out.printf("%4s", " ");
                    } else {
                        System.out.printf("%4s|", " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
