import javax.swing.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateFormatter extends JFormattedTextField.AbstractFormatter {

    private SimpleDateFormat dateFormat;

    public SqlDateFormatter(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        return new Date(dateFormat.parse(text).getTime());
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value == null) { return  "" ;}
        if (value instanceof java.util.Calendar) {
            java.util.Calendar calendar = (java.util.Calendar) value;
            java.util.Date utilDate = calendar.getTime();
            return dateFormat.format(utilDate);
        }
        throw new ParseException("error",0);
    }
}
