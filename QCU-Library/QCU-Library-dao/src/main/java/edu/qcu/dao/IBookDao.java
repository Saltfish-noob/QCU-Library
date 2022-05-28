package edu.qcu.dao;


import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;
import edu.qcu.domain.RecommendBookIndex;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IBookDao {

    @Select("select * from books")
    List<Book> findAll();

    @Select("select categoryName from Category where id=#{Category}")
    String findCategoryNameById(Integer category);

    @Select("select * from books where id=#{id}")
    Book findById(String id);

    @Update("update books set category=#{category},bookName=#{bookName},author=#{author},press=#{press},status=#{status},ISBN=#{ISBN},image=#{image} where ISBN=#{ISBN}")
    void updateBook(Book book);

    @Insert("insert into books(category,bookName,author,press,status,ISBN,image)values(#{category},#{bookName},#{author},#{press},#{status},#{ISBN},#{image})")
    void addBook(Book book);

    @Select("select books.*,IFNULL(rating,0) rating from books LEFT JOIN (SELECT bookId,AVG(rating) AS rating FROM borrowlog GROUP BY (SELECT ISBN FROM books WHERE id = bookId)) AS ratetable ON books.id=ratetable.bookId GROUP BY ISBN")
    List<Book> findAllBookByGroup();

    @Select("select books.*,IFNULL(rating,0) rating from books LEFT JOIN (SELECT bookId,AVG(rating) AS rating FROM borrowlog GROUP BY (SELECT ISBN FROM books WHERE id = bookId)) AS ratetable ON books.id=ratetable.bookId where category=#{category} GROUP BY ISBN")
    List<Book> findBookByCategory(String category);

    @Select("select books.*,IFNULL(rating,0) rating from books LEFT JOIN (SELECT bookId,AVG(rating) AS rating FROM borrowlog GROUP BY (SELECT ISBN FROM books WHERE id = bookId)) AS ratetable ON books.id=ratetable.bookId where rating>=#{rating} AND rating<#{rating_up} GROUP BY ISBN")
    List<Book> findBookByRating(@Param("rating") Double rating,@Param("rating_up") double rating_up);

    @Select("select * from books where ISBN=#{ISBN} AND status=#{status}")
    List<Book> findBookByStatus(@Param("status") Integer status,@Param("ISBN") String ISBN);

    @Select("select books.*,IFNULL(rating,0) rating from books LEFT JOIN (SELECT bookId,AVG(rating) AS rating FROM borrowlog GROUP BY (SELECT ISBN FROM books WHERE id = bookId)) AS ratetable ON books.id=ratetable.bookId WHERE ISBN=#{s} GROUP BY ISBN")
    Book findBookByISBN(String s);

    @Update("update books set status=0 where id=#{bookId}")
    void borrowing(String bookId);

    @Update("update books set status=1 where Id=#{bookId}")
    void returnBook(BorrowLog borrowLog);

    @Delete("delete from books where id=#{id}")
    void delBook(String id);

    @Select("select * from books Group By ISBN")
    List<Book> findAllGroupByISBN();

    @Select("SELECT * FROM bookEuclideanMetric WHERE bookISBN1=#{isbn} OR bookISBN2=#{isbn} ORDER BY `index` DESC")
    List<RecommendBookIndex> findRecommendBook(String isbn);

    @Select("select * from books where category=#{category} GROUP BY ISBN order by (SELECT COUNT(*) FROM borrowlog WHERE bookId=id) limit 4")
    List<Book> findBestSeller(Integer category);

    @Select("SELECT * FROM books WHERE MATCH (bookName,author) AGAINST (#{search})")
    List<Book> findBookByCondition(String search);

    @Select("SELECT rating FROM ( SELECT id,category,bookname,author,press,`status`,ISBN,image,IFNULL( rating, 0 ) rating FROM books LEFT JOIN ( SELECT bookId, AVG( rating ) AS rating FROM borrowlog GROUP BY( SELECT ISBN FROM books WHERE id = bookId )) AS ratetable ON books.id = ratetable.bookId GROUP BY ISBN ) AS d WHERE ISBN = '9787545521177'")
    Float findRatingByISBN(String isbn);

    @Select("select COUNT(*) from books where ISBN=#{isbn}")
    Integer findBookCountByISBN(String isbn);

    @Update("update books set category=#{category},bookName=#{bookName},author=#{author},press=#{press},status=#{status},ISBN=#{ISBN},image=#{image} where id=#{id}")
    void updateBookById(Book book);

    @Select("SELECT * FROM books Right JOIN (SELECT bookId,AVG( rating ) AS rating FROM borrowlog GROUP BY ( SELECT ISBN FROM books WHERE bookId = id ) ORDER BY COUNT(*)* AVG( rating ) DESC LIMIT 0,4)as b ON id = b.bookId")
    List<Book> findBookByBorrowLogCount();

    @Select("select ISBN from books where id=#{id}")
    String findISBNById(String id);
}
