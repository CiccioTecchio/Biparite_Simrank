package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			double c1=Double.parseDouble(args[1]);
			double c2=Double.parseDouble(args[2]);
			if((c1<=0.0 || c1>=1.00)&&(c2<=0.0 || c2>=1.00)) throw new RuntimeException();
			FileReader file= new FileReader(args[0]);
			BufferedReader b= new BufferedReader(file);
			String s=b.readLine();
			
			while(s!=null) {
				s=b.readLine();
			}
			int numIter=Integer.parseInt(args[3]);
			if(numIter<=0) throw new RuntimeException();
			
		}catch(IOException e) {e.getMessage();
							  e.printStackTrace();}
		catch(RuntimeException e) {e.getMessage();
		  					      e.printStackTrace();}
		
	}
	
	public static void printInstance() {
		System.out.println("----------NOMI----------\n");
		System.out.println("\n");
		System.out.println("----------NOMI----------\n");
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println("\n");
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println("----------SCORE INIZIALI----------\n");
		System.out.println("\n");
		System.out.println("----------SCORE INIZIALI----------\n");
		System.out.println("----------COEFF----------\n");
		System.out.println("\n");
		System.out.println("----------COEFF----------\n");
		
	}

}
