package br.com.adapt.application.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adapt.application.model.Course;
import br.com.adapt.framework.domain.Type;
import br.com.adapt.framework.service.SchedulerService;

@Service
public class SchedulerCourseService extends SchedulerService<Course> {

	@Autowired
	private CourseService courseService;
	
	
	@Override
	public void orderTemporaryTasksByPriority() {
		
    	Collections.sort(temporaryTasks, new Comparator<Course>() {
    		public int compare(Course t1, Course t2) {
    			return -(t1.getCategory().compareTo(t2.getCategory()));
    		}
    	});

	}
	


	@Override
	public void generateGroupsTask( ) {
		
		temporaryTasks = courseService.findTemporaryNotDoneByUserAuthenticated();

		List<Course> resources = courseService.findRoutineByUserAuthenticated();
		
		// percorre todas as tarefas
		for( Course resource : resources){
        	
        	// verifica se é rotina
        	if( resource.getType() == Type.ROUTINE ){
        		
        		// verifica de qual dia da semana é e add tarefa na lista
	        	switch( resource.getDay() ) {
	        		case 0: routineTasks.get(0).add(resource); break;
	        		case 1: routineTasks.get(1).add(resource); break;
	        		case 2: routineTasks.get(2).add(resource); break;
	        		case 3: routineTasks.get(3).add(resource); break;
	        		case 4: routineTasks.get(4).add(resource); break;
	        		case 5: routineTasks.get(5).add(resource); break;
	        		case 6: routineTasks.get(6).add(resource); break;
	        		default: break;
        		} 
        		
        	}
        	
        }
		
	}


	@Override
	public int setIntervals( ) {
		return 15;
	}

	
	
}
