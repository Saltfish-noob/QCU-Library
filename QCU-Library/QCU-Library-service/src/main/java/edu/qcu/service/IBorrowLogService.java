package edu.qcu.service;

import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;

import java.util.Collection;
import java.util.List;

public interface IBorrowLogService {

    List<BorrowLog> findAll(Integer page, Integer pageSize)throws Exception;

    void addLog(String id, List<String> bookListId)throws Exception;

    List<BorrowLog> findLogByUserId(String userId)throws Exception;

    void returnSingleBook(BorrowLog borrowLog)throws Exception;

    Integer findLogCountByBookId(String id)throws Exception;

    void delBorrowLogByBookId(String id)throws Exception;

    void delBorrowLogByUserId(String userId)throws Exception;

    List<BorrowLog> findAll()throws Exception;

    List<BorrowLog> findPersonBorrowLog(String userId)throws Exception;

    Integer borrowCount(String userId, String ISBN)throws Exception;

    Double borrowTime(String userId, String ISBN)throws Exception;

    Collection<BorrowLog> findRecommendBook(String s)throws Exception;

    Float getRatingByBookId(String bookId)throws Exception;

    List<BorrowLog> findLogByBookId(String id)throws Exception;
}
