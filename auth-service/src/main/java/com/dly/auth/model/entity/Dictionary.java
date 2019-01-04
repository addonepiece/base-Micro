package com.dly.auth.model.entity;

import java.io.Serializable;

/**
 * dictionary
 * @author 
 */
public class Dictionary implements Serializable {
    private String uuid;

    /**
     * 字典类型id
     */
    private Integer dicId;

    /**
     * 字典类型名称
     */
    private String dicName;

    /**
     * 字典类型项
     */
    private Integer dicItm;

    /**
     * 字典类型名称
     */
    private String dicItmName;

    /**
     * 排序
     */
    private Integer dicSort;

    /**
     * 1:激活；0:未激活
     */
    private String active;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public Integer getDicItm() {
        return dicItm;
    }

    public void setDicItm(Integer dicItm) {
        this.dicItm = dicItm;
    }

    public String getDicItmName() {
        return dicItmName;
    }

    public void setDicItmName(String dicItmName) {
        this.dicItmName = dicItmName;
    }

    public Integer getDicSort() {
        return dicSort;
    }

    public void setDicSort(Integer dicSort) {
        this.dicSort = dicSort;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Dictionary other = (Dictionary) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getDicId() == null ? other.getDicId() == null : this.getDicId().equals(other.getDicId()))
            && (this.getDicName() == null ? other.getDicName() == null : this.getDicName().equals(other.getDicName()))
            && (this.getDicItm() == null ? other.getDicItm() == null : this.getDicItm().equals(other.getDicItm()))
            && (this.getDicItmName() == null ? other.getDicItmName() == null : this.getDicItmName().equals(other.getDicItmName()))
            && (this.getDicSort() == null ? other.getDicSort() == null : this.getDicSort().equals(other.getDicSort()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getDicId() == null) ? 0 : getDicId().hashCode());
        result = prime * result + ((getDicName() == null) ? 0 : getDicName().hashCode());
        result = prime * result + ((getDicItm() == null) ? 0 : getDicItm().hashCode());
        result = prime * result + ((getDicItmName() == null) ? 0 : getDicItmName().hashCode());
        result = prime * result + ((getDicSort() == null) ? 0 : getDicSort().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", dicId=").append(dicId);
        sb.append(", dicName=").append(dicName);
        sb.append(", dicItm=").append(dicItm);
        sb.append(", dicItmName=").append(dicItmName);
        sb.append(", dicSort=").append(dicSort);
        sb.append(", active=").append(active);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}