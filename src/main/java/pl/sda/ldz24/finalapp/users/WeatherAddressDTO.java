package pl.sda.ldz24.finalapp.users;

public class WeatherAddressDTO {

    private String city;
    private Countries country;

    public WeatherAddressDTO(String city, Countries country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public Countries getCountry() {
        return this.country;
    }

    public static WeatherAddressDTO fromAddress(Address address) {
        return new WeatherAddressDTO(address.getCity(), Countries.valueOf(address.getCountry()));
    }
}
