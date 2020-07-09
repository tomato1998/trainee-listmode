package com.boss.businesslist.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PurchaseList {

    @Id
    private Integer id;
    private String applicationdepartment;
    private Date applicationDate;
    private String applicant;
    private List<PurchasingGood> purchasingGoods;

}
