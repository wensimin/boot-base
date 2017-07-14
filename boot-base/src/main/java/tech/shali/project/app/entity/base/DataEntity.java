package tech.shali.project.app.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DataEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Date createDate;
	@Column(nullable = false)
	private Date updateDate;
	@Column(nullable = false)
	private Boolean del;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public void beforeInsert() {
		createDate = new Date();
		updateDate = new Date();
		del = false;
	}

	public void beforeUpdate() {
		updateDate = new Date();
	}
}
