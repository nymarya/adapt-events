package br.com.adapt.framework.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.adapt.framework.model.Resource;


public class DateFormatterAdapt {
	
	/**
	 * Retorna as datas de início e fim de um recurso
	 * @param resource
	 * @return
	 */
	public ArrayList<String> getDateTime(Resource resource) {
		// set the date
	    Calendar start = Calendar.getInstance();

	    // "calculate" the begining of the week
	    start.add(Calendar.DAY_OF_WEEK, start.getFirstDayOfWeek() - start.get(Calendar.DAY_OF_WEEK));	    
	    start.add(Calendar.DAY_OF_WEEK, resource.getDay());
	   
	    start.set(Calendar.HOUR_OF_DAY, resource.getStartDate().getHour());
	    start.set(Calendar.MINUTE, resource.getStartDate().getMinute());
	    start.set(Calendar.SECOND, resource.getStartDate().getSecond());
	    
	    Calendar end = (Calendar) start.clone();
	    end.set(Calendar.HOUR_OF_DAY, resource.getEndDate().getHour());
	    end.set(Calendar.MINUTE, resource.getEndDate().getMinute());
	    end.set(Calendar.SECOND, resource.getEndDate().getSecond());
	    
	    String formattedStartDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(start.getTime());
	    String formattedEndDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(end.getTime());
	    ArrayList<String> result= new ArrayList<>();
	    result.add(formattedStartDate);
	    result.add(formattedEndDate);
	    return result;
	}

}
