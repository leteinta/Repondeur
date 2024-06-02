package com.example.repondeur;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    private ListView messagesListView;
    private EditText newMessageEditText;
    private Button addMessageButton;

    // Liste des messages et cases à cocher associées
    private List<String> messages;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Charge le layout fragment_messages.xml pour ce fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        // Trouve les vues dans le layout
        messagesListView = view.findViewById(R.id.messagesListView);
        newMessageEditText = view.findViewById(R.id.newMessageEditText);
        addMessageButton = view.findViewById(R.id.addMessageButton);

        // Initialise la liste des messages et l'adapter
        messages = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, messages);
        messagesListView.setAdapter(adapter);
        messagesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Configure le bouton "Add" pour ajouter un nouveau message à la liste
        addMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = newMessageEditText.getText().toString().trim();
                if (!newMessage.isEmpty()) {
                    messages.add(newMessage);
                    adapter.notifyDataSetChanged();
                    newMessageEditText.setText("");
                }
            }
        });

        // Retourne la vue chargée
        return view;
    }

    // Méthode pour récupérer les messages sélectionnés
    public List<String> getSelectedMessages() {
        List<String> selectedMessages = new ArrayList<>();
        SparseBooleanArray checkedPositions = messagesListView.getCheckedItemPositions();
        for (int i = 0; i < checkedPositions.size(); i++) {
            if (checkedPositions.valueAt(i)) {
                selectedMessages.add(messages.get(checkedPositions.keyAt(i)));
            }
        }
        return selectedMessages;
    }
}


