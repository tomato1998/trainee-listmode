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

    /**
     * 直接向数据库添加采购清单
     * @param purchaseList
     * @return
     * @throws Exception
     */
    @PostMapping
    public CommonResult add(@RequestBody PurchaseList purchaseList) throws Exception {
        log.info("****************"+purchaseList);
        int result = purchaseListService.add(purchaseList);
        if(result > 0){
            return new CommonResult(200,"添加成功",result);
        }else {
            return new CommonResult(444,"添加失败",null);
        }
    }

    /**
     * 向草稿箱中添加采购草稿
     * @param purchaseList
     * @return
     * @throws Exception
     */
    @PostMapping("/draft")
    public CommonResult addDraft(@RequestBody PurchaseList purchaseList) throws Exception {
        log.info("****************"+purchaseList);
        Long result = purchaseListService.addDraft(purchaseList);
        return new CommonResult(200,"添加成功","采购清单草稿编号："+result);

    }

    /**
     * 直接删除数据库中的订单
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Integer id) throws Exception {
        int result = purchaseListService.delete(id);
        if(result > 0){
            return new CommonResult(200,"删除成功",result);
        }else {
            return new CommonResult(444,"删除失败",null);
        }
    }

    /**
     * 从草稿箱中删除采购清单草稿
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteDraft(@PathVariable("id") Long id) throws Exception {
        int result = purchaseListService.deleteDraft(id);
        if(result > 0){
            return new CommonResult(200,"草稿删除成功",result);
        }else {
            return new CommonResult(444,"草稿删除失败",null);
        }
    }

    /**
     * 直接更新数据库的采购清单
     * @param purchaseList
     * @return
     * @throws Exception
     */
    @PutMapping()
    public CommonResult update(@RequestBody PurchaseList purchaseList) throws Exception {
        int result = purchaseListService.update(purchaseList);
        if(result > 0){
            return new CommonResult(200,"更新成功",result);
        }else {
            return new CommonResult(444,"更新失败",null);
        }
    }

    /**
     * 更新草稿
     * @param id
     * @param purchaseList
     * @return
     * @throws Exception
     */
    @PutMapping("/draft/{id}")
    public CommonResult updateDraft(@PathVariable("id") Long id,@RequestBody PurchaseList purchaseList) throws Exception {
        int result = purchaseListService.updateDraft(id,purchaseList);
        if(result > 0){
            return new CommonResult(200,"草稿更新成功",result);
        }else {
            return new CommonResult(444,"草稿更新失败",null);
        }
    }

    /**
     * 根据采购清单id从数据库直接查询
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public CommonResult selectById(@PathVariable("id") Integer id) throws Exception {
        PurchaseList purchaseList = purchaseListService.selectById(id);
        if(purchaseList != null){
            return new CommonResult(200,"查询成功",purchaseList);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }

    /**
     * 根据草稿Draft的id，即did查询草稿
     * @param did
     * @return
     * @throws Exception
     */
    @GetMapping("/draft/{did}")
    public CommonResult selectDraftById(@PathVariable("did") Long did) throws Exception {
        PurchaseList purchaseList = purchaseListService.selectDraftById(did);
        if(purchaseList != null){
            return new CommonResult(200,"草稿查询成功",purchaseList);
        }else {
            return new CommonResult(444,"草稿查询失败",null);
        }
    }

    /**
     * 查询数据库所有采购清单
     * @return
     * @throws Exception
     */
    @GetMapping("/all")
    public CommonResult selectAll() throws Exception {
        List<PurchaseList> purchaseLists =  purchaseListService.selectAll();
        if(purchaseLists!=null&!purchaseLists.isEmpty()){
            return new CommonResult(200,"查询成功",purchaseLists);
        }else {
            return new CommonResult(444,"查询失败",null);
        }

    }

    @GetMapping("/draft/all")
    public CommonResult selectAllDraft() throws Exception {
        List<PurchaseList> purchaseLists =  purchaseListService.selectAllDraft();
        if(purchaseLists!=null&!purchaseLists.isEmpty()){
            return new CommonResult(200,"查询成功",purchaseLists);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }

    @GetMapping("/saveDraft/{id}")
    public CommonResult saveDraft(@PathVariable("id") Long id) throws Exception{
        int result = purchaseListService.saveDraftById(id);
        if(result > 0){
            return new CommonResult(200,"草稿成功存入数据库","存入数据库的id:"+result);
        }else {
            return new CommonResult(444,"草稿存入数据库失败",null);
        }
    }
}
