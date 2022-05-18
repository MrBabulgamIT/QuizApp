package com.babulgamit.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView imageView;
    int count=0;
    private CardView practiceCV,startquizCV,feedbackCV,rulesCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout_id);
        imageView=findViewById(R.id.menu_id);

        practiceCV=findViewById(R.id.cvpractice_id);
        startquizCV=findViewById(R.id.startQuiz_card_id);
        feedbackCV=findViewById(R.id.cvfeedback_id);
        rulesCV=findViewById(R.id.cvRules_id);

        navigationView =findViewById(R.id.navigationView_id);

        practiceCV.setOnClickListener(this);
        startquizCV.setOnClickListener(this);
        feedbackCV.setOnClickListener(this);
        rulesCV.setOnClickListener(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.nav_home) {

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                } else

                if (id == R.id.nav_feedback) {

                   startActivity(new Intent(getApplicationContext(),FeedBackActivity.class));
                }
                else
                if (id == R.id.nav_contact_us) {

                    startActivity(new Intent(getApplicationContext(),Contace_usActivity.class));


                } else


                if (id == R.id.nav_rate) {
                    Toast.makeText(MainActivity.this, "Rate is clicked", Toast.LENGTH_SHORT).show();

                } else




                if (id == R.id.nav_share) {

                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String subject="Android Application.";
                    String body="This is very Useful to learn C program.\n com.babulgamit.quizapp";

                    intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                    intent.putExtra(Intent.EXTRA_TEXT,body);

                    startActivity(Intent.createChooser(intent,"Share with"));                } else

                if (id == R.id.nav_rate) {

                    Toast.makeText(MainActivity.this, "rate is Clicked", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }


        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cvpractice_id:

                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                break;

            case R.id.cvfeedback_id:
                startActivity(new Intent(getApplicationContext(),FeedBackActivity.class));
                break;

            case R.id.cvRules_id:
               startActivity(new Intent(getApplicationContext(),RulesActivity.class));
                break;

            case R.id.startQuiz_card_id:
               startActivity(new Intent(getApplicationContext(),StartMainQuizActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        count ++;

        if (count==2)
            finish();
    }

}