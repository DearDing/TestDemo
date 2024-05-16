package com.ding.kotlin.test;

import android.text.TextUtils;

import java.util.Locale;
import java.util.Optional;

/**
 * JDK 8中 Optional使用，避免过多的 if else
 */
public class TestOptional {

    public static class UserBean {
        private String name;
        private int age;

        public UserBean(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    /**
     * 普通判空取值方式
     * @param user
     * @return
     */
    public static String getUpperCaseUserName(UserBean user) {
        if (user != null) {
            if (!TextUtils.isEmpty(user.getName())) {
                return user.getName().toUpperCase(Locale.ROOT);
            }
        }
        return "";
    }

    /**
     * 使用 Optional 取值，更优雅
     * @param optional
     * @return
     */
    public static String getUpperCaseUserName(Optional<UserBean> optional) {
        if(!optional.isPresent()){
            return "";
        }
        return optional.map(UserBean::getName)
                .map(String::toUpperCase)
                .orElse("");
    }

    public static void main(String[] args) {
        UserBean user = new UserBean(null,15);

        //普通用法
        System.out.println(getUpperCaseUserName(user));

        //Optional 使用
        Optional<UserBean> op = Optional.of(user);
        System.out.println(getUpperCaseUserName(op));
    }
}
