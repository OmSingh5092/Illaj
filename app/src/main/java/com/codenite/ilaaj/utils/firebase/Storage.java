package com.codenite.ilaaj.utils.firebase;

import android.net.Uri;

import com.codenite.ilaaj.api.dataModels.Record;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import androidx.annotation.NonNull;

public class Storage {
    private static FirebaseStorage storage  = FirebaseStorage.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    public interface recordUploadHandler{
        void onSuccess(Record record);
        void onFailure(Exception e);
    }
    public interface recordDownloadHandler{
        void onSuccess(File file);
        void onFailure(Exception e);
    }

    public static void uploadRecord(Uri fileUri, recordUploadHandler handler){
        Date date = new Date();
        Record record = new Record();
        String path = "records/"+auth.getUid()+"/"+date.getTime()+"/"+fileUri.getLastPathSegment();

        storage.getReference(path).putFile(fileUri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    record.setLink(path);
                    handler.onSuccess(record);
                }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                handler.onFailure(e);
            }
        });
    }

    public static void downloadRecord(String path,recordDownloadHandler handler){

        try {
            File tempFile = File.createTempFile("record","pdf");
            storage.getReference(path).getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    handler.onSuccess(tempFile);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
