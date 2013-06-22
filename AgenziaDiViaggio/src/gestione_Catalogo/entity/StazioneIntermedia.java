/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

public class StazioneIntermedia extends Elemento{
	

	private static final long serialVersionUID = 1L;
	
	private Info		info; //Le Info dovrebbero essere una propriet� del viaggio e non del singolo elemento. La stazione intermedia (o via) � l'elemento che caratterizza il viaggio, essendo l'ultimo... pertanto info � un attributo di StazioneIntermedia.
	private Orologio	data;
	
	
	public StazioneIntermedia(){
		super(new IDEsterno("(Diretto)"));
		info = new Info();
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}
		
	public StazioneIntermedia(IDEsterno idEsterno) {
		super(idEsterno);
		info = new Info();
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}
	
	public StazioneIntermedia(Info info){
		super(new IDEsterno("(Diretto)"));
		this.info = info;
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}
	
	public StazioneIntermedia(IDEsterno idEsterno, Info info) {
		super(idEsterno);
		this.info = info;
		data = new Orologio();
		
		//aggiorno le info mettendoci la data di inserimento
		info.updateInfo("--- Inserito il " + data.stampaDataAttuale());
	}

	public Info getInfo() {
		return info;
	}
	

	public Orologio getData() {
		return data;
	}

	
	//ridefinisco il metodo equals
	public boolean equals(Object altroObject){
				
		// verifico se sono lo stesso oggetto
		if (this == altroObject) return true;
						
		// verifico se il parametro implicito � null
		if (altroObject == null) return false;
						
		//verifico se le classi non coincidono
		if (getClass() != altroObject.getClass()) return false;
						
		//ok, ora so che altroOggetto � un elemento non nullo, per cui faccio i confronti tra attributi
						
		StazioneIntermedia nuovoElemento = (StazioneIntermedia) altroObject;
						
		return (this.idEsterno.equals(nuovoElemento.idEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
