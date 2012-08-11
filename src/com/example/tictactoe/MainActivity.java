package com.example.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean isX;
    private int moveCounter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isX = true;
        moveCounter = 0;
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
    	moveCounter++;
    	String text = "";
    	if(isX){
    		isX = false;
    		text = "X";
    	}else{
    		isX = true;
    		text = "O";
    	}
    	
    	button.setText(text);
    	
    	//Now check Win
    	
    	//Should'nt get here if there's a winner
    	if(moveCounter == 9){
    		Toast toast = Toast.makeText(this, "No one wins...", Toast.LENGTH_LONG);
    		toast.setGravity(Gravity.TOP|Gravity.CENTER_VERTICAL, 0, 0);
    		toast.show(); 

    	}
    }
}
