package com.example.tictactoe.game;

public class Game {

	Side[][] board = new Side[3][3];
	public enum Side{
		X,
		O
	}
	public Game(){
	}
	
	public boolean moveMade(Side side, int[] pos) throws Exception{
		if(board[pos[0]][pos[1]]!=null){
			throw new Exception("Illeagal move");
		}
		
		board[pos[0]][pos[1]] = side;
		return checkWin();
	}

	private boolean checkWin() {
		//Check diagonals
		if(board[0][0]!=null && 
				(board[0][0]==board[1][1]
						&& board[1][1] == board[2][2])){
			return true;
		}
		if(board[0][2]!=null && 
				(board[0][2]==board[1][1]
						&& board[1][1] == board[2][0])){
			return true;
		}
		for(int i=0;i<3;i++){
			if(board[i][0]!=null && 
					(board[i][0]==board[i][1]
							&& board[i][1] == board[i][2])){
				return true;
			}
		}
		for(int i=0;i<3;i++){
			if(board[0][i]!=null && 
					(board[0][i]==board[1][i]
							&& board[1][i] == board[2][i])){
				return true;
			}
		}
		return false;
	}
}
