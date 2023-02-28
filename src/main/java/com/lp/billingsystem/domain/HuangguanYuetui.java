package com.lp.billingsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

/**
 * 
 * @TableName huangguan_yuetui
 */
@TableName(value ="huangguan_yuetui")
@Data
public class HuangguanYuetui implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 代码
     */
    private String zh;

    /**
     * 股东
     */
    private String gd;

    /**
     * 股东结果
     */
    private String gdjg;

    /**
     * 股东实货量
     */
    private String gdshl;

    /**
     * 总代理结果
     */
    private String zdljg;

    /**
     * 总代理实货量
     */
    private String zdlshl;

    /**
     * 总代理百分比
     */
    private String zdlbfb;

    /**
     * 股东占成
     */
    private String gdzc;

    /**
     * 股东百分比
     */
    private String gdbfb;

    /**
     * 代理商
     */
    private String dls;

    /**
     * 代理商占成
     */
    private String dlszc;

    /**
     * 代理商结果
     */
    private String dlsjg;

    /**
     * 代理商实货量
     */
    private String dlsshl;

    /**
     * 总代理占成
     */
    private String zdlzc;

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
     * 开始日期
     */
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
    private LocalDateTime endDate;

    /**
     * 笔数
     */
    private String bs;

    /**
     * 下注金额
     */
    private String xzje;

    /**
     * 有效金额
     */
    private String yxje;

    /**
     * 会员
     */
    private String hy;

    /**
     * 会员币值
     */
    private String hybz;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setZh(String zh) {

        if (zh !=null){
            Pattern pattern = Pattern.compile("^[A-Za-z0-9]+");
            Matcher matcher = pattern.matcher(zh);
            String str = "";
            while (matcher.find()){
                str += matcher.group();
            }
            zh = str;
        }

        this.zh = zh;
    }

    public void setGd(String gd) {
        if (gd !=null)
            this.gd = gd.replace(",","");
    }

    public void setGdjg(String gdjg) {
        if (gdjg !=null)
            this.gdjg = gdjg.replace(",","");
    }

    public void setGdshl(String gdshl) {
        if (gdshl !=null)
            this.gdshl = gdshl.replace(",","");
    }

    public void setZdljg(String zdljg) {
        if (zdljg !=null)
            this.zdljg = zdljg.replace(",","");
    }

    public void setZdlshl(String zdlshl) {
        if (zdlshl !=null)
            this.zdlshl = zdlshl.replace(",","");
    }

    public void setZdlbfb(String zdlbfb) {
        if (zdlbfb !=null)
            this.zdlbfb = zdlbfb.replace(",","");
    }

    public void setGdzc(String gdzc) {
        if (gdzc !=null)
            this.gdzc = gdzc.replace(",","");
    }

    public void setGdbfb(String gdbfb) {
        if (gdbfb !=null)
            this.gdbfb = gdbfb.replace(",","");
    }

    public void setDls(String dls) {
        if (dls !=null)
            this.dls = dls.replace(",","");
    }

    public void setDlszc(String dlszc) {
        if (dlszc !=null)
            this.dlszc = dlszc.replace(",","");
    }

    public void setDlsjg(String dlsjg) {
        if (dlsjg !=null)
            this.dlsjg = dlsjg.replace(",","");
    }

    public void setDlsshl(String dlsshl) {
        if (dlsshl !=null)
            this.dlsshl = dlsshl.replace(",","");
    }

    public void setZdlzc(String zdlzc) {
        if (zdlzc !=null)
            this.zdlzc = zdlzc.replace(",","");
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setNumberOfLayers(Integer numberOfLayers) {
        this.numberOfLayers = numberOfLayers;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setBs(String bs) {
        if (bs !=null)
            this.bs = bs.replace(",","");
    }

    public void setXzje(String xzje) {
        if (xzje !=null)
            this.xzje = xzje.replace(",","");
    }

    public void setYxje(String yxje) {
        if (yxje !=null)
            this.yxje = yxje.replace(",","");
    }

    public void setHy(String hy) {
        if (hy !=null)
            this.hy = hy.replace(",","");
    }

    public void setHybz(String hybz) {
        if (hybz !=null)
            this.hybz = hybz.replace(",","");
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
        Huangguan other = (Huangguan) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getZh() == null ? other.getZh() == null : this.getZh().equals(other.getZh()))
                && (this.getGd() == null ? other.getGd() == null : this.getGd().equals(other.getGd()))
                && (this.getGdjg() == null ? other.getGdjg() == null : this.getGdjg().equals(other.getGdjg()))
                && (this.getGdshl() == null ? other.getGdshl() == null : this.getGdshl().equals(other.getGdshl()))
                && (this.getZdljg() == null ? other.getZdljg() == null : this.getZdljg().equals(other.getZdljg()))
                && (this.getZdlshl() == null ? other.getZdlshl() == null : this.getZdlshl().equals(other.getZdlshl()))
                && (this.getZdlbfb() == null ? other.getZdlbfb() == null : this.getZdlbfb().equals(other.getZdlbfb()))
                && (this.getGdzc() == null ? other.getGdzc() == null : this.getGdzc().equals(other.getGdzc()))
                && (this.getGdbfb() == null ? other.getGdbfb() == null : this.getGdbfb().equals(other.getGdbfb()))
                && (this.getDls() == null ? other.getDls() == null : this.getDls().equals(other.getDls()))
                && (this.getDlszc() == null ? other.getDlszc() == null : this.getDlszc().equals(other.getDlszc()))
                && (this.getDlsjg() == null ? other.getDlsjg() == null : this.getDlsjg().equals(other.getDlsjg()))
                && (this.getDlsshl() == null ? other.getDlsshl() == null : this.getDlsshl().equals(other.getDlsshl()))
                && (this.getZdlzc() == null ? other.getZdlzc() == null : this.getZdlzc().equals(other.getZdlzc()))
                && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getAccountNumber() == null ? other.getAccountNumber() == null : this.getAccountNumber().equals(other.getAccountNumber()))
                && (this.getNumberOfLayers() == null ? other.getNumberOfLayers() == null : this.getNumberOfLayers().equals(other.getNumberOfLayers()))
                && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
                && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
                && (this.getBs() == null ? other.getBs() == null : this.getBs().equals(other.getBs()))
                && (this.getXzje() == null ? other.getXzje() == null : this.getXzje().equals(other.getXzje()))
                && (this.getYxje() == null ? other.getYxje() == null : this.getYxje().equals(other.getYxje()))
                && (this.getHy() == null ? other.getHy() == null : this.getHy().equals(other.getHy()))
                && (this.getHybz() == null ? other.getHybz() == null : this.getHybz().equals(other.getHybz()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getZh() == null) ? 0 : getZh().hashCode());
        result = prime * result + ((getGd() == null) ? 0 : getGd().hashCode());
        result = prime * result + ((getGdjg() == null) ? 0 : getGdjg().hashCode());
        result = prime * result + ((getGdshl() == null) ? 0 : getGdshl().hashCode());
        result = prime * result + ((getZdljg() == null) ? 0 : getZdljg().hashCode());
        result = prime * result + ((getZdlshl() == null) ? 0 : getZdlshl().hashCode());
        result = prime * result + ((getZdlbfb() == null) ? 0 : getZdlbfb().hashCode());
        result = prime * result + ((getGdzc() == null) ? 0 : getGdzc().hashCode());
        result = prime * result + ((getGdbfb() == null) ? 0 : getGdbfb().hashCode());
        result = prime * result + ((getDls() == null) ? 0 : getDls().hashCode());
        result = prime * result + ((getDlszc() == null) ? 0 : getDlszc().hashCode());
        result = prime * result + ((getDlsjg() == null) ? 0 : getDlsjg().hashCode());
        result = prime * result + ((getDlsshl() == null) ? 0 : getDlsshl().hashCode());
        result = prime * result + ((getZdlzc() == null) ? 0 : getZdlzc().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAccountNumber() == null) ? 0 : getAccountNumber().hashCode());
        result = prime * result + ((getNumberOfLayers() == null) ? 0 : getNumberOfLayers().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getBs() == null) ? 0 : getBs().hashCode());
        result = prime * result + ((getXzje() == null) ? 0 : getXzje().hashCode());
        result = prime * result + ((getYxje() == null) ? 0 : getYxje().hashCode());
        result = prime * result + ((getHy() == null) ? 0 : getHy().hashCode());
        result = prime * result + ((getHybz() == null) ? 0 : getHybz().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", zh=").append(zh);
        sb.append(", gd=").append(gd);
        sb.append(", gdjg=").append(gdjg);
        sb.append(", gdshl=").append(gdshl);
        sb.append(", zdljg=").append(zdljg);
        sb.append(", zdlshl=").append(zdlshl);
        sb.append(", zdlbfb=").append(zdlbfb);
        sb.append(", gdzc=").append(gdzc);
        sb.append(", gdbfb=").append(gdbfb);
        sb.append(", dls=").append(dls);
        sb.append(", dlszc=").append(dlszc);
        sb.append(", dlsjg=").append(dlsjg);
        sb.append(", dlsshl=").append(dlsshl);
        sb.append(", zdlzc=").append(zdlzc);
        sb.append(", createdate=").append(createdate);
        sb.append(", type=").append(type);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", numberOfLayers=").append(numberOfLayers);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", bs=").append(bs);
        sb.append(", xzje=").append(xzje);
        sb.append(", yxje=").append(yxje);
        sb.append(", hy=").append(hy);
        sb.append(", hybz=").append(hybz);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}