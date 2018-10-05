/**
 * 
 */
package br.com.adapt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.model.Scheduler;
import br.com.adapt.model.User;
import br.com.adapt.model.Task;
import br.com.adapt.model.Freeblock;

import br.com.adapt.repository.SchedulerRepository;
import br.com.adapt.repository.TagRepository;



/**
 * @author mayra
 *
 */
@Service
@Transactional(readOnly = true)
public class SchedulerService {
	
	@Autowired
	private SchedulerRepository schedulerRepository;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Ao criar um usuário, deve ser criado um 
	 * scheduler paar ele
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public Scheduler save(User user) {
		final Scheduler scheduler = new Scheduler();
		scheduler.setUser(user);
		return schedulerRepository.save(scheduler);
	}
	
	
	public SortedMap<Date, Date> generate(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		
		
		// recupera blocos livres do banco de dados
		List<Freeblock> blocks =  user.getScheduler().getFreeBlocks();
		
        
		// organiza os blocos livres no map
	    // Map<Date, Date> freeBlocks = new HashMap<Date, Date>();
	    SortedMap<Date, Date> freeBlocks = new TreeMap<Date, Date>();

	    for( int i=0; i<blocks.size(); i++ ){
	    	Date startDate = blocks.get(i).getStartDate();
	    	Date endDate = blocks.get(i).getEndDate();
	    	freeBlocks.put(startDate, endDate);
	    }
	    
	    // STUB: ordena map
	    
	    
		// recupera tarefas do banco de dados
		List<Task> tasks = user.getScheduler().getTasks();
		
        // STUB: ordena tarefas com base na data
		
		
		// ALGORITMO DE ALOCAÇÃO
		// percorre lista de tarefas
		for( int i=0; i<tasks.size(); i++ ){
			
			// percorre lista de slots
			Iterator iterator = freeBlocks.entrySet().iterator();
		    while (iterator.hasNext()) {
		    	Map.Entry block = (Map.Entry) iterator.next();
		        
		    	Date startDate = (Date) block.getKey();
		    	Date endDate = (Date) block.getValue();
		    	
		    	// STUB: efetuar diferença e recuperar horas livres
		    	
				//if( tasks.get(i).getExpectedTime() <  ){
		    		
		    		// aloca tarefa	
		    		// mexe no map alterando os blocos de tempo livre e sai do for
		    		
				//}
		    	
		    					
			}
		    
		    // STUB: se não conseguir alocar alguma tarefa, manda mensagem ao usuario
		}
		
		
		return freeBlocks;
	}
	
	
}
