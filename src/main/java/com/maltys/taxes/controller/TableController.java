package com.maltys.taxes.controller;

import com.maltys.taxes.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 汇总，获取相关税收信息
 * @author maltyshane
 */
@RestController
public class TableController {
    @Autowired
    MyService myService;

    @RequestMapping("/get_taxes_by_name_month")
    public Double getTaxes(String item, Integer month) {
        return myService.getTaxesByNameAndMonth(item, month);
    }

    @RequestMapping("/getSalaryAfterTaxes")
    public Double salaryAfterTaxes(Integer month) {
        return myService.getSalaryAfterTaxes(month);
    }

    @RequestMapping("/summary/month")
    public Object summaryByMonth(Integer month) {
        return myService.getTaxesSummary(month);
    }

    @RequestMapping("/getTable")
    public Object table() {
        return myService.getTable();
    }
}
