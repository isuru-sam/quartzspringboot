package com.udemy.quartz.job;

import java.util.stream.IntStream;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SimpleJob Start1................");
        
        try {
			context.getScheduler().getContext().get("applicationContext");
		} catch (SchedulerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        JobKey jk=context.getJobDetail().getKey();
        log.info("group"+ jk.getGroup());
        log.info("name"+ jk.getName());
        IntStream.range(0, 5).forEach(i -> {
            log.info("Counting - {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
        log.info("SimpleJob End................");
    }
}
