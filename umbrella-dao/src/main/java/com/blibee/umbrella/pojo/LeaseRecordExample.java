package com.blibee.umbrella.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaseRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LeaseRecordExample() {
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

        public Criteria andLeaseNumberIsNull() {
            addCriterion("lease_number is null");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberIsNotNull() {
            addCriterion("lease_number is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberEqualTo(Long value) {
            addCriterion("lease_number =", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberNotEqualTo(Long value) {
            addCriterion("lease_number <>", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberGreaterThan(Long value) {
            addCriterion("lease_number >", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("lease_number >=", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberLessThan(Long value) {
            addCriterion("lease_number <", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberLessThanOrEqualTo(Long value) {
            addCriterion("lease_number <=", value, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberIn(List<Long> values) {
            addCriterion("lease_number in", values, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberNotIn(List<Long> values) {
            addCriterion("lease_number not in", values, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberBetween(Long value1, Long value2) {
            addCriterion("lease_number between", value1, value2, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andLeaseNumberNotBetween(Long value1, Long value2) {
            addCriterion("lease_number not between", value1, value2, "leaseNumber");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNull() {
            addCriterion("coupon_amount is null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNotNull() {
            addCriterion("coupon_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountEqualTo(BigDecimal value) {
            addCriterion("coupon_amount =", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotEqualTo(BigDecimal value) {
            addCriterion("coupon_amount <>", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThan(BigDecimal value) {
            addCriterion("coupon_amount >", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_amount >=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThan(BigDecimal value) {
            addCriterion("coupon_amount <", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_amount <=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIn(List<BigDecimal> values) {
            addCriterion("coupon_amount in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotIn(List<BigDecimal> values) {
            addCriterion("coupon_amount not in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_amount between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_amount not between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIsNull() {
            addCriterion("reduce_amount is null");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIsNotNull() {
            addCriterion("reduce_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReduceAmountEqualTo(BigDecimal value) {
            addCriterion("reduce_amount =", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotEqualTo(BigDecimal value) {
            addCriterion("reduce_amount <>", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountGreaterThan(BigDecimal value) {
            addCriterion("reduce_amount >", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reduce_amount >=", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountLessThan(BigDecimal value) {
            addCriterion("reduce_amount <", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reduce_amount <=", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIn(List<BigDecimal> values) {
            addCriterion("reduce_amount in", values, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotIn(List<BigDecimal> values) {
            addCriterion("reduce_amount not in", values, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reduce_amount between", value1, value2, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reduce_amount not between", value1, value2, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberIsNull() {
            addCriterion("umbrella_number is null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberIsNotNull() {
            addCriterion("umbrella_number is not null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberEqualTo(String value) {
            addCriterion("umbrella_number =", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberNotEqualTo(String value) {
            addCriterion("umbrella_number <>", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberGreaterThan(String value) {
            addCriterion("umbrella_number >", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberGreaterThanOrEqualTo(String value) {
            addCriterion("umbrella_number >=", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberLessThan(String value) {
            addCriterion("umbrella_number <", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberLessThanOrEqualTo(String value) {
            addCriterion("umbrella_number <=", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberLike(String value) {
            addCriterion("umbrella_number like", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberNotLike(String value) {
            addCriterion("umbrella_number not like", value, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberIn(List<String> values) {
            addCriterion("umbrella_number in", values, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberNotIn(List<String> values) {
            addCriterion("umbrella_number not in", values, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberBetween(String value1, String value2) {
            addCriterion("umbrella_number between", value1, value2, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andUmbrellaNumberNotBetween(String value1, String value2) {
            addCriterion("umbrella_number not between", value1, value2, "umbrellaNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberIsNull() {
            addCriterion("cabinet_lend_number is null");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberIsNotNull() {
            addCriterion("cabinet_lend_number is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberEqualTo(String value) {
            addCriterion("cabinet_lend_number =", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberNotEqualTo(String value) {
            addCriterion("cabinet_lend_number <>", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberGreaterThan(String value) {
            addCriterion("cabinet_lend_number >", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberGreaterThanOrEqualTo(String value) {
            addCriterion("cabinet_lend_number >=", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberLessThan(String value) {
            addCriterion("cabinet_lend_number <", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberLessThanOrEqualTo(String value) {
            addCriterion("cabinet_lend_number <=", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberLike(String value) {
            addCriterion("cabinet_lend_number like", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberNotLike(String value) {
            addCriterion("cabinet_lend_number not like", value, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberIn(List<String> values) {
            addCriterion("cabinet_lend_number in", values, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberNotIn(List<String> values) {
            addCriterion("cabinet_lend_number not in", values, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberBetween(String value1, String value2) {
            addCriterion("cabinet_lend_number between", value1, value2, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetLendNumberNotBetween(String value1, String value2) {
            addCriterion("cabinet_lend_number not between", value1, value2, "cabinetLendNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberIsNull() {
            addCriterion("cabinet_back_number is null");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberIsNotNull() {
            addCriterion("cabinet_back_number is not null");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberEqualTo(String value) {
            addCriterion("cabinet_back_number =", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberNotEqualTo(String value) {
            addCriterion("cabinet_back_number <>", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberGreaterThan(String value) {
            addCriterion("cabinet_back_number >", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberGreaterThanOrEqualTo(String value) {
            addCriterion("cabinet_back_number >=", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberLessThan(String value) {
            addCriterion("cabinet_back_number <", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberLessThanOrEqualTo(String value) {
            addCriterion("cabinet_back_number <=", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberLike(String value) {
            addCriterion("cabinet_back_number like", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberNotLike(String value) {
            addCriterion("cabinet_back_number not like", value, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberIn(List<String> values) {
            addCriterion("cabinet_back_number in", values, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberNotIn(List<String> values) {
            addCriterion("cabinet_back_number not in", values, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberBetween(String value1, String value2) {
            addCriterion("cabinet_back_number between", value1, value2, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andCabinetBackNumberNotBetween(String value1, String value2) {
            addCriterion("cabinet_back_number not between", value1, value2, "cabinetBackNumber");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeIsNull() {
            addCriterion("start_shop_code is null");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeIsNotNull() {
            addCriterion("start_shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeEqualTo(String value) {
            addCriterion("start_shop_code =", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeNotEqualTo(String value) {
            addCriterion("start_shop_code <>", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeGreaterThan(String value) {
            addCriterion("start_shop_code >", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("start_shop_code >=", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeLessThan(String value) {
            addCriterion("start_shop_code <", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeLessThanOrEqualTo(String value) {
            addCriterion("start_shop_code <=", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeLike(String value) {
            addCriterion("start_shop_code like", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeNotLike(String value) {
            addCriterion("start_shop_code not like", value, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeIn(List<String> values) {
            addCriterion("start_shop_code in", values, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeNotIn(List<String> values) {
            addCriterion("start_shop_code not in", values, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeBetween(String value1, String value2) {
            addCriterion("start_shop_code between", value1, value2, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopCodeNotBetween(String value1, String value2) {
            addCriterion("start_shop_code not between", value1, value2, "startShopCode");
            return (Criteria) this;
        }

        public Criteria andStartShopNameIsNull() {
            addCriterion("start_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andStartShopNameIsNotNull() {
            addCriterion("start_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andStartShopNameEqualTo(String value) {
            addCriterion("start_shop_name =", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameNotEqualTo(String value) {
            addCriterion("start_shop_name <>", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameGreaterThan(String value) {
            addCriterion("start_shop_name >", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_shop_name >=", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameLessThan(String value) {
            addCriterion("start_shop_name <", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameLessThanOrEqualTo(String value) {
            addCriterion("start_shop_name <=", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameLike(String value) {
            addCriterion("start_shop_name like", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameNotLike(String value) {
            addCriterion("start_shop_name not like", value, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameIn(List<String> values) {
            addCriterion("start_shop_name in", values, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameNotIn(List<String> values) {
            addCriterion("start_shop_name not in", values, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameBetween(String value1, String value2) {
            addCriterion("start_shop_name between", value1, value2, "startShopName");
            return (Criteria) this;
        }

        public Criteria andStartShopNameNotBetween(String value1, String value2) {
            addCriterion("start_shop_name not between", value1, value2, "startShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeIsNull() {
            addCriterion("end_shop_code is null");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeIsNotNull() {
            addCriterion("end_shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeEqualTo(String value) {
            addCriterion("end_shop_code =", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeNotEqualTo(String value) {
            addCriterion("end_shop_code <>", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeGreaterThan(String value) {
            addCriterion("end_shop_code >", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("end_shop_code >=", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeLessThan(String value) {
            addCriterion("end_shop_code <", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeLessThanOrEqualTo(String value) {
            addCriterion("end_shop_code <=", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeLike(String value) {
            addCriterion("end_shop_code like", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeNotLike(String value) {
            addCriterion("end_shop_code not like", value, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeIn(List<String> values) {
            addCriterion("end_shop_code in", values, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeNotIn(List<String> values) {
            addCriterion("end_shop_code not in", values, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeBetween(String value1, String value2) {
            addCriterion("end_shop_code between", value1, value2, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopCodeNotBetween(String value1, String value2) {
            addCriterion("end_shop_code not between", value1, value2, "endShopCode");
            return (Criteria) this;
        }

        public Criteria andEndShopNameIsNull() {
            addCriterion("end_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andEndShopNameIsNotNull() {
            addCriterion("end_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndShopNameEqualTo(String value) {
            addCriterion("end_shop_name =", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameNotEqualTo(String value) {
            addCriterion("end_shop_name <>", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameGreaterThan(String value) {
            addCriterion("end_shop_name >", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_shop_name >=", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameLessThan(String value) {
            addCriterion("end_shop_name <", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameLessThanOrEqualTo(String value) {
            addCriterion("end_shop_name <=", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameLike(String value) {
            addCriterion("end_shop_name like", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameNotLike(String value) {
            addCriterion("end_shop_name not like", value, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameIn(List<String> values) {
            addCriterion("end_shop_name in", values, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameNotIn(List<String> values) {
            addCriterion("end_shop_name not in", values, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameBetween(String value1, String value2) {
            addCriterion("end_shop_name between", value1, value2, "endShopName");
            return (Criteria) this;
        }

        public Criteria andEndShopNameNotBetween(String value1, String value2) {
            addCriterion("end_shop_name not between", value1, value2, "endShopName");
            return (Criteria) this;
        }

        public Criteria andLeaseStateIsNull() {
            addCriterion("lease_state is null");
            return (Criteria) this;
        }

        public Criteria andLeaseStateIsNotNull() {
            addCriterion("lease_state is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseStateEqualTo(Byte value) {
            addCriterion("lease_state =", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateNotEqualTo(Byte value) {
            addCriterion("lease_state <>", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateGreaterThan(Byte value) {
            addCriterion("lease_state >", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("lease_state >=", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateLessThan(Byte value) {
            addCriterion("lease_state <", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateLessThanOrEqualTo(Byte value) {
            addCriterion("lease_state <=", value, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateIn(List<Byte> values) {
            addCriterion("lease_state in", values, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateNotIn(List<Byte> values) {
            addCriterion("lease_state not in", values, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateBetween(Byte value1, Byte value2) {
            addCriterion("lease_state between", value1, value2, "leaseState");
            return (Criteria) this;
        }

        public Criteria andLeaseStateNotBetween(Byte value1, Byte value2) {
            addCriterion("lease_state not between", value1, value2, "leaseState");
            return (Criteria) this;
        }

        public Criteria andTradeStateIsNull() {
            addCriterion("trade_state is null");
            return (Criteria) this;
        }

        public Criteria andTradeStateIsNotNull() {
            addCriterion("trade_state is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStateEqualTo(Byte value) {
            addCriterion("trade_state =", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotEqualTo(Byte value) {
            addCriterion("trade_state <>", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateGreaterThan(Byte value) {
            addCriterion("trade_state >", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("trade_state >=", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateLessThan(Byte value) {
            addCriterion("trade_state <", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateLessThanOrEqualTo(Byte value) {
            addCriterion("trade_state <=", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateIn(List<Byte> values) {
            addCriterion("trade_state in", values, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotIn(List<Byte> values) {
            addCriterion("trade_state not in", values, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateBetween(Byte value1, Byte value2) {
            addCriterion("trade_state between", value1, value2, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotBetween(Byte value1, Byte value2) {
            addCriterion("trade_state not between", value1, value2, "tradeState");
            return (Criteria) this;
        }

        public Criteria andFreeDepositIsNull() {
            addCriterion("free_deposit is null");
            return (Criteria) this;
        }

        public Criteria andFreeDepositIsNotNull() {
            addCriterion("free_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andFreeDepositEqualTo(Byte value) {
            addCriterion("free_deposit =", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositNotEqualTo(Byte value) {
            addCriterion("free_deposit <>", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositGreaterThan(Byte value) {
            addCriterion("free_deposit >", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositGreaterThanOrEqualTo(Byte value) {
            addCriterion("free_deposit >=", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositLessThan(Byte value) {
            addCriterion("free_deposit <", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositLessThanOrEqualTo(Byte value) {
            addCriterion("free_deposit <=", value, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositIn(List<Byte> values) {
            addCriterion("free_deposit in", values, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositNotIn(List<Byte> values) {
            addCriterion("free_deposit not in", values, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositBetween(Byte value1, Byte value2) {
            addCriterion("free_deposit between", value1, value2, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andFreeDepositNotBetween(Byte value1, Byte value2) {
            addCriterion("free_deposit not between", value1, value2, "freeDeposit");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeIsNull() {
            addCriterion("lease_node is null");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeIsNotNull() {
            addCriterion("lease_node is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeEqualTo(String value) {
            addCriterion("lease_node =", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeNotEqualTo(String value) {
            addCriterion("lease_node <>", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeGreaterThan(String value) {
            addCriterion("lease_node >", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeGreaterThanOrEqualTo(String value) {
            addCriterion("lease_node >=", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeLessThan(String value) {
            addCriterion("lease_node <", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeLessThanOrEqualTo(String value) {
            addCriterion("lease_node <=", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeLike(String value) {
            addCriterion("lease_node like", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeNotLike(String value) {
            addCriterion("lease_node not like", value, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeIn(List<String> values) {
            addCriterion("lease_node in", values, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeNotIn(List<String> values) {
            addCriterion("lease_node not in", values, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeBetween(String value1, String value2) {
            addCriterion("lease_node between", value1, value2, "leaseNode");
            return (Criteria) this;
        }

        public Criteria andLeaseNodeNotBetween(String value1, String value2) {
            addCriterion("lease_node not between", value1, value2, "leaseNode");
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

        public Criteria andLeaseResourceIsNull() {
            addCriterion("lease_resource is null");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceIsNotNull() {
            addCriterion("lease_resource is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceEqualTo(String value) {
            addCriterion("lease_resource =", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceNotEqualTo(String value) {
            addCriterion("lease_resource <>", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceGreaterThan(String value) {
            addCriterion("lease_resource >", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceGreaterThanOrEqualTo(String value) {
            addCriterion("lease_resource >=", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceLessThan(String value) {
            addCriterion("lease_resource <", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceLessThanOrEqualTo(String value) {
            addCriterion("lease_resource <=", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceLike(String value) {
            addCriterion("lease_resource like", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceNotLike(String value) {
            addCriterion("lease_resource not like", value, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceIn(List<String> values) {
            addCriterion("lease_resource in", values, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceNotIn(List<String> values) {
            addCriterion("lease_resource not in", values, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceBetween(String value1, String value2) {
            addCriterion("lease_resource between", value1, value2, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andLeaseResourceNotBetween(String value1, String value2) {
            addCriterion("lease_resource not between", value1, value2, "leaseResource");
            return (Criteria) this;
        }

        public Criteria andEntranceIsNull() {
            addCriterion("entrance is null");
            return (Criteria) this;
        }

        public Criteria andEntranceIsNotNull() {
            addCriterion("entrance is not null");
            return (Criteria) this;
        }

        public Criteria andEntranceEqualTo(String value) {
            addCriterion("entrance =", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotEqualTo(String value) {
            addCriterion("entrance <>", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceGreaterThan(String value) {
            addCriterion("entrance >", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceGreaterThanOrEqualTo(String value) {
            addCriterion("entrance >=", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLessThan(String value) {
            addCriterion("entrance <", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLessThanOrEqualTo(String value) {
            addCriterion("entrance <=", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLike(String value) {
            addCriterion("entrance like", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotLike(String value) {
            addCriterion("entrance not like", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceIn(List<String> values) {
            addCriterion("entrance in", values, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotIn(List<String> values) {
            addCriterion("entrance not in", values, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceBetween(String value1, String value2) {
            addCriterion("entrance between", value1, value2, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotBetween(String value1, String value2) {
            addCriterion("entrance not between", value1, value2, "entrance");
            return (Criteria) this;
        }

        public Criteria andUserResourceIsNull() {
            addCriterion("user_resource is null");
            return (Criteria) this;
        }

        public Criteria andUserResourceIsNotNull() {
            addCriterion("user_resource is not null");
            return (Criteria) this;
        }

        public Criteria andUserResourceEqualTo(Byte value) {
            addCriterion("user_resource =", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceNotEqualTo(Byte value) {
            addCriterion("user_resource <>", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceGreaterThan(Byte value) {
            addCriterion("user_resource >", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_resource >=", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceLessThan(Byte value) {
            addCriterion("user_resource <", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceLessThanOrEqualTo(Byte value) {
            addCriterion("user_resource <=", value, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceIn(List<Byte> values) {
            addCriterion("user_resource in", values, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceNotIn(List<Byte> values) {
            addCriterion("user_resource not in", values, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceBetween(Byte value1, Byte value2) {
            addCriterion("user_resource between", value1, value2, "userResource");
            return (Criteria) this;
        }

        public Criteria andUserResourceNotBetween(Byte value1, Byte value2) {
            addCriterion("user_resource not between", value1, value2, "userResource");
            return (Criteria) this;
        }

        public Criteria andStartCityIsNull() {
            addCriterion("start_city is null");
            return (Criteria) this;
        }

        public Criteria andStartCityIsNotNull() {
            addCriterion("start_city is not null");
            return (Criteria) this;
        }

        public Criteria andStartCityEqualTo(String value) {
            addCriterion("start_city =", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityNotEqualTo(String value) {
            addCriterion("start_city <>", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityGreaterThan(String value) {
            addCriterion("start_city >", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityGreaterThanOrEqualTo(String value) {
            addCriterion("start_city >=", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityLessThan(String value) {
            addCriterion("start_city <", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityLessThanOrEqualTo(String value) {
            addCriterion("start_city <=", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityLike(String value) {
            addCriterion("start_city like", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityNotLike(String value) {
            addCriterion("start_city not like", value, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityIn(List<String> values) {
            addCriterion("start_city in", values, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityNotIn(List<String> values) {
            addCriterion("start_city not in", values, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityBetween(String value1, String value2) {
            addCriterion("start_city between", value1, value2, "startCity");
            return (Criteria) this;
        }

        public Criteria andStartCityNotBetween(String value1, String value2) {
            addCriterion("start_city not between", value1, value2, "startCity");
            return (Criteria) this;
        }

        public Criteria andEndCityIsNull() {
            addCriterion("end_city is null");
            return (Criteria) this;
        }

        public Criteria andEndCityIsNotNull() {
            addCriterion("end_city is not null");
            return (Criteria) this;
        }

        public Criteria andEndCityEqualTo(String value) {
            addCriterion("end_city =", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityNotEqualTo(String value) {
            addCriterion("end_city <>", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityGreaterThan(String value) {
            addCriterion("end_city >", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityGreaterThanOrEqualTo(String value) {
            addCriterion("end_city >=", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityLessThan(String value) {
            addCriterion("end_city <", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityLessThanOrEqualTo(String value) {
            addCriterion("end_city <=", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityLike(String value) {
            addCriterion("end_city like", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityNotLike(String value) {
            addCriterion("end_city not like", value, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityIn(List<String> values) {
            addCriterion("end_city in", values, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityNotIn(List<String> values) {
            addCriterion("end_city not in", values, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityBetween(String value1, String value2) {
            addCriterion("end_city between", value1, value2, "endCity");
            return (Criteria) this;
        }

        public Criteria andEndCityNotBetween(String value1, String value2) {
            addCriterion("end_city not between", value1, value2, "endCity");
            return (Criteria) this;
        }

        public Criteria andStartSceneIsNull() {
            addCriterion("start_scene is null");
            return (Criteria) this;
        }

        public Criteria andStartSceneIsNotNull() {
            addCriterion("start_scene is not null");
            return (Criteria) this;
        }

        public Criteria andStartSceneEqualTo(String value) {
            addCriterion("start_scene =", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneNotEqualTo(String value) {
            addCriterion("start_scene <>", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneGreaterThan(String value) {
            addCriterion("start_scene >", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneGreaterThanOrEqualTo(String value) {
            addCriterion("start_scene >=", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneLessThan(String value) {
            addCriterion("start_scene <", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneLessThanOrEqualTo(String value) {
            addCriterion("start_scene <=", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneLike(String value) {
            addCriterion("start_scene like", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneNotLike(String value) {
            addCriterion("start_scene not like", value, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneIn(List<String> values) {
            addCriterion("start_scene in", values, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneNotIn(List<String> values) {
            addCriterion("start_scene not in", values, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneBetween(String value1, String value2) {
            addCriterion("start_scene between", value1, value2, "startScene");
            return (Criteria) this;
        }

        public Criteria andStartSceneNotBetween(String value1, String value2) {
            addCriterion("start_scene not between", value1, value2, "startScene");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountIsNull() {
            addCriterion("archived_amount is null");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountIsNotNull() {
            addCriterion("archived_amount is not null");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountEqualTo(BigDecimal value) {
            addCriterion("archived_amount =", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountNotEqualTo(BigDecimal value) {
            addCriterion("archived_amount <>", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountGreaterThan(BigDecimal value) {
            addCriterion("archived_amount >", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("archived_amount >=", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountLessThan(BigDecimal value) {
            addCriterion("archived_amount <", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("archived_amount <=", value, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountIn(List<BigDecimal> values) {
            addCriterion("archived_amount in", values, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountNotIn(List<BigDecimal> values) {
            addCriterion("archived_amount not in", values, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("archived_amount between", value1, value2, "archivedAmount");
            return (Criteria) this;
        }

        public Criteria andArchivedAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("archived_amount not between", value1, value2, "archivedAmount");
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

        public Criteria andPayMethodIsNull() {
            addCriterion("pay_method is null");
            return (Criteria) this;
        }

        public Criteria andPayMethodIsNotNull() {
            addCriterion("pay_method is not null");
            return (Criteria) this;
        }

        public Criteria andPayMethodEqualTo(String value) {
            addCriterion("pay_method =", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotEqualTo(String value) {
            addCriterion("pay_method <>", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThan(String value) {
            addCriterion("pay_method >", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodGreaterThanOrEqualTo(String value) {
            addCriterion("pay_method >=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThan(String value) {
            addCriterion("pay_method <", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLessThanOrEqualTo(String value) {
            addCriterion("pay_method <=", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodLike(String value) {
            addCriterion("pay_method like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotLike(String value) {
            addCriterion("pay_method not like", value, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodIn(List<String> values) {
            addCriterion("pay_method in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotIn(List<String> values) {
            addCriterion("pay_method not in", values, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodBetween(String value1, String value2) {
            addCriterion("pay_method between", value1, value2, "payMethod");
            return (Criteria) this;
        }

        public Criteria andPayMethodNotBetween(String value1, String value2) {
            addCriterion("pay_method not between", value1, value2, "payMethod");
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