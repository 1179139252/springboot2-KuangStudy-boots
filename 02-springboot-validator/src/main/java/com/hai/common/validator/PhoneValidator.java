package com.hai.common.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 15:54
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phonevalue, ConstraintValidatorContext constraintValidatorContext) {
        // 1: 如果用户没输入直接返回不校验，因为空的判断交给@NotNull去做就行了
        if (StringUtils.isEmpty(phonevalue)) {
            return true;
        }
//        Pattern p = Pattern.compile("^(13[0-9]|14[5|7|9]|15[0|1|2|3|5|6|7|8|9]|17[0|1|6|7|8]|18[0-9])\\d{8}$");
//        // 2：如果校验通过就返回true,否则返回false;
//        Matcher matcher = p.matcher(phonevalue);
//        return matcher.matches();
        return ValidateUtil.validateMobile(phonevalue);
    }
    @Override
    public void initialize(Phone constraintAnnotation) {
    }
}