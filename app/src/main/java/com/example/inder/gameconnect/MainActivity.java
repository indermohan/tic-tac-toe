package com.example.inder.gameconnect;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // 0 is yellow and 1 is red

    int activeplayer = 0;
    boolean gameisactive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningposi = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropin(View view) {
        ImageView counter = (ImageView) view;//tap on image
        TextView tx = (TextView) findViewById(R.id.textView);
        tx.setText("Player 1");

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameisactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).setDuration(300);
                activeplayer = 1;
                tx.setText("Player 2");
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).setDuration(300);
                activeplayer = 0;
                tx.setText("Player 1");
            }
            for (int[] winningposi : winningposi) {
                if (gamestate[winningposi[0]] == gamestate[winningposi[1]] && gamestate[winningposi[1]] == gamestate[winningposi[2]] && gamestate[winningposi[0]] != 2) {
                    String winner = "Player 2";
                    gameisactive = false;
                    if (gamestate[winningposi[0]] == 0) {
                        winner = "Player 1";
                    }
                    LinearLayout ln = (LinearLayout) findViewById(R.id.Finish);
                    ln.setVisibility(view.VISIBLE);
                    TextView win = (TextView) findViewById(R.id.textView2);
                    win.setText(winner + " Has Won ");
                    tx.setText("Player 1");


                }
                else
                    {
                    boolean gameisover = true;
                    for (int countertstate : gamestate) {
                        if (countertstate == 2) {
                            gameisover = false;
                        }
                    }
                        if (gameisover) {
                            LinearLayout ln = (LinearLayout) findViewById(R.id.Finish);
                            ln.setVisibility(view.VISIBLE);
                            TextView win = (TextView) findViewById(R.id.textView2);
                            win.setText(" Its a Draw ");

                            tx.setText("Player 1");
                        }
                    }
                }

            }
        }
        public void playagain(View view)
        {

            gameisactive=true;
        LinearLayout ln = (LinearLayout)findViewById(R.id.Finish);
        ln.setVisibility(view.INVISIBLE);
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
            GridLayout gd = (GridLayout)findViewById(R.id.gridLayout);
            for(int i=0;i<gd.getChildCount();i++)
            {
                ((ImageView)gd.getChildAt(i)).setImageResource(0);
            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
