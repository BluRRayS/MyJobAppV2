package com.blurrays.myjobapp.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.blurrays.myjobapp.Classes.Company;
import com.blurrays.myjobapp.CompanyActivities.CompanyInfoActivity;
import com.blurrays.myjobapp.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CompanyArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Company[] company = {new Company()};


    public CompanyArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.company_row_layout, parent, false);
        DocumentReference docRef = db.collection("companies").document(values[position]);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    company[0] = document.toObject(Company.class);
                    TextView companyName = rowView.findViewById(R.id.companyNameRow);
                    TextView companyEmail = rowView.findViewById(R.id.companyEmailRow);
                    companyName.setText(company[0].getName());
                    companyEmail.setText(company[0].getEmail());

                    Button infoButton = rowView.findViewById(R.id.BtnCompanyInfo);
                    infoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), CompanyInfoActivity.class);
                            //if you want to send data to called activity uncomment next line
                            // intent.putExtra("extra", "value");

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("companyId",values[position]);
                            getContext().startActivity(intent);
                        }
                    });
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
        return rowView;
    }
}
