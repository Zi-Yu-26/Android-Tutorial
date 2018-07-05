package tw.com.chyiiiiiiiiiiii.constraintlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTest1, btnTest2, btnTest3, btnTest4, btnTest5, btnTest6, btnTest7, btnTest8, btnTest9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btnTest1 = findViewById(R.id.button1);
        btnTest2 = findViewById(R.id.button2);
        btnTest3 = findViewById(R.id.button3);
        btnTest4 = findViewById(R.id.button4);
        btnTest5 = findViewById(R.id.button5);
        btnTest6 = findViewById(R.id.button6);
        btnTest7 = findViewById(R.id.button7);
        btnTest8 = findViewById(R.id.button8);
        btnTest9 = findViewById(R.id.button9);
        //
        btnTest1.setOnClickListener(this);
        btnTest2.setOnClickListener(this);
        btnTest3.setOnClickListener(this);
        btnTest4.setOnClickListener(this);
        btnTest5.setOnClickListener(this);
        btnTest6.setOnClickListener(this);
        btnTest7.setOnClickListener(this);
        btnTest8.setOnClickListener(this);
        btnTest9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                openActivity(Test1Activity.class);
                break;
            case R.id.button2:
                openActivity(Test2Activity.class);
                break;
            case R.id.button3:
                openActivity(Test3Activity.class);
                break;
            case R.id.button4:
                openActivity(Test4Activity.class);
                break;
            case R.id.button5:
                openActivity(Test5Activity.class);
                break;
            case R.id.button6:
                openActivity(Test6Activity.class);
                break;
            case R.id.button7:
                openActivity(Test7Activity.class);
                break;
            case R.id.button8:
                openActivity(Test8Activity.class);
                break;
            case R.id.button9:
                openActivity(Test9Activity.class);
                break;
            default:
        }
    }

    public void openActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}