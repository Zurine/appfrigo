package es.smt.appfrigo.rest;

import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.ErrorDTO;
import es.smt.appfrigo.model.ResponseDTO;

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
	


    /**************************CODIGOS DE ERROR DE AUTENTICACION************************/

    public ErrorDTO GetError(ErrorDTO error, ErrorDTO errorToken)
    {
        ErrorDTO e = new ErrorDTO();

        if (error.getCode() == Constants.codeOK || error.getCode() == Constants.codeLogout)
        {
            e = error;
            e.setDesc(null);
        }
        else if (error.getCode() == Constants.codeGeneric)
        {
            e = error;
            e.setDesc(Constants.wrong);
        }
        else
        {
            int i = Math.abs(error.getCode());
            while (i >= 10)
                i /= 10;
            if (i == 2)
            {
                e.setCode(Constants.codeOK);
                e.setDesc(error.getDesc());
            }
            else if (i == 4)
            {
                e.setCode(1);
                e.setDesc(Constants.wrong);
            }
            else
            {
            	e.setCode(1);
            	e.setDesc(error.getDesc());
            }
        }

     
        return e;
    }

    public ErrorDTO CheckResult(ResponseDTO r)
    {
        ErrorDTO e = null;
        if (r != null && !r.equals("") && r.getError()!=null)
        {
            e = GetError(r.getError(), r.getErrorToken());
        }
        else e = SetError(); //Set

        return e;
    }

    public ErrorDTO SetError()
    {
        ErrorDTO e = new ErrorDTO(Constants.codeError, Constants.wrong);

        return e;
    }

    public ErrorDTO SetOk()
    {
        ErrorDTO e = new ErrorDTO(Constants.codeOK, "");

        return e;
    }
}
