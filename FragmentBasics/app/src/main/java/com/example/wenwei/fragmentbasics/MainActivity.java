package com.example.wenwei.fragmentbasics;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import layout.ArticleFragment;
import layout.FragmentOne;
import layout.HeadlinesFragment;

public class MainActivity extends FragmentActivity
        implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.news_articles);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            HeadlinesFragment firstFragment = new HeadlinesFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onArticleSelected(int position) {
        ArticleFragment articleFrag = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            articleFrag.updateArticleView(position);
        } else {
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }


    public void changeFragment(View view) {
        Fragment fragment;

        if (view == findViewById(R.id.button1)) {
            fragment = new layout.FragmentOne();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fragment);
            fragmentTransaction.commit();
        }

        if (view == findViewById(R.id.button2)) {
            fragment = new layout.FragmentTwo();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, fragment);
            fragmentTransaction.commit();
        }
    }
}
