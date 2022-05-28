package edu.qcu.domain;

public class RecommendBookIndex {
    private String bookISBN1;
    private String bookISBN2;
    private Double index;

    public String getBookISBN1() {
        return bookISBN1;
    }

    public void setBookISBN1(String bookISBN1) {
        this.bookISBN1 = bookISBN1;
    }

    public String getBookISBN2() {
        return bookISBN2;
    }

    public void setBookISBN2(String bookISBN2) {
        this.bookISBN2 = bookISBN2;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "RecommendBookIndex{" +
                "bookISBN1='" + bookISBN1 + '\'' +
                ", bookISBN2='" + bookISBN2 + '\'' +
                ", index=" + index +
                '}';
    }
}
