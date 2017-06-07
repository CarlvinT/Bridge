package nl.ben_ey.bridge.animations;

/**
 * Created by Ben on 2-6-2017.
 */

public class BtnBounceInterpolator implements android.view.animation.Interpolator {
    double mAmplitude = 1;
    double mFrequency = 10;

    public BtnBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time){
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }

}
