package utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateHelper {
	private static  LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static  String formatDate(Date dateToConvert) {
		LocalDate dateToLocal = convertToLocalDateViaInstant(dateToConvert);
		return dateToLocal.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
	}
	
}
