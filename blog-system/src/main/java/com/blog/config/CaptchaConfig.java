package com.blog.config;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Properties;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.util.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码配置
 * 
 * @author ruoyi
 */
@Configuration
public class CaptchaConfig
{
    @Bean(name = "captchaProducer")
    public TranslucentKaptcha getKaptchaBean()
    {
        TranslucentKaptcha defaultKaptcha = new TranslucentKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "233,190,158");
        // 验证码图片宽度 默认为200
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // 验证码文本字符长度 默认为5
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");

        properties.setProperty(Constants.KAPTCHA_BACKGROUND_IMPL, "com.blog.config.KaptchaBackCreator");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean(name = "captchaProducerMath")
    public TranslucentKaptcha getKaptchaBeanMath()
    {
        TranslucentKaptcha defaultKaptcha = new TranslucentKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 边框颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "233,190,158");
        // 验证码图片宽度 默认为200
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
        // 验证码文本生成器
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL, "com.blog.config.KaptchaTextCreator");
        // 验证码文本字符间距 默认为2
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
        // 验证码文本字符长度 默认为5
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 验证码噪点颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, "white");
        // 干扰实现类
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    static class TranslucentKaptcha extends Configurable implements Producer
    {
        private int width = 200;
        private int height = 50;

        public TranslucentKaptcha() {
        }

        public BufferedImage createImage(String text) {
            WordRenderer wordRenderer = this.getConfig().getWordRendererImpl();
            GimpyEngine gimpyEngine = this.getConfig().getObscurificatorImpl();

            boolean isBorderDrawn = this.getConfig().isBorderDrawn();
            this.width = this.getConfig().getWidth();
            this.height = this.getConfig().getHeight();
            BufferedImage bi = wordRenderer.renderWord(text, this.width, this.height);
            bi = gimpyEngine.getDistortedImage(bi);

            String paramName = Constants.KAPTCHA_BACKGROUND_IMPL;
            String paramValue = this.getConfig().getProperties().getProperty(paramName);

            if ( paramValue != null && !paramValue.isEmpty()) {
                BackgroundProducer backgroundProducer = this.getConfig().getBackgroundImpl();
                bi = backgroundProducer.addBackground(bi);
            }

            Graphics2D graphics = bi.createGraphics();
            if (isBorderDrawn) {
                this.drawBox(graphics);
            }
            return bi;
        }

        private void drawBox(Graphics2D graphics) {
            Color borderColor = this.getConfig().getBorderColor();
            int borderThickness = this.getConfig().getBorderThickness();
            graphics.setColor(borderColor);
            if (borderThickness != 1) {
                BasicStroke stroke = new BasicStroke((float) borderThickness);
                graphics.setStroke(stroke);
            }

            Line2D line1 = new Line2D.Double(0.1D, 0.1D, 0.1D, (double) this.width);
            graphics.draw(line1);
            Line2D line2 = new Line2D.Double(0.1D, 0.1D, (double) this.width, 0.1D);
            graphics.draw(line2);
            line2 = new Line2D.Double(0.1D, (double) (this.height - 1), (double) this.width, (double) (this.height - 1));
            graphics.draw(line2);
            line2 = new Line2D.Double((double) (this.width - 1), (double) (this.height - 1), (double) (this.width - 1), 0.1D);
            graphics.draw(line2);
        }

        public String createText() {
            return this.getConfig().getTextProducerImpl().getText();
        }
    }

}
