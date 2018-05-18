package com.morningstar.billing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    Button mButton1,mButton2,orderButton;
    TextView t1,t2;
    float total;
    EditText mEdit_pname, mEdit_price, mEdit_pquantity,name,m_no,discEdit;
    ArrayList<String> arrproduct = new ArrayList<String>();
    ArrayList<String> arrquantity = new ArrayList<String>();
    ArrayList<String> arrprice = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderButton = findViewById(R.id.order);
        orderButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               float p=calculate(arrprice, arrquantity, arrproduct);

                                               String s=valueOf(p);
                                               t1=findViewById(R.id.summary);

                                               name=findViewById(R.id.name);
                                               m_no=findViewById(R.id.mobile);
                                               t1.setText(name.getText().toString()+" has due "+s+"\n"+"mobile no: "+m_no.getText().toString());

                                           }
                                       });
                mButton1 = findViewById(R.id.addItem);
                mButton1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        mEdit_pname = findViewById(R.id.nameOfProd);

                        mEdit_pquantity = findViewById(R.id.Quantity);

                        mEdit_price = findViewById(R.id.price);
                        discEdit=findViewById(R.id.discount);

                        arrproduct.add(mEdit_pname.getText().toString());
                        arrquantity.add(mEdit_pquantity.getText().toString());
                        if(Float.parseFloat(discEdit.getText().toString())>0.0) {

                            float f = Float.parseFloat(mEdit_price.getText().toString()) * (100-Float.parseFloat(discEdit.getText().toString()))/100;
                            arrprice.add(valueOf(f));
                        }


                        mEdit_pname.setText(" ");
                        mEdit_pquantity.setText("0");
                        mEdit_price.setText("0.0");
                        discEdit.setText("0.0");

                    }

                });
    }
    public float calculate(ArrayList<String> arrprice,ArrayList<String> arrquantity,ArrayList<String> arrproduct){
                for(int i=0;i<arrproduct.size();i++)
                {
                    float a=Float.parseFloat(arrprice.get(i));
                    float b=Float.parseFloat(arrquantity.get(i));
                    total+=a*b;

                }
                arrprice.clear();
                arrproduct.clear();
                arrquantity.clear();
                name.setText(" ");
                m_no.setText("0");

        return total;
    }
}
