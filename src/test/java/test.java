import cn.hutool.core.codec.Base64;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luoli
 * @date 2021/4/21 19:53
 */
public class test {
    private static int num = 0;
    private static BigDecimal unitPrice;
    private static BigDecimal unitPrice2 = new BigDecimal(2);
    public static void main(String[]args){
        System.out.println(unitPrice);
        System.out.println(unitPrice.multiply(unitPrice2));
        String s ="生产年月test1:2021-01,单价:7,入库年月:2021-01-07 16：47：00";
        String[] split = s.split(",");
        System.out.println(split[0]);
        System.out.println(split[1].substring(split[1].indexOf(":") + 1));
        for (String str:split) {
            if (str.contains(":")) {
                String attributeName = str.substring(0, str.indexOf(":"));
                String attributeValue = str.substring(str.indexOf(":") + 1);
                System.out.println(attributeName+": "+attributeValue);
            }
        }

        Date startTime = new Date(Long.valueOf("1620718280626"));
        Date endTime = new Date(Long.valueOf("1622878280626"));
        System.out.println(startTime);
        System.out.println(endTime);

        String a = "伦家是一个非常长的字符串";
        //5Lym5a625piv5LiA5Liq6Z2e5bi46ZW/55qE5a2X56ym5Liy
        String encode = Base64.encode(a);
        System.out.println(encode);
        // 还原为a
        String decodeStr = Base64.decodeStr("$2a$10$Dz3apSlTG/wooeRcurtfQOQbiEy4hQUAKDL/kLcbcJ/GCJldAuXuq");
        System.out.println(decodeStr);

        System.out.println(ColorType.BLUE.type());
        System.out.println(ColorType.BLUE.value());
        System.out.println(ColorType.getTypeByValue(11));
        String Color = ColorType.getTypeByValue(111);
        System.out.println(Color);
//        for(int i=0;i<100000;i++) {
//            num++;
//            num=num%100000;
//            System.out.println(num);
//            System.out.println(new StringBuilder("002020000")
//                    .append(new SimpleDateFormat("YYYYMMdd").format(new Date()))
//                    .append(SequenceCodeUtils.sixSequenceCode(num))
//                    .toString()
//            );
//        }
    }
}
