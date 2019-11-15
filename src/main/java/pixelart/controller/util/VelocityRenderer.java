package pixelart.controller.util;

import spark.ModelAndView;

import java.util.HashMap;

public class VelocityRenderer {
    public static String render(HashMap<String, Object> params, String template) {
        return new VelocityTemplateEngine().render(new ModelAndView(params, template));
    }

    public static String render(String template) {
        return new VelocityTemplateEngine().render(new ModelAndView(new HashMap<>(), template));
    }
}
