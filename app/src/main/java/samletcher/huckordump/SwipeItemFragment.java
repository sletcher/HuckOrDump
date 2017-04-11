package samletcher.huckordump;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeItemFragment extends Fragment {
    private static final String NAME_KEY = "NAME_KEY";
    private static final String PICTURE_KEY = "PICTURE_KEY";
    private static final String AGE_KEY = "AGE_KEY";
    private static final String TEAMS_KEY = "TEAMS_KEY";
    private static final String BIO_KEY = "BIO_KEY";

    @BindView(R.id.swipe_frag_profile_picture) protected ImageView mPicture;
    @BindView(R.id.swipe_frag_name) protected TextView mName;
    @BindView(R.id.swipe_frag_age) protected TextView mAge;
    @BindView(R.id.swipe_frag_teams) protected TextView mTeams;
    @BindView(R.id.swipe_frag_bio) protected TextView mBio;

    public static SwipeItemFragment newInstance(SwipeItemUser user) {
        SwipeItemFragment swipeFrag = new SwipeItemFragment();

        Bundle args = new Bundle();
        args.putString(NAME_KEY, user.name);
        args.putByteArray(PICTURE_KEY, user.picture);
        args.putInt(AGE_KEY, user.age);
        args.putString(TEAMS_KEY, user.teams);
        args.putString(BIO_KEY, user.bio);
        swipeFrag.setArguments(args);

        return swipeFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_item, container, false);
        ButterKnife.bind(this, view);


        Bundle args = getArguments();

        mPicture.setImageBitmap(
                BitmapFactory.decodeByteArray(
                        args.getByteArray(PICTURE_KEY), 0, args.getByteArray(PICTURE_KEY).length)
        );
        mName.setText(args.getString(NAME_KEY));
        mAge.setText(args.getInt(AGE_KEY));
        mTeams.setText(args.getString(AGE_KEY));
        mBio.setText(args.getString(BIO_KEY));
        return view;
    }
}
