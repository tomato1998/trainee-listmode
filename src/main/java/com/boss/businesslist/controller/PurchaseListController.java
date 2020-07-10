package com.boss.businesslist.controller;

import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.entity.PurchasingGood;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/purchaseList")
public class PurchaseListController {

    @PostMapping()
    public String addPurchaseList(@Validated PurchaseList purchaseList,
                                  List<PurchasingGood> purchasingGoods){

        return "添加成功，订单id:";
    }

    @DeleteMapping("/{id}")
    public int deletePurchaseList(@PathVariable("id") Integer id){

        return 1;
    }

    @PutMapping("/{id}")
    public String updatePurchaseList(@PathVariable("id") Integer id,
                                     @Validated PurchaseList purchaseList,
                                     List<PurchasingGood> purchasingGoods){

        return "更新成功";
    }

    @GetMapping("/{id}")
    public String selectPurchaseList(@PathVariable("id") Integer id){

        return "";

    }
}
