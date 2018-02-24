package de.meinkummerkasten;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://mein-kummerkasten.de");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigation_menu);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mNavigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_problem:
                if(webView != null){
                    webView.loadUrl("http://mein-kummerkasten.de/Einstellen");
                    mDrawerLayout.closeDrawers();
                }
                break;
            case R.id.nav_faq:
                if(webView != null){
                    webView.loadUrl("http://mein-kummerkasten.de/FAQ");
                    mDrawerLayout.closeDrawers();
                }
                break;

            case R.id.nav_team:
                if(webView != null){
                    webView.loadUrl("http://mein-kummerkasten.de/Team");
                    mDrawerLayout.closeDrawers();
                }
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        mDrawerLayout.closeDrawers();

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            mDrawerLayout.closeDrawers();
            super.onBackPressed();
        }
    }

}
