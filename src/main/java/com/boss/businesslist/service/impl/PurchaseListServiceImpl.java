package com.boss.businesslist.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.businesslist.dao.PurchaseListMapper;
import com.boss.businesslist.dao.PurchasingGoodMapper;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;
import com.boss.businesslist.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

@Transactional(readOnly = false,propagation = Propagation.SUPPORTS)
public class PurchaseListServiceImpl implements PurchaseListService {

    @Autowired
    private PurchaseListMapper purchaseListMapper;
    @Autowired
    private PurchasingGoodMapper purchasingGoodMapper;

    @Override
    public void addPurchaseList(PurchaseList purchaseList, List<PurchasingGood> purchasingGoods) {
        Integer listId = purchaseListMapper.insert(purchaseList);
        for (PurchasingGood good:purchasingGoods) {
            good.setListId(listId);
            purchasingGoodMapper.insert(good);
        }

    }

    @Override
    public void deletePurchaseList(Integer id) {
        purchaseListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePurchaseList(PurchaseList purchaseList, List<PurchasingGood> purchasingGoods) {
        purchaseListMapper.updateByPrimaryKey(purchaseList);
        for (PurchasingGood good:purchasingGoods) {
            good.setListId(purchaseList.getId());
            purchasingGoodMapper.updateByPrimaryKey(good);
        }
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
}
