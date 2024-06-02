package com.example.repondeur;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    // Code de requête pour les autorisations de lire les contacts
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    // Déclaration des vues
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Trouver les vues dans le layout
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Vérifier si l'autorisation de lire les contacts a été accordée
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Si l'autorisation n'a pas été accordée, demander à l'utilisateur de la donner
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // Si l'autorisation a été accordée, configurer le ViewPager
            setupViewPager(viewPager);
        }

        // Attacher le TabLayout au ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(((ViewPagerAdapter) viewPager.getAdapter()).getPageTitle(position));
        }).attach();
    }

    // Méthode pour configurer le ViewPager
    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        // Ajouter les fragments au ViewPager
        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new MessagesFragment(), "Messages");
        adapter.addFragment(new SpamFragment(), "Spam");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Vérifier si la demande d'autorisation concerne la lecture des contacts
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            // Si l'autorisation a été accordée, configurer le ViewPager
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupViewPager(viewPager);
            }
        }
        // Appeler la méthode parent pour gérer les résultats des demandes d'autorisation
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
