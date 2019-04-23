package com.blibee.umbrella.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmbrellaCabinetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmbrellaCabinetExample() {
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

        public Criteria andUmbrellaCabinetNumberIsNull() {
            addCriterion("umbrella_cabinet_number is null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberIsNotNull() {
            addCriterion("umbrella_cabinet_number is not null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberEqualTo(String value) {
            addCriterion("umbrella_cabinet_number =", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberNotEqualTo(String value) {
            addCriterion("umbrella_cabinet_number <>", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberGreaterThan(String value) {
            addCriterion("umbrella_cabinet_number >", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberGreaterThanOrEqualTo(String value) {
            addCriterion("umbrella_cabinet_number >=", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberLessThan(String value) {
            addCriterion("umbrella_cabinet_number <", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberLessThanOrEqualTo(String value) {
            addCriterion("umbrella_cabinet_number <=", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberLike(String value) {
            addCriterion("umbrella_cabinet_number like", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberNotLike(String value) {
            addCriterion("umbrella_cabinet_number not like", value, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberIn(List<String> values) {
            addCriterion("umbrella_cabinet_number in", values, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberNotIn(List<String> values) {
            addCriterion("umbrella_cabinet_number not in", values, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberBetween(String value1, String value2) {
            addCriterion("umbrella_cabinet_number between", value1, value2, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetNumberNotBetween(String value1, String value2) {
            addCriterion("umbrella_cabinet_number not between", value1, value2, "umbrellaCabinetNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(String value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(String value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(String value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(String value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(String value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLike(String value) {
            addCriterion("device_id like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotLike(String value) {
            addCriterion("device_id not like", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<String> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<String> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(String value1, String value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(String value1, String value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andPointIdIsNull() {
            addCriterion("point_id is null");
            return (Criteria) this;
        }

        public Criteria andPointIdIsNotNull() {
            addCriterion("point_id is not null");
            return (Criteria) this;
        }

        public Criteria andPointIdEqualTo(Integer value) {
            addCriterion("point_id =", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdNotEqualTo(Integer value) {
            addCriterion("point_id <>", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdGreaterThan(Integer value) {
            addCriterion("point_id >", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("point_id >=", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdLessThan(Integer value) {
            addCriterion("point_id <", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdLessThanOrEqualTo(Integer value) {
            addCriterion("point_id <=", value, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdIn(List<Integer> values) {
            addCriterion("point_id in", values, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdNotIn(List<Integer> values) {
            addCriterion("point_id not in", values, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdBetween(Integer value1, Integer value2) {
            addCriterion("point_id between", value1, value2, "pointId");
            return (Criteria) this;
        }

        public Criteria andPointIdNotBetween(Integer value1, Integer value2) {
            addCriterion("point_id not between", value1, value2, "pointId");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNull() {
            addCriterion("msisdn is null");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNotNull() {
            addCriterion("msisdn is not null");
            return (Criteria) this;
        }

        public Criteria andMsisdnEqualTo(String value) {
            addCriterion("msisdn =", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotEqualTo(String value) {
            addCriterion("msisdn <>", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThan(String value) {
            addCriterion("msisdn >", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThanOrEqualTo(String value) {
            addCriterion("msisdn >=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThan(String value) {
            addCriterion("msisdn <", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThanOrEqualTo(String value) {
            addCriterion("msisdn <=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLike(String value) {
            addCriterion("msisdn like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotLike(String value) {
            addCriterion("msisdn not like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnIn(List<String> values) {
            addCriterion("msisdn in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotIn(List<String> values) {
            addCriterion("msisdn not in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnBetween(String value1, String value2) {
            addCriterion("msisdn between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotBetween(String value1, String value2) {
            addCriterion("msisdn not between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andVbatIsNull() {
            addCriterion("vbat is null");
            return (Criteria) this;
        }

        public Criteria andVbatIsNotNull() {
            addCriterion("vbat is not null");
            return (Criteria) this;
        }

        public Criteria andVbatEqualTo(String value) {
            addCriterion("vbat =", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatNotEqualTo(String value) {
            addCriterion("vbat <>", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatGreaterThan(String value) {
            addCriterion("vbat >", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatGreaterThanOrEqualTo(String value) {
            addCriterion("vbat >=", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatLessThan(String value) {
            addCriterion("vbat <", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatLessThanOrEqualTo(String value) {
            addCriterion("vbat <=", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatLike(String value) {
            addCriterion("vbat like", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatNotLike(String value) {
            addCriterion("vbat not like", value, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatIn(List<String> values) {
            addCriterion("vbat in", values, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatNotIn(List<String> values) {
            addCriterion("vbat not in", values, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatBetween(String value1, String value2) {
            addCriterion("vbat between", value1, value2, "vbat");
            return (Criteria) this;
        }

        public Criteria andVbatNotBetween(String value1, String value2) {
            addCriterion("vbat not between", value1, value2, "vbat");
            return (Criteria) this;
        }

        public Criteria andCsqIsNull() {
            addCriterion("csq is null");
            return (Criteria) this;
        }

        public Criteria andCsqIsNotNull() {
            addCriterion("csq is not null");
            return (Criteria) this;
        }

        public Criteria andCsqEqualTo(String value) {
            addCriterion("csq =", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotEqualTo(String value) {
            addCriterion("csq <>", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqGreaterThan(String value) {
            addCriterion("csq >", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqGreaterThanOrEqualTo(String value) {
            addCriterion("csq >=", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLessThan(String value) {
            addCriterion("csq <", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLessThanOrEqualTo(String value) {
            addCriterion("csq <=", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqLike(String value) {
            addCriterion("csq like", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotLike(String value) {
            addCriterion("csq not like", value, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqIn(List<String> values) {
            addCriterion("csq in", values, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotIn(List<String> values) {
            addCriterion("csq not in", values, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqBetween(String value1, String value2) {
            addCriterion("csq between", value1, value2, "csq");
            return (Criteria) this;
        }

        public Criteria andCsqNotBetween(String value1, String value2) {
            addCriterion("csq not between", value1, value2, "csq");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNull() {
            addCriterion("shop_code is null");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNotNull() {
            addCriterion("shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopCodeEqualTo(String value) {
            addCriterion("shop_code =", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotEqualTo(String value) {
            addCriterion("shop_code <>", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThan(String value) {
            addCriterion("shop_code >", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_code >=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThan(String value) {
            addCriterion("shop_code <", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_code <=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLike(String value) {
            addCriterion("shop_code like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotLike(String value) {
            addCriterion("shop_code not like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeIn(List<String> values) {
            addCriterion("shop_code in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotIn(List<String> values) {
            addCriterion("shop_code not in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeBetween(String value1, String value2) {
            addCriterion("shop_code between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotBetween(String value1, String value2) {
            addCriterion("shop_code not between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andUpLngIsNull() {
            addCriterion("up_lng is null");
            return (Criteria) this;
        }

        public Criteria andUpLngIsNotNull() {
            addCriterion("up_lng is not null");
            return (Criteria) this;
        }

        public Criteria andUpLngEqualTo(BigDecimal value) {
            addCriterion("up_lng =", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngNotEqualTo(BigDecimal value) {
            addCriterion("up_lng <>", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngGreaterThan(BigDecimal value) {
            addCriterion("up_lng >", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("up_lng >=", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngLessThan(BigDecimal value) {
            addCriterion("up_lng <", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("up_lng <=", value, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngIn(List<BigDecimal> values) {
            addCriterion("up_lng in", values, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngNotIn(List<BigDecimal> values) {
            addCriterion("up_lng not in", values, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("up_lng between", value1, value2, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("up_lng not between", value1, value2, "upLng");
            return (Criteria) this;
        }

        public Criteria andUpLatIsNull() {
            addCriterion("up_lat is null");
            return (Criteria) this;
        }

        public Criteria andUpLatIsNotNull() {
            addCriterion("up_lat is not null");
            return (Criteria) this;
        }

        public Criteria andUpLatEqualTo(BigDecimal value) {
            addCriterion("up_lat =", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatNotEqualTo(BigDecimal value) {
            addCriterion("up_lat <>", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatGreaterThan(BigDecimal value) {
            addCriterion("up_lat >", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("up_lat >=", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatLessThan(BigDecimal value) {
            addCriterion("up_lat <", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("up_lat <=", value, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatIn(List<BigDecimal> values) {
            addCriterion("up_lat in", values, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatNotIn(List<BigDecimal> values) {
            addCriterion("up_lat not in", values, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("up_lat between", value1, value2, "upLat");
            return (Criteria) this;
        }

        public Criteria andUpLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("up_lat not between", value1, value2, "upLat");
            return (Criteria) this;
        }

        public Criteria andOnlineStateIsNull() {
            addCriterion("online_state is null");
            return (Criteria) this;
        }

        public Criteria andOnlineStateIsNotNull() {
            addCriterion("online_state is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineStateEqualTo(Byte value) {
            addCriterion("online_state =", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateNotEqualTo(Byte value) {
            addCriterion("online_state <>", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateGreaterThan(Byte value) {
            addCriterion("online_state >", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("online_state >=", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateLessThan(Byte value) {
            addCriterion("online_state <", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateLessThanOrEqualTo(Byte value) {
            addCriterion("online_state <=", value, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateIn(List<Byte> values) {
            addCriterion("online_state in", values, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateNotIn(List<Byte> values) {
            addCriterion("online_state not in", values, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateBetween(Byte value1, Byte value2) {
            addCriterion("online_state between", value1, value2, "onlineState");
            return (Criteria) this;
        }

        public Criteria andOnlineStateNotBetween(Byte value1, Byte value2) {
            addCriterion("online_state not between", value1, value2, "onlineState");
            return (Criteria) this;
        }

        public Criteria andMaxCapIsNull() {
            addCriterion("max_cap is null");
            return (Criteria) this;
        }

        public Criteria andMaxCapIsNotNull() {
            addCriterion("max_cap is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCapEqualTo(Byte value) {
            addCriterion("max_cap =", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapNotEqualTo(Byte value) {
            addCriterion("max_cap <>", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapGreaterThan(Byte value) {
            addCriterion("max_cap >", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapGreaterThanOrEqualTo(Byte value) {
            addCriterion("max_cap >=", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapLessThan(Byte value) {
            addCriterion("max_cap <", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapLessThanOrEqualTo(Byte value) {
            addCriterion("max_cap <=", value, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapIn(List<Byte> values) {
            addCriterion("max_cap in", values, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapNotIn(List<Byte> values) {
            addCriterion("max_cap not in", values, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapBetween(Byte value1, Byte value2) {
            addCriterion("max_cap between", value1, value2, "maxCap");
            return (Criteria) this;
        }

        public Criteria andMaxCapNotBetween(Byte value1, Byte value2) {
            addCriterion("max_cap not between", value1, value2, "maxCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapIsNull() {
            addCriterion("property_cap is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCapIsNotNull() {
            addCriterion("property_cap is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCapEqualTo(Byte value) {
            addCriterion("property_cap =", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapNotEqualTo(Byte value) {
            addCriterion("property_cap <>", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapGreaterThan(Byte value) {
            addCriterion("property_cap >", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapGreaterThanOrEqualTo(Byte value) {
            addCriterion("property_cap >=", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapLessThan(Byte value) {
            addCriterion("property_cap <", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapLessThanOrEqualTo(Byte value) {
            addCriterion("property_cap <=", value, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapIn(List<Byte> values) {
            addCriterion("property_cap in", values, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapNotIn(List<Byte> values) {
            addCriterion("property_cap not in", values, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapBetween(Byte value1, Byte value2) {
            addCriterion("property_cap between", value1, value2, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andPropertyCapNotBetween(Byte value1, Byte value2) {
            addCriterion("property_cap not between", value1, value2, "propertyCap");
            return (Criteria) this;
        }

        public Criteria andTransStateIsNull() {
            addCriterion("trans_state is null");
            return (Criteria) this;
        }

        public Criteria andTransStateIsNotNull() {
            addCriterion("trans_state is not null");
            return (Criteria) this;
        }

        public Criteria andTransStateEqualTo(Byte value) {
            addCriterion("trans_state =", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateNotEqualTo(Byte value) {
            addCriterion("trans_state <>", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateGreaterThan(Byte value) {
            addCriterion("trans_state >", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("trans_state >=", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateLessThan(Byte value) {
            addCriterion("trans_state <", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateLessThanOrEqualTo(Byte value) {
            addCriterion("trans_state <=", value, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateIn(List<Byte> values) {
            addCriterion("trans_state in", values, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateNotIn(List<Byte> values) {
            addCriterion("trans_state not in", values, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateBetween(Byte value1, Byte value2) {
            addCriterion("trans_state between", value1, value2, "transState");
            return (Criteria) this;
        }

        public Criteria andTransStateNotBetween(Byte value1, Byte value2) {
            addCriterion("trans_state not between", value1, value2, "transState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateIsNull() {
            addCriterion("occupy_state is null");
            return (Criteria) this;
        }

        public Criteria andOccupyStateIsNotNull() {
            addCriterion("occupy_state is not null");
            return (Criteria) this;
        }

        public Criteria andOccupyStateEqualTo(Byte value) {
            addCriterion("occupy_state =", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateNotEqualTo(Byte value) {
            addCriterion("occupy_state <>", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateGreaterThan(Byte value) {
            addCriterion("occupy_state >", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("occupy_state >=", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateLessThan(Byte value) {
            addCriterion("occupy_state <", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateLessThanOrEqualTo(Byte value) {
            addCriterion("occupy_state <=", value, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateIn(List<Byte> values) {
            addCriterion("occupy_state in", values, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateNotIn(List<Byte> values) {
            addCriterion("occupy_state not in", values, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateBetween(Byte value1, Byte value2) {
            addCriterion("occupy_state between", value1, value2, "occupyState");
            return (Criteria) this;
        }

        public Criteria andOccupyStateNotBetween(Byte value1, Byte value2) {
            addCriterion("occupy_state not between", value1, value2, "occupyState");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andHardVerIsNull() {
            addCriterion("hard_ver is null");
            return (Criteria) this;
        }

        public Criteria andHardVerIsNotNull() {
            addCriterion("hard_ver is not null");
            return (Criteria) this;
        }

        public Criteria andHardVerEqualTo(String value) {
            addCriterion("hard_ver =", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerNotEqualTo(String value) {
            addCriterion("hard_ver <>", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerGreaterThan(String value) {
            addCriterion("hard_ver >", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerGreaterThanOrEqualTo(String value) {
            addCriterion("hard_ver >=", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerLessThan(String value) {
            addCriterion("hard_ver <", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerLessThanOrEqualTo(String value) {
            addCriterion("hard_ver <=", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerLike(String value) {
            addCriterion("hard_ver like", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerNotLike(String value) {
            addCriterion("hard_ver not like", value, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerIn(List<String> values) {
            addCriterion("hard_ver in", values, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerNotIn(List<String> values) {
            addCriterion("hard_ver not in", values, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerBetween(String value1, String value2) {
            addCriterion("hard_ver between", value1, value2, "hardVer");
            return (Criteria) this;
        }

        public Criteria andHardVerNotBetween(String value1, String value2) {
            addCriterion("hard_ver not between", value1, value2, "hardVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerIsNull() {
            addCriterion("soft_ver is null");
            return (Criteria) this;
        }

        public Criteria andSoftVerIsNotNull() {
            addCriterion("soft_ver is not null");
            return (Criteria) this;
        }

        public Criteria andSoftVerEqualTo(String value) {
            addCriterion("soft_ver =", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotEqualTo(String value) {
            addCriterion("soft_ver <>", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerGreaterThan(String value) {
            addCriterion("soft_ver >", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerGreaterThanOrEqualTo(String value) {
            addCriterion("soft_ver >=", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLessThan(String value) {
            addCriterion("soft_ver <", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLessThanOrEqualTo(String value) {
            addCriterion("soft_ver <=", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerLike(String value) {
            addCriterion("soft_ver like", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotLike(String value) {
            addCriterion("soft_ver not like", value, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerIn(List<String> values) {
            addCriterion("soft_ver in", values, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotIn(List<String> values) {
            addCriterion("soft_ver not in", values, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerBetween(String value1, String value2) {
            addCriterion("soft_ver between", value1, value2, "softVer");
            return (Criteria) this;
        }

        public Criteria andSoftVerNotBetween(String value1, String value2) {
            addCriterion("soft_ver not between", value1, value2, "softVer");
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

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeIsNull() {
            addCriterion("locate_time is null");
            return (Criteria) this;
        }

        public Criteria andLocateTimeIsNotNull() {
            addCriterion("locate_time is not null");
            return (Criteria) this;
        }

        public Criteria andLocateTimeEqualTo(Date value) {
            addCriterion("locate_time =", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeNotEqualTo(Date value) {
            addCriterion("locate_time <>", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeGreaterThan(Date value) {
            addCriterion("locate_time >", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("locate_time >=", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeLessThan(Date value) {
            addCriterion("locate_time <", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeLessThanOrEqualTo(Date value) {
            addCriterion("locate_time <=", value, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeIn(List<Date> values) {
            addCriterion("locate_time in", values, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeNotIn(List<Date> values) {
            addCriterion("locate_time not in", values, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeBetween(Date value1, Date value2) {
            addCriterion("locate_time between", value1, value2, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLocateTimeNotBetween(Date value1, Date value2) {
            addCriterion("locate_time not between", value1, value2, "locateTime");
            return (Criteria) this;
        }

        public Criteria andLostRecordIsNull() {
            addCriterion("lost_record is null");
            return (Criteria) this;
        }

        public Criteria andLostRecordIsNotNull() {
            addCriterion("lost_record is not null");
            return (Criteria) this;
        }

        public Criteria andLostRecordEqualTo(Integer value) {
            addCriterion("lost_record =", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordNotEqualTo(Integer value) {
            addCriterion("lost_record <>", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordGreaterThan(Integer value) {
            addCriterion("lost_record >", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("lost_record >=", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordLessThan(Integer value) {
            addCriterion("lost_record <", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordLessThanOrEqualTo(Integer value) {
            addCriterion("lost_record <=", value, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordIn(List<Integer> values) {
            addCriterion("lost_record in", values, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordNotIn(List<Integer> values) {
            addCriterion("lost_record not in", values, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordBetween(Integer value1, Integer value2) {
            addCriterion("lost_record between", value1, value2, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andLostRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("lost_record not between", value1, value2, "lostRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordIsNull() {
            addCriterion("obtain_record is null");
            return (Criteria) this;
        }

        public Criteria andObtainRecordIsNotNull() {
            addCriterion("obtain_record is not null");
            return (Criteria) this;
        }

        public Criteria andObtainRecordEqualTo(Integer value) {
            addCriterion("obtain_record =", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordNotEqualTo(Integer value) {
            addCriterion("obtain_record <>", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordGreaterThan(Integer value) {
            addCriterion("obtain_record >", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("obtain_record >=", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordLessThan(Integer value) {
            addCriterion("obtain_record <", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordLessThanOrEqualTo(Integer value) {
            addCriterion("obtain_record <=", value, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordIn(List<Integer> values) {
            addCriterion("obtain_record in", values, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordNotIn(List<Integer> values) {
            addCriterion("obtain_record not in", values, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordBetween(Integer value1, Integer value2) {
            addCriterion("obtain_record between", value1, value2, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andObtainRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("obtain_record not between", value1, value2, "obtainRecord");
            return (Criteria) this;
        }

        public Criteria andSceneIsNull() {
            addCriterion("scene is null");
            return (Criteria) this;
        }

        public Criteria andSceneIsNotNull() {
            addCriterion("scene is not null");
            return (Criteria) this;
        }

        public Criteria andSceneEqualTo(String value) {
            addCriterion("scene =", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotEqualTo(String value) {
            addCriterion("scene <>", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThan(String value) {
            addCriterion("scene >", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThanOrEqualTo(String value) {
            addCriterion("scene >=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThan(String value) {
            addCriterion("scene <", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThanOrEqualTo(String value) {
            addCriterion("scene <=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLike(String value) {
            addCriterion("scene like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotLike(String value) {
            addCriterion("scene not like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneIn(List<String> values) {
            addCriterion("scene in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotIn(List<String> values) {
            addCriterion("scene not in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneBetween(String value1, String value2) {
            addCriterion("scene between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotBetween(String value1, String value2) {
            addCriterion("scene not between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andPutDateIsNull() {
            addCriterion("put_date is null");
            return (Criteria) this;
        }

        public Criteria andPutDateIsNotNull() {
            addCriterion("put_date is not null");
            return (Criteria) this;
        }

        public Criteria andPutDateEqualTo(Date value) {
            addCriterion("put_date =", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateNotEqualTo(Date value) {
            addCriterion("put_date <>", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateGreaterThan(Date value) {
            addCriterion("put_date >", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateGreaterThanOrEqualTo(Date value) {
            addCriterion("put_date >=", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateLessThan(Date value) {
            addCriterion("put_date <", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateLessThanOrEqualTo(Date value) {
            addCriterion("put_date <=", value, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateIn(List<Date> values) {
            addCriterion("put_date in", values, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateNotIn(List<Date> values) {
            addCriterion("put_date not in", values, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateBetween(Date value1, Date value2) {
            addCriterion("put_date between", value1, value2, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutDateNotBetween(Date value1, Date value2) {
            addCriterion("put_date not between", value1, value2, "putDate");
            return (Criteria) this;
        }

        public Criteria andPutPicIsNull() {
            addCriterion("put_pic is null");
            return (Criteria) this;
        }

        public Criteria andPutPicIsNotNull() {
            addCriterion("put_pic is not null");
            return (Criteria) this;
        }

        public Criteria andPutPicEqualTo(String value) {
            addCriterion("put_pic =", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicNotEqualTo(String value) {
            addCriterion("put_pic <>", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicGreaterThan(String value) {
            addCriterion("put_pic >", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicGreaterThanOrEqualTo(String value) {
            addCriterion("put_pic >=", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicLessThan(String value) {
            addCriterion("put_pic <", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicLessThanOrEqualTo(String value) {
            addCriterion("put_pic <=", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicLike(String value) {
            addCriterion("put_pic like", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicNotLike(String value) {
            addCriterion("put_pic not like", value, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicIn(List<String> values) {
            addCriterion("put_pic in", values, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicNotIn(List<String> values) {
            addCriterion("put_pic not in", values, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicBetween(String value1, String value2) {
            addCriterion("put_pic between", value1, value2, "putPic");
            return (Criteria) this;
        }

        public Criteria andPutPicNotBetween(String value1, String value2) {
            addCriterion("put_pic not between", value1, value2, "putPic");
            return (Criteria) this;
        }

        public Criteria andMigrateDateIsNull() {
            addCriterion("migrate_date is null");
            return (Criteria) this;
        }

        public Criteria andMigrateDateIsNotNull() {
            addCriterion("migrate_date is not null");
            return (Criteria) this;
        }

        public Criteria andMigrateDateEqualTo(Date value) {
            addCriterion("migrate_date =", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateNotEqualTo(Date value) {
            addCriterion("migrate_date <>", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateGreaterThan(Date value) {
            addCriterion("migrate_date >", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("migrate_date >=", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateLessThan(Date value) {
            addCriterion("migrate_date <", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateLessThanOrEqualTo(Date value) {
            addCriterion("migrate_date <=", value, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateIn(List<Date> values) {
            addCriterion("migrate_date in", values, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateNotIn(List<Date> values) {
            addCriterion("migrate_date not in", values, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateBetween(Date value1, Date value2) {
            addCriterion("migrate_date between", value1, value2, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andMigrateDateNotBetween(Date value1, Date value2) {
            addCriterion("migrate_date not between", value1, value2, "migrateDate");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceIsNull() {
            addCriterion("position_accordance is null");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceIsNotNull() {
            addCriterion("position_accordance is not null");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceEqualTo(Byte value) {
            addCriterion("position_accordance =", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceNotEqualTo(Byte value) {
            addCriterion("position_accordance <>", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceGreaterThan(Byte value) {
            addCriterion("position_accordance >", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceGreaterThanOrEqualTo(Byte value) {
            addCriterion("position_accordance >=", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceLessThan(Byte value) {
            addCriterion("position_accordance <", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceLessThanOrEqualTo(Byte value) {
            addCriterion("position_accordance <=", value, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceIn(List<Byte> values) {
            addCriterion("position_accordance in", values, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceNotIn(List<Byte> values) {
            addCriterion("position_accordance not in", values, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceBetween(Byte value1, Byte value2) {
            addCriterion("position_accordance between", value1, value2, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andPositionAccordanceNotBetween(Byte value1, Byte value2) {
            addCriterion("position_accordance not between", value1, value2, "positionAccordance");
            return (Criteria) this;
        }

        public Criteria andCommentMessageIsNull() {
            addCriterion("comment_message is null");
            return (Criteria) this;
        }

        public Criteria andCommentMessageIsNotNull() {
            addCriterion("comment_message is not null");
            return (Criteria) this;
        }

        public Criteria andCommentMessageEqualTo(String value) {
            addCriterion("comment_message =", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageNotEqualTo(String value) {
            addCriterion("comment_message <>", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageGreaterThan(String value) {
            addCriterion("comment_message >", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageGreaterThanOrEqualTo(String value) {
            addCriterion("comment_message >=", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageLessThan(String value) {
            addCriterion("comment_message <", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageLessThanOrEqualTo(String value) {
            addCriterion("comment_message <=", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageLike(String value) {
            addCriterion("comment_message like", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageNotLike(String value) {
            addCriterion("comment_message not like", value, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageIn(List<String> values) {
            addCriterion("comment_message in", values, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageNotIn(List<String> values) {
            addCriterion("comment_message not in", values, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageBetween(String value1, String value2) {
            addCriterion("comment_message between", value1, value2, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCommentMessageNotBetween(String value1, String value2) {
            addCriterion("comment_message not between", value1, value2, "commentMessage");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

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