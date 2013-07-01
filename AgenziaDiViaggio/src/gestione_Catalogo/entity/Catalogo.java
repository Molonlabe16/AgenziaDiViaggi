package gestione_Catalogo.entity;

import gestione_Catalogo.dao.CatalogoDAO;
import gestione_Catalogo.dao.TrattaDAO;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.TrattaException;

import java.util.ArrayList;
import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Catalogo {
	
	//attributi di classe
	private static Catalogo istanza;
	
	//attributi di istanza
	private ArrayList<Tratta> listaTratte;
	private MappaCatalogo mappaCatalogo;
	
	//costruttore
	private Catalogo() {
		listaTratte = new ArrayList<Tratta>();
		mappaCatalogo = new MappaCatalogo(); //istanziato il catalogo, creo subito una mappa per gli ambienti
		CatalogoDAO dao = CatalogoDAO.getIstanza();
		listaTratte = dao.getCatalogo();
		try {
			
			
			caricaCatalogo();
		} catch (IDEsternoElementoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// metodi
	public static Catalogo getIstanza(){
		if (istanza == null){
			istanza = new Catalogo();
		}
		return istanza;
	}
	
	
	public boolean verificaEsistenzaViaggio(Tratta tratta){
		for (Tratta t : listaTratte) {
			if (t.equals(tratta)) 
				return true;
		}
		return false;
	}
	
	
	public boolean verificaEsistenzaViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) {
		for (Tratta tratta : listaTratte){
			if (tratta.verifyExistence(ambiente, mezzo, cittaPartenza, cittaArrivo, via))
				return true;
		}
		return false;
	}
	
	public boolean verificaEsistenzaOfferte(Tratta tratta){
		return false;
		
	}
	
	
	public void aggiungiViaggioAlCatalogo(Tratta tratta) throws IDEsternoElementoException{
		
		listaTratte.add(tratta);
		
		aggiungiInMappaCatalogo(tratta);
	}
	
	
	

	public void rimuoviViaggioDalCatalogo(Tratta tratta) throws IDEsternoElementoException{
		
		listaTratte.remove(tratta);
		
		rimuoviDaMappaCatalogo(tratta);
		
		TrattaDAO dao = TrattaDAO.getIstanza();
		dao.delete(tratta);
		

	}
	
	
	
	
	public void caricaCatalogo() throws IDEsternoElementoException{
		for (Tratta tratta : listaTratte){
			aggiungiInMappaCatalogo(tratta);
		}
	}


	public Set<String> getChiaviAmbienti() throws MappaException {
		Set<String> ambienti = mappaCatalogo.keySet();
		if (ambienti.isEmpty())
			throw new MappaException("Non sono presenti Viaggi nel Catalogo.");
		else
			return ambienti;
	}
	
	public Set<String> getChiaviMezzi(String ambiente) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).listaChiaviElementi();	
	}
	
	public Set<String> getChiaviCittaDiPartenza(String ambiente, String mezzo) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).listaChiaviElementi(); 
	}
	
	public Set<String> getChiaviCittaDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).listaChiaviElementi();
	}
	
	public Set<String> getChiaviVia(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		return  mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).listaChiaviElementi();
	}
	
	
	public Tratta getTrattaByValue(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws TrattaException{
		for (Tratta tratta : listaTratte) {
			if (tratta.verifyExistence(ambiente, mezzo, cittaPartenza, cittaArrivo, via))
				return tratta;
		}
		throw new TrattaException("Tratta non esistente.");
	}

	

	private void aggiungiInMappaCatalogo(Tratta tratta) throws IDEsternoElementoException {
		
		Ambiente ambiente = tratta.getAmbiente();
		Mezzo mezzo = tratta.getMezzo();
		Citta partenza = tratta.getPartenza();
		Citta arrivo = tratta.getArrivo();
		Via via = tratta.getVia();
		
		/*
		 * Bisogna sempre verificare, prima di aggiungere un elemento alla tabella, se questo elemento e' gia' presente!
		 */
		
		//FASE 1: Aggiungo l'ambiente in mappaCatalogo
		if (!mappaCatalogo.esistenzaElemento(ambiente)){
			
			//Se non c'e', lo aggiungo
			mappaCatalogo.aggiungiElemento(ambiente.getIDEsternoElemento(), ambiente);
		}
		
		//FASE 2: Aggiungo il mezzo nella mappa dell'ambiente prima aggiunto
		if (!mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).esistenzaElemento(mezzo)){
	
			//se non c'e' lo aggiungo
			mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).aggiungiElemento(mezzo.getIDEsternoElemento(), mezzo);
		}
		
		//FASE 3: Aggiungo cittaPartenza nella mappa del mezzo prima aggiunto
		if (!mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).esistenzaElemento(partenza)){
			
			//se non c'e' lo aggiungo
			mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).aggiungiElemento(partenza.getIDEsternoElemento(), partenza);
			
		}
		
		//FASE 4: Aggiungo stazioneArrivo nella mappa della cittaPartenza prima aggiunta
		if (!mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).esistenzaElemento(arrivo)){
			//se non c'e' lo aggiungo
			mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).aggiungiElemento(arrivo.getIDEsternoElemento(), arrivo);
		}
		
		
		//FASE 5: Aggiungo via nella mappa delle citta' di Arrivo
		//non c'e' bisogno di controllo, so gia' che non c'e' (verificaEsistenzaViaggio());
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).getElemento(arrivo.getIDEsternoElemento()).aggiungiElemento(via.getIDEsternoElemento(), via);

		//System.out.println("Viaggio Aggiunto");
		
	}
	
	
	private void rimuoviDaMappaCatalogo(Tratta tratta) throws IDEsternoElementoException {

		ElementoIntermedio elementoAmbiente = mappaCatalogo.getElemento(tratta.getAmbiente().getIDEsternoElemento());
		ElementoIntermedio elementoMezzo = elementoAmbiente.getElemento(tratta.getMezzo().getIDEsternoElemento());
		ElementoIntermedio elementoPartenza = elementoMezzo.getElemento(tratta.getPartenza().getIDEsternoElemento());
		ElementoIntermedio elementoArrivo = elementoPartenza.getElemento(tratta.getArrivo().getIDEsternoElemento());

		// Rimuovo via dalla mappa
		elementoArrivo.rimuoviElemento(tratta.getVia().getIDEsternoElemento());

		// Se la mappa della citta' di arrivo non ha elementi, rimuovo la citta' di arrivo;
		if (elementoArrivo.listaChiaviElementi().isEmpty())
			elementoPartenza.rimuoviElemento(tratta.getArrivo().getIDEsternoElemento());
		
		// Se la mappa della citta' di partenza non ha elementi, rimuovo la citta' di partenza
		if (elementoPartenza.listaChiaviElementi().isEmpty())
			elementoMezzo.rimuoviElemento(tratta.getPartenza().getIDEsternoElemento());
		
		// Se la mappa del mezzo non ha elementi, rimuovo il mezzo
		if (elementoMezzo.listaChiaviElementi().isEmpty())
			elementoAmbiente.rimuoviElemento(tratta.getMezzo().getIDEsternoElemento());
		
		// Se la mappa dell'ambiente non ha elementi, rimuovo l'ambiente
		if (elementoAmbiente.listaChiaviElementi().isEmpty())
			mappaCatalogo.rimuoviElemento(tratta.getAmbiente().getIDEsternoElemento());

		//System.out.println("Viaggio Rimosso");
		
	}



}

