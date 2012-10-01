package com.pp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import sun.reflect.generics.tree.BottomSignature;

public class Game {
	
	List<Node> resultlist;
	Stack<Node> dfs;
	PriorityQueue<Node>pqueue;
	
	//TO DO: Create a closed set : Seen Board positions, Should I use HashMap and store the currentBoard, and the cost
	HashMap<String,Integer>closedSet_a;
	HashSet<String> closedSet_dfs;
	int[] pegholderId;

	public Game() {
		super();
		resultlist = new ArrayList<Node>();
		dfs = new Stack<Node>();
		//Comparator of Node
		Comparator<Node> comparater = new NodeComparator();
		pqueue = new PriorityQueue<Node>(10,comparater);
		pegholderId = new int[IBoard.NO_OF_COLUMNS * IBoard.NO_OF_ROWS];
	}

	private void initializeIds(String b) {
		// TODO Auto-generated method stub
		int index = 0;
		int value = 0;
		for(int i = 0 ; i < b.length() ; i++){
			if(b.charAt(i) == '_')
				pegholderId[index++] = -1;
			else
				pegholderId[index++] = value++;
		}
	}
	
	//O(n)
	public Boolean compareBoards(String ks, String ks2) {
		// TODO Auto-generated method stub
		if(ks == null || ks2 == null)
			return true;
		return ks.equals(ks2);
		
		
	}
	
	void UpdateResultListAstar(Node result){
		
		/*for(int i = resultlist.size()-1 ; i >=1 ; i-- ){
			if(compareBoards(result.prevBoard,resultlist.get(i).currentBoard))
				break;
			else{
				resultlist.remove(i);
			}
		}*/
		
		//TO DO: Need to traverse and check previous board position and get the path from the last one.. 
		
		
		resultlist.add(result);
		
	}
	
	
	void UpdateResultList(Node result){
		
		for(int i = resultlist.size()-1 ; i >=1 ; i-- ){
			if(compareBoards(result.prevBoard,resultlist.get(i).currentBoard))
				break;
			else{
				resultlist.remove(i);
			}
		}
		resultlist.add(result);
		
	}
	
	void printStack(){
		
		System.out.println("Printing the Stack");
		for( Node n : dfs){
			if(!(n.Result==null))
				System.out.print(n.Result.start + ":" + n.Result.dest + " , " );
		}
		System.out.println();
	}

	private Node createNewNode(Board b, Result r,String currentBoard, char move){
		Node newNode = new Node(b.createString(),r,currentBoard,b.getNoofMoves(),move);
		newNode.setG(b.getNoofMoves());
		newNode.setH(b.getHn());
		return newNode;
	}
	
	private int Astart(Node n){
		pqueue.add(n);
		int count = 0;
		while(!pqueue.isEmpty()){
					//printStack();
					Node currentNode = pqueue.poll();
					if(closedSet_a.containsKey(currentNode.currentBoard) == true && currentNode.f > closedSet_a.get(currentNode.currentBoard)){
						continue;
					}
					count++;
					//System.out.println("Pop :" + count++);
					UpdateResultListAstar(currentNode);
					Board b = new Board(currentNode.currentBoard,pegholderId,currentNode.getG());
					String currentBoard = b.createString();
					
					if(b.checkGoal() == true){
						System.out.println("SUCCESSSSSS!!!!!!!");
						printResult();
						System.out.println("Number of Nodes Examined i.e. popped : " + count);
						return count;
					}
					for(int x = 0 ; x < IBoard.NO_OF_ROWS ; x++ ){
						for(int y = 0 ; y < IBoard.NO_OF_COLUMNS ; y++){
							if(b.hasPeg(x, y)){
								if(b.LeftMove(x, y)){
									Result r = b.makeLeftMove(x, y);								
									pqueue.add(createNewNode(b,r,currentBoard,'L'));
									b.unmakeLeftMove(x, y);
								}
								if(b.RightMove(x, y)){
									Result r = b.makeRightMove(x, y);
									pqueue.add(createNewNode(b,r,currentBoard,'R'));
									b.unmakeRightMove(x, y);
								}
								if(b.TopMove(x, y)){
									Result r = b.makeTopMove(x, y);
									pqueue.add(createNewNode(b,r,currentBoard,'T'));
									b.unmakeTopMove(x, y);
								}
								if(b.BottomMove(x, y)){
									Result r = b.makeBottomMove(x, y);
									pqueue.add(createNewNode(b,r,currentBoard,'B'));
									b.unmakeBottomMove(x, y);
								}
							}
						}
					}
				}
		return -1;
		
	}
	private int DFS(Node n) {
		
			dfs.add(n);
			int count = 0;
			while(!dfs.isEmpty()){
						//printStack();
						Node currentNode = dfs.pop();
						
						//Continue to the next iteration if the currentBoard has already been seen.but let's not add into the stack
						if(closedSet_dfs.contains(currentNode.currentBoard) == true){
							continue;
						}
						closedSet_dfs.add(currentNode.currentBoard);
						count ++;
						//System.out.println("Pop :" + count++);
						UpdateResultList(currentNode);
						Board b = new Board(currentNode.currentBoard,pegholderId,currentNode.getG());
						String currentBoard = b.createString();
						
						if(b.checkGoal() == true){
							System.out.println("SUCCESSSSSS!!!!!!!");
							printResult();
							System.out.println("Number of Nodes Examined i.e. popped : " + count);
							return count;
						}
						for(int x = 0 ; x < IBoard.NO_OF_ROWS ; x++ ){
							for(int y = 0 ; y < IBoard.NO_OF_COLUMNS ; y++){
								if(b.hasPeg(x, y)){
									if(b.LeftMove(x, y)){
										Result r = b.makeLeftMove(x, y);
										Node newNode = new Node(b.createString(),r,currentBoard,b.getNoofMoves(),'L');
										if(closedSet_dfs.contains(newNode.currentBoard) == false){
											dfs.add(newNode);
										}
										b.unmakeLeftMove(x, y);
									}
									if(b.RightMove(x, y)){
										Result r = b.makeRightMove(x, y);
										Node newNode = new Node(b.createString(),r,currentBoard,b.getNoofMoves(),'R');
										if(closedSet_dfs.contains(newNode.currentBoard) == false){
											dfs.add(newNode);
										}
										b.unmakeRightMove(x, y);
									}
									if(b.TopMove(x, y)){
										Result r = b.makeTopMove(x, y);
										Node newNode = new Node(b.createString(),r,currentBoard,b.getNoofMoves(),'T');
										if(closedSet_dfs.contains(newNode.currentBoard) == false){
											dfs.add(newNode);
										}
										b.unmakeTopMove(x, y);
									}
									if(b.BottomMove(x, y)){
										b.BottomMove(x, y);
										Result r = b.makeBottomMove(x, y);
										Node newNode = new Node(b.createString(),r,currentBoard,b.getNoofMoves(),'B');
										if(closedSet_dfs.contains(newNode.currentBoard) == false){
											dfs.add(newNode);
										}
										b.unmakeBottomMove(x, y);
									}
								}
							}
						}
					}
			return -1;
		}

	private void printResult() {
		// TODO Auto-generated method stub
		System.out.print("< ");
		for(Node n : resultlist){
			
			if(n != null && n.Result != null){
				Result r = n.Result;
				System.out.println("( " + r.start + "," + r.dest +" ),");
				System.out.println("No of Moves : " + n.getG());
				printSerializedInput(n.currentBoard);
			}
		}
		System.out.println(" >");
		
		
	}
	
	private void printSerializedInput(String currentBoard) {
		// TODO Auto-generated method stub
		int count = 0; 
		System.out.println("Printing the Configuration of the board");
		for (int i = 0 ; i < currentBoard.length();i++) {
			char c = currentBoard.charAt(i);
			System.out.print(c + "|");
			count ++;
			if(count == 7){
				System.out.println();
				count = 0;
			}
		}
		System.out.println();
	}

	public Boolean startGame(String initBoard,int i){
		 
		System.out.println("Initial State of the Board");
		printSerializedInput(initBoard);
		Node n = new Node(initBoard,null,null,0,'I');
		
		initializeIds(initBoard);
		if( i == 1){
			//long startTime = System.nanoTime();
			closedSet_dfs = new HashSet<String>(); 
			System.out.println("DFS (Depth First Search)");
			long startTime = System.currentTimeMillis();
			if(DFS(n) == -1){
				System.out.println("No Solution for the input configuration");
			}else{
				
			}
//			long endTime = System.nanoTime();
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			System.out.println("Time taken for DFS : " + duration);
		}
		
		if( i == 2){
			closedSet_a = new HashMap<String, Integer>();
			System.out.println("A * Search");
			long startTime = System.nanoTime();
			if(Astart(n) == -1){
				System.out.println("No Solution for the input configuration");
			}
			
			long endTime = System.nanoTime();
			long duration = endTime - startTime;
			System.out.println("Time taken for A star : " + duration);
			
		}
		
		return false;
		
	}
	
	


	
}
