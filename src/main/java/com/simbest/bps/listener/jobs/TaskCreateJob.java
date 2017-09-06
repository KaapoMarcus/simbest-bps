/**
 * 版权所有 © 北京晟壁科技有限公司 2008-2016。保留一切权利!
 */
package com.simbest.bps.listener.jobs;

import com.simbest.bps.query.model.ActBusinessStatus;



/**
 * 用途：用户创建时执行的任务
 */
public interface TaskCreateJob {

    void execution(ActBusinessStatus businessStatus, String uniqueCode);
}
