package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int[][] board;
    private static Random random = new Random();
    private final List<Position> freePositions;
    private int biggest;
    public Board() {
        freePositions = new ArrayList<>();
        board = new int[4][4];
        biggest = 0;
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

    private boolean getInt(){
        if (freePositions.size() == 0){
            return false;
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
        return true;
    }

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
                            updateBiggest(board[tmp][j]);
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
                            updateBiggest(board[tmp][j]);
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
                            updateBiggest(board[i][tmp]);
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
                            updateBiggest(board[i][tmp]);
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
    private void updateBiggest(int number){
        if (number>biggest){
            biggest = number;
        }
    }

    public boolean hasFreePositions() {
        return freePositions.size()>0;
    }
    public boolean hasWon(){
        return biggest == 2048;
    }

    public void print(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] > 0) {
                    stringBuilder.append(board[i][j]).append(" | ");
                } else {
                    stringBuilder.append("  | ");
                }
            }
            stringBuilder.replace(stringBuilder.length()-3, stringBuilder.length(), "");
            stringBuilder.append("\n");
        }
        System.out.print(stringBuilder);
    }
}
