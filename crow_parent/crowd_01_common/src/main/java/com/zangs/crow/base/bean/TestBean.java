package com.zangs.crow.base.bean;

import com.zangs.crow.base.bean.BaseModel;
import org.nutz.dao.entity.annotation.*;


@Table("test")
public class TestBean extends BaseModel {


    @Column("hhh")
    String hhh;

    public static void main(String[] args) {

    }
}
