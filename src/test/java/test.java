import cn.hutool.core.codec.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luoli
 * @date 2021/4/21 19:53
 */
public class test {
    private static int num = 0;
    public static void main(String[]args){
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
