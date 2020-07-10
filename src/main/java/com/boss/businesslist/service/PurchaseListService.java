package com.boss.businesslist.service;


import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;


import java.util.List;

public interface PurchaseListService {

    public void addPurchaseList(PurchaseList purchaseList, List<PurchasingGood> purchasingGoods);
    public void deletePurchaseList(Integer id);
    public void updatePurchaseList(PurchaseList purchaseList, List<PurchasingGood> purchasingGoods);
    public String selectPurchaseList(Integer id);

}
