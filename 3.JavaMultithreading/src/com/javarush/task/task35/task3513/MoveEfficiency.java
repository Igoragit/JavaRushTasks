package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {

        final int IN_OBJECT = -1;
        final int EQUAL = 0;
        final int THIS_OBJECT = 1;

        if (this.numberOfEmptyTiles > o.numberOfEmptyTiles) return THIS_OBJECT;
        if (this.numberOfEmptyTiles < o.numberOfEmptyTiles) return IN_OBJECT;

        if(this.numberOfEmptyTiles==o.numberOfEmptyTiles){

            if(this.score>o.score) return THIS_OBJECT;
            if(this.score==o.score) return EQUAL;
            if(this.score<o.score) return IN_OBJECT;

        }

        return EQUAL;
    }
}
