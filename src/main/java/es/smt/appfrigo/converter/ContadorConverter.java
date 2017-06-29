package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.Contador;
import es.smt.appfrigo.model.ContadorDTO;

public class ContadorConverter {

	private static ContadorConverter instance;
	
	public ContadorConverter(){}
	
	public static ContadorConverter getInstance()
	{
		if (instance == null )
		{
			instance = new ContadorConverter();
		}
		return instance;
	}
	
	public Contador dtoToBean(ContadorDTO dto)
	{
		Contador c = new Contador();
		c.setDescription(dto.getDescription());
		c.setDate(dto.getServerDate());
		c.setId(dto.getContadorId());
		c.setNumber(dto.getNumber());
		c.setPeople(dto.getPeople());
		c.setSold(dto.getSold());
		
		return c;
	}
	
	public List<Contador> dtoToBeanList(List<ContadorDTO> contador)
	{
		List<Contador> list = new ArrayList<Contador> ();
		for(ContadorDTO e: contador)
		{
			list.add(dtoToBean(e));
		}
		
		return list;
	}
	

}
