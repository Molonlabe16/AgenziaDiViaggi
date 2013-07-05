<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent/common
 * 
 * @name DialogGestioneProfilo.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<script src = "common/Script/jquery-ui/js/jquery-1.9.1.js" type = "text/javascript"></script>
		<script src = "common/Script/jquery-ui/js/jquery-ui-1.10.3.custom.js" type = "text/javascript"></script>
		<link  href = "common/Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" type = "text/css" rel = "stylesheet">
		
		<jsp:useBean id = "utente" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>	
		
		<script>
		
			$(function() {	
				
				$( ".dialogMessaggio" ).dialog({
					autoOpen: false,
					draggable: false,
					modal: true,
					buttons: {
						Ok: function() {
							$( this ).dialog( "close" );
						}
					}
				});
				
				$( "#dialogGestioneProfilo" ).dialog({    				
					    autoOpen: false,
					    draggable: false,
					    height: 550,
					    width: 350,
					    modal: true,
					    show: {
					    	effect: "blind"
					    },
					    hide: {
					    	effect:"blind"
					    },
					    buttons: {
						    "Ok": function() {
						    	/*
						    	var mNome = $( "#nome" ).val();
						    	var mCognome = $( "#nome" ).val();
						    	var mCitta = $( "#nome" ).val();
						    	var mNascita = $( "#nome" ).val();
						    	var mSesso = $( "#nome" ).val();
						    	var mMail = $( "#nome" ).val();
						    	var mRuolo = $( "#nome" ).val();
						    	var mUsername = $( "#nome" ).val();
						    	var mPassword = $( "#nome" ).val();
				   			          
				   			    $.get("http://localhost:8080/WebVoyager/GestioneProfilo", {username : mUsername})
				   			    .done(function() {
					   			    $( "#dialogMessaggioGestioneProfiloSuccesso" ).dialog( "open" );
				   			    })
				   			    .fail(function() {
					   			    $( "#dialogMessaggioGestioneProfiloFallimento" ).dialog( "open" );
				   			    })
				   			    .always(function() {
					   			    $( "#dialogGestioneProfilo" ).dialog( "close" );
				   			    });  */  			          
						    },
						    "Annulla": function() {
				   			    $( this ).dialog( "close" );
						    }
					    }
				});
				
				$( ".datepicker" ).datepicker( {
					show: {
						effect: "blind"
					},
					hide: {
						effect: "blind"
					}
				});
				
				$( ".radio" ).buttonset();
				
				$( "#buttonGestioneProfilo" ).click(function() {
			        $( "#dialogGestioneProfilo" ).dialog( "open" );
			        return false;
			    });
				
			});
		
		</script>
	
	</head>
	
	<body>
	
		<div class = "dialogForm" id = "dialogGestioneProfilo" title = "Gestione Profilo" align = "center">
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome" value = <%= utente.getNome() %> /></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome" value = <%= utente.getCognome() %> /></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Citt�" value = <%= utente.getCitta() %> /></p>
    				<p><input class = "text ui-widget-content ui-corner-all datepicker" id = "nascita" type = "text" name = "nascita"/></p>  
    				<div class = "radio" id = "radioSesso">
    					<input type = "radio" id = "radioUomo" name = "radio" checked = "checked" /><label for = "radioUomo">Uomo</label>
    					<input type = "radio" id = "radioDonna" name = "radio" /><label for = "radioDonna">Donna</label>
  					</div>  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "sesso" type = "text" name = "sesso" /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail" value = <%= utente.getMail() %> /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" readonly value = <%= utente.getRuolo().asString() %> /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" readonly value = <%= utente.getUsername() %> /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "text" name = "password" placeholder = "Password" value = <%= utente.getPassword() %> /></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneProfiloSuccesso" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>tuo profilo</b> � stato correttamente aggiornato!
  			</p>
		</div>		
	
	</body>

</html>