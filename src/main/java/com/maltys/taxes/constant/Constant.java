package com.maltys.taxes.constant;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * 算数扣除数
 *
 * @author maltyshane
 */
@Configuration
@Data
public class Constant {
    private final double deduction_level_1 = 0;
    private final double deduction_level_2 = 2520;
    private final double deduction_level_3 = 16920;
    private final double deduction_level_4 = 31920;
    private final double deduction_level_5 = 52920;
    private final double deduction_level_6 = 85920;
    private final double deduction_level_7 = 181920;

    private final double basic_free_line = 5000;
    private final double texes_rate_1 = 3;
    private final double texes_rate_2 = 10;
    private final double texes_rate_3 = 20;
    private final double texes_rate_4 = 25;
    private final double texes_rate_5 = 30;
    private final double texes_rate_6 = 35;
    private final double texes_rate_7 = 45;
}
