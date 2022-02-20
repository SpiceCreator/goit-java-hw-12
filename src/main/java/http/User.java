package http;

public class User {
    private final int id;
    private final String name;
    private final String username;
    private final String email;
    private final Address address;
    private final String phone;
    private final String website;
    private final Company company;

    public User(int id, String name, String username, String email, String street, String suite, String city, String zipcode,
                String lat, String lng, String phone, String website, String companyName, String catchPhrase, String bs) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = new Address(street, suite, city, zipcode, lat, lng);
        this.phone = phone;
        this.website = website;
        this.company = new Company(companyName, catchPhrase, bs);
    }



    static class Address {
        private final String street;
        private final String suite;
        private final String city;
        private final String zipcode;
        private final Geo geo;

        public Address(String street, String suite, String city, String zipcode, String lat, String lng) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
            this.geo = new Geo(lat, lng);
        }

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        @Override
        public String toString() {
            return "{ " +
                    "\"street\": \"" + street + '\"' +
                    ", \"suite\": \"" + suite + '\"' +
                    ", \"city\": \"" + city + '\"' +
                    ", \"zipcode\": \"" + zipcode + '\"' +
                    ", \"geo\": " + geo +
                    " }";
        }

        static class Geo {
            private final String lat;
            private final String lng;

            public Geo(String lat, String lng) {
                this.lat = lat;
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public String getLng() {
                return lng;
            }

            @Override
            public String toString() {
                return "{ " +
                        "\"lat\": \"" + lat + '\"' +
                        ", \"lng\": \"" + lng + '\"' +
                        " }";
            }
        }

    }


    static class Company {
        private final String name;
        private final String catchPhrase;
        private final String bs;

        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        public String getName() {
            return name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        @Override
        public String toString() {
            return "{ " +
                    "\"name\": \"" + name + '\"' +
                    ", \"catchPhrase\": \"" + catchPhrase + '\"' +
                    ", \"bs\": \"" + bs + '\"' +
                    " }";
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return " { " +
                "\"id\": " + id +
                ", \"name\": \"" + name + '\"' +
                ", \"username\": \"" + username + '\"' +
                ", \"email\": \"" + email + '\"' +
                ", \"address\": " + address +
                ", \"phone\": \"" + phone + '\"' +
                ", \"website\": \"" + website + '\"' +
                ", \"company\": " + company +
                " } ";
    }
}
