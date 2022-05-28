package edu.qcu.dao;

import edu.qcu.domain.Book;
import edu.qcu.domain.RecommendBookIndex;
import edu.qcu.domain.RecommendIndex;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecommendDao {


    @Delete("delete from userEuclideanMetric")
    void delAll();

    @Insert("insert into userEuclideanMetric (userId1,userId2,`index`) values(#{userId1},#{userId2},#{index})")
    void addUserEuclideanMetric(RecommendIndex recommendIndex);

    @Select("SELECT * from userEuclideanMetric where userId1=#{userId} OR userId2=#{userId} ORDER BY `index` DESC")
    List<RecommendIndex> findSimilarUser(String userId);

    //子句多套一层是因为会报错，信息如下 This version of MySQL doesn't yet support 'LIMIT & IN/ALL/ANY/SOME subquery'即limit不能与IN/ALL/ANY/SOME直接组成复合句
    @Select("SELECT books.*,D.rating FROM books right JOIN (SELECT *,AVG(rating),COUNT(*) FROM borrowlog WHERE bookId IN (SELECT id FROM books ORDER BY ISBN) GROUP BY (SELECT ISBN FROM books WHERE id = bookId) ORDER BY COUNT(*) DESC LIMIT 4) D ON books.id=D.bookId")
    List<Book> findBookHotValue();

    @Insert("insert into bookEuclideanMetric (bookISBN1,bookISBN2,`index`) values(#{bookISBN1},#{bookISBN2},#{index})")
    void addBookEuclideanMetric(RecommendBookIndex recommendIndex);

    @Delete("delete from bookEuclideanMetric")
    void delAllBookEuclideanMetric();

    @Delete("delete from userEuclideanMetric where userId1=#{userId} or userId2=#{userId}")
    void delRecommendByUserId(String userId);

    @Delete("delete from bookEuclideanMetric where bookISBN1=#{ISBN} or bookISBN2=#{ISBN}")
    void delRecommendByBookId(String ISBN);
}
