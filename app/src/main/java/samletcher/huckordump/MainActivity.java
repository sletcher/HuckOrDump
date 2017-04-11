package samletcher.huckordump;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    ProfileFragment frag = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frag).commit();
                    return true;
                case R.id.navigation_swipe:
                    return true;
                case R.id.navigation_conversations:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isRegister = getIntent().getBooleanExtra(LoginActivity.INTENT_EXTRA_IS_REGISTER, false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (isRegister) {
            navigation.setSelectedItemId(R.id.navigation_settings);
        } else {
            navigation.setSelectedItemId(R.id.navigation_swipe);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
