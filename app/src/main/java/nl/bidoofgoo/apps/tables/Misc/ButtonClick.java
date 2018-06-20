package nl.bidoofgoo.apps.tables.Misc;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.bidoofgoo.apps.tables.R;

public class ButtonClick extends AppCompatActivity{

    // Bestand dat er toe dient om een gemakkelijkere manier te maken om buttons te stylen en
    // functies hier aantoe te voegen.

    // Add a function to a button.
    public static void setButtonClickFunction(Button button, Resources resources, Function function){
        new ButtonClick().doTheThings(button,resources, function, R.drawable.roundedbutton ,R.drawable.roundedbuttondown);
    }
    public static void setButtonClickFunctionTransp(Button button, Resources resources, Function function){
        new ButtonClick().doTheThings(button,resources, function, R.drawable.roundedbuttontransp ,R.drawable.roundedbuttondown);
    }

    // Add pretties to the button
    public static void makeButtonPretty(Button button, Resources resources){
        new ButtonClick().doTheThings(button, resources,new Function() {
            @Override
            public void whatToDo() {

            }
        }, R.drawable.roundedbutton, R.drawable.roundedbuttondown);
    }

    // Do the hard work, do the function passed as parameter
    private static void doTheThings(Button button, final Resources resources, final Function function, final int drawableUp, final int drawableDown){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        ((TextView) view).setTextColor(resources.getColor(R.color.colorPrimaryDark));
                        view.setBackgroundResource(drawableDown);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        ((TextView) view).setTextColor(resources.getColor(R.color.colorPrimary));
                        view.setBackgroundResource(drawableUp);
                        function.whatToDo();
                        return true;
                }
                return false;
            }
        });
    }
}
