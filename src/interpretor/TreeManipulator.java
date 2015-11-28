package interpretor;

import algebra.*;
import java.util.*;

public class TreeManipulator {
	public TreeManipulator()
	{
		
	}
	
	Expression getTreeAt(String position,Expression tree)
	{
		int size=position.length();
		int currentPosition;
		
		currentPosition=Integer.parseInt(position.substring(0,1));
		List<Expression> liste=tree.getChildren();
		if(liste == null)
			return null;
		
		Expression nextTree=liste.get(currentPosition);
		
		

		if(size == 1)
			return nextTree;
		return getTreeAt(position.substring(1),nextTree);
	}
}
