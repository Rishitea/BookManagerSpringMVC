package site.manager.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import site.manager.books.commons.paging.SearchCriteria;
import site.manager.books.spring.Book;
import site.manager.books.spring.ListAllService;
import site.manager.books.spring.PageMaker;

@Controller
public class SearchController {
	
	@Autowired
	private ListAllService listAllService;

	public void setListAllService(ListAllService listAllService) {
		this.listAllService = listAllService;
	}

	//searchByTitle
	@RequestMapping(value="/search")
	public String searchByTitle(@ModelAttribute("scmd")SearchCriteria  scmd, Errors error,  Model model) throws Exception {

		List<Book> listData=listAllService.listCategory(scmd); //검색어 받아서 listData생성
		model.addAttribute("listData", listData);
		model.addAttribute("scmd", scmd); //?
		
		PageMaker pageMaker = new PageMaker(); //페이징
		pageMaker.setCri(scmd);
		pageMaker.setTotalCount(listAllService.listCountByCategory(scmd));
		model.addAttribute("pageMaker", pageMaker);

		return "books/list"; 
	}
}
