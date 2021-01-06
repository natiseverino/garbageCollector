package carpinteroseverino.com.garbagecollector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle("Developers");

        LinearLayout layoutBautista = findViewById(R.id.bautista_layout);
        layoutBautista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String emailBautista = "bj.carpintero@gmail.com";
                String subject = "";
                String body = "";
                Uri data = Uri.parse("mailto:" + emailBautista +"?subject="+subject+"&body="+body);
                intent.setData(data);
                startActivity(intent);
            }
        });

        LinearLayout layoutNati = findViewById(R.id.nati_layout);
        layoutNati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String emailNati = "natiseverino96@gmail.com";
                String subject = "";
                String body = "";
                Uri data = Uri.parse("mailto:" + emailNati + "?subject="+subject+"&body="+body);
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
