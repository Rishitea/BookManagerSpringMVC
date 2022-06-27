package site.manager.books.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.manager.books.commons.paging.SearchCriteria;

@Service
public class ListAllService {
	
	@Autowired
	private BookDao bookDao;
	
	public ListAllService() {}
	public ListAllService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	//중복확인용
	public List<Book> select() {
		List<Book> listData = bookDao.selectAll();
		return listData;
	}

	//게시물 목록 조회(페이징 적용)
	public List<Book> list(SearchCriteria cri) throws Exception {
		List<Book> listData = bookDao.list(cri);
		return listData;
	}
	//게시물 총 갯수
	public int listCount(SearchCriteria cri) throws Exception {
		int listCount = bookDao.listCount(cri);
		return listCount;
	}
	//게시물 목록 조회 (서치)
	public List<Book> listCategory(SearchCriteria cri) throws Exception {
		List<Book> listData=bookDao.searchByCategory(cri);
		return listData;
	}
	//게시물 총 갯수(서치)
	public int listCountByCategory(SearchCriteria cri) throws Exception {
		int listCountCategory = bookDao.listCountByCategory(cri);
		return listCountCategory;
	}
	
}
