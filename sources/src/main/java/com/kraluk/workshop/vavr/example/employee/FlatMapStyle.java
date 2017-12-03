package com.kraluk.workshop.vavr.example.employee;

import com.kraluk.workshop.vavr.common.exception.WorkshopException;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author lukasz
 */
public final class FlatMapStyle {

    private FlatMapStyle() {
    }

    public static String getRawCode(Supplier<Optional<Employee>> employeeSupplier) {
        return employeeSupplier.get()
            .flatMap(employee -> employee.getPrimaryAddress())
            .flatMap(address -> address.getZipCode())
            .flatMap(zip -> zip.getRawCode())
            .orElseThrow(() -> new WorkshopException("Missing data!"));
    }

    public static String getRawCodeInAnotherWay(Supplier<Optional<Employee>> employeeSupplier) {
        return employeeSupplier.get()
            .flatMap(Employee::getPrimaryAddress)
            .flatMap(Address::getZipCode)
            .flatMap(ZipCode::getRawCode)
            .orElseThrow(FlatMapStyle::provideException);
    }

    // --- Utils

    private static WorkshopException provideException() {
        return new WorkshopException("Missing data!");
    }

    // --- Data

    static final class Employee {
        private Address primaryAddress;

        Optional<Address> getPrimaryAddress() {
            return Optional.ofNullable(primaryAddress);
        }

        void setPrimaryAddress(Address primaryAddress) {
            this.primaryAddress = primaryAddress;
        }
    }

    static final class Address {
        private ZipCode zipCode;

        Optional<ZipCode> getZipCode() {
            return Optional.ofNullable(zipCode);
        }

        void setZipCode(ZipCode zipCode) {
            this.zipCode = zipCode;
        }
    }

    static final class ZipCode {
        private String rawCode;

        Optional<String> getRawCode() {
            return Optional.ofNullable(rawCode);
        }

        void setRawCode(String rawCode) {
            this.rawCode = rawCode;
        }
    }
}
