package caf.admin.employee.matcher;

import caf.admin.employees.entity.Employee;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsAnything;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;

public class EmployeeMatcher extends TypeSafeDiagnosingMatcher<Employee> {


    private Matcher<String> firstName = new IsAnything<>();

    private Matcher<String> lastName = new IsAnything<>();

    private Matcher<LocalDate> birthDate = new IsAnything<>();
    private Matcher<Boolean> hasPremiumAccess = new IsAnything<>();

    public EmployeeMatcher firstName(String firstName) {
        this.firstName = is(firstName);
        return this;
    }

    public EmployeeMatcher lastName(String lastName) {
        this.lastName = is(lastName);
        return this;
    }

    public EmployeeMatcher birthDate(LocalDate birthDate) {
        this.birthDate = is(birthDate);
        return this;
    }

    public EmployeeMatcher hasPremiumAccess(Boolean hasPremiumAccess ) {
        this.hasPremiumAccess = is(hasPremiumAccess);
        return this;
    }


    @Override
    protected boolean matchesSafely(Employee item, Description mismatchDescription) {
        return  matches(firstName, item.getFirstName(), "firstName value: ", mismatchDescription)
                && matches(lastName, item.getLastName(), "lastName value: ", mismatchDescription)
                && matches(birthDate, item.getBirthDate(), "birthDate value: ", mismatchDescription)
                && matches(hasPremiumAccess, item.isHasPremiumAccess(), "hasPremiumAccess value: ", mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(Employee.class.getSimpleName()).appendText(", lastName: ").appendDescriptionOf(lastName)
                .appendText(", firstName: ").appendDescriptionOf(firstName).appendText(", birthDate: ")
                .appendDescriptionOf(birthDate).appendText(", hasPremiumAccess: ").appendDescriptionOf(hasPremiumAccess);
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
