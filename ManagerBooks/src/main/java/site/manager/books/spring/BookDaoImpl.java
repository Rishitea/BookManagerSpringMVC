package site.manager.books.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import site.manager.books.commons.paging.SearchCriteria;

@Repository
public class BookDaoImpl implements BookDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Object selectByTitle(String title) {
		return sqlSessionTemplate.selectOne("book.dtd.selectByTitle", title);
	}

	@Override
	public List<Book> selectAll() {
		return sqlSessionTemplate.selectList("book.dtd.list");
	}

	@Override
	public int count() {
		return sqlSessionTemplate.selectOne("book.dtd.count");
	}

	@Override
	public void update(Book book) {
		sqlSessionTemplate.update("book.dtd.update", book);
	}

	@Override
	public void insert(Book book) {
		sqlSessionTemplate.insert("book.dtd.insert", book);
		
	}

	@Override
	public Book selectByNo(Long no) {
		List<Book> results = sqlSessionTemplate.selectList("book.dtd.selectByNo", no);
		return results.isEmpty()? null : results.get(0);
	}

	@Override
	public List<Book> list(SearchCriteria cri) throws Exception {
		return sqlSessionTemplate.selectList("book.dtd.listPage", cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return sqlSessionTemplate.selectOne("book.dtd.listCount", cri);
	}

	@Override
	public List<Book> searchByCategory(SearchCriteria cri) {
		System.out.println(cri);
		return sqlSessionTemplate.selectList("book.dtd.searchByCategory", cri);
	}

	@Override
	public int listCountByCategory(SearchCriteria cri) throws Exception {
		return sqlSessionTemplate.selectOne("book.dtd.listCountByCategory", cri);
	}

	@Override
	public Object selectByIsbn(String isbn) {
		return sqlSessionTemplate.selectOne("book.dtd.selectByIsbn", isbn);
	}

}
