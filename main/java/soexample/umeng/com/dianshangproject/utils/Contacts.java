package soexample.umeng.com.dianshangproject.utils;

public class Contacts {
    //接口前缀      外网mobile.bwstudent.com    内网172.17.8.100
    public static final String BASEURL = "http://172.17.8.100/small/";

    //1、注册   15830770465
    public static final String REGISTER = "user/v1/register";
    //2、登录
    public static final String LOGIN = "user/v1/login";
    //3、修改昵称
    public static final String UPDATENICK = "user/verify/v1/modifyUserNick";
    //4、修改密码
    public static final String UPDATEPWD = "user/verify/v1/modifyUserPwd";
    //5、用户上传头像
    public static final String HEADPIC = "user/verify/v1/modifyHeadPic";
    //6、根据用户ID查询用户信息
    public static final String GETURSEBYID = "user/verify/v1/getUserById";
    //7、收货地址列表
    public static final String ADDRESSLIST = "user/verify/v1/receiveAddressList";
    //8、新增收货地址
    public static final String ADDADDRESS = "user/verify/v1/addReceiveAddress";
    //9、设置默认收货地址
    public static final String MORENADDRESS = "user/verify/v1/setDefaultReceiveAddress";
    //10、修改收货信息
    public static final String UPDATEADDRESS = "user/verify/v1/changeReceiveAddress";
    //11、查询用户钱包
    public static final String WALLET = "user/verify/v1/findUserWallet";
    //12、轮播
    public static final String LUNBO = "commodity/v1/bannerShow";
    //13、热销新品、魔力时尚、品质生活
    public static final String REXIAO = "commodity/v1/commodityList";
    //14、根据关键次查新商品信息
    public static final String SEARCHGOODS = "commodity/v1/findCommodityByKeyword";
    //15、圈子列表
    public static final String CIRCLELIST = "circle/v1/findCircleList";
    //16、根据商品列表归属标签查询商品信息
    public static final String GOODSINFO = "commodity/v1/findCommodityListByLabel";
    //17、点赞
    public static final String GREAT = "circle/verify/v1/addCircleGreat";
    //18、取消点赞
    public static final String CANCLEGREAT = "circle/verify/v1/cancelCircleGreat";
    //19、我的圈子
    public static final String MYCIRCLE = "circle/verify/v1/findMyCircleById";
    //20、我的足迹
    public static final String MYFOOT = "commodity/verify/v1/browseList";
    //21、商品详情
    public static final String GOODSDETAILS= "commodity/v1/findCommodityDetailsById";
    //22、同步购物车数据
    public static final String SHOPCARDATA= "order/verify/v1/syncShoppingCart";
    //23、查询购物车
    public static final String ADDSHOPCAR = "order/verify/v1/findShoppingCart";
    //24、创建订单
    public static final String WAITPAY = "order/verify/v1/createOrder";
    //25、根据订单状态查询订单信息
    public static final String ORDERINFO = "order/verify/v1/findOrderListByStatus";
    //26、支付
    public static final String GOPAY = "order/verify/v1/pay";
    //27、删除订单
    public static final String DELLIST = "order/verify/v1/deleteOrder";
}
