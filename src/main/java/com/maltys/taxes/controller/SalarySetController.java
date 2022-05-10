package com.maltys.taxes.controller;

import com.maltys.taxes.model.TaxesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工资设置
 * @author maltyshane
 */
@RestController
public class SalarySetController {
    @Autowired
    TaxesData taxesData;

    /**
     * 单独设置某月工资
     * @param month
     * @param salary
     * @return
     */
    @RequestMapping("/salary/month")
    public Object setSalaryOfMonth(Integer month, Double salary) {
        taxesData.getSalary()[month] = salary;
        taxesData.reCalculate();
        return "OK";
    }

    /**
     * 设置每月工资
     * @param salary
     * @return
     */
    @RequestMapping("/salary/permonth")
    public Object setSalaryPerMonth(Double salary) {
        for (int i = 1; i <= 12; i++) {
            taxesData.getSalary()[i] = salary;
        }
        taxesData.reCalculate();
        return "OK";
    }

    /**
     * 每月工资都不同，自行设置
     * @param salarys
     * @return
     */
    @RequestMapping("/salary/details")
    public Object setSalaryPerMonth(Double[] salarys) {
        System.arraycopy(salarys, 0, taxesData.getSalary(), 1, salarys.length);
        taxesData.reCalculate();
        return "OK";
    }

}
