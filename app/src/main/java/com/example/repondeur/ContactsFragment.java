package com.example.repondeur;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private ListView contactsListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Charge le layout fragment_contacts.xml pour ce fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Trouve la liste de contacts dans le layout
        contactsListView = view.findViewById(R.id.contactsListView);

        // Charge la liste de contacts dans la ListView
        loadContacts();

        // Retourne la vue chargée
        return view;
    }

    // Méthode pour charger les contacts dans la ListView
    private void loadContacts() {
        List<String> contacts = new ArrayList<>();

        // Accède aux contacts du téléphone
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Récupère le nom et le numéro de téléphone de chaque contact
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // Ajoute le nom et le numéro à la liste de contacts
                contacts.add(name + " - " + phoneNumber);
            }
            cursor.close();
        }

        // Crée un adaptateur pour afficher les contacts dans la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, contacts);
        // Active le mode de sélection multiple pour la ListView
        contactsListView.setAdapter(adapter);
        contactsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}
