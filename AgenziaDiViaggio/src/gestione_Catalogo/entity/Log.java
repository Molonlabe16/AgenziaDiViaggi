package gestione_Catalogo.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import gestione_Catalogo.exception.FileInesistenteException;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Log {
	
	
	//attributi d'istanza
	private String log;
	
	
	//costruttore
	public Log() {
	//	data = new Data();
		log = "";
	}
	
	
	//Metodi
	public void aggiornaLogAggiungiViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via){
		log = "[" + Data.stampaDataAttuale() + "] AGGIUNTO Viaggio Via " + ambiente + ":\n" 									
				+ mezzo + "  ->  " + cittaPartenza + " : " + cittaArrivo + "  ->  " + via + "\n";
		System.out.println(log);
		salvaLog(log); 
	}
	
	//Metodi
	public void aggiornaLogRimuoviViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via){
		log = "[" + Data.stampaDataAttuale() + "] RIMOSSO Viaggio Via " + ambiente + ":\n" 									
				+ mezzo + "  ->  " + cittaPartenza + " : " + cittaArrivo + "  ->  " + via + "\n";
		System.out.println(log);
		salvaLog(log); 
	}

	public String caricaLog() throws FileInesistenteException {
		String contenutoFile = "";
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileReader fr;
		
		File file = new File(path);
		try {			
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
			String s;
			while((s = br.readLine()) != null) {
				contenutoFile = contenutoFile + s+"\n";
			} 
			fr.close();
		} catch (FileNotFoundException e) {
			throw new FileInesistenteException("Attenzione! File Inesistente!");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return contenutoFile;
	}
	

	private void salvaLog(String input) { //salva su file la stringa di input
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileWriter fw;
		try {
			File file = new File(path);
			fw = new FileWriter(file,true);
			fw.write(input+"\r\n");
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
