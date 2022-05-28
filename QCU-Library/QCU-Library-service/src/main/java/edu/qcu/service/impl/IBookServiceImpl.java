package edu.qcu.service.impl;

import com.github.pagehelper.PageHelper;
import edu.qcu.dao.IBookDao;
import edu.qcu.domain.Book;
import edu.qcu.domain.RecommendBookIndex;
import edu.qcu.service.IBookService;
import edu.qcu.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("book")
@Transactional
public class IBookServiceImpl implements IBookService {

    private final IBookDao bookDao;

    @Autowired
    public IBookServiceImpl(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return bookDao.findAll();
    }

    @Override
    public String findCategoryNameById(Integer category) {
        return bookDao.findCategoryNameById(category);
    }

    @Override
    public Book findById(String id) throws Exception {
        return bookDao.findById(id);
    }

    @Override
    public void updateBook(Book book) throws Exception {
        //临时图片目录
        String temp_path = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\";
        String temp_path_ = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\book\\";
        //target文件夹下的存储路径，如果重新部署服务器将丢失文件
        String path = request.getSession().getServletContext().getRealPath("/") + "images\\books\\";
        //工程目录，重新部署不会丢失文件但若不更新资源则服务器无法访问
        String path_ = "G:\\java_project\\heima\\javaweb\\Library\\QCU-Library\\QCU-Library-web\\src\\main\\webapp\\images\\books\\";
        //将图片文件统一命名为ISBN号.jpg，如果未上传图片则使用默认图片default.jpg
        String fileName;
        //MultipartFile对象一定存在，所以只能用Size进行区分
        if (book.getFile().getSize() != 0) {
            fileName = book.getISBN() + book.getFile().getOriginalFilename().substring(book.getFile().getOriginalFilename().lastIndexOf("."));
            File dir = new File(temp_path, fileName);
            File dir_ = new File(temp_path_);
            if (!dir_.exists()) {
                dir_.mkdirs();
            }
            //将图片存储至临时文件夹
            book.getFile().transferTo(dir);
            //利用PictureUtils类改变图片尺寸并存入temp/book下，利用返回的路径创建Path对象
            Path p1 = FileSystems.getDefault().getPath(PictureUtils.modifyResolution(temp_path + fileName, temp_path_, fileName, 250, 320));
            //创建工程目录和target目录下的Path对象
            Path p2 = FileSystems.getDefault().getPath(path + fileName);
            Path p3 = FileSystems.getDefault().getPath(path_ + fileName);
            //使用Files的copy方法将转换后的文件复制到工程目录和target目录下
            Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(p2, p3, StandardCopyOption.REPLACE_EXISTING);
            //删除临时文件夹下的图片文件但保留文件夹
            Files.delete(FileSystems.getDefault().getPath(temp_path + fileName));
            Files.delete(FileSystems.getDefault().getPath(temp_path_ + fileName));
        } else
            //获取原本的图片名称
            fileName = bookDao.findById(book.getId()).getImage();
        book.setImage(fileName);
        bookDao.updateBook(book);
    }

    @Override
    public void addBook(Book book, Integer number) throws Exception {
        //临时图片目录
        String temp_path = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\";
        String temp_path_ = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\book\\";
        //target文件夹下的存储路径，如果重新部署服务器将丢失文件
        String path = request.getSession().getServletContext().getRealPath("/") + "images\\books\\";
        //工程目录，重新部署不会丢失文件但若不更新资源则服务器无法访问
        String path_ = "G:\\java_project\\heima\\javaweb\\Library\\QCU-Library\\QCU-Library-web\\src\\main\\webapp\\images\\books\\";
        //将图片文件统一命名为ISBN号.jpg，如果未上传图片则使用默认图片default.jpg
        String fileName;
        //MultipartFile对象一定存在，所以只能用Size进行区分
        if (book.getFile().getSize() != 0) {
            fileName = book.getISBN() + book.getFile().getOriginalFilename().substring(book.getFile().getOriginalFilename().lastIndexOf("."));
            File dir = new File(temp_path, fileName);
            File dir_ = new File(temp_path_);
            if (!dir_.exists()) {
                dir_.mkdirs();
            }
            //将图片存储至临时文件夹
            book.getFile().transferTo(dir);
            //利用PictureUtils类改变图片尺寸并存入temp/book下，利用返回的路径创建Path对象
            Path p1 = FileSystems.getDefault().getPath(PictureUtils.modifyResolution(temp_path + fileName, temp_path_, fileName, 250, 320));
            //创建工程目录和target目录下的Path对象
            Path p2 = FileSystems.getDefault().getPath(path + fileName);
            Path p3 = FileSystems.getDefault().getPath(path_ + fileName);
            //使用Files的copy方法将转换后的文件复制到工程目录和target目录下
            Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(p2, p3, StandardCopyOption.REPLACE_EXISTING);
            //删除临时文件夹下的图片文件但保留文件夹
            Files.delete(FileSystems.getDefault().getPath(temp_path + fileName));
            Files.delete(FileSystems.getDefault().getPath(temp_path_ + fileName));
        } else
            fileName = "default.jpg";
        book.setImage(fileName);
        for (int i = 0; i < number; i++) {
            bookDao.addBook(book);
        }
    }

    @Override
    public List<Book> findAllBookByGroup(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return bookDao.findAllBookByGroup();
    }

    @Override
    public List<Book> findBookByCategory(String category, Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return bookDao.findBookByCategory(category);
    }

    @Override
    public List<Book> findBookByRating(Double rating, Integer page, Integer pageSize) throws Exception {
        //设定评分上限并去尾
        double rating_up = Math.floor(rating + 0.5);
        //如果去尾后上限与下限相同则证明原本的下限尾数小于0.5，故先对下限进行去尾
        if (rating_up == rating)
            rating_up = Math.floor(rating) + 0.5;
        PageHelper.startPage(page,pageSize);
        return bookDao.findBookByRating(rating, rating_up);
    }

    @Override
    public List<Book> findBookByStatus(Integer Status, String ISBN) throws Exception {
        return bookDao.findBookByStatus(Status, ISBN);
    }

    @Override
    public List<Book> cartFindBook(List<String> bookList1) throws Exception {
        List<Book> bookList = new ArrayList<>();
        if (bookList1 != null)
            for (String s : bookList1) {
                Book book = bookDao.findBookByISBN(s);
                if (book != null)
                    bookList.add(book);
            }
        return bookList;
    }

    @Override
    public void borrowing(List<String> bookListId) throws Exception {
        for (String bookId : bookListId) {
            bookDao.borrowing(bookId);
        }
    }

    @Override
    public void delBook(String id) throws Exception {
        bookDao.delBook(id);
    }

    @Override
    public List<Book> findRecommendBook(String isbn) throws Exception {
        List<RecommendBookIndex> recommendBookIndexList = bookDao.findRecommendBook(isbn);
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < recommendBookIndexList.size(); i++) {
            if (i < 4)
                bookList.add(bookDao.findBookByISBN(Objects.equals(recommendBookIndexList.get(i).getBookISBN1(), isbn) ? recommendBookIndexList.get(i).getBookISBN2() : recommendBookIndexList.get(i).getBookISBN1()));
        }
        return bookList;
    }

    @Override
    public List<Book> findBestSeller(Integer category) throws Exception {
        return bookDao.findBestSeller(category);
    }

    @Override
    public List<Book> findBookByCondition(String search, Integer page, Integer pageSize) throws Exception {
        List<Book> bookList = bookDao.findBookByCondition(search);
        for (Book book : bookList) {
            book.setRating(bookDao.findRatingByISBN(book.getISBN()));
        }
        PageHelper.startPage(page,pageSize);
        return bookList;
    }

    @Override
    public Integer findBookCountByISBN(String isbn) throws Exception {
        return bookDao.findBookCountByISBN(isbn);
    }

    @Override
    public void updateBookById(Book book) throws Exception {
        //临时图片目录
        String temp_path = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\";
        String temp_path_ = request.getSession().getServletContext().getRealPath("/") + "images\\temp\\book\\";
        //target文件夹下的存储路径，如果重新部署服务器将丢失文件
        String path = request.getSession().getServletContext().getRealPath("/") + "images\\books\\";
        //工程目录，重新部署不会丢失文件但若不更新资源则服务器无法访问
        String path_ = "G:\\java_project\\heima\\javaweb\\Library\\QCU-Library\\QCU-Library-web\\src\\main\\webapp\\images\\books\\";
        //将图片文件统一命名为ISBN号.jpg，如果未上传图片则使用默认图片default.jpg
        String fileName;
        //MultipartFile对象一定存在，所以只能用Size进行区分
        if (book.getFile().getSize() != 0) {
            fileName = book.getISBN() + book.getFile().getOriginalFilename().substring(book.getFile().getOriginalFilename().lastIndexOf("."));
            File dir = new File(temp_path, fileName);
            File dir_ = new File(temp_path_);
            if (!dir_.exists()) {
                dir_.mkdirs();
            }
            //将图片存储至临时文件夹
            book.getFile().transferTo(dir);
            //利用PictureUtils类改变图片尺寸并存入temp/book下，利用返回的路径创建Path对象
            Path p1 = FileSystems.getDefault().getPath(PictureUtils.modifyResolution(temp_path + fileName, temp_path_, fileName, 250, 320));
            //创建工程目录和target目录下的Path对象
            Path p2 = FileSystems.getDefault().getPath(path + fileName);
            Path p3 = FileSystems.getDefault().getPath(path_ + fileName);
            //使用Files的copy方法将转换后的文件复制到工程目录和target目录下
            Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(p2, p3, StandardCopyOption.REPLACE_EXISTING);
            //删除临时文件夹下的图片文件但保留文件夹
            Files.delete(FileSystems.getDefault().getPath(temp_path + fileName));
            Files.delete(FileSystems.getDefault().getPath(temp_path_ + fileName));
        } else
            //获取原本的图片名称
            fileName = bookDao.findById(book.getId()).getImage();
        book.setImage(fileName);
        bookDao.updateBookById(book);
    }

    @Override
    public List<Book> findBookByBorrowLogCount() throws Exception {
        return bookDao.findBookByBorrowLogCount();
    }
}
