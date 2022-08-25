package pl.com.mobilelabs.mobilus.view.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecorator extends RecyclerView.ItemDecoration {
  private boolean includeEdge;
  
  private int spacing;
  
  private int spanCount;
  
  public GridSpacingItemDecorator(int paramInt1, int paramInt2, boolean paramBoolean) {
    this.spanCount = paramInt1;
    this.spacing = paramInt2;
    this.includeEdge = paramBoolean;
  }
  
  public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    int i = paramRecyclerView.getChildAdapterPosition(paramView);
    int j = i % this.spanCount;
    if (this.includeEdge) {
      paramRect.left = this.spacing - this.spacing * j / this.spanCount;
      paramRect.right = (j + 1) * this.spacing / this.spanCount;
      if (i < this.spanCount)
        paramRect.top = this.spacing; 
      paramRect.bottom = this.spacing;
    } else {
      paramRect.left = this.spacing * j / this.spanCount;
      paramRect.right = this.spacing - (j + 1) * this.spacing / this.spanCount;
      if (i >= this.spanCount)
        paramRect.top = this.spacing; 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\GridSpacingItemDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */