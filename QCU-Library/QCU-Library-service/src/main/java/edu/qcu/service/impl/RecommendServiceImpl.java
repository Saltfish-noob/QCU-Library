package edu.qcu.service.impl;

import edu.qcu.dao.IBookDao;
import edu.qcu.dao.RecommendDao;
import edu.qcu.domain.Book;
import edu.qcu.domain.BorrowLog;
import edu.qcu.domain.RecommendBookIndex;
import edu.qcu.domain.RecommendIndex;
import edu.qcu.service.IBorrowLogService;
import edu.qcu.service.IRecommendService;
import edu.qcu.utils.RecommendUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("Recommend")
@Transactional
public class RecommendServiceImpl implements IRecommendService {

    private IBorrowLogService borrowLogService;

    @Autowired
    public void setBorrowLogService(IBorrowLogService borrowLogService) {
        this.borrowLogService = borrowLogService;
    }

    private RecommendDao recommendDao;

    @Autowired
    public void setRecommendDao(RecommendDao recommendDao) {
        this.recommendDao = recommendDao;
    }

    private IBookDao bookDao;

    @Autowired
    public void setBookDao(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void calculateRecommendValue() throws Exception {
        //查询所有记录
        List<BorrowLog> borrowLogList = borrowLogService.findAll();
        Map<String, Map<String, Double>> recommendMap = new HashMap<>();
        List<BorrowLog> personalBorrowLogList = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        //读取所有已经归还的纪录
        for (BorrowLog borrowLog : borrowLogList) {
            personalBorrowLogList.addAll(borrowLogService.findPersonBorrowLog(borrowLog.getUserId()));
            userIds.add(borrowLog.getUserId());
        }
        //去重
        List<String> userId = new ArrayList<>(new LinkedHashSet<>(userIds));
        for (String s : userId) {
            Map<String, Double> KY = new HashMap<>();
            for (BorrowLog borrowLog : personalBorrowLogList) {
                if (Objects.equals(borrowLog.getUserId(), s)) {
                    //如果借阅次数大于1则权值加1
                    Integer borrowCount = borrowLogService.borrowCount(borrowLog.getUserId(), borrowLog.getBook().getISBN()) > 1 ? 1 : 0;
                    //计算借阅时间和最长借阅时间之比得出权值
                    Double borrowTime = borrowLogService.borrowTime(borrowLog.getUserId(), borrowLog.getBook().getISBN());
                    KY.put(borrowLog.getBook().getISBN(), borrowLog.getRating() + borrowCount + borrowTime);
                }
            }
            recommendMap.put(s, KY);
        }
        //清空索引表
        recommendDao.delAll();
        for (int i = 0; i < recommendMap.size(); i++) {
            for (int j = i + 1; j < recommendMap.size(); j++) {
                //两两计算欧几里得距离，该值约大则距离越远
                RecommendIndex recommendIndex = new RecommendIndex();
                recommendIndex.setUserId1(userId.get(i));
                recommendIndex.setUserId2(userId.get(j));
                recommendIndex.setIndex(RecommendUtils.getEuclideanMetric(recommendMap.get(userId.get(i)), recommendMap.get(userId.get(j))));
                recommendDao.addUserEuclideanMetric(recommendIndex);
            }
        }
    }

    @Override
    public List<Book> findSimilarBook(String userId) throws Exception {
        List<RecommendIndex> recommendIndexList = recommendDao.findSimilarUser(userId);
        List<String> similarUserList = new ArrayList<>();
        List<BorrowLog> recommendBorrowLog = new ArrayList<>();
        List<Book> recommendBook = new ArrayList<>();
        Map<String, Double> euclideanMetric = new HashMap<>();;
        for (RecommendIndex index : recommendIndexList) {
            String Temp_userId = Objects.equals(index.getUserId1(), userId) ? index.getUserId2() : index.getUserId1();
            similarUserList.add(Temp_userId);
            euclideanMetric.put(Temp_userId, index.getIndex());
        }
        for (String s : similarUserList) {
            recommendBorrowLog.addAll(borrowLogService.findRecommendBook(s));
        }
        recommendBorrowLog.sort(((o1, o2) -> (int) (o2.getRating() * euclideanMetric.get(o2.getUserId()) - o1.getRating() * euclideanMetric.get(o2.getUserId()))));
        int i;
        for (i = 0; i < 4 && i < recommendBorrowLog.size(); i++) {
            if (recommendBorrowLog.get(i) != null) {
                recommendBorrowLog.get(i).getBook().setRating(borrowLogService.getRatingByBookId(recommendBorrowLog.get(i).getBookId()));
                recommendBook.add(recommendBorrowLog.get(i).getBook());
            } else
                break;
        }
        recommendBook = new ArrayList<>(new LinkedHashSet<>(recommendBook));
        return recommendBook;
    }

    @Override
    public List<Book> findHotBook() throws Exception {
        return recommendDao.findBookHotValue();
    }

    @Override
    public void calculateBookRelation() throws Exception {
        //查询所有书籍
        Map<String, Map<String, Double>> recommendBook = new HashMap<>();
        List<Book> bookList = bookDao.findAllGroupByISBN();
        List<Integer> categoryList = new ArrayList<>();
        List<String> bookISBNList = new ArrayList<>();
        for (Book book : bookList) {
            Map<String, Double> UserReview = new HashMap<>();
            for (BorrowLog borrowLog : borrowLogService.findLogByBookId(book.getId())) {
                UserReview.put(borrowLog.getUserId(), borrowLog.getRating());
            }
            if (UserReview.size() != 0) {
                bookISBNList.add(book.getISBN());
                categoryList.add(book.getCategory());
                recommendBook.put(book.getISBN(), UserReview);
            }
        }
        recommendDao.delAllBookEuclideanMetric();
        for (int i = 0; i < bookISBNList.size(); i++) {
            for (int j = i + 1; j < bookISBNList.size(); j++) {
                RecommendBookIndex recommendIndex = new RecommendBookIndex();
                recommendIndex.setBookISBN1(bookISBNList.get(i));
                recommendIndex.setBookISBN2(bookISBNList.get(j));
                Double value = Objects.equals(categoryList.get(i), categoryList.get(j)) ? 0.2 : 0;
                recommendIndex.setIndex(RecommendUtils.getEuclideanMetric(recommendBook.get(bookISBNList.get(i)), recommendBook.get(bookISBNList.get(j))) + value);
                recommendDao.addBookEuclideanMetric(recommendIndex);
            }
        }
    }

    @Override
    public void delRecommendByUserId(String userId) throws Exception {
        recommendDao.delRecommendByUserId(userId);
    }

    @Override
    public void delRecommendByBookId(String id) throws Exception {
        String ISBN = bookDao.findISBNById(id);
        recommendDao.delRecommendByBookId(ISBN);
    }
}
