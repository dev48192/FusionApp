package com.example.iit.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static int Splash_time=4000;
    /*Button[] buttons;
    int index = 0;
    int count=0;
    int repetition;
    Vector red=new Vector();
    Vector green=new Vector();
    Vector corner=new Vector();
    Vector uside=new Vector();
    Vector dside=new Vector();
    Vector lside=new Vector();
    Vector rside=new Vector();
    Vector mid=new Vector();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,BoardGame.class);
                startActivity(intent);
                finish();
            }
        },Splash_time);
        /*buttons = new Button[54];
        for(int i=1;i<=4;i++){uside.add(i);}
        for(int i=49;i<=52;i++){dside.add(i);}
        for(int i=6;i<=42;i=i+6){lside.add(i);}
        for(int i=5;i<=47;i=i+6){rside.add(i);}
        corner.add(0);
        corner.add(5);
        corner.add(48);
        corner.add(53);
        for(int i=0;i<buttons.length;i++) {
            if(!(corner.contains(i))&& !(uside.contains(i)) && !(dside.contains(i)) && !(lside.contains(i)) && !(rside.contains(i))){mid.add(i);}
        }
        for(int i=0; i<buttons.length; i++) {

                String buttonID = "button" + (i+1);

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i] = ((Button) findViewById(resID));
                buttons[i].setOnClickListener(this);
        }*/
    }
   /* @Override
    public void onClick(View v) {
// TODO Auto-generated method stub


        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getId() == v.getId()) {
                index = i;
                count++;
                break;
            }
        }
        // Odd number is for red turn
        if(count%2!=0) {
            //check if this is not belongs to green
            if(!(green.contains(index))){
                setRed(index);
                for(int i=0;i<buttons.length;i++) {
                buttons[i].setBackground(getResources().getDrawable(R.drawable.button_border_green));
                }
            }else{
                count--;
            }

        }else
        //even number is for green turn
        if(count%2==0){
            //check if this is not belongs to red
            if(!(red.contains(index))) {
                setGreen(index);
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setBackground(getResources().getDrawable(R.drawable.button_border));
                }
            }else{
                count--;
            }
        }
        if(count>2 && (red.isEmpty() || green.isEmpty())){
            final Dialog d=new Dialog(MainActivity.this);
            d.setContentView(R.layout.activity_winning_dialogue);
            TextView t=d.findViewById(R.id.player);
            if(green.isEmpty())
                t.setText("Player 1 Win!");
            if(red.isEmpty())
                t.setText("Player 2 Win!");
            d.show();

            Button new_game=d.findViewById(R.id.btn_new_game);
            Button exit=d.findViewById(R.id.btn_exit);

            new_game.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<buttons.length;i++){
                        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                        buttons[i].setBackground(getResources().getDrawable(R.drawable.button_border));
                        count=0;
                    }
                    red.removeAllElements();
                    green.removeAllElements();
                    Toast.makeText(MainActivity.this, "New Game Started", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                }
            });
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
       //Toast.makeText(this, "Button red index => "+red+"Green"+green, Toast.LENGTH_LONG).show();
    }
    private int countRepeat(Vector v,int i){
        //count the number of click
        int x=0;
        Iterator<Integer>itr=v.iterator();
        while (itr.hasNext()){
            if(itr.next().intValue()==i){x++;}
        }
        return x;
    }
    private Vector deleteElement(Vector v,int i){
        Iterator<Integer>r=v.iterator();
        while (r.hasNext()){
            if (r.next().intValue()==i){
                r.remove();}
        }
        return v;
    }
    private void setRed(int i){
        red.add(i);
        repetition=countRepeat(red,i);
        if(repetition==1) {
            buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
        }
        if(repetition==2){
            if(corner.contains(i)){
                cornerBlastRed(i);
            }else {
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
            }
        }
        if(repetition==3){
            if(uside.contains(i)){usideBlast(i);}else
            if(dside.contains(i)){dsideBlast(i);}else
            if(lside.contains(i)){lsideBlast(i);}else
            if(rside.contains(i)){rsideBlast(i);}else
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red3), null, null, null);
        }
        if(repetition==4){
            if(mid.contains(i)){
                midBlast(i);
            }
        }
    }
    private void setGreen(int i){
        green.add(i);
        repetition=countRepeat(green,i);
        if(repetition==1) {
            buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
        }
        if(repetition==2){
            if(corner.contains(i)){
                cornerBlastGreen(i);
            }else {
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
            }
        }
        if(repetition==3){
            if(uside.contains(i)){usideBlast(i);}else
            if(dside.contains(i)){dsideBlast(i);}else
            if(lside.contains(i)){lsideBlast(i);}else
            if(rside.contains(i)){rsideBlast(i);}else
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green3), null, null, null);
        }
        if(repetition==4){
            if(mid.contains(i)){
                midBlast(i);
            }
        }
    }
    private void cornerBlastRed(int i){
        //leaving a blank
        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        //removing its existance
        red=deleteElement(red,i);
        if(i==0){
            int temp=i;
            if(red.contains(temp+1)){
                red.add(temp+1);
                if(countRepeat(red,temp+1)==2){
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+1)==3){
                    usideBlast(temp+1);
                }

            }else if(green.contains(temp+1)){
                for(int j=0;j<=countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                green=deleteElement(green,temp+1);
                if(countRepeat(red,temp+1)==2){
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+1)==3){
                    usideBlast(temp+1);
                }
            }else{
                red.add(temp+1);
                buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
            if(red.contains(temp+6)){
                red.add(temp+6);
                if(countRepeat(red,temp+6)==2){
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+6)==3){
                    lsideBlast(temp+6);
                }
            }else if(green.contains(temp+6)){
                for(int j=0;j<=countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                green=deleteElement(green,temp+6);
                if(countRepeat(red,temp+6)==2){
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+6)==3){
                    lsideBlast(temp+6);
                }
            }else{
                red.add(temp+6);
                buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
        }
        if(i==5){
            int temp=i;
            if(red.contains(temp-1)){
                red.add(temp-1);
                if(countRepeat(red,temp-1)==2){
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-1)==3){
                    usideBlast(temp-1);
                }
            }else if(green.contains(temp-1)){
                for(int j=0;j<=countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                green=deleteElement(green,temp-1);
                if(countRepeat(red,temp-1)==2){
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-1)==3){
                    usideBlast(temp-1);
                }
            }else{
                red.add(temp-1);
                buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
            if(red.contains(temp+6)){
                red.add(temp+6);
                if(countRepeat(red,temp+6)==2){
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+6)==3){
                    rsideBlast(temp+6);
                }
            }else if(green.contains(temp+6)){
                for(int j=0;j<=countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                green=deleteElement(green,temp+6);
                if(countRepeat(red,temp+6)==2){
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+6)==3){
                    rsideBlast(temp+6);
                }
            }else{
                red.add(temp+6);
                buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
        }
        if(i==48){
            int temp=i;
            if(red.contains(temp+1)){
                red.add(temp+1);
                if(countRepeat(red,temp+1)==2){
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+1)==3){
                    dsideBlast(temp+1);
                }

            }else if(green.contains(temp+1)){
                for(int j=0;j<=countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                green=deleteElement(green,temp+1);
                if(countRepeat(red,temp+1)==2){
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp+1)==3){
                    dsideBlast(temp+1);
                }
            }else{
                red.add(temp+1);
                buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
            if(red.contains(temp-6)){
                red.add(temp-6);
                if(countRepeat(red,temp-6)==2){
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-6)==3){
                    lsideBlast(temp-6);
                }
            }else if(green.contains(temp-6)){
                for(int j=0;j<=countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                green=deleteElement(green,temp-6);
                if(countRepeat(red,temp-6)==2){
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-6)==3){
                    lsideBlast(temp-6);
                }
            }else{
                red.add(temp-6);
                buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
        }
        if(i==53){
            int temp=i;
            if(red.contains(temp-1)){
                red.add(temp-1);
                if(countRepeat(red,temp-1)==2){
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-1)==3){
                    dsideBlast(temp-1);
                }
            }else if(green.contains(temp-1)){
                for(int j=0;j<=countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                green=deleteElement(green,temp-1);
                if(countRepeat(red,temp-1)==2){
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-1)==3){
                    dsideBlast(temp-1);
                }
            }else{
                red.add(temp-1);
                buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
            if(red.contains(temp-6)){
                red.add(temp-6);
                if(countRepeat(red,temp-6)==2){
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-6)==3){
                    rsideBlast(temp-6);
                }
            }else if(green.contains(temp-6)){
                for(int j=0;j<=countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                green=deleteElement(green,temp-6);
                if(countRepeat(red,temp-6)==2){
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red2), null, null, null);
                }
                if(countRepeat(red,temp-6)==3){
                    rsideBlast(temp-6);
                }
            }else{
                red.add(temp-6);
                buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
            }
        }
    }
   private void cornerBlastGreen(int i){
       //leaving a blank
       buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
       //removing its existance
       green=deleteElement(green,i);
       if(i==0){
           int temp=i;
           if(green.contains(temp+1)){
               green.add(temp+1);
               if(countRepeat(green,temp+1)==2){
                   buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+1)==3){
                   usideBlast(temp+1);
               }

           }else if(red.contains(temp+1)){
               for(int j=0;j<=countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
               red=deleteElement(red,temp+1);
               if(countRepeat(green,temp+1)==2){
                   buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+1)==3){
                   usideBlast(temp+1);
               }
           }else{
               green.add(temp+1);
               buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
           if(green.contains(temp+6)){
               green.add(temp+6);
               if(countRepeat(green,temp+6)==2){
                   buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+6)==3){
                   lsideBlast(temp+6);
               }
           }else if(red.contains(temp+6)){
               for(int j=0;j<=countRepeat(red,temp+6);j++){green.insertElementAt(temp+6,0);}
               red=deleteElement(red,temp+6);
               if(countRepeat(green,temp+6)==2){
                   buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+6)==3){
                   lsideBlast(temp+6);
               }
           }else{
               green.add(temp+6);
               buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
       }
       if(i==5){
           int temp=i;
           if(green.contains(temp-1)){
               green.add(temp-1);
               if(countRepeat(green,temp-1)==2){
                   buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-1)==3){
                   usideBlast(temp-1);
               }
           }else if(red.contains(temp-1)){
               for(int j=0;j<=countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
               red=deleteElement(red,temp-1);
               if(countRepeat(green,temp-1)==2){
                   buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-1)==3){
                   usideBlast(temp-1);
               }
           }else{
               green.add(temp-1);
               buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
           if(green.contains(temp+6)){
               green.add(temp+6);
               if(countRepeat(green,temp+6)==2){
                   buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+6)==3){
                   rsideBlast(temp+6);
               }
           }else if(red.contains(temp+6)){
               for(int j=0;j<=countRepeat(red,temp+6);j++){green.insertElementAt(temp+6,0);}
               red=deleteElement(red,temp+6);
               if(countRepeat(green,temp+6)==2){
                   buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+6)==3){
                   rsideBlast(temp+6);
               }
           }else{
               green.add(temp+6);
               buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
       }
       if(i==48){
           int temp=i;
           if(green.contains(temp+1)){
               green.add(temp+1);
               if(countRepeat(green,temp+1)==2){
                   buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+1)==3){
                   dsideBlast(temp+1);
               }

           }else if(red.contains(temp+1)){
               for(int j=0;j<=countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
               red=deleteElement(red,temp+1);
               if(countRepeat(green,temp+1)==2){
                   buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp+1)==3){
                   dsideBlast(temp+1);
               }
           }else{
               green.add(temp+1);
               buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
           if(green.contains(temp-6)){
               green.add(temp-6);
               if(countRepeat(green,temp-6)==2){
                   buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-6)==3){
                   lsideBlast(temp-6);
               }
           }else if(red.contains(temp-6)){
               for(int j=0;j<=countRepeat(red,temp-6);j++){green.insertElementAt(temp-6,0);}
               red=deleteElement(red,temp-6);
               if(countRepeat(green,temp-6)==2){
                   buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-6)==3){
                   lsideBlast(temp-6);
               }
           }else{
               green.add(temp-6);
               buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
       }
       if(i==53){
           int temp=i;
           if(green.contains(temp-1)){
               green.add(temp-1);
               if(countRepeat(green,temp-1)==2){
                   buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-1)==3){
                   dsideBlast(temp-1);
               }
           }else if(red.contains(temp-1)){
               for(int j=0;j<=countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
               red=deleteElement(red,temp-1);
               if(countRepeat(green,temp-1)==2){
                   buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-1)==3){
                   dsideBlast(temp-1);
               }
           }else{
               green.add(temp-1);
               buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
           if(green.contains(temp-6)){
               green.add(temp-6);
               if(countRepeat(green,temp-6)==2){
                   buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-6)==3){
                   rsideBlast(temp-6);
               }
           }else if(red.contains(temp-6)){
               for(int j=0;j<=countRepeat(red,temp-6);j++){green.insertElementAt(temp-6,0);}
               red=deleteElement(red,temp-6);
               if(countRepeat(green,temp-6)==2){
                   buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green2), null, null, null);
               }
               if(countRepeat(green,temp-6)==3){
                   rsideBlast(temp-6);
               }
           }else{
               green.add(temp-6);
               buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
           }
       }
   }
    private void usideBlast(int i){
            buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
            if(red.contains(i)){
                red=deleteElement(red,i);
                int temp=i;
                if(corner.contains(temp+1)){
                    if(red.contains(temp+1)){
                        cornerBlastRed(temp+1);
                    }else if (green.contains(temp+1)){
                        green=deleteElement(green,temp+1);
                        red.add(temp+1);
                        cornerBlastRed(temp+1);
                    } else{
                       red.add(temp+1);
                       buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                    }
                    if(red.contains(temp-1)){
                        setRed(temp-1);
                    }else if(green.contains(temp-1)){
                        for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                        green=deleteElement(green,temp-1);
                        setRed(temp-1);
                    }else{
                        red.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                    if(red.contains(temp+6)){
                        setRed(temp+6);
                    }else if(green.contains(temp+6)){
                        for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                        green=deleteElement(green,temp+6);
                        setRed(temp+6);
                    }else{
                        red.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                }else if(corner.contains(temp-1)){
                    if(red.contains(temp-1)){
                        cornerBlastRed(temp-1);
                    }else if (green.contains(temp-1)){
                        green=deleteElement(green,temp-1);
                        red.add(temp-1);
                        cornerBlastRed(temp-1);
                    } else{
                        red.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                    }
                    if(red.contains(temp+1)){
                        setRed(temp+1);
                    }else if(green.contains(temp+1)){
                        for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                        green=deleteElement(green,temp+1);
                        setRed(temp+1);
                    }else{
                        red.add(temp+1);
                        buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                    if(red.contains(temp+6)){
                        setRed(temp+6);
                    }else if(green.contains(temp+6)){
                        for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                        green=deleteElement(green,temp+6);
                        setRed(temp+6);
                    }else{
                        red.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                }else{
                    if(red.contains(temp+1)){
                        setRed(temp+1);
                    }else if(green.contains(temp+1)){
                        for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                        green=deleteElement(green,temp+1);
                        setRed(temp+1);
                    }else{
                        red.add(temp+1);
                        buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                    if(red.contains(temp-1)){
                        setRed(temp-1);
                    }else if(green.contains(temp-1)){
                        for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                        green=deleteElement(green,temp-1);
                        setRed(temp-1);
                    }else{
                        red.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                    if(red.contains(temp+6)){
                        setRed(temp+6);
                    }else if(green.contains(temp+6)){
                        for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                        green=deleteElement(green,temp+6);
                        setRed(temp+6);
                    }else{
                        red.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                    }
                }
            }
            if(green.contains(i)){
                green=deleteElement(green,i);
                int temp=i;
                if(corner.contains(temp+1)){
                    if(green.contains(temp+1)){
                        cornerBlastGreen(temp+1);
                    }else if (red.contains(temp+1)){
                        red=deleteElement(red,temp+1);
                        green.add(temp+1);
                        cornerBlastGreen(temp+1);
                    } else{
                        green.add(temp+1);
                        buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                    }
                    if(green.contains(temp-1)){
                        setGreen(temp-1);
                    }else if(red.contains(temp-1)){
                        for(int j=0;j<countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
                        red=deleteElement(red,temp-1);
                        setGreen(temp-1);
                    }else{
                        green.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                    if(green.contains(temp+6)){
                        setGreen(temp+6);
                    }else if(red.contains(temp+6)){
                        for(int j=0;j<countRepeat(red,temp+6);j++){green.insertElementAt(temp+6,0);}
                        red=deleteElement(red,temp+6);
                        setRed(temp+6);
                    }else{
                        green.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                }else if(corner.contains(temp-1)){
                    if(green.contains(temp-1)){
                        cornerBlastGreen(temp-1);
                    }else if (red.contains(temp-1)){
                        red=deleteElement(red,temp-1);
                        green.add(temp-1);
                        cornerBlastGreen(temp-1);
                    } else{
                        green.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                    }
                    if(green.contains(temp+1)){
                        setGreen(temp+1);
                    }else if(red.contains(temp+1)){
                        for(int j=0;j<countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
                        red=deleteElement(red,temp+1);
                        setGreen(temp+1);
                    }else{
                        green.add(temp+1);
                        buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                    if(green.contains(temp+6)){
                        setGreen(temp+6);
                    }else if(red.contains(temp+6)){
                        for(int j=0;j<countRepeat(red,temp+6);j++){green.insertElementAt(temp+6,0);}
                        red=deleteElement(red,temp+6);
                        setGreen(temp+6);
                    }else{
                        green.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                }else{
                    if(green.contains(temp+1)){
                        setGreen(temp+1);
                    }else if(red.contains(temp+1)){
                        for(int j=0;j<countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
                        red=deleteElement(red,temp+1);
                        setGreen(temp+1);
                    }else{
                        green.add(temp+1);
                        buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                    if(green.contains(temp-1)){
                        setGreen(temp-1);
                    }else if(red.contains(temp-1)){
                        for(int j=0;j<countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
                        red=deleteElement(red,temp-1);
                        setGreen(temp-1);
                    }else{
                        green.add(temp-1);
                        buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                    if(green.contains(temp+6)){
                        setGreen(temp+6);
                    }else if(red.contains(temp+6)){
                        for(int j=0;j<countRepeat(red,temp+6);j++){green.insertElementAt(temp+6,0);}
                        red=deleteElement(red,temp+6);
                        setGreen(temp+6);
                    }else{
                        green.add(temp+6);
                        buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                    }
                }
            }
    }
    private void dsideBlast(int i){
        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        if(red.contains(i)){
            red=deleteElement(red,i);
            int temp=i;
            if(corner.contains(temp+1)){
                if(red.contains(temp+1)){
                    cornerBlastRed(temp+1);
                }else if (green.contains(temp+1)){
                    green=deleteElement(green,temp+1);
                    red.add(temp+1);
                    cornerBlastRed(temp+1);
                } else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp-1)){
                    setRed(temp-1);
                }else if(green.contains(temp-1)){
                    for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                    green=deleteElement(green,temp-1);
                    setRed(temp-1);
                }else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else if(corner.contains(temp-1)){
                if(red.contains(temp-1)){
                    cornerBlastRed(temp-1);
                }else if (green.contains(temp-1)){
                    green=deleteElement(green,temp-1);
                    red.add(temp-1);
                    cornerBlastRed(temp-1);
                } else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp+1)){
                    setRed(temp+1);
                }else if(green.contains(temp+1)){
                    for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                    green=deleteElement(green,temp+1);
                    setRed(temp+1);
                }else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else{
                if(red.contains(temp+1)){
                    setRed(temp+1);
                }else if(green.contains(temp+1)){
                    for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                    green=deleteElement(green,temp+1);
                    setRed(temp+1);
                }else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-1)){
                    setRed(temp-1);
                }else if(green.contains(temp-1)){
                    for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                    green=deleteElement(green,temp-1);
                    setRed(temp-1);
                }else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }
        }
        if(green.contains(i)){
            green=deleteElement(green,i);
            int temp=i;
            if(corner.contains(temp+1)){
                if(green.contains(temp+1)){
                    cornerBlastGreen(temp+1);
                }else if (red.contains(temp+1)){
                    red=deleteElement(red,temp+1);
                    green.add(temp+1);
                    cornerBlastGreen(temp+1);
                } else{
                    green.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if(green.contains(temp-1)){
                    setGreen(temp-1);
                }else if(red.contains(temp-1)){
                    for(int j=0;j<countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
                    red=deleteElement(red,temp-1);
                    setGreen(temp-1);
                }else{
                    green.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
                if(green.contains(temp-6)){
                    setGreen(temp-6);
                }else if(red.contains(temp-6)){
                    for(int j=0;j<countRepeat(red,temp-6);j++){green.insertElementAt(temp-6,0);}
                    red=deleteElement(red,temp-6);
                    setRed(temp-6);
                }else{
                    green.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
            }else if(corner.contains(temp-1)){
                if(green.contains(temp-1)){
                    cornerBlastGreen(temp-1);
                }else if (red.contains(temp-1)){
                    red=deleteElement(red,temp-1);
                    green.add(temp-1);
                    cornerBlastGreen(temp-1);
                } else{
                    green.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if(green.contains(temp+1)){
                    setGreen(temp+1);
                }else if(red.contains(temp+1)){
                    for(int j=0;j<countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
                    red=deleteElement(red,temp+1);
                    setGreen(temp+1);
                }else{
                    green.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
                if(green.contains(temp-6)){
                    setGreen(temp-6);
                }else if(red.contains(temp-6)){
                    for(int j=0;j<countRepeat(red,temp-6);j++){green.insertElementAt(temp-6,0);}
                    red=deleteElement(red,temp-6);
                    setGreen(temp-6);
                }else{
                    green.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
            }else{
                if(green.contains(temp+1)){
                    setGreen(temp+1);
                }else if(red.contains(temp+1)){
                    for(int j=0;j<countRepeat(red,temp+1);j++){green.insertElementAt(temp+1,0);}
                    red=deleteElement(red,temp+1);
                    setGreen(temp+1);
                }else{
                    green.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
                if(green.contains(temp-1)){
                    setGreen(temp-1);
                }else if(red.contains(temp-1)){
                    for(int j=0;j<countRepeat(red,temp-1);j++){green.insertElementAt(temp-1,0);}
                    red=deleteElement(red,temp-1);
                    setGreen(temp-1);
                }else{
                    green.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
                if(green.contains(temp-6)){
                    setGreen(temp-6);
                }else if(red.contains(temp-6)){
                    for(int j=0;j<countRepeat(red,temp-6);j++){green.insertElementAt(temp-6,0);}
                    red=deleteElement(red,temp-6);
                    setGreen(temp-6);
                }else{
                    green.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green),null,null,null);
                }
            }
        }
    }
    private void lsideBlast(int i){
        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        if(red.contains(i)){
            red=deleteElement(red,i);
            int temp=i;
            if(corner.contains(temp+6)){
                if(red.contains(temp+6)){
                    cornerBlastRed(temp+6);
                }else if (green.contains(temp+6)){
                    green=deleteElement(green,temp+6);
                    red.add(temp+6);
                    cornerBlastRed(temp+6);
                } else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp+1)){
                    setRed(temp+1);
                }else if(green.contains(temp+1)){
                    for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                    green=deleteElement(green,temp+1);
                    setRed(temp+1);
                }else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else if(corner.contains(temp-6)){
                if(red.contains(temp-6)){
                    cornerBlastRed(temp-6);
                }else if (green.contains(temp-6)){
                    green=deleteElement(green,temp-6);
                    red.add(temp-6);
                    cornerBlastRed(temp-6);
                } else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp+6)){
                    setRed(temp+6);
                }else if(green.contains(temp+6)){
                    for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                    green=deleteElement(green,temp+6);
                    setRed(temp+6);
                }else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp+1)){
                    setRed(temp+1);
                }else if(green.contains(temp+1)){
                    for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                    green=deleteElement(green,temp+1);
                    setRed(temp+1);
                }else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else{
                if(red.contains(temp+1)){
                    setRed(temp+1);
                }else if(green.contains(temp+1)){
                    for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                    green=deleteElement(green,temp+1);
                    setRed(temp+1);
                }else{
                    red.add(temp+1);
                    buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp+6)){
                    setRed(temp+6);
                }else if(green.contains(temp+6)){
                    for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                    green=deleteElement(green,temp+6);
                    setRed(temp+6);
                }else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }
        }
        if(green.contains(i)) {
            green = deleteElement(green, i);
            int temp = i;
            if (corner.contains(temp + 6)) {
                if (green.contains(temp + 6)) {
                    cornerBlastGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    red = deleteElement(red, temp + 6);
                    green.add(temp + 6);
                    cornerBlastGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp - 6)) {
                    setGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    for (int j = 0; j < countRepeat(red, temp - 6); j++) {
                        green.insertElementAt(temp - 6, 0);
                    }
                    red = deleteElement(red, temp - 6);
                    setGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 1)) {
                    setGreen(temp + 1);
                } else if (red.contains(temp + 1)) {
                    for (int j = 0; j < countRepeat(red, temp + 1); j++) {
                        green.insertElementAt(temp + 1, 0);
                    }
                    red = deleteElement(red, temp + 1);
                    setGreen(temp + 1);
                } else {
                    green.add(temp + 1);
                    buttons[temp + 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            } else if (corner.contains(temp - 6)) {
                if (green.contains(temp - 6)) {
                    cornerBlastGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    red = deleteElement(red, temp - 6);
                    green.add(temp - 6);
                    cornerBlastGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 6)) {
                    setGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    for (int j = 0; j < countRepeat(red, temp + 6); j++) {
                        green.insertElementAt(temp + 6, 0);
                    }
                    red = deleteElement(red, temp + 6);
                    setGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 1)) {
                    setGreen(temp + 1);
                } else if (red.contains(temp + 1)) {
                    for (int j = 0; j < countRepeat(red, temp + 1); j++) {
                        green.insertElementAt(temp + 1, 0);
                    }
                    red = deleteElement(red, temp + 1);
                    setGreen(temp + 1);
                } else {
                    green.add(temp + 1);
                    buttons[temp + 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            } else {
                if (green.contains(temp + 1)) {
                    setGreen(temp + 1);
                } else if (red.contains(temp + 1)) {
                    for (int j = 0; j < countRepeat(red, temp + 1); j++) {
                        green.insertElementAt(temp + 1, 0);
                    }
                    red = deleteElement(red, temp + 1);
                    setGreen(temp + 1);
                } else {
                    green.add(temp + 1);
                    buttons[temp + 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp - 6)) {
                    setGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    for (int j = 0; j < countRepeat(red, temp - 6); j++) {
                        green.insertElementAt(temp - 6, 0);
                    }
                    red = deleteElement(red, temp - 6);
                    setGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 6)) {
                    setGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    for (int j = 0; j < countRepeat(red, temp + 6); j++) {
                        green.insertElementAt(temp + 6, 0);
                    }
                    red = deleteElement(red, temp + 6);
                    setGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            }
        }
    }
    private void rsideBlast(int i){
        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        if(red.contains(i)){
            red=deleteElement(red,i);
            int temp=i;
            if(corner.contains(temp+6)){
                if(red.contains(temp+6)){
                    cornerBlastRed(temp+6);
                }else if (green.contains(temp+6)){
                    green=deleteElement(green,temp+6);
                    red.add(temp+6);
                    cornerBlastRed(temp+6);
                } else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-1)){
                    setRed(temp-1);
                }else if(green.contains(temp-1)){
                    for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                    green=deleteElement(green,temp-1);
                    setRed(temp-1);
                }else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else if(corner.contains(temp-6)){
                if(red.contains(temp-6)){
                    cornerBlastRed(temp-6);
                }else if (green.contains(temp-6)){
                    green=deleteElement(green,temp-6);
                    red.add(temp-6);
                    cornerBlastRed(temp-6);
                } else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red), null, null, null);
                }
                if(red.contains(temp+6)){
                    setRed(temp+6);
                }else if(green.contains(temp+6)){
                    for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                    green=deleteElement(green,temp+6);
                    setRed(temp+6);
                }else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-1)){
                    setRed(temp-1);
                }else if(green.contains(temp-1)){
                    for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                    green=deleteElement(green,temp-1);
                    setRed(temp-1);
                }else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }else{
                if(red.contains(temp-1)){
                    setRed(temp-1);
                }else if(green.contains(temp-1)){
                    for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                    green=deleteElement(green,temp-1);
                    setRed(temp-1);
                }else{
                    red.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp-6)){
                    setRed(temp-6);
                }else if(green.contains(temp-6)){
                    for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                    green=deleteElement(green,temp-6);
                    setRed(temp-6);
                }else{
                    red.add(temp-6);
                    buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
                if(red.contains(temp+6)){
                    setRed(temp+6);
                }else if(green.contains(temp+6)){
                    for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                    green=deleteElement(green,temp+6);
                    setRed(temp+6);
                }else{
                    red.add(temp+6);
                    buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
                }
            }
        }
        if(green.contains(i)) {
            green = deleteElement(green, i);
            int temp = i;
            if (corner.contains(temp + 6)) {
                if (green.contains(temp + 6)) {
                    cornerBlastGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    red = deleteElement(red, temp + 6);
                    green.add(temp + 6);
                    cornerBlastGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp - 6)) {
                    setGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    for (int j = 0; j < countRepeat(red, temp - 6); j++) {
                        green.insertElementAt(temp - 6, 0);
                    }
                    red = deleteElement(red, temp - 6);
                    setGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp-1)) {
                    setGreen(temp-1);
                } else if (red.contains(temp-1)) {
                    for (int j = 0; j < countRepeat(red, temp-1); j++) {
                        green.insertElementAt(temp-1, 0);
                    }
                    red = deleteElement(red, temp - 1);
                    setGreen(temp - 1);
                } else {
                    green.add(temp-1);
                    buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            } else if (corner.contains(temp - 6)) {
                if (green.contains(temp - 6)) {
                    cornerBlastGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    red = deleteElement(red, temp - 6);
                    green.add(temp - 6);
                    cornerBlastGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 6)) {
                    setGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    for (int j = 0; j < countRepeat(red, temp + 6); j++) {
                        green.insertElementAt(temp + 6, 0);
                    }
                    red = deleteElement(red, temp + 6);
                    setGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp - 1)) {
                    setGreen(temp - 1);
                } else if (red.contains(temp - 1)) {
                    for (int j = 0; j < countRepeat(red, temp - 1); j++) {
                        green.insertElementAt(temp - 1, 0);
                    }
                    red = deleteElement(red, temp - 1);
                    setGreen(temp - 1);
                } else {
                    green.add(temp - 1);
                    buttons[temp - 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            } else {
                if (green.contains(temp - 1)) {
                    setGreen(temp - 1);
                } else if (red.contains(temp - 1)) {
                    for (int j = 0; j < countRepeat(red, temp - 1); j++) {
                        green.insertElementAt(temp - 1, 0);
                    }
                    red = deleteElement(red, temp - 1);
                    setGreen(temp - 1);
                } else {
                    green.add(temp - 1);
                    buttons[temp - 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp - 6)) {
                    setGreen(temp - 6);
                } else if (red.contains(temp - 6)) {
                    for (int j = 0; j < countRepeat(red, temp - 6); j++) {
                        green.insertElementAt(temp - 6, 0);
                    }
                    red = deleteElement(red, temp - 6);
                    setGreen(temp - 6);
                } else {
                    green.add(temp - 6);
                    buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
                if (green.contains(temp + 6)) {
                    setGreen(temp + 6);
                } else if (red.contains(temp + 6)) {
                    for (int j = 0; j < countRepeat(red, temp + 6); j++) {
                        green.insertElementAt(temp + 6, 0);
                    }
                    red = deleteElement(red, temp + 6);
                    setGreen(temp + 6);
                } else {
                    green.add(temp + 6);
                    buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
                }
            }
        }
    }
    private void midBlast(int i){
        buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        if(red.contains(i)){
            red = deleteElement(red, i);
            int temp = i;
            if(red.contains(temp-1)){
                setRed(temp-1);
            }else if(green.contains(temp-1)){
                for(int j=0;j<countRepeat(green,temp-1);j++){red.insertElementAt(temp-1,0);}
                green=deleteElement(green,temp-1);
                setRed(temp-1);
            }else{
                red.add(temp-1);
                buttons[temp-1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
            }
            if(red.contains(temp+1)){
                setRed(temp+1);
            }else if(green.contains(temp+1)){
                for(int j=0;j<countRepeat(green,temp+1);j++){red.insertElementAt(temp+1,0);}
                green=deleteElement(green,temp+1);
                setRed(temp+1);
            }else{
                red.add(temp+1);
                buttons[temp+1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
            }
            if(red.contains(temp-6)){
                setRed(temp-6);
            }else if(green.contains(temp-6)){
                for(int j=0;j<countRepeat(green,temp-6);j++){red.insertElementAt(temp-6,0);}
                green=deleteElement(green,temp-6);
                setRed(temp-6);
            }else{
                red.add(temp-6);
                buttons[temp-6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
            }
            if(red.contains(temp+6)){
                setRed(temp+6);
            }else if(green.contains(temp+6)){
                for(int j=0;j<countRepeat(green,temp+6);j++){red.insertElementAt(temp+6,0);}
                green=deleteElement(green,temp+6);
                setRed(temp+6);
            }else{
                red.add(temp+6);
                buttons[temp+6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.red),null,null,null);
            }
        }
        if(green.contains(i)){
            green = deleteElement(green, i);
            int temp = i;
            if (green.contains(temp - 1)) {
                setGreen(temp - 1);
            } else if (red.contains(temp - 1)) {
                for (int j = 0; j < countRepeat(red, temp - 1); j++) {
                    green.insertElementAt(temp - 1, 0);
                }
                red = deleteElement(red, temp - 1);
                setGreen(temp - 1);
            } else {
                green.add(temp - 1);
                buttons[temp - 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
            }
            if (green.contains(temp + 1)) {
                setGreen(temp + 1);
            } else if (red.contains(temp + 1)) {
                for (int j = 0; j < countRepeat(red, temp + 1); j++) {
                    green.insertElementAt(temp + 1, 0);
                }
                red = deleteElement(red, temp - 1);
                setGreen(temp + 1);
            } else {
                green.add(temp + 1);
                buttons[temp + 1].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
            }
            if (green.contains(temp - 6)) {
                setGreen(temp - 6);
            } else if (red.contains(temp - 6)) {
                for (int j = 0; j < countRepeat(red, temp - 6); j++) {
                    green.insertElementAt(temp - 6, 0);
                }
                red = deleteElement(red, temp - 6);
                setGreen(temp - 6);
            } else {
                green.add(temp - 6);
                buttons[temp - 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
            }
            if (green.contains(temp + 6)) {
                setGreen(temp + 6);
            } else if (red.contains(temp + 6)) {
                for (int j = 0; j < countRepeat(red, temp + 6); j++) {
                    green.insertElementAt(temp + 6, 0);
                }
                red = deleteElement(red, temp + 6);
                setGreen(temp + 6);
            } else {
                green.add(temp + 6);
                buttons[temp + 6].setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.green), null, null, null);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.undo:
                Toast.makeText(this, "Undo is done", Toast.LENGTH_SHORT).show();
                buttons[index].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                if(count%2==0){if(green.contains(index)){green.removeElement(index);}}
                if(count%2!=0){if(red.contains(index)){red.removeElement(index);}}
                count--;
                return true;
            case R.id.newGame:
                for(int i=0;i<buttons.length;i++){
                    buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    buttons[i].setBackground(getResources().getDrawable(R.drawable.button_border));
                    count=0;
                }
                red.removeAllElements();
                green.removeAllElements();
                Toast.makeText(this, "New Game Started", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "Go to settings", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);}
    }*/
}

