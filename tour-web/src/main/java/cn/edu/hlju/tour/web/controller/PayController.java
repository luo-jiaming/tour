package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.common.utils.PaymentUtil;
import cn.edu.hlju.tour.common.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by m on 2017/5/20.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);


    @RequestMapping("/pay")
    @ResponseBody
    public String pay() {

        String p1_MerId = "10001126856";
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String p8_Url = "http://localhost:8080/tour/pay?back";
        // 这个地址是用来接收易宝支付返回结果的路径.这个路径必须外网可以访问.
        StringBuilder url = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
        int max = 10000;
        int min = 1000;
        Random random = new Random();
        int order = random.nextInt(max) % (max - min + 1) + min;
        String p0_Cmd = "Buy";
        String p2_Order = "156365465" + order;
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        String p9_SAF = "";
        String pa_MP = "";
        String pd_FrpId = "CMBCHINA-NET-B2C";
        String pr_NeedResponse = "1";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
        //创建易宝的URL
        url.append("?p0_Cmd=").append(p0_Cmd);
        url.append("&p1_MerId=").append(p1_MerId);
        url.append("&p2_Order=").append(p2_Order);
        url.append("&p3_Amt=").append(p3_Amt);
        url.append("&p4_Cur=").append(p4_Cur);
        url.append("&p5_Pid=").append(p5_Pid);
        url.append("&p6_Pcat=").append(p6_Pcat);
        url.append("&p7_Pdesc=").append(p7_Pdesc);
        url.append("&p8_Url=").append(p8_Url);
        url.append("&p9_SAF=").append(p9_SAF);
        url.append("&pa_MP=").append(pa_MP);
        url.append("&pd_FrpId=").append(pd_FrpId);
        url.append("&pr_NeedResponse=").append(pr_NeedResponse);
        url.append("&hmac=").append(hmac);
        logger.info(url.toString());
        return url.toString();
    }

    @RequestMapping("/back")
    public String back(HttpServletRequest request) {
        /*
         * 1. 获取11 + 1
		 */
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");

        String hmac = request.getParameter("hmac");
        /*
         * 2. 校验访问者是否为易宝！
		 */
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

        boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);

        if (!bool) {//如果校验失败
            request.setAttribute("msg", "您不是什么好东西！");
            return "forward:signin.jsp";
        }

		/*
         * 4. 判断当前回调方式
		 *   如果为点对点，需要回馈以success开头的字符串
		 */
        if (r9_BType.equals("2")) {
            logger.info("success");
        }

		/*
         * 5. 保存成功信息，转发到msg.jsp
		 */
        request.setAttribute("msg", "支付成功！等待卖家发货！你慢慢等~");
        return "forward:hotel.jsp";
    }
}
