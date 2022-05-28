package edu.qcu.domain;

import org.springframework.web.multipart.MultipartFile;

public class Book {
    private String id;
    private Integer category;
    private String categoryStr;
    private String bookName;
    private String author;
    private String press;
    private Integer status;
    private String statusStr;
    private String ISBN;
    private Float rating;
    private String image;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCategoryStr() {
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer state) {
        this.status = state;
    }

    public String getStatusStr() {
        if (status!=null){
            if (status==1)
                statusStr="在馆";
            else
                statusStr="被借阅";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", categoryStr='" + categoryStr + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", rating=" + rating +
                ", image='" + image + '\'' +
                ", file=" + file +
                '}';
    }
}
