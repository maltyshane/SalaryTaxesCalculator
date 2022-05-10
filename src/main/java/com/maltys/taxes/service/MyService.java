package com.maltys.taxes.service;

import com.maltys.taxes.model.InsuranceHouseFounding;
import com.maltys.taxes.model.SpecialExpenseDeduction;
import com.maltys.taxes.model.TaxesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyService {
    @Autowired
    InsuranceHouseFounding insuranceHouseFounding;
    @Autowired
    SpecialExpenseDeduction specialExpenseDeduction;
    @Autowired
    TaxesData taxesData;

    public double getTaxesByNameAndMonth(String item, Integer month) {
        if ("insuranceAndHouseFounding".equals(item)) {
            return insuranceHouseFounding.getTotalDeduction(taxesData.getSalary()[month]);
        } else {
            Double[] doubles = specialExpenseDeduction.getMap().get(item);
            return doubles[month];
        }
    }

    public double getSalaryAfterTaxes(Integer month) {
        // 本月累计所得税额
        double accumulateTaxesCash = taxesData.getAccumulateCashForTaxes()[month];
        // 上月累计所得税额
        double accumulateTaxesCashPre = taxesData.getAccumulateCashForTaxes()[month - 1];
        // 本月应付税额
        double currentTaxesMonth = accumulateTaxesCash - accumulateTaxesCashPre;
        double salary = taxesData.getSalary()[month];
        double val = salary - currentTaxesMonth - insuranceHouseFounding.getTotalDeduction(salary);
        return val;
    }

    public Object getTaxesSummary(Integer month) {
        Map<String, Object> map = new HashMap();
        map.put("salary", taxesData.getSalary()[month]);
        map.put("rate", taxesData.getRateLevel(taxesData.getAccumulatePreDeductionCash()[month]));
        map.put("insurance", getInsuranceHouseFounding(month));
        map.put("spetialItem", getSpectialExpenseDeduction(month));
        map.put("taxes",
                taxesData.getAccumulateCashForTaxes()[month] - taxesData.getAccumulateCashForTaxes()[month - 1]);
        map.put("salaryAfterTaxes", getSalaryAfterTaxes(month));
        return map;
    }


    public Map<String, Object> getInsuranceHouseFounding(Integer month) {
        double salary = taxesData.getSalary()[month];
        double houseFounding = insuranceHouseFounding.countHouseFounding(salary);
        double endowment = insuranceHouseFounding.countEndowment(salary);
        double medical = insuranceHouseFounding.countMedical(salary);
        double unemployment = insuranceHouseFounding.countUnemployment(salary);
        double employmentInjury = insuranceHouseFounding.countEmploymentInjury(salary);
        double maternity = insuranceHouseFounding.countMaternity(salary);
        Map<String, Object> insuranceHouseFoundingMap = new HashMap();
        insuranceHouseFoundingMap.put("houseFounding", houseFounding);
        insuranceHouseFoundingMap.put("endowment", endowment);
        insuranceHouseFoundingMap.put("medical", medical);
        insuranceHouseFoundingMap.put("unemployment", unemployment);
        insuranceHouseFoundingMap.put("employmentInjury", employmentInjury);
        insuranceHouseFoundingMap.put("maternity", maternity);
        double totalDeduction = insuranceHouseFounding.getTotalDeduction(salary);
        insuranceHouseFoundingMap.put("subTotal", totalDeduction);
        return insuranceHouseFoundingMap;
    }

    public Map<String, Object> getSpectialExpenseDeduction(Integer month) {
        Map<String, Double[]> map = specialExpenseDeduction.getMap();
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, Double[]> entry : map.entrySet()) {
            String item = entry.getKey();
            Double val = entry.getValue()[month];
            resultMap.put(item, val);
        }
        resultMap.put("subTotal", specialExpenseDeduction.getTotalAccumlateSpecialExpenseDeduction(month));
        return resultMap;
    }

    public Object getTable() {
        List<Object> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            result.add(getTaxesSummary(i));
        }
        return result;
    }
}
