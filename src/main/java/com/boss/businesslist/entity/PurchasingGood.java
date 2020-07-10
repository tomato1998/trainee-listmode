package com.boss.businesslist.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Data
public class PurchasingGood implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer listId;
    private String name;
    private String type;
    private Integer num;
    private String placeAndUse;
    @JSONField(name="DATE OF BIRTH", format="dd/MM/yyyy")
    private Date usagedTime;

}
