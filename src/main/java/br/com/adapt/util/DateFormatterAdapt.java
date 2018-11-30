package br.com.adapt.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.adapt.model.Task;

public class DateFormatterAdapt {
	
	public ArrayList<String> getDateTime(Task task) {
		// set the date
	    Calendar start = Calendar.getInstance();

	    // "calculate" the begining of the week
	    start.add(Calendar.DAY_OF_WEEK, start.getFirstDayOfWeek() - start.get(Calendar.DAY_OF_WEEK));	    
	    start.add(Calendar.DAY_OF_WEEK, task.getDay());
	   
	    start.set(Calendar.HOUR_OF_DAY, task.getStartDate().getHour());
	    start.set(Calendar.MINUTE, task.getStartDate().getMinute());
	    start.set(Calendar.SECOND, task.getStartDate().getSecond());
	    
	    Calendar end = (Calendar) start.clone();
	    end.set(Calendar.HOUR_OF_DAY, task.getEndDate().getHour());
	    end.set(Calendar.MINUTE, task.getEndDate().getMinute());
	    end.set(Calendar.SECOND, task.getEndDate().getSecond());
	    
	    String formattedStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start.getTime());
	    String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end.getTime());
	    ArrayList<String> result= new ArrayList<>();
	    result.add(formattedStartDate);
	    result.add(formattedEndDate);
	    return result;
	}

}
