package com.cx;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoxiang on 2018/3/19.
 */


public class DemoModel {
    @NotNull(message = "name不能为空")
    @Size(min = 2,max = 30)
    private String name;

    @NotNull
    @Min(value = 18,message = "buneng 小于18")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

   /* public DemoModel(@NotNull(message = "name不能为空") @Size(min = 2, max = 30) String name, @NotNull @Min(18) Integer age) {
        this.name = name;
        this.age = age;
    }*/
    public DemoModel(String name,  Integer age) { //这样是可以的。
        this.name = name;
        this.age = age;
    }
}
