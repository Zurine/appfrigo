package es.smt.appfrigo.validation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;



public class CustomJsonDateDeserializer extends JsonDeserializer<String>
{
	
//	HttpSession session;
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";

	
    @Override
    public String deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
    	    	    	
    	String dateString = jsonparser.getText();
    	
    	@SuppressWarnings("resource")
		long createdate = new Scanner(dateString).useDelimiter("[^0-9]+").nextLong();
    	
    	HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(createdate);
    	
    	SimpleDateFormat displayDateFormatter = new SimpleDateFormat(DATE_FORMAT); 
    	
		String dateInString = displayDateFormatter.format(cal.getTime());
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        
        ZoneId machineZone = TimeZone.getDefault().toZoneId();//   ZoneId.of("Europe/Paris");
        System.out.println("TimeZone : " + machineZone);
        
        ZonedDateTime machineTime = ldt.atZone(machineZone);
        
        
        TimeZone timezone =  (TimeZone)session.getAttribute("timezone");
        
        ZoneId localZone = timezone.toZoneId();

        ZonedDateTime localTime = machineTime.withZoneSameInstant(localZone);
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String response = format.format(localTime);
    	
        return response;
    }
}