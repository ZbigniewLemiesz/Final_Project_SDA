package pl.sda.ldz24.finalapp.users;

import lombok.Getter;

@Getter
public enum Countries {
    POLAND ("Polska", "pl"),
    GERMANY ("Niemcy", "de"),
    ITALY ("Włochy", "it"),
    FRANCE("Francja", "fr");

    private String polishName;
    private String symbol;

    Countries(String polishName, String symbol) {
        this.polishName = polishName;
        this.symbol = symbol;
    }
}
