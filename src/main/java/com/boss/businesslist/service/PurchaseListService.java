package com.boss.businesslist.service;

/**
 * 采购清单业务层接口
 * @author li da shan
 */
public interface PurchaseListService {

    /**
     * 新增一个采购清单
     * @param addJson 新增采购清单的json数据
     * @return 插入数据库的订单编号
     */
    public Integer addPurchaseList(String addJson);

    /**
     * 根据采购清单编号删除订单
     * @param id 采购清单编号
     */
    public void deletePurchaseList(Integer id);

    /**
     * 更新修改采购清单
     * @param updateJson 修改采购清单的json
     * @throws Exception 未查询到要修改的采购清单编号
     */
    public void updatePurchaseList(String updateJson) throws Exception;

    /**
     * 根据采购清单编号查询指定的采购清单
     * @param id 清单编号
     * @return 查询到的采购清单信息的json
     */
    public String selectPurchaseList(Integer id);

    /**
     * 查询数据库中所有的采购清单信息
     * @return 所有采购清单的json数据
     */
    public String selectAll();

}
