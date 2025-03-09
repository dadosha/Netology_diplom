package ru.iteco.fmhandroid.matchers;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewMatcher {
    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public Matcher<View> atPosition(final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                if (recyclerView == null || recyclerView.getAdapter() == null) {
                    return false;
                }
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && view == viewHolder.itemView;
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("Element at position " + position + " in RecyclerView");
            }
        };
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                if (recyclerView == null || recyclerView.getAdapter() == null) {
                    return false;
                }
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return targetView != null && view == targetView;
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("Element at position " + position + " inside view with id " + targetViewId);
            }
        };
    }
}
