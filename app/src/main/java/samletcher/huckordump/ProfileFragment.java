package samletcher.huckordump;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Unbinder unbinder;
    private DatabaseHuckOrDump db;
    private OnFragmentInteractionListener mListener;
    private User mUser;

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

    @OnClick (R.id.profile_delete_button)
    public void checkDatabase() {
        // delete the current user and log out
        db.deleteUser(mUser);
        getActivity().finish();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick (R.id.profile_save_button)
    public void saveSettings() {
        mUser.setFirst_name(mFirstName.getText().toString());
        mUser.setLast_name(mLastName.getText().toString());
        mUser.setBio(mBio.getText().toString());
        switch (mIam.getCheckedRadioButtonId()) {
            case R.id.i_am_man:
                mUser.setGender(0);
                break;
            case R.id.i_am_woman:
                mUser.setGender(1);
                break;
        }

        switch (mLookingFor.getCheckedRadioButtonId()) {
            case R.id.looking_for_woman:
                mUser.setInterest(1);
                break;
            case R.id.looking_for_man:
                mUser.setInterest(0);
                break;
        }
        mUser.setTeam_id(Integer.parseInt(mPid.getText().toString()));
        mUser.setPosition(mPosition.getText().toString());

        // add user to db
        db.updateUser(mUser);
    }

    @OnClick (R.id.profile_admin_button)
    public void adminButton() {
        List<User> users = db.getAllUsers();
        Log.e("Test", users.toString());
    }

    @OnClick (R.id.profile_logout_button)
    public void logoutButton() {
        getContext().getSharedPreferences(LoginActivity.SHARED_PREF_USER_ID, 0).edit().clear().apply();

        getActivity().finish();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        getActivity().startActivity(intent);
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

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int id = sharedPref.getInt(LoginActivity.SHARED_PREF_USER_ID, 0);

        mUser = db.getUser(id);
        updateFields(mUser);

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

    private void updateFields(User user) {
        if (user.getFirst_name() != "") {
            mFirstName.setText(user.getFirst_name());
            mLastName.setText(user.getLast_name());
            mBio.setText(user.getBio());
            mIam.check(user.getGender());
            mLookingFor.check(user.getInterestedIn());
            mPosition.setText(user.getPosition());
        }
    }
}
