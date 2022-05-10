package com.maltys.taxes.controller;

import com.maltys.taxes.model.SpecialExpenseDeduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 按专项扣除项目和月份设置免税额度
 * @author maltyshane
 */
@RestController
public class SpectialExpenseDeductionController {
    @Autowired
    SpecialExpenseDeduction specialExpenseDeduction;

    @RequestMapping("/setExpenseItem")
    public Object setSpectialExpenseItem(String item, Double expense, Integer month) {
        Double[] doubles = specialExpenseDeduction.getMap().get(item);
        doubles[month] = expense;
        return "OK";
    }
}
