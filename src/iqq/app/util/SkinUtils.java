package iqq.app.util;

import com.alee.extended.painter.Painter;
import iqq.app.IMApp;
import iqq.app.core.IMService;
import iqq.app.service.IMSkinService;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:41 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class SkinUtils {
    public static Painter<?> getPainter(IMSkinService.Type type, String key) {
        return getService().getPainter(type, key);
    }

//    public static ImageIcon getImageIcon(String key) {
//        return getService().getImageIcon(key);
//    }
//    public static ImageIcon getImageIcon(String key, Object... params) {
//        return getService().getImageIcon(key, params);
//    }
//
//    public static void setString(Type type, String key, String value) {
//        getService().setString(type, key, value);
//    }

    public static IMSkinService getService() {
        return (IMSkinService) IMApp.me().getSerivce(IMService.Type.SKIN);
    }
}
