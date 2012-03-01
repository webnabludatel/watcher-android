package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ReportImageView extends View {
	int goodCount = -1;
	int badCount = -1;
	
	public ReportImageView(Context c){
		super(c);
	}
	public ReportImageView(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	public ReportImageView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	public void setNewValues(int good, int bad){
		badCount = bad;
		goodCount = good;
	}
	
	@Override 
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(goodCount == -1 || badCount == -1){
			return;
		}
		if ( this.goodCount == 0 && this.badCount == 0 ) {
			Path arcPath = new Path();
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2*0.8f, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	        p.setColor(Color.rgb(180, 180, 180));
	        canvas.drawPath(arcPath, p);
	    } else if ( this.goodCount == 0 && this.badCount > 0) {
	        // #df2a00
			Path arcPath = new Path();
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2*0.8f, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	        p.setColor(Color.rgb(0xdf, 0x2a, 0));
	        canvas.drawPath(arcPath, p);

	    } else if ( this.goodCount > 0 && this.badCount == 0 ) {
	        // #0077cb
	    	Path arcPath = new Path();
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2*0.8f, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	        p.setColor(Color.rgb(0, 0x77, 0xcb));
	        canvas.drawPath(arcPath, p);
	    	
	    } else {
	        float n = (float) Math.sqrt((double)this.goodCount);
	        float m = (float) Math.sqrt((double)this.badCount);
	        float x = (n+m)/2.0f;
	        float deltaX;
	        float deltaY;
	        float scaleX;
	        float scaleY;
	        if ( n > m ) {
	            scaleX = getWidth()/(2*n+2*m+x);
	        	scaleY = getHeight()/(2*n);
	            deltaX = n*scaleX;
	            deltaY = n*scaleY;
	        } else {
	            scaleX = getWidth()/(2*n+2*m+x);
	        	scaleY = getHeight()/(2*m);
	           	deltaX = n*scaleX;
	           	deltaY = m*scaleY;
	        }
	        
	        CGPoint p1 = new CGPoint(-n, 0);
	        CGPoint p2 = new CGPoint(0, n);
	        CGPoint p3 = new CGPoint(n+x*n/(n+m), 0);
	        CGPoint p4 = new CGPoint(0, -n);
	        
	        CGPoint p5 = new CGPoint(n+x*n/(n+m), 0);
	        CGPoint p6 = new CGPoint(n+m+x, m);
	        CGPoint p7 = new CGPoint(n+2*m+x, 0);
	        CGPoint p8 = new CGPoint(n+m+x, -m);
	        
	        Path path1 = new Path();
	        path1.moveTo(p1.x*scaleX+deltaX, p1.y*scaleY+deltaY);
	        
	        path1.cubicTo(p1.x*scaleX+deltaX, (p1.y+n*11/20)*scaleY+deltaY, (p2.x-n*11/20)*scaleX+deltaX, p2.y*scaleY+deltaY, p2.x*scaleX+deltaX, p2.y*scaleY+deltaY);
	        path1.cubicTo((p2.x+n*11/20)*scaleX+deltaX, p2.y*scaleY+deltaY, p3.x*scaleX+deltaX, p3.y*scaleY+deltaY, p3.x*scaleX+deltaX, p3.y*scaleY+deltaY);
	        path1.cubicTo(p3.x*scaleX+deltaX, p3.y*scaleY+deltaY, (p4.x+n*11/20)*scaleX+deltaX, p4.y*scaleY+deltaY, p4.x*scaleX+deltaX, p4.y*scaleY+deltaY);
	        path1.cubicTo((p4.x-n*11/20)*scaleX+deltaX, p4.y*scaleY+deltaY, p1.x*scaleX+deltaX, (p1.y-n*11/20)*scaleY+deltaY, p1.x*scaleX+deltaX, p1.y*scaleY+deltaY);
	        path1.close();
	        path1.setFillType(Path.FillType.EVEN_ODD);
	        
	        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
	        paint1.setColor(Color.rgb(0, 0x77, 0xcb));
	        
	        canvas.drawPath(path1, paint1);

	        Path path2 = new Path();
	        
	        path2.moveTo(p5.x*scaleX+deltaX, p5.y*scaleY+deltaY);
	        path2.cubicTo(p5.x*scaleX+deltaX, p5.y*scaleY+deltaY, (p6.x-m*11/20)*scaleX+deltaX, p6.y*scaleY+deltaY, p6.x*scaleX+deltaX, p6.y*scaleY+deltaY);
	        path2.cubicTo((p6.x+m*11/20)*scaleX+deltaX, p6.y*scaleY+deltaY, p7.x*scaleX+deltaX, (p7.y+m*11/20)*scaleY+deltaY, p7.x*scaleX+deltaX, p7.y*scaleY+deltaY);
	        path2.cubicTo(p7.x*scaleX+deltaX, (p7.y-m*11/20)*scaleY+deltaY, (p8.x+m*11/20)*scaleX+deltaX, p8.y*scaleY+deltaY, p8.x*scaleX+deltaX, p8.y*scaleY+deltaY);
	        path2.cubicTo((p8.x-m*11/20)*scaleX+deltaX, p8.y*scaleY+deltaY, p5.x*scaleX+deltaX, p5.y*scaleY+deltaY, p5.x*scaleX+deltaX, p5.y*scaleY+deltaY);
	        
	        path2.close();
	        path2.setFillType(Path.FillType.EVEN_ODD);
	        
	        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
	        paint2.setColor(Color.rgb(0xdf, 0x2a, 0));

	        canvas.drawPath(path2, paint2);
//	        
//	        CGPathRelease(path1);
//	        CGPathRelease(path2);
	    }
	}

	class CGPoint{
		public float x, y;
		public CGPoint(float X, float Y){
			x = X;
			y = Y;
		}
	}
}
