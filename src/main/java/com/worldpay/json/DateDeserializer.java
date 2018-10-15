package com.worldpay.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.worldpay.constants.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        try {
            return new SimpleDateFormat(Constants.DATE_FORMAT_STRING).parse(parser.getText());
        } catch (ParseException e) {
            throw new InvalidFormatException(parser, "Please input date in the following format: " + Constants.DATE_FORMAT_STRING, parser.getText(), Date.class);
        }
    }
}
