package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * qiancheng
 * @author 
 */
@Document(indexName = "qiancheng", type = "doc")
public class Qiancheng implements Serializable {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    private String position;

    private String company;

    private String city;

    private String region;

    private Date date;

    private Date time;

    private Double maxprice;

    private Double minprice;

    private Double avgprice;

    private String profession;

    private String companytype;

    private String location;

    private String cotype;

    private String degree;

    private String workyear;

    private String companysize;

    private String jobterm;

    private String positionurl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Double maxprice) {
        this.maxprice = maxprice;
    }

    public Double getMinprice() {
        return minprice;
    }

    public void setMinprice(Double minprice) {
        this.minprice = minprice;
    }

    public Double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(Double avgprice) {
        this.avgprice = avgprice;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCotype() {
        return cotype;
    }

    public void setCotype(String cotype) {
        this.cotype = cotype;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getCompanysize() {
        return companysize;
    }

    public void setCompanysize(String companysize) {
        this.companysize = companysize;
    }

    public String getJobterm() {
        return jobterm;
    }

    public void setJobterm(String jobterm) {
        this.jobterm = jobterm;
    }

    public String getPositionurl() {
        return positionurl;
    }

    public void setPositionurl(String positionurl) {
        this.positionurl = positionurl;
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
        Qiancheng other = (Qiancheng) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getMaxprice() == null ? other.getMaxprice() == null : this.getMaxprice().equals(other.getMaxprice()))
            && (this.getMinprice() == null ? other.getMinprice() == null : this.getMinprice().equals(other.getMinprice()))
            && (this.getAvgprice() == null ? other.getAvgprice() == null : this.getAvgprice().equals(other.getAvgprice()))
            && (this.getProfession() == null ? other.getProfession() == null : this.getProfession().equals(other.getProfession()))
            && (this.getCompanytype() == null ? other.getCompanytype() == null : this.getCompanytype().equals(other.getCompanytype()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCotype() == null ? other.getCotype() == null : this.getCotype().equals(other.getCotype()))
            && (this.getDegree() == null ? other.getDegree() == null : this.getDegree().equals(other.getDegree()))
            && (this.getWorkyear() == null ? other.getWorkyear() == null : this.getWorkyear().equals(other.getWorkyear()))
            && (this.getCompanysize() == null ? other.getCompanysize() == null : this.getCompanysize().equals(other.getCompanysize()))
            && (this.getJobterm() == null ? other.getJobterm() == null : this.getJobterm().equals(other.getJobterm()))
            && (this.getPositionurl() == null ? other.getPositionurl() == null : this.getPositionurl().equals(other.getPositionurl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getMaxprice() == null) ? 0 : getMaxprice().hashCode());
        result = prime * result + ((getMinprice() == null) ? 0 : getMinprice().hashCode());
        result = prime * result + ((getAvgprice() == null) ? 0 : getAvgprice().hashCode());
        result = prime * result + ((getProfession() == null) ? 0 : getProfession().hashCode());
        result = prime * result + ((getCompanytype() == null) ? 0 : getCompanytype().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCotype() == null) ? 0 : getCotype().hashCode());
        result = prime * result + ((getDegree() == null) ? 0 : getDegree().hashCode());
        result = prime * result + ((getWorkyear() == null) ? 0 : getWorkyear().hashCode());
        result = prime * result + ((getCompanysize() == null) ? 0 : getCompanysize().hashCode());
        result = prime * result + ((getJobterm() == null) ? 0 : getJobterm().hashCode());
        result = prime * result + ((getPositionurl() == null) ? 0 : getPositionurl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", position=").append(position);
        sb.append(", company=").append(company);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", maxprice=").append(maxprice);
        sb.append(", minprice=").append(minprice);
        sb.append(", avgprice=").append(avgprice);
        sb.append(", profession=").append(profession);
        sb.append(", companytype=").append(companytype);
        sb.append(", location=").append(location);
        sb.append(", cotype=").append(cotype);
        sb.append(", degree=").append(degree);
        sb.append(", workyear=").append(workyear);
        sb.append(", companysize=").append(companysize);
        sb.append(", jobterm=").append(jobterm);
        sb.append(", positionurl=").append(positionurl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}