package com.boss.businesslist.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购清单类
 * @author li da shan
 */

@Data
public class PurchaseList implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String applicationDepartment;


    //@JSONField(name="applicationDate", format="dd/MM/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applicationDate;
    private String applicant;
    private String generalManagerApprovalOpinion;
    private String mineDirectorApprovalOpinion;
    private String departmentApprovalOpinion;
    private String warehouseVerificationOpinion;
    private String purchasingDepartmentOpinion;
    @Transient
    private List<PurchasingGood> purchasingGoods;

}
