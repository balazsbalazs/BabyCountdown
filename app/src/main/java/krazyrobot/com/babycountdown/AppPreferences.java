package krazyrobot.com.babycountdown;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferences {
    private static final String START_DATE = "START_DATE";
    public static final int NUM_OF_WEEKS = 40;

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferences(@AppModule.ForApplication Context context) {
        mPrefs = context.getSharedPreferences("babycountdown", 0);
    }

    public void saveInceptionDate(int year, int monthOfYear, int dayOfMonth) {
        DateTime dt = new DateTime(year, monthOfYear, dayOfMonth, 0, 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putLong(START_DATE, dt.getMillis());
        editor.apply();
    }

    public DateTime getInceptionDate() {
        long startDate = mPrefs.getLong(START_DATE, DateTime.now().getMillis());
        return new DateTime(startDate);
    }

    public int getActiveWeek() {
        int activeWeek = Days.daysBetween(getInceptionDate(), DateTime.now()).toStandardWeeks().getWeeks();
        if (activeWeek > NUM_OF_WEEKS) {
            activeWeek = NUM_OF_WEEKS;
        } else if (activeWeek < 0) {
            activeWeek = 0;
        }
        return activeWeek;
    }
}
