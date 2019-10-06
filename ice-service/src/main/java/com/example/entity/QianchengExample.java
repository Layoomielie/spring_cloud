package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QianchengExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public QianchengExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("`position` is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("`position` is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("`position` =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("`position` <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("`position` >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("`position` >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("`position` <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("`position` <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("`position` like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("`position` not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("`position` in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("`position` not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("`position` between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("`position` not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("`date` is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("`date` is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("`date` =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("`date` <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("`date` >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`date` >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("`date` <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("`date` <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("`date` in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("`date` not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("`date` between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("`date` not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("`time` is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("`time` is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("`time` =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("`time` <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("`time` >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`time` >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("`time` <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("`time` <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("`time` in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("`time` not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("`time` between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("`time` not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andMaxpriceIsNull() {
            addCriterion("maxPrice is null");
            return (Criteria) this;
        }

        public Criteria andMaxpriceIsNotNull() {
            addCriterion("maxPrice is not null");
            return (Criteria) this;
        }

        public Criteria andMaxpriceEqualTo(Double value) {
            addCriterion("maxPrice =", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceNotEqualTo(Double value) {
            addCriterion("maxPrice <>", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceGreaterThan(Double value) {
            addCriterion("maxPrice >", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("maxPrice >=", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceLessThan(Double value) {
            addCriterion("maxPrice <", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceLessThanOrEqualTo(Double value) {
            addCriterion("maxPrice <=", value, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceIn(List<Double> values) {
            addCriterion("maxPrice in", values, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceNotIn(List<Double> values) {
            addCriterion("maxPrice not in", values, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceBetween(Double value1, Double value2) {
            addCriterion("maxPrice between", value1, value2, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMaxpriceNotBetween(Double value1, Double value2) {
            addCriterion("maxPrice not between", value1, value2, "maxprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceIsNull() {
            addCriterion("minPrice is null");
            return (Criteria) this;
        }

        public Criteria andMinpriceIsNotNull() {
            addCriterion("minPrice is not null");
            return (Criteria) this;
        }

        public Criteria andMinpriceEqualTo(Double value) {
            addCriterion("minPrice =", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotEqualTo(Double value) {
            addCriterion("minPrice <>", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceGreaterThan(Double value) {
            addCriterion("minPrice >", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("minPrice >=", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceLessThan(Double value) {
            addCriterion("minPrice <", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceLessThanOrEqualTo(Double value) {
            addCriterion("minPrice <=", value, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceIn(List<Double> values) {
            addCriterion("minPrice in", values, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotIn(List<Double> values) {
            addCriterion("minPrice not in", values, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceBetween(Double value1, Double value2) {
            addCriterion("minPrice between", value1, value2, "minprice");
            return (Criteria) this;
        }

        public Criteria andMinpriceNotBetween(Double value1, Double value2) {
            addCriterion("minPrice not between", value1, value2, "minprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIsNull() {
            addCriterion("avgPrice is null");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIsNotNull() {
            addCriterion("avgPrice is not null");
            return (Criteria) this;
        }

        public Criteria andAvgpriceEqualTo(Double value) {
            addCriterion("avgPrice =", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotEqualTo(Double value) {
            addCriterion("avgPrice <>", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceGreaterThan(Double value) {
            addCriterion("avgPrice >", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("avgPrice >=", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceLessThan(Double value) {
            addCriterion("avgPrice <", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceLessThanOrEqualTo(Double value) {
            addCriterion("avgPrice <=", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIn(List<Double> values) {
            addCriterion("avgPrice in", values, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotIn(List<Double> values) {
            addCriterion("avgPrice not in", values, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceBetween(Double value1, Double value2) {
            addCriterion("avgPrice between", value1, value2, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotBetween(Double value1, Double value2) {
            addCriterion("avgPrice not between", value1, value2, "avgprice");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNull() {
            addCriterion("profession is null");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNotNull() {
            addCriterion("profession is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionEqualTo(String value) {
            addCriterion("profession =", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotEqualTo(String value) {
            addCriterion("profession <>", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThan(String value) {
            addCriterion("profession >", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("profession >=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThan(String value) {
            addCriterion("profession <", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThanOrEqualTo(String value) {
            addCriterion("profession <=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLike(String value) {
            addCriterion("profession like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotLike(String value) {
            addCriterion("profession not like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionIn(List<String> values) {
            addCriterion("profession in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotIn(List<String> values) {
            addCriterion("profession not in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionBetween(String value1, String value2) {
            addCriterion("profession between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotBetween(String value1, String value2) {
            addCriterion("profession not between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIsNull() {
            addCriterion("companyType is null");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIsNotNull() {
            addCriterion("companyType is not null");
            return (Criteria) this;
        }

        public Criteria andCompanytypeEqualTo(String value) {
            addCriterion("companyType =", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotEqualTo(String value) {
            addCriterion("companyType <>", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeGreaterThan(String value) {
            addCriterion("companyType >", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeGreaterThanOrEqualTo(String value) {
            addCriterion("companyType >=", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeLessThan(String value) {
            addCriterion("companyType <", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeLessThanOrEqualTo(String value) {
            addCriterion("companyType <=", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeLike(String value) {
            addCriterion("companyType like", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotLike(String value) {
            addCriterion("companyType not like", value, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeIn(List<String> values) {
            addCriterion("companyType in", values, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotIn(List<String> values) {
            addCriterion("companyType not in", values, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeBetween(String value1, String value2) {
            addCriterion("companyType between", value1, value2, "companytype");
            return (Criteria) this;
        }

        public Criteria andCompanytypeNotBetween(String value1, String value2) {
            addCriterion("companyType not between", value1, value2, "companytype");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("`location` is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("`location` is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("`location` =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("`location` <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("`location` >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("`location` >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("`location` <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("`location` <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("`location` like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("`location` not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("`location` in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("`location` not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("`location` between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("`location` not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andCotypeIsNull() {
            addCriterion("cotype is null");
            return (Criteria) this;
        }

        public Criteria andCotypeIsNotNull() {
            addCriterion("cotype is not null");
            return (Criteria) this;
        }

        public Criteria andCotypeEqualTo(String value) {
            addCriterion("cotype =", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotEqualTo(String value) {
            addCriterion("cotype <>", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeGreaterThan(String value) {
            addCriterion("cotype >", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeGreaterThanOrEqualTo(String value) {
            addCriterion("cotype >=", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeLessThan(String value) {
            addCriterion("cotype <", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeLessThanOrEqualTo(String value) {
            addCriterion("cotype <=", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeLike(String value) {
            addCriterion("cotype like", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotLike(String value) {
            addCriterion("cotype not like", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeIn(List<String> values) {
            addCriterion("cotype in", values, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotIn(List<String> values) {
            addCriterion("cotype not in", values, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeBetween(String value1, String value2) {
            addCriterion("cotype between", value1, value2, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotBetween(String value1, String value2) {
            addCriterion("cotype not between", value1, value2, "cotype");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("`degree` is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("`degree` is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(String value) {
            addCriterion("`degree` =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(String value) {
            addCriterion("`degree` <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(String value) {
            addCriterion("`degree` >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("`degree` >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(String value) {
            addCriterion("`degree` <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(String value) {
            addCriterion("`degree` <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLike(String value) {
            addCriterion("`degree` like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotLike(String value) {
            addCriterion("`degree` not like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<String> values) {
            addCriterion("`degree` in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<String> values) {
            addCriterion("`degree` not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(String value1, String value2) {
            addCriterion("`degree` between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(String value1, String value2) {
            addCriterion("`degree` not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andWorkyearIsNull() {
            addCriterion("workyear is null");
            return (Criteria) this;
        }

        public Criteria andWorkyearIsNotNull() {
            addCriterion("workyear is not null");
            return (Criteria) this;
        }

        public Criteria andWorkyearEqualTo(String value) {
            addCriterion("workyear =", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearNotEqualTo(String value) {
            addCriterion("workyear <>", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearGreaterThan(String value) {
            addCriterion("workyear >", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearGreaterThanOrEqualTo(String value) {
            addCriterion("workyear >=", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearLessThan(String value) {
            addCriterion("workyear <", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearLessThanOrEqualTo(String value) {
            addCriterion("workyear <=", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearLike(String value) {
            addCriterion("workyear like", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearNotLike(String value) {
            addCriterion("workyear not like", value, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearIn(List<String> values) {
            addCriterion("workyear in", values, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearNotIn(List<String> values) {
            addCriterion("workyear not in", values, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearBetween(String value1, String value2) {
            addCriterion("workyear between", value1, value2, "workyear");
            return (Criteria) this;
        }

        public Criteria andWorkyearNotBetween(String value1, String value2) {
            addCriterion("workyear not between", value1, value2, "workyear");
            return (Criteria) this;
        }

        public Criteria andCompanysizeIsNull() {
            addCriterion("companySize is null");
            return (Criteria) this;
        }

        public Criteria andCompanysizeIsNotNull() {
            addCriterion("companySize is not null");
            return (Criteria) this;
        }

        public Criteria andCompanysizeEqualTo(String value) {
            addCriterion("companySize =", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeNotEqualTo(String value) {
            addCriterion("companySize <>", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeGreaterThan(String value) {
            addCriterion("companySize >", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeGreaterThanOrEqualTo(String value) {
            addCriterion("companySize >=", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeLessThan(String value) {
            addCriterion("companySize <", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeLessThanOrEqualTo(String value) {
            addCriterion("companySize <=", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeLike(String value) {
            addCriterion("companySize like", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeNotLike(String value) {
            addCriterion("companySize not like", value, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeIn(List<String> values) {
            addCriterion("companySize in", values, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeNotIn(List<String> values) {
            addCriterion("companySize not in", values, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeBetween(String value1, String value2) {
            addCriterion("companySize between", value1, value2, "companysize");
            return (Criteria) this;
        }

        public Criteria andCompanysizeNotBetween(String value1, String value2) {
            addCriterion("companySize not between", value1, value2, "companysize");
            return (Criteria) this;
        }

        public Criteria andJobtermIsNull() {
            addCriterion("jobTerm is null");
            return (Criteria) this;
        }

        public Criteria andJobtermIsNotNull() {
            addCriterion("jobTerm is not null");
            return (Criteria) this;
        }

        public Criteria andJobtermEqualTo(String value) {
            addCriterion("jobTerm =", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermNotEqualTo(String value) {
            addCriterion("jobTerm <>", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermGreaterThan(String value) {
            addCriterion("jobTerm >", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermGreaterThanOrEqualTo(String value) {
            addCriterion("jobTerm >=", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermLessThan(String value) {
            addCriterion("jobTerm <", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermLessThanOrEqualTo(String value) {
            addCriterion("jobTerm <=", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermLike(String value) {
            addCriterion("jobTerm like", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermNotLike(String value) {
            addCriterion("jobTerm not like", value, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermIn(List<String> values) {
            addCriterion("jobTerm in", values, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermNotIn(List<String> values) {
            addCriterion("jobTerm not in", values, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermBetween(String value1, String value2) {
            addCriterion("jobTerm between", value1, value2, "jobterm");
            return (Criteria) this;
        }

        public Criteria andJobtermNotBetween(String value1, String value2) {
            addCriterion("jobTerm not between", value1, value2, "jobterm");
            return (Criteria) this;
        }

        public Criteria andPositionurlIsNull() {
            addCriterion("positionUrl is null");
            return (Criteria) this;
        }

        public Criteria andPositionurlIsNotNull() {
            addCriterion("positionUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPositionurlEqualTo(String value) {
            addCriterion("positionUrl =", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlNotEqualTo(String value) {
            addCriterion("positionUrl <>", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlGreaterThan(String value) {
            addCriterion("positionUrl >", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlGreaterThanOrEqualTo(String value) {
            addCriterion("positionUrl >=", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlLessThan(String value) {
            addCriterion("positionUrl <", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlLessThanOrEqualTo(String value) {
            addCriterion("positionUrl <=", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlLike(String value) {
            addCriterion("positionUrl like", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlNotLike(String value) {
            addCriterion("positionUrl not like", value, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlIn(List<String> values) {
            addCriterion("positionUrl in", values, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlNotIn(List<String> values) {
            addCriterion("positionUrl not in", values, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlBetween(String value1, String value2) {
            addCriterion("positionUrl between", value1, value2, "positionurl");
            return (Criteria) this;
        }

        public Criteria andPositionurlNotBetween(String value1, String value2) {
            addCriterion("positionUrl not between", value1, value2, "positionurl");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}