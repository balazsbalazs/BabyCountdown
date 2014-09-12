package krazyrobot.com.babycountdown;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonsware.cwac.anddown.AndDown;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

public class InfoFragment extends Fragment {

    public static final String WEEK = "week";
    private int mWeek;
    private TextView mTitleTextView;
    private ImageView mImageView;
    private TextView mDescTextView;
    @Inject
    ResourceProvider mResProvider;

    public static InfoFragment newInstance(int week) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(WEEK, week);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BabyApplication.inject(this);
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.info_card, container, false);
        mWeek = getArguments().getInt(WEEK, 1);
        mTitleTextView = (TextView) rootView.findViewById(R.id.title);
        mImageView = (ImageView) rootView.findViewById(R.id.image);
        mDescTextView = (TextView) rootView.findViewById(R.id.description);

        String title = "Week " + (mWeek + 1);
        mTitleTextView.setText(title);
        mImageView.setImageDrawable(mResProvider.loadInfoImage(mWeek));
        mDescTextView.setText(Html.fromHtml(mResProvider.getHtmlFromMarkdown(mWeek)));

        return rootView;
    }
}
