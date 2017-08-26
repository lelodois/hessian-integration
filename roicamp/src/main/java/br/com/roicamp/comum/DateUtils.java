package br.com.roicamp.comum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.roicamp.exception.DateInvalidException;

public class DateUtils {

	private static String formatDate = "dd/MM/yyyy";
	private static String formatDateTime = "dd/MM/yyyy HH:mm";

	private DateUtils() {
	}

	/**
	 * @param date
	 * @return date in format dd/MM/yyyy
	 */
	public static String toStringDateTruncate(Date date) {
		return new SimpleDateFormat(formatDate).format(date);
	}

	/**
	 * @param dateTime
	 *            in format yyyy-MM-dd
	 * @return
	 * @throws DateInvalidException
	 */
	public static Date toDateTruncate(String dateTime) throws DateInvalidException {
		try {
			return org.apache.commons.lang3.time.DateUtils.truncate(new SimpleDateFormat(formatDate).parse(dateTime), Calendar.DATE);
		} catch (Exception e) {
			throw new DateInvalidException(dateTime + " fora do formato: " + formatDate);
		}
	}

	/**
	 * @param date
	 * @return in format dd/MM/yyyy hh:mm:ss
	 */
	public static String toStringDateTime(Date dateTime) {
		return new SimpleDateFormat(formatDateTime).format(dateTime);
	}

	/**
	 * @param date1
	 * @param date2
	 * @return True para mesmo dia
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return date1 != null && date2 != null && org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
	}
}
