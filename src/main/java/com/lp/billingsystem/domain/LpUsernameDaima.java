package com.lp.billingsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lp_username_daima
 */
@TableName(value ="lp_username_daima")
@Data
public class LpUsernameDaima implements Serializable {
    /**
     * 
     */
//    @TableId
    private Integer usernameId;

    /**
     * 
     */
//    @TableId
    private String daima;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private Integer isDel;

    /**
     * 
     */
    private Date createdate;

    /**
     * 
     */
    private String dianshu;

    /**
     * 
     */
    private String chengshu;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        LpUsernameDaima other = (LpUsernameDaima) that;
        return (this.getUsernameId() == null ? other.getUsernameId() == null : this.getUsernameId().equals(other.getUsernameId()))
            && (this.getDaima() == null ? other.getDaima() == null : this.getDaima().equals(other.getDaima()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getDianshu() == null ? other.getDianshu() == null : this.getDianshu().equals(other.getDianshu()))
            && (this.getChengshu() == null ? other.getChengshu() == null : this.getChengshu().equals(other.getChengshu()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUsernameId() == null) ? 0 : getUsernameId().hashCode());
        result = prime * result + ((getDaima() == null) ? 0 : getDaima().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getDianshu() == null) ? 0 : getDianshu().hashCode());
        result = prime * result + ((getChengshu() == null) ? 0 : getChengshu().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", usernameId=").append(usernameId);
        sb.append(", daima=").append(daima);
        sb.append(", username=").append(username);
        sb.append(", isDel=").append(isDel);
        sb.append(", createdate=").append(createdate);
        sb.append(", dianshu=").append(dianshu);
        sb.append(", chengshu=").append(chengshu);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}