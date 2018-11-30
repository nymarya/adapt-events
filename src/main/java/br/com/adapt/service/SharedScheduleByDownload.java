package br.com.adapt.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import br.com.adapt.model.Resource;
import br.com.adapt.model.Task;

public class SharedScheduleByDownload<T extends Resource> implements SharedSchedule<T> {

	@Override
	public void export(List<T> resources, HttpServletRequest request, HttpServletResponse response) throws Exception {
		buildCsvDocument(resources, request, response);
	}

	/**
	 * Cria planilha
	 * @see https://aboullaite.me/spring-boot-excel-csv-and-pdf-view-example/
	 * 
	 * @param model
	 * @param workbook
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void buildCsvDocument(List<T> tasks, HttpServletRequest request, HttpServletResponse response) throws Exception {

	    response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

	    
	    String[] header = {"Firstname","LastName","LastName","JobTitle","Company","Address","City","Country", "PhoneNumber"};
	    ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
	            CsvPreference.STANDARD_PREFERENCE);

	    csvWriter.writeHeader(header);

	    for(T user : tasks){
	        csvWriter.write(user, header);
	    }
	    csvWriter.close();


	}

}
