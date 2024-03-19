package org.example;

import java.util.logging.StreamHandler;

public class Branch {
    private String branchname;
    private String ifscCode;
    private String branchId;
    private String bankName;
    private long branchContact;

    public Branch(String branchname, String ifscCode, String branchId, String bankName, long branchContact) {
        this.branchname = branchname;
        this.ifscCode = ifscCode;
        this.branchId = branchId;
        this.bankName = bankName;
        this.branchContact = branchContact;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(long branchContact) {
        this.branchContact = branchContact;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchname='" + branchname + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", branchId='" + branchId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchContact=" + branchContact +
                '}';
    }
}

