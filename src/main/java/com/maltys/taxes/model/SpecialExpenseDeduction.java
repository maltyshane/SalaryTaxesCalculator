package com.maltys.taxes.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 专项扣除免除部分计算
 */
@Configuration
@Data
public class SpecialExpenseDeduction {
    /**
     * 房贷
     */
    private Double[] HousingLoan = new Double[]{0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
    /**
     * 赡养老人
     */
    private Double[] SuportOlderly = new Double[]{0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
    /**
     * 继续教育
     */
    private Double[] FurtherEducation = new Double[]{0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
    /**
     * 大病医疗
     */
    private Double[] CurePayment = new Double[]{0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
    /**
     * 房租
     */
    private Double[] Rent = new Double[]{0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
    private Map<String, Double[]> map = new HashMap();

    {
        map.put("HousingLoan", HousingLoan);
        map.put("SuportOlderly", SuportOlderly);
        map.put("FurtherEducation", FurtherEducation);
        map.put("CurePayment", CurePayment);
        map.put("Rent", Rent);
    }

    public double getTotalAccumlateSpecialExpenseDeduction(int month) {
        double result = 0;
        for (int i = 0; i <= month; i++) {
            result += HousingLoan[i];
        }
        for (int i = 0; i <= month; i++) {
            result += SuportOlderly[i];
        }
        for (int i = 0; i <= month; i++) {
            result += FurtherEducation[i];
        }
        for (int i = 0; i <= month; i++) {
            result += CurePayment[i];
        }
        for (int i = 0; i <= month; i++) {
            result += Rent[i];
        }
        return result;
    }
}
