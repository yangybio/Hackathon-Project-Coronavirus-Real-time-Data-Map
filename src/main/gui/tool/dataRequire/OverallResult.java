package gui.tool.dataRequire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OverallResult {
    public ArrayList<ProvinceResult> results;


    public int filter(String provinceName, String criteria, ArrayList<Date> date) {
        int result = 0;
        for (ProvinceResult pr : this.results) {
//            System.out.println(pr.provinceName);
//            System.out.println(pr.confirmedCount);
//            System.out.println(pr.inTimeRange(date));
//            System.out.println(pr.provinceName == provinceName );

//            System.out.println(pr.provinceName == provinceName && pr.inTimeRange(date));
            if (pr.provinceName.equals(provinceName) && pr.inTimeRange(date)) {
                if (result < this.getData(pr, criteria)) {
                    result = this.getData(pr, criteria);
                }
            }
        }
        return result;
    }

    private int getData(ProvinceResult pr, String criteria) {
        int result = 0;
        System.out.println("!!!!!!!!!");
        if (criteria == "Confirmed Cases") {
            result = pr.confirmedCount;
        } else if (criteria == "Deaths") {
            result = pr.deadCount;
        } else if (criteria == "Cured") {
            result = pr.curedCount;
        } else if (criteria == "Suspected Cases") {
            result = pr.suspectedCount;
        }
        return result;
    }

    public ArrayList<Date> getDateRange(String singleDate) throws ParseException {
        ArrayList<Date> StartnEnd = new ArrayList<>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(singleDate);
            long endLong = date.getTime() + 24 * 3600000;
            Date endDate = new Date(endLong);
            StartnEnd.add(date);
            StartnEnd.add(endDate);
        } catch (ParseException e) {
            System.out.println("wrong date");
//            return null
//         todo    handle eception here
        }
        return StartnEnd;
    }
    // todo
    public int collectRegionalData(String region, String criteria, ArrayList<Date> date) {
        int headCount = 0;
        if (region.equals("Wuhan")) {
            System.out.println(criteria);
            System.out.println(date);
            headCount += this.filter("?????????", criteria, date);

        } else if (region.equals("NorthEast")) {
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);

        } else if (region.equals("NorthWest")) {
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????????????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);

        } else if (region.equals("SouthEast")) {
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????????????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("??????", criteria, date);
            headCount += this.filter("??????", criteria, date);
            headCount += this.filter("?????????", criteria, date);

        } else if (region.equals("SouthWest")) {
            headCount += this.filter("???????????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);
            headCount += this.filter("?????????", criteria, date);

        }
        return headCount;
    }

}
