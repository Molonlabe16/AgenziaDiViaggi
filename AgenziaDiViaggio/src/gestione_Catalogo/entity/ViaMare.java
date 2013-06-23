/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class ViaMare extends Ambiente implements Serializable{


	private static final long serialVersionUID = 1L;

	public ViaMare(IDEsternoElemento idEsterno){
		super(idEsterno);
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
						
		ViaMare nuovoElemento = (ViaMare) altroObject;
						
		return (this.idEsterno.equals(nuovoElemento.idEsterno));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
