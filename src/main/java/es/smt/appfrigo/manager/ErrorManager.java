package es.smt.appfrigo.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

public class ErrorManager {

	private static ErrorManager instance;
	
	public ErrorManager(){}
	
	public static ErrorManager getInstance()
	{
		if (instance == null )
		{
			instance = new ErrorManager();
		}
		return instance;
	}
	

	public List<String> getBindingResultMessage(List<ObjectError> errors)
	{
		List<String>  message = new ArrayList<String>();
		for (int i = 0; i < errors.size(); i++) {
			message.add(errors.get(i).getDefaultMessage());
		}
		
		return message;
	}
	
	public String getBindingResultMessageString(List<ObjectError> errors)
	{
		String message = "";
		for (int i = 0; i < errors.size(); i++) {
			message += errors.get(i).getDefaultMessage()+"\n";
		}
		
		return message;
	}

}
