package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import org.dvaletin.apps.nabludatel.R;

public class Tumbler extends SeekBar {
	public static final String TUMBLER_UNDEFINED = "undef";

	private String loValue;
	private String hiValue;
	private String violationText;

	public Tumbler(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initThumbler(attrs);
	}

	public Tumbler(Context context, AttributeSet attrs) {
		super(context, attrs);
		initThumbler(attrs);
	}

	private void initThumbler(AttributeSet attrs) {
		Context c = getContext();
		TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.Tumbler);
		loValue = a.getString(R.styleable.Tumbler_loValue);
		hiValue = a.getString(R.styleable.Tumbler_hiValue);
		violationText = a.getString(R.styleable.Tumbler_violationText);

		// By default on Samsung phones Thumb offset is 15. Reset it to zero.
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
		if (paddingLeft == paddingRight) {
			setThumbOffset(paddingLeft);
		} else {
			throw new IllegalArgumentException("Wrong padding settings: left " + paddingLeft +
					", right " + paddingRight + ". Thiese must be equals.");
		}
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		if (this.getTumblerValue().equals("true")) {
			this.setBackgroundResource(R.drawable.for_frontend_15);
		} else if (this.getTumblerValue().equals("false")) {
			this.setBackgroundResource(R.drawable.for_frontend_11);
		} else {
			this.setBackgroundResource(R.drawable.for_frontend_04);
		}

		super.onDraw(canvas);
	}

	public void setTumbler(String value) {
		if (value.equals(loValue)) {
			this.setProgress(0);
		} else if (value.equals(hiValue)) {
			this.setProgress(getMax());
		} else {
			this.setProgress(1);
		}
	}

	public String getTumblerValue() {
		if (getProgress() == getMax()) {
			return hiValue;
		}
		if (getProgress() == 0) {
			return loValue;
		}
		return TUMBLER_UNDEFINED;
	}

	public String getLoValue() {
		return loValue;
	}

	public String getHiValue() {
		return hiValue;
	}

	public String getViolation() {
		return this.violationText;
	}
}
