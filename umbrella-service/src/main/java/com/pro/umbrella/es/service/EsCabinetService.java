package com.pro.umbrella.es.service;

import com.google.common.collect.Lists;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.page.Limit;
import com.pro.umbrella.api.pojo.page.Pager;
import com.pro.umbrella.es.ElasticSearchClient;
import com.pro.umbrella.es.controller.resp.EsCabinetResp;
import com.pro.umbrella.es.service.bean.EsCabinet;
import com.pro.umbrella.es.service.param.CabinetQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class  EsCabinetService {


    @Value("${es.cabinet.index.name}")
    private String INDEX_NAME;
    public static final String TYPE_NAME = "default";
    public static final Integer GROUP_SIZE = 100;

    public interface Prop {
        String CAPACITY = "capacity";
        String TRANS_STATE = "transState";
        String ID = "id";
        String CITY = "city";
        String PROVINCE = "province";
        String DISTRICT = "district";
        String CLIENT_ID = "clientId";
        String CLIENT_NAME = "clientName";
        String CAN_LEASE = "canLease";
        String CAN_RETURN = "canReturn";
        String DEVICE_ID = "deviceId";
        String IS_ONLINE = "isOnline";
        String LOCATION = "location";
        String SHOP_CODE = "shopCode";
        String POINT_ID = "pointId";
        String POINT_NAME = "pointName";
        String SCENE = "scene";
        String SCENE_TYPE = "sceneType";
        String HARD_VER = "hardVer";
        String SOFT_VER = "softVer";
        String CSQ = "csq";
        String MIGRATE_DATE = "migrateDate";
        String PUT_DATE = "putDate";
        String UNSORTED_CAP = "unsortedCapacity";
        String VBAT = "vbat";
    }

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    public void saveOrUpdate(EsCabinet cabinet) {
        IndexRequest updateRequest = new IndexRequest(INDEX_NAME, TYPE_NAME, cabinet.getId());

        updateRequest.opType(DocWriteRequest.OpType.INDEX);
        updateRequest.source(JsonUtil.toJson(cabinet), XContentType.JSON);

        long start = System.currentTimeMillis();

        elasticSearchClient.getClient().index(updateRequest);

    }
    public Pager<EsCabinetResp> queryResult(CabinetQueryParam param) {
        Pager<EsCabinet> pager = query(param);

        if (pager.getData() != null) {
            return pager.transform(EsCabinetService::transform);
        } else {
            return new Pager<>(pager.getPage(), Collections.emptyList());
        }
    }

    private static EsCabinetResp transform(EsCabinet cabinet) {
        EsCabinetResp resp = new EsCabinetResp();
        resp.setId(cabinet.getId());
        resp.setHardVer(cabinet.getHardVer());
        resp.setSoftVer(cabinet.getSoftVer());
        resp.setScene(cabinet.getScene());
        resp.setDeviceId(cabinet.getDeviceId());
        resp.setCapacity(cabinet.getCapacity());
        resp.setCsq(cabinet.getCsq());
        resp.setVbat(cabinet.getVbat());
        resp.setPutDate(cabinet.getPutDate());
        resp.setIsOnline(cabinet.getIsOnline());
        resp.setTransState(cabinet.getTransState());
        resp.setCanLease(cabinet.getCanLease());
        resp.setCanReturn(cabinet.getCanReturn());
        resp.setCity(cabinet.getCity());
        return resp;
    }


    public Pager<EsCabinet> query(CabinetQueryParam param) {
        long start = System.currentTimeMillis();

        try {
        BoolQueryBuilder qb = queryCondition(param);
        Limit limit = param.getPage().getLimit();
        SearchRequestBuilder sv = elasticSearchClient.getClient().prepareSearch(INDEX_NAME)
//                .setTypes(TYPE_NAME)
                .setQuery(qb).setFrom(limit.getOffset()).setSize(limit.getSize()).addSort(Prop.PUT_DATE, SortOrder.ASC);
        SearchResponse response = sv.get();
        SearchHits searchHits = response.getHits();
        int count = searchHits == null ? 0 : (int) searchHits.getTotalHits();
        List<EsCabinet> cabinetList;
        if (searchHits != null && searchHits.getHits() != null) {
            cabinetList = Lists.newArrayList();
            for (SearchHit hit : searchHits.getHits()) {
                EsCabinet esCabinet = JsonUtil.of(hit.getSourceAsString(), EsCabinet.class);
                if (esCabinet != null) {
                    cabinetList.add(esCabinet);
                }
            }
        } else {
            cabinetList = Collections.emptyList();
        }
        Pager.PageData page = new Pager.PageData(param.getPage().getPageNo(), param.getPage().getPageSize(), count);
        return new Pager<>(page, cabinetList);
        } finally {
        }
    }
//
//    public Map<Integer, Integer> umbrellaCabinetGroup(CabinetGroupParam param) {
//        Map<Integer, Integer> map = new HashMap<>();
//        CabinetQueryParam cabinetQueryParam = new CabinetQueryParam();
//        BeanUtils.copyProperties(param, cabinetQueryParam);
//        BoolQueryBuilder qb = queryCondition(cabinetQueryParam);
//        SearchRequestBuilder sv = elasticSearchClient.getClient().prepareSearch(INDEX_NAME).setTypes(TYPE_NAME)
//                .setQuery(qb);
//        if (param.getGroupField().contains(Prop.TRANS_STATE)) {
//            sv = sv.addAggregation(
//                    AggregationBuilders.terms(Prop.TRANS_STATE).field(Prop.TRANS_STATE).size(GROUP_SIZE));
//            SearchResponse response = sv.execute().actionGet();
//            Terms terms = response.getAggregations().get(Prop.TRANS_STATE);
//            for (Terms.Bucket entry : terms.getBuckets()) {
//                map.put(Integer.parseInt(String.valueOf(entry.getKey())),
//                        Integer.parseInt(String.valueOf(entry.getDocCount())));
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 分组查询雨伞数量
//     *
//     * @param param
//     * @return
//     */
//    public Map<String, Integer> umbrellaGroup(CabinetGroupParam param) {
//        Map<String, Integer> map = new HashMap<>();
//        CabinetQueryParam cabinetQueryParam = new CabinetQueryParam();
//        BeanUtils.copyProperties(param, cabinetQueryParam);
//        BoolQueryBuilder qb = queryCondition(cabinetQueryParam);
//        SearchRequestBuilder sv = elasticSearchClient.getClient().prepareSearch(INDEX_NAME).setTypes(TYPE_NAME)
//                .setQuery(qb);
//        if (param.getGroupField().contains(Prop.SCENE_TYPE)) {
//            sv = sv.addAggregation(AggregationBuilders.terms(Prop.SCENE_TYPE).field(Prop.SCENE_TYPE).size(GROUP_SIZE)
//                    .subAggregation(AggregationBuilders.terms(Prop.SOFT_VER).field(Prop.SOFT_VER)
//                            .size(GROUP_SIZE)));
//            SearchResponse response = sv.execute().actionGet();
//            Terms sceneTerms = response.getAggregations().get(Prop.SCENE_TYPE);
//            for (Terms.Bucket sceneEntry : sceneTerms.getBuckets()) {
//                Terms softVerTerms = sceneEntry.getAggregations().get(Prop.SOFT_VER);
//                map.put(sceneEntry.getKey().toString(), umbrellaTransform(softVerTerms));
//            }
//        }
//        if (param.getGroupField().contains(Prop.CITY)) {
//            sv = sv.addAggregation(
//                    AggregationBuilders.terms(Prop.CITY).field(Prop.CITY).size(GROUP_SIZE).subAggregation(
//                            AggregationBuilders.terms(Prop.SOFT_VER).field(Prop.SOFT_VER ).size(GROUP_SIZE)));
//            SearchResponse response = sv.execute().actionGet();
//            Terms sceneTerms = response.getAggregations().get(Prop.CITY);
//            for (Terms.Bucket sceneEntry : sceneTerms.getBuckets()) {
//                Terms softVerTerms = sceneEntry.getAggregations().get(Prop.SOFT_VER);
//                map.put(sceneEntry.getKey().toString(), umbrellaTransform(softVerTerms));
//            }
//        }
//        return map;
//    }

    /**
     * 伞柜数量转换成雨伞数量
     *
     * @param softVerTerms
     * @return
     */
    private Integer umbrellaTransform(Terms softVerTerms) {
        Integer umbrellaCount = 0;
        for (Terms.Bucket SoftVerEntry : softVerTerms.getBuckets()) {
            if (SoftVerEntry.getKey().toString().contains("blf")) {
                umbrellaCount += 12 * Integer.parseInt(String.valueOf(SoftVerEntry.getDocCount()));
            } else if (SoftVerEntry.getKey().toString().contains("frn")) {
                umbrellaCount += 15 * Integer.parseInt(String.valueOf(SoftVerEntry.getDocCount()));
            }
        }
        return umbrellaCount;
    }

    /**
     * 组装查询条件
     *
     * @param param
     * @return
     */
    private BoolQueryBuilder queryCondition(CabinetQueryParam param) {
        BoolQueryBuilder qb = QueryBuilders.boolQuery();

            if (param.getMinCap() != null && param.getMaxCap() != null) {
                qb.must(QueryBuilders.rangeQuery(Prop.CAPACITY).from(param.getMinCap()).to(param.getMaxCap()));
            }

            if (param.getTransStates() != null && !param.getTransStates().isEmpty()) {
                qb.must(QueryBuilders.termsQuery(Prop.TRANS_STATE, param.getTransStates()));
            }

            if (!Strings.isNullOrEmpty(param.getId())) {
                qb.must(QueryBuilders.termQuery(Prop.ID, param.getId()));
            }

            if (param.getCity() != null && !param.getCity().isEmpty()) {
                qb.must(QueryBuilders.termsQuery(Prop.CITY, param.getCity()));
            }


            if (param.getCanLease() != null) {
                qb.must(QueryBuilders.termQuery(Prop.CAN_LEASE, param.getCanLease()));
            }

            if (param.getCanReturn() != null) {
                qb.must(QueryBuilders.termQuery(Prop.CAN_RETURN, param.getCanReturn()));
            }

            if (!Strings.isNullOrEmpty(param.getDeviceId())) {
                qb.must(QueryBuilders.termQuery(Prop.DEVICE_ID, param.getDeviceId()));
            }

            if (param.getIsOnline() != null) {
                qb.must(QueryBuilders.termQuery(Prop.IS_ONLINE, param.getIsOnline()));
            }

            if (param.getScene() != null && !param.getScene().isEmpty()) {
                qb.must(QueryBuilders.termsQuery(Prop.SCENE, param.getScene()));
            }

            if (param.getHardVers() != null && !param.getHardVers().isEmpty()) {
                qb.must(QueryBuilders.termsQuery(Prop.HARD_VER, param.getHardVers()));
            }

            if (param.getSoftVers() != null && !param.getSoftVers().isEmpty()) {
                qb.must(QueryBuilders.termsQuery(Prop.SOFT_VER, param.getSoftVers()));
            }

            if (param.getMinCsq() != null && param.getMaxCsq() != null) {
                qb.must(QueryBuilders.rangeQuery(Prop.CSQ).from(param.getMinCsq()).to(param.getMaxCsq()));
            }

            if (param.getMinPutDate() != null && param.getMaxPutDate() != null) {
                qb.must(QueryBuilders.rangeQuery(Prop.PUT_DATE).from(param.getMinPutDate()).to(param.getMaxPutDate()));
            }

        if (param.getMinVbat() != null && param.getMaxVbat() != null) {
            qb.must(QueryBuilders.rangeQuery(Prop.VBAT).gte(param.getMinVbat()).lt(param.getMaxVbat()));
        }
        return qb;
    }

}
