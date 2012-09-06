package com.fusionbeam.database.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenm
 * Date: 28/08/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Auditable extends Serializable {
    public Date getCreatedTime();
    public void setCreatedTime(Date time);
    public Date getLastModifiedTime();
    public void setLastModifiedTime(Date time);
}
