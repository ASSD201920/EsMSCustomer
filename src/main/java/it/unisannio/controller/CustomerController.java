package it.unisannio.controller;

import it.unisannio.model.Customer;
import it.unisannio.service.BranchLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Consumes("text/plain")
@Produces("text/plain")
@Path("/customers")
public class CustomerController {
	@EJB
	private BranchLocal branch;

	@Resource UserTransaction utx; // To handle user transactions from a Web component


	public CustomerController() {
		super();

	}

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createCustomer(Customer c) {
		System.out.println(c);
		try {
			branch.createCustomer(c.getCF(), c.getFirstName(), c.getLastName());
			return Response.created(new URI("/customers/"+c.getCF())).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@GET
	public Response test() {
		System.out.println("test");
		try {
			branch.getCustomer("");
			return Response.ok().build();
		} catch (Exception e) {return Response.status(500).build();}

	}
}
