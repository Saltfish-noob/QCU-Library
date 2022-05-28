package edu.qcu.dao;

import edu.qcu.domain.Book;
import edu.qcu.domain.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ICategoryDao {

    @Select("select * from Category")
    List<Category> findAll();

    @Insert("insert into category (categoryName) values(#{categoryName})")
    void save(Category category);

    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    @Update("update category set categoryName=#{categoryName} where id=#{id}")
    void update(Category category);

    @Delete("delete from category where id=#{id}")
    void delCategory(Integer id);

    @Select("select COUNT(*) from books where category=#{id}")
    int findBookByCategoryId(Integer id);
}
