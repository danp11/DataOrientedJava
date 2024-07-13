package nestedrecords;

public class Test {

    record Name       (String fName, String lName) { }
    record PhoneNumber(String areaCode, String number) { }
    record Country    (String countryCode, String countryName) { }
    record Passenger  (Name name,
                       PhoneNumber phoneNumber,
                       Country from,
                       Country destination) { }

    public class GeoMaps {
        boolean checkFirstNameAndCountryCodeAgain (Object obj) {
            if (obj instanceof Passenger(Name (String fName, _),
                                         _,
                                         _,
                                         Country (String countryCode, _) )) {

                if (fName != null && countryCode != null) {
                    return fName.startsWith("Simo") && countryCode.equals("PRG");
                }
            }
            return false;
        }
    }
}
