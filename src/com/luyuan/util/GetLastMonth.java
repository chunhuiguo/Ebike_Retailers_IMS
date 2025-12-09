package com.luyuan.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GetLastMonth {

	public static List<String> lastMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		try {
			cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			list.add(new SimpleDateFormat("yyyy").format(cal.getTime()));
			list.add(new SimpleDateFormat("MM").format(cal.getTime()));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
