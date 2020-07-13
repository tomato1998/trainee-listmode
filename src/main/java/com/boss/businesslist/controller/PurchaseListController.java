package com.boss.businesslist.controller;


import com.boss.businesslist.entity.CommonResult;
import com.boss.businesslist.entity.PurchaseList;
import com.boss.businesslist.service.PurchaseListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 采购清单控制器
 * @author li da shan
 * content:    Postman测试Json
 * {
 *     "id": 1,
 *     "applicationDepartment": "人事部",
 *     "applicationDate": "2020-07-11 12:00:00",
 *     "applicant": "tomato",
 *     "generalManagerApprovalOpinion": "同意",
 *     "mineDirectorApprovalOpinion": "同意",
 *     "departmentApprovalOpinion": "同意",
 *     "warehouseVerificationOpinion": "同意",
 *     "purchasingDepartmentOpinion": "同意",
 *     "purchasingGoods": [
 *         {
 *             "id": null,
 *             "listId": null,
 *             "name": "挖掘机",
 *             "type": "工程器械",
 *             "num": 99,
 *             "placeAndUse": "福州",
 *             "usagedTime": "2020-09-01 12:00:00"
 *         }
 *     ]
 * }
 *
 */
@RestController
@RequestMapping("/purchaseList")
@Slf4j
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;


    @PostMapping()
    public CommonResult addPurchaseList(@RequestBody PurchaseList purchaseList) throws Exception {
        log.info("****************"+purchaseList);
        int result = purchaseListService.addPurchaseList(purchaseList);
        if(result > 0){
            return new CommonResult(200,"添加成功",result);
        }else {
            return new CommonResult(444,"添加失败",null);
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult deletePurchaseList(@PathVariable("id") Integer id) throws Exception {
        int result = purchaseListService.deletePurchaseList(id);
        if(result > 0){
            return new CommonResult(200,"删除成功",result);
        }else {
            return new CommonResult(444,"删除失败",null);
        }
    }

    @PutMapping("/{id}")
    public CommonResult updatePurchaseList(@RequestBody PurchaseList purchaseList) throws Exception {
        int result = purchaseListService.updatePurchaseList(purchaseList);
        if(result > 0){
            return new CommonResult(200,"更新成功",result);
        }else {
            return new CommonResult(444,"更新失败",null);
        }
    }

    @GetMapping("/{id}")
    public CommonResult selectPurchaseList(@PathVariable("id") Integer id) throws Exception {
        PurchaseList purchaseList = purchaseListService.selectPurchaseList(id);
        if(purchaseList != null){
            return new CommonResult(200,"查询成功",purchaseList);
        }else {
            return new CommonResult(444,"查询失败",null);
        }

    }

    @GetMapping("/all")
    public CommonResult selectAll() throws Exception {
        List<PurchaseList> purchaseLists =  purchaseListService.selectAll();
        if(purchaseLists!=null&!purchaseLists.isEmpty()){
            return new CommonResult(200,"查询成功",purchaseLists);
        }else {
            return new CommonResult(444,"查询失败",null);
        }

    }
}
