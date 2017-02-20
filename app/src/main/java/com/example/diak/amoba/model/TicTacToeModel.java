package com.example.diak.amoba.model;


public class TicTacToeModel {
    private static TicTacToeModel instance = null;
    private TicTacToeModel() {
    }
    public static TicTacToeModel getInstance() {
        if (instance == null) {
            instance = new TicTacToeModel();
        }
        return instance;
    }
    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;
    private short[][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };
    private short nextPlayer = CIRCLE;

    public static void resetModel(){
        getInstance().model = new short[][]{{EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}};
    }
    public static short getFieldContent(int x,int y){
        return TicTacToeModel.getInstance().model[x][y];
    }
    public static void setFieldContent(int x,int y, short content){
        TicTacToeModel.getInstance().model[x][y] = content;
    }
    public static short getNextPlayer(){
        return getInstance().nextPlayer;
    }
    public static void changeNextPlayer(){
        if (TicTacToeModel.getInstance().getNextPlayer()==CIRCLE){
            getInstance().nextPlayer = CROSS;
        }
        else if (TicTacToeModel.getInstance().getNextPlayer() ==CROSS){
            getInstance().nextPlayer = CIRCLE;
        }
    }
    public static boolean isWin(){
        boolean win = false;
        if (isRowWin()){
            win = true;
        }
        else if (isColumnWin()){
            win = true;
        }
        else if (isCrossWin()){
            win = true;
        }
        return win;
    }
    private static boolean isCrossWin(){
        boolean crossWin = false;
        short[][] model2 = getInstance().model;
        if (model2[0][0] == getInstance().CIRCLE &&
                model2[1][1] == getInstance().CIRCLE &&
                model2[2][2] == getInstance().CIRCLE){
            crossWin = true;
        }else if (model2[0][0] == getInstance().CROSS &&
                model2[1][1] == getInstance().CROSS &&
                model2[2][2] == getInstance().CROSS){
            crossWin = true;
        }
        else if (model2[0][2] == getInstance().CIRCLE &&
                model2[1][1] == getInstance().CIRCLE &&
                model2[2][0] == getInstance().CIRCLE){
            crossWin = true;
        }
        else if (model2[0][2] == getInstance().CROSS &&
                model2[1][1] == getInstance().CROSS &&
                model2[2][0] == getInstance().CROSS){
            crossWin = true;
        }
        return crossWin;
    }
    private static boolean isColumnWin(){
        boolean ColumnWin = false;
        short[][] model2 = getInstance().model;
        for (int i = 0; i < model2[0].length; i++) {
            if (model2[0][i]== getInstance().CIRCLE &&
                    model2[1][i] ==getInstance().CIRCLE &&
                    model2[3][i] == getInstance().CIRCLE){
                ColumnWin = true;
            }
            else if (model2[0][i]== getInstance().CROSS &&
                    model2[1][i] ==getInstance().CROSS &&
                    model2[3][i] == getInstance().CROSS){
                ColumnWin = true;
            }
        }
        return ColumnWin;
    }
    private static boolean isRowWin(){
        boolean RowWin =false;
        for (int i = 0; i < getInstance().model.length; i++) {
            short[] row=getInstance().model[i];
            if (row[0]==getInstance().CIRCLE &&
                    row[1] ==getInstance().CIRCLE &&
                    row[2] == getInstance().CIRCLE){
                RowWin = true;
            }
            else if (row[0]==getInstance().CROSS &&
                    row[1] ==getInstance().CROSS &&
                    row[2] == getInstance().CROSS){
                RowWin = true;
            }
        }
        return RowWin;
    }
}
