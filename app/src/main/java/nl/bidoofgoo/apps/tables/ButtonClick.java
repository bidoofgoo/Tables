package nl.bidoofgoo.apps.tables;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonClick extends AppCompatActivity{

    // Bestand dat er toe dient om een gemakkelijkere manier te maken om buttons te stylen en
    // functies hier aantoe te voegen.

    // Add a function to a button.
    public static void setButtonClickFunction(Button button, Resources resources, Function function){
        new ButtonClick().doTheThings(button,resources, function);
    }

    // Add pretties to the button
    public static void makeButtonPretty(Button button, Resources resources){
        new ButtonClick().doTheThings(button, resources,new Function() {
            @Override
            public void whatToDo() {

            }
        });
    }

    // Do the hard work, do the function passed as parameter
    private static void doTheThings(Button button, final Resources resources, final Function function){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        ((TextView) view).setTextColor(resources.getColor(R.color.colorPrimaryDark));
                        view.setBackgroundResource(R.drawable.roundedbuttondown);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        ((TextView) view).setTextColor(resources.getColor(R.color.colorPrimary));
                        view.setBackgroundResource(R.drawable.roundedbutton);
                        function.whatToDo();
                        return true;
                }
                return false;
            }
        });
    }
}
