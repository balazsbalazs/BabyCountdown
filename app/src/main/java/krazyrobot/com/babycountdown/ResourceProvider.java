package krazyrobot.com.babycountdown;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonsware.cwac.anddown.AndDown;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResourceProvider {
    private final Context mContext;
    // TODO: inject
    public static final AndDown MD_CONVERTER = new AndDown();

    @Inject
    public ResourceProvider(@AppModule.ForApplication Context context) {
        mContext = context;
    }

    public Drawable loadInfoImage(int week) {
        Drawable drawable;
        String filename = String.format("week%02d.jpeg", week + 4);
        try {
            InputStream ims = mContext.getAssets().open(filename);
            drawable = Drawable.createFromStream(ims, null);
            return drawable;
        } catch (IOException ex) {
            return null;
        }
    }

    public String getHtmlFromMarkdown(int week) {
        String raw = "";
        String filename = String.format("description%02d.md", week + 1);
        try {
            InputStream is = mContext.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            raw = new String(buffer);
            String html = MD_CONVERTER.markdownToHtml(raw);
            return html;
        } catch (IOException ex) {
            return null;
        }
    }
}
