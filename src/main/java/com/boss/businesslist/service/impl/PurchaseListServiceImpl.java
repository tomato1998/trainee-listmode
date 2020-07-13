package com.boss.businesslist.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.businesslist.dao.PurchaseListMapper;
import com.boss.businesslist.dao.PurchasingGoodMapper;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;
import com.boss.businesslist.service.PurchaseListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li da shan
 */
@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
@Slf4j
public class PurchaseListServiceImpl implements PurchaseListService {

    @Resource
    private PurchaseListMapper purchaseListMapper;
    @Resource
    private PurchasingGoodMapper purchasingGoodMapper;

    @Override
    public int addPurchaseList(PurchaseList purchaseList) throws Exception{
        log.info("==================add=====================");
        log.info("purchaseList:"+purchaseList);
        List<PurchasingGood> purchasingGoods = purchaseList.getPurchasingGoods();
        Integer listId = purchaseListMapper.insert(purchaseList);
        for (PurchasingGood good:purchasingGoods) {
           good.setListId(listId);
           purchasingGoodMapper.insert(good);
        }
        //int i=1/0;
        return listId;
    }

    @Override
    public int deletePurchaseList(Integer id) throws Exception{
        log.info("==================delete=====================");
        log.info("PurchaseListService正在执行操作删除id："+id+"的采购清单");
        return purchaseListMapper.deleteByPrimaryKey(id);
    }

    /**
     * 默认前端已校验id
     */
    @Override
    public int updatePurchaseList(PurchaseList purchaseList) throws Exception{
        log.info("==================update=====================");
        log.info("PurchaseListService:"+purchaseList);
        int deleteResult = this.deletePurchaseList(purchaseList.getId());
        int addResult = this.addPurchaseList(purchaseList);
        if(addResult>=0&deleteResult>=0){
            return 1;
        }
        return 0;

    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PurchaseList selectPurchaseList(Integer id) throws Exception{
        log.info("==================seleteById=====================");
        log.info("PurchaseListService正在执行操作查询id："+id+"的采购清单");
        PurchaseList purchaseList = purchaseListMapper.selectByPrimaryKey(id);
        if(purchaseList==null){
            return null;
        }else {
            PurchasingGood purchasingGood = new PurchasingGood();
            purchasingGood.setListId(id);
            List<PurchasingGood> purchasingGoods = purchasingGoodMapper.select(purchasingGood);
            purchaseList.setPurchasingGoods(purchasingGoods);
            log.info("查询结果: "+purchaseList);
            return purchaseList;
        }

    }

    @Override
    public List<PurchaseList> selectAll() throws Exception{
        log.info("==================seleteAll=====================");
        List<PurchasingGood> purchasingGoods = purchasingGoodMapper.selectAll();
        List<PurchaseList> purchaseLists = purchaseListMapper.selectAll();
        if(purchaseLists.isEmpty()||purchasingGoods.isEmpty()){
            return null;
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
        return purchaseLists;
    }
}
