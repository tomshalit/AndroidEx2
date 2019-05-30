package com.example.androidex2;

import android.text.Html;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Server {

    public static class Question{
        String question;
        String correctAnswer;
        ArrayList<String> answers = new ArrayList<>();

        public Question(){}
        public Question(JSONObject first){
            try {
                    question =  Html.fromHtml(first.getString("question")).toString();
                    correctAnswer =  Html.fromHtml(first.getString("correct_answer")).toString();
                  //  answers.add(correctAnswer);
                    JSONArray wrong_ans = first
                            .getJSONArray("incorrect_answers");
                    for (int i = 0; i < wrong_ans.length(); i++) {
                        Log.i( "i", "Question: " + i);
                        answers.add( Html.fromHtml(wrong_ans.getString(i)).toString());
                    }

            } catch (Exception e){
                Log.e("error", e.toString());
            }
        }
    }

    public interface HandleQuestion{
        void handleQuestion(Question q);
    }

    public static void getTriviaQuestion(final HandleQuestion h, String category, String difficulty){

        String url = "https://opentdb.com/api.php?amount=1&category=" +category+ "&difficulty=" +difficulty+ "&type=multiple";

       // String url = "https://opentdb.com/api.php?amount=1&category=9&difficulty=medium&type=multiple";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    //if results.length() == 0 ?
                    JSONObject first = results.getJSONObject(0);
                    h.handleQuestion(new Question(first));
                } catch (Exception e){

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                //handle array
            }
        });
    }
}
