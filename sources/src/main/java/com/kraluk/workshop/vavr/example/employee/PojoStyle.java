package com.kraluk.workshop.vavr.example.employee;

import com.kraluk.workshop.vavr.common.exception.WorkshopException;

public final class PojoStyle {

    private PojoStyle() {
    }

    public static String getLastFour(Employee employee) {
        if (employee != null) {
            Address address = employee.getPrimaryAddress();
            if (address != null) {
                ZipCode zip = address.getZipCode();
                if (zip != null) {
                    return zip.getLastFour();
                }
            }
        }
        throw new WorkshopException("Missing data");
    }

    // --- Data

    final class Employee {
        private Address primaryAddress;

        Address getPrimaryAddress() {
            return primaryAddress;
        }

        void setPrimaryAddress(Address primaryAddress) {
            this.primaryAddress = primaryAddress;
        }
    }

    final class Address {
        private ZipCode zipCode;

        ZipCode getZipCode() {
            return zipCode;
        }

        void setZipCode(ZipCode zipCode) {
            this.zipCode = zipCode;
        }
    }

    final class ZipCode {
        private String lastFour;

        String getLastFour() {
            return lastFour;
        }

        void setLastFour(String lastFour) {
            this.lastFour = lastFour;
        }
    }
}
