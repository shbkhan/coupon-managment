package com.shehab.couponmngmnt.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public Timestamp getCurrentTimestamp() {
		LocalDateTime localTime = LocalDateTime.now();
		return Timestamp.valueOf(localTime);
	}
	
}
