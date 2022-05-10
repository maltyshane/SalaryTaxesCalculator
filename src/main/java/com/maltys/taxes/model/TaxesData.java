package com.maltys.taxes.model;

import com.maltys.taxes.constant.Constant;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Data
public class TaxesData {
    @Autowired
    Constant constant;
    @Autowired
    InsuranceHouseFounding insuranceAndHouseFounding;
    @Autowired
    SpecialExpenseDeduction specialExpenseDeduction;
    /**
     * 当月工资
     */
    private double[] Salary = new double[13];
    /**
     * 累计工资
     */
    private double[] AccumulateSalary = new double[13];

    /**
     * 累计法定扣除项--五险一金累计
     */
    private double[] AccumulateHouseFoundingDeductionCash = new double[13];
    /**
     * 专项附加扣除
     */
    private double[] AccumulateSpecialExpenseDeduction = new double[13];
    /**
     * 5000起征的纳税门槛--累计扣除
     */
    private double[] Accumulate5000Deduction = new double[13];

    /**
     * 累计应纳税所得额--累计应该纳税的那部分薪资
     */
    private double[] AccumulatePreDeductionCash = new double[13];
    /**
     * 累计应纳所得税额--累计应该交多少税
     */
    private double[] AccumulateCashForTaxes = new double[13];

    /**
     * 税后薪资
     */
    private double[] SalaryAfterTaxes = new double[13];

    /**
     * 速算扣除数
     */
    private double[] fastDeduction = new double[46];
    /**
     * 预扣费率
     */
    private Map<Double, Double> rateMap = new HashMap<>();

    @PostConstruct
    public void init() {
        fastDeduction[3] = constant.getDeduction_level_1();
        fastDeduction[10] = constant.getDeduction_level_2();
        fastDeduction[20] = constant.getDeduction_level_3();
        fastDeduction[25] = constant.getDeduction_level_4();
        fastDeduction[30] = constant.getDeduction_level_5();
        fastDeduction[35] = constant.getDeduction_level_6();
        fastDeduction[45] = constant.getDeduction_level_7();
        rateMap.put(36000d, 3d);
        rateMap.put(144000d, 10d);
        rateMap.put(300000d, 20d);
        rateMap.put(420000d, 25d);
        rateMap.put(660000d, 30d);
        rateMap.put(960000d, 35d);
        rateMap.put((double) (1 << 30), 45d);
        for (int i = 1; i <= 12; i++) {
            Accumulate5000Deduction[i] = 5000 * i;
        }
    }

    /**
     * 每次重设工资都应该重新计算
     */
    public void reCalculate() {
        for (int i = 1; i <= 12; i++) {
            // 计算累计工资
            AccumulateSalary[i] += AccumulateSalary[i - 1] + Salary[i];
            // 计算当月截止累计的五险一金
            AccumulateHouseFoundingDeductionCash[i] += AccumulateHouseFoundingDeductionCash[i - 1]
                    + insuranceAndHouseFounding.getTotalDeduction(Salary[i]);
            // 计算当月截止累计的专项扣除额度
            AccumulateSpecialExpenseDeduction[i] += AccumulateSpecialExpenseDeduction[i - 1]
                    + specialExpenseDeduction.getTotalAccumlateSpecialExpenseDeduction(i);
            // 计算当月截止累计应纳的税所得额度
            AccumulatePreDeductionCash[i] = AccumulateSalary[i]
                    - AccumulateHouseFoundingDeductionCash[i]
                    - AccumulateSpecialExpenseDeduction[i]
                    - Accumulate5000Deduction[i];
            // 计算当月的累计应纳税额
            double rateLevel = getRateLevel(AccumulatePreDeductionCash[i]);
            AccumulateCashForTaxes[i] = AccumulatePreDeductionCash[i] * rateLevel / 100 - fastDeduction[(int) rateLevel];
        }
    }


    // 获取税率
    public double getRateLevel(double accumulatePreDeductionCash) {
        double maxRate = 0;
        Double[] set = new Double[rateMap.size()];
        rateMap.keySet().toArray(set);
        Arrays.sort(set);
        for (Double aDouble : set) {
            maxRate = rateMap.get(aDouble);
            if (aDouble > accumulatePreDeductionCash) {
                break;
            }
        }
        return maxRate;
    }
}
