/**
 * 
 */
package br.com.adapt.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
	
	
	public List< List< Freeblock > > generate(){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		
       
		
		

        // lista de tarefas de rotina
        List< List<Task> > routineTasks = new ArrayList< List<Task> >();
        
        for( int i=0; i<7; i++ ){
        	routineTasks.add( new ArrayList<Task>() );
        }
        
        
     // lista de tarefas de temporarias
        List< Task > temporaryTasks = new ArrayList< Task >();
        
        
        
        
        
        List<Task> tasks = user.getScheduler().getTasks();
        
        
        // percorre todas as tarefas
        //for( int i=0; i<tasks.size(); i++ ){
		for( Task task : tasks ){
        	//Task task = tasks.get(i);
        	
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
        		
        	} else {
        		temporaryTasks.add(task);
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

        
        // listas de blocos livres 
        List< List<Freeblock> > freeblocks = new ArrayList< List<Freeblock> >(); 
        for( int i=0; i<7; i++ ){
        	freeblocks.add( new ArrayList<Freeblock>() );
        }
        
        
        
        String[] hours = {"07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
        				  "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
        				  "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
        				  "20:00", "20:30", "21:00", "21:30", "22:00"};
        
        
        // percorre as listas de cada dia da semana
        for( int d=0; d<7; d++ ){	
	        
        	//LocalTime lastEndDate = "07:00";
        	//LocalTime lastEndDate = LocalTime.parse("07:00");
        	LocalTime lastEndDate = LocalTime.of(07, 00);
        	
	        // percorre tarefas rotineiras
	        for( int i=0; i<routineTasks.get(d).size(); i++ ){	
	        		
	    		// recupera tarefa rotina da vez
	    		Task routineTask = routineTasks.get(d).get(i);
	    		LocalTime startDate = routineTask.getStartDate();
	    		
	    		// procura esse horário na lista
	    		for( int k=0; k<31; k++ ){
	    		
	    			// quando achar
	    			if( startDate == LocalTime.parse(hours[k]) ){
	    				
	    				if( !(lastEndDate  == LocalTime.parse(hours[k]) ) ) {
	    					// cria bloco
		    				Freeblock block = new Freeblock();
		    				block.setStartDate( lastEndDate );
		    				block.setEndDate( startDate );
		    				freeblocks.get(d).add(block);
		    				
	    				}
	    				
	    				lastEndDate = routineTask.getEndDate();
	    			
	    			}
	    			
	    			
	    		}
	    		
    			// se for a ultima tarefa rotina do dia
    			if( i == routineTasks.get(d).size()-1 ){
    				
    				Freeblock block = new Freeblock();
    				block.setStartDate( lastEndDate );
    				block.setEndDate( LocalTime.parse(hours[30]) );
    				freeblocks.get(d).add(block);
    				
    			}
	        		
	        }
	        
	        // se nao tiver tarefa naquele dia, bloco livre cheio
	        if( routineTasks.get(d).size() == 0 ){
	        	Freeblock block = new Freeblock();
				block.setStartDate( LocalTime.parse(hours[0]) );
				block.setEndDate( LocalTime.parse(hours[30]) );
				freeblocks.get(d).add(block);
	        }
	        
        
        }
        
     

             
        
        // ordena lista de tarefas temporárias   

    	/*Collections.sort(temporaryTasks, new Comparator<Task>() {
    		public int compare(Task t1, Task t2) {
    			return t1.getStartDate().compareTo(t2.getStartDate());
    		}
    	});*/
    	

    	
		boolean update = true;
        
		// ALGORITMO DE ALOCAÇÃO
		// percorre lista de tarefas
		for( int i=0; i<temporaryTasks.size(); i++ ){
			
			update = true;
			
			// percorre blocos livres verificando se tarefa "cabe" lá dentro
			for( int j=0; j<7; j++ ){
				
				
				for( int k=0; k<freeblocks.get(j).size(); k++ ){
					
					Freeblock block = freeblocks.get(j).get(k);
					
					long freeTime = Duration.between(block.getEndDate(), block.getStartDate()).toMinutes();
					
					// se tempo esperado pra concluir tarefa couber no bloco
					if( temporaryTasks.get(i).getExpectedTime() < freeTime*(-1) 
							&& update==true ){
						
						// atualiza o horário na tarefa
						temporaryTasks.get(i).setDay(j);
						temporaryTasks.get(i).setStartDate( block.getStartDate() );
						LocalTime endTime = block.getStartDate();
						
						endTime = endTime.plusMinutes(temporaryTasks.get(i).getExpectedTime());
						
						
						//System.out.println(temporaryTasks.get(i).getExpectedTime());
						//System.out.println(block.getStartDate());
						//System.out.println(endTime);
						temporaryTasks.get(i).setEndDate( endTime );
						
						// diminui tempo do bloco livre
						block.setStartDate( endTime );
						
						update = false;
						
					}
					
					
				}
				
			}
			
		}
		
		

	
		for( int j=0; j<temporaryTasks.size(); j++ ){
			System.out.println("TASK "+j);
			System.out.println(temporaryTasks.get(j).getDay());
			System.out.println(temporaryTasks.get(j).getStartDate());
			System.out.println(temporaryTasks.get(j).getEndDate());
			System.out.println(temporaryTasks.get(j).getExpectedTime());
		}
		
	
        
		
		return freeblocks;
	}
	
	
}
