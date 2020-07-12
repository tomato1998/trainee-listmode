package com.boss.businesslist.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * 采购商品类
 * @author li da shan
 */
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
    //@JSONField(name="usagedTime", format="YYYY-MM-DD HH:MM:SS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date usagedTime;

}
