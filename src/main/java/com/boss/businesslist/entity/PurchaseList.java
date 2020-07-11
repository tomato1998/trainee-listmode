package com.boss.businesslist.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
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
    private String applicationDepartment;
    @JSONField(name="applicationDate", format="YYYY-MM-DD HH:MM:SS")
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
