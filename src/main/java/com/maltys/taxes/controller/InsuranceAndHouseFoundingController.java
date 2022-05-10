package com.maltys.taxes.controller;

import com.maltys.taxes.model.InsuranceHouseFounding;
import com.maltys.taxes.model.TaxesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 五险一金参数设置
 */
@RestController
public class InsuranceAndHouseFoundingController {
    @Autowired
    TaxesData taxesData;
    @Autowired
    InsuranceHouseFounding insuranceHouseFounding;

    @RequestMapping("/param/houseFoundingRate")
    public Object setHouseFoundingRate(double rate) {
        insuranceHouseFounding.setHouseFoundingRate(rate);
        return "OK";
    }

    @RequestMapping("/param/endowmentRate")
    public Object setEndowmentRate(double rate) {
        insuranceHouseFounding.setEndowmentRate(rate);
        return "OK";
    }

    @RequestMapping("/param/medicalRate")
    public Object setMedical(double rate) {
        insuranceHouseFounding.setMedicalRate(rate);
        return "OK";
    }

    @RequestMapping("/param/employmentInjuryRate")
    public Object setEmploymentInjuryRate(double rate) {
        insuranceHouseFounding.setEmploymentInjuryRate(rate);
        return "OK";
    }

    @RequestMapping("/param/unemploymentRate")
    public Object setUnemploymentRate(double rate) {
        insuranceHouseFounding.setUnemploymentRate(rate);
        return "OK";
    }

    @RequestMapping("/param/maternityRate")
    public Object setMaternityRate(double rate) {
        insuranceHouseFounding.setMaternityRate(rate);
        return "OK";
    }

    @RequestMapping("/getTotalTaxes/month")
    public Double getTotalTaxesByMonth(Integer month) {
        return insuranceHouseFounding.getTotalDeduction(taxesData.getSalary()[month]);
    }
}
