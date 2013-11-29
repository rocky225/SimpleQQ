package iqq.app.service;

import com.alee.extended.painter.Painter;
import iqq.app.core.IMService;

/**
 * <b>配置相关服务</b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:43 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface IMSkinService extends IMService {

    public Painter<?> getPainter(Type type, String key);

    public enum Type {
        /**
         * 普通图标
         */
        ICON("icon"),
        /**
         * 九格图片
         */
        NPICON("npicon"),
        /**
         * XML资源文件
         */
        RESOURCE("resource");
        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

//        public static Type valueOfRaw(String txt) {
//            if (txt.equals(NPICON.getName())) {
//                return NPICON;
//            } else if (txt.equals(RESOURCE.getName())) {
//                return RESOURCE;
//            } else {
//                return ICON;
//            }
//        }
    }
}
