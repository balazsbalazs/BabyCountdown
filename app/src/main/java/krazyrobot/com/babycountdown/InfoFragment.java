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

public class InfoFragment extends Fragment {

    public static final String WEEK = "week";
    public static final AndDown MD_CONVERTER = new AndDown();
    private int mWeek;
    private TextView mTitleTextView;
    private ImageView mImageView;
    private TextView mDescTextView;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.info_card, container, false);
        mWeek = getArguments().getInt(WEEK, 1);
        mTitleTextView = (TextView) rootView.findViewById(R.id.title);
        mImageView = (ImageView) rootView.findViewById(R.id.image);
        mDescTextView = (TextView) rootView.findViewById(R.id.description);

        String title = "Week " + (mWeek + 1);
        mTitleTextView.setText(title);
        loadImageFromFile(String.format("week%02d.gif", mWeek + 4), mImageView);
//        loadMarkdownFromFile(String.format("description%02d.md", mWeek + 1), mDescTextView);
        loadMarkdownFromFile("description02.md", mDescTextView);

        return rootView;
    }

    public void loadImageFromFile(String filename, ImageView imageView) {
        try {
            InputStream ims = getActivity().getAssets().open(filename);
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }

    }

    public void loadMarkdownFromFile(String filename, TextView textView) {
        String raw = "";
        try {
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            raw = new String(buffer);
        } catch (IOException ex) {
            return;
        }
        String html = MD_CONVERTER.markdownToHtml(raw);
        textView.setText(Html.fromHtml(html));
    }
}
