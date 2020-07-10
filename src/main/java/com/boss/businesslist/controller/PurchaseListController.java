package com.boss.businesslist.controller;


import com.boss.businesslist.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/purchaseList")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;


    @PostMapping()
    public String addPurchaseList(String addJson){
        Integer id = purchaseListService.addPurchaseList(addJson);
        return "添加成功,用户id为"+id;
    }

    @DeleteMapping("/{id}")
    public String deletePurchaseList(@PathVariable("id") Integer id){
        purchaseListService.deletePurchaseList(id);
        return "删除id: "+id+"的用户";
    }

    @PutMapping("/{id}")
    public String updatePurchaseList(String updateJson) throws Exception {
        purchaseListService.updatePurchaseList(updateJson);
        return "更新成功";
    }

    @GetMapping("/{id}")
    public String selectPurchaseList(@PathVariable("id") Integer id){
        String purchaseList = purchaseListService.selectPurchaseList(id);
        return purchaseList;

    }
}
