package com.boss.businesslist.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.businesslist.dao.PurchaseListMapper;
import com.boss.businesslist.dao.PurchasingGoodMapper;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;
import com.boss.businesslist.service.PurchaseListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
@Slf4j
public class PurchaseListServiceImpl implements PurchaseListService {

    @Autowired
    private PurchaseListMapper purchaseListMapper;
    @Autowired
    private PurchasingGoodMapper purchasingGoodMapper;

    @Override
    public Integer addPurchaseList(String addJson) {
        log.info("==================add=====================");
        log.info("PurchaseListService已接收json:"+addJson);
        PurchaseList purchaseList = (PurchaseList) JSON.parse(addJson);
        log.info("解析结果:"+purchaseList);
        Integer listId = purchaseListMapper.insert(purchaseList);
        return listId;
//        for (PurchasingGood good:purchasingGoods) {
//            good.setListId(listId);
//            purchasingGoodMapper.insert(good);
//        }

    }

    @Override
    public void deletePurchaseList(Integer id) {
        purchaseListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String updatePurchaseList(String updateJson) throws Exception{
        log.info("==================update=====================");
        log.info("PurchaseListService已接收json:"+updateJson);
        PurchaseList purchaseList = (PurchaseList) JSON.parse(updateJson);
        if (purchaseList.getId()==null){
            log.info("更新失败，未指定用户的id");
            throw new RuntimeException("用户id为空");
        }
        purchaseListMapper.updateByPrimaryKeySelective(purchaseList);
        return "更新成功";

    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public String selectPurchaseList(Integer id) {
        PurchaseList purchaseList = purchaseListMapper.selectByPrimaryKey(id);
        PurchasingGood purchasingGood = new PurchasingGood();
        purchasingGood.setListId(id);
        List<PurchasingGood> purchasingGoods = purchasingGoodMapper.select(purchasingGood);
        purchaseList.setPurchasingGoods(purchasingGoods);
        String jsonString = JSON.toJSONString(purchaseList);
        System.out.println(jsonString);
        return jsonString;
    }

    @Override
    public String selectAll() {
        List<PurchasingGood> purchasingGoods = purchasingGoodMapper.selectAll();
        List<PurchaseList> purchaseLists = purchaseListMapper.selectAll();
        for (PurchaseList purchaseList:purchaseLists) {
            Integer listId = purchaseList.getId();
            ArrayList goods = new ArrayList();
            for (PurchasingGood purchasingGood:purchasingGoods) {
                if (purchasingGood.getListId().equals(listId)){
                    goods.add(purchasingGood);
                }
            }
            purchaseList.setPurchasingGoods(goods);
        }
        return JSON.toJSONString(purchaseLists);
    }
}
