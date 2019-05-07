package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    private Stack<Integer> previousScores;
    private Stack<Tile[][]> previousStates;

    protected int score;
    protected int maxTile;

    private boolean isSaveNeeded = true;

    public Model() {

        resetGameTiles();
        this.previousScores = new Stack<Integer>();
        this.previousStates = new Stack<Tile[][]>();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty()) return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)  return true;
                if (gameTiles[j][i].value == gameTiles[j - 1][i].value)  return true;
            }
        }
        return false;
    }

    protected void resetGameTiles(){

        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for(int x =0; x<FIELD_WIDTH; x++){
            for(int j =0; j<FIELD_WIDTH; j++){
                gameTiles[x][j]=new Tile();
            }
        }

        score=0;
        maxTile=2;
        addTile();
        addTile();
    }

    private void addTile(){

        List<Tile> emptyTiels = getEmptyTiles();
        if(emptyTiels != null && !emptyTiels.isEmpty()){
            int index = (int) (emptyTiels.size()*Math.random());
            emptyTiels.get(index).value = Math.random()<0.9?2:4;
        }
        }

    private List<Tile> getEmptyTiles(){

        List<Tile> tileList = new ArrayList<>();

        for(int x =0; x<FIELD_WIDTH; x++){
            for(int j =0; j<FIELD_WIDTH; j++){
                if(gameTiles[x][j].isEmpty()){
                    tileList.add(gameTiles[x][j]);
                }
            }
        }

        return tileList;
    }
    private boolean compressTiles(Tile[] tiles) {

        boolean isChanged = false;

        Tile temp;
        for (int i = 0; i < FIELD_WIDTH-1; i++) {
            for (int j = 0; j < FIELD_WIDTH-1; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value != 0) {
                    isChanged = true;
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;

                }
            }
        }

        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles){

        boolean isChanged=false;
            for (int j = 0; j < FIELD_WIDTH-1; j++) {
                if (tiles[j].value!=0 && tiles[j].value ==  tiles[j + 1].value) {
                    isChanged=true;

                    tiles[j].value = tiles[j].value*2;
                    tiles[j + 1].value=0;

                    if(tiles[j].value>maxTile) maxTile=tiles[j].value;
                    score+=tiles[j].value;

                }

            }

        if (isChanged) {
            Tile temp;
            for (int j = 0; j < FIELD_WIDTH-1; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value!= 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                }
            }
        }
        return isChanged;
    }

    public void left(){

        if(isSaveNeeded)saveState(gameTiles);
        boolean isChanged = false;

        for (int x =0; x<FIELD_WIDTH; x++){

            if (compressTiles(gameTiles[x]) | mergeTiles(gameTiles[x])) {
                isChanged = true;
            }
        }
        if (isChanged) {
             addTile();
        }

        isSaveNeeded=true;

    }
    public void up(){
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right(){
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();

    }

    public void down(){
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }


    public void rotate() {

        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {

                Tile tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[j][FIELD_WIDTH - 1 - i];
                gameTiles[j][FIELD_WIDTH - 1 - i] = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
                gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = gameTiles[FIELD_WIDTH - 1 - j][i];
                gameTiles[FIELD_WIDTH - 1 - j][i] = tmp;
            }
        }
    }

    private void saveState(Tile[][] tile){

        Tile[][] tilesToSave = new Tile[tile.length][tile[0].length];
        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[0].length; j++) {
                tilesToSave[i][j] = new Tile(tile[i][j].value);
            }
        }

        this.previousScores.push(this.score);
        this.previousStates.push(tilesToSave);
        isSaveNeeded=false;
    }

    public void rollback(){
        if(!previousStates.empty() & !previousScores.empty()){
            this.gameTiles=previousStates.pop();
            this.score=previousScores.pop();
        }

    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0: left();
                break;
            case 1: up();
                break;
            case 2: down();
                break;
            case 3: right();
                break;
        }

    }

    public boolean hasBoardChanged(){

        int thisSum = 0;
        int previousSum = 0;

        Tile[][] tmp = previousStates.peek();

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                thisSum += gameTiles[i][j].value;
                previousSum += tmp[i][j].value;
            }
        }

        return thisSum != previousSum;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue(4, Collections.reverseOrder());

        queue.add(getMoveEfficiency(() -> left()));
        queue.add(getMoveEfficiency(() -> right()));
        queue.add(getMoveEfficiency(() -> up()));
        queue.add(getMoveEfficiency(() -> down()));

        Move move = queue.peek().getMove();
        move.move();
        }
        }
