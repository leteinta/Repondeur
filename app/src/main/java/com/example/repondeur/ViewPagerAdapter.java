package com.example.repondeur;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    // Liste des fragments et de leurs titres
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    // Constructeur prenant en paramètre l'activité parent
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Crée un fragment en fonction de sa position dans la liste
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    // Retourne le nombre total de fragments
    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    // Ajoute un fragment avec un titre à la liste
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    // Retourne le titre du fragment à une position donnée
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
