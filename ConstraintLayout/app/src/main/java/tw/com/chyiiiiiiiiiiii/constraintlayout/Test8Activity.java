package tw.com.chyiiiiiiiiiiii.constraintlayout;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Test8Activity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test8);
        //
        mConstraintLayout = findViewById(R.id.constraintLayout);
        group = findViewById(R.id.group);
        //
        mConstraintLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (group.getVisibility() == View.VISIBLE) {
            group.setVisibility(View.GONE);
        } else {
            group.setVisibility(View.VISIBLE);
        }
    }

}
