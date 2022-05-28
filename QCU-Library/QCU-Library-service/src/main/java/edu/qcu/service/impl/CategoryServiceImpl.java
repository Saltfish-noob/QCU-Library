package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.ICategoryDao;
import edu.qcu.domain.Category;
import edu.qcu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("category")
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findAll(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return categoryDao.findAll();
    }

    @Override
    public void save(Category category) throws Exception {
        categoryDao.save(category);
    }

    @Override
    public Category findById(Integer id) throws Exception {
        return categoryDao.findById(id);
    }

    @Override
    public void update(Category category) throws Exception {
        categoryDao.update(category);
    }

    @Override
    public void delCategory(Integer id) throws Exception {
                categoryDao.delCategory(id);
    }

    @Override
    public Integer bookCountByCategory(Integer id) throws Exception {
        return categoryDao.findBookByCategoryId(id);
    }
}
