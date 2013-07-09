/**
 * @project WebVoyager
 * 
 * @package home.servlet
 * 
 * @name Logout.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package home.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("utente", null);
        return;
		
	}

}