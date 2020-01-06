package function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenvo
 */
public class DateFormating {
    private SimpleDateFormat sdf;
    
    public DateFormating() {
        sdf = new SimpleDateFormat("HH'h' mm'm' ss.SSS's' MMMM dd yyyy", Locale.ENGLISH);
    }
    
    public long stringToLong(String datetime) {
        Calendar cal = Calendar.getInstance();
        
        try {
            cal.setTime(sdf.parse(datetime));
            
        } catch (ParseException ex) {
            Logger.getLogger(DateFormating.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cal.getTime().getTime();
    }
    
    public String longToString(long datetime) {
        return sdf.format(datetime);
    }
    
}
