package site.manager.books.commons.paging;

//@Component 
public class SearchCriteria extends Criteria{
	private String keyword;//검색 입력값
	private String category;//<select>선택값(제목,저자 등)
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "SearchCriteria [keyword=" + keyword + ", category=" + category + "]";
	}

	
}
