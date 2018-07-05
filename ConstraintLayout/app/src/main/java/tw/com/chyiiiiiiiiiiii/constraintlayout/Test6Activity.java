package tw.com.chyiiiiiiiiiiii.constraintlayout;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;

public class Test6Activity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout mConstraintLayout;

    private ConstraintSet mConstraintSet1 = new ConstraintSet(); // create a Constraint Set
    private ConstraintSet mConstraintSet2 = new ConstraintSet(); // create a Constraint Set

    private int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);
        //
        mConstraintLayout = findViewById(R.id.constraintLayout);
        mConstraintLayout.setOnClickListener(this);
        //
        init();
    }

    public void init() {
        mConstraintSet1.clone(mConstraintLayout);
        //
        mConstraintSet2.clone(this, R.layout.activity_test6_2);
    }

    @Override
    public void onClick(View view) {
        doAnimation();
    }

    public void doAnimation() {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        if (count == 1) {
            mConstraintSet1.setVerticalBias(R.id.button1, (float) 0.5);
            mConstraintSet1.setVerticalBias(R.id.button2, (float) 0.5);
            mConstraintSet1.setVerticalBias(R.id.button3, (float) 0.5);
            mConstraintSet1.applyTo(mConstraintLayout);
            count++;
        } else if (count == 2) {
            mConstraintSet2.applyTo(mConstraintLayout);
            count++;
        } else {
            mConstraintSet1.setVerticalBias(R.id.button1, 1);
            mConstraintSet1.setVerticalBias(R.id.button2, 1);
            mConstraintSet1.setVerticalBias(R.id.button3, 1);
            mConstraintSet1.applyTo(mConstraintLayout);
            count = 1;
        }
    }

}
