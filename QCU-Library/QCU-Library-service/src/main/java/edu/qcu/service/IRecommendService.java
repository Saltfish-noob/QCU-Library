package edu.qcu.service;

import edu.qcu.domain.Book;

import java.util.Collection;
import java.util.List;

public interface IRecommendService {

    public void calculateRecommendValue() throws Exception;

    List<Book> findSimilarBook(String userId)throws Exception;

    List<Book> findHotBook()throws Exception;

    void calculateBookRelation()throws Exception;

    void delRecommendByUserId(String userId) throws Exception;

    void delRecommendByBookId(String id)throws Exception;
}
