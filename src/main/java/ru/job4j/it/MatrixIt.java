package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = row; i < data.length; i++) {
            System.out.println("row : " + row + "dlina v yacheikax: " + data.length);
            if (row == data.length - 1) {
                return column < data[row].length;
            }
            if (data[row].length == 0 || data[row].length == column) {
                row++;
                column = 0;
            }
            if (data.length == 0) {
                return false;
            }
        }

        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        System.out.println("row next: " + row + "column" + column);
        return data[row][column++];
    }

    public static void main(String[] args) {

        int[][] array3 = {{1}, {}, {}, {}, {2}};
        MatrixIt matrixIt = new MatrixIt(array3);
        System.out.println(array3);
        System.out.println("длина массива: " + matrixIt.data.length);
        System.out.println("длина строки 1 - " + matrixIt.data[1].length);

        System.out.println(matrixIt.next());
        System.out.println(array3);
        System.out.println(matrixIt.next());

    }
}
