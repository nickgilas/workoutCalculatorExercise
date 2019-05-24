package com.nick.workoutCalculator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.joda.time.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom Jackson json deserializer which converts string dates to
 * {@link DataTime} objects
 * 
 * @author Nick Gilas
 *
 */
public class CustomDateDeserializer extends StdDeserializer<DateTime> {
	private static final long serialVersionUID = -8367872195964335940L;

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public CustomDateDeserializer() {
		this(null);
	}

	public CustomDateDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public DateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException, JsonProcessingException {
		String dateStr = jsonparser.getText();
		try {
			Date date = formatter.parse(dateStr);
			DateTime dt = new DateTime(date);
			return dt;
		} catch (ParseException e) {
			throw new RuntimeException("Error occurred while trying to deserialize date: " + dateStr + ", message: " + e.getMessage(), e);
		}
	}
}
