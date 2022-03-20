package com.example.rivera_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView character,enemy,characterhp,enemyhp,characterdmg,enemydmg,charactermana,enemymana,combatlog, enemyhptxt, characterhptxt;
    Button nextturn;
    ImageButton firstskill,secondskill,thirdskill;
    ProgressBar healthbarcharacter,healthbarenemy;

    String Character ="Knight";
    int Characterhp =250;
    int Charactermana =200;
    int characterminimum =5;
    int charactermax = 40;
    int turnNumber =1;
    int characterhpercent;



    String Enemy ="Lava Lizard";
    int Enemyhp =175;
    int Enemymana =180;
    int enemyminimum =15;
    int enemymax =50;
    int enemyhppercent;
    Boolean disablestatus =false;
    int statuscounter =0;
    int buttoncounter =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        character =findViewById(R.id.character);
        combatlog =findViewById(R.id.combatlog);
        enemy =findViewById(R.id.enemy);
        characterhp =findViewById(R.id.characterhp);
        enemyhp =findViewById(R.id.enemyhp);
        characterdmg =findViewById(R.id.damage1);
        enemydmg =findViewById(R.id.damage2);
        nextturn =findViewById(R.id.turn_button);

        character.setText(Character);
        characterhp.setText(String.valueOf(characterhp));
        charactermana.setText(String.valueOf(charactermana));

        enemy.setText(Enemy);
        enemyhp.setText(String.valueOf(enemyhp));
        enemymana.setText(String.valueOf(enemymana));

        characterdmg.setText(String.valueOf(characterminimum) + " -" + String.valueOf(charactermax));
        enemydmg.setText(String.valueOf(enemyminimum) + " -" + String.valueOf(enemymax));

        firstskill =findViewById(R.id.skillone);
        secondskill =findViewById(R.id.skilltwo);
        thirdskill =findViewById(R.id.skillthree);

        nextturn.setOnClickListener(this);
        firstskill.setOnClickListener(this);
        secondskill.setOnClickListener(this);
        thirdskill.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Random randomizer =new Random();
        int characterdmg = randomizer.nextInt(charactermax - characterminimum) + charactermax;
        int monsdps = randomizer.nextInt(enemymax - enemyminimum) + enemymax;


        switch (v.getId()) {
            case R.id.skillone:
                enemyhp = enemyhp - 18;
                enemyhppercent= enemyhp * 100 / 4000;
                healthbarenemy.setProgress((int) enemyhppercent, true);
                turnNumber++;
                enemyhptxt.setText(String.valueOf(enemyhp));
                nextturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                combatlog.setText(" Our Knight " + String.valueOf(Character) + " used slow!. It dealt " + String.valueOf(270) + " to the monster. The monster has now slow effect");
                disablestatus = true;
                statuscounter = 1;

                if (enemyhp < 0) {
                    combatlog.setText(" Our Knight " + String.valueOf(Character) + " dealt " + String.valueOf(characterdmg) + " to the monster. The Hero has won victorious!.");
                    characterhp = 250;
                    enemyhp = 175;
                    turnNumber = 1;
                    nextturn.setText("Reset Game");
            case R.id.turn_button:
                //
                if (turnNumber % 2 == 1) { //add
                    Enemyhp = Enemyhp - characterdmg;
                    turnNumber++;
                    enemyhp.setText(String.valueOf(enemyhp));
                    nextturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    combatlog.setText("Our Knight " + String.valueOf(character) + " dealt " + String.valueOf(characterdmg) + " to the enemy.");

                    if (Enemyhp < 0) {
                        combatlog.setText("Our Knight " + String.valueOf(character) + " dealt " + String.valueOf(characterdmg) + " to the enemy. The Knight is proven to be Stronger!.");
                        Characterhp = 250;
                        Enemyhp= 175;
                        turnNumber = 1;
                        nextturn.setText("Reset Game");


                    }


                } else if (turnNumber % 2 != 1) {
                    Characterhp = Characterhp - monsdps;
                    turnNumber++;
                    characterhp.setText(String.valueOf(characterhp));
                    nextturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    combatlog.setText("The monster " + String.valueOf(enemy) + " dealt " + String.valueOf(enemydmg) + " to the hero.");
                    if (Characterhp < 0) {

                        combatlog.setText("The Monster " + String.valueOf(enemy) + " dealt " + String.valueOf(enemydmg) + " to the monster. Game Over.");
                        Characterhp = 1200;
                        Enemyhp = 4000;
                        turnNumber = 1;
                        nextturn.setText("Reset Game");



                        break;


                    }


                }


        }
    }
}