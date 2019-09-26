package com.blurrays.myjobapp.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blurrays.myjobapp.Classes.Company;
import com.blurrays.myjobapp.Classes.Workfloor;
import com.blurrays.myjobapp.CompanyActivities.CompanyInfoActivity;
import com.blurrays.myjobapp.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class WorkfloorArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Workfloor[] workfloors = {new Workfloor()};

    public WorkfloorArrayAdapter(Context context, String[] workfloorIds) {
        super(context, -1, workfloorIds);
        this.context = context;
        this.values = workfloorIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.workfloor_row_layout, parent, false);
        DocumentReference docRef = db.collection("workfloors").document(values[position]);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    workfloors[0] = document.toObject(Workfloor.class);
                    TextView workfloorName = rowView.findViewById(R.id.WorkfloorNameRow);
                    ImageView workfloorColorPreview = rowView.findViewById(R.id.workfloorColorRow);
                    workfloorName.setText(workfloors[0].getName());
                    workfloorColorPreview.setBackgroundColor(Color.argb(workfloors[0].getAlpha(), workfloors[0].getRed(), workfloors[0].getGreen(), workfloors[0].getBlue()));

                    Button editButton = rowView.findViewById(R.id.btnEditWorkFloorRow);
                    Button deleteButton = rowView.findViewById(R.id.btnDeleteWorkFloorRow);
                    editButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Intent intent = new Intent(getContext(), CompanyInfoActivity.class);
//                            //if you want to send data to called activity uncomment next line
//                            // intent.putExtra("extra", "value");
//
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.putExtra("companyId",values[position]);
//                            getContext().startActivity(intent);
                            Toast.makeText(context, "Open edit menu", Toast.LENGTH_SHORT).show();
                        }
                    });
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Intent intent = new Intent(getContext(), CompanyInfoActivity.class);
//                            //if you want to send data to called activity uncomment next line
//                            // intent.putExtra("extra", "value");
//
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.putExtra("companyId",values[position]);
//                            getContext().startActivity(intent);
                            Toast.makeText(context, "Open delete menu", Toast.LENGTH_SHORT).show();
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
