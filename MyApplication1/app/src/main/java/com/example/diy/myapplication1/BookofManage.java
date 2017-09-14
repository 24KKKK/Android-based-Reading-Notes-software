package com.example.diy.myapplication1;

/**
 * Created by diy on 2017/4/14.
 */

public class BookofManage {
  private String bookcode;
    private String name;
    private String Zname;
    private String publish;
    private String ISBN;
    private String sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public String getZname() {
        return Zname;
    }

    public void setZname(String zname) {
        Zname = zname;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
