package edu.qcu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;
import edu.qcu.domain.Category;
import edu.qcu.service.IBookService;
import edu.qcu.service.IBorrowLogService;
import edu.qcu.service.ICategoryService;
import edu.qcu.service.IRecommendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/book")
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private IBorrowLogService borrowLogService;

    @Autowired
    public void setBorrowLogService(IBorrowLogService borrowLogService) {
        this.borrowLogService = borrowLogService;
    }

    private IRecommendService recommendService;

    @Autowired
    public void setRecommendService(IRecommendService recommendService) {
        this.recommendService = recommendService;
    }

    List<String> ISBNList = new ArrayList<>();

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindAllBook')")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Book> bookList = bookService.findAll(page, pageSize);
        for (Book book : bookList) {
            book.setCategoryStr(bookService.findCategoryNameById(book.getCategory()));
        }
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("back-end/book-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    @PreAuthorize("hasAuthority('Permission_FindBookById')")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Book book = bookService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        mv.addObject("book", book);
        mv.addObject("categoryList", categoryList);
        mv.setViewName("back-end/book-show");
        return mv;
    }

    @RequestMapping("/updateBook.do")
    @PreAuthorize("hasAuthority('Permission_UpdateBook')")
    public String updateBook(Book book) throws Exception {
        if (bookService.findBookCountByISBN(book.getISBN()) == 0) {
            bookService.updateBookById(book);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('您更改了书籍的ISBN号，故该操作只对本书籍生效');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        } else {
            bookService.updateBook(book);
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/addBookPage.do")
    @PreAuthorize("hasAuthority('Permission_AddBookPage')")
    public ModelAndView addBookPage() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> categoryList = categoryService.findAll();
        mv.addObject("categoryList", categoryList);
        mv.setViewName("back-end/book-add");
        return mv;
    }

    @RequestMapping(value = "/addBook.do", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('Permission_AddBook')")
    public String addBook(Book book, @Param("number") Integer number) throws Exception {
        bookService.addBook(book, number);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAllBookByGroup.do")
    @PreAuthorize("hasAuthority('Permission_FindAllBookINFront')")
    public ModelAndView findAllBookByGroup(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "12") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        session.setAttribute("pageInfo", "findAllBookByGroup.do");
        List<Category> categoryList = categoryService.findAll();
        List<Book> bookList = bookService.findAllBookByGroup(page, pageSize);
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("front-end/shop");
        return mv;
    }

    @RequestMapping("/findBookByCategory.do")
    @PreAuthorize("hasAuthority('Permission_FindBookByCategory')")
    public ModelAndView findBookByCategory(
            @RequestParam(value = "category", required = true) String category,
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "12") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        session.setAttribute("pageInfo", "findBookByCategory.do");
        List<Category> categoryList = categoryService.findAll();
        List<Book> bookList = bookService.findBookByCategory(category, page, pageSize);
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("front-end/shop");
        return mv;
    }

    @RequestMapping("/findBookByReview.do")
    @PreAuthorize("hasAuthority('Permission_FindBookByReview')")
    public ModelAndView findBookByReview(
            @RequestParam(value = "rating") Double rating,
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "12") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("pageInfo", "findBookByReview.do");
        List<Book> bookList = bookService.findBookByRating(rating, page, pageSize);
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("front-end/shop");
        return mv;
    }

    @RequestMapping("/bookDetails.do")
    @PreAuthorize("hasAuthority('Permission_FindBookDetails')")
    public ModelAndView bookDetails(Book book) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Book> bookList = bookService.findBookByStatus(1, book.getISBN());
        book.setCategoryStr(categoryService.findById(book.getCategory()).getCategoryName());
        List<Book> recommendBookList = bookService.findRecommendBook(book.getISBN());
        if (recommendBookList.size() < 4) {
            recommendBookList.clear();
            recommendBookList = bookService.findBestSeller(book.getCategory());
            if (recommendBookList.size() < 4) {
                recommendBookList.clear();
                recommendBookList = bookService.findBookByBorrowLogCount();
            }
        }
        mv.addObject("bookList", bookList);
        mv.addObject("book", book);
        mv.addObject("recommendBookList", recommendBookList);
        mv.setViewName("front-end/single_product");
        return mv;
    }

    @RequestMapping("/delBook.do")
    @PreAuthorize("hasAuthority('Permission_DeleteBook')")
    public String delBook(
            @RequestParam(name = "ids", required = false) String[] ids
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
        List<String> alert = new ArrayList<>();
        boolean NoInfo = true;
        for (String id : ids) {
            if (borrowLogService.findLogCountByBookId(id) != 0) {
                NoInfo = false;
                alert.add(id);
            } else {
                //删除权限
                borrowLogService.delBorrowLogByBookId(id);
                recommendService.delRecommendByBookId(id);
                bookService.delBook(id);
            }
        }
        if (!NoInfo) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('以下ID的书籍仍有未归还的纪录：" + alert + "');");
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

    @RequestMapping("/addCart.do")
    @PreAuthorize("hasAuthority('Permission_AddCart')")
    public String addCart(Book book) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        List<String> haveBorrowedList = (List<String>) session.getAttribute("LogId");
        boolean flag_NotInCart = true;
        boolean flag_NotHaveBorrowed = true;
        for (String s : haveBorrowedList) {
            if (Objects.equals(s, book.getId())) {
                flag_NotHaveBorrowed = false;
                break;
            }
        }
        for (String s : ISBNList) {
            if (Objects.equals(s, book.getISBN())) {
                flag_NotInCart = false;
                break;
            }
        }
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        if (ISBNList.size() + (Integer) session.getAttribute("haveBorrow") > 3) {
            pw.println("alert('超过数量');");
        } else {
            if (flag_NotInCart && flag_NotHaveBorrowed) {
                ISBNList.add(book.getISBN());
                session.setAttribute("reserve", ISBNList);
                pw.println("alert('已放入购物车');");
            } else {
                pw.println("alert('已经借阅过一本了');");
            }
        }
        pw.println("window.location.href='findAllBookByGroup.do'");
        pw.println("</script>");
        pw.close();
        return "redirect:findAllBookByGroup.do";
    }

    @RequestMapping("/checkout.do")
    @PreAuthorize("hasAuthority('Permission_Checkout')")
    public ModelAndView checkout() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<String> bookList1 = (List<String>) session.getAttribute("reserve");
        List<Book> bookList = bookService.cartFindBook(bookList1);
        List<String> bookListId = new ArrayList<>();
        for (Book book : bookList) {
            if (book != null)
                bookListId.add(book.getId());
        }
        session.setAttribute("bookListId", bookListId);
        mv.addObject("bookList", bookList);
        mv.setViewName("front-end/checkout");
        return mv;
    }

    @RequestMapping("/checkoutAfter.do")
    @PreAuthorize("hasAuthority('Permission_CheckoutAfter')")
    public String checkout(@Param("afterDel") String afterDel) throws Exception {
        List<String> strList = Arrays.asList(afterDel.split(","));
        //asList返回的数组不允许被修改
        ISBNList.clear();
        ISBNList.addAll(strList);
        session.setAttribute("reserve", ISBNList);
        return "redirect:checkout.do";
    }

    @RequestMapping("/borrowing.do")
    @PreAuthorize("hasAuthority('Permission_Borrowing')")
    public void borrowing() throws Exception {
        String ID = (String) session.getAttribute("userId");
        List<String> bookListId = (List<String>) session.getAttribute("bookListId");
        bookService.borrowing(bookListId);
        borrowLogService.addLog(ID, bookListId);
        //变更已借阅未归还的数量
        Integer haveBorrow = (Integer) session.getAttribute("haveBorrow") + ISBNList.size();
        session.setAttribute("haveBorrow", haveBorrow);
        //清除购物车里的数据
        ISBNList.clear();
        session.removeAttribute("bookListId");
        session.setAttribute("reserve", ISBNList);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('借阅成功');");
        pw.println("window.location.href='/QCU_Library/borrowLog/findBorrowLogByUserId.do'");
        pw.println("</script>");
        pw.close();
    }

    @RequestMapping("/findBookByCondition.do")
    @PreAuthorize("hasAuthority('Permission_FindBookByCondition')")
    public ModelAndView findBookByCondition(
            @RequestParam(name = "Search", required = true) String search,
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "12") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        session.setAttribute("pageInfo", "findBookByCondition.do");
        session.setAttribute("condition", search);
        List<Category> categoryList = categoryService.findAll();
        List<Book> bookList = bookService.findBookByCondition(search, page, pageSize);
        PageInfo pageInfo = new PageInfo(bookList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("front-end/shop");
        return mv;
    }
}
