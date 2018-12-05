package br.com.adapt.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.adapt.framework.service.ResourceService;

@Component
public class ScheduledTasks {

	@Autowired
    private ResourceService resourceService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        resourceService.dailyCheck();
    }
}
