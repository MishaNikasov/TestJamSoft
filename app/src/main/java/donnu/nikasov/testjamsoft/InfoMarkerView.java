package donnu.nikasov.testjamsoft;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Миша on 09.06.2018.
 */

public class InfoMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */

    private TextView tvContent;

    public InfoMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(R.id.tvContent);
    }

//    private MPPointF mOffset;
//
//    @Override
//    public MPPointF getOffset() {
//        return new MPPointF(0, 0);
//    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {

        super.draw(canvas, 80, 40);
    }

//    @Override
//    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
//        return super.getOffsetForDrawingAtPoint(posX, posY);
//    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Automobile automobile = Automobile.getAutoInPosition(e.getX(),e.getY());

        String string = "<b>Цена:</b> $" + NumberFormat.getNumberInstance(Locale.US).format(automobile.getPrice()) +
                "<br><b>Макс. Скорость:</b> " + NumberFormat.getNumberInstance(Locale.US).format(automobile.getSpeed()) + " км/ч</br>" +
                "<br><b>Вес:</b> " + NumberFormat.getNumberInstance(Locale.US).format(automobile.getWeight()) + " кг</br>" +
                "<br><b>Модель авто:</b> " + String.valueOf(automobile.getModelName()) + "</br>" ;

        tvContent.setText(Html.fromHtml(string));
        super.refreshContent(e, highlight);
    }
}
