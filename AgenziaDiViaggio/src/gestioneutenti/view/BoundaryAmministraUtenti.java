/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.view
 * 
 * @name BoundaryAmministraUtenti.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.view;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.model.bean.UtenteBean;
import gestioneutenti.view.utils.DialogModificaUtente;
import gestioneutenti.view.utils.TabellaUtenti;
import gestioneutenti.view.utils.DialogNuovoUtente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utils.swing.GBCHelper;

public class BoundaryAmministraUtenti extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITOLO = "Amministrazione Utenti";	
	
	private JPanel panelMe;
	
	private JPanel panelTitolo;
	private JPanel panelCerca;
	private JScrollPane panelTabella;
	private JPanel panelBottoni;	
	
	private JLabel labelTitolo;
	private JTextField textfieldCerca;
	private JButton buttonCerca;
	private JButton buttonNuovo;
	private JButton buttonModifica;
	private JButton buttonRimuovi;
	private TabellaUtenti tabellaUtenti;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
	
	public BoundaryAmministraUtenti() {
		this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
		this.panelMe = this;
		buildFrame();					
	}	
	
	private void buildFrame() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
		this.addAncestorListener(new RefreshListener());
				
		//Panel Title
		this.panelTitolo = new JPanel();		
		this.labelTitolo = new JLabel(TITOLO);
		this.labelTitolo.setFont(new Font(this.labelTitolo.getFont().getName(), Font.BOLD, 20));		
		this.panelTitolo.add(labelTitolo);
				
		//Panel Search
		this.panelCerca = new JPanel();		
		this.textfieldCerca = new JTextField("", 30); 
		this.buttonCerca = new JButton("Cerca");		
		this.panelCerca.add(this.textfieldCerca);
		this.panelCerca.add(this.buttonCerca);
				
		//Panel Table
		this.tabellaUtenti = new TabellaUtenti(this.controllerAmministraUtenti.getUtenti());
		this.tabellaUtenti.getSelectionModel().addListSelectionListener(new UtenteSelezionatoListener());
		this.panelTabella = new JScrollPane(tabellaUtenti);
				
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridBagLayout());		
		this.buttonNuovo = new JButton("Nuovo");
		this.buttonModifica = new JButton("Modifica");
		this.buttonModifica.setEnabled(false);
		this.buttonRimuovi = new JButton("Rimuovi");	
		this.buttonRimuovi.setEnabled(false);
		this.buttonNuovo.addActionListener(new NuovoListener());
		this.buttonModifica.addActionListener(new ModificaListener());
		this.buttonRimuovi.addActionListener(new RimuoviListener());
		this.buttonCerca.addActionListener(new CercaListener());	
		this.panelBottoni.add(this.buttonNuovo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonModifica, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonRimuovi, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));	
				
		//Frame Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(this.panelCerca, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(this.panelTabella, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.add(this.panelBottoni, new GBCHelper(1, 2).setWeight(0, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 5, 0, 10));		
	}
	
	private class RefreshListener implements AncestorListener {

		@Override
		public void ancestorAdded(AncestorEvent event) {		

			aggiornaTabellaUtenti();		
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			
		}
		
	}
	
	private void aggiornaTabellaUtenti() {
		this.tabellaUtenti.aggiornaTabella(this.controllerAmministraUtenti.getUtenti());
	}

	private class UtenteSelezionatoListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			buttonModifica.setEnabled(true);
			buttonRimuovi.setEnabled(true);
		}
		
	}
	
	private class NuovoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogNuovoUtente dialog = new DialogNuovoUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				nuovo(dialog.getUtenteBean());
				aggiornaTabellaUtenti();
			}
		}		
	}
		
	private class ModificaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UtenteBean utenteBean = tabellaUtenti.getUtenteSelezionato();
			DialogModificaUtente dialog = new DialogModificaUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe), utenteBean);
			dialog.setVisible(true);
			if (dialog.datiValidi()) {
				modifica(dialog.getUtenteBean());
				aggiornaTabellaUtenti();
			}
		}		
	}
	
	private class RimuoviListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UtenteBean utenteBean = tabellaUtenti.getUtenteSelezionato();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere " + utenteBean.getUsername() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				rimuovi(utenteBean);
				aggiornaTabellaUtenti();
			}
		}
	}
	
	private class CercaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String query = textfieldCerca.getText().toString();
			cerca(query);
		}
	}
	
	private void nuovo(UtenteBean utenteBean) {
		this.controllerAmministraUtenti.nuovo(utenteBean);
	}
	
	private void modifica(UtenteBean utenteBean) {		
		this.controllerAmministraUtenti.modifica(utenteBean);
	}
	
	private void rimuovi(UtenteBean utenteBean) {
		this.controllerAmministraUtenti.rimuovi(utenteBean);
	}
	
	private void cerca(String query) {
		this.controllerAmministraUtenti.cerca(query);
	}		
	
}
