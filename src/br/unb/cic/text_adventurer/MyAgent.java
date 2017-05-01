package br.unb.cic.text_adventurer;

import ieeecig.advent.Agent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

import edu.stanford.nlp.simple.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyAgent implements Agent {
	
	private Random random = new Random();
	
	public MyAgent() {
	}
	
	
	// Create a document. No computation is done yet.
	private ArrayList<String> verbos(String texto){
		ArrayList<String> mVerbos = new ArrayList<>();
	    Document doc = new Document(texto);
	    for (Sentence sent : doc.sentences()) { 
//	    	System.out.println("sent.length() "+sent.length());
	        for(int i=0; i < sent.length(); i++) {
	        	//adicionando o verbo a lista dos identificados
		    	String temp = sent.posTag(i);
		        if(temp.compareTo("VB") == 0) {
//		        	System.out.println("O verbo eh " + sent.word(i));
			        mVerbos.add(sent.word(i));
		        }
		        else {
//		        	System.out.println("Não verbo " + sent.word(i));
		        }
	        }
	    }
    	System.out.println("Os verbos sao:");
    	System.out.println(mVerbos);

		return mVerbos;
	}
	
	@Override
	public String action(String narrative) {
		System.out.println(narrative);
		ArrayList<String> losVerbos = verbos(narrative);
		String command = new String();
		command = losVerbos.get(0);
		losVerbos.remove(0);
		System.out.println(">" + command); 
		return command;
	}
	
	public static void main( String [] args ) throws FileNotFoundException {
		
		 //1. Create an instance of your agent		
		Agent agent = new MyAgent();
		
		if( args.length == 0 ) {

			 //2. Load the default adventure file
			InputStream zMachineData = ieeecig.advent.Main.class.getResourceAsStream( "monkey-and-bananas-v1.z8" );
			
			 //3. Invoke the adventure framework			
			ieeecig.advent.Main.invoke( agent, zMachineData );
		}
		else {

			 //2. Load the adventure file, e.g. "myPath/someMachineGame.z8"			
			File zMachineData = new File( args[ 0 ] );
			System.out.println("leu essa merda"); 

			 //3. Invoke the adventure framework			
			ieeecig.advent.Main.invoke( agent, zMachineData );
		}
	}

}
