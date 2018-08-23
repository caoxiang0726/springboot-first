package com.cx.utils;

import com.cx.DemoModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by caoxiang on 2018/3/19.
 */
public class HiberValidator {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        DemoModel model = new DemoModel(null, 17);

        Set<ConstraintViolation<DemoModel>> constraintViolations  = validator.validate(model);
        System.out.println(constraintViolations.size());//这里是违法限制的数量
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<DemoModel> constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
        }

        /**
         * 约束条件的分组
         在JSR 303 中定义了group的概念，用定义的接口类来标识，
         在上面的Person结构体定义的例子中可以看出有个group属性，该属性上有两个约束，
         分别是@NotNull(groups = Intf1.class) 和@Size(min = 1, max = 3, groups = Intf2.class)
         */



    }
}
