GetLoginSigAction.java
用来获取login_sig

CheckVerifyAction.java
https://ssl.ptlogin2.qq.com/check?uin=1431444862&appid=1003903&js_ver=10038&js_type=0&login_sig=v6Lv7W4zArmx1OH0J8jhWxUaksxFj8aKLTmRwnr*eV0Wz7bnVlxY2uYj1mGcAHKW&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=0.04289448520406802
https://ssl.ptlogin2.qq.com/check?uin=   {qq号}    &appid=1003903&js_ver=10038&js_type=0&login_sig=                    {login_sig}                              &u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r={随机数}

GetCaptchaImageAction.java
http://captcha.qq.com/getimage?r=0.31845641666674285&uin=1431444862&aid=1003903

WebLoginAction.java
https://ssl.ptlogin2.qq.com/login?from_ui=1&g=1&login2qq=1&pttype=1&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&webqq_type=10&daid=164&dumy=&ptlang=2052&aid=1003903&h=1&login_sig=v6Lv7W4zArmx1OH0J8jhWxUaksxFj8aKLTmRwnr*eV0Wz7bnVlxY2uYj1mGcAHKW&js_type=0&u=1431444862&t=1&p=84E236FEFB4282B7E98329791144A33F&action=4-28-1632882&remember_uin=1&verifycode=UVUV&mibao_css=m_webqq&js_ver=10038&fp=loginerroralert&ptredirect=0

CheckLoginSigAction.java
http://ptlogin4.web2.qq.com/check_sig?pttype=1&uin=1431444862&service=login&nodirect=0&ptsig=C1xuSf31qlTlWKbeKLDst1MdLOUfk2yagCMfbi5egBU_&s_url=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&f_url=&ptlang=2052&ptredirect=100&aid=1003903&daid=164&j_later=0&low_login_hour=0&regmaster=0

ChannelLoginAction.java
http://d.web2.qq.com/channel/login2

HttpActor.java
    execute : 远程

ApacheHttpService.java
    executeHttpRequest : POST or GET method

AbstractHttpAction.java
    onHttpFinish : 调用onHttpStatusOK方法