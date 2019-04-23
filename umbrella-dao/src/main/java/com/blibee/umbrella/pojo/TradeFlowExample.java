package com.blibee.umbrella.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeFlowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeFlowExample() {
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

        public Criteria andTradeNumberIsNull() {
            addCriterion("trade_number is null");
            return (Criteria) this;
        }

        public Criteria andTradeNumberIsNotNull() {
            addCriterion("trade_number is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNumberEqualTo(Long value) {
            addCriterion("trade_number =", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberNotEqualTo(Long value) {
            addCriterion("trade_number <>", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberGreaterThan(Long value) {
            addCriterion("trade_number >", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_number >=", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberLessThan(Long value) {
            addCriterion("trade_number <", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberLessThanOrEqualTo(Long value) {
            addCriterion("trade_number <=", value, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberIn(List<Long> values) {
            addCriterion("trade_number in", values, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberNotIn(List<Long> values) {
            addCriterion("trade_number not in", values, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberBetween(Long value1, Long value2) {
            addCriterion("trade_number between", value1, value2, "tradeNumber");
            return (Criteria) this;
        }

        public Criteria andTradeNumberNotBetween(Long value1, Long value2) {
            addCriterion("trade_number not between", value1, value2, "tradeNumber");
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

        public Criteria andRefundPrepayAmountIsNull() {
            addCriterion("refund_prepay_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountIsNotNull() {
            addCriterion("refund_prepay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountEqualTo(BigDecimal value) {
            addCriterion("refund_prepay_amount =", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_prepay_amount <>", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_prepay_amount >", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_prepay_amount >=", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountLessThan(BigDecimal value) {
            addCriterion("refund_prepay_amount <", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_prepay_amount <=", value, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountIn(List<BigDecimal> values) {
            addCriterion("refund_prepay_amount in", values, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_prepay_amount not in", values, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_prepay_amount between", value1, value2, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_prepay_amount not between", value1, value2, "refundPrepayAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountIsNull() {
            addCriterion("refund_part_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountIsNotNull() {
            addCriterion("refund_part_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountEqualTo(BigDecimal value) {
            addCriterion("refund_part_amount =", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_part_amount <>", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_part_amount >", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_part_amount >=", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountLessThan(BigDecimal value) {
            addCriterion("refund_part_amount <", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_part_amount <=", value, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountIn(List<BigDecimal> values) {
            addCriterion("refund_part_amount in", values, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_part_amount not in", values, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_part_amount between", value1, value2, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_part_amount not between", value1, value2, "refundPartAmount");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberIsNull() {
            addCriterion("refund_part_trade_number is null");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberIsNotNull() {
            addCriterion("refund_part_trade_number is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberEqualTo(Long value) {
            addCriterion("refund_part_trade_number =", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberNotEqualTo(Long value) {
            addCriterion("refund_part_trade_number <>", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberGreaterThan(Long value) {
            addCriterion("refund_part_trade_number >", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_part_trade_number >=", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberLessThan(Long value) {
            addCriterion("refund_part_trade_number <", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberLessThanOrEqualTo(Long value) {
            addCriterion("refund_part_trade_number <=", value, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberIn(List<Long> values) {
            addCriterion("refund_part_trade_number in", values, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberNotIn(List<Long> values) {
            addCriterion("refund_part_trade_number not in", values, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberBetween(Long value1, Long value2) {
            addCriterion("refund_part_trade_number between", value1, value2, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTradeNumberNotBetween(Long value1, Long value2) {
            addCriterion("refund_part_trade_number not between", value1, value2, "refundPartTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeIsNull() {
            addCriterion("refund_part_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeIsNotNull() {
            addCriterion("refund_part_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeEqualTo(Date value) {
            addCriterion("refund_part_time =", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeNotEqualTo(Date value) {
            addCriterion("refund_part_time <>", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeGreaterThan(Date value) {
            addCriterion("refund_part_time >", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_part_time >=", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeLessThan(Date value) {
            addCriterion("refund_part_time <", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_part_time <=", value, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeIn(List<Date> values) {
            addCriterion("refund_part_time in", values, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeNotIn(List<Date> values) {
            addCriterion("refund_part_time not in", values, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeBetween(Date value1, Date value2) {
            addCriterion("refund_part_time between", value1, value2, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPartTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_part_time not between", value1, value2, "refundPartTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberIsNull() {
            addCriterion("refund_prepay_trade_number is null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberIsNotNull() {
            addCriterion("refund_prepay_trade_number is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberEqualTo(Long value) {
            addCriterion("refund_prepay_trade_number =", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberNotEqualTo(Long value) {
            addCriterion("refund_prepay_trade_number <>", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberGreaterThan(Long value) {
            addCriterion("refund_prepay_trade_number >", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_prepay_trade_number >=", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberLessThan(Long value) {
            addCriterion("refund_prepay_trade_number <", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberLessThanOrEqualTo(Long value) {
            addCriterion("refund_prepay_trade_number <=", value, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberIn(List<Long> values) {
            addCriterion("refund_prepay_trade_number in", values, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberNotIn(List<Long> values) {
            addCriterion("refund_prepay_trade_number not in", values, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberBetween(Long value1, Long value2) {
            addCriterion("refund_prepay_trade_number between", value1, value2, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTradeNumberNotBetween(Long value1, Long value2) {
            addCriterion("refund_prepay_trade_number not between", value1, value2, "refundPrepayTradeNumber");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeIsNull() {
            addCriterion("refund_prepay_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeIsNotNull() {
            addCriterion("refund_prepay_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeEqualTo(Date value) {
            addCriterion("refund_prepay_time =", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeNotEqualTo(Date value) {
            addCriterion("refund_prepay_time <>", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeGreaterThan(Date value) {
            addCriterion("refund_prepay_time >", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_prepay_time >=", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeLessThan(Date value) {
            addCriterion("refund_prepay_time <", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_prepay_time <=", value, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeIn(List<Date> values) {
            addCriterion("refund_prepay_time in", values, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeNotIn(List<Date> values) {
            addCriterion("refund_prepay_time not in", values, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeBetween(Date value1, Date value2) {
            addCriterion("refund_prepay_time between", value1, value2, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundPrepayTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_prepay_time not between", value1, value2, "refundPrepayTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNull() {
            addCriterion("refund_time is null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNotNull() {
            addCriterion("refund_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeEqualTo(Date value) {
            addCriterion("refund_time =", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotEqualTo(Date value) {
            addCriterion("refund_time <>", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThan(Date value) {
            addCriterion("refund_time >", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_time >=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThan(Date value) {
            addCriterion("refund_time <", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_time <=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIn(List<Date> values) {
            addCriterion("refund_time in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotIn(List<Date> values) {
            addCriterion("refund_time not in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeBetween(Date value1, Date value2) {
            addCriterion("refund_time between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_time not between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("trade_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("trade_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(Date value) {
            addCriterion("trade_time =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(Date value) {
            addCriterion("trade_time <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(Date value) {
            addCriterion("trade_time >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trade_time >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(Date value) {
            addCriterion("trade_time <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("trade_time <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<Date> values) {
            addCriterion("trade_time in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<Date> values) {
            addCriterion("trade_time not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(Date value1, Date value2) {
            addCriterion("trade_time between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("trade_time not between", value1, value2, "tradeTime");
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