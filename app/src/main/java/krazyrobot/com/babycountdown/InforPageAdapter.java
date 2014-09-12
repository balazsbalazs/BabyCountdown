package krazyrobot.com.babycountdown;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
class InforPageAdapter extends FragmentStatePagerAdapter {
    public InforPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return InfoFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return AppPreferences.NUM_OF_WEEKS;
    }
}
