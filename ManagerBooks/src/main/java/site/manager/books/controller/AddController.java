package site.manager.books.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import site.manager.books.exception.AlreadyExistingBookException;
import site.manager.books.exception.AlreadyExistingIsbnException;
import site.manager.books.spring.AddBookService;
import site.manager.books.spring.AddRequest;

@Controller
public class AddController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddController.class);
	
	private AddBookService addBookService;	
	@Autowired
	public void setAddBookService(AddBookService addBookService) {
		this.addBookService = addBookService;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String regControl(@ModelAttribute("formData")AddRequest addRequest, Model model) {
		if (addRequest == null) {
			model.addAttribute("formData", new AddRequest());
		}
		return "books/book_reg_form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String regControl(@Valid @ModelAttribute("formData")AddRequest addReq, BindingResult bindingResult, Model model, MultipartFile img, HttpSession session) {
		//커맨드객체 Error
		if (bindingResult.hasErrors()) {
			return "books/book_reg_form";
		}
		if (!img.isEmpty()) {
			logger.info("!img.isEmpty() 실행");
			//값 세팅 (img관련)
			String imgoriginalName = img.getOriginalFilename();
			String imgsavedName ="";
			imgsavedName =  makeImgSavedName(imgoriginalName);			
			String savePath = session.getServletContext().getRealPath("/resources/upload/");
			logger.info("savePath : " + savePath);		
			addReq.setImgoriginal(imgoriginalName);
			addReq.setImgsaved(imgsavedName);
			
			try { //파일 서버 저장
				img.transferTo(new File(savePath+imgsavedName));
				logger.info("이미지 저장 to Server 성공");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//DB에 저장
			try {
				addBookService.add(addReq);
				logger.info("addBookService 실행 - DB에 데이터 저장");				
			}  catch (AlreadyExistingBookException e) {
				bindingResult.rejectValue("title", "title.duplicate.title", "이미 존재하는 도서입니다.");
				//model.addAttribute("formData", new AddRequest());
				return "books/book_reg_form";
			} catch (AlreadyExistingIsbnException e) {
				bindingResult.rejectValue("isbn", "duplicate.isbn", "이미 존재하는 번호입니다.");
				//model.addAttribute("formData", new AddRequest());
				return "books/book_reg_form";
			}
			return "redirect:/list";
			
		} else {
			logger.info("img.isEmpty()");
			model.addAttribute("formData", new AddRequest());
			return "books/book_reg_form";
		}
	}
		

	
	//업로드 파일 imgSaved 이름 변경해주는 클래스 작성
	public String makeImgSavedName (String imgoriginalName) {
		String uuid = UUID.randomUUID().toString();
		String ext = extractExt(imgoriginalName);
		return uuid + "." + ext;
	}
	public String extractExt (String imgoriginalName) {
		int posi = imgoriginalName.lastIndexOf(".");
		return imgoriginalName.substring(posi + 1);
	}

}