package edu.qcu.service;

import edu.qcu.domain.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll()throws Exception;

    List<Book> findAll(Integer page, Integer pageSize)throws Exception;

    String findCategoryNameById(Integer category)throws Exception;

    Book findById(String id)throws Exception;

    void updateBook(Book book)throws Exception;

    void addBook(Book book,Integer number)throws Exception;

    List<Book> findAllBookByGroup(Integer page,Integer pageSize)throws Exception;

    List<Book> findBookByCategory(String category, Integer page, Integer pageSize)throws Exception;

    List<Book> findBookByRating(Double rating, Integer page, Integer pageSize)throws Exception;

    List<Book> findBookByStatus(Integer Status, String isbn)throws Exception;

    List<Book> cartFindBook(List<String> bookList1)throws Exception;

    void borrowing(List<String> bookListId)throws Exception;

    void delBook(String id)throws Exception;

    List<Book> findRecommendBook(String isbn)throws Exception;

    List<Book> findBestSeller(Integer category)throws Exception;

    List<Book> findBookByCondition(String search, Integer page, Integer pageSize)throws Exception;

    Integer findBookCountByISBN(String isbn)throws Exception;

    void updateBookById(Book book)throws Exception;

    List<Book> findBookByBorrowLogCount()throws Exception;
}
