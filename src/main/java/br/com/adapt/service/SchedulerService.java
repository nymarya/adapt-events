/**
 * 
 */
package br.com.adapt.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.model.Resource;
import br.com.adapt.domain.Type;
import br.com.adapt.model.Freeblock;

import br.com.adapt.repository.SchedulerRepository;
import br.com.adapt.repository.TagRepository;
import br.com.adapt.repository.ResourceRepository;



/**
 * @author mayra
 *
 */

@Service
@Transactional(readOnly = false)
public abstract class SchedulerService<T extends Resource> {
	
	@Autowired
	private SchedulerRepository schedulerRepository;
	
	
	protected ResourceService resourceService;
	
	
	@Autowired
	private UserService userService;
	
	
	// Lista de tarefas rotina
	protected List< List<T> > routineTasks = new ArrayList< List<T> >();

	// lista de tarefas de temporarias
    protected List<T> temporaryTasks = new ArrayList< T >();
    
    // lista de blocos livres
    private List< List<Freeblock> > freeblocks = new ArrayList< List<Freeblock> >(); 

	
	/**
	 * Ao criar um usuário, deve ser criado um 
	 * scheduler paar ele
	 * @param user
	 * @return
	 */
	public Scheduler save(User user) {
		final Scheduler scheduler = new Scheduler();
		scheduler.setUser(user);
		return schedulerRepository.save(scheduler);
	}
	
	@Transactional(readOnly = true)
	public abstract void generateGroupsTask( );
	
	
	
	/**
	 * Ordena tarefas de rotina pela data de início
	 */
	@Transactional(readOnly = true)
	public void orderRoutineByDate() {
		for( int i=0; i<7; i++ ){
			
        	Collections.sort(routineTasks.get(i), new Comparator<T>() {
        		public int compare(T t1, T t2) {
        			return t1.getStartDate().compareTo(t2.getStartDate());
        		}
        	});
        		
        }
	}
	
	/**
	 * Ordena tarefas pela prioridade
	 */
	public abstract void orderTemporaryTasksByPriority();
	
	
	
	@Transactional(readOnly = true)
	public void generateFreeBlocks() {
		
	      
        String[] hours = {"07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
        				  "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
        				  "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
        				  "20:00", "20:30", "21:00", "21:30", "22:00"};
        
        
        // percorre as listas de cada dia da semana
        for( int d=0; d<7; d++ ){	
	        
        	LocalTime lastEndDate = LocalTime.of(07, 00);
        	
	        // percorre tarefas rotineiras
	        for( int i=0; i<routineTasks.get(d).size(); i++ ){	
	        		
	    		// recupera tarefa rotina da vez
	    		Resource routineTask = routineTasks.get(d).get(i);
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
	}
	
	/**
	 * Tenta distribuir as tarefas temporárias a partir do dia atual.
	 */
	public void distributeTaskTime() {
		
		boolean update = true;
		
		for( int i=0; i<temporaryTasks.size(); i++ ){
			
			update = true;
			
			// percorre blocos livres verificando se tarefa "cabe" lá dentro
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK)-1; 
			for( int j=day; j<7; j++ ){
				
				
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
						
						temporaryTasks.get(i).setEndDate( endTime );
						
						
						// diminui tempo do bloco livre
						block.setStartDate( endTime );
						
						update = false;
						
					}
					
					
				}
				
			}
			
		}
	}
	
	/**
	 * Gera a distribuição de tarefas
	 * @return
	 */
	public List< List< Freeblock > > generate(){
		
		// recupera usuario ativo
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		freeblocks.clear();
        routineTasks.clear();
        // inicializa lista de tarefas de rotina
        for( int i=0; i<7; i++ ){
        	routineTasks.add( new ArrayList<T>() );
        }
        
        temporaryTasks = new ArrayList<T>();
        
        // recupera lista de tarefas do usuario
        List<Resource> resources = user.getScheduler().getTasks();
        
        
        // gera listas com tarefas rotina e temporárias
        generateGroupsTask(  );
		
		
		// ordena tarefas de rotina
        orderRoutineByDate();

        
        // inicializa lista de blocos livres 
        for( int i=0; i<7; i++ ){
        	freeblocks.add( new ArrayList<Freeblock>() );
        }
        
        
        // gera blocos livres
        generateFreeBlocks();


        
        // ordena lista de tarefas temporárias   
        orderTemporaryTasksByPriority();

        
        // alocar tempo das tarefas
        distributeTaskTime();
		
		
		return freeblocks;
		
	}
	
}
