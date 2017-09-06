
package com.simbest.bps.listener.schedule;

import com.simbest.cores.admin.task.distributed.DistributedJobExecutor;
import com.simbest.cores.exceptions.Exceptions;
import com.simbest.cores.service.IGenericService;
import com.simbest.cores.utils.DateUtil;
import com.simbest.bps.listener.jobs.TaskCompletedJob;
import com.simbest.bps.listener.jobs.TaskCreateJob;
import com.simbest.bps.listener.schedule.model.TaskCallbackRetry;
import com.simbest.bps.query.model.ActBusinessStatus;
import com.simbest.bps.query.service.IActBusinessStatusService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 用途：每十分钟检查一次待办生成与待办核销的回调接口执行情况，执行回调失败的任务，一个尝试执行5次(基于SQL)
 * 作者: lishuyi 
 * 时间: 2016-04-18  18:08 
 */
@Component
public class TaskCallbackRetrySchedule extends ApplicationObjectSupport {
    private static transient final Log log = LogFactory.getLog(TaskCallbackRetrySchedule.class);

    @Autowired
    @Qualifier("taskCallbackRetryService")
    private IGenericService<TaskCallbackRetry, Long> taskCallbackRetryService;

    @Autowired
    private IActBusinessStatusService statusService;

    @Autowired
    private DistributedJobExecutor distributedJobExecutor;

    @Scheduled(cron = "0 0/10 * * * ?")
    //@Scheduled(cron = "0/5 * * * * ?")
    private void checkFailedAndRun() throws ClassNotFoundException {
        if (distributedJobExecutor.checkMasterIsMe()) {
            log.debug("TaskCallbackRetrySchedule Start .....................................");
            Collection<TaskCallbackRetry> list = taskCallbackRetryService.getAll();
            for (TaskCallbackRetry o : list) {
                boolean tryResult = true;
                try {
                    TaskCreateJob createJob = (TaskCreateJob) getApplicationContext().getBean(Class.forName(o.getTaskJobClass()));
                    if (o.getCallbackType().equals("CreateCallback")) {
                        if (null != o.getActBusinessStatusId()) {
                            ActBusinessStatus status = statusService.getById(o.getActBusinessStatusId());
                            createJob.execution(status, o.getUniqueCode());
                        }
                    } else {
                        TaskCompletedJob completedJob = (TaskCompletedJob) getApplicationContext().getBean(Class.forName(o.getTaskJobClass()));
                        if (null != o.getActBusinessStatusId()) {
                            ActBusinessStatus status = statusService.getById(o.getActBusinessStatusId());
                            completedJob.execution(status, o.getUniqueCode());
                        }
                    }
                } catch (Exception e) {
                    Exceptions.printException(e);
                    tryResult = false;
                } finally {
                    int ret;
                    if (tryResult) {
                        ret = taskCallbackRetryService.delete(o.getId());
                    } else {
                        o.setLastExecuteDate(DateUtil.getCurrent());
                        ret = taskCallbackRetryService.update(o);
                    }
                    log.debug(ret);
                }
            }
            log.debug("TaskCallbackRetrySchedule End .....................................");
        }
    }
}
