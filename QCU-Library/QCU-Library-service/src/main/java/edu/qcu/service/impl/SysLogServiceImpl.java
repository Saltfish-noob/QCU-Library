package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.ISysLogDao;
import edu.qcu.domain.SysLog;
import edu.qcu.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SysLog")
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    private final ISysLogDao sysLogDao;

    @Autowired
    public SysLogServiceImpl(ISysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
