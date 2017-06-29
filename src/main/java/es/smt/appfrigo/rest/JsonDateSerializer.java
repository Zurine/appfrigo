package es.smt.appfrigo.rest;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{

	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
		
//		String dateFormat = "/Date("+ date.getTime()+")/"; 
		String dateFormat ="\"/Date("+ date.getTime()+")/\"";
		gen.writeString(dateFormat);
	}
}
