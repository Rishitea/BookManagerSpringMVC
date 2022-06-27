package site.manager.books.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import site.manager.books.exception.AlreadyExistingBookException;
import site.manager.books.spring.AddBookService;
import site.manager.books.spring.AddRequest;
import site.manager.books.validator.AddRequestValidator;

@Controller
public class AddController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddController.class);
	
	private AddBookService addBookService;	
	@Autowired
	public void setAddBookService(AddBookService addBookService) {
		this.addBookService = addBookService;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String regControl(Model model) {
		model.addAttribute("formData", new AddRequest());
		return "books/book_reg_form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String regControl(@ModelAttribute("formData")AddRequest addReq, Errors errors, MultipartFile img, HttpSession session) {
		logger.info("AddController(register-post) 실행!");
		new AddRequestValidator().validate(addReq, errors);

		//값 세팅 (img관련)
		String imgoriginalName = img.getOriginalFilename();
		String imgsavedName ="";
		if (!img.getOriginalFilename().equals("")) {
			 imgsavedName =  img.getOriginalFilename(); 
			logger.info("imgoriginalName : " + imgoriginalName);
			logger.info("imgsavedName : " + imgsavedName);
			
			String savePath = session.getServletContext().getRealPath("/resources/upload/");
			logger.info("savePath : " + savePath);		
			addReq.setImgoriginal(imgoriginalName);
			addReq.setImgsaved(imgsavedName);
			
			
			//이미지 서버에 저장
			try {
				img.transferTo(new File(savePath+imgsavedName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				addBookService.add(addReq);
				//validate
				if(errors.hasErrors()) {
					List<ObjectError> errList = errors.getAllErrors();
					for(ObjectError oe : errList) {
						System.out.println(oe.getCode());
					}
					
					return "books/book_reg_form";
				}
				System.out.println("add 완료");
				return "redirect:/list";
			} catch (AlreadyExistingBookException e) {
				errors.rejectValue("title", "duplicate");
				System.out.println("중복 데이터 삽입");
			}
		}
		
		return "books/book_reg_form";

	}

}
