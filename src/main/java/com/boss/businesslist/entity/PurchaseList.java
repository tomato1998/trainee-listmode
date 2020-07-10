package com.boss.businesslist.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 李大山
 *
 */
@Data
@Table()
public class PurchaseList implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @NotNull
    private String applicationDepartment;
    @NotNull
    @JSONField(name="DATE OF BIRTH", format="dd/MM/yyyy")
    private Date applicationDate;
    @NotNull
    private String applicant;
    private String generalManagerApprovalOpinion;
    private String mineDirectorApprovalOpinion;
    private String departmentApprovalOpinion;
    private String warehouseVerificationOpinion;
    private String purchasingDepartmentOpinion;
    @Transient
    private List<PurchasingGood> purchasingGoods;

}
