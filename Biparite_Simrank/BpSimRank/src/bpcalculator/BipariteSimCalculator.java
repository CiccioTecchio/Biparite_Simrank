package bpcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.text.AbstractDocument.LeafElement;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import structures.ScoreTable;

public class BipariteSimCalculator {

	private UndirectedGraph<String,DefaultEdge> graph;
	private ArrayList<String> nomiSx;
	private ArrayList<String> nomiDx;
	private ScoreTable scoreSx;
	private ScoreTable scoreDx;
	private ScoreTable coeffSx;
	private ScoreTable coeffDx;
	private ScoreTable vecchiaSx;
	private ScoreTable vecchiaDx;
	private int sizeSx;
	private int sizeDx;
	
	public BipariteSimCalculator() {
	graph= new SimpleGraph<>(DefaultEdge.class);
	nomiSx= new ArrayList<>();
	nomiDx= new ArrayList<>();
	scoreSx=new ScoreTable();
	scoreDx=new ScoreTable();
	coeffSx=new ScoreTable();
	coeffDx=new ScoreTable();
	sizeSx=0;
	sizeDx=0;
	 vecchiaDx=new ScoreTable();
	 vecchiaSx=new ScoreTable();
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
		vecchiaSx.putAll(scoreSx);
		vecchiaDx.putAll(scoreDx);
		while(it<iteration) {
		scoreSx=leftSimScore(vecchiaSx,vecchiaDx);
		scoreDx=rightSimScore(vecchiaDx,vecchiaSx);
		vecchiaSx.putAll(scoreSx);
		vecchiaDx.putAll(scoreDx);
		it++;
		}
		
	}
	
	protected ScoreTable rightSimScore(ScoreTable appDx,ScoreTable appSx) {
		vecchiaDx.putAll(appDx);
		Set entrySet=vecchiaDx.entrySet();
		Iterator it= entrySet.iterator();
		String key;
		String nodes[]=new String[2];
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		String newkey;
		int inA,inB;
		float simScore=0.0f,sumScore=0.0f;
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			key=""+me.getKey();
			nodes=key.split(",");
			indexA=graph.edgesOf(nodes[0]);
			indexB=graph.edgesOf(nodes[1]);
			inA=indexA.size();
			inB=indexB.size();
			if((inA==0)||(inB==0)) { scoreDx.put(key, 0.0f);}			
				if(nodes[0].equals(nodes[1])) {scoreDx.put(key, 1.00f);}
				else {
				String v1,v2="";
							for(DefaultEdge edge : indexA) {

								    v1   = graph.getEdgeSource(edge);
								    
								   for(DefaultEdge edge2 : indexB) {
									    v2   = graph.getEdgeSource(edge2);
									    newkey=v1+","+v2;
									    sumScore+=appSx.get(newkey);
									   
								   }
					simScore=coeffDx.get(key)*sumScore;
					scoreDx.put(key, simScore);
					scoreDx.put(nodes[1]+","+nodes[0],simScore);				
				}
				sumScore=0;
				simScore=0;
			}//fine else
			
			
		}//fine iterator
		return scoreDx;
	}

	
	
	protected ScoreTable leftSimScore(ScoreTable appSx,ScoreTable appDx) {
		//ScoreTable app=appSx;
		vecchiaSx.putAll(appSx);
		Set entrySet=vecchiaSx.entrySet();
		Iterator it= entrySet.iterator();
		String key;
		String nodes[]=new String[2];
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		String strA,strB,newkey;
		int outA,outB;
		float simScore=0.0f,sumScore=0.0f;
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			key=""+me.getKey();
			nodes=key.split(",");
			indexA=graph.edgesOf(nodes[0]);
			indexB=graph.edgesOf(nodes[1]);
			outA=indexA.size();
			outB=indexB.size();
			if(nodes[0].equals(nodes[1]))scoreSx.put(key, 1.00f);
			else {
			if((outA==0)||(outB==0)) scoreSx.put(key, 0.0f);
			else {
				for(DefaultEdge edgeA:indexA) {
					strA   = graph.getEdgeTarget(edgeA);
					for(DefaultEdge edgeB:indexB) {
						strB  = graph.getEdgeTarget(edgeB);
						newkey=strA+","+strB;
						sumScore+=appDx.get(newkey);
					}
					
				}
				simScore=coeffSx.get(key)*sumScore;
				scoreSx.put(key, simScore);
				scoreSx.put(nodes[1]+","+nodes[0],simScore);
			}
			simScore=0;
			sumScore=0;
		}//FINE ELSE
		
		}
		return scoreSx;
	}
	
	
	
	public void initScore(float c1,float c2) {
		int i,j;
		String key;
		float coef=0;
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
				inB=indexB.size();
				coef=c2/(inA*inB);
				coeffDx.put(key, coef);
			}
		}
		
		
	}
	
	public float assignValue(String a,String b) {
		float toReturn=0;
		if(a.equals(b)) toReturn=1;
		return toReturn;
	}
	

	public UndirectedGraph<String, DefaultEdge> getGraph() {
		return graph;
	}


	public void setGraph(UndirectedGraph<String, DefaultEdge> graph) {
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


	public ScoreTable getScoreSx() {
		return scoreSx;
	}


	public void setScoreSx(ScoreTable scoreSx) {
		this.scoreSx = scoreSx;
	}


	public ScoreTable getScoreDx() {
		return scoreDx;
	}


	public void setScoreDx(ScoreTable scoreDx) {
		this.scoreDx = scoreDx;
	}


	public ScoreTable getCoeffSx() {
		return coeffSx;
	}


	public void setCoeffSx(ScoreTable coeffSx) {
		this.coeffSx = coeffSx;
	}


	public ScoreTable getCoeffDx() {
		return coeffDx;
	}


	public void setCoeffDx(ScoreTable coeffDx) {
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
