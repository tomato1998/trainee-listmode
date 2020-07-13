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
     * 新增一个采购清单
     * @param purchaseList 新增采购清单
     * @return 插入数据库的订单编号
     */
    public int addPurchaseList(PurchaseList purchaseList) throws Exception;

    /**
     * 根据采购清单编号删除订单
     * @param id 采购清单编号
     * @return 删除是否成功
     */
    public int deletePurchaseList(Integer id) throws Exception;

    /**
     * 更新修改采购清单
     * @param purchaseList 修改采购清单的json
     * @return 更新是否成功
     */
    public int updatePurchaseList(PurchaseList purchaseList) throws Exception;

    /**
     * 根据采购清单编号查询指定的采购清单
     * @param id 清单编号
     * @return 查询到的采购清单信息的json
     */
    public PurchaseList selectPurchaseList(Integer id) throws Exception;

    /**
     * 查询数据库中所有的采购清单信息
     * @return 所有采购清单
     */
    public List<PurchaseList> selectAll() throws Exception;

}
