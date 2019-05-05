package com.pro.umbrella.service;

import com.pro.umbrella.common.util.CryptoUtils;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.UserMapper;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.pojo.UserExample;
import com.pro.umbrella.model.ro.UserRegisterReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User queryByUid(Long uid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUidEqualTo(uid);
        return userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
    }

    private boolean havePhone(String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTelNumberEqualTo(phone);
        return userMapper.selectByExample(userExample).size() > 0 ? true : false;
    }

    public User queryByPhone(String phone){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTelNumberEqualTo(phone);
        return userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
    }

    private Integer add( String phone, String password) {
        User user = new User();
        user.setTelNumber(phone);
        user.setPassword(password);
        WAssert.isTrue(1 == userMapper.insertSelective(user), "用户注册失败");
        return user.getId();
    }

    /**
     * 获取手机号加密后的字符串
     *
     * @param str
     * @return
     */
    public String getPhoneCode(String str) {
        return CryptoUtils.encodeBASE64(str);
    }

    /**
     * 获取加密的手机号字符串
     *
     * @param str
     * @return
     */
    public String getPhone(String str) {
        String b = "";
        try {
            b = CryptoUtils.decodeBASE64(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b.substring(0, 3) + str.substring(0, 4) + b.substring(7, 11);
    }

    /**
     * 获取解密后的真实手机号
     *
     * @param str
     * @return
     */
    public String getRealPhone(String str) {
        return CryptoUtils.decodeBASE64(str);
    }

    public String getPassWord(String str) {
        return CryptoUtils.encodeMD5(str);
    }

    private void editUid(Integer id){
        User user = new User();
        user.setId(id);
        user.setUid(Long.parseLong(id.toString()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void register(UserRegisterReq req) {
        String phone = getPhoneCode(req.getPhone());
        WAssert.isTrue(!havePhone(phone), "手机号已注册");
        Integer id = add(getPhoneCode(req.getPhone()), getPassWord(req.getPassword()));
        editUid(id);
    }
    public void signin(UserRegisterReq req) {
        String phone = getPhoneCode(req.getPhone());
        User user = WAssert.notNull(queryByPhone(phone), "该用户不存在");
        WAssert.isTrue(user.getPassword().equals(CryptoUtils.encodeMD5(req.getPassword())),"密码错误");
    }

    public User queryUserByUid(long uid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUidEqualTo(uid);
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? null : users.get(0);
    }
    @Transactional
    public void updateFinishLease(String operator, User user, Long uid, Integer time, Integer count,
                                  Date firstFinishTime, Date lastFinishTime) {
        int result =updateFinishLease(uid, user.getTotalTime() + time, user.getTotalCount() + count,
                firstFinishTime, lastFinishTime);
        WAssert.isTrue(result > 0, "用户状态更新失败,uid = " + uid);
    }
    public int updateFinishLease(long uid, int time, int count, Date firstFinishTime, Date lastFinishTime) {
        User user = this.queryUserByUid(uid);
        user.setTotalTime(time);
        user.setTotalCount(count);
        user.setFirstFinishTime(firstFinishTime);
        user.setLastFinishTime(lastFinishTime);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUidEqualTo(uid);
        return userMapper.updateByExampleSelective(user, userExample);
    }
}
