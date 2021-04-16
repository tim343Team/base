package tim.com.libnetwork.utils;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * $属性动画工具类，以建造者模式封装，提供缩放，移动，旋转，渐变动画。动画执行时间，插值器，动画状态监听
 *
 * @author weiqiliu
 * @version 1.0 2020/2/17
 */
public class AnimationUtil {
    private View view;
    private long delay = 0;
    private long delay1 = 0;
    private List<AnimationAction> animationActionList = new ArrayList<>();
    private long duration;
    private Interpolator interpolator;
    private Animator.AnimatorListener animatorListener;

    public enum AnimationType {
        SCALEY,     //Y轴缩放
        SCALEX,     //X轴缩放
        TRANSLATIONY,   //Y轴移动
        TRANSLATIONX,   //X轴移动
        TRANSLATIONZ,   //Z轴移动
        ROTATIONY,      //Y轴旋转
        ROTATIONX,      //X轴旋转
        ROTATION,       //旋转
        ALPHA       //渐变值
    }

    class AnimationAction {
        public AnimationType type;
        public float value;

        AnimationAction(AnimationType type, float value) {
            this.type = type;
            this.value = value;
        }

        public AnimationType getType() {
            return type;
        }

        public void setType(AnimationType type) {
            this.type = type;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    //设置作用view
    public AnimationUtil setView(View view) {
        this.view = view;
        return this;
    }

    //设置延迟时间
    public AnimationUtil setDetaly(long delay) {
        this.delay = delay;
        return this;
    }

    //设置动画类型及数值
    public AnimationUtil setType(AnimationType type, float value) {
        AnimationAction animationAction = new AnimationAction(type, value);
        animationActionList.add(animationAction);
        return this;
    }

    //设置动画执行时间
    public AnimationUtil setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    //设置插值器
    public AnimationUtil setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    //设置监听
    public AnimationUtil setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
        return this;
    }

    public void build() {
        try {
            view.animate().cancel();
            ViewPropertyAnimator viewPropertyAnimator = view.animate().setStartDelay(delay);
            for (AnimationAction animationAction : animationActionList) {
                viewPropertyAnimator = executeAnimation(viewPropertyAnimator, animationAction.getType(), animationAction.getValue());
            }
            viewPropertyAnimator.setDuration(duration);
            if (interpolator != null) {
                viewPropertyAnimator = viewPropertyAnimator.setInterpolator(interpolator);
            } else {
                viewPropertyAnimator = viewPropertyAnimator.setInterpolator(new LinearInterpolator());
            }
            if (animatorListener != null) {
                viewPropertyAnimator.setListener(animatorListener);
            }
        } catch (Exception e) {
            clear();
        } finally {
            clear();
        }
    }

    private ViewPropertyAnimator executeAnimation(ViewPropertyAnimator viewPropertyAnimator, AnimationType type, float value) throws Exception {
        if (value < 0.0) {
            throw new Exception("没有传递动画所以的值");
        } else {
            switch (type) {
                case SCALEY:
                    return viewPropertyAnimator.scaleY(value);
                case SCALEX:
                    return viewPropertyAnimator.scaleX(value);
                case TRANSLATIONY:
                    return viewPropertyAnimator.translationY(value);
                case TRANSLATIONX:
                    return viewPropertyAnimator.translationX(value);
                case TRANSLATIONZ:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        return viewPropertyAnimator.translationZ(value);
                    }else {
                        throw new Exception("手机版本不支持该动画");
                    }
                case ROTATIONY:
                    return viewPropertyAnimator.rotationY(value);
                case ROTATIONX:
                    return viewPropertyAnimator.rotationX(value);
                case ROTATION:
                    return viewPropertyAnimator.rotation(value);
                case ALPHA:
                    return viewPropertyAnimator.alpha(value);
                default:
                    throw new Exception("动画类型不支持");
            }
        }
    }

    private void clear() {
        view = null;
        delay = 0;
        animationActionList.clear();
        interpolator = null;
        duration = 0;
        animatorListener = null;
    }
}
