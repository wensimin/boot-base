package tech.shali.project.app.entity.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class QueryPage<T extends DataEntity> {
	private int page = 0;
	private int pageSize = 10;
	private Direction sort = Direction.ASC;
	private String sortBy = null;
	private T query;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Direction getSort() {
		return sort;
	}

	public void setSort(Direction sort) {
		this.sort = sort;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public T getQuery() {
		return query;
	}

	public QueryPage<T> setQuery(T query) {
		this.query = query;
		return this;
	}

	public PageRequest getPageRequest() {
		if (StringUtils.isEmpty(this.sortBy)) {
			return new PageRequest(page, pageSize);
		} else {
			return new PageRequest(page, pageSize, sort, sortBy);
		}
	}

}
