package com.lp.billingsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName yaxing
 */
@TableName(value ="yaxing")
@Data
public class Yaxing implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 级别
     */
    private String jb;

    /**
     * 账号
     */
    private String zh;

    /**
     * 别名
     */
    private String bm;

    /**
     * 类型
     */
    private String lx;

    /**
     * 笔数
     */
    private String bs;

    /**
     * 投注金额
     */
    private String tzje;

    /**
     * 总洗马量
     */
    private String zxml;

    /**
     * 输赢金额
     */
    private String syje;

    /**
     * 
     */
    private String gdzcb;

    /**
     * 总代理占成比
     */
    private String zdlzcb;

    /**
     * 交上线输赢
     */
    private String jsxsy;

    /**
     * 交上线洗马量
     */
    private String jsxxml;

    /**
     * 洗马比
     */
    private String xmb;

    /**
     * 洗马佣金
     */
    private String xmyj;

    /**
     * 交上线结果
     */
    private String jsxjg;

    /**
     * 获利率
     */
    private String hll;

    /**
     * 股东结果
     */
    private String gdjg;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 1周账 2：月账 
     */
    private String type;

    /**
     * 所属账号
     */
    private String accountNumber;

    /**
     * 层数
     */
    private Integer numberOfLayers;

    /**
     * 
     */
    private LocalDateTime startDate;

    /**
     * 
     */
    private LocalDateTime endDate;


    public void setJb(String jb) {
        this.jb = jb;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public void setTzje(String tzje) {
        this.tzje = tzje.replace(",","");
    }

    public void setZxml(String zxml) {
        this.zxml = zxml.replace(",","");
    }

    public void setSyje(String syje) {
        this.syje = syje.replace(",","");
    }

    public void setGdzcb(String gdzcb) {
        this.gdzcb = gdzcb.replace(",","");
    }

    public void setZdlzcb(String zdlzcb) {
        this.zdlzcb = zdlzcb.replace(",","");
    }

    public void setJsxsy(String jsxsy) {
        this.jsxsy = jsxsy.replace(",","");
    }

    public void setJsxxml(String jsxxml) {
        this.jsxxml = jsxxml.replace(",","");
    }

    public void setXmb(String xmb) {
        this.xmb = xmb.replace(",","");
    }

    public void setXmyj(String xmyj) {
        this.xmyj = xmyj.replace(",","");
    }

    public void setJsxjg(String jsxjg) {
        this.jsxjg = jsxjg.replace(",","");
    }

    public void setHll(String hll) {
        this.hll = hll.replace(",","");
    }

    public void setGdjg(String gdjg) {
        this.gdjg = gdjg.replace(",","");
    }

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
        Yaxing other = (Yaxing) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getJb() == null ? other.getJb() == null : this.getJb().equals(other.getJb()))
            && (this.getZh() == null ? other.getZh() == null : this.getZh().equals(other.getZh()))
            && (this.getBm() == null ? other.getBm() == null : this.getBm().equals(other.getBm()))
            && (this.getLx() == null ? other.getLx() == null : this.getLx().equals(other.getLx()))
            && (this.getBs() == null ? other.getBs() == null : this.getBs().equals(other.getBs()))
            && (this.getTzje() == null ? other.getTzje() == null : this.getTzje().equals(other.getTzje()))
            && (this.getZxml() == null ? other.getZxml() == null : this.getZxml().equals(other.getZxml()))
            && (this.getSyje() == null ? other.getSyje() == null : this.getSyje().equals(other.getSyje()))
            && (this.getGdzcb() == null ? other.getGdzcb() == null : this.getGdzcb().equals(other.getGdzcb()))
            && (this.getZdlzcb() == null ? other.getZdlzcb() == null : this.getZdlzcb().equals(other.getZdlzcb()))
            && (this.getJsxsy() == null ? other.getJsxsy() == null : this.getJsxsy().equals(other.getJsxsy()))
            && (this.getJsxxml() == null ? other.getJsxxml() == null : this.getJsxxml().equals(other.getJsxxml()))
            && (this.getXmb() == null ? other.getXmb() == null : this.getXmb().equals(other.getXmb()))
            && (this.getXmyj() == null ? other.getXmyj() == null : this.getXmyj().equals(other.getXmyj()))
            && (this.getJsxjg() == null ? other.getJsxjg() == null : this.getJsxjg().equals(other.getJsxjg()))
            && (this.getHll() == null ? other.getHll() == null : this.getHll().equals(other.getHll()))
            && (this.getGdjg() == null ? other.getGdjg() == null : this.getGdjg().equals(other.getGdjg()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAccountNumber() == null ? other.getAccountNumber() == null : this.getAccountNumber().equals(other.getAccountNumber()))
            && (this.getNumberOfLayers() == null ? other.getNumberOfLayers() == null : this.getNumberOfLayers().equals(other.getNumberOfLayers()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getJb() == null) ? 0 : getJb().hashCode());
        result = prime * result + ((getZh() == null) ? 0 : getZh().hashCode());
        result = prime * result + ((getBm() == null) ? 0 : getBm().hashCode());
        result = prime * result + ((getLx() == null) ? 0 : getLx().hashCode());
        result = prime * result + ((getBs() == null) ? 0 : getBs().hashCode());
        result = prime * result + ((getTzje() == null) ? 0 : getTzje().hashCode());
        result = prime * result + ((getZxml() == null) ? 0 : getZxml().hashCode());
        result = prime * result + ((getSyje() == null) ? 0 : getSyje().hashCode());
        result = prime * result + ((getGdzcb() == null) ? 0 : getGdzcb().hashCode());
        result = prime * result + ((getZdlzcb() == null) ? 0 : getZdlzcb().hashCode());
        result = prime * result + ((getJsxsy() == null) ? 0 : getJsxsy().hashCode());
        result = prime * result + ((getJsxxml() == null) ? 0 : getJsxxml().hashCode());
        result = prime * result + ((getXmb() == null) ? 0 : getXmb().hashCode());
        result = prime * result + ((getXmyj() == null) ? 0 : getXmyj().hashCode());
        result = prime * result + ((getJsxjg() == null) ? 0 : getJsxjg().hashCode());
        result = prime * result + ((getHll() == null) ? 0 : getHll().hashCode());
        result = prime * result + ((getGdjg() == null) ? 0 : getGdjg().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAccountNumber() == null) ? 0 : getAccountNumber().hashCode());
        result = prime * result + ((getNumberOfLayers() == null) ? 0 : getNumberOfLayers().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jb=").append(jb);
        sb.append(", zh=").append(zh);
        sb.append(", bm=").append(bm);
        sb.append(", lx=").append(lx);
        sb.append(", bs=").append(bs);
        sb.append(", tzje=").append(tzje);
        sb.append(", zxml=").append(zxml);
        sb.append(", syje=").append(syje);
        sb.append(", gdzcb=").append(gdzcb);
        sb.append(", zdlzcb=").append(zdlzcb);
        sb.append(", jsxsy=").append(jsxsy);
        sb.append(", jsxxml=").append(jsxxml);
        sb.append(", xmb=").append(xmb);
        sb.append(", xmyj=").append(xmyj);
        sb.append(", jsxjg=").append(jsxjg);
        sb.append(", hll=").append(hll);
        sb.append(", gdjg=").append(gdjg);
        sb.append(", createdate=").append(createdate);
        sb.append(", type=").append(type);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", numberOfLayers=").append(numberOfLayers);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}