package com.boss.businesslist.service;

import com.boss.businesslist.entity.PurchaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购清单业务层接口
 * @author li da shan
 */
public interface PurchaseListService {

    /**
     * 保存采购清单草稿，暂不提交采购清单至数据库
     * @param purchaseList
     * @return 草稿的编号
     * @throws Exception
     */
    public Long addDraft(PurchaseList purchaseList)throws Exception;


    /**
     * 删除采购清单草稿
     * @param id
     * @return 1 删除草稿，0没有此编号订单
     * @throws Exception
     */
    public int deleteDraft(Long id) throws Exception;

    /**
     * 更新草稿
     * @param purchaseList
     * @return 1 更新成功，0没有此编号订单
     * @throws Exception
     */
    public int updateDraft(Long id,PurchaseList purchaseList) throws Exception;


    /**
     * 查询所有的草稿
     * @return 返回内存中所有草稿的id
     * @throws Exception
     */
    public List<PurchaseList> selectAllDraft() throws Exception;

    /**
     * 根据草稿id查询草稿
     * @param id
     * @return
     * @throws Exception
     */
    public PurchaseList selectDraftById(Long id) throws Exception;

    /**
     * 把指定id的草稿正式存入数据库
     * @param id
     * @return 成功返回一个大于0的存入数据库的清单id
     * @throws Exception
     */
    public int saveDraftById(Long id)throws Exception;

    /**
     * 新增一个采购清单到数据库
     * @param purchaseList 新增采购清单
     * @return 插入数据库的订单编号
     */
    public int add(PurchaseList purchaseList) throws Exception;

    /**
     * 根据采购清单编号删除订单
     * @param id 采购清单编号
     * @return 删除是否成功
     */
    public int delete(Integer id) throws Exception;

    /**
     * 更新修改采购清单
     * @param purchaseList 修改采购清单的json
     * @return 更新是否成功
     */
    public int update(PurchaseList purchaseList) throws Exception;

    /**
     * 根据采购清单编号查询指定的采购清单
     * @param id 清单编号
     * @return 查询到的采购清单信息的json
     */
    public PurchaseList selectById(Integer id) throws Exception;

    /**
     * 查询数据库中所有的采购清单信息
     * @return 所有采购清单
     */
    public List<PurchaseList> selectAll() throws Exception;

}
