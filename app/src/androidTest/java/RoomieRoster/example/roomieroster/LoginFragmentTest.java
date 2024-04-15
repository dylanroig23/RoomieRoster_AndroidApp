package RoomieRoster.example.roomieroster;

import android.content.Intent;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import com.RoomieRoster.R;
import com.google.firebase.auth.FirebaseAuth;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.LoginActivity;
import RoomieRoster.UI.Activities.RegisterActivity;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoginFragmentTest {



    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    private FirebaseAuth mAuth;

    @Before
    public void setUp() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut(); // Make sure the user is signed out before testing
    }

    @After
    public void tearDown() {
        mAuth.signOut(); // Sign out after testing
    }

@Test
    public void testLoginSuccess() {
        // Launch the LoginFragment
        ActivityScenario.launch(LoginActivity.class);

        // Enter email and password
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("amber@gmail.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("test123"), ViewActions.closeSoftKeyboard());

        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click());

        // Check if Popup is displayed
        assertTrue(isPermissionPopupDisplayed());
}

    private boolean isPermissionPopupDisplayed() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        // Check if the permission popup is displayed by searching for "Allow" or "Deny" text
        return device.hasObject(By.textContains("Allow")) || device.hasObject(By.textContains("Deny"));
    }


    @Test
    public void testLoginFailure() {
        ActivityScenario.launch(LoginActivity.class);

        // Enter incorrect email and password
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("incorrect@example.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("incorrectPassword"), ViewActions.closeSoftKeyboard());

        assertFalse(isPermissionPopupDisplayed());
    }

    @Test
    public void testNavigateToRegister() {
        ActivityScenario.launch(LoginActivity.class);

        // Click on the create account text
        Espresso.onView(ViewMatchers.withId(R.id.text_create_account)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btn_register)).check(matches(isDisplayed()));
    }
}


