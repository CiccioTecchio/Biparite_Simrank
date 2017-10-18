package bpcalculator;

import java.util.ArrayList;
import java.util.HashMap;

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
	coeffSx=new HashMap<String,Double>();
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
