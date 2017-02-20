package com.example.diak.amoba.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.diak.amoba.model.TicTacToeModel;

public class TicTacToeView extends View {
    Paint paintBg;
    Paint paintLine;
    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);
        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
        drawGameArea(canvas);
        drawPlayers(canvas);

    }
    private void drawGameArea(Canvas canvas) {
        canvas.drawLine(0,0,getWidth(),0,paintLine);
        //canvas.drawLine(0,0,getWidth(),getHeight(),paintLine);
        canvas.drawLine(getWidth(),0,getWidth(),getHeight(),paintLine);
        canvas.drawLine(getWidth(),getHeight(),0,getHeight(),paintLine);
        canvas.drawLine(0,getHeight(),0,0,paintLine);

        canvas.drawLine((getWidth()/3),0,(getWidth()/3),getHeight(),paintLine);
        canvas.drawLine((getWidth()/3)+(getWidth()/3),0,(getWidth()/3)+(getWidth()/3),getHeight(),paintLine);


        canvas.drawLine(0,(getHeight()/3),getWidth(),getHeight()/3,paintLine);
        canvas.drawLine(0,(getHeight()/3)*2,getWidth(),(getHeight()/3)*2,paintLine);
    }
    private void drawPlayers(Canvas canvas) {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (TicTacToeModel.getInstance().getFieldContent(i,j) ==
                        TicTacToeModel.CIRCLE){
                    float x = ((getWidth()/3)*(i+1))-((getWidth()/3)/2);
                    float y = ((getHeight()/3)*(j+1))-((getHeight()/3)/2);
                    canvas.drawCircle(x,y,((getHeight()/3)/2)-50,paintLine);
                }
                else if (TicTacToeModel.getInstance().getFieldContent(i,j) ==
                        TicTacToeModel.CROSS){
                    float x2Begin1 =(getWidth()/3)*i;
                    float x2End1 =(getWidth()/3)*(i+1);
                    float y2Begin1=(getHeight()/3)*j;
                    float y2End1=(getHeight()/3)*(j+1);
                    canvas.drawLine(x2Begin1,y2Begin1,x2End1,y2End1,paintLine);

                    float x2Begin2 =(getWidth()/3)*(i+1);
                    float x2End2 =(getWidth()/3)*i;
                    float y2Begin2=(getHeight()/3)*j;
                    float y2End2=(getHeight()/3)*(j+1);
                    canvas.drawLine(x2Begin2,y2Begin2,x2End2,y2End2,paintLine);
                }
                else {

                }
            }
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int
            heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);
            if (tX < 3 && tY < 3 && TicTacToeModel.getInstance().getFieldContent(tX,tY)
                    == TicTacToeModel.EMPTY) {

                TicTacToeModel.getInstance().setFieldContent(tX,tY,TicTacToeModel.getInstance
                        ().getNextPlayer());
                TicTacToeModel.getInstance().changeNextPlayer();
                invalidate();
            }

        }
        return super.onTouchEvent(event);
    }
}

