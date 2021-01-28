package com.nish.springannotation;

/*
Apple. (A Series, S Series)
Qualcomm (Snapdragon, Snapwear)
Samsung (Exynos)
Mediatek (MTxxx, Helio, Dimensity)
Huawei (Kirin).
*/

import org.springframework.beans.factory.annotation.Required;

public class Processor {
    private String processorName;
    private String companyName;

    public String getProcessorName() {
        return processorName;
    }

    @Required
    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void turnOn() {
        System.out.println("Load operating system");
    }

    public void turnOff() {
        System.out.println("Close all programs");
    }

    @Override
    public String toString() {
        return "Processor{" +
                "processorName='" + processorName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
