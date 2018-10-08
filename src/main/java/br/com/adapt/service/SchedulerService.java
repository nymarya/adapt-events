/**
 * 
 */
package br.com.adapt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import br.com.adapt.domain.Type;
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
	
	
	public List< List<Task> > generate(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		
		
		// recupera blocos livres do banco de dados
		// List<Freeblock> blocks =  user.getScheduler().getFreeBlocks();
		
        
		// organiza os blocos livres no map
	    // Map<Date, Date> freeBlocks = new HashMap<Date, Date>();
	    // SortedMap<Date, Date> freeBlocks = new TreeMap<Date, Date>();

	    /*for( int i=0; i<blocks.size(); i++ ){
	    	Date startDate = blocks.get(i).getStartDate();
	    	Date endDate = blocks.get(i).getEndDate();
	    	freeBlocks.put(startDate, endDate);
	    }*/
        
        // lista de tarefas de rotina
        List< List<Task> > routineTasks = new ArrayList< List<Task> >();
        
        for( int i=0; i<7; i++ ){
        	routineTasks.add( new ArrayList<Task>() );
        }
        
        
        // recupera tarefas do banco de dados
        List<Task> tasks = user.getScheduler().getTasks();
        
        // percorre todas as tarefas
        for( int i=0; i<tasks.size(); i++ ){
        	
        	Task task = tasks.get(i);
        	
        	// verifica se é rotina
        	if( task.getType() == Type.ROUTINE ){
        		
        		// verifica de qual dia da semana é e add tarefa na lista
        		
	        	switch( task.getDay() ) {
	        		case 0: routineTasks.get(0).add(task); break;
	        		case 1: routineTasks.get(1).add(task); break;
	        		case 2: routineTasks.get(2).add(task); break;
	        		case 3: routineTasks.get(3).add(task); break;
	        		case 4: routineTasks.get(4).add(task); break;
	        		case 5: routineTasks.get(5).add(task); break;
	        		case 6: routineTasks.get(6).add(task); break;
	        		default: break;
        		}
        		
        	}
        }
        
        // ordena tarefas de rotina
        for( int i=0; i<7; i++ ){
        	
        	Collections.sort(routineTasks.get(i), new Comparator<Task>() {
        		public int compare(Task t1, Task t2) {
        			return t1.getStartDate().compareTo(t2.getStartDate());
        		}
        	});
        	
        	
        }
       
        
        // percorre a lista de tarefas cadastradas
        /*for( int i=0; i<tasks.size(); i++ ){
        	
        	if(  ){
        		
        	}
        	
        }*/
        
	    
	    // STUB: ordena map
	    
	    
		// recupera tarefas do banco de dados
		//List<Task> tasks = user.getScheduler().getTasks();
		
        // STUB: ordena tarefas com base na data
		
		
		// ALGORITMO DE ALOCAÇÃO
		// percorre lista de tarefas
		/*for( int i=0; i<tasks.size(); i++ ){
			
			// percorre lista de slots
			//Iterator iterator = freeBlocks.entrySet().iterator();
		    //while (iterator.hasNext()) {
		    	//Map.Entry block = (Map.Entry) iterator.next();
		        
		    	//Date startDate = (Date) block.getKey();
		    	//Date endDate = (Date) block.getValue();
		    	
		    	// STUB: efetuar diferença e recuperar horas livres
		    	
				//if( tasks.get(i).getExpectedTime() <  ){
		    		
		    		// aloca tarefa	
		    		// mexe no map alterando os blocos de tempo livre e sai do for
		    		
				//}
		    	
		    					
			}*/
		    
		    // STUB: se não conseguir alocar alguma tarefa, manda mensagem ao usuario
		//}
		
		
		return routineTasks;
	}
	
	
}
