package structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ScoreTable extends HashMap<String, Double> {

	public ScoreTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ScoreTable(ScoreTable score) {
		super(score);
	}

	public String toString(int sizeCol) {
		Set entrySet=this.entrySet();
		Iterator it= entrySet.iterator();
		int i=0;
		String str="";
		while(it.hasNext()) {
			if(i==sizeCol) {i=0;
					  str+="\n";}
			Map.Entry me = (Map.Entry)it.next();
			String troncato= String.format ("%.3f", me.getValue()); 
			str+="\t"+me.getKey()+": "+troncato+"\t";
			i++;
		}
		return str;
	}

	
	
	
}
