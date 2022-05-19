package com.dennis_brink.android.myerrands;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;

public class DialogHelper  {

    public static AlertDialog getDeleteDialog(Context context, ArrayList<String> itemList, int position, ArrayAdapter arrayAdapter){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string._delete);
        builder.setCancelable(false);
        builder.setMessage(R.string._confirm_delete);

        builder.setNegativeButton(R.string._dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string._dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemList.remove(position);
                FileHelper.writeData(itemList, context);
                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        return builder.create();

    }

    public static AlertDialog getInputDialog(Context context, ArrayList<String> itemList, ArrayAdapter arrayAdapter){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string._add);
        builder.setCancelable(true);

        LayoutInflater inf = LayoutInflater.from(context);
        View dialogAddItem = inf.inflate(R.layout.dialog_add_item, null);

        builder.setView(dialogAddItem);

        EditText editText = dialogAddItem.findViewById(R.id.etAdd);
        ImageView imageView = dialogAddItem.findViewById(R.id.imgCheck);

        AlertDialog dlg = builder.create();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    itemList.add(editText.getText().toString());
                    editText.setText("");
                    FileHelper.writeData(itemList, context);
                    arrayAdapter.notifyDataSetChanged();
                    dlg.dismiss(); // only dismiss after some text was entered, trap the user
                }
            }
        });

        return dlg;

    }

}
