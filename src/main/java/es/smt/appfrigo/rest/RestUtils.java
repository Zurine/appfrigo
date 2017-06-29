package es.smt.appfrigo.rest;

import es.smt.appfrigo.model.ResponseDTO;

public class RestUtils {

	private static RestUtils instance;
	
	public RestUtils(){}
	
	public static RestUtils getInstance()
	{
		if (instance == null )
		{
			instance = new RestUtils();
		}
		return instance;
	}
	
	  public ResponseDTO InitializeResponse()
      {
		  ResponseDTO r = new ResponseDTO();
          r.setError(ErrorManager.getInstance().SetError());

          return r;
      }
}
