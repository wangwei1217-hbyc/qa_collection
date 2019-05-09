package com.wangwei.ip;

/**
 * Created by wangwei on 2019/5/9.
 */
public class IpConvertUtil {
    /**
     * ip 转 int
     * ip的每一段数字转为8位二进制数，斌把它们放在结果的适当位置上。
     * @param ipString
     * @return
     */
    public static int ip2int(String ipString){
        //ip的各段
        String[] ipSliceArray = ipString.split("\\.");
        int rs = 0;
        for(int i= 0;i < ipSliceArray.length;i++){
            //将ip的每一段解析为int，并根据位置左移8位
            int intSlice = Integer.parseInt(ipSliceArray[i]) << 8 * i;
            //求与
            rs = rs | intSlice;
        }
        return rs;
    }

    /**
     * int 转 ip
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt){
        String[] ipString = new String[4];
        for(int i = 0;i < 4;i++){
            //每8位为一段，这里取当前要处理的最高位的位置
            int pos = i * 8;
            //取当前处理的ip段的值
            int and = ipInt & (255 << pos);
            //将当前ip段转换为0~255的数字串，注意这里必须使用无符号右移
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".",ipString);
    }
}
