package org.muieer.java.clazz.upon11;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class RecordClassDemo {

    public static void main(String[] args) {
        var s1 = new SocialSecurityNumber(new byte[]{1, 2, 3, 4});
        System.out.println("s1 " + s1);
        var s2 = new SocialSecurityNumber(new byte[]{1, 2, 3, 4});
        System.out.println("s2 " + s2);
        System.out.println("s1 equals s2 res is " + s1.equals(s2));
        s1.ssn()[0] = 0;
        System.out.println("执行 s1.ssn()[0] = 0 语句后 ssn() 结果是：" + Arrays.toString(s1.ssn()));
    }
}

/*
 * 档案类：社会保障号
 * */
record SocialSecurityNumber(byte[] ssn) {

    public SocialSecurityNumber {
        if (ssn.length < 2) throw new RuntimeException("ssn length less than 2");
    }

    // 只能保证指针地址的不变，对应的数据内容还是可以修改，所以返回结果需要深拷贝
    @Override
    public byte[] ssn() {
        return Arrays.copyOf(this.ssn, this.ssn.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof SocialSecurityNumber so) {
            return new String(so.ssn).equals(new String(this.ssn));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new String(this.ssn).hashCode();
    }

    @Override
    public String toString() {
//        return Arrays.toString(this.ssn);
        return StringUtils.join(this.ssn, ' ').replace(" ", "");
    }
}
