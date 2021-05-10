/**
 * @author luoli
 * @date 2021/4/21 20:20
 */
public class SequenceCodeUtils {

    //生成6位数字顺序码
    public static String sixSequenceCode(int num) {
        num = num %999999;
        StringBuilder numbuilder = new StringBuilder();
        if(num>=100000){
            numbuilder.append(num);
        }else if(num>=10000){
            numbuilder.append("0").append(num);
        }else if(num>=1000){
            numbuilder.append("00").append(num);
        }else if(num>=100){
            numbuilder.append("000").append(num);
        }else if(num>=10){
            numbuilder.append("0000").append(num);
        }else{
            numbuilder.append("00000").append(num);
        }
        return numbuilder.toString();
    }
}
