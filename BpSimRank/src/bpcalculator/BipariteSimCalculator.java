package bpcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class BipariteSimCalculator {

	private Graph<String,DefaultEdge> graph;
	private Graph<String,DefaultEdge> gQuadro;
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
	gQuadro= new SimpleGraph<>(DefaultEdge.class);
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
	
	protected void createGQuadro() {
		int i,j;
		for(i=0;i<sizeSx;i++) {
			for(j=0;j<sizeDx;j++) {
				
			}
		}
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

	
	public double assignValue(String a,String b) {
		double toReturn=0;
		if(a.equals(b)) toReturn=1;
		return toReturn;
	}
	
	public Graph<String, DefaultEdge> getGQuadro() {
		return gQuadro;
	}


	public void setGQuadro(Graph<String, DefaultEdge> gQuadro) {
		this.gQuadro = gQuadro;
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
