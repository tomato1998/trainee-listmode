package com.boss.businesslist.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class PurchasingGood {

    private String name;
    private String type;
    private int num;
    private String placeAndUse;
    private Date usagedTime;

}
