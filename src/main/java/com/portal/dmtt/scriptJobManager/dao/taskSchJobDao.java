package com.portal.dmtt.scriptJobManager.dao;

import com.portal.dmtt.model.TaskSchJob;

public interface taskSchJobDao {


    /**
     *
     * @param taskId = find the value by using the primary key(ID)
     * @return
     */
    TaskSchJob findTaskSchJob(int taskId);

}
