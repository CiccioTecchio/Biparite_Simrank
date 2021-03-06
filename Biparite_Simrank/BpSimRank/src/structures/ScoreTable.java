package structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ScoreTable extends HashMap<String, Float> {

	
	
	public ScoreTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ScoreTable(ScoreTable score) {
		super(score);
	}
	
	

	@Override
	public void putAll(Map<? extends String, ? extends Float> m) {
		// TODO Auto-generated method stub
		super.putAll(m);
	}


	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return super.clone();
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
