package br.com.adapt.application.service;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import br.com.adapt.application.model.Course;
import br.com.adapt.framework.service.SharedSchedule;
import br.com.adapt.framework.util.DateFormatterAdapt;

public class SharedScheduleByDownload implements SharedSchedule<Course> {

	@Override
	public void export(List<Course> resources, HttpServletRequest request, HttpServletResponse response) throws Exception {
		buildCsvDocument(resources, request, response);
	}

	/**
	 * Cria planilha
	 * @see https://aboullaite.me/spring-boot-excel-csv-and-pdf-view-example/
	 * @see https://super-csv.github.io/super-csv/examples_writing.html
	 * 
	 * @param model
	 * @param workbook
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void buildCsvDocument(List<Course> courses, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String formattedDate = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance().getTime());
	    response.setHeader("Content-Disposition", "attachment; filename=\"adapt_"+formattedDate+".csv\"");
	    
	    final String[] header = new String[] { "Títuto", "Descrição", "Data de início","Data de término", "Dificuldade" };
        
	    DateFormatterAdapt formatter = new DateFormatterAdapt();
        
        
        ICsvMapWriter mapWriter = null;
        try {
                mapWriter = new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
                
                
                // write the header
                mapWriter.writeHeader(header); 
                
                for(Course course : courses) {
                	final Map<String, Object> entry = new HashMap<String, Object>();
                    entry.put(header[0], course.getTitle());
                    entry.put(header[1], course.getDescription());
                    entry.put(header[2], formatter.getDateTime(course).get(0));
                    entry.put(header[3], formatter.getDateTime(course).get(1));
                    entry.put(header[4], course.getDificulty());
                    
                    mapWriter.write(entry, header);
                                    	
                }
                
        }
        finally {
                if( mapWriter != null ) {
                        mapWriter.close();
                }
        }


	}

}
