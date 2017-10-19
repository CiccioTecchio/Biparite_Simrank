package bpcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.text.AbstractDocument.LeafElement;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class BipariteSimCalculator {

	private Graph<String,DefaultEdge> graph;
	private ArrayList<String> nomiSx;
	private ArrayList<String> nomiDx;
	private HashMap<String, Double> scoreSx;
	private HashMap<String, Double> scoreDx;
	private HashMap<String, Double> coeffSx;
	private HashMap<String, Double> coeffDx;
	private int sizeSx;
	private int sizeDx;
	
	public BipariteSimCalculator() {
	graph= new SimpleGraph<>(DefaultEdge.class);
	nomiSx= new ArrayList<>();
	nomiDx= new ArrayList<>();
	scoreSx=new HashMap<String,Double>();
	scoreDx=new HashMap<String,Double>();
	coeffSx=new HashMap<String,Double>();
	coeffDx=new HashMap<String,Double>();
	sizeSx=0;
	sizeDx=0;
	}
	
	
	public void initNomi (String s) {
		String [] nodes= new String[2];
		nodes=s.split(",");
		graph.addVertex(nodes[0]);
		graph.addVertex(nodes[1]);
		graph.addEdge(nodes[0], nodes[1]);
		if(!nomiSx.contains(nodes[0]))
		nomiSx.add(nodes[0]);
		if(!nomiDx.contains(nodes[1]))
		nomiDx.add(nodes[1]);
		sizeSx=nomiSx.size();
		sizeDx=nomiDx.size();
	}
	
	
	public void simScoreCalculator(int iteration) {
		
		int it=0;
		HashMap<String, Double> appDx=scoreDx;
		HashMap<String, Double> appSx=scoreSx;
		while(it<iteration) {
			
			it++;
		}
	}
	
	protected HashMap<String, Double> rightSimScore(HashMap<String, Double> appDx) {
		HashMap<String, Double> app=scoreDx;
		Set entrySet=app.entrySet();
		Iterator it= entrySet.iterator();
		String key;
		String nodes[]=new String[2];
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		String splitA[]=new String[2];
		String splitB[]=new String[2];
		String strA,strB,str="",newkey;
		int inA,inB,i,j;
		double simScore=0.0,sumScore=0.0;
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			key=""+me.getKey();
			nodes=key.split(",");
			indexA=graph.edgesOf(nodes[0]);
			indexB=graph.edgesOf(nodes[1]);
			//System.out.println(key);
			//System.out.println("indexA"+indexA);
			//System.out.println("indexB"+indexB);
			inA=indexA.size();
			inB=indexB.size();
			//System.out.println("inA: "+inA);
			//System.out.println("inB: "+inB);
			if(nodes[0].equals(nodes[1])) {scoreDx.put(key, 1.00);}
			else {
				if((inA==0)||(inB==0)) { scoreDx.put(key, 0.0);}
				else {
					for(DefaultEdge edgeA:indexA) {
						strA=edgeA.toString();
						splitA=strA.split(" : ");
						strA=splitA[0].substring(1);
						for(DefaultEdge edgeB:indexB) {
							strB=edgeB.toString();
							splitB=strB.split(" : ");
							strB=splitB[0].substring(1);
							newkey=strA+","+strB;
							sumScore+=scoreSx.get(newkey);
						}
					}
					System.out.println("sumScore: "+sumScore+" coeff: "+coeffDx.get(key));
					simScore=coeffDx.get(key)*sumScore;
					scoreDx.put(key, simScore);
				}
				
			}//fine else
			simScore=0;
			sumScore=0;
		}//fine iterator
		return scoreDx;
	}

	
	
	protected HashMap<String, Double> leftSimScore() {
		HashMap<String,Double>app=scoreSx;
		Set entrySet=app.entrySet();
		Iterator it= entrySet.iterator();
		String key;
		String nodes[]=new String[2];
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		String strA,strB,str="",newkey;
		int outA,outB,i,j;
		double simScore=0.0,sumScore=0.0;
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			key=""+me.getKey();
			nodes=key.split(",");
			indexA=graph.edgesOf(nodes[0]);
			indexB=graph.edgesOf(nodes[1]);
			outA=indexA.size();
			outB=indexB.size();
			if(nodes[0].equals(nodes[1]))scoreSx.put(key, 1.00);
			else {
			if((outA==0)||(outB==0)) scoreSx.put(key, 0.0);
			else {
				for(DefaultEdge edgeA:indexA) {
					strA=edgeA.toString();
					strA=strA.substring(nodes[0].length()+3);
					strA=strA.substring(0,strA.length()-1);
					strA=strA.trim();
					for(DefaultEdge edgeB:indexB) {
						strB=edgeB.toString();
						strB=strB.substring(nodes[1].length()+3);
						strB=strB.substring(0,strB.length()-1);
						strB=strB.trim();
						newkey=strA+","+strB;
						sumScore+=scoreDx.get(newkey);
					}
					
				}
				simScore=coeffSx.get(key)*sumScore;
				scoreSx.put(key, simScore);
			}
		}//FINE ELSE
		simScore=0;
		sumScore=0;
		}
		return scoreSx;
	}
	
	
	
	public void initScore(double c1,double c2) {
		int i,j;
		String key;
		double coef=0;
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		int inA,inB,outA,outB;
		for(i=0;i<sizeSx;i++) {
			for(j=0;j<sizeSx;j++) {
				key=nomiSx.get(i)+","+nomiSx.get(j);
				scoreSx.put(key, assignValue(nomiSx.get(i),nomiSx.get(j)));
				indexA=graph.edgesOf(nomiSx.get(i));
				indexB=graph.edgesOf(nomiSx.get(j));
				outA=indexA.size();
				outB=indexB.size();
				coef=c1/(outA*outB);
				coeffSx.put(key, coef);
			}
		}
		coef=0;
		for(i=0;i<sizeDx;i++) {
			for(j=0;j<sizeDx;j++) {
				key=nomiDx.get(i)+","+nomiDx.get(j);
				scoreDx.put(key, assignValue(nomiDx.get(i),nomiDx.get(j)));
				indexA=graph.edgesOf(nomiDx.get(i));
				indexB=graph.edgesOf(nomiDx.get(j));
				inA=indexA.size();
				inB=indexA.size();
				coef=c2/(inA*inB);
				coeffDx.put(key, coef);
			}
		}
		
		
	}

	
	
	/*	protected void rightSimScore() {
	HashMap<String,Double>app=scoreDx;
	Set entrySet=app.entrySet();
	Iterator it= entrySet.iterator();
	String key;
	String nodes[]=new String[2];
	Set<DefaultEdge> indexA;
	Set<DefaultEdge> indexB;
	String strA,strB,str="",newkey;
	String splitA[]=new String[2];
	String splitB[]=splitA;
	int inA,inB,i,j;
	double simScore=0.0,sumScore=0.0;
	while(it.hasNext()) {
		Map.Entry me = (Map.Entry)it.next();
		key=""+me.getKey();
		nodes=key.split(",");
		indexA=graph.edgesOf(nodes[0]);
		indexB=graph.edgesOf(nodes[1]);
		inA=indexA.size();
		inB=indexB.size();
		if(nodes[0].equals(nodes[1]))scoreDx.put(key, 1.00);
		else {
		if((inA==0)||(inB==0)) scoreDx.put(key, 0.0);
		else {
			for(DefaultEdge edgeA:indexA) {
				strA=edgeA.toString();
				
				//strA=strA.substring(nodes[0].length()+3);
				strA=strA.substring(1);
				splitA=strA.split(" : "+nodes[0]+"");
				strA=splitA[0].trim();
				
				for(DefaultEdge edgeB:indexB) {
					strB=edgeB.toString();
					strB=strB.substring(1);
					splitB=strB.split(" : "+nodes[1]+"");
					strB=splitB[0].trim();
					newkey=strA+","+strB;
					System.out.println(newkey);
					sumScore+=scoreSx.get(newkey);
				}
				
			}
			simScore=coeffDx.get(key)*sumScore;
			scoreDx.put(key, simScore);
		}
	}//FINE ELSE
	}
	
}*/
	
	
	public double assignValue(String a,String b) {
		double toReturn=0;
		if(a.equals(b)) toReturn=1;
		return toReturn;
	}
	

	public Graph<String, DefaultEdge> getGraph() {
		return graph;
	}


	public void setGraph(Graph<String, DefaultEdge> graph) {
		this.graph = graph;
	}


	public ArrayList<String> getNomiSx() {
		return nomiSx;
	}


	public void setNomiSx(ArrayList<String> nomiSx) {
		this.nomiSx = nomiSx;
	}


	public ArrayList<String> getNomiDx() {
		return nomiDx;
	}


	public void setNomiDx(ArrayList<String> nomiDx) {
		this.nomiDx = nomiDx;
	}


	public HashMap<String, Double> getScoreSx() {
		return scoreSx;
	}


	public void setScoreSx(HashMap<String, Double> scoreSx) {
		this.scoreSx = scoreSx;
	}


	public HashMap<String, Double> getScoreDx() {
		return scoreDx;
	}


	public void setScoreDx(HashMap<String, Double> scoreDx) {
		this.scoreDx = scoreDx;
	}


	public HashMap<String, Double> getCoeffSx() {
		return coeffSx;
	}


	public void setCoeffSx(HashMap<String, Double> coeffSx) {
		this.coeffSx = coeffSx;
	}


	public HashMap<String, Double> getCoeffDx() {
		return coeffDx;
	}


	public void setCoeffDx(HashMap<String, Double> coeffDx) {
		this.coeffDx = coeffDx;
	}


	public int getSizeSx() {
		return sizeSx;
	}


	public void setSizeSx(int sizeSx) {
		this.sizeSx = sizeSx;
	}


	public int getSizeDx() {
		return sizeDx;
	}


	public void setSizeDx(int sizeDx) {
		this.sizeDx = sizeDx;
	}
	
	
	
	
	
}
