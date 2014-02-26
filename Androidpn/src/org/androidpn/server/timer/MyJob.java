package org.androidpn.server.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {  
    
    private MyTask myTask;  
  
    protected void executeInternal(JobExecutionContext context)  
            throws JobExecutionException {  
          
        myTask.run();  
    }  
  
    public MyTask getMyTask() {  
        return myTask;  
    }  
  
    public void setMyTask(MyTask myTask) {  
        this.myTask = myTask;  
    }  
}  
