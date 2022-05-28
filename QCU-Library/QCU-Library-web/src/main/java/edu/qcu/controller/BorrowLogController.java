package edu.qcu.controller;

import com.github.pagehelper.PageInfo;
import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;
import edu.qcu.service.IBookService;
import edu.qcu.service.IBorrowLogService;
import edu.qcu.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/borrowLog")
public class BorrowLogController {

    private final IBorrowLogService borrowLogService;

    @Autowired
    public BorrowLogController(IBorrowLogService borrowLogService) {
        this.borrowLogService = borrowLogService;
    }

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    private IRecommendService recommendService;

    @Autowired
    public void setRecommendService(IRecommendService recommendService) {
        this.recommendService = recommendService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindAllBorrowLog')")
    public ModelAndView findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<BorrowLog> borrowLogList = borrowLogService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(borrowLogList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("back-end/borrowLog-list");
        return mv;
    }

    @RequestMapping("/findBorrowLogByUserId.do")
    @PreAuthorize("hasAuthority('Permission_FindBorrowLogByUserId')")
    public ModelAndView returnBookView() throws Exception {
        ModelAndView mv = new ModelAndView();
        String userId = (String) session.getAttribute("userId");
        List<BorrowLog> borrowLogList = borrowLogService.findLogByUserId(userId);
        List<Book> recommendBookList = recommendService.findSimilarBook(userId);
        //如果推荐的书籍小于4则推荐最火热的书籍，但仍显示是根据相似用户推荐
        if (recommendBookList.size() < 4) {
            recommendBookList.clear();
            recommendBookList.addAll(recommendService.findHotBook());
        }
        mv.addObject("borrowLogList", borrowLogList);
        mv.addObject("recommendBookList", recommendBookList);
        mv.setViewName("front-end/returnBook");
        return mv;
    }

    @RequestMapping("/returnSingleBook.do")
    @PreAuthorize("hasAuthority('Permission_RetuenSingleBook')")
    public String returnSingleBook(BorrowLog borrowLog) throws Exception {
        borrowLogService.returnSingleBook(borrowLog);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('归还成功');");
        pw.println("window.location.href='/QCU_Library/borrowLog/findBorrowLogByUserId.do'");
        pw.println("</script>");
        pw.close();
        return "redirect:findBorrowLogByUserId.do";
    }
}
