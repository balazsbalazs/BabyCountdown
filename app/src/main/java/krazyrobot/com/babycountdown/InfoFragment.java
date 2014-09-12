package krazyrobot.com.babycountdown;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle;

    public static InfoFragment newInstance(String title) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.info_card, container, false);
        mTitle = getArguments().getString(TITLE);
        TextView title = (TextView)rootView.findViewById(R.id.title);
        title.setText(mTitle);
        return rootView;
    }
}
