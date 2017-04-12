package samletcher.huckordump;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {
    public final static String I_AM_MAN = "I AM MAN";

    private  DatabaseHandler db;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.profile_fname) protected TextView mFirstName;
    @BindView(R.id.profile_lname) protected TextView mLastName;
    @BindView(R.id.profile_bio) protected TextView mBio;
    @BindView(R.id.password) protected TextView mPassword;
    @BindView(R.id.profile_teams) protected TextView mTeams;
    @BindView(R.id.email) protected TextView mEmail;
    @BindView(R.id.radio_group_i_am) RadioGroup mIam;
    @BindView(R.id.radio_group_looking_for) RadioGroup mLookingFor;

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
        user.setPw(mPassword.getText().toString());
        user.setEmail(mEmail.getText().toString());
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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
