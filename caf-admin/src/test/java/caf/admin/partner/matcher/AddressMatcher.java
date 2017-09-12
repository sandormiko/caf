package caf.admin.partner.matcher;

import caf.admin.partners.entity.Address;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsAnything;

import static org.hamcrest.core.Is.is;

public class AddressMatcher extends TypeSafeDiagnosingMatcher<Address> {

    private Matcher<String> street = new IsAnything<>();

    private Matcher<String> city = new IsAnything<>();

    private Matcher<String> postCode = new IsAnything<>();

    private Matcher<String> country = new IsAnything<>();

    public static AddressMatcher newAddressMatcher(){
        return new AddressMatcher();
    }
    private AddressMatcher(){}


    public AddressMatcher street(String street) {
        this.street = is(street);
        return this;
    }

    public AddressMatcher city(String city) {
        this.city = is(city);
        return this;
    }

    public AddressMatcher postCode(String postCode) {
        this.postCode = is(postCode);
        return this;
    }

    public AddressMatcher country(String country) {
        this.country = is(country);
        return this;
    }

    @Override
    protected boolean matchesSafely(Address item, Description mismatchDescription) {
        return matches(street, item.getStreet(), "street erteke: ", mismatchDescription) &&
                matches(city, item.getCity(), "city erteke: ", mismatchDescription) &&
                matches(postCode, item.getPostCode(), "postCode erteke: ", mismatchDescription) &&
                matches(country, item.getCountry(), "country erteke: ", mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(Address.class.getSimpleName())
                .appendText(", street: ").appendDescriptionOf(street)
                .appendText(", city: ").appendDescriptionOf(city)
                .appendText(", postCode: ").appendDescriptionOf(postCode)
                .appendText(", country: ").appendDescriptionOf(country);
    }

    protected <X> boolean matches(Matcher<? extends X> matcher, X value, String attribute, Description mismatchDescription) {
        if (!matcher.matches(value)) {
            mismatchDescription.appendText(" " + attribute + " ");
            matcher.describeMismatch(value, mismatchDescription);
            return false;
        } else {
            return true;
        }
    }
}