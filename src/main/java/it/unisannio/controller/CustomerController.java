package it.unisannio.controller;

import it.unisannio.service.BranchLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private BranchLocal branch;
	
	@Resource UserTransaction utx; // To handle user transactions from a Web component
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("operation");
		String firstName, lastName, cf; 
		String message = "";
		switch (action) {
			case "createCust":
				try {
					cf = request.getParameter("cf");
					firstName = request.getParameter("firstName");
					lastName = request.getParameter("lastName");
				
					branch.createCustomer(cf, firstName, lastName);
					message = "l'utente con codice fiscale " + cf + " è stato registrato nel sistema";
				} catch (Exception e) {
					message = "L'utente non può essere registrato";
				}
				break;
			default:
				message = "Operazione non supportata";
				break;
		}	
		
		response.getWriter().print(message);
	}

}
