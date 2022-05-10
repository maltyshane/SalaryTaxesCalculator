package com.maltys.taxes.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;


/**
 * 五险一金免除部分计算
 *
 * @author maltyshane
 */
@Configuration
@Data
public class InsuranceHouseFounding {
    //    公积金比例
    private double houseFoundingRate = 12;
    //    养老保险比例
    private double endowmentRate = 8;
    //    医疗保险比例
    private double medicalRate = 2;
    //    失业保险比例
    private double unemploymentRate = 0.5;
    //    工伤保险比例
    private double employmentInjuryRate = 0;
    //    生育保险比例
    private double maternityRate = 0;

    //    工伤保险固定额度
    private double employmentInjury = 0;
    //    医疗保险固定额度
    private double medical = 3;

    public double getTotalDeduction(double salary) {
        double result = 0;
        double houseFoundingItem = countHouseFounding(salary);
        double endowmentItem = countEndowment(salary);
        double medicalItem = countMedical(salary);
        double unemploymentItem = countUnemployment(salary);
        double employmentInjuryItem = countEmploymentInjury(salary);
        double maternityItem = countMaternity(salary);
        result = houseFoundingItem + endowmentItem + medicalItem + unemploymentItem + employmentInjuryItem + maternityItem;
        return result;
    }

    public double countHouseFounding(double salary) {
        return salary * houseFoundingRate / 100;
    }

    public double countEndowment(double salary) {
        return salary * endowmentRate / 100;
    }

    public double countMedical(double salary) {
        return salary * medicalRate / 100 + medical;
    }

    public double countUnemployment(double salary) {
        return salary * unemploymentRate / 100;
    }

    public double countEmploymentInjury(double salary) {
        return salary * employmentInjuryRate / 100 + employmentInjury;
    }

    public double countMaternity(double salary) {
        return salary * maternityRate / 100;
    }
}
