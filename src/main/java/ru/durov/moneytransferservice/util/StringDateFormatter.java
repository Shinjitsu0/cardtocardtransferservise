package ru.durov.moneytransferservice.util;

import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringDateFormatter {
    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");

    public Date formatStringToDate(String date) {
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public String formatDateToString(Date date) {
        return formatter.format(date);
    }
}
