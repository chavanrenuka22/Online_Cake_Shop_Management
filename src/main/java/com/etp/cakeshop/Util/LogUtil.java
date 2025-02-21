package com.etp.cakeshop.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Swapnil Utility class for log ID generation and extraction.
 *         <p>
 *         This class provides methods to generate unique log IDs using a
 *         timestamp and a UUID, ensuring each log entry has a distinct
 *         identifier. It also contains a placeholder method for extracting a
 *         log ID from a JSON string.
 *         </p>
 *
 */
public class LogUtil {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String generateLogId() {
		String timestamp = DATE_FORMAT.format(new Date());
		String uniqueId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return "LOG-" + timestamp +  "#";
	}

	public static String getLogIdFromJson(String inputJsonString) {
		return null;
	}

}
