package br.com.adapt.service;

import java.util.List;

import br.com.adapt.model.Resource;
import br.com.adapt.model.Task;

/**
 * Interface responsável por definir o modo de compartilhamento
 * do cronograma
 * @author mayra
 *
 */
public interface SharedSchedule<T extends Resource> {
	
	public void export(List<T> resources);
}
