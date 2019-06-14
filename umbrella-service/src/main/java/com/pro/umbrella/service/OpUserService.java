package com.pro.umbrella.service;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.OperationUserMapper;
import com.pro.umbrella.dao.UserMapper;
import com.pro.umbrella.model.pojo.OperationUser;
import com.pro.umbrella.model.pojo.OperationUserExample;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.pojo.UserExample;
import com.pro.umbrella.model.ro.OpUserRegisterReq;
import com.pro.umbrella.model.ro.UserInfoResp;
import com.pro.umbrella.model.ro.UserRegisterReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OpUserService {
    @Resource
    private OperationUserMapper operationUserMapper;

    public OperationUser queryById(Integer id) {
        OperationUserExample userExample = new OperationUserExample();
        userExample.createCriteria().andIdEqualTo(id);
        return operationUserMapper.selectByExample(userExample).stream().findFirst().orElse(null);
    }


    public void updateByUid(OperationUser user){
        operationUserMapper.updateByPrimaryKeySelective(user);
    }

    public List<OperationUser> query(Byte roleId,String phone){
        OperationUserExample example = new OperationUserExample();
        OperationUserExample.Criteria criteria = example.createCriteria();
        if (roleId!=null)
            criteria.andRoleEqualTo(roleId);
        if (phone!=null&&!"".equals(phone))
            criteria.andTelEqualTo(phone);
        return operationUserMapper.selectByExample(example);

    }


    public OperationUser add(Integer id, String phone, String username,Byte roleId) {
        OperationUser user = new OperationUser();
        user.setTel(phone);
        user.setUserName(username);
        user.setRole(roleId);
        user.setPassword("123456");
        WAssert.isTrue(1 == operationUserMapper.insertSelective(user), "用户新增失败");

        return operationUserMapper.selectByPrimaryKey(id);
    }
    public OperationUser delete(Integer id,Integer deleteId){
        operationUserMapper.deleteByPrimaryKey(deleteId);
        OperationUser operationUser = queryById(id);
        return operationUser;
    }

    public OperationUser signin(OpUserRegisterReq req) {
        OperationUser user = WAssert.notNull(queryByUserName(req.getUsername()), "该用户不存在");
//        WAssert.isTrue(user.getPassword().equals(CryptoUtils.encodeMD5(req.getPassword())),"密码错误");
        WAssert.isTrue(user.getPassword().equals(req.getPassword()),"密码错误");
        return user;
    }
    public OperationUser queryByUserName(String username){
        OperationUserExample operationUserExample = new OperationUserExample();
        operationUserExample.createCriteria().andUserNameEqualTo(username);
        return operationUserMapper.selectByExample(operationUserExample).stream().findFirst().orElse(null);
    }

    public OperationUser changePWD(Integer id,String pwd){
        OperationUser user = queryById(id);
        user.setPassword(pwd);
        operationUserMapper.updateByPrimaryKey(user);
        return user;
    }

}
