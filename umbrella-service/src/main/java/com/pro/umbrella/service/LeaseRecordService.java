package com.pro.umbrella.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.page.Pager;
import com.pro.umbrella.common.util.*;
import com.pro.umbrella.dao.LeaseRecordMapper;
import com.pro.umbrella.model.bo.ChargeDetail;
import com.pro.umbrella.model.bo.LeaseCost;
import com.pro.umbrella.model.bo.LeaseQueryBo;
import com.pro.umbrella.model.constants.*;
import com.pro.umbrella.model.constants.id.IDGenerator;
import com.pro.umbrella.model.constants.id.IDGeneratorSeed;
import com.pro.umbrella.model.pojo.*;
import com.pro.umbrella.model.ro.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LeaseRecordService {
    @Resource
    private LeaseRecordMapper leaseRecordMapper;
    @Resource
    private UserService userService;
    @Resource
    private GlobalConfigService globalConfigService;
    @Resource
    private UmbrellaCabinetService umbrellaCabinetService;
    @Resource
    private IDGenerator idGenerator;
    @Resource
    private CostService costService;
    @Resource
    private FeedBackService feedBackService;
    @Resource
    private UmbrellaService umbrellaService;
    @Resource
    private TradeFlowService tradeFlowService;
    @Value("${umbrella.cabinet.url.pattern}")
    private String umbrellaCabinetUrlPattern;
    private Pattern pattern;

    @PostConstruct
    public void init() {
        pattern = Pattern.compile(umbrellaCabinetUrlPattern);
    }

    public void add() {
        LeaseRecord leaseRecord = new LeaseRecord();
        leaseRecordMapper.insertSelective(leaseRecord);
    }

    @Transactional
    public LeasePayResp createTrade(long uid, String url) {
        Matcher matcher = pattern.matcher(url);
        WAssert.isTrue(matcher.matches(), "二维码错误");
        String umbrellaCabinetNumber = matcher.group(1);
        WAssert.hasLength(umbrellaCabinetNumber, "雨伞柜编号为空");
        UmbrellaCabinet umbrellaCabinet = umbrellaCabinetService.queryByCabinetId(umbrellaCabinetNumber);
//        WAssert.isTrue(umbrellaCabinet != null, "雨伞柜编号不存在");
//        WAssert.isTrue(
//                TransferStateEnums.UmbrellaCabinetTransferState.waitOrOnline(umbrellaCabinet.getTransState()),
//                "流转状态错误");
//        WAssert.isTrue(umbrellaCabinet.getOnlineState().equals(DeviceEnums.DeviceStatus.ONLINE.status),
//                "雨伞柜不在线");

//        BigDecimal deposit = NumberUtils.createBigDecimal(JsonUtil
//                .of(globalConfigService.queryByName(Constants.UMBRELLA_NEW_CHARGE).getContext(), ChargeDetail.class).getDeposit());

        // 创建租赁订单
        LeaseRecord leaseRecord = newLease(umbrellaCabinet, uid);

        // 添加检查，查看雨伞是否可以借用
//        WAssert.isTrue(leaseRecord.getLeaseState() != LeaseStateEnums.LeaseState.OVER_TIME, "租借已超时");
//
//        TradeFlow waitFlow = tradeFlowService.createTradeFlow(TypeStateEnums.OpUser.SYSTEM, uid,
//                leaseRecord.getLeaseNumber(), deposit);
        LeasePayResp leasePayResp = new LeasePayResp();
        leasePayResp.setLeaseNumber(leaseRecord.getLeaseNumber().toString());
//        leasePayResp.setPayAmount(waitFlow.getAmount().toString());
//        leasePayResp.setPayNo(waitFlow.getTradeNumber());
        leasePayResp.setUid(uid);
        return leasePayResp;
    }

    /**
     * 后台运营订单列表
     *
     * @param req
     * @return
     */
    public Pager list(OperationLeasePageReq req) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        LeaseRecordExample.Criteria criteria = leaseRecordExample.createCriteria();
        if (req.getId() != null)
            criteria.andLeaseNumberEqualTo(Long.parseLong(req.getId()));
        if (req.getUmbrellaNumber() != null)
            criteria.andUmbrellaNumberEqualTo(req.getUmbrellaNumber());
        if (req.getStartCabinetNumber() != null)
            criteria.andCabinetLendNumberEqualTo(req.getStartCabinetNumber());
        if (req.getEndCabinetNumber() != null)
            criteria.andCabinetBackNumberEqualTo(req.getEndCabinetNumber());
        if (req.getUid() != null)
            criteria.andUidEqualTo(Long.parseLong(req.getUid()));
        if (req.getLeaseState() != null)
            criteria.andLeaseStateEqualTo(Byte.parseByte(req.getLeaseState()));
        if (req.getTradeState() != null)
            criteria.andTradeStateEqualTo(Byte.parseByte(req.getTradeState()));
        if (req.getMinCreateTime() != null && req.getMaxCreateTime() != null)
            criteria.andStartTimeBetween(DateFormatUtils.parse4y2M2d2h2m2s(req.getMinCreateTime()), DateFormatUtils.parse4y2M2d2h2m2s(req.getMaxCreateTime()));
        if (req.getMinReturnTime() != null && req.getMaxReturnTime() != null)
            criteria.andEndTimeBetween(DateFormatUtils.parse4y2M2d2h2m2s(req.getMinReturnTime()), DateFormatUtils.parse4y2M2d2h2m2s(req.getMaxReturnTime()));
        if (req.getStartScene() != null)
            criteria.andStartSceneLike(req.getStartScene());
        if (req.getStartCity() != null)
            criteria.andStartCityLike(req.getStartCity());
        if (req.getEndCity() != null)
            criteria.andEndCityLike(req.getEndCity());
        if (req.getLeaseNode() != null)
            criteria.andLeaseNodeEqualTo(req.getLeaseNode());
        List<LeaseRecord> leaseRecord = leaseRecordMapper.selectByExample(leaseRecordExample);
        List<OperationLeasePageResp> pageResp = leaseRecord.stream().map(x -> {
            OperationLeasePageResp operationLeasePageResp = new OperationLeasePageResp();
            operationLeasePageResp.setId(x.getLeaseNumber().toString());
            operationLeasePageResp.setUmbrellaNumber(x.getUmbrellaNumber());
            operationLeasePageResp.setStartCabinetNumber(x.getCabinetBackNumber());
            operationLeasePageResp.setEndCabinetNumber(x.getCabinetBackNumber());
            operationLeasePageResp.setUid(x.getUid().toString());
            User user = userService.queryByUid(x.getUid());
            if (user != null) {
                operationLeasePageResp.setPhone(user.getTelNumber());
                operationLeasePageResp.setPhoneCode(user.getTelNumber());
            }
            operationLeasePageResp.setLeaseState(x.getLeaseState().intValue());
            operationLeasePageResp.setTradeState(x.getTradeState().intValue());
            operationLeasePageResp.setCreateTime(DateFormatUtils.format4y2M2d2h2m2s(x.getStartTime()));
            operationLeasePageResp.setReturnTime(DateFormatUtils.format4y2M2d2h2m2s(x.getEndTime()));
            operationLeasePageResp.setStartScene(x.getStartScene());
            operationLeasePageResp.setStartCity(x.getStartCity());
            operationLeasePageResp.setEndCity(x.getEndCity());
            operationLeasePageResp.setAmount(x.getAmount().toString());
            operationLeasePageResp.setLeaseNode(x.getLeaseNode());
            return operationLeasePageResp;
        }).collect(Collectors.toList());
        return Pager.builder(pageResp).total(pageResp.size()).current(req.getPage()).create();
    }

    /**
     * 是否有行进中订单
     *
     * @param uid
     * @return
     */
    public Long haveByUid(Long uid) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        leaseRecordExample.createCriteria().andUidEqualTo(uid).andLeaseStateEqualTo(LeaseStateEnums.LeaseState.LEASING);
        LeaseRecord leaseRecord = leaseRecordMapper.selectByExample(leaseRecordExample).stream().findFirst().orElse(null);
        return leaseRecord.getLeaseNumber();
    }

    @Transactional
    public LeaseRecord newLease(UmbrellaCabinet umbrellaCabinet, Long uid) {

        LeaseQueryBo bo = new LeaseQueryBo();
        bo.setUid(uid);
        bo.setUmbrellaCabinetNumber(umbrellaCabinet.getUmbrellaCabinetNumber());
        bo.setLeaseStates(Collections.singletonList(LeaseStateEnums.LeaseState.NOT_START));
        List<LeaseRecord> list = queryLeases(bo);
        if (CollectionUtils.isNotEmpty(list)) {
            LeaseRecord record = list.get(0);
            System.out.println(umbrellaCabinet.getUmbrellaCabinetNumber() + " :" + uid + "未开始租赁，使用该租赁信息");
            /**
             * 如果存在未开始租赁，用户多次扫码，更新相关时间，防止等待期间被定时任务改掉状态
             * 微信免押单特殊处理
             */
            Date now = new Date();
            record.setStartTime(now);
            record.setCreateTime(now);
            record.setUpdateTime(now);
            updateLease(TypeStateEnums.OpUser.SYSTEM, record,
                    Lists.newArrayList(LeaseStateEnums.LeaseState.NOT_START));
            return list.get(0);

        }

        LeaseRecord leaseRecord = new LeaseRecord();
        leaseRecord.setUid(uid);
        leaseRecord.setCabinetLendNumber(umbrellaCabinet.getUmbrellaCabinetNumber());
        leaseRecord.setAmount(new BigDecimal("0"));
        leaseRecord.setLeaseNumber(idGenerator.generate(IDGeneratorSeed.LEASE_NUMBER));
        leaseRecord.setTradeState(LeaseStateEnums.LeaseTradeState.WAIT_PAY);
        leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.NOT_START);
        leaseRecord.setStartShopCode(umbrellaCabinet.getShopCode());
        leaseRecord.setStartShopName(umbrellaCabinet.getShopName());
        leaseRecord.setStartCity(umbrellaCabinet.getCity());
        leaseRecord.setStartScene(umbrellaCabinet.getScene());
        leaseRecord.setTransState(umbrellaCabinet.getTransState());
        WAssert.isTrue(insertLease(leaseRecord) > 0, "租赁创建失败");
        return leaseRecord;
    }

    public List<LeaseRecord> queryLeases(LeaseQueryBo bo) {
        LeaseRecordExample example = new LeaseRecordExample();
        LeaseRecordExample.Criteria criteria = example.createCriteria();
        if (bo.getUid() != null) {
            criteria.andUidEqualTo(bo.getUid());
        }
        if (bo.getUmbrellaNumber() != null) {
            criteria.andUmbrellaNumberEqualTo(bo.getUmbrellaNumber());
        }
        if (bo.getUmbrellaCabinetNumber() != null) {
            criteria.andCabinetLendNumberEqualTo(bo.getUmbrellaCabinetNumber());
        }
        if (CollectionUtils.isNotEmpty(bo.getLeaseStates())) {
            criteria.andLeaseStateIn(bo.getLeaseStates());
        }
        if (CollectionUtils.isNotEmpty(bo.getTradeStates())) {
            criteria.andTradeStateIn(bo.getTradeStates());
        }
        if (bo.getCreateTimeRange() != null) {
            criteria.andCreateTimeBetween(bo.getCreateTimeRange().lowerEndpoint(),
                    bo.getCreateTimeRange().upperEndpoint());
        }
        if (bo.getUpdateTimeRange() != null) {
            criteria.andUpdateTimeBetween(bo.getUpdateTimeRange().lowerEndpoint(),
                    bo.getUpdateTimeRange().upperEndpoint());
        }
        example.setOrderByClause("id desc limit " + bo.getOffset() + "," + bo.getLimit());
        return leaseRecordMapper.selectByExample(example);
    }

    @Transactional
    public void updateLease(String operator, LeaseRecord record, List<Byte> currentLeaseState) {
        LeaseRecord leaseRecordOld = queryDetail(record.getLeaseNumber());
        int result = updateByLeaseNumberAndLeaseState(record, currentLeaseState);
        System.out.println("update result is {}" + result);
        WAssert.isTrue(result > 0, "状态更新失败");
    }

    public LeaseRecord queryDetail(Long leaseNumber) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        leaseRecordExample.createCriteria().andLeaseNumberEqualTo(leaseNumber);
        List<LeaseRecord> leaseRecords = leaseRecordMapper.selectByExample(leaseRecordExample);
        return leaseRecords.isEmpty() ? null : leaseRecords.get(0);
    }

    public int updateByLeaseNumberAndLeaseState(LeaseRecord record, List<Byte> currentLeaseStates) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        if (currentLeaseStates == null) {
            leaseRecordExample.createCriteria().andLeaseNumberEqualTo(record.getLeaseNumber());
        } else {
            leaseRecordExample.createCriteria().andLeaseNumberEqualTo(record.getLeaseNumber())
                    .andLeaseStateIn(currentLeaseStates);
        }
        int i = leaseRecordMapper.updateByExampleSelective(record, leaseRecordExample);
        return i;
    }

    public int insertLease(LeaseRecord leaseRecord) {
        int i = leaseRecordMapper.insertSelective(leaseRecord);
        return i;
    }

    public boolean updateLeaseTradeState(Long leaseNumber, Byte preState, Byte currState) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        leaseRecordExample.createCriteria().andLeaseNumberEqualTo(leaseNumber).andTradeStateEqualTo(preState);
        LeaseRecord leaseRecord = new LeaseRecord();
        leaseRecord.setLeaseNumber(leaseNumber);
        leaseRecord.setTradeState(currState);
        boolean b = leaseRecordMapper.updateByExampleSelective(leaseRecord, leaseRecordExample) == 1;
        return b;
    }

    public Void leaseNodeDataCollector(LeaseNodeEnum leaseNodeEnum, Long uid, Long leaseNumber) {
        LeaseRecord leaseRecord = queryDetail(leaseNumber);
        leaseRecord.setLeaseNode(leaseNodeEnum.getCode());
        updateByLeaseNumberAndLeaseState(leaseRecord, null);
        return null;
    }

    @Transactional
    public LeaseBasicResp startLeaseV2(long leaseNumber,long uid) {
        LeaseBasicResp leaseBasicResp = new LeaseBasicResp();
        LeaseRecord leaseRecord = queryDetail(leaseNumber);
        leaseBasicResp.setLeaseNumber(leaseRecord.getLeaseNumber().toString());
        leaseBasicResp.setUid(uid);
//        leaseBasicResp.setUmbrellaCabinetNumber(leaseRecord.getCabinetLendNumber());
        User user = userService.queryByUid(uid);
        WAssert.isTrue(user.getAccount()>6,"余额不足");
        user.setAccount(user.getAccount()-6);
        userService.updateByUid(user);
        leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.LEASING);
        leaseRecord.setTradeState(LeaseStateEnums.LeaseTradeState.PAYED);
        leaseRecordMapper.updateByPrimaryKey(leaseRecord);
//        /** 针对同一订单重复下发的情况 */
//        if (ObjectUtils.equals(leaseRecord.getLeaseState(), LeaseStateEnums.LeaseState.LEASING)) {
//            return leaseBasicResp;
//        }

//        // 订单支付状态校验
//        if (Objects.equals(leaseRecord.getTradeState(), LeaseStateEnums.LeaseTradeState.REFUNDING)
//                || Objects.equals(leaseRecord.getTradeState(), LeaseStateEnums.LeaseTradeState.REFUND)) {
//            leaseBasicResp.setTradeState(leaseRecord.getTradeState());
//            leaseBasicResp.setPayType(leaseRecord.getPayMethod());
//            return leaseBasicResp;
//        }

//        TradeFlow tradeFlow = tradeFlowService.queryTradeByLeaseAndStates(leaseNumber, ImmutableList.of(
//                LeaseStateEnums.TradeFlowState.PAYED, LeaseStateEnums.TradeFlowState.AUTH_FREE_SUCC));
//
//        WAssert.notNull(tradeFlow, "未收到支付回调");
//
//        // 看完操作介绍后、取伞前检查 LOG
//        leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_CHECK_FROM_GUIDE,
//                leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//
//        UmbrellaCabinet cabinet = WAssert.notNull(
//                umbrellaCabinetService.queryByCabinetId(leaseRecord.getCabinetLendNumber()), "雨伞柜编号不存在");
//
//        if (!umbrellaCabinetService.isOnline(cabinet.getUmbrellaCabinetNumber())) {
//            leaseBasicResp.setCabinetStatus(TypeStateEnums.CabinetStatus.OFFLINE);

//            if (Objects.equals(TypeStateEnums.OperateStatus.NORMAL, retryReason)) {
//                // 检查时发现伞柜不在线 LOG
//                leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_CHECK_OFFLINE,
//                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//            } else {
//                // 伞柜不在线重试 LOG
//                leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_OFFLINE_RETRY,
//                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//            }
//
//            return leaseBasicResp;
//        }
//
//        // 如果伞柜正在出伞，返回错误
//        if (Objects.equals(TypeStateEnums.OccupyState.OCCUPYING, cabinet.getOccupyState())) {
//            // 伞柜占用中
//            leaseBasicResp.setCabinetStatus(TypeStateEnums.CabinetStatus.OCCUPYING);
//            if (Objects.equals(TypeStateEnums.OperateStatus.NORMAL, retryReason)) {
//                // 检查时发现伞柜被占用
//                leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_CHECK_LOCKED,
//                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//            } else {
//                // 伞柜占用重试
//                leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_LOCKED_RETRY,
//                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//            }
//            return leaseBasicResp;
//        }
//
//        // TODO 更新伞柜状态失败的情况，这里没有处理
//        // 更新雨伞柜使用状态
//        umbrellaCabinetService.updateUmbrellaCabinetOccupyState(leaseRecord.getCabinetLendNumber(),
//                TypeStateEnums.OccupyState.NOT_OCCUPY, TypeStateEnums.OccupyState.OCCUPYING);
//
//        leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.LEASING);

        // 更新租赁状态为预租赁
//        if (Objects.equals(TypeStateEnums.OperateStatus.NORMAL, retryReason)) {
//            int result = updateByLeaseNumberAndLeaseState(leaseRecord, Lists
//                    .newArrayList(LeaseStateEnums.LeaseState.NOT_START, LeaseStateEnums.LeaseState.OVER_TIME));
//            System.out.println("update result is {}" + result);
//            WAssert.isTrue(result > 0, "状态更新失败");
//        } else {
//            // 重试需重置订单状态
//            LeaseRecord leaseRetryRecord = queryDetail(leaseNumber);
//            leaseRetryRecord.setLeaseState(LeaseStateEnums.LeaseState.LEASING);
////            updateLease(TypeStateEnums.OpUser.UMBRELLA, leaseRetryRecord,
//                    Lists.newArrayList(LeaseStateEnums.LeaseState.NOT_START,
//                            LeaseStateEnums.LeaseState.LEASING, LeaseStateEnums.LeaseState.LEASE_FAIL,
//                            LeaseStateEnums.LeaseState.OVER_TIME,
//                            LeaseStateEnums.LeaseState.UMBRELLA_NOT_EXIST));
//        }

        // 出伞
        // 下发雨伞弹出指令
//        try {
//            taskExecute.execute(() -> {
//                boolean flag = umbrellaDeviceService.requestLeaseDevice(cabinet.getDeviceId(),
//                        OperationCallerEnums.WORM.operatedName, String.valueOf(leaseNumber));
//
//                // 后端向伞柜下发出伞指令
//                leaseUmbrellaBiz.leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_UMBRELLA_OUTPUT_START,
//                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
//                if (!flag) {
//                    LOGGER.error("指令下发失败，umbrellaCabinetNumber={}", leaseRecord.getCabinetLendNumber());
//                    umbrellaCallbackBiz.openFail(leaseRecord.getCabinetLendNumber(),
//                            LeaseStateEnums.LeaseState.OVER_TIME, new Date());
//                }
//                // 区分下发的软件版本
//                jedisClient.setex(Constants.UMBRELLACABINET_VERSION + leaseNumber, cacheTime,
//                        Constants.VERSION_SECOND);
//            });
//            System.out.println("下发雨伞柜弹出指令，umbrellaCabinetNumber={}", leaseRecord.getCabinetLendNumber());
//        } catch (Exception e) {
//            LOGGER.error("雨伞柜{}指令下发异常", leaseRecord.getCabinetLendNumber());
////        }
//        UmbrellaCabinet umbrellaCabinet = new UmbrellaCabinet();
//        umbrellaCabinet.setUmbrellaCabinetNumber(cabinet.getUmbrellaCabinetNumber());
//        umbrellaCabinet.setOccupyState(TypeStateEnums.CabinetStatus.NORMAL);
//        umbrellaCabinetService.updateByCabinetId(umbrellaCabinet);
//
//        Message message = MessageBuilder.create().withTopic(wmqLeaseSnapshot)
//                .withMessageId(UUID.randomUUID().toString())
//                .withAttr(OrderSnapshotEnum.MessageParam.LEASE_NUMBER.getParam(), String.valueOf(leaseNumber))
//                .withAttr(OrderSnapshotEnum.MessageParam.OP_TYPE.getParam(), String.valueOf(OrderSnapshotEnum.OpType.LEND.getOpType())).build();
//
//        producer.send(message);
        return leaseBasicResp;
    }

    public int updateByLeaseNumberAndLeaseTradeState(LeaseRecord record, List<Byte> currentLeaseState) {
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        leaseRecordExample.createCriteria().andLeaseNumberEqualTo(record.getLeaseNumber())
                .andTradeStateIn(currentLeaseState);
        int i = leaseRecordMapper.updateByExampleSelective(record, leaseRecordExample);
        return i;
    }


    @Transactional
    public String endLease(Long uid, Long leaseNumber, String umbrellaCabinetNumber) {
        LeaseRecord leaseRecord = queryDetail(leaseNumber);
        leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.END);
        leaseRecord.setCabinetBackNumber(umbrellaCabinetNumber);
        leaseRecordMapper.updateByPrimaryKey(leaseRecord);
//        System.out.println("结束租赁数据:{}" + JsonUtil.of(leaseRecord));
//
//        // 设置结束时间
//        leaseRecord.setEndTime(time);
//
//        if (umbrellaCabinetNumber != null) {
//            leaseRecord.setCabinetBackNumber(umbrellaCabinetNumber);
//            UmbrellaCabinet umbrellaCabinet = umbrellaCabinetService.queryByCabinetId(umbrellaCabinetNumber);
//            if (umbrellaCabinet != null) {
//                leaseRecord.setEndShopCode(umbrellaCabinet.getShopCode());
//                leaseRecord.setEndShopName(umbrellaCabinet.getShopName());
//                leaseRecord.setEndCity(umbrellaCabinet.getCity());
//            }
//        }
//        leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.END);
//        // 此处需要在交易流水中查询已经支付或者信用免押的交易流水号
//        TradeFlow tradeFlow = tradeFlowService.queryTradeByLeaseAndStates(leaseRecord.getLeaseNumber(),
//                Lists.newArrayList(LeaseStateEnums.TradeFlowState.PAYED, LeaseStateEnums.TradeFlowState.AUTH_FREE_SUCC));
//        WAssert.isTrue(tradeFlow != null, "未找到交易状态为[已预支付或者免押]的交易流水号，订单号=" + leaseRecord.getLeaseNumber());
//        if (endState == TypeStateEnums.EndType.NORMAL) {
//            LeaseCost leaseCost = costService.leaseRecordCost(leaseRecord, time);
//            leaseRecord.setTotalAmount(leaseCost.getTotalAmount());
//            leaseRecord.setReduceAmount(leaseCost.getReduceAmount());
//            leaseRecord.setAmount(leaseRecord.getTotalAmount().subtract(leaseRecord.getReduceAmount())
//                    .min(tradeFlow.getAmount()));
//        } else {
//            leaseRecord.setTotalAmount(tradeFlow.getAmount());
//            leaseRecord.setReduceAmount(BigDecimal.ZERO);
//            leaseRecord.setAmount(tradeFlow.getAmount());
//            if (endState == TypeStateEnums.EndType.EXCEPT) {
//                leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.DISCARD_END);
//            } else if (endState == TypeStateEnums.EndType.SELL) {
//                leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.SELL_END);
//                List<String> umbrellaNumbers = Collections.singletonList(leaseRecord.getUmbrellaNumber());
//                umbrellaService.updateUmbrellaTransState(umbrellaNumbers,
//                        TransferStateEnums.UmbrellaTransferState.SELLED_BUY);
//            } else {
//                leaseRecord.setLeaseState(LeaseStateEnums.LeaseState.OVER_END);
//            }
//        }
//
//        /**
//         * 更新租赁信息
//         */
//        leaseRecord.setUpdateTime(new Date());
//        leaseRecord.setArchivedAmount(leaseRecord.getAmount());
//        updateLease(operator, leaseRecord, Collections.singletonList(LeaseStateEnums.LeaseState.LEASING));
//
//        /** 更新用户的租赁总次数和总时间 */
//        User user = userService.queryByUid(leaseRecord.getUid());
//        if (user == null) {
//            WAssert.isTrue(true, "租赁uid找不到用户信息");
//        }
//        Date firstFinishTime = null;
//        if (user.getFirstFinishTime() == null || user.getFirstFinishTime().compareTo(Defaults.DATE) == 0) {
//            firstFinishTime = leaseRecord.getEndTime();
//        }
//        userService.updateFinishLease(operator, user, leaseRecord.getUid(),
//                DateFormatUtils.diffSecond(leaseRecord.getStartTime(), leaseRecord.getEndTime()) / 60, 1,
//                firstFinishTime, leaseRecord.getEndTime());
//
//
//        /** 部分退款操作 **/
//        tradeFlowService.refundTradeFlow(TypeStateEnums.OpUser.PAY, tradeFlow.getTradeNumber(),
//                MoneyUtils.of(tradeFlow.getAmount()).minus(MoneyUtils.of(leaseRecord.getAmount())),
//                LeaseStateEnums.RefundType.REFUND_PREPAY);
//
//        RefundBasicResp refundBasicResp = new RefundBasicResp();
//        refundBasicResp.setPayNo(tradeFlow.getTradeNumber());
//        refundBasicResp.setAmount(tradeFlow.getRefundPartAmount());
        return uid.toString();
    }

    @Transactional
    public String updateFaultState(OperationRefundUpdateReq req) {
        String message = "operate_success";

        Long leaseNumber = NumberUtils.toLong(req.getLeaseNumber());
        byte newState = TypeStateEnums.FeedBackState.SOLVED;
        LeaseRecord leaseRecord = queryDetail(leaseNumber);
        switch (req.getStatus()) {
            case OperationMethodState.END_LEASE_OPERATION:
//                endLease("operator", leaseNumber);
                leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_OPERATE_CLOSED,
                        leaseRecord.getUid(), leaseRecord.getLeaseNumber());
                break;
            case OperationMethodState.REFUND_OPERATION:
                WAssert.notNull(req.getRefundAmount(), "退款金额不能为空");
                updateRefundLease("operator", leaseRecord, req.getRefundAmount());
                break;
            case OperationMethodState.REJECT_OPERATION:
                newState = TypeStateEnums.FeedBackState.REJECT;
                break;
            default:
                message = "operate_fail";
                break;
        }

        // 更新状态工单状态值为已解决
        if ("operate_success".equals(message)) {
            Feedback feedback = new Feedback();
            feedback.setLeaseId(leaseNumber);
            feedback.setState(newState);
            feedback.setSolveType(req.getStatus().byteValue());
            feedback.setUpdateTime(new Date());
            feedBackService.updateState(feedback);
        }
        return message;
    }

//    @Transactional(rollbackFor = Exception.class)
//    public void endLease(String operator, Long leaseNumber) {
//        LeaseRecord leaseRecord = WAssert.notNull(queryDetail(leaseNumber), "找不到租赁订单");
//        /* 更新雨伞状态为暂存 */
//        byte umbrellaTrans = TransferStateEnums.UmbrellaTransferState.STORAGING;
//        try {
//            umbrellaService.updateUmbrellaInfo(operator, leaseRecord.getUmbrellaNumber(),
//                    leaseRecord.getCabinetLendNumber(), umbrellaTrans);
//        } catch (Throwable e) {
//            System.out.println("结束订单-更新雨伞状态异常,伞柜号{" + leaseRecord.getCabinetLendNumber() + "},雨伞号{}" +
//                    leaseRecord.getUmbrellaNumber());
//        }
//        // 结束租赁
//        endLease(operator, leaseRecord, Constants.DEFAULT_CABINET, new Date());
//    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRefundLease(String operator, LeaseRecord leaseRecord, double amount) {
        TradeFlow tradeFlow = tradeFlowService.queryNewestInStateForLease(leaseRecord.getLeaseNumber(),
                Lists.newArrayList(LeaseStateEnums.TradeFlowState.PAYED, LeaseStateEnums.TradeFlowState.PARTREFUND));
        WAssert.notNull(tradeFlow, "租借交易状态和流水交易状态不符合，请刷新重新查看.");

        // 默认退全款
        BigDecimal refundAmount = tradeFlow.getAmount().subtract(tradeFlow.getRefundPrepayAmount());
        byte refundType = LeaseStateEnums.RefundType.REFUND_FEE;

        BigDecimal targetRefundAmount = BigDecimal.valueOf(amount);

        BigDecimal totalRefundAmount = tradeFlow.getRefundPartAmount();
        if (totalRefundAmount == null) {
            totalRefundAmount = BigDecimal.ZERO;
        }

        int compare = refundAmount.subtract(targetRefundAmount).subtract(totalRefundAmount).compareTo(BigDecimal.ZERO);
        WAssert.isTrue(compare >= 0, "退款金额错误");

        // 有过退款 或者 退款金额 小于 订单金额 - 历史退款金额
        if (compare > 0 || totalRefundAmount.compareTo(BigDecimal.ZERO) > 0) {
            refundAmount = targetRefundAmount;
            refundType = LeaseStateEnums.RefundType.REFUND_PART;
        }

        LeaseNodeEnum leaseNodeEnum;
        if (leaseRecord.getLeaseNode().equals(LeaseNodeEnum.WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL.getCode())) {
            leaseNodeEnum = refundType == LeaseStateEnums.RefundType.REFUND_FEE
                    ? LeaseNodeEnum.WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL_FREE
                    : LeaseNodeEnum.WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL_PART;
        } else {
            leaseNodeEnum = refundType == LeaseStateEnums.RefundType.REFUND_FEE
                    ? LeaseNodeEnum.WXAPP_UMBRELLA_OPERATE_CLOSED_ALL_REFUND
                    : LeaseNodeEnum.WXAPP_UMBRELLA_OPERATE_CLOSED_PART_REFUND;
        }

        leaseNodeDataCollector(leaseNodeEnum, leaseRecord.getUid(),
                leaseRecord.getLeaseNumber());

        tradeFlowService.refundTradeFlow(operator, tradeFlow.getTradeNumber(), MoneyUtils.of(refundAmount), refundType);

    }
    public List<LeaseRecord> listByUid(long uid){
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        leaseRecordExample.createCriteria().andUidEqualTo(uid);
        List<LeaseRecord> leaseRecords = leaseRecordMapper.selectByExample(leaseRecordExample);
        return leaseRecords;
    }
    public LeaseDetailResp detail(Long leaseNumber) {
                LeaseRecord leaseRecord = WAssert.notNull(queryDetail(leaseNumber), "行程不存在");
                Date endTime = leaseRecord.getEndTime().compareTo(leaseRecord.getStartTime()) >= 0
                        ? leaseRecord.getEndTime()
                        : new Date();
                LeaseDetailResp leaseDetailResp = new LeaseDetailResp();
                leaseDetailResp
                        .setLeaseTime(DateUtil.formatDuring(endTime.getTime() - leaseRecord.getStartTime().getTime()));
                leaseDetailResp.setLeaseState(leaseRecord.getLeaseState());

                leaseDetailResp.setUmbrellaNumber(leaseRecord.getUmbrellaNumber());
                leaseDetailResp.setTradeState(leaseRecord.getTradeState());
                leaseDetailResp.setStartTime(DateFormatUtils.format4y2M2d2h2m2s(leaseRecord.getStartTime()));
                leaseDetailResp.setCabinetLendNumber(leaseRecord.getCabinetLendNumber());
                leaseDetailResp.setLeaseTimeUnit("");
                leaseDetailResp.setEndTime(DateFormatUtils.format4y2M2d2h2m2s(endTime));

                /** 租赁结束前根据时间计算费用 */
//                if (LeaseStateEnums.LeaseState.endContains(leaseRecord.getLeaseState())) {
//                    leaseDetailResp.setLeaseCost(costService.getLeaseCost(leaseRecord));
//                } else {
//                    leaseDetailResp
//                            .setLeaseCost(costService.leaseRecordCost(leaseRecord, new Date()));
//                }
                return leaseDetailResp;
    }


}
