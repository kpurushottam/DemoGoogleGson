package com.krp.android.demogooglegson;

/**
 * Created by purushottam.kumar on 11/26/2015.
 */
public enum UserType {
    Student(0) {
        public String toStringValue() {
            return "Student";
        }
    },
    Alumni(1) {
        public String toStringValue() {
            return "Alumni";
        }
    },
    Employee(2) {
        public String toStringValue() {
            return "Employee";
        }
    };

    private int value;
    public int getValue() {
        return value;
    }

    private UserType(int value) {
        this.value = value;
    }

    abstract public String toStringValue();
}
