package cn.esports.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2018040202491200";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxwAEczlZXHGF7mSdlLENUP6btH5pikryZ0rq+ySu2ap3Wtt2+Yk+kz92i8OncOb7ENWs/14D7VH5v9nBNTpcOVPEQhEtW9TlHVVozQi9zw+RLGU9PnwpIoGNDrn0PpMa1pZbTJbDJN1JFDVmd2WbC02Yu3GqASo/y/6QwlBQZhoTLlUQKpih0JHISCmaKpxQAtd77cW/45YgyNOB7VPN1LQGnOhQwvfnBqY9ErZhHSrm5b5BqbXVrS3RQT1WzJAnBHRUqEmmQV7wkUcahPp5a8/79NWotEfWauPL/g857bQa6nrb764lp+XWRjJMRWaMB/j60KTnPxxgdEr2Die0hAgMBAAECggEATXJ2SaHR1tTD2MGre5ako3G0fr71WpvK108NFRG8HRgpO6OjzE8zmoEDdfp4Ov9iEkWgPEnepiNzj78VwWlLwe/atTKfTP09/sm+qnTdDhi9QMd/WhbWuTxA4/2D+iv2dLD4nXlfpNyY2QNvF6rg7mSDsklRkiJqxBsE5DGw7EmJZ6dvOiwV5SdEFoBeKoXb/vxJ7blhL1vFTExzBTMbpwGM5CRsxQI7ApVS/kEppXTBLkdNwNy44UQwT5Z2LpVsYXRtVipkpUivOxiB10z2L9Esz6DcLPBEveGp8oMSDLfGrtQXxpL7nzQ2KfBLWfwcokApCHQarsI6d54VccworQKBgQDZj4lhiPMUurmnSqmu+T+nG1Sy23Mx5U1doZhoB+svLl9xDMBkgQzP+K+3d8QPOwE4NY/goK6nJzZS5rCXfd0C1ZJhppi7xGqNPJF7mCMJenOaPmv97Fgw3refNb24xdYKGSHVsH+f/KPbXdwjegNqWCd3wjvbu+YGasOZe9ZedwKBgQDRJ8urH5mY9Aw+UJp0pOKTNuf5I9hrWA1/D5P3+7oomHuSCmS8SZQ0onMeUK5CNXFL6YQwIbmgTTw/0L+RBfjMIvvqQiqkzrPmdbd7Hg+itlE2mCr5dvzd2C3U9ODJdmxBXHnfDmpy4RErA8Qu/FodmnTknTtvUaHINeUONb//JwKBgHgtq+vu5KgsxWU9/yGimaDLniv2wVlsmyH/o9rDlQzJBvvB2X0rNFKlwxsL04e+RlwwajvhRtka9g0xVZc5dZ6dL3E1v+TpCRrfvFWTorcXp0gq9yibJ9RHExOlchnhXuzRGTmbd8R+oF0LKhGG0uApthOXfc1RrC0bSNU97RrdAoGARXLCVNrURI5cdmCLOMn7Fci4c+hsFxCQwqzohAIIySwAc/iRhFemJ2f9c4Z0pjVanks2gkjgexLKO6JTyZLDDv1aymkYActEvvQ9bVsI9A03oDocouLYic0bQsfgz+RLg9Rx/xepTuXernq8rKFMRuIfiD2RG6BuaK1EllhQnScCgYEAovFYkOvMXwzvWKgZcrWGcMoI8POwrf++sajG1B5APIqNmh+EIVAFo9kIk3SGRx2CFflmpQDgpGbZ9Fn86VkOqgqwTM9mxYlbdXX5JPHdyKNrL/kvrR9bMPuFZAFL5uLIqK7h9w82SRryXzRvLvH+4RR0cVZ2FiO0zf7+uDi2tx4=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://39.106.145.173:8087/api/pay/aliNotify.json";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://39.106.145.173:8087/user/pay";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAny7QY0zIfe7xiWuZ7eNaANiXUd6KOhFKPdCndrGNCictCz68k/A872LKFcr2DhV12vNLFj+bneit+X/sDnkjSEjHslZDDfpNJK/wn1sxGKAmWID7zJUymCdvOHovP4O6J1+k6UIj0kwkAlWwPOdpqnT/ityJoGAOHLa7AbQR96dB1FfkEJoOPPfMYTIoIUBGt4P/lTj5M1EmGSdIDBawVZemr864rZ+Hee+LfY4Ere17Jt534pp4/QPm9c1rIQlic2EbxjMig+/n9cAhY8pNYN4FHYJjhaxoyzd+qORy5NbniHbtx+qLNiwP9X1rcHC06EywJW94/23+BcYQ61tJVQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";


    // 商户appid
    //public static String APPID = "2016082100307483";
    // 私钥 pkcs8格式的
    //public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBkk8SSGkY2Fhh8bvrluFXxWdNxYExvuTyOrCShwSHkIet4680BIDq1tvly4EepnmxaRxk3FydEUGpzGiqlaL48aYqLkd7Y76VRY4rspDRKjHMLRqy1TjMfZEOm5Y2Xsqkq9VTu45pYMlwAWKmJ5IjuJdPEP9x0/n17pQQXCtrJyhVmfmV3ayOIvPQlicpiInoqwjLPWPoVkE38cV2X83ArgHncA8qC3OlMg/Pj8K+qLhj1vls8/beoi2F6G3YHE/aRZbVWkMQpEWcnw7kLs0kIcw1qIT0R1HJ13jLjtLQblZq+YpsM3Jmc6Wou6WErLMU3DvwX6UPWfnQ9yJayNGtAgMBAAECggEAF8QzdsILbQA57fU+J+9r9AKEeVJx6SL/saKsQqQ7Y3ZSPMYXm+37i5fPi+3xCCM3DSCfkayN3E2PAss4o5mA5DLG7NPCTtKz4YN5mXBOed9IdRAM5qKPoYqisdA+FEj82ImgPpSXgaK8MCxA4QM/Hgg80MboZAOixMZ3KC0P1b57kYwWh1XjXZ3oMKTAN30VYbB2euCvtVQfmkE+V4ARjFTJFGcgtZ/ThvH+tEakdpq3cE8FL10t/uvr7je8DXOWYcwAfR6ApP092DeLY50mMVLNypSv7kBA7zaOyFfO/21lHEqBE1wLauiQcSwwRJy4um2jhFbXi7RXCc9YYTVegQKBgQDihwd5Ohkt0UPHvKKTV2iCbH3Tcn6JsVMLutaRxW5jas57oxcrp46YR1l2dvCcGmdpyhXPpLOcy8o49gPD08En4ZHYu++BmgCRpspDWmNJK/5ywxJ6FHvOfGUEq9Lax7+Dul5eYWfh1ytARomJJpeDqke2NfJ6KhanMhPfhOOmYQKBgQCSbfWXIuB8Bb24XVw6sRE1vrV0/kwukXtXbTONSggdrrEiBbt8spWJKyvwHzM6YLixHZwpU6tAHI5sVAQMfG5TfhI6JXMQ84AvRqmtzjqkenLjRH3BCsQ5x1BhR3BW3DrKZB5u5Lcamg8aJnJk7W1CLMrfAHYoLegCvGVazZhWzQKBgFoxhUpBVbEmdyRZ+SlNTjFP3TGJjtRoJDaxZ6mWwIMZDamwoBMjneUH05kVdmvRH4fBjLmmTFWL1rKjUZBxCaFFcPqoqY4isUNM84+pt4p019T90qeE7F6krNwVRdkSWzCpzdUqV2BWecQX0b8n/H/SFsd351m41Fgx2jPPB41BAoGAMHds3oWQv/w2e5uLN3H8PEnPTdYN6SwGIz3l3lIutYRo5NX45KFHlYtQk73/+sag2x7t3U/wUtwg2AJHBMr6yW/lIJHcsDOorf05lGm6R06wUEEPTqH9qRIrrMc0i8uBOhRBbnsNkCSF8yXen0Fm9IxW6dEBSvNG5ADvm0qJrdECgYA5Gmi9GnNBkT+7AIHvHcvtdr3wVJ4jtbcDrBp9j8UGQW1fldm9RRba2on4KdU/KQ89EHz7A63O/iFuQwBlKQX0/NkrARuwPSSOVOTuoVRV6MxmzbWbwJjfcb0MhZzpuGjQ6zcy83ZoYhc4gvqfpv9Qehrqj4CDdRiiHkmXNZfFVQ==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String notify_url = "http://39.106.145.173:8087/api/pay/aliNotify.json";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    //public static String return_url = "http://39.106.145.173:8087/user/pay/alipay";
    // 请求网关地址
    //public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    //public static String CHARSET = "UTF-8";
    // 返回格式
    //public static String FORMAT = "json";
    // 支付宝公钥
    //public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvCDyYSb9RgRXxeCdUhsvvv2/V49Kc7qpdYXuCMoyrjdtXsSEXl7hvOBfYQTODe4cYM3liFto0opwlMCa/q4c5qGJaT9dqHU+/FFSz9IZ/g5Mif7vcXLdeMKge6vrYsSMxnVIt+TKCOSQDqP/2GPJl43AzuxnBRxbAsGJEsgKFfa/OkQw7538oJhTHuGkXEpwRSvSfNvYRnFByqaz/POeceR+uuktfwRNxgFmspEWOEWIO4zutQ+8s8k7JqSAgLNI80B8zel5hYz62sPlm4HwVvZ+1n5SQZIDCJCNP7h/1xwrOLkmGO+docr80vXwEvPDsYHAoRM5yR8UH7M+a49FsQIDAQAB";
    // 日志记录目录
    //public static String log_path = "/log";
    // RSA2
    //public static String SIGNTYPE = "RSA2";


}
