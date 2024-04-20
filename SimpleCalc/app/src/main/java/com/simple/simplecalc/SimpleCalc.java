package com.simple.simplecalc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SimpleCalc extends AppCompatActivity implements View.OnClickListener {
    EditText edit1, edit2, output;
    Button btn_add, btn_sub, btn_mul, btn_div;
    TextView text_Result;
    String num1, num2;
    Integer result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.Edit_1);
        edit2 = findViewById(R.id.Edit_2);
        //edit1.setFocusable(false);
        edit1.requestFocus();
        //edit2.requestFocus();

        //EditText의 글씨가 변경될때 마다 반응하기 - 텍스트변경리스터 TextChangedListener로서 TextWatcher객체를 전달
        edit1.addTextChangedListener(new TextWatcher() {

            //텍스트가 변경되기 이전에 자동 실행되는 메소드 - 변경 이전의 텍스트를 얻어올 때 활용가능
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                String beforeMessage = charSequence.toString();
                //Toast.makeText(getApplicationContext(),beforeMessage,Toast.LENGTH_LONG).show();
            }

            //텍스트가 변경되었을 때 자동 실행되는 메소드 - 변경된 텍스트를 얻어올 때 활용
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //첫번재 파라미터 charSequence : 현재 EditText에 써있는 글씨

                // 전화번호의 첫번째 자리는 010 처럼 3자리 이므로..
                //EditText에 작성된 글씨(String)의 길이(글자수)가 3가 이상인가? - 그럼 edit02객체가 포커스를 가지도록 요청
                if(charSequence.length()>=3){
                    edit2.requestFocus();//포커스 요청
                }
            }

            //텍스트변경 작업이 완료된 후 자동 실행되는 메소드
            @Override
            public void afterTextChanged(Editable editable) {
                char[] postMessage = new char[editable.length()];
                editable.getChars(0,editable.length(),postMessage, 0);
                String message = String.valueOf(postMessage);
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);

        text_Result = findViewById(R.id.Text_result);
        output = findViewById(R.id.result_value);
        // 오른쪽 정렬
        //content.setGravity(Gravity.RIGHT);
        // 편집 불가능
        output.setEnabled(false);

        btn_add.setOnClickListener(v -> {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();
            result = Integer.parseInt(num1) + Integer.parseInt(num2);
            output.setText(String.valueOf(result));
        });

 //           @Override
//            public void onClick(View v) {
//                num1 = edit1.getText().toString();
//                num2 = edit2.getText().toString();
//                result = Integer.parseInt(num1) + Integer.parseInt(num2);
//                output.setText(result.toString());
//            }
//        });


        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                output.setText(String.valueOf(result));
            }
        });

        btn_mul.setOnClickListener(new Multipler());
        btn_div.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        num1 = edit1.getText().toString();
        num2 = edit2.getText().toString();
        Double result = 0.0;

        if (v.getId() == R.id.btn_div) {
            if(Integer.parseInt(num2) != 0 )
                result = Double.parseDouble(num1) / Integer.parseInt(num2);

        }
        output.setText(String.format("%6.2f", result));
    }

    // 내부 클래스
    public class Multipler implements View.OnClickListener {
        int num1, num2;
        @Override
        public void onClick(View v) {
            num1 = Integer.parseInt(edit1.getText().toString());
            num2 = Integer.parseInt(edit2.getText().toString());
            result = num1 * num2;
            output.setText(String.valueOf(result));
        }
    }

    public void power(View v){
        int num1 = Integer.parseInt(edit1.getText().toString());
        int num2 = Integer.parseInt(edit2.getText().toString());

        double result = Math.pow((double)num1, (double)num2);
        output.setText(String.valueOf(result));
    }

}