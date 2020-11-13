// 正则表达式





// 分治算法
// 直接函数使用
// 错误前置零
// import java.net.*;
// class Solution {
//     public String validIPAddress(String IP) {
//         try{
//             return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6" : "IPv4";
//         }catch(Exception e){
//         }
//         return "Neither";
//     }
// }



// 正则表达式 和相关函数
// class Solution {
//     public String validIPAddress(String IP) {


        

//     }
// }

// 对于 IPv4 地址，通过界定符 . 将地址分为四块；对于 IPv6 地址，通过界定符 : 将地址分为八块。
// 对于 IPv4 地址的每一块，检查它们是否在 0 - 255 内，且没有前置零。
// 对于 IPv6 地址的每一块，检查其长度是否为 1 - 4 位的十六进制数
// string split
class Solution {
    public String validIPAddress(String IP) {
        if(IP.chars().filter(ch -> ch == '.').count() == 3){
            return validateIPv4(IP);
        }
        else if(IP.chars().filter(ch -> ch == ':').count() == 7){
            return validateIPv6(IP);
        }
        else return "Neither";
    }

    // IPv4 
    public String validateIPv4(String IP){
        String[] nums  = IP.split("\\.", -1);
        for(String x : nums){
            // validate integer in range(0,255)
            if(x.length() == 0 || x.length() > 3) return "Neither";
            // no leading zeros
            if(x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // only digits are allowed
            for(char ch : x.toCharArray()){
                if(!Character.isDigit(ch)) return "Neither";
            }
            // decide range
            if(Integer.parseInt(x) > 255) return "Neither";
        }  
        return "IPv4";
    }

    // IPv6
    public String validateIPv6(String IP){
        String[] nums = IP.split("\\:", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for(String x : nums){
            // at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            //  only hexdigits are allowed: 0-9, a-f, A-F
            for(Character ch : x.toCharArray()){
                if(hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }
}



// python 






















