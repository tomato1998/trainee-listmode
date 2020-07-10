package com.boss.businesslist.service;


public interface PurchaseListService {

    public Integer addPurchaseList(String addJson);
    public void deletePurchaseList(Integer id);
    public String updatePurchaseList(String updateJson) throws Exception;
    public String selectPurchaseList(Integer id);
    public String selectAll();

}
