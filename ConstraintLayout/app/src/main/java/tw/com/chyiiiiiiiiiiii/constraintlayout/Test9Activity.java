package tw.com.chyiiiiiiiiiiii.constraintlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;

public class Test9Activity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout constraintLayout;
    private Placeholder placeholder;

    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test9);
        //
        constraintLayout = findViewById(R.id.constraintLayout);
        placeholder = findViewById(R.id.placeholder);
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        //
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        placeholder.setContentId(view.getId());
    }

}
