package es.smt.appfrigo.manager;

import java.util.Iterator;
import java.util.List;

import es.smt.appfrigo.bean.Channel;

public class ChannelManager {

	private static ChannelManager instance;
	
	public ChannelManager(){}
	
	public static ChannelManager getInstance()
	{
		if (instance == null )
		{
			instance = new ChannelManager();
		}
		return instance;
	}
	
	public List<Channel> getActive(List<Channel> list)
	{
		Iterator<Channel> i = list.iterator();
		Channel c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	public List<Channel> getDeactive(List<Channel> list)
	{
		Iterator<Channel> i = list.iterator();
		Channel c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	
}
