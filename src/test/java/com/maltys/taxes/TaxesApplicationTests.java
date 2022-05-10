package com.maltys.taxes;

import com.maltys.taxes.model.InsuranceHouseFounding;
import com.maltys.taxes.model.TaxesData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaxesApplicationTests {
    @Autowired
    InsuranceHouseFounding socialInsuranceAndHouseFounding;
    @Autowired
    TaxesData taxesData;

    @Test
    void contextLoads() {
    }

    @Test
    void getInsuranceAndHouseFounding() {
        int salary = 23000;
        double founding = this.socialInsuranceAndHouseFounding.getTotalDeduction(salary);
        System.out.println(founding);
    }
}
