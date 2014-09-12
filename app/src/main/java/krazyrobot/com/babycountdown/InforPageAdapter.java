package krazyrobot.com.babycountdown;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
class InforPageAdapter extends FragmentStatePagerAdapter {
    private String[] titles = {"hello1", "hello2", "hello3", "hello4", "hello5", "hello6", "hello7", "hello1", "hello2", "hello3", "hello4", "hello5", "hello6", "hello7"};

    public InforPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return InfoFragment.newInstance(titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
