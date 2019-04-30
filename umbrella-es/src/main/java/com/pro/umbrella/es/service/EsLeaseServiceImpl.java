//package com.pro.umbrella.es.service;
//
//import com.google.common.collect.Lists;
//import com.tempoon.umbrella.constants.enums.TypeStateEnums;
//import com.tempoon.umbrella.es.biz.param.LeaseGroupParam;
//import com.tempoon.umbrella.es.biz.param.LeaseQueryParam;
//import com.tempoon.umbrella.es.common.ElasticSearchClient;
//import com.tempoon.umbrella.es.service.EsLeaseService;
//import com.tempoon.umbrella.es.service.bean.EsLease;
//import com.wormpex.api.json.JsonUtil;
//import com.wormpex.api.pojo.page.Limit;
//import com.wormpex.api.pojo.page.Pager;
//import com.wormpex.common.metrics.Metrics;
//import com.wormpex.monitor.WMonitor;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.DocWriteRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.common.Strings;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class EsLeaseService {
//
//    @Value("${es.lease.index.name}")
//    private String INDEX_NAME;
//    public static final String TYPE_NAME = "default";
//    public static final Integer GROUP_SIZE = 100;
//
//    public interface Prop {
//        String ID = "id"; // 订单号
//        String UMBRELLA_NUMBER = "umbrellaNumber"; // 雨伞编号
//        String START_CABINET_NUMBER = "startCabinetNumber"; // 借出伞柜
//        String END_CABINET_NUMBER = "endCabinetNumber"; // 归还伞柜
//        String UID = "uid"; // 用户ID
//        String PHONE = "phone"; // 电话
//        String LEASE_STATE = "leaseState"; // 订单状态
//        String TRADE_STATE = "tradeState"; // 支付状态
//        String TRANS_STATE = "transState"; // 流转状态
//        String IS_SAME_CABINET = "isSameCabinet";
//        String CLIENT_ID = "clientId"; // 客户ID
//        String CREATE_TIME = "createTime"; // 下单时间
//        String RETURN_TIME = "returnTime"; // 还伞时间
//        String START_SCENE_TYPE = "startSceneType"; // 借出场景
//        String START_SCENE = "startScene"; // 借出场景
//        String START_POINT_ID = "startPointId"; // 借出点位ID
//        String START_POINT_NAME = "startPointName"; // 借出点位名称
//        String START_PROVINCE = "startProvince"; // 借出省
//        String START_CITY = "startCity"; // 借出市
//        String START_DISTRICT = "startDistrict"; // 借出区
//        String START_LOCATION = "startLocation"; // 借出位置
//        String END_SCENE_TYPE = "endSceneType"; // 归还场景
//        String END_SCENE = "endScene"; // 归还场景
//        String END_POINT_ID = "endPointId";
//        String END_POINT_NAME = "endPointName";
//        String END_PROVINCE = "endProvince";
//        String END_CITY = "endCity";
//        String END_DISTRICT = "endDistrict";
//        String END_LOCATION = "endLocation";
//        String USER_RESOURCE = "userResource";
//        String DURATION = "duration";
//        String LEASE_NODE = "leaseNode";
//        String ENTRANCE = "entrance";
//        String PAY_TYPE = "payType";
//    }
//
//    @Resource
//    private ElasticSearchClient elasticSearchClient;
//
//    @Override
//    public void saveOrUpdate(EsLease lease) {
//        IndexRequest updateRequest = new IndexRequest(INDEX_NAME, TYPE_NAME, String.valueOf(lease.getId()));
//
//        updateRequest.opType(DocWriteRequest.OpType.INDEX);
//        updateRequest.source(JsonUtil.toJson(lease), XContentType.JSON);
//
//        long start = System.currentTimeMillis();
//
//        elasticSearchClient.getClient().index(updateRequest);
//
//        WMonitor.recordMetricForDuration(this.getClass().getName() + ".saveOrUpdate", System.currentTimeMillis() - start);
//        Metrics.counter(this.getClass().getName() + ".saveOrUpdate").get().inc();
//    }
//
//    @Override
//    public Map<String, Integer> group(LeaseGroupParam param) {
//        Map<String, Integer> map = new HashMap<>();
//        LeaseQueryParam leaseQueryParam = new LeaseQueryParam();
//        BeanUtils.copyProperties(param, leaseQueryParam);
//        BoolQueryBuilder qb = queryCondition(leaseQueryParam);
//        SearchRequestBuilder sv = elasticSearchClient.getClient().prepareSearch(INDEX_NAME).setTypes(TYPE_NAME)
//                .setQuery(qb);
//        if (param.getGroupField().contains(Prop.START_CITY)) {
//            sv = sv.addAggregation(
//                    AggregationBuilders.terms(Prop.START_CITY).field(Prop.START_CITY).size(GROUP_SIZE));
//            SearchResponse response = sv.execute().actionGet();
//            Terms terms = response.getAggregations().get(Prop.START_CITY);
//            for (Terms.Bucket entry : terms.getBuckets()) {
//                map.put(entry.getKey().toString(), Integer.parseInt(String.valueOf(entry.getDocCount())));
//            }
//        }
//        if (param.getGroupField().contains(Prop.START_SCENE_TYPE)) {
//            sv = sv.addAggregation(
//                    AggregationBuilders.terms(Prop.START_SCENE_TYPE).field(Prop.START_SCENE_TYPE).size(GROUP_SIZE));
//            SearchResponse response = sv.execute().actionGet();
//            Terms terms = response.getAggregations().get(Prop.START_SCENE_TYPE);
//            for (Terms.Bucket entry : terms.getBuckets()) {
//                map.put(entry.getKey().toString(), Integer.parseInt(String.valueOf(entry.getDocCount())));
//            }
//        }
//        return map;
//    }
//
//    @Override
//    public Pager<EsLease> query(LeaseQueryParam param) {
//        long start = System.currentTimeMillis();
//        try {
//            BoolQueryBuilder qb = queryCondition(param);
//            Limit limit = param.getPage().getLimit();
//            SearchRequestBuilder sv = elasticSearchClient.getClient().prepareSearch(INDEX_NAME).setTypes(TYPE_NAME)
//                    .setQuery(qb).setFrom(limit.getOffset()).setSize(limit.getSize())
//                    .addSort(Prop.CREATE_TIME, SortOrder.DESC);
//            SearchResponse response = sv.get();
//            SearchHits searchHits = response.getHits();
//            int count = searchHits == null ? 0 : (int) searchHits.getTotalHits();
//            List<EsLease> esLeases;
//            if (searchHits != null && searchHits.getHits() != null) {
//                esLeases = Lists.newArrayList();
//                for (SearchHit hit : searchHits.getHits()) {
//                    EsLease esLease = JsonUtil.of(hit.getSourceAsString(), EsLease.class);
//                    if (esLease != null) {
//                        esLeases.add(esLease);
//                    }
//                }
//            } else {
//                esLeases = Collections.emptyList();
//            }
//            Pager.PageData page = new Pager.PageData(param.getPage().getPageNo(), param.getPage().getPageSize(), count);
//            return new Pager<>(page, esLeases);
//        } finally {
//            WMonitor.recordMetricForDuration(this.getClass().getName() + ".query", System.currentTimeMillis() - start);
//            Metrics.counter(this.getClass().getName() + ".query").get().inc();
//        }
//    }
//
//    private BoolQueryBuilder queryCondition(LeaseQueryParam param) {
//        BoolQueryBuilder qb = QueryBuilders.boolQuery();
//
//        if (!Strings.isNullOrEmpty(param.getId())) {
//            qb.must(QueryBuilders.termQuery(Prop.ID, param.getId()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getUmbrellaNumber())) {
//            qb.must(QueryBuilders.termQuery(Prop.UMBRELLA_NUMBER, param.getUmbrellaNumber()));
//        }
//
//        // or
//        if (null != param.getIsSameCabinet()
//                && Integer.valueOf(TypeStateEnums.OpLeaseFilter.ALL).equals(param.getIsSameCabinet())) {
//            qb.must(QueryBuilders.boolQuery()
//                    .should(QueryBuilders.termQuery(Prop.START_CABINET_NUMBER, param.getStartCabinetNumber()))
//                    .should(QueryBuilders.termQuery(Prop.END_CABINET_NUMBER, param.getEndCabinetNumber())));
//        } else {
//            if (!Strings.isNullOrEmpty(param.getStartCabinetNumber())) {
//                qb.must(QueryBuilders.termQuery(Prop.START_CABINET_NUMBER, param.getStartCabinetNumber()));
//            }
//
//            if (!Strings.isNullOrEmpty(param.getEndCabinetNumber())) {
//                qb.must(QueryBuilders.termQuery(Prop.END_CABINET_NUMBER, param.getEndCabinetNumber()));
//            }
//        }
//
//        if (!Strings.isNullOrEmpty(param.getUid())) {
//            qb.must(QueryBuilders.termQuery(Prop.UID, param.getUid()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getPhone())) {
//            qb.must(QueryBuilders.matchQuery(Prop.PHONE, param.getPhone().trim()));
//        }
//
//        if (param.getLeaseState() != null && !param.getLeaseState().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.LEASE_STATE, param.getLeaseState()));
//        }
//
//        if (param.getTradeState() != null && !param.getTradeState().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.TRADE_STATE, param.getTradeState()));
//        }
//
//        if (param.getIsSameCabinet() != null
//                && param.getIsSameCabinet() < Integer.valueOf(TypeStateEnums.OpLeaseFilter.ALL)) {
//            qb.must(QueryBuilders.termQuery(Prop.IS_SAME_CABINET, param.getIsSameCabinet()));
//        }
//        if (param.getTransState() != null && !param.getTransState().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.TRANS_STATE, param.getTransState()));
//        }
//
//        if (param.getClientId() != null) {
//            qb.must(QueryBuilders.termQuery(Prop.CLIENT_ID, param.getClientId()));
//        }
//
//        if (param.getMinCreateTime() != null && param.getMaxCreateTime() != null) {
//            qb.must(QueryBuilders.rangeQuery(Prop.CREATE_TIME).from(param.getMinCreateTime())
//                    .to(param.getMaxCreateTime()));
//        }
//
//        if (param.getMinReturnTime() != null && param.getMaxReturnTime() != null) {
//            qb.must(QueryBuilders.rangeQuery(Prop.RETURN_TIME).from(param.getMinReturnTime())
//                    .to(param.getMaxReturnTime()));
//        }
//
//        if (param.getDuration() != null) {
//            qb.must(QueryBuilders.termQuery(Prop.DURATION, param.getDuration()));
//        }
//
//        if (param.getStartSceneType() != null && !param.getStartSceneType().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.START_SCENE_TYPE, param.getStartSceneType()));
//        }
//
//        if (param.getStartScene() != null && !param.getStartScene().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.START_SCENE, param.getStartScene()));
//        }
//
//        if (param.getStartPointId() != null) {
//            qb.must(QueryBuilders.termQuery(Prop.START_POINT_ID, param.getStartPointId()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getStartPointName())) {
//            qb.must(QueryBuilders.matchPhraseQuery(Prop.START_POINT_NAME, param.getStartPointName()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getStartProvince())) {
//            qb.must(QueryBuilders.termQuery(Prop.START_PROVINCE, param.getStartProvince()));
//        }
//
//        if (param.getStartCity() != null && !param.getStartCity().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.START_CITY, param.getStartCity()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getStartDistrict())) {
//            qb.must(QueryBuilders.termQuery(Prop.START_DISTRICT, param.getStartDistrict()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getStartLocation())) {
//            qb.must(QueryBuilders.matchPhraseQuery(Prop.START_LOCATION, param.getStartLocation()));
//        }
//
//        if (param.getEndSceneType() != null && !param.getEndSceneType().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.END_SCENE_TYPE, param.getEndSceneType()));
//        }
//
//        if (param.getEndScene() != null && !param.getEndScene().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.END_SCENE, param.getEndScene()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getEndPointId())) {
//            qb.must(QueryBuilders.termQuery(Prop.END_POINT_ID, param.getEndPointId()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getEndPointName())) {
//            qb.must(QueryBuilders.matchPhraseQuery(Prop.END_POINT_NAME, param.getEndPointName()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getEndProvince())) {
//            qb.must(QueryBuilders.termQuery(Prop.END_PROVINCE, param.getEndProvince()));
//        }
//
//        if (param.getEndCity() != null && !param.getEndCity().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.END_CITY, param.getEndCity()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getEndDistrict())) {
//            qb.must(QueryBuilders.termQuery(Prop.END_DISTRICT, param.getEndDistrict()));
//        }
//
//        if (param.getUserResource() != null) {
//            qb.must(QueryBuilders.termQuery(Prop.USER_RESOURCE, param.getUserResource()));
//        }
//
//        if (!Strings.isNullOrEmpty(param.getEndLocation())) {
//            qb.must(QueryBuilders.matchPhraseQuery(Prop.END_LOCATION, param.getEndLocation()));
//        }
//
//        if (param.getLeaseNode() != null && !param.getLeaseNode().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.LEASE_NODE, param.getLeaseNode()));
//        }
//        if (param.getEntrance() != null && !param.getEntrance().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.ENTRANCE, param.getEntrance()));
//        }
//        if (param.getPayType() != null && !param.getPayType().isEmpty()) {
//            qb.must(QueryBuilders.termsQuery(Prop.PAY_TYPE, param.getPayType()));
//        }
//
//        return qb;
//    }
//
//}
