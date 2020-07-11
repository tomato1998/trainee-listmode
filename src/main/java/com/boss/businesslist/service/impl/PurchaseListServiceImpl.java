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
        PurchaseList purchaseList=JSON.parseObject(addJson,PurchaseList.class);
        List<PurchasingGood> purchasingGoods = purchaseList.getPurchasingGoods();
        log.info("解析结果:"+purchaseList);
        Integer listId = purchaseListMapper.insert(purchaseList);
        for (PurchasingGood good:purchasingGoods) {
           good.setListId(listId);
            purchasingGoodMapper.insert(good);
        }
        return listId;

    }

    @Override
    public void deletePurchaseList(Integer id) {
        log.info("==================delete=====================");
        log.info("PurchaseListService正在执行操作删除id："+id+"的采购清单");
        purchaseListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String updatePurchaseList(String updateJson) throws Exception{
        log.info("==================update=====================");
        log.info("PurchaseListService已接收json:"+updateJson);
        PurchaseList purchaseList=JSON.parseObject(updateJson,PurchaseList.class);
        if (purchaseList.getId()==null){
            log.info("更新失败，未指定用户的id");
            throw new RuntimeException("用户id为空");
        }
        this.deletePurchaseList(purchaseList.getId());
        this.addPurchaseList(updateJson);
        return "更新成功";

    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public String selectPurchaseList(Integer id) {
        log.info("==================seleteById=====================");
        log.info("PurchaseListService正在执行操作查询id："+id+"的采购清单");
        PurchaseList purchaseList = purchaseListMapper.selectByPrimaryKey(id);
        if(purchaseList==null){
            return "无此采购订单";
        }else {
            PurchasingGood purchasingGood = new PurchasingGood();
            purchasingGood.setListId(id);
            List<PurchasingGood> purchasingGoods = purchasingGoodMapper.select(purchasingGood);
            purchaseList.setPurchasingGoods(purchasingGoods);
            String jsonString = JSON.toJSONString(purchaseList);
            System.out.println(jsonString);
            return jsonString;
        }

    }

    @Override
    public String selectAll() {
        log.info("==================seleteAll=====================");
        log.info("PurchaseListService正在执行操作查询所有的采购清单");
        List<PurchasingGood> purchasingGoods = purchasingGoodMapper.selectAll();
        List<PurchaseList> purchaseLists = purchaseListMapper.selectAll();
        if(purchaseLists.isEmpty()||purchasingGoods.isEmpty()){
            return "无采购清单";
        }else {
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
        }
        return JSON.toJSONString(purchaseLists);
    }
}
