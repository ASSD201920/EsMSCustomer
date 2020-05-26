package it.unisannio.service;

import it.unisannio.model.Customer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BranchLocal {
	public void createCustomer(String cf, String fn, String ln) throws Exception;
	public Customer getCustomer(String cf);

}
