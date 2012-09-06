package com.fusionbeam.database.entity;

import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 28/08/12
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditableImpl implements Auditable {
    @Column(name="created_time", nullable = false)
    private Date createdTime;

    @Column(name="last_modified_time", nullable = false)
    private Date lastModifiedTime;

    @Version
    private long version = 0;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getVersion() {
        return version;
    }
}
