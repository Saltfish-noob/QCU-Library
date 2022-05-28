package edu.qcu.domain;

import edu.qcu.utils.DateUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.List;

public class BorrowLog {
    private String id;
    private String bookId;
    private Book book;
    private String userId;
    private UserInfo user;
    private Date borrowDate;
    private String borrowDateStr;
    private Date returnDate;
    private String returnDateStr;
    private Date dueDate;
    private String dueDateStr;
    private Integer status;
    private String statusStr;
    private Double rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowDateStr() {
        if (borrowDate!=null){
            borrowDateStr = DateUtils.date2String(borrowDate,"yyyy-MM-dd");
        }
        return borrowDateStr;
    }

    public void setBorrowDateStr(String borrowDateStr) {
        this.borrowDateStr = borrowDateStr;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDateStr() {
        if (dueDate!=null){
            dueDateStr = DateUtils.date2String(dueDate,"yyyy-MM-dd");
        }
        return dueDateStr;
    }

    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status==0)
            statusStr="未归还";
        else
            statusStr="已归还";
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnDateStr() {
        if (returnDate!=null){
            returnDateStr = DateUtils.date2String(returnDate,"yyyy-MM-dd");
        }
        return returnDateStr;
    }

    public void setReturnDateStr(String returnDateStr) {
        this.returnDateStr = returnDateStr;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "BorrowLog{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", book=" + book +
                ", userId='" + userId + '\'' +
                ", user=" + user +
                ", borrowDate=" + borrowDate +
                ", borrowDateStr='" + borrowDateStr + '\'' +
                ", returnDate=" + returnDate +
                ", returnDateStr='" + returnDateStr + '\'' +
                ", dueDate=" + dueDate +
                ", dueDateStr='" + dueDateStr + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", rating=" + rating +
                '}';
    }
}
