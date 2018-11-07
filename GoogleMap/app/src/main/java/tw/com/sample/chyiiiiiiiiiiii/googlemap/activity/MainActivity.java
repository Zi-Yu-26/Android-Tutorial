package tw.com.sample.chyiiiiiiiiiiii.googlemap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.sample.chyiiiiiiiiiiii.googlemap.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTest1, btnTest2, btnTest3, btnTest4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btnTest1 = findViewById(R.id.btnTest1);
        btnTest2 = findViewById(R.id.btnTest2);
        btnTest3 = findViewById(R.id.btnTest3);
        btnTest4 = findViewById(R.id.btnTest4);
        //
        btnTest1.setOnClickListener(this);
        btnTest2.setOnClickListener(this);
        btnTest3.setOnClickListener(this);
        btnTest4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnTest1:
                intent = new Intent(this, Test1Activity.class);
                break;
            case R.id.btnTest2:
                intent = new Intent(this, Test2Activity.class);
                break;
            case R.id.btnTest3:
                intent = new Intent(this, Test3Activity.class);
                break;
            case R.id.btnTest4:
                intent = new Intent(this, Test4Activity.class);
                break;
            default:
        }
        startActivity(intent);
    }

}
