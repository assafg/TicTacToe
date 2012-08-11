package com.example.tictactoe;

import com.example.tictactoe.game.Game;
import com.example.tictactoe.game.Game.Side;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity {

	private boolean isX;
    private int moveCounter;
    private Side side;
    private Game game;
    private String player1, player2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newGame();
        player1 = getIntent().getExtras().getString("player1");
        player2 = getIntent().getExtras().getString("player2");
        Toast toast = Toast.makeText(this, player1+"'s turn", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void handleClick(View view){
    	Button button = (Button) view;
    	if(!button.getText().toString().equals("")){
    		//Place taken
    		return;
    	}
    	boolean isWinnerMove;
		try {
			isWinnerMove = game.moveMade(side, getPos(view.getId()));
		} catch (Exception e) {
			//Place taken
    		return;
		}
		moveCounter++;
    	
    	String text = "";
    	if(isX){
    		isX = false;
    		side = Side.X;
    		text = side.toString();
    	}else{
    		isX = true;
    		side = Side.O;
    		text = side.toString();
    		
    	}
    	button.setText(text);
    	
    	//Now check Win 
    	if(isWinnerMove){
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle("We have a winner!");
    		alertDialog.setMessage(side.toString()+" wins the game!");
    		setDialogButtons(alertDialog);
    		alertDialog.show();
    		return;
    	}
    	
    	//Switsh sides
    	if(side == Side.X){
    		side = Side.O;
    	}else{
    		side = Side.X;
    	}
    	
    	//Should'nt get here if there's a winner
    	if(moveCounter == 9){
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle("We have a tie!");
    		alertDialog.setMessage("You're both extremely good!");
    		setDialogButtons(alertDialog);
    		alertDialog.show();
    		return;

    	}
    }

	private void setDialogButtons(AlertDialog alertDialog) {
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Play Again", new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				newGame();
			}
		});
		alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Quit", new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				backToMainMenu();
			}
		});
	}
    
    private void newGame(){
    	setContentView(R.layout.activity_game);
    	game = new Game();
    	side = Side.X;
    	isX = true;
    	moveCounter = 0;
    }

    private void backToMainMenu(){
    	
    }
    
	private int[] getPos(int id) {
		switch (id) {
		case R.id.button1:
			return new int[]{0,0};
		case R.id.button2:
			return new int[]{0,1};
		case R.id.button3:
			return new int[]{0,2};
		case R.id.button4:
			return new int[]{1,0};
		case R.id.button5:
			return new int[]{1,1};
		case R.id.button6:
			return new int[]{1,2};
		case R.id.button7:
			return new int[]{2,0};
		case R.id.button8:
			return new int[]{2,1};
		case R.id.button9:
			return new int[]{2,2};
		}
		//Should never happen
		return new int[]{0,0};
	}
}
