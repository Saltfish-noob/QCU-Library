package edu.qcu.service;

import edu.qcu.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    List<SysLog> findAll(Integer page,Integer pageSize)throws Exception;

    void save(SysLog sysLog)throws Exception;
}
