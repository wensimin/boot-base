package tech.shali.project.app.entity.base;

import tech.shali.project.app.entity.listeners.DataEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 公共dataEntity
 */
@MappedSuperclass
@EntityListeners(DataEntityListener.class)
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
