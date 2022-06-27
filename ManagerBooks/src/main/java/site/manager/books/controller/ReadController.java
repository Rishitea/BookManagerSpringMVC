package site.manager.books.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.manager.books.spring.Book;
import site.manager.books.spring.ReadBookService;

@Controller
public class ReadController {
	
	private ReadBookService readBookService;
	@Autowired
	public void setReadBookService(ReadBookService readBookService) {
		this.readBookService = readBookService;
	}
	
	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String Read(@PathVariable("no")long no,Model model,HttpSession session) {
		Book bookData = readBookService.read(no);
		model.addAttribute("bookData", bookData);		

		return "books/read";
	}
	
}
