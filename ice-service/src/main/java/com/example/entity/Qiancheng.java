package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * qiancheng
 * @author 
 */
@Document(indexName = "qiancheng*", type = "doc")
public class Qiancheng implements Serializable {
    @Id
    private String id;

    private String position;

    private String company;

    private String city;

    private String region;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    private Double maxPrice;

    private Double minPrice;

    private Double avgPrice;

    private String profession;

    private String companyType;

    private String location;

    private String cotype;

    private String degree;

    private String workyear;

    private String companySize;

    private String jobTerm;

    private String positionUrl;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Double getmaxPrice() {
        return maxPrice;
    }

    public void setmaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getminPrice() {
        return minPrice;
    }

    public void setminPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getavgPrice() {
        return avgPrice;
    }

    public void setavgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getcompanyType() {
        return companyType;
    }

    public void setcompanyType(String companyType) {
        this.companyType = companyType;
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

    public String getcompanySize() {
        return companySize;
    }

    public void setcompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getjobTerm() {
        return jobTerm;
    }

    public void setjobTerm(String jobTerm) {
        this.jobTerm = jobTerm;
    }

    public String getpositionUrl() {
        return positionUrl;
    }

    public void setpositionUrl(String positionUrl) {
        this.positionUrl = positionUrl;
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
            && (this.getmaxPrice() == null ? other.getmaxPrice() == null : this.getmaxPrice().equals(other.getmaxPrice()))
            && (this.getminPrice() == null ? other.getminPrice() == null : this.getminPrice().equals(other.getminPrice()))
            && (this.getavgPrice() == null ? other.getavgPrice() == null : this.getavgPrice().equals(other.getavgPrice()))
            && (this.getProfession() == null ? other.getProfession() == null : this.getProfession().equals(other.getProfession()))
            && (this.getcompanyType() == null ? other.getcompanyType() == null : this.getcompanyType().equals(other.getcompanyType()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCotype() == null ? other.getCotype() == null : this.getCotype().equals(other.getCotype()))
            && (this.getDegree() == null ? other.getDegree() == null : this.getDegree().equals(other.getDegree()))
            && (this.getWorkyear() == null ? other.getWorkyear() == null : this.getWorkyear().equals(other.getWorkyear()))
            && (this.getcompanySize() == null ? other.getcompanySize() == null : this.getcompanySize().equals(other.getcompanySize()))
            && (this.getjobTerm() == null ? other.getjobTerm() == null : this.getjobTerm().equals(other.getjobTerm()))
            && (this.getpositionUrl() == null ? other.getpositionUrl() == null : this.getpositionUrl().equals(other.getpositionUrl()));
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
        result = prime * result + ((getmaxPrice() == null) ? 0 : getmaxPrice().hashCode());
        result = prime * result + ((getminPrice() == null) ? 0 : getminPrice().hashCode());
        result = prime * result + ((getavgPrice() == null) ? 0 : getavgPrice().hashCode());
        result = prime * result + ((getProfession() == null) ? 0 : getProfession().hashCode());
        result = prime * result + ((getcompanyType() == null) ? 0 : getcompanyType().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCotype() == null) ? 0 : getCotype().hashCode());
        result = prime * result + ((getDegree() == null) ? 0 : getDegree().hashCode());
        result = prime * result + ((getWorkyear() == null) ? 0 : getWorkyear().hashCode());
        result = prime * result + ((getcompanySize() == null) ? 0 : getcompanySize().hashCode());
        result = prime * result + ((getjobTerm() == null) ? 0 : getjobTerm().hashCode());
        result = prime * result + ((getpositionUrl() == null) ? 0 : getpositionUrl().hashCode());
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
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", avgPrice=").append(avgPrice);
        sb.append(", profession=").append(profession);
        sb.append(", companyType=").append(companyType);
        sb.append(", location=").append(location);
        sb.append(", cotype=").append(cotype);
        sb.append(", degree=").append(degree);
        sb.append(", workyear=").append(workyear);
        sb.append(", companySize=").append(companySize);
        sb.append(", jobTerm=").append(jobTerm);
        sb.append(", positionUrl=").append(positionUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}