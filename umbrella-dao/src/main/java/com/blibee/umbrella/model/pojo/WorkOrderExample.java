package com.blibee.umbrella.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkOrderExample() {
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

        public Criteria andWorkIdIsNull() {
            addCriterion("work_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkIdIsNotNull() {
            addCriterion("work_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkIdEqualTo(Long value) {
            addCriterion("work_id =", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotEqualTo(Long value) {
            addCriterion("work_id <>", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThan(Long value) {
            addCriterion("work_id >", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThanOrEqualTo(Long value) {
            addCriterion("work_id >=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThan(Long value) {
            addCriterion("work_id <", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThanOrEqualTo(Long value) {
            addCriterion("work_id <=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdIn(List<Long> values) {
            addCriterion("work_id in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotIn(List<Long> values) {
            addCriterion("work_id not in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdBetween(Long value1, Long value2) {
            addCriterion("work_id between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotBetween(Long value1, Long value2) {
            addCriterion("work_id not between", value1, value2, "workId");
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

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueIsNull() {
            addCriterion("feedback_use_value is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueIsNotNull() {
            addCriterion("feedback_use_value is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueEqualTo(String value) {
            addCriterion("feedback_use_value =", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueNotEqualTo(String value) {
            addCriterion("feedback_use_value <>", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueGreaterThan(String value) {
            addCriterion("feedback_use_value >", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_use_value >=", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueLessThan(String value) {
            addCriterion("feedback_use_value <", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueLessThanOrEqualTo(String value) {
            addCriterion("feedback_use_value <=", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueLike(String value) {
            addCriterion("feedback_use_value like", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueNotLike(String value) {
            addCriterion("feedback_use_value not like", value, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueIn(List<String> values) {
            addCriterion("feedback_use_value in", values, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueNotIn(List<String> values) {
            addCriterion("feedback_use_value not in", values, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueBetween(String value1, String value2) {
            addCriterion("feedback_use_value between", value1, value2, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackUseValueNotBetween(String value1, String value2) {
            addCriterion("feedback_use_value not between", value1, value2, "feedbackUseValue");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserIsNull() {
            addCriterion("execute_user is null");
            return (Criteria) this;
        }

        public Criteria andExecuteUserIsNotNull() {
            addCriterion("execute_user is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteUserEqualTo(String value) {
            addCriterion("execute_user =", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserNotEqualTo(String value) {
            addCriterion("execute_user <>", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserGreaterThan(String value) {
            addCriterion("execute_user >", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserGreaterThanOrEqualTo(String value) {
            addCriterion("execute_user >=", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserLessThan(String value) {
            addCriterion("execute_user <", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserLessThanOrEqualTo(String value) {
            addCriterion("execute_user <=", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserLike(String value) {
            addCriterion("execute_user like", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserNotLike(String value) {
            addCriterion("execute_user not like", value, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserIn(List<String> values) {
            addCriterion("execute_user in", values, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserNotIn(List<String> values) {
            addCriterion("execute_user not in", values, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserBetween(String value1, String value2) {
            addCriterion("execute_user between", value1, value2, "executeUser");
            return (Criteria) this;
        }

        public Criteria andExecuteUserNotBetween(String value1, String value2) {
            addCriterion("execute_user not between", value1, value2, "executeUser");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescIsNull() {
            addCriterion("feedback_desc is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescIsNotNull() {
            addCriterion("feedback_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescEqualTo(String value) {
            addCriterion("feedback_desc =", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescNotEqualTo(String value) {
            addCriterion("feedback_desc <>", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescGreaterThan(String value) {
            addCriterion("feedback_desc >", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_desc >=", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescLessThan(String value) {
            addCriterion("feedback_desc <", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescLessThanOrEqualTo(String value) {
            addCriterion("feedback_desc <=", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescLike(String value) {
            addCriterion("feedback_desc like", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescNotLike(String value) {
            addCriterion("feedback_desc not like", value, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescIn(List<String> values) {
            addCriterion("feedback_desc in", values, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescNotIn(List<String> values) {
            addCriterion("feedback_desc not in", values, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescBetween(String value1, String value2) {
            addCriterion("feedback_desc between", value1, value2, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andFeedbackDescNotBetween(String value1, String value2) {
            addCriterion("feedback_desc not between", value1, value2, "feedbackDesc");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNull() {
            addCriterion("location_name is null");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNotNull() {
            addCriterion("location_name is not null");
            return (Criteria) this;
        }

        public Criteria andLocationNameEqualTo(String value) {
            addCriterion("location_name =", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotEqualTo(String value) {
            addCriterion("location_name <>", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThan(String value) {
            addCriterion("location_name >", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("location_name >=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThan(String value) {
            addCriterion("location_name <", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThanOrEqualTo(String value) {
            addCriterion("location_name <=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLike(String value) {
            addCriterion("location_name like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotLike(String value) {
            addCriterion("location_name not like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameIn(List<String> values) {
            addCriterion("location_name in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotIn(List<String> values) {
            addCriterion("location_name not in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameBetween(String value1, String value2) {
            addCriterion("location_name between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotBetween(String value1, String value2) {
            addCriterion("location_name not between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationPositionIsNull() {
            addCriterion("location_position is null");
            return (Criteria) this;
        }

        public Criteria andLocationPositionIsNotNull() {
            addCriterion("location_position is not null");
            return (Criteria) this;
        }

        public Criteria andLocationPositionEqualTo(String value) {
            addCriterion("location_position =", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionNotEqualTo(String value) {
            addCriterion("location_position <>", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionGreaterThan(String value) {
            addCriterion("location_position >", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionGreaterThanOrEqualTo(String value) {
            addCriterion("location_position >=", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionLessThan(String value) {
            addCriterion("location_position <", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionLessThanOrEqualTo(String value) {
            addCriterion("location_position <=", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionLike(String value) {
            addCriterion("location_position like", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionNotLike(String value) {
            addCriterion("location_position not like", value, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionIn(List<String> values) {
            addCriterion("location_position in", values, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionNotIn(List<String> values) {
            addCriterion("location_position not in", values, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionBetween(String value1, String value2) {
            addCriterion("location_position between", value1, value2, "locationPosition");
            return (Criteria) this;
        }

        public Criteria andLocationPositionNotBetween(String value1, String value2) {
            addCriterion("location_position not between", value1, value2, "locationPosition");
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

        public Criteria andDetailStateIsNull() {
            addCriterion("detail_state is null");
            return (Criteria) this;
        }

        public Criteria andDetailStateIsNotNull() {
            addCriterion("detail_state is not null");
            return (Criteria) this;
        }

        public Criteria andDetailStateEqualTo(Byte value) {
            addCriterion("detail_state =", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateNotEqualTo(Byte value) {
            addCriterion("detail_state <>", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateGreaterThan(Byte value) {
            addCriterion("detail_state >", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("detail_state >=", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateLessThan(Byte value) {
            addCriterion("detail_state <", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateLessThanOrEqualTo(Byte value) {
            addCriterion("detail_state <=", value, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateIn(List<Byte> values) {
            addCriterion("detail_state in", values, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateNotIn(List<Byte> values) {
            addCriterion("detail_state not in", values, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateBetween(Byte value1, Byte value2) {
            addCriterion("detail_state between", value1, value2, "detailState");
            return (Criteria) this;
        }

        public Criteria andDetailStateNotBetween(Byte value1, Byte value2) {
            addCriterion("detail_state not between", value1, value2, "detailState");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNull() {
            addCriterion("review_time is null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNotNull() {
            addCriterion("review_time is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeEqualTo(Date value) {
            addCriterion("review_time =", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotEqualTo(Date value) {
            addCriterion("review_time <>", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThan(Date value) {
            addCriterion("review_time >", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("review_time >=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThan(Date value) {
            addCriterion("review_time <", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("review_time <=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIn(List<Date> values) {
            addCriterion("review_time in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotIn(List<Date> values) {
            addCriterion("review_time not in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeBetween(Date value1, Date value2) {
            addCriterion("review_time between", value1, value2, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("review_time not between", value1, value2, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andValidStateIsNull() {
            addCriterion("valid_state is null");
            return (Criteria) this;
        }

        public Criteria andValidStateIsNotNull() {
            addCriterion("valid_state is not null");
            return (Criteria) this;
        }

        public Criteria andValidStateEqualTo(Byte value) {
            addCriterion("valid_state =", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateNotEqualTo(Byte value) {
            addCriterion("valid_state <>", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateGreaterThan(Byte value) {
            addCriterion("valid_state >", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("valid_state >=", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateLessThan(Byte value) {
            addCriterion("valid_state <", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateLessThanOrEqualTo(Byte value) {
            addCriterion("valid_state <=", value, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateIn(List<Byte> values) {
            addCriterion("valid_state in", values, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateNotIn(List<Byte> values) {
            addCriterion("valid_state not in", values, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateBetween(Byte value1, Byte value2) {
            addCriterion("valid_state between", value1, value2, "validState");
            return (Criteria) this;
        }

        public Criteria andValidStateNotBetween(Byte value1, Byte value2) {
            addCriterion("valid_state not between", value1, value2, "validState");
            return (Criteria) this;
        }

        public Criteria andSyncStateIsNull() {
            addCriterion("sync_state is null");
            return (Criteria) this;
        }

        public Criteria andSyncStateIsNotNull() {
            addCriterion("sync_state is not null");
            return (Criteria) this;
        }

        public Criteria andSyncStateEqualTo(Byte value) {
            addCriterion("sync_state =", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateNotEqualTo(Byte value) {
            addCriterion("sync_state <>", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateGreaterThan(Byte value) {
            addCriterion("sync_state >", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("sync_state >=", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateLessThan(Byte value) {
            addCriterion("sync_state <", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateLessThanOrEqualTo(Byte value) {
            addCriterion("sync_state <=", value, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateIn(List<Byte> values) {
            addCriterion("sync_state in", values, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateNotIn(List<Byte> values) {
            addCriterion("sync_state not in", values, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateBetween(Byte value1, Byte value2) {
            addCriterion("sync_state between", value1, value2, "syncState");
            return (Criteria) this;
        }

        public Criteria andSyncStateNotBetween(Byte value1, Byte value2) {
            addCriterion("sync_state not between", value1, value2, "syncState");
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