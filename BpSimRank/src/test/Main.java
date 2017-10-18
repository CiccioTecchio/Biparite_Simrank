package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import bpcalculator.*;
public class Main {
	
	private static BipariteSimCalculator calculator;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			double c1=Double.parseDouble(args[1]);
			double c2=Double.parseDouble(args[2]);
			if((c1<=0.0 || c1>=1.00)&&(c2<=0.0 || c2>=1.00)) throw new RuntimeException();
			FileReader file= new FileReader(args[0]);
			BufferedReader b= new BufferedReader(file);
			String s=b.readLine();
			calculator=new BipariteSimCalculator();
			while(s!=null) {
				calculator.initNomi(s);
				s=b.readLine();
			}
			calculator.initScore(c1,c2);
			printInstance();
			int numIter=Integer.parseInt(args[3]);
			if(numIter<=0) throw new RuntimeException();
			
		}catch(IOException e) {e.getMessage();
							  e.printStackTrace();}
		catch(RuntimeException e) {e.getMessage();
		  					      e.printStackTrace();}
		
	}
	
	public static void printInstance() {
		System.out.println("----------NOMI SX----------\n");
		System.out.println(calculator.getNomiSx()+"\n");
		System.out.println("----------NOMI SX----------\n");
		System.out.println("----------NOMI DX----------\n");
		System.out.println(calculator.getNomiDx()+"\n");
		System.out.println("----------NOMI DX----------\n");
		System.out.println("----------GRAFO ----------\n");
		System.out.println(calculator.getGraph()+"\n");
		System.out.println("----------GRAFO----------\n");
		System.out.println("----------SCORE SX INIZIALI----------\n");
		System.out.println(calculator.getScoreSx()+"\n");
		System.out.println("----------SCORE SX INIZIALI----------\n");
		System.out.println("----------SCORE DX INIZIALI----------\n");
		System.out.println(calculator.getScoreDx()+"\n");
		System.out.println("----------SCORE DX INIZIALI----------\n");
		System.out.println("----------COEFF SX----------\n");
		System.out.println(calculator.getCoeffSx()+"\n");
		System.out.println("----------COEFF SX----------\n");
		System.out.println("----------COEFF DX----------\n");
		System.out.println(calculator.getCoeffDx()+"\n");
		System.out.println("----------COEFF DX----------\n");
		
	}

}
