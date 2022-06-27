package site.manager.books.spring;

import java.util.List;

import org.springframework.stereotype.Component;

import site.manager.books.commons.paging.SearchCriteria;

@Component
public interface BookDao {
	
	public Object selectByTitle(String title); //Read 디테일
//	public Object selectByWriter(String writer);
//	public Object selectByPublisher(String publisher);
		
	public List<Book> selectAll();
	//검색용 추가
	public List<Book> searchByCategory(SearchCriteria cri) throws Exception;
	public int listCountByCategory(SearchCriteria cri) throws Exception;
	
	public int count();
	public void update(Book book);
	public void insert(Book book);
	public Book selectByNo(Long no);

	//Paging용!!
	public List<Book> list(SearchCriteria cri) throws Exception;
	public int listCount(SearchCriteria cri) throws Exception;


}
