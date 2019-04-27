package com.blibee.umbrella.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedbackExample() {
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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

        public Criteria andLeaseIdIsNull() {
            addCriterion("lease_id is null");
            return (Criteria) this;
        }

        public Criteria andLeaseIdIsNotNull() {
            addCriterion("lease_id is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseIdEqualTo(Long value) {
            addCriterion("lease_id =", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdNotEqualTo(Long value) {
            addCriterion("lease_id <>", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdGreaterThan(Long value) {
            addCriterion("lease_id >", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("lease_id >=", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdLessThan(Long value) {
            addCriterion("lease_id <", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdLessThanOrEqualTo(Long value) {
            addCriterion("lease_id <=", value, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdIn(List<Long> values) {
            addCriterion("lease_id in", values, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdNotIn(List<Long> values) {
            addCriterion("lease_id not in", values, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdBetween(Long value1, Long value2) {
            addCriterion("lease_id between", value1, value2, "leaseId");
            return (Criteria) this;
        }

        public Criteria andLeaseIdNotBetween(Long value1, Long value2) {
            addCriterion("lease_id not between", value1, value2, "leaseId");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Byte value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Byte value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Byte value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Byte value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Byte value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Byte value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Byte> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Byte> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Byte value1, Byte value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Byte value1, Byte value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Byte value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Byte value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Byte value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Byte value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Byte value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Byte> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Byte> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Byte value1, Byte value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Byte value1, Byte value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeIsNull() {
            addCriterion("lease_type is null");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeIsNotNull() {
            addCriterion("lease_type is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeEqualTo(String value) {
            addCriterion("lease_type =", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeNotEqualTo(String value) {
            addCriterion("lease_type <>", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeGreaterThan(String value) {
            addCriterion("lease_type >", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("lease_type >=", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeLessThan(String value) {
            addCriterion("lease_type <", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeLessThanOrEqualTo(String value) {
            addCriterion("lease_type <=", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeLike(String value) {
            addCriterion("lease_type like", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeNotLike(String value) {
            addCriterion("lease_type not like", value, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeIn(List<String> values) {
            addCriterion("lease_type in", values, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeNotIn(List<String> values) {
            addCriterion("lease_type not in", values, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeBetween(String value1, String value2) {
            addCriterion("lease_type between", value1, value2, "leaseType");
            return (Criteria) this;
        }

        public Criteria andLeaseTypeNotBetween(String value1, String value2) {
            addCriterion("lease_type not between", value1, value2, "leaseType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeIsNull() {
            addCriterion("fault_type is null");
            return (Criteria) this;
        }

        public Criteria andFaultTypeIsNotNull() {
            addCriterion("fault_type is not null");
            return (Criteria) this;
        }

        public Criteria andFaultTypeEqualTo(String value) {
            addCriterion("fault_type =", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeNotEqualTo(String value) {
            addCriterion("fault_type <>", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeGreaterThan(String value) {
            addCriterion("fault_type >", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeGreaterThanOrEqualTo(String value) {
            addCriterion("fault_type >=", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeLessThan(String value) {
            addCriterion("fault_type <", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeLessThanOrEqualTo(String value) {
            addCriterion("fault_type <=", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeLike(String value) {
            addCriterion("fault_type like", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeNotLike(String value) {
            addCriterion("fault_type not like", value, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeIn(List<String> values) {
            addCriterion("fault_type in", values, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeNotIn(List<String> values) {
            addCriterion("fault_type not in", values, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeBetween(String value1, String value2) {
            addCriterion("fault_type between", value1, value2, "faultType");
            return (Criteria) this;
        }

        public Criteria andFaultTypeNotBetween(String value1, String value2) {
            addCriterion("fault_type not between", value1, value2, "faultType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeIsNull() {
            addCriterion("solve_type is null");
            return (Criteria) this;
        }

        public Criteria andSolveTypeIsNotNull() {
            addCriterion("solve_type is not null");
            return (Criteria) this;
        }

        public Criteria andSolveTypeEqualTo(Byte value) {
            addCriterion("solve_type =", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeNotEqualTo(Byte value) {
            addCriterion("solve_type <>", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeGreaterThan(Byte value) {
            addCriterion("solve_type >", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("solve_type >=", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeLessThan(Byte value) {
            addCriterion("solve_type <", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeLessThanOrEqualTo(Byte value) {
            addCriterion("solve_type <=", value, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeIn(List<Byte> values) {
            addCriterion("solve_type in", values, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeNotIn(List<Byte> values) {
            addCriterion("solve_type not in", values, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeBetween(Byte value1, Byte value2) {
            addCriterion("solve_type between", value1, value2, "solveType");
            return (Criteria) this;
        }

        public Criteria andSolveTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("solve_type not between", value1, value2, "solveType");
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