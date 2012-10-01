package com.pp;

public class MainClass {
	
	public static void main(String [ ] args){		
		
		//Create the Initial Config
		/*
		char[] initialConfig = {'_','_','O','O','O','_','_',
								'_','_','O','X','O','_','_',
								'O','O','X','X','X','O','O',
								'O','O','O','X','O','O','O',
								'O','O','O','X','O','O','O',
								'_','_','O','O','O','_','_',
								'_','_','O','O','O','_','_'
							   };
		
		*/
		char[] initialConfig = {'_','_','X','X','X','_','_',
				'_','_','X','X','X','_','_',
				'X','X','X','X','X','X','X',
				'X','X','X','O','X','X','X',
				'X','X','X','X','X','X','X',
				'_','_','X','X','X','_','_',
				'_','_','X','X','X','_','_'
			   };
		
		/*
		char[] initialConfig = {'_','_','O','O','O','_','_',
				'_','_','O','O','O','_','_',
				'O','O','O','O','O','O','O',
				'X','X','O','O','O','X','O',
				'O','O','O','X','O','O','O',
				'_','_','O','X','O','_','_',
				'_','_','O','O','O','_','_'
			   };
		*/
		// Create Game Function
		Game g = new Game();
		
		String s = new String(initialConfig);
		//Initialize & start the game
		g.startGame(s,Integer.parseInt(args[0]));
	}

}
