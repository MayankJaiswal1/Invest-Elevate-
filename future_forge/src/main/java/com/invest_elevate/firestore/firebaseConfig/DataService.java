package com.invest_elevate.firestore.firebaseConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class DataService {

    private static Firestore db;

    static {
        try {
            initializeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("future_forge//src//main//resources//javafx-admin.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setProjectId("your-project-id")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    // public DataService() {
    //     FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
    //                     .setProjectId("your-project-id")
    //                     .build();
    //     db = firestoreOptions.getService();
    // }

    public void addData(String collection, String documentId, Map<String, Object> data) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = db.collection(collection);
        collectionReference.document(documentId).set(data).get();
        //DocumentReference docRef = db.collection(collection).document(document);
        //ApiFuture<WriteResult> result = docRef.set(data);
        //result.get(); // Block until complete
    }

    public DocumentSnapshot getData(String collection, String document) throws ExecutionException, InterruptedException {
        try {
            DocumentReference docRef = db.collection(collection).document(document);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean authenticateUser(String username, String password) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String storedPassword = document.getString("password");
            return password.equals(storedPassword);
        }
        return false;
    }

    public boolean isAdmin(String username) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String role = document.getString("role");
            return "admin".equals(role);
        }
        return false;
    }

    public void deleteProject(String collectionName, String leaderName) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = db.collection(collectionName).document(leaderName).delete();
        writeResult.get();
    }

    public List<Map<String, Object>> getDataInDescendingOrder(String collectionName, String orderByField) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> documentsList = new ArrayList<>();

        CollectionReference collection = db.collection(collectionName);
        Query query = collection.orderBy(orderByField, Query.Direction.DESCENDING);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documentsList.add(document.getData());
        }

        return documentsList;
    }

    public void updateData(String collectionName, String documentId, Map<String, Object> updatedData) throws ExecutionException, InterruptedException {
        CollectionReference collection = db.collection(collectionName);
        DocumentReference docRef = collection.document(documentId);

        ApiFuture<WriteResult> future = docRef.set(updatedData, SetOptions.merge());

        future.get(); // Wait for the update to complete
    }

    public List<Map<String, Object>> getAllData(String collection) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = db.collection(collection);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (DocumentSnapshot documentSnapshot : collectionReference.get().get().getDocuments()) {
            dataList.add(documentSnapshot.getData());
        }
        return dataList;
    }

    // public List<Map<String, Object>> getAllDocuments(String string) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllDocuments'");
    // }
}
