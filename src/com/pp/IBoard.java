package com.pp;

public interface IBoard {
	
	final char UNUSABLE = '_';
	
	final char HASPEG = 'X';
	
	final char EMPTY = 'O';
	
	// Row from 0 to 6
	final int NO_OF_ROWS = 7;
	
	//Column from 0 to 6
	final int NO_OF_COLUMNS = 7;

	
	
	void setBoard(char[] array);
	
	Boolean isEmpty(int x,int y);
	
	Boolean hasPeg(int x,int y);
	
	Boolean isUnusable(int x,int y);
	
	
	char getCellValue(int x,int y);
	
	public Boolean BottomMove(int x,int y);
	
	public Boolean TopMove(int x,int y);
	
	public Boolean LeftMove(int x,int y);
	
	public Boolean RightMove(int x,int y);
	
	public Result makeBottomMove(int x,int y);
	
	public Result makeTopMove(int x,int y);
	
	public Result makeLeftMove(int x,int y);
	
	public Result makeRightMove(int x,int y);
	
	public void unmakeBottomMove(int x,int y);
	
	public void unmakeTopMove(int x,int y);
	
	public void unmakeLeftMove(int x,int y);
	
	public void unmakeRightMove(int x,int y);
	public int getHn();
	
	
	public String createString();
	
	Boolean checkGoal();

	void Update(int x, int y, char value);
	
	int returnSerializedIndex(int x,int y);

	void setBoard(String array);
	

}
