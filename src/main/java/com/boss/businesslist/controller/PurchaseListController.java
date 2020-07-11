package com.boss.businesslist.controller;


import com.boss.businesslist.service.PurchaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 采购清单控制器
 * @author li da shan
 */
@RestController
@RequestMapping("/purchaseList")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;


    @PostMapping()
    public String addPurchaseList(@RequestBody String addJson){
        Integer id = purchaseListService.addPurchaseList(addJson);
        return "add PurchaseList successfully , purchaseList id is"+id;
    }

    @DeleteMapping("/{id}")
    public String deletePurchaseList(@PathVariable("id") Integer id){
        purchaseListService.deletePurchaseList(id);
        return "delete PurchaseList successfully , id: "+id;
    }

    @PutMapping("/{id}")
    public String updatePurchaseList(@RequestBody String updateJson) throws Exception {
        purchaseListService.updatePurchaseList(updateJson);
        return "update PurchaseList successfully";
    }

    @GetMapping("/{id}")
    public String selectPurchaseList(@PathVariable("id") Integer id){
        return purchaseListService.selectPurchaseList(id);

    }

    @GetMapping("/all")
    public String selectAll(){
        return purchaseListService.selectAll();

    }
}
