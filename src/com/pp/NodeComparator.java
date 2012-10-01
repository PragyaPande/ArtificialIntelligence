package com.pp;
import java.util.Comparator;


//Return 0 if both are same
//Return -1 if current Node
public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		
		assert(arg0 != null && arg1 != null);
		return arg0.f - arg1.f;
	}

}
