package urp.arch.simuladorcredito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import urp.arch.simuladorcredito.model.SimulacionCredito;
import urp.arch.simuladorcredito.model.SimulacionCuota;

public class SimulacionCuotasActivity extends AppCompatActivity {

    SimulacionCredito sim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion_cuotas);

        // Rretrieve object in the intent
        sim = (SimulacionCredito) getIntent().getSerializableExtra(Simulador.SIMULACION);

        showTable();
    }

    //TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
    //TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

    private void showTable() {


        TableLayout tableLayout = (TableLayout)findViewById(R.id.simulador_table);
        tableLayout.removeAllViews();


        TableRow header = new TableRow(new ContextThemeWrapper(this, R.style.AppTheme_TableHeader));

        addCell(header, "#", R.style.AppTheme_TableCellHeader);
        addCell(header, "CAPITAL", R.style.AppTheme_TableCellHeader);
        addCell(header, "INTERÉS", R.style.AppTheme_TableCellHeader);
        addCell(header, "VENCIMIENTO", R.style.AppTheme_TableCellHeader);
        addCell(header, "CUOTA", R.style.AppTheme_TableCellHeader);
        tableLayout.addView(header);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //"yyyy-MM-dd HH:mm:ss"
        for(int i=0;i<sim.cuotas.size();i++) {
            SimulacionCuota cuota = sim.cuotas.get(i);
            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setMinimumHeight(40);
            //tableRow.setLayoutParams(tableParams);
            addCell(tableRow, String.format("%d", cuota.numero), R.style.AppTheme_TableCell);
            addCell(tableRow, String.format("%.2f", cuota.capital), R.style.AppTheme_TableCell);
            addCell(tableRow, String.format("%.2f", cuota.interes), R.style.AppTheme_TableCell);
            addCell(tableRow, dateFormat.format(cuota.vencimiento), R.style.AppTheme_TableCell);
            addCell(tableRow, String.format("%.2f", cuota.cuota), R.style.AppTheme_TableCell);

            tableLayout.addView(tableRow);
        }
    }

    private void addCell(TableRow tableRow, String content, int style){
        final TextView columView = new TextView(new ContextThemeWrapper(this, style));
        //columView.setLayoutParams(rowParams);
        columView.setText(content);

        tableRow.addView(columView);
    }
}
