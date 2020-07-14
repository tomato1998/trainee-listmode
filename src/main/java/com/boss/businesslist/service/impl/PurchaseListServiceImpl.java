package com.boss.businesslist.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.businesslist.dao.PurchaseListMapper;
import com.boss.businesslist.dao.PurchasingGoodMapper;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;
import com.boss.businesslist.service.PurchaseListService;
import com.boss.businesslist.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    @Resource
    private HttpSession session;
    private SnowFlake snowFlake=new SnowFlake(2,2);

    /**
     * 从session中取出草稿箱
     * @return
     */
    public ConcurrentHashMap<Long,PurchaseList> getPurchaseLists(){
        return  (ConcurrentHashMap<Long, PurchaseList>) session.getAttribute("purchaseLists");
    }

    @Override
    public Long addDraft(PurchaseList purchaseList) throws Exception {
        /*
            用户第一次登陆成功，会在session中创建purchaseLists用于存储草稿
         */
        ConcurrentHashMap<Long,PurchaseList> purchaseLists = getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        long id = snowFlake.nextId();
        purchaseLists.put(id,purchaseList);
        log.info("草稿箱新增后："+purchaseLists);
        session.setAttribute("purchaseLists",purchaseLists);
        return id;
    }

    @Override
    public int deleteDraft(Long id) throws Exception {
        ConcurrentHashMap<Long,PurchaseList> purchaseLists = getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        if(purchaseLists.containsKey(id)){
            purchaseLists.remove(id);
            log.info("草稿箱删除："+purchaseLists);
            session.setAttribute("purchaseLists",purchaseLists);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateDraft(Long id,PurchaseList purchaseList) throws Exception {
        ConcurrentHashMap<Long,PurchaseList> purchaseLists = getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        if(purchaseLists.containsKey(id)){
            purchaseLists.put(id,purchaseList);
            log.info("草稿箱新增后："+purchaseLists);
            session.setAttribute("purchaseLists",purchaseLists);
            return 1;
        }
        return 0;
    }

    @Override
    public List<PurchaseList> selectAllDraft() throws Exception {
        ConcurrentHashMap<Long,PurchaseList> purchaseLists =getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        List<PurchaseList> purchaseListsValues = (List<PurchaseList>) purchaseLists.values();
        return purchaseListsValues;
    }

    @Override
    public PurchaseList selectDraftById(Long id) throws Exception {
        ConcurrentHashMap<Long,PurchaseList> purchaseLists = getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        PurchaseList purchaseList = purchaseLists.get(id);
        log.info("指定id的草稿："+purchaseList);
        return purchaseList;
    }

    @Override
    public int saveDraftById(Long id) throws Exception {
        ConcurrentHashMap<Long,PurchaseList> purchaseLists = getPurchaseLists();
        log.info("草稿箱："+purchaseLists);
        PurchaseList purchaseList = purchaseLists.get(id);
        return this.add(purchaseList);
    }


    @Override
    public int add(PurchaseList purchaseList) throws Exception{
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
    public int delete(Integer id) throws Exception{
        log.info("==================delete=====================");
        log.info("PurchaseListService正在执行操作删除id："+id+"的采购清单");
        return purchaseListMapper.deleteByPrimaryKey(id);
    }

    /**
     * 默认前端已校验id
     */
    @Override
    public int update(PurchaseList purchaseList) throws Exception{
        log.info("==================update=====================");
        log.info("PurchaseListService:"+purchaseList);
        int deleteResult = this.delete(purchaseList.getId());
        int addResult = this.add(purchaseList);
        if(addResult>=0&deleteResult>=0){
            return 1;
        }
        return 0;

    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PurchaseList selectById(Integer id) throws Exception{
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
