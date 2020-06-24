package com.jiangzhe.controller;

import com.jiangzhe.pojo.Books;
import com.jiangzhe.service.BookService;
import com.jiangzhe.service.BookServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller层就是去控制Service层
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;


    //查询全部的书籍并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> books = bookService.queryAllBooks();

        model.addAttribute("list",books);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPage(){
        return "addBook";
    }

    //添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("addBook==>"+books);
        bookService.addBook(books);
        return "redirect:/book/allBook";//重定向到我们的@RequestMapping("/allBook")请求；
    }

    //跳转到修改书籍页面
    @RequestMapping("/toUpdate")
    public String toUpdatePage(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("QBooks",books);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("updateBook===>"+books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    //删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model){
        List<Books> list = bookService.queryBookByName(queryBookName);
        //System.err.println("books==>"+books);
        //List<Books> list = new ArrayList<Books>();
        //
        //list.add(books);
        //
        //if (books == null) {
        //    list = bookService.queryAllBooks();
        //    model.addAttribute("error","未查到");
        //}
        model.addAttribute("list",list);
        return "allBook";

    }

}

