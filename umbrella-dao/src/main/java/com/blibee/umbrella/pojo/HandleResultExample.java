package com.blibee.umbrella.pojo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandleResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HandleResultExample() {
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

        public Criteria andUmbrellaCabinetCoordinateIsNull() {
            addCriterion("umbrella_cabinet_coordinate is null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateIsNotNull() {
            addCriterion("umbrella_cabinet_coordinate is not null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateEqualTo(Byte value) {
            addCriterion("umbrella_cabinet_coordinate =", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateNotEqualTo(Byte value) {
            addCriterion("umbrella_cabinet_coordinate <>", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateGreaterThan(Byte value) {
            addCriterion("umbrella_cabinet_coordinate >", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateGreaterThanOrEqualTo(Byte value) {
            addCriterion("umbrella_cabinet_coordinate >=", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateLessThan(Byte value) {
            addCriterion("umbrella_cabinet_coordinate <", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateLessThanOrEqualTo(Byte value) {
            addCriterion("umbrella_cabinet_coordinate <=", value, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateIn(List<Byte> values) {
            addCriterion("umbrella_cabinet_coordinate in", values, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateNotIn(List<Byte> values) {
            addCriterion("umbrella_cabinet_coordinate not in", values, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateBetween(Byte value1, Byte value2) {
            addCriterion("umbrella_cabinet_coordinate between", value1, value2, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaCabinetCoordinateNotBetween(Byte value1, Byte value2) {
            addCriterion("umbrella_cabinet_coordinate not between", value1, value2, "umbrellaCabinetCoordinate");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumIsNull() {
            addCriterion("umbrella_total_num is null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumIsNotNull() {
            addCriterion("umbrella_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumEqualTo(String value) {
            addCriterion("umbrella_total_num =", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumNotEqualTo(String value) {
            addCriterion("umbrella_total_num <>", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumGreaterThan(String value) {
            addCriterion("umbrella_total_num >", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumGreaterThanOrEqualTo(String value) {
            addCriterion("umbrella_total_num >=", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumLessThan(String value) {
            addCriterion("umbrella_total_num <", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumLessThanOrEqualTo(String value) {
            addCriterion("umbrella_total_num <=", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumLike(String value) {
            addCriterion("umbrella_total_num like", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumNotLike(String value) {
            addCriterion("umbrella_total_num not like", value, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumIn(List<String> values) {
            addCriterion("umbrella_total_num in", values, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumNotIn(List<String> values) {
            addCriterion("umbrella_total_num not in", values, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumBetween(String value1, String value2) {
            addCriterion("umbrella_total_num between", value1, value2, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaTotalNumNotBetween(String value1, String value2) {
            addCriterion("umbrella_total_num not between", value1, value2, "umbrellaTotalNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumIsNull() {
            addCriterion("umbrella_damaged_num is null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumIsNotNull() {
            addCriterion("umbrella_damaged_num is not null");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumEqualTo(String value) {
            addCriterion("umbrella_damaged_num =", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumNotEqualTo(String value) {
            addCriterion("umbrella_damaged_num <>", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumGreaterThan(String value) {
            addCriterion("umbrella_damaged_num >", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumGreaterThanOrEqualTo(String value) {
            addCriterion("umbrella_damaged_num >=", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumLessThan(String value) {
            addCriterion("umbrella_damaged_num <", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumLessThanOrEqualTo(String value) {
            addCriterion("umbrella_damaged_num <=", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumLike(String value) {
            addCriterion("umbrella_damaged_num like", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumNotLike(String value) {
            addCriterion("umbrella_damaged_num not like", value, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumIn(List<String> values) {
            addCriterion("umbrella_damaged_num in", values, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumNotIn(List<String> values) {
            addCriterion("umbrella_damaged_num not in", values, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumBetween(String value1, String value2) {
            addCriterion("umbrella_damaged_num between", value1, value2, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andUmbrellaDamagedNumNotBetween(String value1, String value2) {
            addCriterion("umbrella_damaged_num not between", value1, value2, "umbrellaDamagedNum");
            return (Criteria) this;
        }

        public Criteria andCodeStateIsNull() {
            addCriterion("code_state is null");
            return (Criteria) this;
        }

        public Criteria andCodeStateIsNotNull() {
            addCriterion("code_state is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStateEqualTo(Byte value) {
            addCriterion("code_state =", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotEqualTo(Byte value) {
            addCriterion("code_state <>", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateGreaterThan(Byte value) {
            addCriterion("code_state >", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("code_state >=", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateLessThan(Byte value) {
            addCriterion("code_state <", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateLessThanOrEqualTo(Byte value) {
            addCriterion("code_state <=", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateIn(List<Byte> values) {
            addCriterion("code_state in", values, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotIn(List<Byte> values) {
            addCriterion("code_state not in", values, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateBetween(Byte value1, Byte value2) {
            addCriterion("code_state between", value1, value2, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotBetween(Byte value1, Byte value2) {
            addCriterion("code_state not between", value1, value2, "codeState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateIsNull() {
            addCriterion("borrow_state is null");
            return (Criteria) this;
        }

        public Criteria andBorrowStateIsNotNull() {
            addCriterion("borrow_state is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowStateEqualTo(Byte value) {
            addCriterion("borrow_state =", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateNotEqualTo(Byte value) {
            addCriterion("borrow_state <>", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateGreaterThan(Byte value) {
            addCriterion("borrow_state >", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("borrow_state >=", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateLessThan(Byte value) {
            addCriterion("borrow_state <", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateLessThanOrEqualTo(Byte value) {
            addCriterion("borrow_state <=", value, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateIn(List<Byte> values) {
            addCriterion("borrow_state in", values, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateNotIn(List<Byte> values) {
            addCriterion("borrow_state not in", values, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateBetween(Byte value1, Byte value2) {
            addCriterion("borrow_state between", value1, value2, "borrowState");
            return (Criteria) this;
        }

        public Criteria andBorrowStateNotBetween(Byte value1, Byte value2) {
            addCriterion("borrow_state not between", value1, value2, "borrowState");
            return (Criteria) this;
        }

        public Criteria andReturnStateIsNull() {
            addCriterion("return_state is null");
            return (Criteria) this;
        }

        public Criteria andReturnStateIsNotNull() {
            addCriterion("return_state is not null");
            return (Criteria) this;
        }

        public Criteria andReturnStateEqualTo(Byte value) {
            addCriterion("return_state =", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateNotEqualTo(Byte value) {
            addCriterion("return_state <>", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateGreaterThan(Byte value) {
            addCriterion("return_state >", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("return_state >=", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateLessThan(Byte value) {
            addCriterion("return_state <", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateLessThanOrEqualTo(Byte value) {
            addCriterion("return_state <=", value, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateIn(List<Byte> values) {
            addCriterion("return_state in", values, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateNotIn(List<Byte> values) {
            addCriterion("return_state not in", values, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateBetween(Byte value1, Byte value2) {
            addCriterion("return_state between", value1, value2, "returnState");
            return (Criteria) this;
        }

        public Criteria andReturnStateNotBetween(Byte value1, Byte value2) {
            addCriterion("return_state not between", value1, value2, "returnState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateIsNull() {
            addCriterion("voice_state is null");
            return (Criteria) this;
        }

        public Criteria andVoiceStateIsNotNull() {
            addCriterion("voice_state is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceStateEqualTo(Byte value) {
            addCriterion("voice_state =", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateNotEqualTo(Byte value) {
            addCriterion("voice_state <>", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateGreaterThan(Byte value) {
            addCriterion("voice_state >", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("voice_state >=", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateLessThan(Byte value) {
            addCriterion("voice_state <", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateLessThanOrEqualTo(Byte value) {
            addCriterion("voice_state <=", value, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateIn(List<Byte> values) {
            addCriterion("voice_state in", values, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateNotIn(List<Byte> values) {
            addCriterion("voice_state not in", values, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateBetween(Byte value1, Byte value2) {
            addCriterion("voice_state between", value1, value2, "voiceState");
            return (Criteria) this;
        }

        public Criteria andVoiceStateNotBetween(Byte value1, Byte value2) {
            addCriterion("voice_state not between", value1, value2, "voiceState");
            return (Criteria) this;
        }

        public Criteria andPositionStateIsNull() {
            addCriterion("position_state is null");
            return (Criteria) this;
        }

        public Criteria andPositionStateIsNotNull() {
            addCriterion("position_state is not null");
            return (Criteria) this;
        }

        public Criteria andPositionStateEqualTo(Byte value) {
            addCriterion("position_state =", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateNotEqualTo(Byte value) {
            addCriterion("position_state <>", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateGreaterThan(Byte value) {
            addCriterion("position_state >", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("position_state >=", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateLessThan(Byte value) {
            addCriterion("position_state <", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateLessThanOrEqualTo(Byte value) {
            addCriterion("position_state <=", value, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateIn(List<Byte> values) {
            addCriterion("position_state in", values, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateNotIn(List<Byte> values) {
            addCriterion("position_state not in", values, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateBetween(Byte value1, Byte value2) {
            addCriterion("position_state between", value1, value2, "positionState");
            return (Criteria) this;
        }

        public Criteria andPositionStateNotBetween(Byte value1, Byte value2) {
            addCriterion("position_state not between", value1, value2, "positionState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateIsNull() {
            addCriterion("hardware_state is null");
            return (Criteria) this;
        }

        public Criteria andHardwareStateIsNotNull() {
            addCriterion("hardware_state is not null");
            return (Criteria) this;
        }

        public Criteria andHardwareStateEqualTo(Byte value) {
            addCriterion("hardware_state =", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateNotEqualTo(Byte value) {
            addCriterion("hardware_state <>", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateGreaterThan(Byte value) {
            addCriterion("hardware_state >", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("hardware_state >=", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateLessThan(Byte value) {
            addCriterion("hardware_state <", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateLessThanOrEqualTo(Byte value) {
            addCriterion("hardware_state <=", value, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateIn(List<Byte> values) {
            addCriterion("hardware_state in", values, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateNotIn(List<Byte> values) {
            addCriterion("hardware_state not in", values, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateBetween(Byte value1, Byte value2) {
            addCriterion("hardware_state between", value1, value2, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHardwareStateNotBetween(Byte value1, Byte value2) {
            addCriterion("hardware_state not between", value1, value2, "hardwareState");
            return (Criteria) this;
        }

        public Criteria andHandleStateIsNull() {
            addCriterion("handle_state is null");
            return (Criteria) this;
        }

        public Criteria andHandleStateIsNotNull() {
            addCriterion("handle_state is not null");
            return (Criteria) this;
        }

        public Criteria andHandleStateEqualTo(Byte value) {
            addCriterion("handle_state =", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotEqualTo(Byte value) {
            addCriterion("handle_state <>", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateGreaterThan(Byte value) {
            addCriterion("handle_state >", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("handle_state >=", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateLessThan(Byte value) {
            addCriterion("handle_state <", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateLessThanOrEqualTo(Byte value) {
            addCriterion("handle_state <=", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateIn(List<Byte> values) {
            addCriterion("handle_state in", values, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotIn(List<Byte> values) {
            addCriterion("handle_state not in", values, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateBetween(Byte value1, Byte value2) {
            addCriterion("handle_state between", value1, value2, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotBetween(Byte value1, Byte value2) {
            addCriterion("handle_state not between", value1, value2, "handleState");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueIsNull() {
            addCriterion("feedback_hardware_value is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueIsNotNull() {
            addCriterion("feedback_hardware_value is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueEqualTo(String value) {
            addCriterion("feedback_hardware_value =", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueNotEqualTo(String value) {
            addCriterion("feedback_hardware_value <>", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueGreaterThan(String value) {
            addCriterion("feedback_hardware_value >", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_hardware_value >=", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueLessThan(String value) {
            addCriterion("feedback_hardware_value <", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueLessThanOrEqualTo(String value) {
            addCriterion("feedback_hardware_value <=", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueLike(String value) {
            addCriterion("feedback_hardware_value like", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueNotLike(String value) {
            addCriterion("feedback_hardware_value not like", value, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueIn(List<String> values) {
            addCriterion("feedback_hardware_value in", values, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueNotIn(List<String> values) {
            addCriterion("feedback_hardware_value not in", values, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueBetween(String value1, String value2) {
            addCriterion("feedback_hardware_value between", value1, value2, "feedbackHardwareValue");
            return (Criteria) this;
        }

        public Criteria andFeedbackHardwareValueNotBetween(String value1, String value2) {
            addCriterion("feedback_hardware_value not between", value1, value2, "feedbackHardwareValue");
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

        public Criteria andVideoIsNull() {
            addCriterion("video is null");
            return (Criteria) this;
        }

        public Criteria andVideoIsNotNull() {
            addCriterion("video is not null");
            return (Criteria) this;
        }

        public Criteria andVideoEqualTo(String value) {
            addCriterion("video =", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotEqualTo(String value) {
            addCriterion("video <>", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThan(String value) {
            addCriterion("video >", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThanOrEqualTo(String value) {
            addCriterion("video >=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThan(String value) {
            addCriterion("video <", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThanOrEqualTo(String value) {
            addCriterion("video <=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLike(String value) {
            addCriterion("video like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotLike(String value) {
            addCriterion("video not like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoIn(List<String> values) {
            addCriterion("video in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotIn(List<String> values) {
            addCriterion("video not in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoBetween(String value1, String value2) {
            addCriterion("video between", value1, value2, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotBetween(String value1, String value2) {
            addCriterion("video not between", value1, value2, "video");
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