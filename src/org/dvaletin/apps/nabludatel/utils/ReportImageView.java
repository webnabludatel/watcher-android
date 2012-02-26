package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint();
	        p.setColor(Color.rgb(180, 180, 180));
	        canvas.drawPath(arcPath, p);
	    } else if ( this.goodCount == 0 && this.badCount > 0) {
	        // #df2a00
			Path arcPath = new Path();
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint();
	        p.setColor(Color.rgb(0xdf, 0x2a, 0));
	        canvas.drawPath(arcPath, p);

	    } else if ( this.goodCount > 0 && this.badCount == 0 ) {
	        // #0077cb
	    	Path arcPath = new Path();
	        arcPath.addCircle(this.getWidth()/2, this.getHeight()/2, this.getHeight()/2, Path.Direction.CW);
	        arcPath.setFillType(Path.FillType.EVEN_ODD);
	        Paint p = new Paint();
	        p.setColor(Color.rgb(0, 0x77, 0xcb));
	        canvas.drawPath(arcPath, p);
	    	
	    } else {
	        float n = (float) Math.sqrt((double)this.goodCount);
	        float m = (float) Math.sqrt((double)this.badCount);
	        float x = (n+m)/2.0f;
//	        
//	        if ( n > m ) {
//	            CGContextScaleCTM(ctx, rect.getWidth()/(2*n+2*m+x), rect.getHeight()/(2*n));
//	            CGContextTranslateCTM(ctx, n, n);
//	        } else {
//	            CGContextScaleCTM(ctx, rect.getWidth()/(2*n+2*m+x), rect.getHeight()/(2*m));
//	            CGContextTranslateCTM(ctx, n, m);
//	        }
//	        
	        CGPoint p1 = new CGPoint(-n, 0);
	        CGPoint p2 = new CGPoint(0, n);
	        CGPoint p3 = new CGPoint(n+x*n/(n+m), 0);
	        CGPoint p4 = new CGPoint(0, -n);
	        
	        CGPoint p5 = new CGPoint(n+x*n/(n+m), 0);
	        CGPoint p6 = new CGPoint(n+m+x, m);
	        CGPoint p7 = new CGPoint(n+2*m+x, 0);
	        CGPoint p8 = new CGPoint(n+m+x, -m);
//	        
	        Path path1 = new Path();
	        path1.moveTo(p1.x, p1.y);
	        path1.cubicTo(p1.x, p1.y+n*11/20, p2.x-n*11/20, p2.y, p2.x, p2.y);
	        path1.cubicTo(p2.x+n*11/20, p2.y, p3.x, p3.y, p3.x, p3.y);
	        path1.cubicTo(p3.x, p3.y, p4.x+n*11/20, p4.y, p4.x, p4.y);
	        path1.cubicTo(p4.x-n*11/20, p4.y, p1.x, p1.y-n*11/20, p1.x, p1.y);
	        path1.close();
	        path1.setFillType(Path.FillType.EVEN_ODD);
	        
	        Paint paint1 = new Paint();
	        paint1.setColor(Color.rgb(0, 0x77, 0xcb));
	        
	        canvas.drawPath(path1, paint1);
//	        CGContextAddPath(ctx, path1);
//	        CGContextFillPath(ctx);
//	        
//	        CGMutablePathRef path2 = CGPathCreateMutable();
//	        CGPathMoveToPoint(path2, nil, p5.x, p5.y);
//	        CGPathAddCurveToPoint(path2, NULL, p5.x, p5.y, p6.x-m*11/20, p6.y, p6.x, p6.y);
//	        CGPathAddCurveToPoint(path2, NULL, p6.x+m*11/20, p6.y, p7.x, p7.y+m*11/20, p7.x, p7.y);
//	        CGPathAddCurveToPoint(path2, NULL, p7.x, p7.y-m*11/20, p8.x+m*11/20, p8.y, p8.x, p8.y);
//	        CGPathAddCurveToPoint(path2, NULL, p8.x-m*11/20, p8.y, p5.x, p5.y, p5.x, p5.y);
//	        CGPathCloseSubpath(path2);
//	        CGContextSetRGBFillColor(ctx, 0xdf/255.0f, 0x2a/255.0f, 0, 1);
//	        CGContextAddPath(ctx, path2);
//	        CGContextFillPath(ctx);
//	        
//	        CGPathRelease(path1);
//	        CGPathRelease(path2);
	    }
	}

	private class CGPoint{
		public float x, y;
		public CGPoint(float X, float Y){
			x = X;
			y = Y;
		}
	}
}
