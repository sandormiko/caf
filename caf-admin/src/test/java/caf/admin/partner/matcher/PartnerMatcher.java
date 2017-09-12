package caf.admin.partner.matcher;


import caf.admin.partners.entity.Partner;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsAnything;

import static org.hamcrest.core.Is.is;

public class PartnerMatcher extends TypeSafeDiagnosingMatcher<Partner> {

    private Matcher<String> name = new IsAnything<>();

    private Matcher<String> account = new IsAnything<>();


    public static PartnerMatcher newPartnerMatcher(){
        return new PartnerMatcher();
    }
    private PartnerMatcher(){}

    public PartnerMatcher name(String name) {
        this.name = is(name);
        return this;
    }

    public PartnerMatcher account(String account) {
        this.account = is(account);
        return this;
    }


    @Override
    protected boolean matchesSafely(Partner item, Description mismatchDescription) {
        return  matches(name, item.getName(), "name value: ", mismatchDescription)
                && matches(account, item.getAccount(), "account value: ", mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(Partner.class.getSimpleName()).appendText(", name: ").appendDescriptionOf(name)
                .appendText(", account: ").appendDescriptionOf(account);
    }

    protected <X> boolean matches(Matcher<? extends X> matcher, X value, String attribute,
                                  Description mismatchDescription) {
        if (!matcher.matches(value)) {
            mismatchDescription.appendText(" " + attribute + " ");
            matcher.describeMismatch(value, mismatchDescription);
            return false;
        } else {
            return true;
        }
    }
}
