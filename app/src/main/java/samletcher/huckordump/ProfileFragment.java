package samletcher.huckordump;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {
    public final static String I_AM_MAN = "I AM MAN";

    private Unbinder unbinder;
    private DatabaseHuckOrDump db;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.profile_fname) protected TextView mFirstName;
    @BindView(R.id.profile_lname) protected TextView mLastName;
    @BindView(R.id.profile_bio) protected TextView mBio;
    @BindView(R.id.profile_teams) protected TextView mTeams;
    @BindView(R.id.profile_id) protected TextView mPid;
    @BindView(R.id.radio_group_i_am) RadioGroup mIam;
    @BindView(R.id.radio_group_looking_for) RadioGroup mLookingFor;
    @BindView(R.id.position_id) TextView mPosition;

    //TODO: add picture;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @OnClick (R.id.profile_admin_button)
    public void checkDatabase() {
        // get all the users
        List<User> users = this.db.getAllUsers();
        for (User u: users) {
            u.printUser();
        }
    }

    @OnClick (R.id.profile_save_button)
    public void saveSettings() {
        User user = new User();
        user.setFirst_name(mFirstName.getText().toString());
        user.setLast_name(mLastName.getText().toString());
        user.setBio(mBio.getText().toString());
        switch (mIam.getCheckedRadioButtonId()) {
            case R.id.i_am_man:
                user.setGender(0);
                break;
            case R.id.i_am_woman:
                user.setGender(1);
                break;
        }

        switch (mLookingFor.getCheckedRadioButtonId()) {
            case R.id.looking_for_woman:
                user.setInterest(1);
                break;
            case R.id.looking_for_man:
                user.setInterest(0);
                break;
        }
        user.setTeam_id(Integer.parseInt(mPid.getText().toString()));
        user.setPosition(mPosition.getText().toString());

        // add user to db
        db.addUser(user);

        Log.e("test", "Added new user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        try {
            db = new DatabaseHuckOrDump(getContext()).open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
