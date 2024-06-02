package com.example.repondeur;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class SpamFragment extends Fragment {

    private Spinner contactSpinner;
    private Button spamButton;
    private CheckBox autoResponseCheckBox;

    // Liste des contacts
    private List<String> contacts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Charge le layout fragment_spam.xml pour ce fragment
        View view = inflater.inflate(R.layout.fragment_spam, container, false);

        // Trouve les vues dans le layout
        contactSpinner = view.findViewById(R.id.contactSpinner);
        spamButton = view.findViewById(R.id.spamButton);
        autoResponseCheckBox = view.findViewById(R.id.autoResponseCheckBox);

        // Charge la liste des contacts dans le spinner
        contacts = loadContacts();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, contacts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactSpinner.setAdapter(adapter);

        // Retourne la vue chargée
        return view;
    }

    // Méthode pour charger les contacts depuis le téléphone
    private List<String> loadContacts() {
        List<String> contacts = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Récupère le nom du contact
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contacts.add(contactName);
            }
            cursor.close();
        }
        return contacts;
    }
}

