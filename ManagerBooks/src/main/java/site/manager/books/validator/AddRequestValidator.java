package site.manager.books.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import site.manager.books.spring.AddRequest;

public class AddRequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return AddRequest.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//target:검사 대상 객체, errors:검사 결과 에러코드를 저장하는 객체
		AddRequest addReq = (AddRequest)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "titleRequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writerRequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "isbnRequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publisher", "publisherRequired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "intro", "introRequired");
		
		if(addReq.getPrice()==0) {
			errors.rejectValue("price", "priceRequired");
		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required");//
		
		//
		if(addReq.getImgoriginal()==null || addReq.getImgoriginal().isEmpty()) {
			errors.rejectValue("imgoriginal", "imgRequired");
		}
	}
}
