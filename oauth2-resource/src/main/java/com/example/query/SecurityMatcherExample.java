package com.example.query;

import java.util.ArrayList;
import java.util.List;

public class SecurityMatcherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public SecurityMatcherExample() {
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

        public Criteria andMatchersIsNull() {
            addCriterion("matchers is null");
            return (Criteria) this;
        }

        public Criteria andMatchersIsNotNull() {
            addCriterion("matchers is not null");
            return (Criteria) this;
        }

        public Criteria andMatchersEqualTo(String value) {
            addCriterion("matchers =", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersNotEqualTo(String value) {
            addCriterion("matchers <>", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersGreaterThan(String value) {
            addCriterion("matchers >", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersGreaterThanOrEqualTo(String value) {
            addCriterion("matchers >=", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersLessThan(String value) {
            addCriterion("matchers <", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersLessThanOrEqualTo(String value) {
            addCriterion("matchers <=", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersLike(String value) {
            addCriterion("matchers like", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersNotLike(String value) {
            addCriterion("matchers not like", value, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersIn(List<String> values) {
            addCriterion("matchers in", values, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersNotIn(List<String> values) {
            addCriterion("matchers not in", values, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersBetween(String value1, String value2) {
            addCriterion("matchers between", value1, value2, "matchers");
            return (Criteria) this;
        }

        public Criteria andMatchersNotBetween(String value1, String value2) {
            addCriterion("matchers not between", value1, value2, "matchers");
            return (Criteria) this;
        }

        public Criteria andRolesNameIsNull() {
            addCriterion("roles_name is null");
            return (Criteria) this;
        }

        public Criteria andRolesNameIsNotNull() {
            addCriterion("roles_name is not null");
            return (Criteria) this;
        }

        public Criteria andRolesNameEqualTo(String value) {
            addCriterion("roles_name =", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotEqualTo(String value) {
            addCriterion("roles_name <>", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameGreaterThan(String value) {
            addCriterion("roles_name >", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameGreaterThanOrEqualTo(String value) {
            addCriterion("roles_name >=", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLessThan(String value) {
            addCriterion("roles_name <", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLessThanOrEqualTo(String value) {
            addCriterion("roles_name <=", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLike(String value) {
            addCriterion("roles_name like", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotLike(String value) {
            addCriterion("roles_name not like", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameIn(List<String> values) {
            addCriterion("roles_name in", values, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotIn(List<String> values) {
            addCriterion("roles_name not in", values, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameBetween(String value1, String value2) {
            addCriterion("roles_name between", value1, value2, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotBetween(String value1, String value2) {
            addCriterion("roles_name not between", value1, value2, "rolesName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameIsNull() {
            addCriterion("authoritys_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameIsNotNull() {
            addCriterion("authoritys_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameEqualTo(String value) {
            addCriterion("authoritys_name =", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameNotEqualTo(String value) {
            addCriterion("authoritys_name <>", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameGreaterThan(String value) {
            addCriterion("authoritys_name >", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameGreaterThanOrEqualTo(String value) {
            addCriterion("authoritys_name >=", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameLessThan(String value) {
            addCriterion("authoritys_name <", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameLessThanOrEqualTo(String value) {
            addCriterion("authoritys_name <=", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameLike(String value) {
            addCriterion("authoritys_name like", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameNotLike(String value) {
            addCriterion("authoritys_name not like", value, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameIn(List<String> values) {
            addCriterion("authoritys_name in", values, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameNotIn(List<String> values) {
            addCriterion("authoritys_name not in", values, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameBetween(String value1, String value2) {
            addCriterion("authoritys_name between", value1, value2, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andAuthoritysNameNotBetween(String value1, String value2) {
            addCriterion("authoritys_name not between", value1, value2, "authoritysName");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("ip_address is null");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("ip_address =", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("ip_address <>", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("ip_address >", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ip_address >=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("ip_address <", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("ip_address <=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("ip_address like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("ip_address not like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressIn(List<String> values) {
            addCriterion("ip_address in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotIn(List<String> values) {
            addCriterion("ip_address not in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("ip_address between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("ip_address not between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("`level` is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("`level` is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("`level` =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("`level` <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("`level` >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("`level` >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("`level` <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("`level` <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("`level` in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("`level` not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("`level` between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("`level` not between", value1, value2, "level");
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