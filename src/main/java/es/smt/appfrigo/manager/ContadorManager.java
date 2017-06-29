package es.smt.appfrigo.manager;

import java.util.List;

import es.smt.appfrigo.bean.Contador;

public class ContadorManager {

	private static ContadorManager instance;
	
	public ContadorManager(){}
	
	public static ContadorManager getInstance()
	{
		if (instance == null )
		{
			instance = new ContadorManager();
		}
		return instance;
	}
	
	public Contador getTotalContador(List<Contador> contador)
	{
		Contador total = new Contador();
		for (Contador s : contador) {
			total.setPeople(s.getPeople()+total.getPeople());
			total.setSold(s.getSold()+total.getSold());
		}
		
		return total;
	}
}
