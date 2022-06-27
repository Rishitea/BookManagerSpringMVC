package site.manager.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.manager.books.commons.paging.SearchCriteria;
import site.manager.books.spring.Book;
import site.manager.books.spring.ListAllService;
import site.manager.books.spring.PageMaker;

@Controller
public class listController {
	
	@Autowired
	private ListAllService listAllService;

	public void setListAllService(ListAllService listAllService) {
		this.listAllService = listAllService;
	}
	@RequestMapping(value="/list")
	public String list(Model model, SearchCriteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();

		System.out.println(cri.getCategory());
		if(cri.getCategory() == null || cri.getKeyword()==null) {
			/*검색 안함*/
			List<Book> listData = listAllService.list(cri); 
			model.addAttribute("listData", listData);
			model.addAttribute("scmd", new SearchCriteria());
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(listAllService.listCount(cri));
		}else{ //검색어잇음 - 빈 문자열 / 없는 결과??
			
			List<Book> listData = listAllService.listCategory(cri); 
			model.addAttribute("listData", listData);
			model.addAttribute("scmd", cri);
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(listAllService.listCountByCategory(cri));
			
		}
		model.addAttribute("pageMaker", pageMaker);

		return "books/list";
	}
	

}
