package pl.sda.ldz24.finalapp.products;

import lombok.Getter;

@Getter
public enum ProductType {
    BOOK("KSIĄŻKA"),
    TOY("ZABAWKA"),
    FISH("RYBA");

    private String plName;

    ProductType(String plName) {
        this.plName = plName;
    }

    public String getXyz() {
        return "aaaa";
    }
}
