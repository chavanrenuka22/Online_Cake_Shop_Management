package com.etp.cakeshop.Helper;

import java.time.LocalDate;

public class IDHelper {
	public static String[] getIDFromJsonInput(String inputJsonString)
	{
		LocalDate myObj =LocalDate.now();
		String[] Ids=new String[3];
		Ids[0] ="ActionID:-save_cake";
		Ids[1]="LOG"+myObj+"_"+System.currentTimeMillis()+"#";
		
		return Ids;
		
	}
}
