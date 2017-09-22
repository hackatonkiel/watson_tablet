package de.bewatec.hackerthon.service;

public interface State {

	public void postExecute();
	
	public void preExecute();
	
	public void execute();
		
}
