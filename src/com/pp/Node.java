package com.pp;

public class Node {
	
	String prevBoard;
	
	// 'L'-Left, 'R'- Right , 'T'-Top, 'B'- Bottom, 'I' - Initial
	char theMove;
	
	String currentBoard;
	int f;//f(n)
	
	//Stores the number of moves to g
	int g;//g(n)
	
	//Stores the heuristics
	int h;//h(n) = 0 for DFS
	
	Result Result;
	
	@Override 
	public int hashCode() {
		
		return currentBoard.hashCode();
	};
	

	//Previous Board is null for the initial condition
	public Node(String currentBoard, Result result, String currentBoard2,int g,char move) {
		super();
		this.currentBoard = currentBoard;
		Result = result;
		this.prevBoard = currentBoard2;
		this.g = g;
		this.theMove = move;
	}
	
	public Node(String currentBoard, Result result, String prevBoard,int g,int h) {
		super();
		this.currentBoard = currentBoard;
		Result = result;
		this.prevBoard = prevBoard;
		this.g = g;
		this.h = h;
	}
	
	public int getF() {
		return this.g + this.h;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	public char getTheMove() {
		return theMove;
	}

	public void setTheMove(char theMove) {
		this.theMove = theMove;
	}
}
