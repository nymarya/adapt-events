package br.com.adapt.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.adapt.framework.service.SchedulerService;

@Component
public class ScheduledTasks {

	@Autowired
    private SchedulerService schedulerService;

	/**
	 * Todo dia às 22h
	 */
    @Scheduled(cron="0 0 22 * * *")
    public void reportCurrentTime() {
        schedulerService.dailyCheck();
    }
}
