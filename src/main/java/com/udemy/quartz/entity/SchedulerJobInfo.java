package com.udemy.quartz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter

public class SchedulerJobInfo {

 
    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String jobClass;
    private String cronExpression;
    private String desc;    
    private String interfaceName;
    private Long repeatTime;
    private Boolean cronJob;
}
