package edu.qcu.service;

import edu.qcu.domain.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll()throws Exception;

    List<Category> findAll(Integer page, Integer pageSize)throws Exception;

    void save(Category category)throws Exception;

    Category findById(Integer id)throws Exception;

    void update(Category category)throws Exception;

    void delCategory(Integer id)throws Exception;

    Integer bookCountByCategory(Integer id)throws Exception;
}
