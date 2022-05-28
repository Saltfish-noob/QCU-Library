package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.IBookDao;
import edu.qcu.dao.IBorrowLogDao;
import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;
import edu.qcu.service.IBorrowLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service("borrowLog")
@Transactional
public class BorrowLogServiceImpl implements IBorrowLogService {

    private final IBorrowLogDao borrowLogDao;

    @Autowired
    public BorrowLogServiceImpl(IBorrowLogDao borrowLogDao) {
        this.borrowLogDao = borrowLogDao;
    }

    private IBookDao bookDao;

    @Autowired
    public void setBookDao(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public List<BorrowLog> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return borrowLogDao.findAll();
    }

    @Override
    public void addLog(@Param("id") String id, @Param("bookListId") List<String> bookListId) throws Exception {
        Date borrow_Time = new Date(System.currentTimeMillis());
        Date Due_Time = new Date(System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000L));
        BorrowLog borrowLog = new BorrowLog();
        borrowLog.setUserId(id);
        borrowLog.setBorrowDate(borrow_Time);
        borrowLog.setDueDate(Due_Time);
        borrowLog.setStatus(0);
        if (bookListId != null)
            for (String bookId : bookListId) {
                System.out.println(bookId);
                if (bookId != null) {
                    borrowLog.setBookId(bookId);
                    borrowLogDao.addLog(borrowLog);
                }
            }
    }

    @Override
    public List<BorrowLog> findLogByUserId(String userId) throws Exception {
        return borrowLogDao.findLogByUserId(userId);
    }

    @Override
    public void returnSingleBook(BorrowLog borrowLog) throws Exception {
        bookDao.returnBook(borrowLog);
        borrowLog.setReturnDate(new Date(System.currentTimeMillis()));
        borrowLogDao.returnSingleBook(borrowLog);
        //更新session
        List<String> logIdByUserId = borrowLogDao.findLogIdByUserId((String) session.getAttribute("userId"));
        session.setAttribute("haveBorrow",logIdByUserId.size());
        session.setAttribute("LogId",logIdByUserId);
    }

    @Override
    public Integer findLogCountByBookId(String id) throws Exception {
        return borrowLogDao.findLogCountByBookId(id);
    }

    @Override
    public void delBorrowLogByBookId(String id) throws Exception {
        borrowLogDao.delBorrowLogByBookId(id);
    }

    @Override
    public void delBorrowLogByUserId(String userId) throws Exception {
        borrowLogDao.delBorrowLogByUserId(userId);
    }

    @Override
    public List<BorrowLog> findAll() throws Exception {
        return borrowLogDao.findAll();
    }

    @Override
    public List<BorrowLog> findPersonBorrowLog(String userId) throws Exception {
        return borrowLogDao.findPersonBorrowLog(userId);
    }

    @Override
    public Integer borrowCount(String userId, String ISBN) throws Exception {
        return borrowLogDao.borrowCount(userId,ISBN);
    }

    @Override
    public Double borrowTime(String userId, String ISBN) throws Exception {
        return borrowLogDao.borrowTime(userId,ISBN);
    }

    @Override
    public Collection<BorrowLog> findRecommendBook(String s) throws Exception {
        return borrowLogDao.findRecommendBook(s,(String)session.getAttribute("userId"));
    }

    @Override
    public Float getRatingByBookId(String bookId) throws Exception {
        return borrowLogDao.getRatingByBookId(bookId);
    }

    @Override
    public List<BorrowLog> findLogByBookId(String id) throws Exception {
       return borrowLogDao.findLogByBookId(id);
    }
}
