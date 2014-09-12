package krazyrobot.com.babycountdown;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;

import org.joda.time.DateTime;

import javax.inject.Inject;


public class HomeActivity extends FragmentActivity {
    @Inject
    AppPreferences mPrefs;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BabyApplication.inject(this);
        setContentView(R.layout.home_activity);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new InfoPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        jumpToActiveWeek();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_set_date:
                launchDatePicker();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchDatePicker() {
        DateTime startDate = mPrefs.getInceptionDate();
        CalendarDatePickerDialog.newInstance((CalendarDatePickerDialog calendarDatePickerDialog, int year, int monthOfYear, int dayOfMonth) -> {
            mPrefs.saveInceptionDate(year, monthOfYear + 1, dayOfMonth);
            jumpToActiveWeek();
        }, startDate.getYear(), startDate.getMonthOfYear() - 1, startDate.getDayOfMonth()).show(getSupportFragmentManager(), "tag");
    }

    private void jumpToActiveWeek() {
        mPager.setCurrentItem(mPrefs.getActiveWeek());
    }

}
