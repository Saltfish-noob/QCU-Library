package edu.qcu.dao;

import edu.qcu.domain.BorrowLog;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

public interface IBorrowLogDao {

    @Select("select * from borrowLog")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "bookId",property = "bookId"),
            @Result(column = "bookId",property = "book",one = @One(select = "edu.qcu.dao.IBookDao.findById")),
            @Result(column = "userId",property = "userId"),
            @Result(column = "userId",property = "user",one = @One(select = "edu.qcu.dao.IUserDao.findById")),
            @Result(column = "borrowDate",property = "borrowDate"),
            @Result(column = "dueDate",property = "dueDate"),
            @Result(column = "returnDate",property = "returnDate"),
            @Result(column = "status",property = "status")
    })
    List<BorrowLog> findAll();

    @Insert("insert into borrowLog(bookId,userId,borrowDate,returnDate,dueDate,status) values(#{bookId},#{userId},#{borrowDate},#{returnDate},#{dueDate},#{status})")
    void addLog(BorrowLog borrowLog);

    @Select("select COUNT(*) from borrowLog where userId=#{id} AND status=0")
    Integer findLogCountByUserId(String id);

    @Select("select * from borrowLog where userId=#{userId} AND status=0")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "bookId",property = "bookId"),
            @Result(column = "bookId",property = "book",one = @One(select = "edu.qcu.dao.IBookDao.findById")),
            @Result(column = "userId",property = "userId"),
            @Result(column = "borrowDate",property = "borrowDate"),
            @Result(column = "dueDate",property = "dueDate"),
            @Result(column = "returnDate",property = "returnDate"),
            @Result(column = "status",property = "status")
    })
    List<BorrowLog> findLogByUserId(String userId);

    @Select("select bookId from borrowLog where userId=#{id} AND status=0")
    List<String> findLogIdByUserId(String id);

    @Update("update borrowLog set status=1,rating=#{rating},returnDate=#{returnDate} where id=#{id}")
    void returnSingleBook(BorrowLog borrowLog);

    @Select("select Count(*) from borrowLog where bookId=#{id} AND status=0")
    Integer findLogCountByBookId(String id);

    @Delete("delete from borrowLog where bookId=#{id}")
    void delBorrowLogByBookId(String id);

    @Delete("delete from borrowLog where userId=#{userId}")
    void delBorrowLogByUserId(String userId);

    @Select("select *, AVG(rating) AS rating FROM borrowLog WHERE userId = #{userId}  AND `status` = 1 GROUP BY bookId")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "bookId",property = "bookId"),
            @Result(column = "bookId",property = "book",one = @One(select = "edu.qcu.dao.IBookDao.findById")),
            @Result(column = "userId",property = "userId"),
            @Result(column = "borrowDate",property = "borrowDate"),
            @Result(column = "dueDate",property = "dueDate"),
            @Result(column = "returnDate",property = "returnDate"),
            @Result(column = "status",property = "status"),
            @Result(column = "rating",property = "rating")
    })
    List<BorrowLog> findPersonBorrowLog(String userId);

    @Select("select COUNT(*) from borrowLog where userId=#{userId} AND bookId IN (SELECT id FROM books WHERE ISBN = #{ISBN})")
    Integer borrowCount(@Param("userId") String userId,@Param("ISBN") String ISBN);

    @Select("SELECT ROUND(a/b,2) FROM (SELECT SUM(returnDate-borrowDate) AS a,SUM(dueDate-borrowDate) AS b FROM borrowlog WHERE userId = #{userId} AND bookId IN (SELECT id FROM books WHERE ISBN = #{ISBN}) AND `status`=1) AS a;")
    Double borrowTime(@Param("userId") String userId,@Param("ISBN") String ISBN);

    @Select("SELECT * FROM borrowlog WHERE userId=#{s} AND `status`=1 AND bookId NOT IN (SELECT id FROM books WHERE id IN ( SELECT bookId id FROM borrowlog WHERE userId = #{userId} )) ORDER BY rating DESC")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "bookId",property = "bookId"),
            @Result(column = "bookId",property = "book",one = @One(select = "edu.qcu.dao.IBookDao.findById")),
            @Result(column = "userId",property = "userId"),
            @Result(column = "borrowDate",property = "borrowDate"),
            @Result(column = "dueDate",property = "dueDate"),
            @Result(column = "returnDate",property = "returnDate"),
            @Result(column = "status",property = "status")
    })
    Collection<BorrowLog> findRecommendBook(@Param("s") String s,@Param("userId") String userId)throws Exception;

    @Select("SELECT AVG(rating) AS rating FROM borrowlog WHERE bookId IN (SELECT id FROM books WHERE ISBN in (SELECT ISBN FROM books WHERE id=#{bookId}))")
    Float getRatingByBookId(String bookId);

    @Select("SELECT id,bookId,userId,borrowDate,returnDate,dueDate,`status`,AVG(rating) rating FROM borrowlog WHERE bookId IN (SELECT id FROM books WHERE ISBN=(SELECT ISBN FROM books WHERE id=#{id})) GROUP BY userId")
    List<BorrowLog> findLogByBookId(String id);
}
