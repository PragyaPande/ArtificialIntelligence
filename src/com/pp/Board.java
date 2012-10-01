package com.pp;

public class Board implements IBoard {
	
	char[][] theBoard;
	int noOfpegs;
	static int[] pegholderId = null;
	int noofMoves;
	public Board(String p_Board,int[] pegholderId,int f) {
		super();
		this.theBoard = new char[NO_OF_ROWS][NO_OF_COLUMNS];
		noOfpegs = 0;
		initUnusableCells();
		setBoard(p_Board);
		Board.pegholderId = pegholderId;
		this.noofMoves = f;
	}

	
	//Update Value of a cell
	@Override
	public void Update(int x, int y,char value) {
		theBoard[x][y] = value;
		
	}

	//Initialize the Array
	@Override
	public void setBoard(String array) {
		int row,column;
		for(int i = 0 ; i < array.length() ; i++){
			
			if(array.charAt(i) == -1)
				continue;
			 row = i / 7;
			 column = i % 7;
			 theBoard[row][column] = array.charAt(i);
			 
			 if(array.charAt(i) == HASPEG)
				 noOfpegs++;
		}
	}
	
	//Convert the 2-D Board into a 1-D string
	public String createString(){
		
		char[] currentArray = new char[NO_OF_ROWS * NO_OF_COLUMNS];
		int index = 0;
		for(int i = 0; i < IBoard.NO_OF_ROWS; i ++)
			for(int j = 0 ; j < IBoard.NO_OF_COLUMNS; j++)
				currentArray[index++] = theBoard[i][j];
				
		
		return new String(currentArray);
		
	}

	/*
	 * Set the unusable cells
	 */
	private void initUnusableCells() {
		
		//Top-Left
		for(int i = 0; i < 2 ; i++)
			for(int j = 0; i < 2 ; i++)
				theBoard[i][j] = UNUSABLE;
		
		//Top-Right
		for(int i = 0; i < 2 ; i++)
			for(int j = 5; i < 7 ; i++)
				theBoard[i][j] = UNUSABLE;
		
		//Bottom-Left
		for(int i = 5; i < 7 ; i++)
			for(int j = 0; i < 2 ; i++)
				theBoard[i][j] = UNUSABLE;
		
		//Bottom-Right
		for(int i = 5; i < 7 ; i++)
			for(int j = 5; i < 7 ; i++)
				theBoard[i][j] = UNUSABLE;
		
	}

	@Override
	public char getCellValue(int x, int y) {
		
		assert(x < NO_OF_ROWS && y < NO_OF_COLUMNS && x > 0 && y > 0);
		
		return theBoard[x][y];
	}

	@Override
	public Boolean hasPeg(int x, int y) {
		
		if(getCellValue(x, y) == HASPEG)
			return true;
		else
			return false;
	}

	@Override
	public Boolean isEmpty(int x, int y) {
		if(getCellValue(x, y) == EMPTY)
			return true;
		else
			return false;
	}

	@Override
	public Boolean isUnusable(int x, int y) {
		if(getCellValue(x, y) == UNUSABLE)
			return true;
		else
			return false;
	}

	public char[][] getTheBoard() {
		return theBoard;
	}

	public void setTheBoard(char[][] theBoard) {
		this.theBoard = theBoard;
	}

	@Override
	public Boolean LeftMove(int x, int y) {
		// TODO Auto-generated method stub
		
		if((y-2 < 0) || !hasPeg(x, y) || isUnusable(x, y-2)|| isUnusable(x, y-1)){
			return false;
		}
		if(hasPeg(x, y-1) && isEmpty(x, y-2)){
			return true;
		}
		return false;
	}


	@Override
	public Boolean RightMove(int x, int y) {
		// TODO Auto-generated method stub
		
		if((y+2 >= IBoard.NO_OF_COLUMNS) || !hasPeg(x, y) || isUnusable(x, y+2) || isUnusable(x, y+1) ){
			return false;
		}
		
		if(hasPeg(x, y+1) && isEmpty(x, y+2)){
			return true;
		}
		return false;
	}


	@Override
	public Boolean TopMove(int x, int y) {
		// TODO Auto-generated method stub
		
		if((x-2 < 0)||!(hasPeg(x, y))|| isUnusable(x-2, y) || isUnusable(x-1, y)){
			return false;
		}
		
		if(hasPeg(x-1, y) && isEmpty(x-2, y))
			return true;
		
		return false;
	}
	@Override
	public Boolean BottomMove(int x,int y){
		// TODO Auto-generated method stub
		
		if( (x+2 >= IBoard.NO_OF_ROWS) || !hasPeg(x, y) || isUnusable(x+2, y) || isUnusable(x+1, y)) {
			return false;
		}
		
		if(hasPeg(x+1, y) && isEmpty(x+2, y))
			return true;
		
		return false;
		
	}

	public int getNoOfpegs() {
		return noOfpegs;
	}

	public void setNoOfpegs(int noOfpegs) {
		this.noOfpegs = noOfpegs;
	}

	@Override
	public Boolean checkGoal() {
		
		if(noOfpegs == 1 && hasPeg(NO_OF_COLUMNS/2, NO_OF_ROWS/2))
			return true;
		
		return false;
	}


	@Override
	public int returnSerializedIndex(int x, int y) {
		// TODO Auto-generated method stub
		assert(x < NO_OF_ROWS && y < NO_OF_COLUMNS);
		int val = x*7+y;
		return pegholderId[val];
	}


	@Override
	public Result makeBottomMove(int x, int y) {
		// TODO Auto-generated method stub
		this.noofMoves ++;
		this.theBoard[x][y]= EMPTY;
		this.theBoard[x+1][y]=EMPTY;
		this.theBoard[x+2][y]= HASPEG;
		return new Result(returnSerializedIndex(x,y),returnSerializedIndex(x+2,y));
	}


	@Override
	public Result makeLeftMove(int x, int y) {
		// TODO Auto-generated method stub
		this.noofMoves ++;
		this.theBoard[x][y] = EMPTY;
		this.theBoard[x][y-1]=EMPTY;
		this.theBoard[x][y-2] = HASPEG;
		return new Result(returnSerializedIndex(x,y),returnSerializedIndex(x,y-2));
		
	}


	@Override
	public Result makeRightMove(int x, int y) {
		// TODO Auto-generated method stub
		this.noofMoves ++;
		this.theBoard[x][y] = EMPTY;
		this.theBoard[x][y+1]= EMPTY;
		this.theBoard[x][y+2] = HASPEG;
		return new Result(returnSerializedIndex(x,y),returnSerializedIndex(x,y+2));
	}


	@Override
	public Result makeTopMove(int x, int y) {
		// TODO Auto-generated method stub
		this.noofMoves ++;
		this.theBoard[x][y] = EMPTY;
		this.theBoard[x-1][y] = EMPTY;
		this.theBoard[x-2][y] = HASPEG;
		return new Result(returnSerializedIndex(x,y),returnSerializedIndex(x-2,y));
	}


	@Override
	public void unmakeBottomMove(int x, int y) {
		// TODO Auto-generated method stub
		this.theBoard[x][y]= HASPEG ;
		this.theBoard[x+1][y]=HASPEG;
		this.theBoard[x+2][y]= EMPTY;
		this.noofMoves --;
	}


	@Override
	public void unmakeLeftMove(int x, int y) {
		// TODO Auto-generated method stub
		this.theBoard[x][y] = HASPEG ;
		this.theBoard[x][y-1]= HASPEG;
		this.theBoard[x][y-2] = EMPTY;
		this.noofMoves --;
	}


	@Override
	public void unmakeRightMove(int x, int y) {
		// TODO Auto-generated method stub
		this.theBoard[x][y] = HASPEG ;
		this.theBoard[x][y+1] = HASPEG;
		this.theBoard[x][y+2] = EMPTY ;
		this.noofMoves --;
	}


	@Override
	public void unmakeTopMove(int x, int y) {
		// TODO Auto-generated method stub
		this.theBoard[x][y] = HASPEG ;
		this.theBoard[x-1][y] = HASPEG;
		this.theBoard[x-2][y] = EMPTY;
		this.noofMoves --;
	}


	public int getNoofMoves() {
		return noofMoves;
	}


	public void setNoofMoves(int noofMoves) {
		this.noofMoves = noofMoves;
	}

	public int movesForEntireBoard(){
		
		int count = 0;
		for(int i = 0 ; i < NO_OF_ROWS;i++ )
			for(int j = 0 ; j < NO_OF_COLUMNS;j++ )
				if(movePossible(i,j) == true)
					count++;
				
		int avg = count / noOfpegs;
		
		return avg;
		
	}
	/*Basic ... Maybe I am counting a move more than once - Over-estimating*/
	//Heuristic should decide the next move and the board position
	private Boolean movePossible(int x,int y){
		// TODO Auto-generated method stub
		if(LeftMove(x, y)||RightMove(x, y)||TopMove(x, y)||BottomMove(x, y)) 
			return true;
		return false;
		
	}

	@Override
	public int getHn() {
		// TODO Auto-generated method stub
		
		//1.
		//return noOfpegs - 1;
		//2.
		return movesForEntireBoard();
	}


	@Override
	public void setBoard(char[] array) {
		// TODO Auto-generated method stub
		
	}




	
}
