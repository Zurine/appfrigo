package es.smt.appfrigo.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import es.smt.appfrigo.bean.TimeZone;

public class TimeZoneUtil {

	private static TimeZoneUtil instance;
	private static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";
	private static List<TimeZone> timeZones;
	
	public TimeZoneUtil(){}
	
	public static TimeZoneUtil getInstance()
	{
		if (instance == null )
		{
			instance = new TimeZoneUtil();
		}
		return instance;
	}

	public static List<TimeZone> getTimeZones() {

		if (timeZones == null) {
			timeZones = new ArrayList<TimeZone>();
			
			
			Set<String> zoneIds = ZoneId.getAvailableZoneIds();

			for (String zoneId : zoneIds) {
			    ZoneId zone = ZoneId.of(zoneId);
			    if (zone.getId().matches(TIMEZONE_ID_PREFIXES)) {
			    	TimeZone tz = new TimeZone();
			    	  ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
			    	  tz.setName(zoneId);
			    	  tz.setOffset("(" + zonedDateTime.getOffset() + ") " );
					  timeZones.add(tz);
					}
		        }

		     Collections.sort(timeZones, new Comparator<TimeZone>() {
		        public int compare(final TimeZone t1, final TimeZone t2) {
		          return t1.getName().compareTo(t2.getName());
		        }
		      });
		}
		return timeZones;
	}

		  
}
