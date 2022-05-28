package edu.qcu.controller;

import com.github.pagehelper.PageInfo;
import edu.qcu.domain.Category;
import edu.qcu.service.ICategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindAllCategory')")
    public ModelAndView findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> categoryList = categoryService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(categoryList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("back-end/category-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("hasAuthority('Permission_SaveCategory')")
    public String save(Category category) throws Exception {
        categoryService.save(category);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    @PreAuthorize("hasAuthority('Permission_FindCategoryBYId')")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Category category = categoryService.findById(id);
        mv.addObject(category);
        mv.setViewName("back-end/category-show");
        return mv;
    }

    @RequestMapping("/update.do")
    @PreAuthorize("hasAuthority('Permission_UpdateCategory')")
    public String update(Category category) throws Exception {
        categoryService.update(category);
        return "redirect:findAll.do";
    }

    @RequestMapping("/delCategory.do")
    @PreAuthorize("hasAuthority('Permission_DeleteCategory')")
    public String delCategory(
            @RequestParam(value = "ids", required = false) Integer[] ids
    ) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        if (ids.length == 0) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('您尚未选择任何信息');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        List<Integer> alert = new ArrayList<>();
        boolean NoInfo = true;
        for (Integer id : ids) {
            if (categoryService.bookCountByCategory(id) != 0) {
                NoInfo = false;
                alert.add(id);
            } else {
                //删除权限
                categoryService.delCategory(id);
            }
        }
        if (!NoInfo) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('以下类别未被删除，因为该类别下仍有书籍.类别ID如下" + alert + "');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('删除成功');");
        pw.println("window.location.href='findAll.do'");
        pw.println("</script>");
        pw.close();
        return "redirect:findAll.do";
    }
}
