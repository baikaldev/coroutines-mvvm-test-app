package studio.inprogress.postertestapp.ui.itemDecorator

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecorator(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.right = space / 2
        outRect.left = space / 2
        outRect.top = space / 2
        outRect.bottom = space / 2
    }
}