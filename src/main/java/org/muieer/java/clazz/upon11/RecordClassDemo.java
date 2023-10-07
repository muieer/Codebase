package org.muieer.java.clazz.upon11;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class RecordClassDemo {

    public static void main(String[] args) {
        var s1 = new SocialSecurityNumber(new byte[]{1, 2, 3, 4});
        var s2 = new SocialSecurityNumber(new byte[]{1, 2, 3, 4});
        System.out.println(s1.equals(s2));
        System.out.println(s1);
    }
}

/*
 * 档案类：社会保障号
 * */
record SocialSecurityNumber(byte[] ssn) {

    public SocialSecurityNumber {
        if (ssn.length < 2) throw new RuntimeException("ssn length less than 2");
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
