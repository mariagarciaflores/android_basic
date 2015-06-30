package com.possiblelabs.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements View.OnClickListener {

    private final static int BUTTONS_SIZE = 11;
    private Button[] btnNumbers;

    private TextView txtResult;
    private ImageButton btnPlus;
    private ImageButton btnSustract;
    private ImageButton btnMultiply;
    private ImageButton btnDivide;
    private ImageButton btnEquals;
    private ImageButton btnC;
    private Boolean press = false;
    private String operator = "";
    private Boolean isTheResult = false;
    private ExpressionParser expressionParser = new ExpressionParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumbers = new Button[BUTTONS_SIZE];
        txtResult = (TextView) findViewById(R.id.txt_result);
        btnPlus = (ImageButton) findViewById(R.id.btn_plus);
        btnSustract = (ImageButton) findViewById(R.id.btn_minus);
        btnMultiply = (ImageButton) findViewById(R.id.btn_multiply);
        btnDivide = (ImageButton) findViewById(R.id.btn_divide);
        btnEquals = (ImageButton) findViewById(R.id.btn_equals);
        btnC = (ImageButton) findViewById(R.id.btn_c);

        btnNumbers[0] = (Button) findViewById(R.id.btn_0);
        btnNumbers[1] = (Button) findViewById(R.id.btn_1);
        btnNumbers[2] = (Button) findViewById(R.id.btn_2);
        btnNumbers[3] = (Button) findViewById(R.id.btn_3);
        btnNumbers[4] = (Button) findViewById(R.id.btn_4);
        btnNumbers[5] = (Button) findViewById(R.id.btn_5);
        btnNumbers[6] = (Button) findViewById(R.id.btn_6);
        btnNumbers[7] = (Button) findViewById(R.id.btn_7);
        btnNumbers[8] = (Button) findViewById(R.id.btn_8);
        btnNumbers[9] = (Button) findViewById(R.id.btn_9);
        btnNumbers[10] = (Button) findViewById(R.id.btn_dot);

        for (Button btn : btnNumbers) {
            btn.setOnClickListener(this);
        }

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!press) {
                    operator = "+";
                    txtResult.append(operator);
                    isTheResult = false;
                    press = true;
                }
            }
        });

        btnSustract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!press) {
                    operator = "-";
                    txtResult.append(operator);
                    isTheResult = false;
                    press = true;
                }
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!press) {
                    operator = "*";
                    txtResult.append(operator);
                    isTheResult = false;
                    press = true;
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!press) {
                    operator = "/";
                    txtResult.append(operator);
                    isTheResult = false;
                    press = true;
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String current = txtResult.getText().toString();
                    if (current.equals("") || isTheResult) {
                        txtResult.setText("");
                    } else {
                        txtResult.setText(current.substring(0, current.length() - 1));
                    }
            }
        });


        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTheResult = true;
                String operations = txtResult.getText().toString();
                Expression calculations = expressionParser.parse(operations);
                txtResult.setText(calculations.evaluate() + "");
            }
        });


    }

    private void clearDisplay() {
        if (isTheResult) {
            txtResult.setText("");
            isTheResult = false;
        }
    }

    @Override
    public void onClick(View view) {
        Button pressed = (Button) view;
        for (Button btn : btnNumbers) {
            if (pressed == btn) {
                clearDisplay();
                String v = btn.getText().toString();
                txtResult.append(v);
                press = false;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
