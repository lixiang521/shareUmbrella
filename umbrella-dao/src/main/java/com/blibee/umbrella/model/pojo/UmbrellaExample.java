package com.blibee.umbrella.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmbrellaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmbrellaExample() {
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

        public Criteria andRepairStateIsNull() {
            addCriterion("repair_state is null");
            return (Criteria) this;
        }

        public Criteria andRepairStateIsNotNull() {
            addCriterion("repair_state is not null");
            return (Criteria) this;
        }

        public Criteria andRepairStateEqualTo(Byte value) {
            addCriterion("repair_state =", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateNotEqualTo(Byte value) {
            addCriterion("repair_state <>", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateGreaterThan(Byte value) {
            addCriterion("repair_state >", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("repair_state >=", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateLessThan(Byte value) {
            addCriterion("repair_state <", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateLessThanOrEqualTo(Byte value) {
            addCriterion("repair_state <=", value, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateIn(List<Byte> values) {
            addCriterion("repair_state in", values, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateNotIn(List<Byte> values) {
            addCriterion("repair_state not in", values, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateBetween(Byte value1, Byte value2) {
            addCriterion("repair_state between", value1, value2, "repairState");
            return (Criteria) this;
        }

        public Criteria andRepairStateNotBetween(Byte value1, Byte value2) {
            addCriterion("repair_state not between", value1, value2, "repairState");
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